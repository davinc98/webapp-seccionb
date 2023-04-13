package org.seccionb.webapp.converters;


import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.seccionb.webapp.entities.Escuela;
import org.seccionb.webapp.services.EstudianteService;

import java.util.Optional;

@RequestScoped
@Named("escuelaConverter")
public class EscuelaConverter implements Converter<Escuela> {

    @Inject
    private EstudianteService service;

    @Override
    public Escuela getAsObject(FacesContext context, UIComponent component, String id) {
        if(id==null)
            return null;

        Optional<Escuela> escuela = service.porIdEscuela(Long.valueOf(id));
        if(escuela.isPresent())
            return escuela.get();
        else
            return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Escuela escuela) {
        if(escuela==null)
            return "0";
        return escuela.getId().toString();
    }
}
