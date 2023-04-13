package org.seccionb.webapp.controllers;


import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.RowEditEvent;
import org.seccionb.webapp.entities.*;
import org.seccionb.webapp.services.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@ViewScoped
@Named("contactoController")
public class ContactoController implements Serializable {

    private static final long serialVersionUID = 17760780L;

    @Inject
    private FacesContext facesContext;

    private Long personaId;
    private Persona personaContacto;

    private Long contactoId;
    private Contacto contacto;

    private List<Contacto> contactos;
    private List<TipoContacto> tiposContacto;

    Pattern patternEmail = Pattern
            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    Pattern patternTelefono = Pattern.compile("^\\d{10}$");

    @Inject
    private EstudianteService estudianteService;
    @Inject
    private PersonaService personaService;
    @Inject
    private ContactoService contactoService;


    @PostConstruct
    public void init() {
        System.out.println("Invocando PostConstruct");

        this.tiposContacto = contactoService.listarTipoContactos();
        this.contactos = new ArrayList<>();

        if (personaId != null && personaId > 0) {
            this.contactos = contactoService.contactosPorIdPersona(personaId);
        }

        System.out.println("PERSONA ID: " + personaId);
        System.out.println("PERSONA: " + personaContacto);
        this.personaContacto = new Persona();
        if (personaId != null && personaId > 0) {
            personaService.porId(personaId).ifPresent(p -> {
                this.personaContacto = p;
            });
        }

        System.out.println("ID CONTACTO: " + contactoId);
        System.out.println("CONTACTO: " + contacto);
        this.contacto = new Contacto();
        if (contactoId != null && contactoId > 0) {
            contactoService.porId(contactoId).ifPresent(p -> {
                this.contacto = p;
            });
        }
    }

    public String guardar() {
        System.out.println("PERSONA ID:" + personaId);
        System.out.println("PERSONA: " + personaContacto);

        Boolean continuar=true;
        if(contacto.getTipoContacto().getId()==1){
            Matcher mather = patternEmail.matcher(contacto.getContacto());
            if (mather.find() == true) {
                System.out.println("El email ingresado es válido.");
            } else {
                continuar=false;
                System.out.println("El email ingresado es inválido.");
                FacesMessage msg = new FacesMessage("Email no válido", String.valueOf(contacto.getContacto()));
                facesContext.addMessage(null,msg);
            }
        }else{
            Matcher mather = patternTelefono.matcher(contacto.getContacto());
            if (mather.find() == true) {
                System.out.println("El telefono ingresado es válido.");
            } else {
                continuar=false;
                System.out.println("El telefono ingresado es inválido.");
                FacesMessage msg = new FacesMessage("Número de teléfono no válido", String.valueOf(contacto.getContacto()));
                facesContext.addMessage(null,msg);
            }
        }


        //Validacion de contacto único
        for (Contacto connn : contactoService.listar()) {
            if (connn.getContacto().equals(this.contacto.getContacto()) &&
                    connn.getId() != this.contacto.getId()) {
                facesContext.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,"Contacto no disponible", "" + contacto.getContacto() + "' ya ha sido registrado."));
                continuar = false;
                break;
            }
        }

        if(continuar) {
            if (contacto.getId() != null && contacto.getId() > 0) {
                FacesMessage msg = new FacesMessage("Contacto actualizado", String.valueOf(contacto.getContacto()));
                facesContext.addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage("Contacto agregado con éxito", String.valueOf(contacto.getContacto()));
                facesContext.addMessage(null, msg);
            }
            contacto.setPersonaId(personaId);

            System.out.println("CONTACTO======================" + contacto);
            contactoService.guardar(contacto);

            contacto = new Contacto();
            this.contactos = contactoService.contactosPorIdPersona(personaId);
            return "gestionContactos.jsf?idPersona=" + personaId + "&faces-redirect=true";
        }else{
            return "gestionContactos.jsf?idPersona=" + personaId + "&faces-redirect=true";
        }
    }

    public void editar(Long idContactoRecived) {
        System.out.println("ID CONTACTO EDITAR: " + idContactoRecived);
        this.contactoId = idContactoRecived;
        contactoService.porId(idContactoRecived).ifPresent(c->{
            this.contacto = c;
        });
        System.out.println("CONTACTO:::::::::::::::::" + contacto);
    }

    public String eliminarContacto(){
        this.personaId = contacto.getPersonaId();
        System.out.println("CONTACTO A ELIMINAR: " + contacto);
        contactoService.eliminar(contacto.getId());

        FacesMessage msg = new FacesMessage("Contacto eliminado", String.valueOf(contacto.getContacto()));
        facesContext.addMessage(null, msg);
        return "gestionContactos.jsf?idPersona=" + personaId + "&faces-redirect=true";
    }

    public void actualizarContacto() {
        cerrarDialogo();
    }

    public void cerrarDialogo() {
        System.out.println("Cerrando ventana de dialogo...");
        contacto = new Contacto();
    }

    public void onRowEdit(RowEditEvent<Contacto> event) {

        Boolean continuar=true;
        if(event.getObject().getTipoContacto().getId()==1){
            Matcher mather = patternEmail.matcher(event.getObject().getContacto());
            if (mather.find() == true) {
                System.out.println("El email ingresado es válido.");
            } else {
                continuar=false;
                System.out.println("El email ingresado es inválido.");
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Email no válido", String.valueOf(event.getObject().getContacto()));
                facesContext.addMessage(null,msg);
            }
        }else{
            Matcher mather = patternTelefono.matcher(event.getObject().getContacto());
            if (mather.find() == true) {
                System.out.println("El telefono ingresado es válido.");
            } else {
                continuar=false;
                System.out.println("El telefono ingresado es inválido.");
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Número de teléfono no válido", String.valueOf(event.getObject().getContacto()));
                facesContext.addMessage(null,msg);
            }
        }

        //Validacion de contacto único
        for (Contacto connn : contactoService.listar()) {
            if (connn.getContacto().equals(event.getObject().getContacto()) &&
                    connn.getId() != event.getObject().getId()) {
                facesContext.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,"Contacto no disponible", "" + event.getObject().getContacto() + "' ya ha sido registrado."));
                continuar = false;
                break;
            }
        }

        if(continuar==true) {
            System.out.println("Actualizando contacto: " + event.getObject());
            FacesMessage msg = new FacesMessage("Contacto actualizado", String.valueOf(event.getObject().getContacto()));
            contactoService.guardar(event.getObject());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onRowCancel(RowEditEvent<Contacto> event) {
        FacesMessage msg = new FacesMessage("Contacto sin cambios", String.valueOf(event.getObject().getContacto()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public Long getContactoId() {
        return contactoId;
    }

    public void setContactoId(Long contactoId) {
        this.contactoId = contactoId;
    }

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public Persona getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(Persona personaContacto) {
        this.personaContacto = personaContacto;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public List<TipoContacto> getTiposContacto() {
        return tiposContacto;
    }

    public void setTiposContacto(List<TipoContacto> tiposContacto) {
        this.tiposContacto = tiposContacto;
    }

}
