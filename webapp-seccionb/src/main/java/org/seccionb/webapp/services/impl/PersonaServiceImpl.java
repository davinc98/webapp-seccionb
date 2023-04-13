package org.seccionb.webapp.services.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.seccionb.webapp.entities.FotoTemporal;
import org.seccionb.webapp.entities.Persona;
import org.seccionb.webapp.repositories.PersonaRepository;
import org.seccionb.webapp.services.PersonaService;

import java.util.List;
import java.util.Optional;

@Stateless
public class PersonaServiceImpl implements PersonaService {

    @Inject
    private PersonaRepository repository;

    @Override
    public List<Persona> listar() {
        return repository.listar();
    }

    @Override
    public Optional<Persona> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public Optional<Persona> porIdPersona(Long id) {
        return Optional.ofNullable(repository.porIdPersona(id));
    }


    @Override
    public void guardar(Persona persona) {
        repository.guardar(persona);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }

    @Override
    public List<Persona> listarUsuariosEstacionamiento() {
        return repository.listarUsuariosEstacionamiento();
    }

    @Override
    public List<Persona> listarPersonasEstudiantes() {
        return repository.listarPersonasEstudiantes();
    }

    @Override
    public List<Persona> listarPersonasEstudiantesActivos() {
        return repository.listarPersonasEstudiantesActivos();
    }

    @Override
    public List<Persona> getPersonasPorCurp(String curp) {
        return repository.getPersonasPorCurp(curp);
    }

    @Override
    public void guardarImagenTemporalBase64(FotoTemporal fotoTemporal) {
        repository.guardarImagenTemporalBase64(fotoTemporal);
    }

    @Override
    public void eliminarImagenTemporalBase64() {
        repository.eliminarImagenTemporalBase64();
    }

    @Override
    public FotoTemporal getImagenTemporalBase64() {
        return repository.getImagenTemporalBase64();
    }

    @Override
    public void eliminarFotoBase64PorId(Long personaId) {
        repository.eliminarFotoBase64PorId(personaId);
    }
}
