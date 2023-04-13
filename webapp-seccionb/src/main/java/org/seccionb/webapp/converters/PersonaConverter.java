package org.seccionb.webapp.converters;


import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.seccionb.webapp.entities.Persona;
import org.seccionb.webapp.services.PersonaService;

import java.util.Optional;

@RequestScoped
@Named("personaConverter")
public class PersonaConverter implements Converter<Persona> {

    @Inject
    private PersonaService service;

    @Override
    public Persona getAsObject(FacesContext context, UIComponent component, String id) {
        if(id==null)
            return null;

        Optional<Persona> persona = service.porIdPersona(Long.valueOf(id));
        if(persona.isPresent())
            return persona.get();
        else
            return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Persona persona) {
        if(persona==null)
            return "0";
        return persona.getId().toString();
    }
}
