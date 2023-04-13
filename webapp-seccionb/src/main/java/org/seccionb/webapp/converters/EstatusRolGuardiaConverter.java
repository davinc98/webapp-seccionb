package org.seccionb.webapp.converters;


import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.seccionb.webapp.entities.EstatusCajon;
import org.seccionb.webapp.entities.EstatusRolGuardia;
import org.seccionb.webapp.services.CajonEstacionamientoService;
import org.seccionb.webapp.services.GuardiasService;

import java.util.Optional;

@RequestScoped
@Named("estatusRolGuardiaConverter")
public class EstatusRolGuardiaConverter implements Converter<EstatusRolGuardia> {

    @Inject
    private GuardiasService service;

    @Override
    public EstatusRolGuardia getAsObject(FacesContext context, UIComponent component, String id) {
        if(id==null)
            return null;

        Optional<EstatusRolGuardia> estatusRolGuardia = service.porIdEstatusRolDeGuardia(Long.valueOf(id));
        if(estatusRolGuardia.isPresent())
            return estatusRolGuardia.get();
        else
            return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, EstatusRolGuardia estatusRolGuardia) {
        if(estatusRolGuardia==null)
            return "0";
        return estatusRolGuardia.getId().toString();
    }
}
