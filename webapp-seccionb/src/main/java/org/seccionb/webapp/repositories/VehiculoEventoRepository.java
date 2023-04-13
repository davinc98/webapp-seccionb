package org.seccionb.webapp.repositories;

import org.seccionb.webapp.entities.*;

import java.time.LocalDate;
import java.util.List;

public interface VehiculoEventoRepository extends CrudRepository<VehiculoEvento>{
    VehiculoEvento porIdVehiculoEvento(Long id);
    List<VehiculoEvento> listarPorFecha(LocalDate inicio, LocalDate fin);

    List<VehiculoEvento> buscarPorMatricula(String textoBuscar);
}
