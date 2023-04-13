package org.seccionb.webapp.services.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.seccionb.webapp.entities.EstatusPorton;
import org.seccionb.webapp.entities.SolicitudIngreso;
import org.seccionb.webapp.model.SolicitudIngresoDTO;
import org.seccionb.webapp.repositories.RESTRepository;
import org.seccionb.webapp.services.RESTService;


@Stateless
public class RESTServiceImpl implements RESTService {

    @Inject
    private RESTRepository repository;

    @Override
    public int solicitarIngreso(String matricula) {
        return repository.solicitarIngreso(matricula);
    }

    @Override
    public EstatusPorton getEstatusPorton() {
        return repository.getStatusPorton();
    }

    @Override
    public int actualizarEstatusPorton(String estatus) {
        return repository.actualizarEstatusPorton(estatus);
    }

    @Override
    public int solicitarIngreso(SolicitudIngreso solicitudIngreso) {
        return repository.solicitarIngreso(solicitudIngreso);
    }
}
