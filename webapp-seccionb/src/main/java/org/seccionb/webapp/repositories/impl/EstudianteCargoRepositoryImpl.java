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
    public List<EstudianteCargo> listar() {
        return em.createQuery("SELECT ec FROM EstudianteCargo ec", EstudianteCargo.class).getResultList();
    }

    @Override
    public EstudianteCargo porId(Long id) {
        return em.createQuery("SELECT ec FROM EstudianteCargo ec"+
                        " WHERE ec.id=:id", EstudianteCargo.class)
                .setParameter("id", id)
                .getSingleResult();
    }



    @Override
    public void guardar(EstudianteCargo estudianteCargo) {
        if(estudianteCargo.getId()!=null && estudianteCargo.getId()>0){
            em.merge(estudianteCargo);
        }else{
            em.persist(estudianteCargo);
        }
    }

    @Override
    public void eliminar(Long id) {
        EstudianteCargo estudianteCargo = porId(id);
        em.remove(estudianteCargo);
    }

    @Override
    public List<EstudianteCargo> getCargosPorIdEstudiante(Long id) {
        return em.createQuery("SELECT ec FROM EstudianteCargo ec"+
                        " WHERE ec.estudianteId=:id", EstudianteCargo.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<EstudianteCargo> getEstudiantesCargoPorCurp(String curp) {
        return null;
    }

}
