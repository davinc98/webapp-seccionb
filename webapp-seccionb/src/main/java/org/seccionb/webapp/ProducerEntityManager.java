package org.seccionb.webapp;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class ProducerEntityManager {

    @PersistenceContext(name = "conexionJpa")
    private EntityManager em;

    @Produces
    @RequestScoped
    private EntityManager beanEntityManager(){
        return em;
    }

}
