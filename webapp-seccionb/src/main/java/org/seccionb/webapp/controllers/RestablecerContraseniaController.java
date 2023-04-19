package org.seccionb.webapp.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.primefaces.event.FlowEvent;
import org.seccionb.webapp.entities.Contacto;
import org.seccionb.webapp.entities.Interno;
import org.seccionb.webapp.entities.SolicitudResetPassword;
import org.seccionb.webapp.services.*;

import java.io.Serializable;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Random;

@ViewScoped
@Named("restablecerContraseniaController")
public class RestablecerContraseniaController implements Serializable {

    private static final long serialVersionUID = 890398L;

    @Inject
    private FacesContext facesContext;

    private String email;
    private String codigoVerificacion;

    private String pwdActual;

    private String pwd1;
    private String pwd2;

    private boolean skip;

    private Boolean renderEnvioCodigo;
    private Boolean renderVerificarCodigo;
    private Boolean renderResetPassword;

    private Random aleatorio = new Random();
    private Long personaId;

    @Inject
    private ContactoService contactoService;

    @Inject
    private SolicitudResetPasswordService solicitudResetPasswordService;

    @Inject
    private EnvioCorreoService envioCorreoService;

    @Inject
    private EstudianteService estudianteService;

    @Inject
    private PersonaService personaService;

    @PostConstruct
    public void init() {
        if (this.email == null || this.email.equals("")) {
            this.renderEnvioCodigo = true;
            this.renderVerificarCodigo = false;
            this.renderResetPassword = false;
        } else {
            this.renderEnvioCodigo = false;
        }


        System.out.println("renderEnvioCodigo: " + renderEnvioCodigo.toString());
        System.out.println("renderVerificarCodigo: " + renderVerificarCodigo.toString());
        System.out.println("renderResetPassword: " + renderResetPassword.toString());
        System.out.println("email: " + email);
        System.out.println("codigo: " + codigoVerificacion);
        System.out.println("pwd1: " + pwd1);
        System.out.println("pwd2: " + pwd2);
    }

    public void save() {
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + email);
        facesContext.addMessage(null, msg);
    }

    public void enviarCodigo() {

        //Verificar email registrado
        Boolean continuar = false;
        for (Contacto contacto : contactoService.listar()) {
            if (contacto.getContacto().equals(this.email)) {
                personaId = contacto.getPersonaId();
                //Agregar verificacion de que la persona sea un estudiante
                continuar = true;
                break;
            }
        }

        if (continuar == false) {
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Correo electrónico no registrado", "Ingrese un correo valido."));
        } else {

            //Generar codigo y alamacenarlo en base de datos
            int A = aleatorio.nextInt(75 - 25 + 1) + 25;
            int B = aleatorio.nextInt(75 - 25 + 1) + 25;
            int C = aleatorio.nextInt(75 - 25 + 1) + 25;

            String codigo = A + "" + B + "" + C;
            System.out.println("Codigo de verificacion generado: " + codigo);

            List<SolicitudResetPassword> solicitudes = solicitudResetPasswordService.listar();
            SolicitudResetPassword solicitud = new SolicitudResetPassword();
            for (SolicitudResetPassword sol : solicitudes) {
                if (sol.getCorreo().equals(this.email)) {
                    solicitud = sol;
                    break;
                }
            }
            //Validar estatus de solicitud anteriores si exixten
            if (solicitud.getId() != null && solicitud.getId() > 0) {
                solicitud.setCodigo(codigo);
                solicitud.setFecha(LocalDate.now());
            } else {
                solicitud.setCodigo(codigo);
                solicitud.setCorreo(this.email);
                solicitud.setEstatus("Pendiente");
                solicitud.setPersonaId(personaId);
                solicitud.setFecha(LocalDate.now());
            }

            //Enviar correo con codigo y guardar solicitud
            if (envioCorreoService.enviarCorreo(solicitud.getCorreo(), "RESTABLECER CONTRASEÑA", "Código de verificación: " + codigo)) {
//            if(true){
                solicitudResetPasswordService.guardar(solicitud);
                facesContext.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Código de verificación enviado", "Revisa tu correo electrónico."));
                this.renderEnvioCodigo = false;
                this.renderVerificarCodigo = true;
            } else {
                facesContext.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al enviar código", "No se pudo enviar el código de verificación al correo " + solicitud.getCorreo() + "."));
            }
        }
    }

    public void verificarCodigo() {
        //Comparar con email
        SolicitudResetPassword solicitud = null;
        for (SolicitudResetPassword sol : solicitudResetPasswordService.listar()) {
            if (sol.getCorreo().equals(this.email)) {
                solicitud = sol;
                break;
            }
        }

        //verificar con codigo almacenado en base de datos
        if (solicitud != null) {
            if (solicitud.getCodigo().equals(this.codigoVerificacion)) {
                System.out.println("Codigo verificado...");
                facesContext.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Código verificado", "Codigo verificado correctamente."));
            } else {
                facesContext.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Código de verificación incorrecto", "El código de verificación ingresado no es correcto, verifique en su correo electrónico."));
            }
        } else {
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al verificar código", "Ocurrió un error al verificar el código.\nInicie el proceso nuevamente."));
        }
    }

    public String restablecerPassword() {
        //Recuperar usuario por idPersona
        System.out.println("Persona ID: " + personaId);
        Interno interno = estudianteService.getEstudiantePorIdPersona(personaId);

        if (interno != null) {
            System.out.println("Usuario: " + interno);
            //Actualizar password

            //Validacion de password
            if (this.pwd1 != null && this.pwd1 != "") {
                if (this.pwd1.equals(this.pwd2)) {
                    interno.setContrasenia(getSHA256(pwd1));
                    System.out.println("Cifra password: " + interno.getContrasenia());
                    estudianteService.guardar(interno);
                    facesContext.addMessage(null,
                            new FacesMessage("Contraseña Restablecida", "La contraseña ha sido restablecida con exito."));
                    return "login.xhtml?faces-redirect=true";
                } else {
                    facesContext.addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN, "Contraseñas no coinciden", "Las contraseñas no coinciden. \nIntente nuevamente."));
                }
            }
        }
        return "#";
    }

    public String cambiarContrasenia(String username) throws ServletException {
        System.out.println("Entrando a cambiar contrasena..." + username);

        Interno interno = estudianteService.getEstudiantePorNombreUsuario(username);
        if (interno != null) {
            System.out.println("ESTUDIANTE: " + interno);

            if (pwdActual != null && pwdActual != "") {
                //Validar contrasenia actual
                String cifra = getSHA256(this.pwdActual);
                if (cifra.equals(interno.getContrasenia())) {
                    //Validacion de password
                    if (this.pwd1 != null && this.pwd1 != "") {
                        if (this.pwd1.equals(this.pwd2)) {
                            interno.setContrasenia(getSHA256(pwd1));
                            System.out.println("Cifra password: " + interno.getContrasenia());
                            estudianteService.guardar(interno);

                            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
                            request.logout();
                            request.getSession().invalidate();

                            facesContext.addMessage(null,
                                    new FacesMessage("Cambio de contraseña", "La contraseña ha sido restablecida con exito."));
                            return "login.xhtml";
                        } else {
                            facesContext.addMessage(null,
                                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Contraseñas no coinciden", "Las contraseñas no coinciden. \nIntente nuevamente."));
                        }
                    }
                } else {
                    //la contrasenia no es valida
                    facesContext.addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN, "Contraseña incorrecta", "Las contraseña actual no es válida. \nIntente nuevamente."));
                    return "#";
                }
            }
        }
        return "#";
    }

    private String getSHA256(String contrasenia) {
        try {
            String password = contrasenia;
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] passwordBytes = password.getBytes();
            byte[] hash = md.digest(passwordBytes);
            String passwordHash = Base64.getEncoder().encodeToString(hash);
            return passwordHash;
        } catch (Exception ex) {
            System.out.println("Error al cifrar la clave");
        }
        return null;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false; //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigoVerificacion() {
        return codigoVerificacion;
    }

    public void setCodigoVerificacion(String codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
    }

    public String getPwd1() {
        return pwd1;
    }

    public void setPwd1(String pwd1) {
        this.pwd1 = pwd1;
    }

    public String getPwd2() {
        return pwd2;
    }

    public void setPwd2(String pwd2) {
        this.pwd2 = pwd2;
    }

    public Boolean getRenderEnvioCodigo() {
        return renderEnvioCodigo;
    }

    public void setRenderEnvioCodigo(Boolean renderEnvioCodigo) {
        this.renderEnvioCodigo = renderEnvioCodigo;
    }

    public Boolean getRenderVerificarCodigo() {
        return renderVerificarCodigo;
    }

    public void setRenderVerificarCodigo(Boolean renderVerificarCodigo) {
        this.renderVerificarCodigo = renderVerificarCodigo;
    }

    public Boolean getRenderResetPassword() {
        return renderResetPassword;
    }

    public void setRenderResetPassword(Boolean renderResetPassword) {
        this.renderResetPassword = renderResetPassword;
    }

    public String getPwdActual() {
        return pwdActual;
    }

    public void setPwdActual(String pwdActual) {
        this.pwdActual = pwdActual;
    }
}
