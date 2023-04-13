package org.seccionb.webapp.model;

import java.io.Serializable;
import java.util.Objects;

public class EstatusPortonDTO implements Serializable {

    public enum ESTATUS_PORTON {
        ABRIENDO, ABIERTO, CERRANDO, CERRADO, PAUSADO, PAUSANDO
    }
    private ESTATUS_PORTON estatus;

    public EstatusPortonDTO() {
    }

    public EstatusPortonDTO(ESTATUS_PORTON estatus) {
        this.estatus = estatus;
    }

    public ESTATUS_PORTON getEstatus() {
        return estatus;
    }

    public void setEstatus(ESTATUS_PORTON estatus) {
        this.estatus = estatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstatusPortonDTO that = (EstatusPortonDTO) o;
        return estatus.equals(that.estatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estatus);
    }
}
