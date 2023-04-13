package org.seccionb.webapp.converters;


import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.seccionb.webapp.entities.Cargo;
import org.seccionb.webapp.entities.EstatusCajon;
import org.seccionb.webapp.services.CajonEstacionamientoService;
import org.seccionb.webapp.services.CargoService;

import java.util.Optional;

@RequestScoped
@Named("estatusCajonConverter")
public class EstatusCajonConverter implements Converter<EstatusCajon> {

    @Inject
    private CajonEstacionamientoService service;

    @Override
    public EstatusCajon getAsObject(FacesContext context, UIComponent component, String id) {
        if(id==null)
            return null;

        Optional<EstatusCajon> estatusCajon = service.porIdEstatusCajon(Long.valueOf(id));
        if(estatusCajon.isPresent())
            return estatusCajon.get();
        else
            return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, EstatusCajon estatusCajon) {
        if(estatusCajon==null)
            return "0";
        return estatusCajon.getId().toString();
    }
}
