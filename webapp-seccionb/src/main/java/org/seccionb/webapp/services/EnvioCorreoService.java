package org.seccionb.webapp.services;


import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.jws.WebService;

@Local
@WebService
@Stateless
public interface EnvioCorreoService {

    public Boolean enviarCorreo(String correoDestinatario, String asunto, String cuerpo);
}
