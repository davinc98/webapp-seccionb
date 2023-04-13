package org.seccionb.webapp.services;


import jakarta.ejb.Local;
import org.seccionb.webapp.entities.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Local
public interface VehiculoEventoService {

    List<VehiculoEvento> listar();
    Optional<VehiculoEvento> porId(Long id);
    void guardar(VehiculoEvento vehiculoEvento);
    void eliminar(Long id);

    Optional<VehiculoEvento> porIdVehiculoEvento(Long id);
    List<VehiculoEvento> listarPorFecha(LocalDate inicio, LocalDate fin);

    List<VehiculoEvento> buscarPorMatricula(String textoBuscar);
}
