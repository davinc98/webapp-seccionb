package org.seccionb.webapp.entities;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "contactos")
public class Contacto implements Serializable {

    @Id
    @Column(name = "contacto_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "persona_id")
    private Long personaId;

    @OneToOne
    @JoinColumn(name = "cat_tipo_contacto_id")
    private TipoContacto tipoContacto;

    @Column(name = "contacto")
    private String contacto;

    public Contacto() {
    }

    public Contacto(Long id, Long personaId, TipoContacto tipoContacto, String contacto) {
        this.id = id;
        this.personaId = personaId;
        this.tipoContacto = tipoContacto;
        this.contacto = contacto;
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

    public TipoContacto getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(TipoContacto tipoContacto) {
        this.tipoContacto = tipoContacto;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "personaId=" + personaId +
                ", tipoContactoId=" + tipoContacto +
                ", contacto='" + contacto + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacto contacto = (Contacto) o;
        return id.equals(contacto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
