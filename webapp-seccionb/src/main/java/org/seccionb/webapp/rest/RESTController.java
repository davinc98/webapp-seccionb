package org.seccionb.webapp.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.seccionb.webapp.entities.EstatusPorton;
import org.seccionb.webapp.entities.EstatusVehiculo;
import org.seccionb.webapp.entities.SolicitudIngreso;
import org.seccionb.webapp.entities.Vehiculo;
import org.seccionb.webapp.model.EstatusPortonDTO;
import org.seccionb.webapp.model.MatriculasDTO;
import org.seccionb.webapp.model.SolicitudIngresoDTO;
import org.seccionb.webapp.services.RESTService;
import org.seccionb.webapp.services.VehiculoService;
import org.seccionb.webapp.model.RespuestaGenerica;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestScoped
@Path("/estacionamiento")
@Produces(MediaType.APPLICATION_JSON)
public class RESTController {

    @Inject
    private VehiculoService vehiculoService;

    @Inject
    private RESTService restService;

    private Vehiculo vehiculo;

    private int OK = Response.Status.OK.getStatusCode();
    private int ERR = Response.Status.BAD_REQUEST.getStatusCode();
    private String msjOK = "Servicio ejecutado correctamente";
    private String msjERR = "Error al ejecutar el servicio";


    public Vehiculo consultarExistenciaMatricula(List<String> matriculas) {

        List<Vehiculo> vehiculoList = vehiculoService.listar();

        vehiculoList.forEach(v -> {
            System.out.println("Comparando matricula: ");
            matriculas.forEach(matricula -> {
                System.out.println(matricula);
                if (v.getMatricula().equals(matricula)) {
                    this.vehiculo = v;
                }
            });
        });

        System.out.println("Vehiculo: " + vehiculo);

        if (this.vehiculo != null) {
            return vehiculo;
        }
        return null;
    }


    @POST
    @Path("/solicitarIngreso")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response solicitarIngreso(SolicitudIngresoDTO solicitudIngresoDTO) {

        Map<String, Object> informacion = new HashMap<>();

        Vehiculo veh = consultarExistenciaMatricula(solicitudIngresoDTO.getMatriculas());
        if(veh==null){
            informacion.put("ingreso", "La matricula no fue reconocida");
            return Response.ok(new RespuestaGenerica(ERR, msjERR, informacion)).build();
        }

        SolicitudIngreso solicitudIngreso = new SolicitudIngreso();
        solicitudIngreso.setVehiculo(veh);
        solicitudIngreso.setFotoPlaca(solicitudIngresoDTO.getFotoMatricula());
        solicitudIngreso.setTipo(1);//Solicitud matricula conocida
        solicitudIngreso.setEstatus(1);

        int resp = restService.solicitarIngreso(solicitudIngreso);

        informacion.put("ingreso", "Solicitando ingreso");
        System.out.println("Solicitando Ingreso...");

        return Response.ok(new RespuestaGenerica(OK, msjOK, informacion)).build();

    }


    @GET
    @Path("/estatus")
    public Response getStatusPorton() {
        Map<String, Object> informacion = new HashMap<>();
        EstatusPorton estatusPorton = restService.getEstatusPorton();
        informacion.put("estatusPorton", estatusPorton.getDescripcion());
        return Response.ok(new RespuestaGenerica(OK, msjOK, informacion)).build();
    }

    @PUT
    @Path("/estatus")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizaEstatusPorton(EstatusPortonDTO estatusPortonDTO) {

        Map<String, Object> informacion = new HashMap<>();


        int resp = restService.actualizarEstatusPorton(estatusPortonDTO.getEstatus().toString());

        informacion.put("exito", "Estatus de porton actualizado");
        informacion.put("estatusPorton", estatusPortonDTO.getEstatus());
        System.out.println("Solicitando Ingreso...");

        return Response.ok(new RespuestaGenerica(OK, msjOK, informacion)).build();

    }


    @POST
    @Path("/solicitarIngresoDesconocido")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response solicitarIngresoDesconocido(SolicitudIngresoDTO solicitudIngresoDTO) {

        Map<String, Object> informacion = new HashMap<>();

        Vehiculo vehiculoDesconocido = consultarExistenciaMatricula(solicitudIngresoDTO.getMatriculas());
        if(vehiculoDesconocido==null){
            vehiculoDesconocido = new Vehiculo();
            vehiculoDesconocido.setMatricula(solicitudIngresoDTO.getMatriculas().get(0));
            vehiculoDesconocido.setEstatusVehiculo(new EstatusVehiculo(5L, "Desconocido"));
            vehiculoService.guardar(vehiculoDesconocido);
        }else{
            return this.solicitarIngreso(solicitudIngresoDTO);
        }

        SolicitudIngreso solicitudIngreso = new SolicitudIngreso();
        solicitudIngreso.setVehiculo(vehiculoDesconocido);
        solicitudIngreso.setFotoPlaca(solicitudIngresoDTO.getFotoMatricula());
        solicitudIngreso.setTipo(2);//Solicitud DESCONOCIDO
        solicitudIngreso.setEstatus(1);

        int resp = restService.solicitarIngreso(solicitudIngreso);

        informacion.put("ingreso", "Solicitando ingreso vehiculo desconocido");
        System.out.println("Solicitando Ingreso vehiculo deconocido...");

        return Response.ok(new RespuestaGenerica(OK, msjOK, informacion)).build();

    }

}
