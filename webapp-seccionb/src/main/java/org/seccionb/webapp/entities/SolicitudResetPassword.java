package org.seccionb.webapp.entities;


import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "solicitud_reset_password")
public class SolicitudResetPassword implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solicitud_id")
    private Long id;

    @Column(name = "persona_id")
    private Long personaId;

    @Column(name = "correo")
    private String correo;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "estatus")
    private String estatus;

    @Column(name = "fecha")
    private LocalDate fecha;


    public SolicitudResetPassword() {
    }

    public SolicitudResetPassword(Long id, Long personaId, String correo, String codigo, String estatus, LocalDate fecha) {
        this.id = id;
        this.personaId = personaId;
        this.correo = correo;
        this.codigo = codigo;
        this.estatus = estatus;
        this.fecha = fecha;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "SolicitudResetPassword{" +
                "id=" + id +
                ", personaId=" + personaId +
                ", correo='" + correo + '\'' +
                ", codigo='" + codigo + '\'' +
                ", estatus='" + estatus + '\'' +
                ", fecha=" + fecha +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolicitudResetPassword that = (SolicitudResetPassword) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
