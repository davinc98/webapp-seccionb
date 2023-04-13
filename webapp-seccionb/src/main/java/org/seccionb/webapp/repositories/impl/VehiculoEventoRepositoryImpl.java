package org.seccionb.webapp.repositories.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.seccionb.webapp.entities.*;
import org.seccionb.webapp.repositories.VehiculoEventoRepository;
import org.seccionb.webapp.repositories.VehiculoRepository;

import java.time.LocalDate;
import java.util.List;


@RequestScoped
public class VehiculoEventoRepositoryImpl implements VehiculoEventoRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<VehiculoEvento> listar() {
        return em.createQuery("SELECT ve FROM VehiculoEvento ve " +
                "LEFT OUTER JOIN FETCH ve.vehiculo " +
                "LEFT OUTER JOIN FETCH ve.tipoEvento " +
                "ORDER BY ve.id DESC", VehiculoEvento.class).getResultList();
    }

    @Override
    public VehiculoEvento porId(Long id) {
        return em.createQuery("SELECT ve FROM VehiculoEvento ve " +
                        "LEFT OUTER JOIN FETCH ve.vehiculo " +
                        "LEFT OUTER JOIN FETCH ve.tipoEvento " +
                        "WHERE ve.id=:id", VehiculoEvento.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void guardar(VehiculoEvento vehiculoEvento) {
        if(vehiculoEvento.getId()!=null && vehiculoEvento.getId()>0){
            em.merge(vehiculoEvento);
        }else{
            em.persist(vehiculoEvento);
        }
    }

    @Override
    public void eliminar(Long id) {
        VehiculoEvento vehiculoEvento = porId(id);
        em.remove(vehiculoEvento);
    }

    @Override
    public VehiculoEvento porIdVehiculoEvento(Long id) {
        return em.find(VehiculoEvento.class, id);
    }

    @Override
    public List<VehiculoEvento> listarPorFecha(LocalDate inicio, LocalDate fin) {
        //WHERE fecha BETWEEN '20121201 00:00' AND '20121202 23:59'
        return null;
    }

    @Override
    public List<VehiculoEvento> buscarPorMatricula(String matricula) {
        return em.createQuery("SELECT ve FROM VehiculoEvento ve " +
                        "LEFT OUTER JOIN FETCH ve.vehiculo " +
                        "LEFT OUTER JOIN FETCH ve.tipoEvento " +
                        " WHERE ve.vehiculo.matricula LIKE :matricula ORDER BY ve.id DESC", VehiculoEvento.class)
                .setParameter("matricula", "%"+matricula+"%")
                .getResultList();
    }
}
