package org.seccionb.webapp.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "vehiculos")
public class Vehiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehiculo_id")
    private Long id;

    @Column(name = "persona_id")
    private Long personaId;

    @OneToOne
    @JoinColumn(name = "cajon_estacionamiento_id")
    private CajonEstacionamiento cajonEstacionamiento;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "anio")
    private String anio;

    @Column(name = "color")
    private String color;

    @Column(name = "matricula")
    private String matricula;

    @OneToOne
    @JoinColumn(name = "estado_procedencia_id")
    private EstadoProcedencia estadoProcedencia;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToOne
    @JoinColumn(name = "cat_estatus_vehiculo_id")
    private EstatusVehiculo estatusVehiculo;

    @OneToOne
    @JoinColumn(name = "cat_tipo_tarjeton_id")
    private TipoTarjeton tipoTarjeton;

    public Vehiculo() {
    }

    public Vehiculo(Long id, Long personaId, CajonEstacionamiento cajonEstacionamiento, String marca, String modelo, String anio, String color, String matricula, EstadoProcedencia estadoProcedencia, String descripcion, EstatusVehiculo estatusVehiculo, TipoTarjeton tipoTarjeton) {
        this.id = id;
        this.personaId = personaId;
        this.cajonEstacionamiento = cajonEstacionamiento;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.color = color;
        this.matricula = matricula;
        this.estadoProcedencia = estadoProcedencia;
        this.descripcion = descripcion;
        this.estatusVehiculo = estatusVehiculo;
        this.tipoTarjeton = tipoTarjeton;
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

    public CajonEstacionamiento getCajonEstacionamiento() {
        return cajonEstacionamiento;
    }

    public void setCajonEstacionamiento(CajonEstacionamiento cajonEstacionamiento) {
        this.cajonEstacionamiento = cajonEstacionamiento;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public EstadoProcedencia getEstadoProcedencia() {
        return estadoProcedencia;
    }

    public void setEstadoProcedencia(EstadoProcedencia estadoProcedencia) {
        this.estadoProcedencia = estadoProcedencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public EstatusVehiculo getEstatusVehiculo() {
        return estatusVehiculo;
    }

    public void setEstatusVehiculo(EstatusVehiculo estatusVehiculo) {
        this.estatusVehiculo = estatusVehiculo;
    }

    public TipoTarjeton getTipoTarjeton() {
        return tipoTarjeton;
    }

    public void setTipoTarjeton(TipoTarjeton tipoTarjeton) {
        this.tipoTarjeton = tipoTarjeton;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", personaId="+personaId+
                ", cajonEstacionamiento=" + cajonEstacionamiento +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anio='" + anio + '\'' +
                ", color='" + color + '\'' +
                ", matricula='" + matricula + '\'' +
                ", estadoProcedenciaId=" + estadoProcedencia +
                ", descripcion='" + descripcion + '\'' +
                ", estatusVehiculo=" + estatusVehiculo +
                ", tipoTarjeton="+tipoTarjeton+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return id.equals(vehiculo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

