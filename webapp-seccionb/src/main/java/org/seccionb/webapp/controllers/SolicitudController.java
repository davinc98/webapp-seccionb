package org.seccionb.webapp.controllers;


import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.CloseEvent;
import org.seccionb.webapp.entities.*;
import org.seccionb.webapp.services.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

@ViewScoped
@Named("solicitudController")
public class SolicitudController implements Serializable {

    private static final long serialVersionUID = 5869L;

    @Inject
    private FacesContext facesContext;

    private Long solicitudId;

    private SolicitudIngreso solicitud;

    private Long vehiculoId;
    private Vehiculo vehiculo;

    private LocalDate fecha;

    private List<SolicitudIngreso> solicitudes;

    private List<SolicitudIngreso> solicitudesReconocido;
    private List<SolicitudIngreso> solicitudesDesconocido;

    private EstatusPorton estatusPorton;


    @Inject
    private SolicitudIngresoService solicitudIngresoService;
    @Inject
    private VehiculoService vehiculoService;
    @Inject
    private VehiculoEventoService vehiculoEventoService;
    @Inject
    private RESTService restService;


    @PostConstruct
    public void init() {
        System.out.println("Invocando PostConstruct");

        this.solicitudes = solicitudIngresoService.listar();

        this.solicitudesReconocido  = solicitudIngresoService.listarReconocido();
        this.solicitudesDesconocido = solicitudIngresoService.listarDesconocido();

        this.estatusPorton = solicitudIngresoService.getEstatusPorton();

        solicitudes.forEach(solicitudIngreso -> {
            this.solicitudId = solicitudIngreso.getId();
        });

        System.out.println("ID SOLICITUD: "+solicitudId);
        solicitud = new SolicitudIngreso();
        if (solicitudId != null && solicitudId > 0) {
            solicitudIngresoService.porId(solicitudId).ifPresent(p -> {
                this.solicitud = p;
            });
        }
        System.out.println("SOLICITUD: " + solicitud);


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



    public void cerrarDialogo() {
        System.out.println("Cerrando ventana de dialogo...");
        vehiculo = new Vehiculo();
    }

    public void abrirPorton(){
        //Verificar si existen solicitudes de ingreso
        TipoEvento tipo = new TipoEvento(1L, "Entrada");

        System.out.println("Solicitando APERTURA");
        this.solicitudes = solicitudIngresoService.listar();
        this.solicitudesReconocido = solicitudIngresoService.listarReconocido();
        this.solicitudesDesconocido = solicitudIngresoService.listarDesconocido();

        LocalDate hoy = LocalDate.now();
        LocalTime ahora = LocalTime.now();

        LocalDateTime fecha = LocalDateTime.of(hoy, ahora);

//        fecha.getYear();
//        fecha.getMonth().getValue();
//        fecha.getDayOfMonth();
//        fecha.getHour();
//        fecha.getMinute();

        solicitudes.forEach(sol->{

            VehiculoEvento ve = new VehiculoEvento();
            ve.setVehiculo(sol.getVehiculo());
            ve.setFecha(fecha);
            ve.setTipoEvento(tipo);
            System.out.println("Registrando evento: "+ve);
            vehiculoEventoService.guardar(ve);

            solicitudIngresoService.eliminar(sol.getId());
        });

        int resp = restService.actualizarEstatusPorton("ABRIR");
        facesContext.addMessage(null,
                new FacesMessage("Solicitando apertura","ABRIR"));
    }

    public void cerrarPorton(){
        System.out.println("Solicitando CIERRE");
        int resp = restService.actualizarEstatusPorton("CERRAR");
        facesContext.addMessage(null,
                new FacesMessage("Solicitando cierre","CERRAR"));
    }

    public void detenerPorton(){
        System.out.println("Solicitando PAUSA");
        int resp = restService.actualizarEstatusPorton("PAUSAR");
        facesContext.addMessage(null,
                new FacesMessage("Solicitando pausa","PAUSAR"));
    }

    public void onClose(CloseEvent event) {

        System.out.println("Eliminando solicitudes de ingreso");
        this.solicitudes = solicitudIngresoService.listar();
        solicitudes.forEach(sol->{
            Vehiculo vehiculo = sol.getVehiculo();
            solicitudIngresoService.eliminar(sol.getId());
            //Si el vehiculo es desconocido se elimina tambien
            if(vehiculo.getEstatusVehiculo().getId()==5){
                System.out.println("Eliminando vehiculo: "+vehiculo);
                vehiculoService.eliminar(vehiculo.getId());
            }
        });


        //Actualizar estatus de porton a RECHAZAR
        int resp = restService.actualizarEstatusPorton("RECHAZAR");

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud Eliminada",
                "La solicitud de ingreso fue eliminada.");
        FacesContext.getCurrentInstance().addMessage(null, message);

        //Enviar mensaje a la RASPBERRY que deje de escuchar el estatus del porton
    }


    public void descartarSolicitud() {

        System.out.println("Eliminando solicitudes de ingreso");
        this.solicitudes = solicitudIngresoService.listar();

        solicitudes.forEach(sol->{
            Vehiculo vehiculo = sol.getVehiculo();
            solicitudIngresoService.eliminar(sol.getId());
            //Si el vehiculo es desconocido se elimina tambien
            if(vehiculo.getEstatusVehiculo().getId()==5){
                System.out.println("Eliminando vehiculo: "+vehiculo);
                vehiculoService.eliminar(vehiculo.getId());
            }
        });

        //Actualizar estatus de porton a RECHAZAR
        int resp = restService.actualizarEstatusPorton("RECHAZAR");

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud descartada",
                "La solicitud de ingreso fue descartada.");
        FacesContext.getCurrentInstance().addMessage(null, message);

        //Enviar mensaje a la RASPBERRY que deje de escuchar el estatus del porton
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

    public Long getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(Long solicitudId) {
        this.solicitudId = solicitudId;
    }

    public SolicitudIngreso getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudIngreso solicitud) {
        this.solicitud = solicitud;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<SolicitudIngreso> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<SolicitudIngreso> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public EstatusPorton getEstatusPorton() {
        return estatusPorton;
    }

    public void setEstatusPorton(EstatusPorton estatusPorton) {
        this.estatusPorton = estatusPorton;
    }

    public List<SolicitudIngreso> getSolicitudesDesconocido() {
        return solicitudesDesconocido;
    }

    public void setSolicitudesDesconocido(List<SolicitudIngreso> solicitudesDesconocido) {
        this.solicitudesDesconocido = solicitudesDesconocido;
    }

    public List<SolicitudIngreso> getSolicitudesReconocido() {
        return solicitudesReconocido;
    }

    public void setSolicitudesReconocido(List<SolicitudIngreso> solicitudesReconocido) {
        this.solicitudesReconocido = solicitudesReconocido;
    }
}
