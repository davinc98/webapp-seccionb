package org.seccionb.webapp.services.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.seccionb.webapp.entities.*;
import org.seccionb.webapp.repositories.GuardiasRepository;
import org.seccionb.webapp.repositories.VehiculoRepository;
import org.seccionb.webapp.services.GuardiasService;
import org.seccionb.webapp.services.VehiculoService;

import java.util.List;
import java.util.Optional;

@Stateless
public class GuardiasServiceImpl implements GuardiasService {

    @Inject
    private GuardiasRepository repository;

    @Override
    public List<RolDeGuardias> listar() {
        return repository.listar();
    }

    @Override
    public Optional<RolDeGuardias> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }


    @Override
    public void guardar(RolDeGuardias rolDeGuardias) {
        repository.guardar(rolDeGuardias);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }

    @Override
    public Optional<RolDeGuardias> porIdRolGuardias(Long id) {
        return Optional.ofNullable(repository.porIdRolDeGuardias(id));
    }

    @Override
    public Optional<EstatusRolGuardia> porIdEstatusRolDeGuardia(Long id) {
        return Optional.ofNullable(repository.porIdEstatusRolDeGuardia(id));
    }

    @Override
    public List<EstatusRolGuardia> estatusRolesDeGuardia() {
        return repository.estatusRolesDeGuardia();
    }

    @Override
    public Optional<TipoGuardia> porIdTipoGuardia(Long id) {
        return Optional.ofNullable(repository.porIdTipoGuardia(id));
    }

    @Override
    public List<TipoGuardia> tiposGuardia() {
        return repository.tiposGuardia();
    }

    @Override
    public List<Guardia> guardiasPorIdRol(Long idRol) {
        return repository.guardiasPorIdRol(idRol);
    }

    @Override
    public List<Guardia> listarGuardias() {
        return repository.listarGuardias();
    }

    @Override
    public Optional<Guardia> porIdGuardia(Long id) {
        return Optional.ofNullable(repository.porIdGuardia(id));
    }

    @Override
    public void guardarGuardia(Guardia guardia) {
        repository.guardarGuardia(guardia);
    }

    @Override
    public void eliminarGuardia(Long id) {
        repository.eliminarGuardia(id);
    }

}
