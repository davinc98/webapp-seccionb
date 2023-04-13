package org.seccionb.webapp.model;

import java.io.Serializable;
import java.util.List;

public class SolicitudIngresoDTO implements Serializable {

    private String ipRaspBerry;
    private String fecha;
    private String hora;

    private String fotoMatricula;
    private List<String> matriculas;

    public SolicitudIngresoDTO() {
    }

    public SolicitudIngresoDTO(String ipRaspBerry, String fecha, String hora, String fotoMatricula, List<String> matriculas) {
        this.ipRaspBerry = ipRaspBerry;
        this.fecha = fecha;
        this.hora = hora;
        this.fotoMatricula = fotoMatricula;
        this.matriculas = matriculas;
    }

    public String getIpRaspBerry() {
        return ipRaspBerry;
    }

    public void setIpRaspBerry(String ipRaspBerry) {
        this.ipRaspBerry = ipRaspBerry;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFotoMatricula() {
        return fotoMatricula;
    }

    public void setFotoMatricula(String fotoMatricula) {
        this.fotoMatricula = fotoMatricula;
    }

    public List<String> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<String> matriculas) {
        this.matriculas = matriculas;
    }

    @Override
    public String toString() {
        return "{" +
                "ipRaspBerry='" + ipRaspBerry + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", fotoMatricula='" + fotoMatricula + '\'' +
                ", matriculas=" + matriculas +
                '}';
    }
}
