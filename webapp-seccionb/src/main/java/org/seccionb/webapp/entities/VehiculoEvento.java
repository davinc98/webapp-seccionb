package org.seccionb.webapp.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "vehiculos_eventos")
public class VehiculoEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehiculo_evento_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @OneToOne
    @JoinColumn(name = "evento_id")
    private TipoEvento tipoEvento;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    public VehiculoEvento() {
    }


    public VehiculoEvento(Long id, Vehiculo vehiculo, TipoEvento tipoEvento, LocalDateTime fecha) {
        this.id = id;
        this.vehiculo = vehiculo;
        this.tipoEvento = tipoEvento;
        this.fecha = fecha;
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

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "VehiculoEvento{" +
                "id=" + id +
                ", tipoEvento=" + tipoEvento +
                ", fecha=" + fecha +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehiculoEvento that = (VehiculoEvento) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
