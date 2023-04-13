package org.seccionb.webapp.repositories;

import org.seccionb.webapp.entities.Cargo;
import org.seccionb.webapp.entities.EstudianteCargo;

import java.util.List;

public interface EstudianteCargoRepository extends CrudRepository<EstudianteCargo>{

    List<EstudianteCargo> listar();
    EstudianteCargo porId(Long id);
    void guardar(EstudianteCargo estudianteCargo);
    void eliminar(Long id);
    List<EstudianteCargo> getCargosPorIdEstudiante(Long id);

    List<EstudianteCargo> getEstudiantesCargoPorCurp(String curp);
}
