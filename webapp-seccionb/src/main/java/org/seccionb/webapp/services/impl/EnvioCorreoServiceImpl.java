package org.seccionb.webapp.services.impl;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.seccionb.webapp.correo.EnvioCorreoController;
import org.seccionb.webapp.entities.CajonEstacionamiento;
import org.seccionb.webapp.entities.EstatusVehiculo;
import org.seccionb.webapp.entities.TipoTarjeton;
import org.seccionb.webapp.entities.Vehiculo;
import org.seccionb.webapp.repositories.VehiculoRepository;
import org.seccionb.webapp.services.EnvioCorreoService;
import org.seccionb.webapp.services.VehiculoService;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class EnvioCorreoServiceImpl implements EnvioCorreoService {

    private static final Logger logger = Logger.getLogger(EnvioCorreoController.class.getName());

    @Resource(name = "java:jboss/mail/gmailSalida")
    private Session mailSesion;

    @Override
    public Boolean enviarCorreo(String correoDestinatario, String asunto, String cuerpo) {
        try {
            logger.log(Level.INFO, "Entrando a enviar correo ");
            Message m = new MimeMessage(mailSesion);
            m.setRecipient(Message.RecipientType.TO, new InternetAddress(correoDestinatario));
            m.setSubject(asunto);
            m.setText(cuerpo);
            Transport.send(m);
            logger.log(Level.INFO, "Correo enviado a: {0}", correoDestinatario);
            return Boolean.TRUE;
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Error al enviar correo a: {0}", correoDestinatario);
        }
        return null;
    }
}
