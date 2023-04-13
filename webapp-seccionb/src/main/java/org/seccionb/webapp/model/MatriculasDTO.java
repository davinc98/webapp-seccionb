package org.seccionb.webapp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class MatriculasDTO implements Serializable {

    private String ipRaspBerry;
    private String fotoPlaca;
    private List<String> matriculas;

    public MatriculasDTO() {
    }

    public MatriculasDTO(String ipRaspBerry, String fotoPlaca, List<String> matriculas) {
        this.ipRaspBerry = ipRaspBerry;
        this.fotoPlaca = fotoPlaca;
        this.matriculas = matriculas;
    }

    public String getIpRaspBerry() {
        return ipRaspBerry;
    }

    public void setIpRaspBerry(String ipRaspBerry) {
        this.ipRaspBerry = ipRaspBerry;
    }

    public String getFotoPlaca() {
        return fotoPlaca;
    }

    public void setFotoPlaca(String fotoPlaca) {
        this.fotoPlaca = fotoPlaca;
    }

    public List<String> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<String> matriculas) {
        this.matriculas = matriculas;
    }

    @Override
    public String toString() {
        return "MatriculasDTO{" +
                "ipRaspBerry='" + ipRaspBerry + '\'' +
                ", fotoPlaca='" + fotoPlaca + '\'' +
                ", matriculas=" + matriculas +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatriculasDTO that = (MatriculasDTO) o;
        return ipRaspBerry.equals(that.ipRaspBerry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipRaspBerry);
    }
}
