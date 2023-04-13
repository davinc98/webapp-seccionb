package org.seccionb.webapp.entities;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "escuelas")
public class Escuela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="escuela_id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    public Escuela() {
    }

    public Escuela(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Escuela(String nombre) {
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
        return "Escuela{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Escuela escuela = (Escuela) o;
        return Objects.equals(id, escuela.id);
    }

}
