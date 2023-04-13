package org.seccionb.webapp.controllers;


import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.seccionb.webapp.entities.EstatusRolGuardia;
import org.seccionb.webapp.entities.RolDeGuardias;
import org.seccionb.webapp.services.GuardiasService;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ViewScoped
@Named("rolGuardiasController")
public class RolGuardiasController implements Serializable {

    private static final long serialVersionUID = 7760875L;

    @Inject
    private FacesContext facesContext;

    private Long rolGuardiaId;
    private RolDeGuardias rolGuardia;


    private List<RolDeGuardias> rolesDeGuardia;

    private List<EstatusRolGuardia> estatusRolGuardias;

    private List<String> anios;
    private List<String> meses = Arrays.asList("Enero", "Febrero", "Marzo", "Abril",
            "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
    LocalDate current_date = LocalDate.now();
    private String anioActual = current_date.getYear()+"";

    @Inject
    private GuardiasService guardiasService;



    @PostConstruct
    public void init() {
        this.rolesDeGuardia = guardiasService.listar();
        this.rolGuardia = new RolDeGuardias();
        this.estatusRolGuardias = guardiasService.estatusRolesDeGuardia();

        LocalDate current_date = LocalDate.now();
        int year = current_date.getYear();
        anios = new ArrayList<>();
        year = year+1;
        for(int i = 0; i<=5; i++){
            this.anios.add(""+year);
            year--;
        }
    }


    public void editar(Long idRolGuardiaRecived) {
        System.out.println("ID ROL GUARDIA EDITAR: " + idRolGuardiaRecived);
        this.rolGuardiaId = idRolGuardiaRecived;
        guardiasService.porId(idRolGuardiaRecived).ifPresent(c->{
            this.rolGuardia = c;
        });
        System.out.println("ROL GUARDIA:::::::::::::::::" + rolGuardia);
    }

//    public String editar(Long idRolGuardias) {
//
//        this.rolGuardiaId = idRolGuardias;
//        this.rolGuardia = new RolDeGuardias();
//
//
//        if (idRolGuardias != null && idRolGuardias > 0) {
//            guardiasService.porId(idRolGuardias).ifPresent(p -> {
//                this.rolGuardia = p;
//            });
//        }
//        System.out.println("ROL DE GUARDIAS:::::::::::::::::" + rolGuardia);
//        return "formRolGuardias.xhtml";
//    }

//    public String crear() {
//        this.rolGuardia = new RolDeGuardias();
//
//        System.out.println("ROL DE GUARDIAS++++++++++++++++" + rolGuardia);
//        return "formRolGuardias.xhtml";
//    }

    public String guardar() {
        System.out.println("ROL GUARDIAS ID:" + rolGuardiaId);
        System.out.println("ROL GUARDIA: " + rolGuardia);

        if (rolGuardia.getId() != null && rolGuardia.getId() > 0) {
            facesContext.addMessage(null,
                    new FacesMessage(String.format("Información de rol de guardias actualizada. ")));
        } else {
            facesContext.addMessage(null,
                    new FacesMessage(String.format("Rol de Guardias creado con éxito. ")));
        }

        System.out.println("ROL GUARDIAS======================" + rolGuardia);

        guardiasService.guardar(rolGuardia);
        Long idRedirect = rolGuardia.getId();

        rolGuardia = new RolDeGuardias();

        this.rolesDeGuardia = guardiasService.listar();

        return "gestionRolGuardias.jsf?idRolGuardia="+idRedirect+"&faces-redirect=true";
    }

    public String eliminarRol() {
        this.rolGuardiaId = rolGuardia.getId();
        System.out.println("ROL GUARDIA A ELIMINAR: " + rolGuardia);

        //Borrar guardias del rol
        guardiasService.guardiasPorIdRol(rolGuardiaId).forEach(g -> {
            System.out.println("Eliminando guardia de rol: "+g.getId());
            guardiasService.eliminarGuardia(g.getId());
        });

        guardiasService.eliminar(rolGuardia.getId());
        facesContext.addMessage(null,
                new FacesMessage(String.format("Rol de Guardias eliminado.")));
        return "gestionGuardias.jsf?faces-redirect=true";
    }


    public void cerrarDialogo() {
        cerrarDialogo(null);
    }

    public void cerrarDialogo(FacesMessage mensaje) {
        PrimeFaces.current().dialog().closeDynamic(mensaje);
    }

    public Long getRolGuardiaId() {
        return rolGuardiaId;
    }

    public void setRolGuardiaId(Long rolGuardiaId) {
        this.rolGuardiaId = rolGuardiaId;
    }

    public RolDeGuardias getRolGuardia() {
        return rolGuardia;
    }

    public void setRolGuardia(RolDeGuardias rolGuardia) {
        this.rolGuardia = rolGuardia;
    }

    public List<RolDeGuardias> getRolesDeGuardia() {
        return rolesDeGuardia;
    }

    public void setRolesDeGuardia(List<RolDeGuardias> rolesDeGuardia) {
        this.rolesDeGuardia = rolesDeGuardia;
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

    public List<EstatusRolGuardia> getEstatusRolGuardias() {
        return estatusRolGuardias;
    }

    public void setEstatusRolGuardias(List<EstatusRolGuardia> estatusRolGuardias) {
        this.estatusRolGuardias = estatusRolGuardias;
    }

    public List<String> getMeses() {
        return meses;
    }

    public void setMeses(List<String> meses) {
        this.meses = meses;
    }
}
