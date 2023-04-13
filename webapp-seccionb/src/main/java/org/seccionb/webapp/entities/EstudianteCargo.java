package org.seccionb.webapp.entities;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "estudiantes_cargos")
public class EstudianteCargo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estudiante_cargo_id")
    private Long id;

    @Column(name = "estudiante_id")
    private Long estudianteId;

    @Column(name = "cargo_id")
    private Long cargoId;

    @Column(name = "fecha_asignacion")
    private Date fechaAsignacion;

    public EstudianteCargo() {
    }

    public EstudianteCargo(Long id, Long estudianteId, Long cargoId, Date fechaAsignacion) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.cargoId = cargoId;
        this.fechaAsignacion = fechaAsignacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Long getCargoId() {
        return cargoId;
    }

    public void setCargoId(Long cargoId) {
        this.cargoId = cargoId;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    @Override
    public String toString() {
        return "EstudianteCargo{" +
                "estudianteId=" + estudianteId +
                ", cargoId=" + cargoId +
                ", fechaAsignacion=" + fechaAsignacion +
                '}';
    }
}
