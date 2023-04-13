package org.seccionb.webapp.correo;


import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.seccionb.webapp.services.EnvioCorreoService;

import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@Named("envioCorreoController")
public class EnvioCorreoController {
    private static final Logger logger = Logger.getLogger(EnvioCorreoController.class.getName());

    @Resource(name = "java:jboss/mail/gmailSalida")
    private Session mailSesion;

    private String correoDestinatario;
    private String asunto;
    private String cuerpo;

    @Inject
    private EnvioCorreoService envioCorreoService;


    public Boolean enviarCorreo(String correoDestinatario, String asunto, String cuerpo) {
        envioCorreoService.enviarCorreo(correoDestinatario, asunto, cuerpo);
        return null;
    }


    public String getCorreoDestinatario() {
        return correoDestinatario;
    }

    public void setCorreoDestinatario(String correoDestinatario) {
        this.correoDestinatario = correoDestinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }
}