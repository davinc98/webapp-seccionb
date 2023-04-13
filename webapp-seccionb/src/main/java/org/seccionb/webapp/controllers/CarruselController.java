package org.seccionb.webapp.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Model
@Named("carruselController")
public class CarruselController implements Serializable {

    private List<String> imagenesInicio;

    public CarruselController(){}

    @PostConstruct
    public void init(){
        imagenesInicio = new ArrayList<>();
        imagenesInicio.add("inicioDirectivo.PNG");
        imagenesInicio.add("inicioEstacionamiento.PNG");
        imagenesInicio.add("inicioDetall.PNG");
        imagenesInicio.add("inicio1.PNG");
    }

    public List<String> getImagenesInicio() {
        return imagenesInicio;
    }

    public void setImagenesInicio(List<String> imagenesInicio) {
        this.imagenesInicio = imagenesInicio;
    }

}
