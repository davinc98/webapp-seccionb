package org.seccionb.webapp.services.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.seccionb.webapp.entities.EstatusPorton;
import org.seccionb.webapp.entities.SolicitudIngreso;
import org.seccionb.webapp.repositories.SolicitudIngresoRepository;
import org.seccionb.webapp.services.SolicitudIngresoService;

import java.util.List;
import java.util.Optional;

@Stateless
public class SolicitudIngresoServiceImpl implements SolicitudIngresoService {

    @Inject
    private SolicitudIngresoRepository repository;


    @Override
    public List<SolicitudIngreso> listar() {
        return repository.listar();
    }

    @Override
    public Optional<SolicitudIngreso> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void guardar(SolicitudIngreso solicitudIngreso) {
        repository.guardar(solicitudIngreso);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }

    @Override
    public EstatusPorton getEstatusPorton() {
        return repository.getEstatusPorton();
    }

    @Override
    public List<SolicitudIngreso> listarReconocido() {
        return repository.listarReconocido();
    }

    @Override
    public List<SolicitudIngreso> listarDesconocido() {
        return repository.listarDesconocido();
    }
}
