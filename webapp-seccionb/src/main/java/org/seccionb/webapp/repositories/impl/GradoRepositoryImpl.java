package org.seccionb.webapp.repositories.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.seccionb.webapp.entities.Cargo;
import org.seccionb.webapp.entities.Grado;
import org.seccionb.webapp.repositories.CargoRepository;
import org.seccionb.webapp.repositories.GradoRepository;

import java.util.List;

@RequestScoped
public class GradoRepositoryImpl implements GradoRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<Grado> listar() {
        return em.createQuery("SELECT g FROM Grado g", Grado.class).getResultList();
    }

    @Override
    public Grado porId(Long id) {
        return em.createQuery("SELECT c FROM Grado c WHERE c.id=:id", Grado.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void guardar(Grado grado) {
        if(grado.getId()!=null && grado.getId()>0){
            em.merge(grado);
        }else{
            em.persist(grado);
        }
    }

    @Override
    public void eliminar(Long id) {
        Grado grado = porId(id);
        em.remove(grado);
    }
}
