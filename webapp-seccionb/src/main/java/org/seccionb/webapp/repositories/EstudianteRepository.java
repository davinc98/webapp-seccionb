package org.seccionb.webapp.repositories;

import org.seccionb.webapp.entities.*;

import java.util.List;

public interface EstudianteRepository extends CrudRepository<Estudiante>{

    List<Escuela> listarEscuelas();

    List<UnidadAcademica> listarUnidadesAcademicas();

    List<Carrera> listarCarreras();

    List<SituacionEscolar> listarSituacionesAcademicas();

    List<EstadoProcedencia> listarEstadosDeProcedencia();

    EstadoProcedencia porIdEstadoProcedencia(Long id);

    Escuela porIdEscuela(Long id);

    SituacionEscolar porIdSituacionEscolar(Long id);

    Carrera porIdCarrera(Long id);

    Persona getPersonaPorIdEstudiante(Long id);

    Estudiante getEstudiantePorNombreUsuario(String username);

    List<EstatusEstudiante> listarEstatusEstudiantes();

    EstatusEstudiante porIdEstatusEstudiante(Long id);

    List<Estudiante> listarEstudiantesActivos();

    List<Estudiante> listarEstudiantesEgresados();

    List<Estudiante> listarEstudiantesBajaTemporal();

    List<Estudiante> listarEstudiantesBajaDefinitiva();

    List<Estudiante> getEstudiantesPorCurp(String curp);

    Estudiante getEstudiantePorIdPersona(Long personaId);
}
