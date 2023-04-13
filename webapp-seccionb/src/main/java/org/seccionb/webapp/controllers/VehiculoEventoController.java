package org.seccionb.webapp.controllers;


import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import org.seccionb.webapp.entities.*;
import org.seccionb.webapp.services.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ViewScoped
@Named("vehiculoEventoController")
public class VehiculoEventoController implements Serializable {

    private static final long serialVersionUID = 5869L;
    @Inject
    private FacesContext facesContext;

    private Long vehiculoId;
    private Vehiculo vehiculo;

    private Long vehiculoEventoId;
    private VehiculoEvento vehiculoEvento;

    private LocalDate fecha;

    private List<VehiculoEvento> entradas;

    private String textoBuscar;

    private Date dateSelected;


    @Inject
    private VehiculoService vehiculoService;

    @Inject
    private VehiculoEventoService vehiculoEventoService;
    @Inject
    private PersonaService personaService;

    @Inject
    private CajonEstacionamientoService cajonEstacionamientoService;

    @PostConstruct
    public void init() {
        System.out.println("Invocando PostConstruct");


        this.entradas = new ArrayList<>();
        List<VehiculoEvento> todosEventos = vehiculoEventoService.listar();
        this.entradas = vehiculoEventoService.listar();
        this.dateSelected = new Date();


        LocalDate current_date = LocalDate.now();
        System.out.println("FECHA HOY: "+current_date);


        System.out.println("ID EVENTOVEHICULO: " + vehiculoEventoId);
        vehiculoEvento = new VehiculoEvento();
        if (vehiculoEventoId != null && vehiculoEventoId > 0) {
            vehiculoEventoService.porId(vehiculoEventoId).ifPresent(p -> {
                this.vehiculoEvento = p;
            });
        }
        System.out.println("EVENTOVEHICULO: " + vehiculoEvento);

        System.out.println("ID VEHICULO: " + vehiculo);
        vehiculo = new Vehiculo();
        if (vehiculoId != null && vehiculoId > 0) {
            vehiculoService.porId(vehiculoId).ifPresent(p -> {
                this.vehiculo = p;
            });
        }
        System.out.println("VEHICULO: " + vehiculo);

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

    public void buscar(){
        System.out.println("Buscando matricula: "+textoBuscar);
        this.entradas = vehiculoEventoService.buscarPorMatricula(this.textoBuscar);
    }

    public void onDateSelect(SelectEvent<Date> event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
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

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Long getVehiculoEventoId() {
        return vehiculoEventoId;
    }

    public void setVehiculoEventoId(Long vehiculoEventoId) {
        this.vehiculoEventoId = vehiculoEventoId;
    }

    public VehiculoEvento getVehiculoEvento() {
        return vehiculoEvento;
    }

    public void setVehiculoEvento(VehiculoEvento vehiculoEvento) {
        this.vehiculoEvento = vehiculoEvento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<VehiculoEvento> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<VehiculoEvento> entradas) {
        this.entradas = entradas;
    }

    public String getTextoBuscar() {
        return textoBuscar;
    }

    public void setTextoBuscar(String textoBuscar) {
        this.textoBuscar = textoBuscar;
    }

    public Date getDateSelected() {
        return dateSelected;
    }

    public void setDateSelected(Date dateSelected) {
        this.dateSelected = dateSelected;
    }
}
