package org.seccionb.webapp.services;


import jakarta.ejb.Local;
import org.seccionb.webapp.entities.*;

import java.util.List;
import java.util.Optional;

@Local
public interface EstudianteCargoService {
    List<InternoCargo> listar();
    Optional<InternoCargo> porId(Long id);
    void guardar(InternoCargo estudiante);
    void eliminar(Long id);

    List<InternoCargo> getCargosPorIdEstudiante(Long id);

    List<InternoCargo> getEstudiantesCargoPorCurp(String curp);
}
