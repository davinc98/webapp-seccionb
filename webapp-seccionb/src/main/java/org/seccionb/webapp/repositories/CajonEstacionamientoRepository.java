package org.seccionb.webapp.repositories;

import org.seccionb.webapp.entities.AreaEstacionamiento;
import org.seccionb.webapp.entities.CajonEstacionamiento;
import org.seccionb.webapp.entities.EstatusCajon;

import java.util.List;

public interface CajonEstacionamientoRepository extends CrudRepository<CajonEstacionamiento>{

    List<CajonEstacionamiento> listar();
    CajonEstacionamiento porId(Long id);
    void guardar(CajonEstacionamiento cajonEstacionamiento);
    void eliminar(Long id);

    List<EstatusCajon> estatusCajon();

    EstatusCajon porIdEstatusCajon(Long id);

    List<AreaEstacionamiento> areasEstacionamiento();

    AreaEstacionamiento porIdAreaEstacionamiento(Long id);
}
