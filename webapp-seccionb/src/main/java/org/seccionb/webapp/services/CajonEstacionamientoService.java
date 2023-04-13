package org.seccionb.webapp.services;


import jakarta.ejb.Local;
import org.seccionb.webapp.entities.AreaEstacionamiento;
import org.seccionb.webapp.entities.CajonEstacionamiento;
import org.seccionb.webapp.entities.Cargo;
import org.seccionb.webapp.entities.EstatusCajon;

import java.util.List;
import java.util.Optional;

@Local
public interface CajonEstacionamientoService {

    List<CajonEstacionamiento> listar();
    Optional<CajonEstacionamiento> porId(Long id);
    void guardar(CajonEstacionamiento cajonEstacionamiento);
    void eliminar(Long id);

    List<EstatusCajon> estatusCajon();

    Optional<EstatusCajon> porIdEstatusCajon(Long id);

    List<AreaEstacionamiento> areasEstacionamiento();

    Optional<AreaEstacionamiento> porIdAreaEstacionamiento(Long id);

}
