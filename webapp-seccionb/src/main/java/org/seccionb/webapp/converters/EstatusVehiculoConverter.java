package org.seccionb.webapp.converters;


import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.seccionb.webapp.entities.EstatusVehiculo;
import org.seccionb.webapp.entities.TipoTarjeton;
import org.seccionb.webapp.services.VehiculoService;

import java.util.Optional;

@RequestScoped
@Named("estatusVehiculoConverter")
public class EstatusVehiculoConverter implements Converter<EstatusVehiculo> {

    @Inject
    private VehiculoService service;

    @Override
    public EstatusVehiculo getAsObject(FacesContext context, UIComponent component, String id) {
        if(id==null)
            return null;

        Optional<EstatusVehiculo> estatusVehiculo = service.porIdEstatusVehiculo(Long.valueOf(id));
        if(estatusVehiculo.isPresent())
            return estatusVehiculo.get();
        else
            return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, EstatusVehiculo estatusVehiculo) {
        if(estatusVehiculo==null)
            return "0";
        return estatusVehiculo.getId().toString();
    }
}
