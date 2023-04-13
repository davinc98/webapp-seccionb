package org.seccionb.webapp.services;


import jakarta.ejb.Local;
import org.seccionb.webapp.entities.*;

import java.util.List;
import java.util.Optional;

@Local
public interface EstudianteService {
    List<Estudiante> listar();
    Optional<Estudiante> porId(Long id);
    void guardar(Estudiante estudiante);
    void eliminar(Long id);

    List<Escuela> listarEscuelas();

    List<UnidadAcademica> listarUnidadesAcademicas();

    List<EstadoProcedencia> listarEstadosDeProcedencia();

    List<Carrera> listarCarreras();

    List<SituacionEscolar> listarSituacionesEscolares();

    Optional<Escuela> porIdEscuela(Long id);

    Optional<SituacionEscolar> porIdSituacionEscolar(Long id);

    Optional<Carrera> porIdCarrera(Long id);

    Optional<EstadoProcedencia> porIdEstadoProcedencia(Long id);

    Persona getPersonaPorIdEstudiante(Long id);

    Estudiante getEstudiantePorNombreUsuario(String username);

    List<EstatusEstudiante> listarEstatusEstudiantes();

    Optional<EstatusEstudiante> porIdEstatusEstudiante(Long id);

    List<Estudiante> listarEstudiantesActivos();

    List<Estudiante> listarEstudiantesEgresados();

    List<Estudiante> listarEstudiantesBajaTemporal();

    List<Estudiante> listarEstudiantesBajaDefinitiva();

    List<Estudiante> getEstudiantesPorCurp(String curp);

    Estudiante getEstudiantePorIdPersona(Long personaId);
}
