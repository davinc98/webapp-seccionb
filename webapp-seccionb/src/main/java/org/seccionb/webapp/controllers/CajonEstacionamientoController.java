package org.seccionb.webapp.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.seccionb.webapp.entities.*;
import org.seccionb.webapp.services.CajonEstacionamientoService;
import org.seccionb.webapp.services.VehiculoService;

import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named("cajonEstacionamientoController")
public class CajonEstacionamientoController implements Serializable {

    private static final long serialVersionUID = 567956L;
    @Inject
    private FacesContext facesContext;

    private Long vehiculoId;
    private Vehiculo vehiculo;

    private Long cajonEstacionamientoId;
    private CajonEstacionamiento cajonEstacionamiento;

    private List<CajonEstacionamiento> cajonesEstacionamiento;

    private List<EstatusCajon> estatusCajon;
    private List<AreaEstacionamiento> areasEstacionamiento;

    @Inject
    private CajonEstacionamientoService cajonEstacionamientoService;

    @Inject
    private VehiculoService vehiculoService;

    @PostConstruct
    public void init() {
        System.out.println("Invocando PostConstruct");

        this.cajonesEstacionamiento = cajonEstacionamientoService.listar();
        this.estatusCajon = cajonEstacionamientoService.estatusCajon();
        this.areasEstacionamiento = cajonEstacionamientoService.areasEstacionamiento();

        System.out.println("ID CAJON: " + vehiculo);
        System.out.println("CAJON: " + vehiculo);
        this.cajonEstacionamiento = new CajonEstacionamiento();
        if (cajonEstacionamientoId != null && cajonEstacionamientoId > 0) {
            cajonEstacionamientoService.porId(cajonEstacionamientoId).ifPresent(p -> {
                this.cajonEstacionamiento = p;
            });
        }

    }


    public String guardar() {
        System.out.println("CAJON ID:" + cajonEstacionamientoId);
        System.out.println("CAJON: " + cajonEstacionamiento);

        if (cajonEstacionamiento.getId() != null && cajonEstacionamiento.getId() > 0) {
            facesContext.addMessage(null,
                    new FacesMessage(String.format("Información de vehiculo actualizada. ")));
        } else {
            facesContext.addMessage(null,
                    new FacesMessage(String.format("Vehiculo creado con éxito. ")));
        }

        cajonEstacionamientoService.guardar(cajonEstacionamiento);

        cajonEstacionamiento = new CajonEstacionamiento();

        this.cajonesEstacionamiento = cajonEstacionamientoService.listar();
        return "gestionEstacionamiento.jsf?faces-redirect=true";
    }

    public void editar(Long idCajonRecived) {
        System.out.println("ID CAJON EDITAR: " + idCajonRecived);
        this.vehiculoId = idCajonRecived;

        cajonEstacionamientoService.porId(idCajonRecived).ifPresent(c->{
            this.cajonEstacionamiento = c;
        });
        System.out.println("CAJON:::::::::::::::::" + cajonEstacionamiento);
    }

    public String eliminarCajon() {
        this.cajonEstacionamientoId = cajonEstacionamiento.getId();
        System.out.println("CAJON A ELIMINAR: " + cajonEstacionamiento);
        cajonEstacionamientoService.eliminar(cajonEstacionamiento.getId());
        facesContext.addMessage(null,
                new FacesMessage(String.format("Cajon eliminado.")));
        return "gestionEstacionamiento.jsf?faces-redirect=true";
    }

    public void verInformacion(Long idCajonRecived) {
        this.cajonEstacionamientoId = idCajonRecived;
        this.cajonEstacionamiento = new CajonEstacionamiento();
        if (idCajonRecived != null && idCajonRecived > 0) {
            cajonEstacionamientoService.porId(idCajonRecived).ifPresent(p -> {
                this.cajonEstacionamiento = p;
            });
        }
        System.out.println("CAJON:::::::::::::::::" + cajonEstacionamiento);
    }

    public void actualizarCajon() {
        cerrarDialogo();
    }

    public void cerrarDialogo() {
        System.out.println("Cerrando ventana de dialogo...");
        cajonEstacionamiento = new CajonEstacionamiento();
    }
    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }


    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }


    public List<CajonEstacionamiento> getCajonesEstacionamiento() {
        return cajonesEstacionamiento;
    }

    public void setCajonesEstacionamiento(List<CajonEstacionamiento> cajonesEstacionamiento) {
        this.cajonesEstacionamiento = cajonesEstacionamiento;
    }

    public Long getCajonEstacionamientoId() {
        return cajonEstacionamientoId;
    }

    public void setCajonEstacionamientoId(Long cajonEstacionamientoId) {
        this.cajonEstacionamientoId = cajonEstacionamientoId;
    }

    public CajonEstacionamiento getCajonEstacionamiento() {
        return cajonEstacionamiento;
    }

    public void setCajonEstacionamiento(CajonEstacionamiento cajonEstacionamiento) {
        this.cajonEstacionamiento = cajonEstacionamiento;
    }

    public List<EstatusCajon> getEstatusCajon() {
        return estatusCajon;
    }

    public void setEstatusCajon(List<EstatusCajon> estatusCajon) {
        this.estatusCajon = estatusCajon;
    }

    public List<AreaEstacionamiento> getAreasEstacionamiento() {
        return areasEstacionamiento;
    }

    public void setAreasEstacionamiento(List<AreaEstacionamiento> areasEstacionamiento) {
        this.areasEstacionamiento = areasEstacionamiento;
    }
}
