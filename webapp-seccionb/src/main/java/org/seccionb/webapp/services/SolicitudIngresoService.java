package org.seccionb.webapp.services;


import jakarta.ejb.Local;
import org.seccionb.webapp.entities.EstatusPorton;
import org.seccionb.webapp.entities.SolicitudIngreso;
import org.seccionb.webapp.entities.VehiculoEvento;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Local
public interface SolicitudIngresoService {

    List<SolicitudIngreso> listar();
    Optional<SolicitudIngreso> porId(Long id);
    void guardar(SolicitudIngreso solicitudIngreso);
    void eliminar(Long id);

    EstatusPorton getEstatusPorton();

    List<SolicitudIngreso> listarReconocido();

    List<SolicitudIngreso> listarDesconocido();
}
