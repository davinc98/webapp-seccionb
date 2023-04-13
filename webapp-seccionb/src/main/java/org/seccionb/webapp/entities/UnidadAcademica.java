package org.seccionb.webapp.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "unidades")
public class UnidadAcademica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unidad_id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "escuela_id")
    private Long escuelaId;

    public UnidadAcademica() {
    }

    public UnidadAcademica(Long id, String nombre, Long escuelaId) {
        this.id = id;
        this.nombre = nombre;
        this.escuelaId = escuelaId;
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

    public Long getEscuelaId() {
        return escuelaId;
    }

    public void setEscuelaId(Long escuelaId) {
        this.escuelaId = escuelaId;
    }

    @Override
    public String toString() {
        return "UnidadAcademica{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", escuelaId=" + escuelaId +
                '}';
    }
}
