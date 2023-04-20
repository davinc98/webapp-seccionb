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

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Model
@Named("usuarioController")
public class UsuarioController implements Serializable {

//    @Inject
//    private FacesContext facesContext;
//
//    private Long usuarioId;
//    private Usuario usuario;
//
//    private Long personaId;
//    private Persona persona;
//
//
//    private Long cargoId;
//    private Cargo cargo;
//
//
//    private List<Interno> internos;
//
//    private List<Interno> estudiantesActivos;
//    private List<Interno> estudiantesEsgresados;
//    private List<Interno> estudiantesBajaTemporal;
//    private List<Interno> estudiantesBajaDefinitiva;
//    private List<Interno> estudiantesBuscar;
//
//    private Integer totalUnam;
//    private Integer totalIpn;
//    private Integer totalUam;
//    private Integer totalOtros;
//
//    private UploadedFile originalImageFile;
//    private String fotoBase64;
//
//    private String pwd1;
//    private String pwd2;
//
//    private List<Contacto> contactos;
//
//    @Inject
//    private EstudianteService estudianteService;
//    @Inject
//    private PersonaService personaService;
//    @Inject
//    private CargoService cargoService;
//
//    @Inject
//    private EstudianteCargoService estudianteCargoService;
//
//    @Inject
//    private ContactoService contactoService;
//
//
//    @PostConstruct
//    public void init() {
//
//        persona = new Persona();
//        usuario = new Usuario();
//        cargo = new Cargo();
//        estudiantesBuscar = new ArrayList<>();
//    }
//
//
//    @Produces
//    @Model
//    public List<Cargo> cargos() {
//        return cargoService.listar();
//    }
//
//    @Produces
//    @Model
//    public List<EstatusInterno> estatusEstudiantes() {
//        return estudianteService.listarEstatusEstudiantes();
//    }
//
//
//    @Produces
//    @Model
//    public Persona persona() {
//        this.persona = new Persona();
//        if (usuario.getId() != null && usuario.getId() > 0) {
//            personaService.porId(usuario.getPersona().getId()).ifPresent(p -> {
//                this.persona = p;
//            });
//        } else {
////            if(personaId==null)
////                personaService.eliminarImagenTemporalBase64();
//        }
//        return this.persona;
//    }
//
//    @Produces
//    @Model
//    public Cargo cargo() {
//        this.cargo = new Cargo();
//        if (cargoId != null && cargoId > 0) {
//            System.out.println("Buscar cargo por id " + cargoId);
//            cargoService.porId(cargoId).ifPresent(c -> {
//                this.cargo = c;
//            });
//        }
//        return this.cargo;
//    }

}
