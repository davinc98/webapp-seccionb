package org.seccionb.webapp.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cat_carrera")
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_carrera_id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    public Carrera() {
    }

    public Carrera(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrera carrera = (Carrera) o;
        return Objects.equals(id, carrera.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
