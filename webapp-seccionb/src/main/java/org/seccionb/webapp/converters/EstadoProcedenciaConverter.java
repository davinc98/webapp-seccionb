package org.seccionb.webapp.converters;


import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.seccionb.webapp.entities.Escuela;
import org.seccionb.webapp.entities.EstadoProcedencia;
import org.seccionb.webapp.services.EstudianteService;

import java.util.Optional;

@RequestScoped
@Named("estadoProcedenciaConverter")
public class EstadoProcedenciaConverter implements Converter<EstadoProcedencia> {

    @Inject
    private EstudianteService service;

    @Override
    public EstadoProcedencia getAsObject(FacesContext context, UIComponent component, String id) {
        if(id==null)
            return null;

        Optional<EstadoProcedencia> estadoProcedencia = service.porIdEstadoProcedencia(Long.valueOf(id));
        if(estadoProcedencia.isPresent())
            return estadoProcedencia.get();
        else
            return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, EstadoProcedencia estadoProcedencia) {
        if(estadoProcedencia==null)
            return "0";
        return estadoProcedencia.getId().toString();
    }
}
