package org.seccionb.webapp.services.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.seccionb.webapp.entities.SolicitudResetPassword;
import org.seccionb.webapp.repositories.SolicitudResetPasswordRepository;
import org.seccionb.webapp.services.SolicitudResetPasswordService;

import java.util.List;
import java.util.Optional;

@Stateless
public class SolicitudResetPasswordServiceImpl implements SolicitudResetPasswordService {

    @Inject
    private SolicitudResetPasswordRepository repository;


    @Override
    public List<SolicitudResetPassword> listar() {
        return repository.listar();
    }

    @Override
    public Optional<SolicitudResetPassword> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void guardar(SolicitudResetPassword solicitudResetPassword) {
        repository.guardar(solicitudResetPassword);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }

}
