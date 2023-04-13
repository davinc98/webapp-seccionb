package org.seccionb.webapp.services;


import jakarta.ejb.Local;
import org.seccionb.webapp.entities.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Local
public interface GuardiasService {
    List<RolDeGuardias> listar();
    Optional<RolDeGuardias> porId(Long id);
    void guardar(RolDeGuardias rolDeGuardias);
    void eliminar(Long id);

    Optional<RolDeGuardias> porIdRolGuardias(Long id);

    Optional<EstatusRolGuardia> porIdEstatusRolDeGuardia(Long id);
    List<EstatusRolGuardia> estatusRolesDeGuardia();

    Optional<TipoGuardia> porIdTipoGuardia(Long id);

    List<TipoGuardia> tiposGuardia();

    List<Guardia> guardiasPorIdRol(Long idRol);


    List<Guardia> listarGuardias();
    Optional<Guardia> porIdGuardia(Long id);
    void guardarGuardia(Guardia guardia);
    void eliminarGuardia(Long id);

}
