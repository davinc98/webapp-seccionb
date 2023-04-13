package org.seccionb.webapp.controllers;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.seccionb.webapp.entities.*;
import org.seccionb.webapp.model.PersonaDTO;
import org.seccionb.webapp.services.PersonaService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

@Model
@Named("usuarioEstacionamientoController")
public class UsuarioEstacionamientoController {

    @Inject
    private FacesContext facesContext;

    private Long usuarioEstacionamientoId;
    private Persona usuarioEstacionamiento;


    private List<Persona> usuariosEstacionamiento;

    @Inject
    private PersonaService personaService;


    private PersonaDTO personaDTO;


    @PostConstruct
    public void init() {
        this.usuariosEstacionamiento = personaService.listarUsuariosEstacionamiento();
        personaDTO = new PersonaDTO();
        usuarioEstacionamiento = new Persona();
    }


    @Produces
    @Model
    public Persona usuarioEstacionamiento() {
        this.usuarioEstacionamiento = new Persona();
        if (usuarioEstacionamientoId != null && usuarioEstacionamientoId > 0) {
            personaService.porId(usuarioEstacionamientoId).ifPresent(p -> {
                this.usuarioEstacionamiento = p;
            });
        }
        return this.usuarioEstacionamiento;
    }


    public String guardar() {
        System.out.println("");
        System.out.println("PERSONA======================" + usuarioEstacionamiento);
        System.out.println("");

        if (usuarioEstacionamiento.getId() != null && usuarioEstacionamiento.getId() > 0) {
            facesContext.addMessage(null,
                    new FacesMessage(String.format("Usuario actualizado. ", usuarioEstacionamiento.getId())));
        } else {
            facesContext.addMessage(null,
                    new FacesMessage(String.format("Usuario creado con éxito. ", usuarioEstacionamiento.getId())));
        }

        personaService.guardar(usuarioEstacionamiento);

        usuarioEstacionamiento = new Persona();

        return "gestionEstacionamiento.xhtml?faces-redirect=true";

    }

    public String editar(Long idPersonaRecived) {

        this.usuarioEstacionamientoId = idPersonaRecived;
        this.usuarioEstacionamiento = new Persona();


        if (idPersonaRecived != null && idPersonaRecived > 0) {
            personaService.porId(idPersonaRecived).ifPresent(p -> {
                this.usuarioEstacionamiento = p;
            });
        }
        System.out.println("PERSONA:::::::::::::::::" + usuarioEstacionamiento);
        return "formUsuarioEstacionamiento.xhtml";
    }

    public String crear() {
        this.usuarioEstacionamiento = new Persona();

        System.out.println("USUARIO ESTACIONAMIENTO++++++++++++++++" + usuarioEstacionamiento);
        return "formUsuarioEstacionamiento.xhtml";
    }

    public void eliminar(Persona per) {
        personaService.eliminar(per.getId());
        facesContext.addMessage(null,
                new FacesMessage(String.format("Usuario eliminado.", per.getId())));
    }

    public void verInformacion(Long idPersonaRecived) {
        this.usuarioEstacionamientoId = idPersonaRecived;
        this.usuarioEstacionamiento = new Persona();
        if (idPersonaRecived != null && idPersonaRecived > 0) {
            personaService.porId(idPersonaRecived).ifPresent(p -> {
                this.usuarioEstacionamiento = p;
            });
        }
        System.out.println("PERSONA:::::::::::::::::" + usuarioEstacionamiento);
    }

    public void cerrarDialogo() {
        cerrarDialogo(null);
    }

    public void cerrarDialogo(FacesMessage mensaje) {
        PrimeFaces.current().dialog().closeDynamic(mensaje);
    }


    public Long getUsuarioEstacionamientoId() {
        return usuarioEstacionamientoId;
    }

    public void setUsuarioEstacionamientoId(Long usuarioEstacionamientoId) {
        this.usuarioEstacionamientoId = usuarioEstacionamientoId;
    }

    public List<Persona> getUsuariosEstacionamiento() {
        return usuariosEstacionamiento;
    }

    public void setUsuariosEstacionamiento(List<Persona> usuariosEstacionamiento) {
        this.usuariosEstacionamiento = usuariosEstacionamiento;
    }

    public PersonaDTO getPersonaDTO() {
        return personaDTO;
    }

    public void setPersonaDTO(PersonaDTO personaDTO) {
        this.personaDTO = personaDTO;
    }
}
