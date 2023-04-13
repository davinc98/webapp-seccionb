package org.seccionb.webapp.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cajon_estacionamiento")
public class CajonEstacionamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cajon_estacionamiento_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "cat_estatus_cajon_id")
    private EstatusCajon estatusCajon;

    @OneToOne
    @JoinColumn(name = "cat_area_id")
    private AreaEstacionamiento areaEstacionamiento;

    @Column(name = "numero")
    private String numero;

    public CajonEstacionamiento() {
    }

    public CajonEstacionamiento(Long id, EstatusCajon estatusCajon, AreaEstacionamiento areaEstacionamiento, String numero) {
        this.id = id;
        this.estatusCajon = estatusCajon;
        this.areaEstacionamiento = areaEstacionamiento;
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstatusCajon getEstatusCajon() {
        return estatusCajon;
    }

    public void setEstatusCajon(EstatusCajon estatusCajon) {
        this.estatusCajon = estatusCajon;
    }

    public AreaEstacionamiento getAreaEstacionamiento() {
        return areaEstacionamiento;
    }

    public void setAreaEstacionamiento(AreaEstacionamiento areaEstacionamiento) {
        this.areaEstacionamiento = areaEstacionamiento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "CajonEstacionamiento{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CajonEstacionamiento that = (CajonEstacionamiento) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
