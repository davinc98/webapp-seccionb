package org.seccionb.webapp.repositories.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.seccionb.webapp.entities.EstatusPorton;
import org.seccionb.webapp.entities.SolicitudIngreso;
import org.seccionb.webapp.entities.SolicitudResetPassword;
import org.seccionb.webapp.repositories.SolicitudIngresoRepository;
import org.seccionb.webapp.repositories.SolicitudResetPasswordRepository;

import java.util.List;


@RequestScoped
public class SolicitudResetPasswordRepositoryImpl implements SolicitudResetPasswordRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<SolicitudResetPassword> listar() {
        return em.createQuery("SELECT srp FROM SolicitudResetPassword srp " +
                "ORDER BY srp.id DESC", SolicitudResetPassword.class).getResultList();
    }

    @Override
    public SolicitudResetPassword porId(Long id) {
        return em.createQuery("SELECT srp FROM SolicitudResetPassword srp " +
                        "WHERE srp.id=:id", SolicitudResetPassword.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void guardar(SolicitudResetPassword solicitudResetPassword) {
        if (solicitudResetPassword.getId() != null && solicitudResetPassword.getId() > 0) {
            em.merge(solicitudResetPassword);
        } else {
            em.persist(solicitudResetPassword);
        }
    }

    @Override
    public void eliminar(Long id) {
        SolicitudResetPassword solicitudResetPassword = porId(id);
        em.remove(solicitudResetPassword);
    }

}
