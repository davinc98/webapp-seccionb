package org.seccionb.webapp.services;


import jakarta.ejb.Local;
import org.seccionb.webapp.entities.*;

import java.util.List;
import java.util.Optional;

@Local
public interface EstudianteCargoService {
    List<EstudianteCargo> listar();
    Optional<EstudianteCargo> porId(Long id);
    void guardar(EstudianteCargo estudiante);
    void eliminar(Long id);

    List<EstudianteCargo> getCargosPorIdEstudiante(Long id);

    List<EstudianteCargo> getEstudiantesCargoPorCurp(String curp);
}
