package org.seccionb.webapp.converters;


import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.seccionb.webapp.entities.CajonEstacionamiento;
import org.seccionb.webapp.entities.TipoTarjeton;
import org.seccionb.webapp.services.CajonEstacionamientoService;
import org.seccionb.webapp.services.VehiculoService;

import java.util.Optional;

@RequestScoped
@Named("cajonEstacionamientoConverter")
public class CajonEstacionamientoConverter implements Converter<CajonEstacionamiento> {

    @Inject
    private VehiculoService service;

    @Override
    public CajonEstacionamiento getAsObject(FacesContext context, UIComponent component, String id) {
        if(id==null)
            return null;

        Optional<CajonEstacionamiento> cajonEstacionamiento = service.porIdCajonEstacionamiento(Long.valueOf(id));
        if(cajonEstacionamiento.isPresent())
            return cajonEstacionamiento.get();
        else
            return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, CajonEstacionamiento cajonEstacionamiento) {
        if(cajonEstacionamiento==null)
            return "0";
        return cajonEstacionamiento.getId().toString();
    }
}
