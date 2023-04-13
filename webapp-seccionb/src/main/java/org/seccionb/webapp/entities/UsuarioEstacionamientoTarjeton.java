package org.seccionb.webapp.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "usuario_estaciomaniento_tarjeton")
public class UsuarioEstacionamientoTarjeton {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_estaciomaniento_tarjeton_id")
    private Long id;

    @Column(name = "usuario_estacionamiento_id")
    private Long usuarioEstacionamientoId;

    @Column(name = "tipo_tarjeton_id")
    private Long tipoTarjetonId;

    @Column(name = "fecha_actualizado")
    private Date fechaActualizacion;

    public UsuarioEstacionamientoTarjeton() {
    }

    public UsuarioEstacionamientoTarjeton(Long id, Long usuarioEstacionamientoId, Long tipoTarjetonId, Date fechaActualizacion) {
        this.id = id;
        this.usuarioEstacionamientoId = usuarioEstacionamientoId;
        this.tipoTarjetonId = tipoTarjetonId;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioEstacionamientoId() {
        return usuarioEstacionamientoId;
    }

    public void setUsuarioEstacionamientoId(Long usuarioEstacionamientoId) {
        this.usuarioEstacionamientoId = usuarioEstacionamientoId;
    }

    public Long getTipoTarjetonId() {
        return tipoTarjetonId;
    }

    public void setTipoTarjetonId(Long tipoTarjetonId) {
        this.tipoTarjetonId = tipoTarjetonId;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @Override
    public String toString() {
        return "UsuarioEstacionamientoTarjeton{" +
                "id=" + id +
                ", usuarioEstacionamientoId=" + usuarioEstacionamientoId +
                ", tipoTarjetonId=" + tipoTarjetonId +
                ", fechaActualizacion=" + fechaActualizacion +
                '}';
    }
}

