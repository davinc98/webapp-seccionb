package org.seccionb.webapp.converters;


import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.seccionb.webapp.entities.Cargo;
import org.seccionb.webapp.entities.TipoTarjeton;
import org.seccionb.webapp.services.CargoService;
import org.seccionb.webapp.services.VehiculoService;

import java.util.Optional;

@RequestScoped
@Named("tipoTarjetonConverter")
public class TipoTarjetonConverter implements Converter<TipoTarjeton> {

    @Inject
    private VehiculoService service;

    @Override
    public TipoTarjeton getAsObject(FacesContext context, UIComponent component, String id) {
        if(id==null)
            return null;

        Optional<TipoTarjeton> tipoTarjeton = service.porIdTipoTarjeton(Long.valueOf(id));
        if(tipoTarjeton.isPresent())
            return tipoTarjeton.get();
        else
            return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, TipoTarjeton tipoTarjeton) {
        if(tipoTarjeton==null)
            return "0";
        return tipoTarjeton.getId().toString();
    }
}
