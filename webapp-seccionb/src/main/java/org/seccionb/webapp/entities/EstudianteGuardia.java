package org.seccionb.webapp.entities;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "estudiantes_guardias")
public class EstudianteGuardia {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guardia_id")
    private Long id;

    @Column(name = "horario")
    private String horario;

    @Column(name = "fecha_guardia")
    private Date fechaGuardia;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "rol_guardia_id")
    private Long rolDeGuardiasId;

    @Column(name = "estudiante_id")
    private Long estudianteId;

    public EstudianteGuardia() {
    }

    public EstudianteGuardia(Long id, String horario, Date fechaGuardia, String tipo, Long rolDeGuardiasId, Long estudianteId) {
        this.id = id;
        this.horario = horario;
        this.fechaGuardia = fechaGuardia;
        this.tipo = tipo;
        this.rolDeGuardiasId = rolDeGuardiasId;
        this.estudianteId = estudianteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Date getFechaGuardia() {
        return fechaGuardia;
    }

    public void setFechaGuardia(Date fechaGuardia) {
        this.fechaGuardia = fechaGuardia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getRolDeGuardiasId() {
        return rolDeGuardiasId;
    }

    public void setRolDeGuardiasId(Long rolDeGuardiasId) {
        this.rolDeGuardiasId = rolDeGuardiasId;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    @Override
    public String toString() {
        return "EstudianteGuardia{" +
                "id=" + id +
                ", horario='" + horario + '\'' +
                ", fechaGuardia=" + fechaGuardia +
                ", tipo='" + tipo + '\'' +
                ", rolDeGuardiasId=" + rolDeGuardiasId +
                ", estudianteId=" + estudianteId +
                '}';
    }
}
