package org.seccionb.webapp.services.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.seccionb.webapp.entities.*;
import org.seccionb.webapp.repositories.EstudianteRepository;
import org.seccionb.webapp.services.EstudianteService;

import java.util.List;
import java.util.Optional;

@Stateless
public class EstudianteServiceImpl implements EstudianteService {

    @Inject
    private EstudianteRepository repository;

    @Override
    public List<Estudiante> listar() {
        return repository.listar();
    }

    @Override
    public Optional<Estudiante> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }


    @Override
    public void guardar(Estudiante estudiante) {
        repository.guardar(estudiante);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }

    @Override
    public List<Escuela> listarEscuelas() {
        return repository.listarEscuelas();
    }

    @Override
    public List<UnidadAcademica> listarUnidadesAcademicas() {
        return repository.listarUnidadesAcademicas();
    }

    @Override
    public List<EstadoProcedencia> listarEstadosDeProcedencia() {
        return repository.listarEstadosDeProcedencia();
    }

    @Override
    public List<Carrera> listarCarreras() {
        return repository.listarCarreras();
    }

    @Override
    public List<SituacionEscolar> listarSituacionesEscolares() {
        return repository.listarSituacionesAcademicas();
    }

    @Override
    public Optional<Escuela> porIdEscuela(Long id) {
        return Optional.ofNullable( repository.porIdEscuela(id));
    }

    @Override
    public Optional<EstadoProcedencia> porIdEstadoProcedencia(Long id) {
        return Optional.ofNullable( repository.porIdEstadoProcedencia(id));
    }

    @Override
    public Optional<SituacionEscolar> porIdSituacionEscolar(Long id) {
        return Optional.ofNullable(repository.porIdSituacionEscolar(id));
    }

    @Override
    public Optional<Carrera> porIdCarrera(Long id) {
        return Optional.ofNullable(repository.porIdCarrera(id));
    }

    @Override
    public Persona getPersonaPorIdEstudiante(Long id) {
        return repository.getPersonaPorIdEstudiante(id);
    }

    @Override
    public Estudiante getEstudiantePorNombreUsuario(String username) {
        return repository.getEstudiantePorNombreUsuario(username);
    }

    @Override
    public List<EstatusEstudiante> listarEstatusEstudiantes() {
        return repository.listarEstatusEstudiantes();
    }

    @Override
    public Optional<EstatusEstudiante> porIdEstatusEstudiante(Long id) {
        return Optional.ofNullable(repository.porIdEstatusEstudiante(id));
    }

    @Override
    public List<Estudiante> listarEstudiantesActivos() {
        return repository.listarEstudiantesActivos();
    }

    @Override
    public List<Estudiante> listarEstudiantesEgresados() {
        return repository.listarEstudiantesEgresados();
    }

    @Override
    public List<Estudiante> listarEstudiantesBajaTemporal() {
        return repository.listarEstudiantesBajaTemporal();
    }

    @Override
    public List<Estudiante> listarEstudiantesBajaDefinitiva() {
        return repository.listarEstudiantesBajaDefinitiva();
    }

    @Override
    public List<Estudiante> getEstudiantesPorCurp(String curp) {
        return repository.getEstudiantesPorCurp(curp);
    }

    @Override
    public Estudiante getEstudiantePorIdPersona(Long personaId) {
        return repository.getEstudiantePorIdPersona(personaId);
    }
}
