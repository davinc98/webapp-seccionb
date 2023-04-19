package org.seccionb.webapp.converters;


import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.seccionb.webapp.entities.EstatusCajon;
import org.seccionb.webapp.entities.EstatusInterno;
import org.seccionb.webapp.services.CajonEstacionamientoService;
import org.seccionb.webapp.services.EstudianteService;

import java.util.Optional;

@RequestScoped
@Named("estatusEstudianteConverter")
public class EstatusEstudianteConverter implements Converter<EstatusInterno> {

    @Inject
    private EstudianteService service;

    @Override
    public EstatusInterno getAsObject(FacesContext context, UIComponent component, String id) {
        if(id==null)
            return null;

        Optional<EstatusInterno> estatusEstudiante = service.porIdEstatusEstudiante(Long.valueOf(id));
        if(estatusEstudiante.isPresent())
            return estatusEstudiante.get();
        else
            return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, EstatusInterno estatusEstudiante) {
        if(estatusEstudiante==null)
            return "0";
        return estatusEstudiante.getId().toString();
    }
}
