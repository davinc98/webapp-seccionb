package org.seccionb.webapp.repositories;

import org.seccionb.webapp.entities.Grado;

import java.util.List;

public interface GradoRepository extends CrudRepository<Grado>{

    List<Grado> listar();
    Grado porId(Long id);
    void guardar(Grado grado);
    void eliminar(Long id);
}
