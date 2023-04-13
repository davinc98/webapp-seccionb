package org.seccionb.webapp.repositories;

import org.seccionb.webapp.entities.EstatusPorton;
import org.seccionb.webapp.entities.SolicitudIngreso;
import org.seccionb.webapp.model.SolicitudIngresoDTO;

public interface RESTRepository {
    int solicitarIngreso(String matricula);

    EstatusPorton getStatusPorton();

    int actualizarEstatusPorton(String estatus);

    int solicitarIngreso(SolicitudIngreso solicitudIngreso);
}
