package org.seccionb.webapp.repositories;

import org.seccionb.webapp.entities.FotoTemporal;
import org.seccionb.webapp.entities.Persona;

import java.util.List;

public interface PersonaRepository extends CrudRepository<Persona>{
    List<Persona> listarUsuariosEstacionamiento();

    Persona porIdPersona(Long id);

    List<Persona> listarPersonasEstudiantes();

    List<Persona> listarPersonasEstudiantesActivos();

    List<Persona> getPersonasPorCurp(String curp);

    void guardarImagenTemporalBase64(FotoTemporal fotoTemporal);

    void eliminarImagenTemporalBase64();

    FotoTemporal getImagenTemporalBase64();

    void eliminarFotoBase64PorId(Long personaId);
}
