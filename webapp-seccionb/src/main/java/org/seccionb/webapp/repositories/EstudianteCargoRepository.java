package org.seccionb.webapp.repositories;

import org.seccionb.webapp.entities.Cargo;
import org.seccionb.webapp.entities.InternoCargo;

import java.util.List;

public interface EstudianteCargoRepository extends CrudRepository<InternoCargo>{

    List<InternoCargo> listar();
    InternoCargo porId(Long id);
    void guardar(InternoCargo estudianteCargo);
    void eliminar(Long id);
    List<InternoCargo> getCargosPorIdEstudiante(Long id);

    List<InternoCargo> getEstudiantesCargoPorCurp(String curp);
}
