package org.seccionb.webapp.repositories.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.seccionb.webapp.entities.*;
import org.seccionb.webapp.repositories.EstudianteCargoRepository;
import org.seccionb.webapp.repositories.EstudianteRepository;

import java.util.List;

@RequestScoped
public class EstudianteCargoRepositoryImpl implements EstudianteCargoRepository {

    @Inject
    private EntityManager em;


    @Override
    public List<InternoCargo> listar() {
        return em.createQuery("SELECT ec FROM EstudianteCargo ec", InternoCargo.class).getResultList();
    }

    @Override
    public InternoCargo porId(Long id) {
        return em.createQuery("SELECT ec FROM EstudianteCargo ec"+
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
        return em.createQuery("SELECT ec FROM EstudianteCargo ec"+
                        " WHERE ec.estudianteId=:id", InternoCargo.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<InternoCargo> getEstudiantesCargoPorCurp(String curp) {
        return null;
    }

}
