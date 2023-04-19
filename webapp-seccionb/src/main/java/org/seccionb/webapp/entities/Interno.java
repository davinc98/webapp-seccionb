package org.seccionb.webapp.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "internos")
public class Interno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interno_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @OneToOne
    @JoinColumn(name = "escuela_id")
    private Escuela escuela;

    @OneToOne
    @JoinColumn(name = "carrera_id")
    private Carrera carrera;

    @OneToOne
    @JoinColumn(name = "estado_procedencia_id")
    private EstadoProcedencia estadoProcedencia;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @OneToOne
    @JoinColumn(name = "situacion_escolar_id")
    private SituacionEscolar situacionEscolar;

    @OneToOne
    @JoinColumn(name = "estatus_id")
    private EstatusInterno estatusEstudiante;

    public Interno() {
    }

    public Interno(Long id, Persona persona, Escuela escuela, Carrera carrera, EstadoProcedencia estadoProcedencia, LocalDate fechaIngreso, SituacionEscolar situacionEscolar, EstatusInterno estatusEstudiante, String usuario, String contrasenia) {
        this.id = id;
        this.persona = persona;
        this.escuela = escuela;
        this.carrera = carrera;
        this.estadoProcedencia = estadoProcedencia;
        this.fechaIngreso = fechaIngreso;
        this.situacionEscolar = situacionEscolar;
        this.estatusEstudiante = estatusEstudiante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Escuela getEscuela() {
        return escuela;
    }

    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public EstadoProcedencia getEstadoProcedencia() {
        return estadoProcedencia;
    }

    public void setEstadoProcedencia(EstadoProcedencia estadoProcedencia) {
        this.estadoProcedencia = estadoProcedencia;
    }

    public SituacionEscolar getSituacionEscolar() {
        return situacionEscolar;
    }

    public void setSituacionEscolar(SituacionEscolar situacionEscolar) {
        this.situacionEscolar = situacionEscolar;
    }

    public EstatusInterno getEstatusEstudiante() {
        return estatusEstudiante;
    }

    public void setEstatusEstudiante(EstatusInterno estatusEstudiante) {
        this.estatusEstudiante = estatusEstudiante;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interno that = (Interno) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", fechaIngreso=" + fechaIngreso +
                ", estatus='" + estatusEstudiante + '\'' +
                '}';
    }


}
