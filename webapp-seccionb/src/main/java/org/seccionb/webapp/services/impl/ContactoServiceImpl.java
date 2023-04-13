package org.seccionb.webapp.services.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.seccionb.webapp.entities.Contacto;
import org.seccionb.webapp.entities.TipoContacto;
import org.seccionb.webapp.repositories.ContactoRepository;
import org.seccionb.webapp.services.ContactoService;

import java.util.List;
import java.util.Optional;


@Stateless
public class ContactoServiceImpl implements ContactoService {

    @Inject
    private ContactoRepository repository;

    @Override
    public List<Contacto> listar() {
        return repository.listar();
    }

    @Override
    public Optional<Contacto> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void guardar(Contacto contacto) {
        repository.guardar(contacto);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }

    @Override
    public List<Contacto> contactosPorIdPersona(Long id) {
        return repository.contactosPorIdPersona(id);
    }

    @Override
    public List<TipoContacto> listarTipoContactos() {
        return repository.listarTiposContacto();
    }

    @Override
    public Optional<TipoContacto> porIdTipoContacto(Long id) {
        return Optional.ofNullable(repository.porIdTipoContacto(id));
    }

    @Override
    public void guardarTipoContacto(TipoContacto tipoContacto) {
        repository.guardarTipoContacto(tipoContacto);
    }

    @Override
    public void eliminarTipoContacto(Long id) {
        repository.eliminarTipoContacto(id);
    }
}
