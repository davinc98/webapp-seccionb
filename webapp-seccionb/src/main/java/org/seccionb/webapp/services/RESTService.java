package org.seccionb.webapp.services;

import jakarta.ejb.Local;
import org.seccionb.webapp.entities.EstatusPorton;
import org.seccionb.webapp.entities.SolicitudIngreso;
import org.seccionb.webapp.model.SolicitudIngresoDTO;

@Local
public interface RESTService {
    int solicitarIngreso(String matricula);
    EstatusPorton getEstatusPorton();
    int actualizarEstatusPorton(String estatus);

    int solicitarIngreso(SolicitudIngreso solicitudIngreso);
}
