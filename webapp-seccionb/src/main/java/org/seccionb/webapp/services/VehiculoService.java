package org.seccionb.webapp.services;


import jakarta.ejb.Local;
import jakarta.jws.WebService;
import org.seccionb.webapp.entities.*;

import java.util.List;
import java.util.Optional;

@Local
@WebService
public interface VehiculoService {
    List<Vehiculo> listar();
    List<Vehiculo> vehiculosPorIdPersona(Long idPersona);
    Optional<Vehiculo> porId(Long id);
    void guardar(Vehiculo vehiculo);
    void eliminar(Long id);

    Optional<TipoTarjeton> porIdTipoTarjeton(Long id);
    List<TipoTarjeton> tiposTarjeton();

    Optional<EstatusVehiculo> porIdEstatusVehiculo(Long id);
    List<EstatusVehiculo> estatusVehiculo();

    Optional<CajonEstacionamiento> porIdCajonEstacionamiento(Long id);

    Optional<Vehiculo> porMatriculaVehiculo(String matricula);

    List<Vehiculo> getVehiculosPorMatricula(String matricula);
}
