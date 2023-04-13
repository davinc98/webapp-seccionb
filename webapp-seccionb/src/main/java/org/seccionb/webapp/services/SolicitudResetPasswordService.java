package org.seccionb.webapp.services;


import jakarta.ejb.Local;
import org.seccionb.webapp.entities.SolicitudResetPassword;

import java.util.List;
import java.util.Optional;

@Local
public interface SolicitudResetPasswordService {

    List<SolicitudResetPassword> listar();
    Optional<SolicitudResetPassword> porId(Long id);
    void guardar(SolicitudResetPassword solicitudResetPassword);
    void eliminar(Long id);
}
