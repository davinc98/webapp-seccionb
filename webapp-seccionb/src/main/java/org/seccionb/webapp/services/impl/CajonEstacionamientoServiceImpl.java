package org.seccionb.webapp.services.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.seccionb.webapp.entities.AreaEstacionamiento;
import org.seccionb.webapp.entities.CajonEstacionamiento;
import org.seccionb.webapp.entities.Cargo;
import org.seccionb.webapp.entities.EstatusCajon;
import org.seccionb.webapp.repositories.CajonEstacionamientoRepository;
import org.seccionb.webapp.repositories.CargoRepository;
import org.seccionb.webapp.services.CajonEstacionamientoService;
import org.seccionb.webapp.services.CargoService;

import java.util.List;
import java.util.Optional;


@Stateless
public class CajonEstacionamientoServiceImpl implements CajonEstacionamientoService {

    @Inject
    private CajonEstacionamientoRepository repository;

    @Override
    public List<CajonEstacionamiento> listar() {
        return repository.listar();
    }

    @Override
    public Optional<CajonEstacionamiento> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void guardar(CajonEstacionamiento cargo) {
        repository.guardar(cargo);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }

    @Override
    public List<EstatusCajon> estatusCajon() {
        return repository.estatusCajon();
    }

    @Override
    public Optional<EstatusCajon> porIdEstatusCajon(Long id) {
        return Optional.ofNullable(repository.porIdEstatusCajon(id));
    }

    @Override
    public List<AreaEstacionamiento> areasEstacionamiento() {
        return repository.areasEstacionamiento();
    }

    @Override
    public Optional<AreaEstacionamiento> porIdAreaEstacionamiento(Long id) {
        return Optional.ofNullable(repository.porIdAreaEstacionamiento(id));
    }
}
