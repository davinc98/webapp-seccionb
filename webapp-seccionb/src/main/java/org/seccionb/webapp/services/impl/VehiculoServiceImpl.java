package org.seccionb.webapp.services.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.seccionb.webapp.entities.*;
import org.seccionb.webapp.repositories.PersonaRepository;
import org.seccionb.webapp.repositories.VehiculoRepository;
import org.seccionb.webapp.services.PersonaService;
import org.seccionb.webapp.services.VehiculoService;

import java.util.List;
import java.util.Optional;

@Stateless
public class VehiculoServiceImpl implements VehiculoService {

    @Inject
    private VehiculoRepository repository;

    @Override
    public List<Vehiculo> listar() {
        return repository.listar();
    }

    @Override
    public List<Vehiculo> vehiculosPorIdPersona(Long idPersona) {
        return repository.vehiculosPorIdPersona(idPersona);
    }

    @Override
    public Optional<Vehiculo> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }


    @Override
    public void guardar(Vehiculo vehiculo) {
        repository.guardar(vehiculo);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }

    @Override
    public Optional<TipoTarjeton> porIdTipoTarjeton(Long id) {
        return Optional.ofNullable(repository.porIdTipoTarjeton(id));
    }

    @Override
    public List<TipoTarjeton> tiposTarjeton() {
        return repository.tiposTarjeton();
    }

    @Override
    public Optional<EstatusVehiculo> porIdEstatusVehiculo(Long id) {
        return Optional.ofNullable(repository.porIdEstatusVehiculo(id));
    }

    @Override
    public List<EstatusVehiculo> estatusVehiculo() {
        return repository.estatusVehiculo();
    }

    @Override
    public Optional<CajonEstacionamiento> porIdCajonEstacionamiento(Long id) {
        return Optional.ofNullable(repository.porIdCajonEstacionamiento(id));
    }

    @Override
    public Optional<Vehiculo> porMatriculaVehiculo(String matricula) {
        return Optional.ofNullable(repository.porMatriculaVehiculo(matricula));
    }

    @Override
    public List<Vehiculo> getVehiculosPorMatricula(String matricula) {
        return repository.getVehiculosPorMatricula(matricula);
    }
}
