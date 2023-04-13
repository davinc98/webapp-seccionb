package org.seccionb.webapp.entities;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlIDREF;

@Entity
@Table(name = "cat_estatus_usuario_estacionamiento")
public class EstatusUsuarioEstacionamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_estatus_usuario_estacionamiento_id")
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    public EstatusUsuarioEstacionamiento() {
    }

    public EstatusUsuarioEstacionamiento(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "EstatusUsuarioEstacionamiento{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
