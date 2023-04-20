package org.seccionb.webapp.services;


import jakarta.ejb.Local;
import org.seccionb.webapp.entities.*;

import java.util.List;
import java.util.Optional;

@Local
public interface EstudianteService {
    List<Interno> listar();
    Optional<Interno> porId(Long id);
    void guardar(Interno interno);
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

//    Interno getEstudiantePorNombreUsuario(String username);

    List<EstatusInterno> listarEstatusEstudiantes();

    Optional<EstatusInterno> porIdEstatusEstudiante(Long id);

    List<Interno> listarEstudiantesActivos();

    List<Interno> listarEstudiantesEgresados();

    List<Interno> listarEstudiantesBajaTemporal();

    List<Interno> listarEstudiantesBajaDefinitiva();

    List<Interno> getEstudiantesPorCurp(String curp);

    Interno getEstudiantePorIdPersona(Long personaId);
}
