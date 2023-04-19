package org.seccionb.webapp.entities;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cat_situacion_escolar")
public class SituacionEscolar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "situacion_escolar_id")
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    public SituacionEscolar() {
    }

    public SituacionEscolar(Long id, String descripcion) {
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
        return "SituacionEscolar{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SituacionEscolar that = (SituacionEscolar) o;
        return Objects.equals(id, that.id);
    }

}
