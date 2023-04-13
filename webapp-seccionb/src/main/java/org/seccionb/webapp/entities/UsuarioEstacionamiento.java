package org.seccionb.webapp.entities;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "usuario_estacionamiento")
public class UsuarioEstacionamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_estacionamiento_id")
    private Long id;

    @Column(name = "persona_id")
    private Long personaId;

    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;

    @Column(name = "estatus_id")
    private Long estatusUsuarioEstacionamientoId;

    public UsuarioEstacionamiento() {
    }

    public UsuarioEstacionamiento(Long id, Long personaId, Date fechaIngreso, Long estatusUsuarioEstacionamientoId) {
        this.id = id;
        this.personaId = personaId;
        this.fechaIngreso = fechaIngreso;
        this.estatusUsuarioEstacionamientoId = estatusUsuarioEstacionamientoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Long getEstatusUsuarioEstacionamientoId() {
        return estatusUsuarioEstacionamientoId;
    }

    public void setEstatusUsuarioEstacionamientoId(Long estatusUsuarioEstacionamientoId) {
        this.estatusUsuarioEstacionamientoId = estatusUsuarioEstacionamientoId;
    }

    @Override
    public String toString() {
        return "UsuarioEstacionamiento{" +
                "id=" + id +
                ", personaId=" + personaId +
                ", fechaIngreso=" + fechaIngreso +
                ", estatusUsuarioEstacionamientoId=" + estatusUsuarioEstacionamientoId +
                '}';
    }
}
