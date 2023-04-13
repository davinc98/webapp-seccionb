package org.seccionb.webapp.controllers;


import jakarta.enterprise.inject.Model;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Model
@Named("logoutController")
public class LogoutController {

    @Inject
    private FacesContext facesContext;

    public String logout() throws ServletException {
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        request.logout();
        request.getSession().invalidate();
        facesContext.addMessage(null, new FacesMessage("Sesión cerrada con éxito."));
        return "login.xhtml";
    }

}
