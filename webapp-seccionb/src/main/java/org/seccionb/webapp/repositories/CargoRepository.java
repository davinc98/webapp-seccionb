package org.seccionb.webapp.repositories;

import org.seccionb.webapp.entities.Cargo;

import java.util.List;

public interface CargoRepository extends CrudRepository<Cargo>{

    List<Cargo> listar();
    Cargo porId(Long id);
    void guardar(Cargo cargo);
    void eliminar(Long id);
}
