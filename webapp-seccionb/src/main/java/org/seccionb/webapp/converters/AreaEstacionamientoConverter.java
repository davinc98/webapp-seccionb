package org.seccionb.webapp.converters;


import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.seccionb.webapp.entities.AreaEstacionamiento;
import org.seccionb.webapp.entities.EstatusCajon;
import org.seccionb.webapp.services.CajonEstacionamientoService;

import java.util.Optional;

@RequestScoped
@Named("areaEstacionamientoConverter")
public class AreaEstacionamientoConverter implements Converter<AreaEstacionamiento> {

    @Inject
    private CajonEstacionamientoService service;

    @Override
    public AreaEstacionamiento getAsObject(FacesContext context, UIComponent component, String id) {
        if(id==null)
            return null;

        Optional<AreaEstacionamiento> areaEstacionamiento = service.porIdAreaEstacionamiento(Long.valueOf(id));
        if(areaEstacionamiento.isPresent())
            return areaEstacionamiento.get();
        else
            return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, AreaEstacionamiento areaEstacionamiento) {
        if(areaEstacionamiento==null)
            return "0";
        return areaEstacionamiento.getId().toString();
    }
}
