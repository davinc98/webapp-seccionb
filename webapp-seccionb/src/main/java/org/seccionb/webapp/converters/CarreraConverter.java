package org.seccionb.webapp.converters;


import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.seccionb.webapp.entities.Carrera;
import org.seccionb.webapp.services.EstudianteService;

import java.util.Optional;

@RequestScoped
@Named("carreraConverter")
public class CarreraConverter implements Converter<Carrera> {

    @Inject
    private EstudianteService service;

    @Override
    public Carrera getAsObject(FacesContext context, UIComponent component, String id) {
        if(id==null)
            return null;

        Optional<Carrera> carrera = service.porIdCarrera(Long.valueOf(id));
        if(carrera.isPresent())
            return carrera.get();
        else
            return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Carrera carrera) {
        if(carrera==null)
            return "0";
        return carrera.getId().toString();
    }
}
