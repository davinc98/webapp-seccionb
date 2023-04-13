package org.seccionb.webapp.controllers;


import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.RowEditEvent;
import org.seccionb.webapp.entities.*;
import org.seccionb.webapp.services.ContactoService;
import org.seccionb.webapp.services.EstudianteService;
import org.seccionb.webapp.services.GuardiasService;
import org.seccionb.webapp.services.PersonaService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;


@ViewScoped
@Named("rolGuardiaController")
public class RolGuardiaController implements Serializable {

    private static final long serialVersionUID = 589536L;

    @Inject
    private FacesContext facesContext;

    private Long rolGuardiaId;
    private RolDeGuardias rolGuardia;

    private List<Guardia> guardiasDelRol;

    private List<Persona> personasEstudiantes;

    private List<TipoGuardia> tiposGuardia;

    private List<String> horarios = Arrays.asList("5:45-5:45");


    @Inject
    private GuardiasService guardiasService;

    @Inject
    private PersonaService personaService;

    @PostConstruct
    public void init() {
        System.out.println("Invocando PostConstruct");

        System.out.println("ROL GUARDIA ID: " + rolGuardiaId);

        this.rolGuardia = new RolDeGuardias();
        this.guardiasDelRol = new ArrayList<>();

        if (rolGuardiaId != null && rolGuardiaId > 0) {
            guardiasService.porId(rolGuardiaId).ifPresent(p -> {
                this.rolGuardia = p;
                System.out.println("ROL GUARDIA: " + rolGuardia);

                this.guardiasDelRol = guardiasService.guardiasPorIdRol(rolGuardiaId);

                System.out.println("Guardias del Rol: "+guardiasDelRol.size());
                if(guardiasDelRol.isEmpty()){
                    int numeroDias = this.numeroDeDiasMes(rolGuardia.getMes(),
                            Integer.parseInt(rolGuardia.getAnio()));

                    System.out.println("Creando "+numeroDias+" guardias en el rol...");
//                    TipoGuardia tipoG = new TipoGuardia(1L, "R", "Rol");
                    for(int i=1;i<=numeroDias;i++){
                        Guardia g = new Guardia();
                        g.setRolDeGuardias(rolGuardia);
                        g.setDia(Long.parseLong(i+""));
//                        g.setHorario("5:45-5:45");
//                        g.setTipoGuardia(tipoG);
                        guardiasService.guardarGuardia(g);
                    }

                    this.guardiasDelRol = guardiasService.guardiasPorIdRol(rolGuardiaId);
                }else{
                    //Comparar si el numero de dias es correcto
                }
            });
        }

        this.personasEstudiantes = personaService.listarPersonasEstudiantesActivos();
        this.tiposGuardia = guardiasService.tiposGuardia();

    }

    public String guardar() {
        System.out.println("ROL GUARDIA ID:" + rolGuardiaId);
        System.out.println("ROL GUARDIA: " + rolGuardia);

        if (rolGuardia.getId() != null && rolGuardia.getId() > 0) {
            facesContext.addMessage(null,
                    new FacesMessage(String.format("Rol de Guardias actualizado. ")));
        } else {
            facesContext.addMessage(null,
                    new FacesMessage(String.format("Rol de Guardias creado con éxito. ")));
        }

        System.out.println("CONTACTO======================" + rolGuardia);
        guardiasService.guardar(rolGuardia);

        rolGuardia = new RolDeGuardias();
//        this.guardias = ???
        return "gestionContactos.jsf?idPersona=" + rolGuardiaId + "&faces-redirect=true";
    }

    public void onRowEdit(RowEditEvent<Guardia> event) {
        Guardia temp = event.getObject();
        System.out.println("Guardia a editar: "+temp);
        guardiasService.guardarGuardia(temp);
        facesContext.addMessage(null,
                new FacesMessage("Guardia asignada",String.format("["+event.getObject().getId()+"]")));
    }

    public void onRowCancel(RowEditEvent<Guardia> event) {
        facesContext.addMessage(null,
                new FacesMessage("Guardia sin cambios",String.format("["+event.getObject().getId()+"]")));
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

    public List<Persona> getPersonasEstudiantes() {
        return personasEstudiantes;
    }

    public void setPersonasEstudiantes(List<Persona> personasEstudiantes) {
        this.personasEstudiantes = personasEstudiantes;
    }

    public List<TipoGuardia> getTiposGuardia() {
        return tiposGuardia;
    }

    public void setTiposGuardia(List<TipoGuardia> tiposGuardia) {
        this.tiposGuardia = tiposGuardia;
    }

    public List<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<String> horarios) {
        this.horarios = horarios;
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
