package org.seccionb.webapp.services;


import jakarta.ejb.Local;
import org.seccionb.webapp.entities.Contacto;
import org.seccionb.webapp.entities.TipoContacto;

import java.util.List;
import java.util.Optional;

@Local
public interface ContactoService {

    List<Contacto> listar();
    Optional<Contacto> porId(Long id);
    void guardar(Contacto contacto);
    void eliminar(Long id);

    List<Contacto> contactosPorIdPersona(Long id);

    List<TipoContacto> listarTipoContactos();
    Optional<TipoContacto> porIdTipoContacto(Long id);
    void guardarTipoContacto(TipoContacto tipoContacto);
    void eliminarTipoContacto(Long id);

}
