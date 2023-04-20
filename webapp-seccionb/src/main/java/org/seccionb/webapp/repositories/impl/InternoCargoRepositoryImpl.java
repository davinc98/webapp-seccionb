package org.seccionb.webapp.repositories.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.seccionb.webapp.entities.*;
import org.seccionb.webapp.repositories.InternoCargoRepository;

import java.util.List;

@RequestScoped
public class InternoCargoRepositoryImpl implements InternoCargoRepository {

    @Inject
    private EntityManager em;


    @Override
    public List<InternoCargo> listar() {
        return em.createQuery("SELECT ec FROM InternoCargo ec", InternoCargo.class).getResultList();
    }

    @Override
    public InternoCargo porId(Long id) {
        return em.createQuery("SELECT ec FROM InternoCargo ec"+
                        " WHERE ec.id=:id", InternoCargo.class)
                .setParameter("id", id)
                .getSingleResult();
    }



    @Override
    public void guardar(InternoCargo estudianteCargo) {
        if(estudianteCargo.getId()!=null && estudianteCargo.getId()>0){
            em.merge(estudianteCargo);
        }else{
            em.persist(estudianteCargo);
        }
    }

    @Override
    public void eliminar(Long id) {
        InternoCargo estudianteCargo = porId(id);
        em.remove(estudianteCargo);
    }

    @Override
    public List<InternoCargo> getCargosPorIdEstudiante(Long id) {
        return em.createQuery("SELECT ec FROM InternoCargo ec"+
                        " WHERE ec.internoId=:id", InternoCargo.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<InternoCargo> getEstudiantesCargoPorCurp(String curp) {
        return null;
    }

}
