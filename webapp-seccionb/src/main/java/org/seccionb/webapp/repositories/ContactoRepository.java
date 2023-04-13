package org.seccionb.webapp.repositories;

import org.seccionb.webapp.entities.Contacto;
import org.seccionb.webapp.entities.TipoContacto;

import java.util.List;

public interface ContactoRepository extends CrudRepository<Contacto>{

    List<Contacto> listar();
    Contacto porId(Long id);
    void guardar(Contacto cargo);
    void eliminar(Long id);

    List<Contacto> contactosPorIdPersona(Long id);

    List<TipoContacto> listarTiposContacto();
    TipoContacto porIdTipoContacto(Long id);
    void guardarTipoContacto(TipoContacto tipoContacto);
    void eliminarTipoContacto(Long id);
}
