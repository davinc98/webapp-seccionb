package org.seccionb.webapp.repositories.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.seccionb.webapp.entities.Cargo;
import org.seccionb.webapp.repositories.CargoRepository;

import java.util.List;

@RequestScoped
public class CargoRepositoryImpl implements CargoRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<Cargo> listar() {
        return em.createQuery("SELECT c FROM Cargo c", Cargo.class).getResultList();
    }

    @Override
    public Cargo porId(Long id) {
        return em.createQuery("SELECT c FROM Cargo c WHERE c.id=:id", Cargo.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void guardar(Cargo cargo) {
        if(cargo.getId()!=null && cargo.getId()>0){
            em.merge(cargo);
        }else{
            em.persist(cargo);
        }
    }

    @Override
    public void eliminar(Long id) {
        Cargo cargo = porId(id);
        em.remove(cargo);
    }
}
