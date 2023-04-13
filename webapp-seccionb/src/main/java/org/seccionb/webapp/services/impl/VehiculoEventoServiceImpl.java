package org.seccionb.webapp.services.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.seccionb.webapp.entities.*;
import org.seccionb.webapp.repositories.VehiculoEventoRepository;
import org.seccionb.webapp.repositories.VehiculoRepository;
import org.seccionb.webapp.services.VehiculoEventoService;
import org.seccionb.webapp.services.VehiculoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Stateless
public class VehiculoEventoServiceImpl implements VehiculoEventoService {

    @Inject
    private VehiculoEventoRepository repository;


    @Override
    public List<VehiculoEvento> listar() {
        return repository.listar();
    }

    @Override
    public Optional<VehiculoEvento> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void guardar(VehiculoEvento vehiculoEvento) {
        repository.guardar(vehiculoEvento);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }

    @Override
    public Optional<VehiculoEvento> porIdVehiculoEvento(Long id) {
        return Optional.ofNullable(repository.porIdVehiculoEvento(id));
    }

    @Override
    public List<VehiculoEvento> listarPorFecha(LocalDate inicio, LocalDate fin) {
        return repository.listarPorFecha(inicio, fin);
    }

    @Override
    public List<VehiculoEvento> buscarPorMatricula(String textoBuscar) {
        return repository.buscarPorMatricula(textoBuscar);
    }
}
