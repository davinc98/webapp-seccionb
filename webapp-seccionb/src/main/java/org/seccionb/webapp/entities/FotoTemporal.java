package org.seccionb.webapp.entities;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "foto_tmp")
public class FotoTemporal implements Serializable {

    @Id
    @Column(name = "foto_tmp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "foto")
    private String fotoBase64;

    @Column(name = "persona_id")
    private Long personaId;

    public FotoTemporal() {
    }

    public FotoTemporal(String fotoBase64, Long personaId) {
        this.fotoBase64 = fotoBase64;
        this.personaId = personaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFotoBase64() {
        return fotoBase64;
    }

    public void setFotoBase64(String fotoBase64) {
        this.fotoBase64 = fotoBase64;
    }

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    @Override
    public String toString() {
        return "FotoTemporal{" +
                "id=" + id +
                ", fotoBase64=" + fotoBase64 +
                ", personaId=" + personaId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FotoTemporal that = (FotoTemporal) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
