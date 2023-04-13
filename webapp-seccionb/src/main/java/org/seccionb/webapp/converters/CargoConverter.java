package org.seccionb.webapp.converters;


import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.seccionb.webapp.entities.Cargo;
import org.seccionb.webapp.services.CargoService;

import java.util.Optional;

@RequestScoped
@Named("cargoConverter")
public class CargoConverter implements Converter<Cargo> {

    @Inject
    private CargoService service;

    @Override
    public Cargo getAsObject(FacesContext context, UIComponent component, String id) {
        if(id==null)
            return null;

        Optional<Cargo> cargo = service.porId(Long.valueOf(id));
        if(cargo.isPresent())
            return cargo.get();
        else
            return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Cargo cargo) {
        if(cargo==null)
            return "0";
        return cargo.getId().toString();
    }
}
