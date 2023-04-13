package org.seccionb.webapp.repositories.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.seccionb.webapp.entities.AreaEstacionamiento;
import org.seccionb.webapp.entities.CajonEstacionamiento;
import org.seccionb.webapp.entities.Cargo;
import org.seccionb.webapp.entities.EstatusCajon;
import org.seccionb.webapp.repositories.CajonEstacionamientoRepository;
import org.seccionb.webapp.repositories.CargoRepository;

import java.util.List;

@RequestScoped
public class CajonEstacionamientoRepositoryImpl implements CajonEstacionamientoRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<CajonEstacionamiento> listar() {
        return em.createQuery("SELECT ce FROM CajonEstacionamiento ce ORDER BY ce.id DESC", CajonEstacionamiento.class).getResultList();
    }

    @Override
    public CajonEstacionamiento porId(Long id) {
        return em.createQuery("SELECT ce FROM CajonEstacionamiento ce WHERE ce.id=:id", CajonEstacionamiento.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void guardar(CajonEstacionamiento cajonEstacionamiento) {
        if(cajonEstacionamiento.getId()!=null && cajonEstacionamiento.getId()>0){
            em.merge(cajonEstacionamiento);
        }else{
            em.persist(cajonEstacionamiento);
        }
    }

    @Override
    public void eliminar(Long id) {
        CajonEstacionamiento cajonEstacionamiento = porId(id);
        em.remove(cajonEstacionamiento);
    }

    @Override
    public List<EstatusCajon> estatusCajon() {
        return em.createQuery("SELECT ec FROM EstatusCajon ec", EstatusCajon.class)
                .getResultList();
    }

    @Override
    public EstatusCajon porIdEstatusCajon(Long id) {
        return em.find(EstatusCajon.class, id);
    }

    @Override
    public List<AreaEstacionamiento> areasEstacionamiento() {
        return em.createQuery("SELECT ae FROM AreaEstacionamiento ae", AreaEstacionamiento.class)
                .getResultList();
    }

    @Override
    public AreaEstacionamiento porIdAreaEstacionamiento(Long id) {
        return em.find(AreaEstacionamiento.class, id);
    }
}
