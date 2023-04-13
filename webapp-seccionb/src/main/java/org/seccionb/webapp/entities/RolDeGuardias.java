package org.seccionb.webapp.entities;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "rol_de_guardias")
public class RolDeGuardias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_guardia_id")
    private Long id;

    @Column(name = "anio")
    private String anio;

    @Column(name = "mes")
    private String mes;

    @OneToOne
    @JoinColumn(name = "estatus_id")
    private EstatusRolGuardia estatus;

    public RolDeGuardias() {
    }

    public RolDeGuardias(Long id, String anio, String mes, EstatusRolGuardia estatus) {
        this.id = id;
        this.anio = anio;
        this.mes = mes;
        this.estatus = estatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public EstatusRolGuardia getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusRolGuardia estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "RolDeGuardias{" +
                "id=" + id +
                ", anio='" + anio + '\'' +
                ", mes='" + mes + '\'' +
                ", estatus='" + estatus + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolDeGuardias that = (RolDeGuardias) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
