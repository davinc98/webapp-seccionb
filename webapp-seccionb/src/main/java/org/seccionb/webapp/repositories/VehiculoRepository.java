package org.seccionb.webapp.repositories;

import org.seccionb.webapp.entities.CajonEstacionamiento;
import org.seccionb.webapp.entities.EstatusVehiculo;
import org.seccionb.webapp.entities.TipoTarjeton;
import org.seccionb.webapp.entities.Vehiculo;

import java.util.List;

public interface VehiculoRepository extends CrudRepository<Vehiculo>{
    List<Vehiculo> vehiculosPorIdPersona(Long idPersona);

    List<TipoTarjeton> tiposTarjeton();

    List<EstatusVehiculo> estatusVehiculo();

    TipoTarjeton porIdTipoTarjeton(Long id);

    EstatusVehiculo porIdEstatusVehiculo(Long id);

    CajonEstacionamiento porIdCajonEstacionamiento(Long id);

    Vehiculo porMatriculaVehiculo(String matricula);

    List<Vehiculo> getVehiculosPorMatricula(String matricula);
}
