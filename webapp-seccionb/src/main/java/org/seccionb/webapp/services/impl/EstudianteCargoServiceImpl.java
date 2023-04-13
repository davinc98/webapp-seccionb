package org.seccionb.webapp.services.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.seccionb.webapp.entities.EstudianteCargo;
import org.seccionb.webapp.repositories.EstudianteCargoRepository;
import org.seccionb.webapp.services.EstudianteCargoService;

import java.util.List;
import java.util.Optional;

@Stateless
public class EstudianteCargoServiceImpl implements EstudianteCargoService {

    @Inject
    EstudianteCargoRepository repository;

    @Override
    public List<EstudianteCargo> listar() {
        return repository.listar();
    }

    @Override
    public Optional<EstudianteCargo> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void guardar(EstudianteCargo estudianteCargo) {
        repository.guardar(estudianteCargo);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }

    @Override
    public List<EstudianteCargo> getCargosPorIdEstudiante(Long id) {
        return repository.getCargosPorIdEstudiante(id);
    }

    @Override
    public List<EstudianteCargo> getEstudiantesCargoPorCurp(String curp) {
        return repository.getEstudiantesCargoPorCurp(curp);
    }
}
