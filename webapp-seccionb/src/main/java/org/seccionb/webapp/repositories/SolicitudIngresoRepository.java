package org.seccionb.webapp.repositories;

import org.seccionb.webapp.entities.EstatusPorton;
import org.seccionb.webapp.entities.SolicitudIngreso;

import java.util.List;

public interface SolicitudIngresoRepository extends CrudRepository<SolicitudIngreso>{
    EstatusPorton getEstatusPorton();

    List<SolicitudIngreso> listarReconocido();

    List<SolicitudIngreso> listarDesconocido();
}
