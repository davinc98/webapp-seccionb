package org.seccionb.webapp.entities;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "solicitudes_ingreso")
public class SolicitudIngreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solicitud_ingreso_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @Column(name = "fotoPlaca")
    private String fotoPlaca;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "estatus")
    private Integer estatus;

    public SolicitudIngreso() {
    }

    public SolicitudIngreso(Long id, Vehiculo vehiculo, String fotoPlaca, Integer tipo, Integer estatus) {
        this.id = id;
        this.vehiculo = vehiculo;
        this.fotoPlaca = fotoPlaca;
        this.tipo = tipo;
        this.estatus = estatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public String getFotoPlaca() {
        return fotoPlaca;
    }

    public void setFotoPlaca(String fotoPlaca) {
        this.fotoPlaca = fotoPlaca;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "SolicitudIngreso{" +
                "id=" + id +
                ", vehiculo=" + vehiculo +
                ", fotoPlaca=...."+
                ", tipo=" + tipo +
                ", estatus=" + estatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolicitudIngreso that = (SolicitudIngreso) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
