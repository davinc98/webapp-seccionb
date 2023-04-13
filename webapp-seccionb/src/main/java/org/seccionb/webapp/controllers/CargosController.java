package org.seccionb.webapp.controllers;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.RowEditEvent;
import org.seccionb.webapp.entities.*;
import org.seccionb.webapp.services.CargoService;
import org.seccionb.webapp.services.EstudianteCargoService;
import org.seccionb.webapp.services.EstudianteService;
import org.seccionb.webapp.services.PersonaService;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@ViewScoped
@Named("cargosController")
public class CargosController implements Serializable {

    @Inject
    private FacesContext facesContext;

    private Long estudianteId;
    private Estudiante estudiante;

    private Long personaId;
    private Persona persona;


    private Long cargoId;
    private Cargo cargo;


    private List<Estudiante> estudiantes;

    private List<EstudianteCargo> estudiantesCargos;

    private List<Cargo> cargos;

    @Inject
    private EstudianteService estudianteService;
    @Inject
    private PersonaService personaService;
    @Inject
    private CargoService cargoService;

    @Inject
    private EstudianteCargoService estudianteCargoService;


    @PostConstruct
    public void init() {
        System.out.println("Invocando PostConstruct");
//        this.estudiantes = estudianteService.listar();
//        System.out.println("TOTAL ESTUDIANTES: "+estudiantes.size());

        this.estudiantesCargos = estudianteCargoService.listar();
        System.out.println("TOTAL ESTUDIANTESCARGOS: "+estudiantesCargos.size());

        this.cargos = cargoService.listar();
        persona = new Persona();
        estudiante = new Estudiante();
        cargo = new Cargo();
    }

    public Persona getPersonaEstudiante(Long idEstudiante){
        this.persona = new Persona();
        this.persona = estudianteService.getPersonaPorIdEstudiante(idEstudiante);
        System.out.println("Persona Estudiante: "+persona);
        return persona;
    }

    public String getCargo(Long idCargo){
        this.cargo = new Cargo();
        System.out.println("CARGO ID: "+idCargo);
        cargoService.porId(idCargo).ifPresent(carg->{
            this.cargo = carg;
        });
        System.out.println("Cargo Estudiante: "+cargo);
        return cargo.getNombre();
    }



    public void onRowEdit(RowEditEvent<EstudianteCargo> event) {
        EstudianteCargo temp = event.getObject();
        System.out.println("EstudianteCargo a editar: "+temp);
        temp.setCargoId(cargo.getId());
        estudianteCargoService.guardar(temp);
        facesContext.addMessage(null,
                new FacesMessage("Estudiante actalizado",String.format("["+event.getObject().getId()+"]")));
    }

    public void onRowCancel(RowEditEvent<EstudianteCargo> event) {
        facesContext.addMessage(null,
                new FacesMessage("Sin cambios",String.format("["+event.getObject().getId()+"]")));
    }



    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
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

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<EstudianteCargo> getEstudiantesCargos() {
        return estudiantesCargos;
    }

    public void setEstudiantesCargos(List<EstudianteCargo> estudiantesCargos) {
        this.estudiantesCargos = estudiantesCargos;
    }
}
