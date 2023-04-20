package org.seccionb.webapp.repositories;

import org.seccionb.webapp.entities.*;

import java.util.List;

public interface EstudianteRepository extends CrudRepository<Interno>{

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

//    Interno getEstudiantePorNombreUsuario(String username);

    List<EstatusInterno> listarEstatusEstudiantes();

    EstatusInterno porIdEstatusEstudiante(Long id);

    List<Interno> listarEstudiantesActivos();

    List<Interno> listarEstudiantesEgresados();

    List<Interno> listarEstudiantesBajaTemporal();

    List<Interno> listarEstudiantesBajaDefinitiva();

    List<Interno> getEstudiantesPorCurp(String curp);

    Interno getEstudiantePorIdPersona(Long personaId);
}
