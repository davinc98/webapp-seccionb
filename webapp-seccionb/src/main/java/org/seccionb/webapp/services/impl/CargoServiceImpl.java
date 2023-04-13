package org.seccionb.webapp.services.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.seccionb.webapp.entities.Cargo;
import org.seccionb.webapp.repositories.CargoRepository;
import org.seccionb.webapp.services.CargoService;

import java.util.List;
import java.util.Optional;


@Stateless
public class CargoServiceImpl implements CargoService {

    @Inject
    private CargoRepository repository;

    @Override
    public List<Cargo> listar() {
        return repository.listar();
    }

    @Override
    public Optional<Cargo> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void guardar(Cargo cargo) {
        repository.guardar(cargo);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }
}
