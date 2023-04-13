package org.seccionb.webapp.repositories.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.seccionb.webapp.entities.EstatusPorton;
import org.seccionb.webapp.entities.SolicitudIngreso;
import org.seccionb.webapp.repositories.SolicitudIngresoRepository;

import java.util.List;


@RequestScoped
public class SolicitudIngresoRepositoryImpl implements SolicitudIngresoRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<SolicitudIngreso> listar() {
        return em.createQuery("SELECT si FROM SolicitudIngreso si " +
                "LEFT OUTER JOIN FETCH si.vehiculo " +
                "ORDER BY si.id DESC", SolicitudIngreso.class).getResultList();
    }

    @Override
    public SolicitudIngreso porId(Long id) {
        return em.createQuery("SELECT si FROM SolicitudIngreso si " +
                        "LEFT OUTER JOIN FETCH si.vehiculo " +
                        "WHERE si.id=:id", SolicitudIngreso.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void guardar(SolicitudIngreso solicitudIngreso) {
        if (solicitudIngreso.getId() != null && solicitudIngreso.getId() > 0) {
            em.merge(solicitudIngreso);
        } else {
            em.persist(solicitudIngreso);
        }
    }

    @Override
    public void eliminar(Long id) {
        SolicitudIngreso solicitudIngreso = porId(id);
        em.remove(solicitudIngreso);
    }

    @Override
    public EstatusPorton getEstatusPorton() {
        return em.createQuery("SELECT ep FROM EstatusPorton ep", EstatusPorton.class).getSingleResult();
    }

    @Override
    public List<SolicitudIngreso> listarReconocido() {
        return em.createQuery("SELECT si FROM SolicitudIngreso si " +
                "LEFT OUTER JOIN FETCH si.vehiculo WHERE si.tipo=:tipo " +
                "ORDER BY si.id DESC", SolicitudIngreso.class)
                .setParameter("tipo", 1)
                .getResultList();
    }

    @Override
    public List<SolicitudIngreso> listarDesconocido() {
        return em.createQuery("SELECT si FROM SolicitudIngreso si " +
                        "LEFT OUTER JOIN FETCH si.vehiculo WHERE si.tipo=:tipo " +
                        "ORDER BY si.id DESC", SolicitudIngreso.class)
                .setParameter("tipo", 2)
                .getResultList();
    }
}
