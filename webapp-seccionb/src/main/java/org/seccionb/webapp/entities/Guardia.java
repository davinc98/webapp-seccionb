package org.seccionb.webapp.entities;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "guardias")
public class Guardia implements Serializable {

    @Id
    @Column(name = "guardia_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "rol_guardia_id")
    private RolDeGuardias rolDeGuardias;

    @OneToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @Column(name = "dia")
    private Long dia;

    @Column(name = "horario")
    private String horario;

    @OneToOne
    @JoinColumn(name = "tipo_guardia_id")
    private TipoGuardia tipoGuardia;

    public Guardia() {
    }

    public Guardia(Long id, RolDeGuardias rolDeGuardias, Persona persona, Long dia, String horario, TipoGuardia tipoGuardia) {
        this.id = id;
        this.rolDeGuardias = rolDeGuardias;
        this.persona = persona;
        this.dia = dia;
        this.horario = horario;
        this.tipoGuardia = tipoGuardia;
    }

    public RolDeGuardias getRolDeGuardias() {
        return rolDeGuardias;
    }

    public void setRolDeGuardias(RolDeGuardias rolDeGuardias) {
        this.rolDeGuardias = rolDeGuardias;
    }

    public Long getDia() {
        return dia;
    }

    public void setDia(Long dia) {
        this.dia = dia;
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

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public TipoGuardia getTipoGuardia() {
        return tipoGuardia;
    }

    public void setTipoGuardia(TipoGuardia tipoGuardia) {
        this.tipoGuardia = tipoGuardia;
    }

    @Override
    public String toString() {
        return "Guardia{" +
                "id=" + id +
                ", rolDeGuardias=" + rolDeGuardias +
                ", persona=" + persona +
                ", dia=" + dia +
                ", horario='" + horario + '\'' +
                ", tipoGuardia=" + tipoGuardia +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guardia contacto = (Guardia) o;
        return id.equals(contacto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
