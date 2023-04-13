package org.seccionb.webapp.services;


import jakarta.ejb.Local;
import org.seccionb.webapp.entities.FotoTemporal;
import org.seccionb.webapp.entities.Persona;

import java.util.List;
import java.util.Optional;

@Local
public interface PersonaService {
    List<Persona> listar();
    Optional<Persona> porId(Long id);

    Optional<Persona> porIdPersona(Long id);
    void guardar(Persona persona);
    void eliminar(Long id);
    List<Persona> listarUsuariosEstacionamiento();

    List<Persona> listarPersonasEstudiantes();

    List<Persona> listarPersonasEstudiantesActivos();

    List<Persona> getPersonasPorCurp(String curp);

    void guardarImagenTemporalBase64(FotoTemporal fotoTemporal);

    void eliminarImagenTemporalBase64();

    FotoTemporal getImagenTemporalBase64();

    void eliminarFotoBase64PorId(Long personaId);
}
