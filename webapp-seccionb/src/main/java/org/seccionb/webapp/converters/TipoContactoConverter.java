package org.seccionb.webapp.converters;


import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.seccionb.webapp.entities.Cargo;
import org.seccionb.webapp.entities.TipoContacto;
import org.seccionb.webapp.services.CargoService;
import org.seccionb.webapp.services.ContactoService;

import java.util.Optional;

@RequestScoped
@Named("tipoContactoConverter")
public class TipoContactoConverter implements Converter<TipoContacto> {

    @Inject
    private ContactoService service;

    @Override
    public TipoContacto getAsObject(FacesContext context, UIComponent component, String id) {
        if(id==null)
            return null;

        Optional<TipoContacto> tipoContacto = service.porIdTipoContacto(Long.valueOf(id));
        if(tipoContacto.isPresent())
            return tipoContacto.get();
        else
            return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, TipoContacto tipoContacto) {
        if(tipoContacto==null)
            return "0";
        return tipoContacto.getId().toString();
    }
}
