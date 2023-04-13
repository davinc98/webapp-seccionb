package org.seccionb.webapp.repositories;

import org.seccionb.webapp.entities.*;

import java.util.List;


public interface GuardiasRepository extends CrudRepository<RolDeGuardias>{

    RolDeGuardias porIdRolDeGuardias(Long id);

    EstatusRolGuardia porIdEstatusRolDeGuardia(Long id);

    List<EstatusRolGuardia> estatusRolesDeGuardia();

    TipoGuardia porIdTipoGuardia(Long id);

    List<TipoGuardia> tiposGuardia();

    List<Guardia> guardiasPorIdRol(Long idRol);

    //CRUD Guardia
    List<Guardia> listarGuardias();
    Guardia porIdGuardia(Long id);
    void guardarGuardia(Guardia guardia);
    void eliminarGuardia(Long id);

}
