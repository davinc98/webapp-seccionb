package org.seccionb.webapp.services;


import jakarta.ejb.Local;
import org.seccionb.webapp.entities.Cargo;

import java.util.List;
import java.util.Optional;

@Local
public interface CargoService {

    List<Cargo> listar();
    Optional<Cargo> porId(Long id);
    void guardar(Cargo cargo);
    void eliminar(Long id);

}
