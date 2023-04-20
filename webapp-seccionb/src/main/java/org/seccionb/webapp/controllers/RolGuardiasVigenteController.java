package org.seccionb.webapp.controllers;


import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.seccionb.webapp.entities.*;
import org.seccionb.webapp.services.EstudianteService;
import org.seccionb.webapp.services.GuardiasService;
import org.seccionb.webapp.services.PersonaService;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;


@ViewScoped
@Named("rolGuardiasVigenteController")
public class RolGuardiasVigenteController implements Serializable {

    private static final long serialVersionUID = 58935536L;

    @Inject
    private FacesContext facesContext;

    private Long rolGuardiaId;
    private RolDeGuardias rolGuardia;

    private List<Guardia> guardiasDelRol;

    private LocalDate current_date = LocalDate.now();
    private String anioActual = current_date.getYear()+"";

    private List<String> meses = Arrays.asList("Enero", "Febrero", "Marzo", "Abril",
            "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
    private Integer mes = current_date.getMonth().getValue();

    private String mesActual = meses.get(mes-1);

    private PieChartModel resumenRolVigente;

    @Inject
    private GuardiasService guardiasService;

    @Inject
    private PersonaService personaService;

    @Inject
    private EstudianteService estudianteService;

    @PostConstruct
    public void init() {
        System.out.println("Invocando PostConstruct ROL GUARDIA VIGENTE");

        System.out.println("ANIO: " + anioActual);
        System.out.println("MES: "+mesActual);

        this.rolGuardia = new RolDeGuardias();
        this.guardiasDelRol = new ArrayList<>();

        guardiasService.listar().forEach(g->{
            if(g.getAnio().equals(anioActual) && g.getMes().equals(mesActual)){
                System.out.println("ROL GUARDIA: " + g);
                this.rolGuardiaId = g.getId();
                this.rolGuardia = g;
                this.guardiasDelRol = guardiasService.guardiasPorIdRol(rolGuardiaId);

                //Filtras guardias
                this.guardiasDelRol.removeIf(gd -> (gd.getPersona()==null));
            }
        });

        createPieModel();
    }

    private void createPieModel() {
        int rol = 0;
        int sansion=0;
        for(Guardia gr: guardiasDelRol){
            if(gr.getTipoGuardia().getId()==1)
                rol++;
            else
                sansion++;
        };

        resumenRolVigente = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(rol);
        values.add(sansion);
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Rol");
        labels.add("Sansión");
        data.setLabels(labels);

        resumenRolVigente.setData(data);
    }

    public List<Guardia> misGuardias(String username){
        List<Guardia> guardias = new ArrayList<>();
//        Interno temp = estudianteService.getEstudiantePorNombreUsuario(username);
//        init();
//        this.guardiasDelRol.forEach(gua->{
//            if(gua.getPersona().getId() == temp.getPersona().getId()){
//                guardias.add(gua);
//            }
//        });
        return guardias;
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

    public List<Guardia> getGuardiasDelRol() {
        return guardiasDelRol;
    }

    public void setGuardiasDelRol(List<Guardia> guardiasDelRol) {
        this.guardiasDelRol = guardiasDelRol;
    }

    public String getAnioActual() {
        return anioActual;
    }

    public void setAnioActual(String anioActual) {
        this.anioActual = anioActual;
    }

    public String getMesActual() {
        return mesActual;
    }

    public void setMesActual(String mesActual) {
        this.mesActual = mesActual;
    }

    public PieChartModel getResumenRolVigente() {
        return resumenRolVigente;
    }

    public void setResumenRolVigente(PieChartModel resumenRolVigente) {
        this.resumenRolVigente = resumenRolVigente;
    }

    /**
     * Devuelve el número de dias del mes (número) pasado por parámetro
     * Si es Febrero tiene en cuenta si este año es bisiesto o no
     * Empieza por 1
     * @param mes Mes que queremos saber el número de días
     * @return Número de días de ese mes
     */
    public int numeroDeDiasMes(String mes, int anio){

        int numeroDias=-1;

        switch (mes.toLowerCase().trim()) {
            case "enero":
            case "marzo":
            case "mayo":
            case "julio":
            case "agosto":
            case "octubre":
            case "diciembre":
                numeroDias = 31;
                break;
            case "abril":
            case "junio":
            case "septiembre":
            case "noviembre":
                numeroDias = 30;
                break;
            case "febrero":

                if (esBisiesto(anio)) {
                    numeroDias = 29;
                } else {
                    numeroDias = 28;
                }
                break;

        }

        return numeroDias;
    }

    /**
     * Indica si un año es bisiesto o no
     *
     * @param anio Año
     * @return True = es bisiesto
     */
    public boolean esBisiesto(int anio) {

        GregorianCalendar calendar = new GregorianCalendar();
        boolean esBisiesto = false;
        if (calendar.isLeapYear(anio)) {
            esBisiesto = true;
        }
        return esBisiesto;

    }
}
