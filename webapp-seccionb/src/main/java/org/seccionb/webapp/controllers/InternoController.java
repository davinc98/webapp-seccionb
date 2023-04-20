package org.seccionb.webapp.controllers;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;
import org.seccionb.webapp.entities.*;
import org.seccionb.webapp.services.*;

import java.io.*;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.*;

@Model
@Named("internoController")
public class InternoController implements Serializable {

    @Inject
    private FacesContext facesContext;

    private Long internoId;
    private Interno interno;

    private Long personaId;
    private Persona persona;


    private Long cargoId;
    private Cargo cargo;


    private List<Interno> internos;

    private List<Interno> estudiantesActivos;
    private List<Interno> estudiantesEsgresados;
    private List<Interno> estudiantesBajaTemporal;
    private List<Interno> estudiantesBajaDefinitiva;
    private List<Interno> estudiantesBuscar;

    private Integer totalUnam;
    private Integer totalIpn;
    private Integer totalUam;
    private Integer totalOtros;

    private UploadedFile originalImageFile;
    private String fotoBase64;

    private String pwd1;
    private String pwd2;

    private List<Contacto> contactos;

    @Inject
    private EstudianteService estudianteService;
    @Inject
    private PersonaService personaService;
    @Inject
    private CargoService cargoService;

    @Inject
    private EstudianteCargoService estudianteCargoService;

    @Inject
    private ContactoService contactoService;


    @PostConstruct
    public void init() {
        System.out.println("Invocando constructor INTERNO CONTROLLER");
        this.estudiantesActivos = estudianteService.listarEstudiantesActivos();
        this.estudiantesEsgresados = estudianteService.listarEstudiantesEgresados();
        this.estudiantesBajaTemporal = estudianteService.listarEstudiantesBajaTemporal();
        this.estudiantesBajaDefinitiva = estudianteService.listarEstudiantesBajaDefinitiva();

        this.totalUnam = 0;
        this.totalIpn = 0;
        this.totalUam = 0;
        this.totalOtros = 0;
        this.estudiantesActivos.forEach(e -> {
            switch (e.getEscuela().getId().intValue()) {
                case 1:
                    this.totalUnam++;
                    break;
                case 2:
                    this.totalIpn++;
                    break;
                case 3:
                    this.totalUam++;
                    break;
                default:
                    this.totalOtros++;
                    break;
            }
        });

        FotoTemporal fotoTmpBase64 = personaService.getImagenTemporalBase64();
        if (fotoTmpBase64 != null) {
            System.out.println("Foto temporal recuperada: " + fotoTmpBase64.getId());
            this.fotoBase64 = fotoTmpBase64.getFotoBase64();
        }

        persona = new Persona();
        interno = new Interno();
        cargo = new Cargo();
        estudiantesBuscar = new ArrayList<>();
    }

    @Produces
    @Model
    public List<Carrera> carreras() {
        return estudianteService.listarCarreras();
    }

    @Produces
    @Model
    public List<Escuela> escuelas() {
        return estudianteService.listarEscuelas();
    }

    @Produces
    @Model
    public List<SituacionEscolar> situacionesEscolares() {
        return estudianteService.listarSituacionesEscolares();
    }

    @Produces
    @Model
    public List<EstadoProcedencia> estadosDeProcedencia() {
        return estudianteService.listarEstadosDeProcedencia();
    }


    @Produces
    @Model
    public List<Cargo> cargos() {
        return cargoService.listar();
    }

    @Produces
    @Model
    public List<EstatusInterno> estatusEstudiantes() {
        return estudianteService.listarEstatusEstudiantes();
    }

    @Produces
    @Model
    public Interno estudiante() {
        this.interno = new Interno();
        this.interno.setFechaIngreso(LocalDate.now());
        if (internoId != null && internoId > 0) {
            estudianteService.porId(internoId).ifPresent(p -> {
                this.interno = p;
            });
        }
        return this.interno;
    }

    @Produces
    @Model
    public Persona persona() {
        this.persona = new Persona();
        if (interno.getId() != null && interno.getId() > 0) {
            personaService.porId(interno.getPersona().getId()).ifPresent(p -> {
                this.persona = p;
            });
        } else {
//            if(personaId==null)
//                personaService.eliminarImagenTemporalBase64();
        }
        return this.persona;
    }

    @Produces
    @Model
    public Cargo cargo() {
        this.cargo = new Cargo();
        if (cargoId != null && cargoId > 0) {
            System.out.println("Buscar cargo por id " + cargoId);
            cargoService.porId(cargoId).ifPresent(c -> {
                this.cargo = c;
            });
        }
        return this.cargo;
    }

    public String guardar() {
        Boolean continuar = true;

        System.out.println("");
        System.out.println("ESTUDIANTE===================" + interno);
        System.out.println("");
        System.out.println("PERSONA======================" + persona);
        System.out.println("");
        System.out.println("CARGOID======================" + cargoId);
        System.out.println("CARGO======================" + cargo);
        System.out.println("FOTO BASE64======================" + fotoBase64);

        //Validacion de curp
        for (Persona per : personaService.listar()) {
            if (per.getCurp().equals(this.persona.getCurp()) &&
                    per.getId() != this.persona.getId()) {
                facesContext.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "CURP no disponible", "El curp '" + persona.getCurp() + "' ya ha sido registrado."));
                continuar = false;
                break;
            }
        }

        //Validacion de nombre de usuario
//        for (Interno est : estudianteService.listar()) {
//            if (est.getUsuario().equals(this.interno.getUsuario()) &&
//                    est.getId() != this.interno.getId()) {
//                facesContext.addMessage(null,
//                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Nombre de usuario no disponible", "El nombre de usuario '" + interno.getUsuario() + "' ya ha sido registrado.\n Intente con otro."));
//                continuar = false;
//                break;
//            }
//        }

        //Validacion de password
//        if (this.pwd1 != null && this.pwd1 != "") {
//            if (this.pwd1.equals(this.pwd2)) {
//                interno.setContrasenia(getSHA256(pwd1));
//                System.out.println("Cifra password: " + interno.getContrasenia());
//            } else {
//                continuar = false;
//                facesContext.addMessage(null,
//                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Contraseñas no coinciden", "Las contraseñas no coinciden. \nIntente nuevamente."));
//            }
//        }

        //Validacion de password(actualizacion)
//        System.out.println("Nueva Contra: " + pwd1);
//        if (this.pwd1 != null && this.pwd1 != "") {
//            interno.setContrasenia(getSHA256(pwd1));
//            System.out.println("Cifra password: " + pwd1);
//        }

        if (continuar) {
            if (interno.getId() != null && interno.getId() > 0) {
                facesContext.addMessage(null,
                        new FacesMessage("Usuario actualizado. ", interno.getId() + ""));
            } else {
                facesContext.addMessage(null,
                        new FacesMessage("Usuario creado con éxito. ", interno.getId() + ""));
            }

            if (this.fotoBase64 != null) {
                persona.setFotoBase64(this.fotoBase64);
                System.out.println("PERSONA======================" + persona);
                personaService.eliminarImagenTemporalBase64();
            }


            personaService.guardar(persona);
            interno.setPersona(persona);
//            interno.setActivo(1);
            estudianteService.guardar(interno);

            List<InternoCargo> misCargos = estudianteCargoService.getCargosPorIdEstudiante(interno.getId());
            //Asignar cargo
            if (cargo.getId() != null && cargo.getId() > 0 && !misCargos.isEmpty()) {
                cargoService.porId(cargo.getId()).ifPresent(c -> {
                    this.cargo = c;
                });
            } else {
                InternoCargo estudianteCargo = new InternoCargo();
                estudianteCargo.setEstudianteId(interno.getId());
                estudianteCargo.setCargoId(5L);
                estudianteCargoService.guardar(estudianteCargo);
            }


            interno = new Interno();
            persona = new Persona();
            cargo = new Cargo();
            return "gestionInternos.xhtml?faces-redirect=true";
        } else {
            return "#";
        }
    }

    public String verInformacionPerfil(String username) {
//        this.interno = estudianteService.getEstudiantePorNombreUsuario(username);
        this.interno = new Interno();
        this.internoId= interno.getId();
        for (InternoCargo ec : estudianteCargoService.getCargosPorIdEstudiante(interno.getId())) {
            this.cargoId = ec.getCargoId();
        }
        this.persona = interno.getPersona();
        this.personaId= interno.getPersona().getId();

        System.out.println("INFO ESTUDIANTE: "+ interno);
        System.out.println("INFO PERSONA: "+persona);

        if (personaId != null && personaId > 0) {
            this.contactos = contactoService.contactosPorIdPersona(personaId);
        }
        System.out.println("INFO CONTACTOS: "+contactos);

        return "perfilUsuario.xhtml";
    }

    public String editar(Long idRecived, Long idPersonaRecived) {
        System.out.println("Entrando a EDITAR");

        this.internoId = idRecived;
        this.interno = new Interno();

        this.personaId = idPersonaRecived;
        this.persona = new Persona();

        this.cargo = new Cargo();

        FotoTemporal fotoTmpBase64 = personaService.getImagenTemporalBase64();
        if (fotoTmpBase64 != null) {
            System.out.println("Foto temporal recuperada: " + fotoTmpBase64.getId());
            this.fotoBase64 = fotoTmpBase64.getFotoBase64();
        }

        if (idRecived != null && idRecived > 0) {
            estudianteService.porId(idRecived).ifPresent(e -> {
                this.interno = e;
                for (InternoCargo ec : estudianteCargoService.getCargosPorIdEstudiante(e.getId())) {
                    this.cargoId = ec.getCargoId();
                }
                this.persona = e.getPersona();
            });
        }

        System.out.println("PERSONA:::::::::::::::::" + persona);
        System.out.println("ESTUDIANTE::::::::::::::::" + interno);
        System.out.println("CARGO::::::::::::::::" + cargoId);
        return "formEstudiante.xhtml";
    }

    public String crear() {
        this.interno = new Interno();
        this.persona = new Persona();
        this.cargo = new Cargo();
        this.cargoId = 5L;

        System.out.println("ESTUDIANTE+++++++++++++" + interno);
        System.out.println("CARGO++++++++++++++++++" + cargoId);
        System.out.println("PERSONA++++++++++++++++" + persona);
        return "formEstudiante.xhtml";
    }

    public void eliminar(Interno est) {
        estudianteService.eliminar(est.getId());
        facesContext.addMessage(null,
                new FacesMessage("Usuario eliminado.", est.getId() + ""));
    }

    public void handleFileUpload(FileUploadEvent event) {
        this.originalImageFile = null;
        UploadedFile file = event.getFile();
        System.out.println("Cargando foto... ");

        if (file != null && file.getContent() != null && file.getContent().length > 0 && file.getFileName() != null) {
            this.originalImageFile = file;
            if (originalImageFile.getContent() != null) {
                String extension = FilenameUtils.getExtension(file.getFileName());
                String enc = "data:image/" + extension + ";base64,";

                String imageBase64 = Base64.getEncoder().encodeToString(originalImageFile.getContent());

                if (imageBase64 != null && !imageBase64.trim().equals("")) {
                    this.fotoBase64 = enc + imageBase64;

                    FotoTemporal fotoTemporal = new FotoTemporal();
                    fotoTemporal.setFotoBase64(fotoBase64);
                    if (persona.getId() != null)
                        fotoTemporal.setPersonaId(persona.getId());

                    System.out.println("Almacenando foto temporal en bd: " + fotoTemporal);
                    personaService.guardarImagenTemporalBase64(fotoTemporal);
                }
            }

            FacesMessage msg = new FacesMessage("Subir imagen ", "Imagen " + this.originalImageFile.getFileName() + " cargada");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void borrarFoto(Long perId) {

        System.out.println("Eliminando foto de perfil...");
        personaService.eliminarFotoBase64PorId(perId);

        //Mandar la imagen a imagen temporal por si no se desean guardar los cambios

        FacesMessage msg = new FacesMessage("Eliminar imagen ", "Imagen de perfil eliminada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onClose(CloseEvent event) {

        System.out.println("Eliminando solicitude de ingreso");


        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud Eliminada",
                "La solicitud de ingreso fue eliminada.");
        FacesContext.getCurrentInstance().addMessage(null, message);
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

    public Long getInternoId() {
        return internoId;
    }

    public void setInternoId(Long internoId) {
        this.internoId = internoId;
    }

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public Long getCargoId() {
        return cargoId;
    }

    public void setCargoId(Long cargoId) {
        this.cargoId = cargoId;
    }

    public List<Interno> getEstudiantes() {
        return internos;
    }

    public void setEstudiantes(List<Interno> internos) {
        this.internos = internos;
    }

    public List<Interno> getEstudiantesBuscar() {
        return estudiantesBuscar;
    }

    public void setEstudiantesBuscar(List<Interno> estudiantesBuscar) {
        this.estudiantesBuscar = estudiantesBuscar;
    }

    public List<Interno> getEstudiantesActivos() {
        return estudiantesActivos;
    }

    public void setEstudiantesActivos(List<Interno> estudiantesActivos) {
        this.estudiantesActivos = estudiantesActivos;
    }

    public List<Interno> getEstudiantesEsgresados() {
        return estudiantesEsgresados;
    }

    public void setEstudiantesEsgresados(List<Interno> estudiantesEsgresados) {
        this.estudiantesEsgresados = estudiantesEsgresados;
    }

    public List<Interno> getEstudiantesBajaTemporal() {
        return estudiantesBajaTemporal;
    }

    public void setEstudiantesBajaTemporal(List<Interno> estudiantesBajaTemporal) {
        this.estudiantesBajaTemporal = estudiantesBajaTemporal;
    }

    public List<Interno> getEstudiantesBajaDefinitiva() {
        return estudiantesBajaDefinitiva;
    }

    public void setEstudiantesBajaDefinitiva(List<Interno> estudiantesBajaDefinitiva) {
        this.estudiantesBajaDefinitiva = estudiantesBajaDefinitiva;
    }

    public Integer getTotalUnam() {
        return totalUnam;
    }

    public void setTotalUnam(Integer totalUnam) {
        this.totalUnam = totalUnam;
    }

    public Integer getTotalIpn() {
        return totalIpn;
    }

    public void setTotalIpn(Integer totalIpn) {
        this.totalIpn = totalIpn;
    }

    public Integer getTotalUam() {
        return totalUam;
    }

    public void setTotalUam(Integer totalUam) {
        this.totalUam = totalUam;
    }

    public Integer getTotalOtros() {
        return totalOtros;
    }

    public void setTotalOtros(Integer totalOtros) {
        this.totalOtros = totalOtros;
    }

    public StreamedContent getOriginalImageFile() {
        return DefaultStreamedContent.builder()
                .contentType(originalImageFile == null ? null : originalImageFile.getContentType())
                .stream(() -> {
                    if (originalImageFile == null
                            || originalImageFile.getContent() == null
                            || originalImageFile.getContent().length == 0) {
                        return null;
                    }

                    try {
                        System.out.println("STREAMED CONTENT FOTO: " + new ByteArrayInputStream(originalImageFile.getContent()));
                        return new ByteArrayInputStream(originalImageFile.getContent());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .build();
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

    public String getFotoBase64() {
        return fotoBase64;
    }

    public void setFotoBase64(String fotoBase64) {
        this.fotoBase64 = fotoBase64;
    }

    public void setOriginalImageFile(UploadedFile originalImageFile) {
        this.originalImageFile = originalImageFile;
    }


    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }
}
