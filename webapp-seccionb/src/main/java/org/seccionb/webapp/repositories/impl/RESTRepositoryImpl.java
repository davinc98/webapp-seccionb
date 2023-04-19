package org.seccionb.webapp.repositories.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.seccionb.webapp.entities.EstatusPorton;
import org.seccionb.webapp.entities.SolicitudIngreso;
import org.seccionb.webapp.entities.Vehiculo;
import org.seccionb.webapp.repositories.RESTRepository;
import org.seccionb.webapp.services.SolicitudIngresoService;
import org.seccionb.webapp.services.VehiculoService;

import java.util.List;

@RequestScoped
public class RESTRepositoryImpl implements RESTRepository {

    @Inject
    private EntityManager em;

    @Inject
    SolicitudIngresoService solicitudIngresoService;

    @Inject
    VehiculoService vehiculoService;

    @Override
    public int solicitarIngreso(String matricula) {

        //Borrar registros de solicitudes de ingreso
        List<SolicitudIngreso> solicitudes = solicitudIngresoService.listar();
        solicitudes.forEach(si->{
            solicitudIngresoService.eliminar(si.getId());
        });

        final Vehiculo[] vehiculo = new Vehiculo[1];
        vehiculoService.porMatriculaVehiculo(matricula).ifPresent(v->{
            vehiculo[0] = v;
        });
        //Registrar nueva solicitud de ingreso
        SolicitudIngreso nuevaSolicitud = new SolicitudIngreso();
        nuevaSolicitud.setVehiculo(vehiculo[0]);
        nuevaSolicitud.setEstatus(1);

        solicitudIngresoService.guardar(nuevaSolicitud);
        return 1;
    }

    @Override
    public EstatusPorton getStatusPorton() {
        return em.createQuery("SELECT ep FROM EstatusPorton ep", EstatusPorton.class).getSingleResult();
    }

    public int guardarEstatusPorton(EstatusPorton estatusPorton) {
        if(estatusPorton.getId()!=null && estatusPorton.getId()>0){
            em.merge(estatusPorton);
        }else{
            em.persist(estatusPorton);
        }
        return 1;
    }

    @Override
    public int actualizarEstatusPorton(String estatus) {
        EstatusPorton estatusPorton = getStatusPorton();
        estatusPorton.setDescripcion(estatus);
        return guardarEstatusPorton(estatusPorton);
    }

    @Override
    public int solicitarIngreso(SolicitudIngreso solicitudIngreso) {

        //Borrar registros de solicitudes de ingreso
        List<SolicitudIngreso> solicitudes = solicitudIngresoService.listar();
        solicitudes.forEach(si->{
            solicitudIngresoService.eliminar(si.getId());
        });

        solicitudIngresoService.guardar(solicitudIngreso);
        return 1;
    }
}
