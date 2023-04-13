package org.seccionb.webapp.controllers;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import org.seccionb.webapp.entities.*;
import org.seccionb.webapp.services.CajonEstacionamientoService;
import org.seccionb.webapp.services.EstudianteService;
import org.seccionb.webapp.services.PersonaService;
import org.seccionb.webapp.services.VehiculoService;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@ViewScoped
@Named("vehiculoController")
public class VehiculoController implements Serializable {

    private static final long serialVersionUID = 134457L;
    @Inject
    private FacesContext facesContext;

    private Long personaId;
    private Persona personaVehiculo;

    private Long vehiculoId;
    private Vehiculo vehiculo;

    private List<Vehiculo> vehiculos;



    private List<EstadoProcedencia> estadosProcedencia;
    private List<CajonEstacionamiento> cajonesEstacionamiento;
    private List<EstatusVehiculo> estatusVehiculo;
    private List<TipoTarjeton> tiposTarjeton;

    private List<String> anios;
    LocalDate current_date = LocalDate.now();
    private String anioActual = current_date.getYear()+"";


    @Inject
    private VehiculoService vehiculoService;

    @Inject
    private PersonaService personaService;

    @Inject
    private EstudianteService estudianteService;

    @Inject
    private CajonEstacionamientoService cajonEstacionamientoService;

    @PostConstruct
    public void init() {
        System.out.println("Invocando PostConstruct");

        this.estadosProcedencia = estudianteService.listarEstadosDeProcedencia();
        this.cajonesEstacionamiento = cajonEstacionamientoService.listar();
        this.estatusVehiculo = vehiculoService.estatusVehiculo();
        this.tiposTarjeton = vehiculoService.tiposTarjeton();

        this.vehiculos = new ArrayList<>();

        if (personaId != null && personaId > 0) {
            this.vehiculos = vehiculoService.vehiculosPorIdPersona(personaId);
        }

        System.out.println("PERSONA ID: " + personaId);
        System.out.println("PERSONA: " + personaVehiculo);
        this.personaVehiculo = new Persona();
        if (personaId != null && personaId > 0) {
            personaService.porId(personaId).ifPresent(p -> {
                this.personaVehiculo = p;
            });
        }

        LocalDate current_date = LocalDate.now();
        int year = current_date.getYear();
        anios = new ArrayList<>();
        for(int i = 0; i<=50; i++){
            this.anios.add(""+year);
            year--;
        }


        System.out.println("ID VEHICULO: " + vehiculo);
        System.out.println("VEHICULO: " + vehiculo);
        vehiculo = new Vehiculo();
        if (vehiculoId != null && vehiculoId > 0) {
            vehiculoService.porId(vehiculoId).ifPresent(p -> {
                this.vehiculo = p;
            });
        }

    }


    public String guardar() {
        System.out.println("PERSONA ID:" + personaId);
        System.out.println("PERSONA: " + personaVehiculo);

        Boolean continuar=true;
        //Validacion de matricula única
        for (Vehiculo vehh : vehiculoService.listar()) {
            if (vehh.getMatricula().equals(this.vehiculo.getMatricula()) &&
                    vehh.getId() != this.vehiculo.getId()) {
                facesContext.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,"Matrícula no disponible", "La matrícula '" + this.vehiculo.getMatricula() + "' ya ha sido registrada."));
                continuar = false;
                break;
            }
        }

        if(continuar) {

            if (vehiculo.getId() != null && vehiculo.getId() > 0) {
                facesContext.addMessage(null,
                        new FacesMessage(String.format("Información de vehiculo actualizada. ", vehiculo.getId())));
            } else {
                facesContext.addMessage(null,
                        new FacesMessage(String.format("Vehiculo creado con éxito. ", vehiculo.getId())));
            }

            vehiculo.setPersonaId(personaId);
            System.out.println("VEHICULO======================" + vehiculo);
            vehiculoService.guardar(vehiculo);

            vehiculo = new Vehiculo();

            this.vehiculos = vehiculoService.vehiculosPorIdPersona(personaId);
            return "gestionVehiculosPersona.jsf?idPersona=" + personaId + "&faces-redirect=true";
        }else{
            return "gestionVehiculosPersona.jsf?idPersona=" + personaId + "&faces-redirect=true";
        }
    }

    public void editar(Long idVehiculoRecived) {
        System.out.println("ID VEHICULO EDITAR: " + idVehiculoRecived);
        this.vehiculoId = idVehiculoRecived;

        vehiculoService.porId(idVehiculoRecived).ifPresent(c->{
            this.vehiculo = c;
        });
        System.out.println("VEHICULO:::::::::::::::::" + vehiculo);
    }

    public String eliminarVehiculo() {
        this.personaId = vehiculo.getPersonaId();
        System.out.println("VEHICULO A ELIMINAR: " + vehiculo);
        vehiculoService.eliminar(vehiculo.getId());
        facesContext.addMessage(null,
                new FacesMessage(String.format("Vehiculo eliminado.")));
        return "gestionVehiculosPersona.jsf?idPersona=" + personaId + "&faces-redirect=true";
    }

    public void verInformacion(Long idVehiculoRecived) {
        this.vehiculoId = idVehiculoRecived;
        this.vehiculo = new Vehiculo();
        if (idVehiculoRecived != null && idVehiculoRecived > 0) {
            vehiculoService.porId(idVehiculoRecived).ifPresent(p -> {
                this.vehiculo = p;
            });
        }
        System.out.println("VEHICULO:::::::::::::::::" + vehiculo);
    }

    public void actualizarVehiculo() {
        cerrarDialogo();
    }

    public void cerrarDialogo() {
        System.out.println("Cerrando ventana de dialogo...");
        vehiculo = new Vehiculo();
    }
    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public List<String> getAnios() {
        return anios;
    }

    public void setAnios(List<String> anios) {
        this.anios = anios;
    }

    public String getAnioActual() {
        return anioActual;
    }

    public void setAnioActual(String anioActual) {
        this.anioActual = anioActual;
    }

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public Persona getPersonaVehiculo() {
        return personaVehiculo;
    }

    public void setPersonaVehiculo(Persona personaVehiculo) {
        this.personaVehiculo = personaVehiculo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<EstadoProcedencia> getEstadosProcedencia() {
        return estadosProcedencia;
    }

    public void setEstadosProcedencia(List<EstadoProcedencia> estadosProcedencia) {
        this.estadosProcedencia = estadosProcedencia;
    }

    public List<CajonEstacionamiento> getCajonesEstacionamiento() {
        return cajonesEstacionamiento;
    }

    public void setCajonesEstacionamiento(List<CajonEstacionamiento> cajonesEstacionamiento) {
        this.cajonesEstacionamiento = cajonesEstacionamiento;
    }

    public List<EstatusVehiculo> getEstatusVehiculo() {
        return estatusVehiculo;
    }

    public void setEstatusVehiculo(List<EstatusVehiculo> estatusVehiculo) {
        this.estatusVehiculo = estatusVehiculo;
    }

    public List<TipoTarjeton> getTiposTarjeton() {
        return tiposTarjeton;
    }

    public void setTiposTarjeton(List<TipoTarjeton> tiposTarjeton) {
        this.tiposTarjeton = tiposTarjeton;
    }
}
