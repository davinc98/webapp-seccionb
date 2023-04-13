package org.seccionb.webapp.converters;


import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.seccionb.webapp.entities.SituacionEscolar;
import org.seccionb.webapp.services.EstudianteService;

import java.util.Optional;

@RequestScoped
@Named("situacionEscolarConverter")
public class SituacionEscolarConverter implements Converter<SituacionEscolar> {

    @Inject
    private EstudianteService service;

    @Override
    public SituacionEscolar getAsObject(FacesContext context, UIComponent component, String id) {
        if(id==null)
            return null;

        Optional<SituacionEscolar> situacionEscolar = service.porIdSituacionEscolar(Long.valueOf(id));
        if(situacionEscolar.isPresent())
            return situacionEscolar.get();
        else
            return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, SituacionEscolar situacionEscolar) {
        if(situacionEscolar==null)
            return "0";
        return situacionEscolar.getId().toString();
    }
}
