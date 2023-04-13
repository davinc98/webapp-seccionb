package org.seccionb.webapp.controllers;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.seccionb.webapp.entities.Estudiante;
import org.seccionb.webapp.entities.EstudianteCargo;
import org.seccionb.webapp.entities.Persona;
import org.seccionb.webapp.entities.Vehiculo;
import org.seccionb.webapp.services.EstudianteCargoService;
import org.seccionb.webapp.services.EstudianteService;
import org.seccionb.webapp.services.PersonaService;
import org.seccionb.webapp.services.VehiculoService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestScoped
@Named("busquedaController")
public class BusquedaController implements Serializable {

    private static final long serialVersionUID = 58969L;

    @Inject
    private FacesContext facesContext;


    private List<Estudiante> estudiantesBuscar;

    private List<Vehiculo> vehiculosBuscar;

    private List<Persona> personasBuscar;

    private List<EstudianteCargo> estudiantesCargosBuscar;

    private String textoBuscar;
    private String opcionBusqueda="curp";

    private Map<String, String> opcionesBusquedaEstudiante;


    @Inject
    private EstudianteService estudianteService;

    @Inject
    private VehiculoService vehiculoService;

    @Inject
    private PersonaService personaService;

    @Inject
    private EstudianteCargoService estudianteCargoService;

    @PostConstruct
    public void init() {
        System.out.println("Invocando constructor BUSQUEDA CONTROLLER");
        estudiantesBuscar = new ArrayList<>();
        vehiculosBuscar = new ArrayList<>();

        opcionesBusquedaEstudiante = new HashMap<>();
        opcionesBusquedaEstudiante.put("Nombre", "nomb");
        opcionesBusquedaEstudiante.put("Apellido Paterno", "apep");
        opcionesBusquedaEstudiante.put("Apellido Materno", "apem");
        opcionesBusquedaEstudiante.put("CURP", "curp");
    }

    public void buscarEstudiante(){
        System.out.println("Opcion de busqueda: "+opcionBusqueda);
        System.out.println("Texto: "+textoBuscar);
//        switch (opcionBusqueda){
//            case "nomb":
//                System.out.println("Buscando por nombre: "+textoBuscar);
//                break;
//            case "apep":
//                System.out.println("Buscando por apellido pat: "+textoBuscar);
//                break;
//            case "apem":
//                System.out.println("Buscando por apellido mat: "+textoBuscar);
//                break;
//            case "curp":
//                System.out.println("Buscando por curp: "+textoBuscar);
//
//                break;
//        }
        if(!textoBuscar.equals(""))
            this.estudiantesBuscar = estudianteService.getEstudiantesPorCurp(textoBuscar);
        else
            this.estudiantesBuscar = new ArrayList<>();
        System.out.println("RESULTADOS: "+estudiantesBuscar);
    }

    public void buscarVehiculo(){
        System.out.println("Busqueda de vehiculo...");
        System.out.println("Texto: "+textoBuscar);
        if(!textoBuscar.equals(""))
            this.vehiculosBuscar = vehiculoService.getVehiculosPorMatricula(textoBuscar);
        else
            this.vehiculosBuscar = new ArrayList<>();
        System.out.println("RESULTADOS: "+vehiculosBuscar);
    }

    public void buscarPersona(){
        System.out.println("Opcion de busqueda: "+opcionBusqueda);
        System.out.println("Texto: "+textoBuscar);
        if(!textoBuscar.equals(""))
            this.personasBuscar = personaService.getPersonasPorCurp(textoBuscar);
        else
            this.personasBuscar = new ArrayList<>();
        System.out.println("RESULTADOS: "+personasBuscar);
    }


    public void buscarEstudianteCargo(){
        System.out.println("Opcion de busqueda: "+opcionBusqueda);
        System.out.println("Texto: "+textoBuscar);
        if(!textoBuscar.equals(""))
            this.estudiantesCargosBuscar = estudianteCargoService.getEstudiantesCargoPorCurp(textoBuscar);
        else
            this.estudiantesCargosBuscar = new ArrayList<>();
        System.out.println("RESULTADOS: "+estudiantesCargosBuscar);
    }

    public List<Estudiante> getEstudiantesBuscar() {
        return estudiantesBuscar;
    }

    public void setEstudiantesBuscar(List<Estudiante> estudiantesBuscar) {
        this.estudiantesBuscar = estudiantesBuscar;
    }

    public String getTextoBuscar() {
        return textoBuscar;
    }

    public void setTextoBuscar(String textoBuscar) {
        this.textoBuscar = textoBuscar;
    }

    public String getOpcionBusqueda() {
        return opcionBusqueda;
    }

    public void setOpcionBusqueda(String opcionBusqueda) {
        this.opcionBusqueda = opcionBusqueda;
    }


    public Map<String, String> getOpcionesBusquedaEstudiante() {
        return opcionesBusquedaEstudiante;
    }

    public void setOpcionesBusquedaEstudiante(Map<String, String> opcionesBusquedaEstudiante) {
        this.opcionesBusquedaEstudiante = opcionesBusquedaEstudiante;
    }

    public List<Vehiculo> getVehiculosBuscar() {
        return vehiculosBuscar;
    }

    public void setVehiculosBuscar(List<Vehiculo> vehiculosBuscar) {
        this.vehiculosBuscar = vehiculosBuscar;
    }

    public List<Persona> getPersonasBuscar() {
        return personasBuscar;
    }

    public void setPersonasBuscar(List<Persona> personasBuscar) {
        this.personasBuscar = personasBuscar;
    }

    public List<EstudianteCargo> getEstudiantesCargosBuscar() {
        return estudiantesCargosBuscar;
    }

    public void setEstudiantesCargosBuscar(List<EstudianteCargo> estudiantesCargosBuscar) {
        this.estudiantesCargosBuscar = estudiantesCargosBuscar;
    }
}
