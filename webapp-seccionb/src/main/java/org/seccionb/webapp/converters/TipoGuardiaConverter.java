package org.seccionb.webapp.converters;


import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.seccionb.webapp.entities.TipoGuardia;
import org.seccionb.webapp.services.GuardiasService;

import java.util.Optional;

@RequestScoped
@Named("tipoGuardiaConverter")
public class TipoGuardiaConverter implements Converter<TipoGuardia> {

    @Inject
    private GuardiasService service;

    @Override
    public TipoGuardia getAsObject(FacesContext context, UIComponent component, String id) {
        if(id==null)
            return null;

        Optional<TipoGuardia> tipoGuardia = service.porIdTipoGuardia(Long.valueOf(id));
        if(tipoGuardia.isPresent())
            return tipoGuardia.get();
        else
            return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, TipoGuardia tipoGuardia) {
        if(tipoGuardia==null)
            return "0";
        return tipoGuardia.getId().toString();
    }
}
