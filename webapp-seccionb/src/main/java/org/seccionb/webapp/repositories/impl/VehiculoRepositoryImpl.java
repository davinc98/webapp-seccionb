package org.seccionb.webapp.repositories.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.seccionb.webapp.entities.*;
import org.seccionb.webapp.repositories.VehiculoRepository;

import java.util.List;


@RequestScoped
public class VehiculoRepositoryImpl implements VehiculoRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<Vehiculo> listar() {
        return em.createQuery("SELECT v FROM Vehiculo v " +
                "LEFT OUTER JOIN FETCH v.estadoProcedencia " +
                "LEFT OUTER JOIN FETCH v.tipoTarjeton " +
                "LEFT OUTER JOIN FETCH V.estatusVehiculo " +
                "ORDER BY v.id DESC", Vehiculo.class).getResultList();
    }

    @Override
    public Vehiculo porId(Long id) {
        return em.createQuery("SELECT v FROM Vehiculo v " +
                        "LEFT OUTER JOIN FETCH v.estadoProcedencia " +
                        "LEFT OUTER JOIN FETCH v.tipoTarjeton " +
                        "LEFT OUTER JOIN FETCH V.estatusVehiculo " +
                        "WHERE v.id=:id ORDER BY v.id DESC", Vehiculo.class)
                .setParameter("id", id)
                .getSingleResult();

    }

    @Override
    public void guardar(Vehiculo vehiculo) {
        if(vehiculo.getId()!=null && vehiculo.getId()>0){
            em.merge(vehiculo);
        }else{
            em.persist(vehiculo);
        }
    }

    @Override
    public void eliminar(Long id) {
        Vehiculo vehiculo = porId(id);
        em.remove(vehiculo);
    }

    @Override
    public List<Vehiculo> vehiculosPorIdPersona(Long idPersona) {
        return em.createQuery("SELECT v FROM Vehiculo v " +
                        "LEFT OUTER JOIN FETCH v.estadoProcedencia " +
                        "LEFT OUTER JOIN FETCH v.tipoTarjeton " +
                        "LEFT OUTER JOIN FETCH V.estatusVehiculo " +
                        "WHERE v.personaId=:personaId", Vehiculo.class)
                .setParameter("personaId", idPersona)
                .getResultList();
    }

    @Override
    public List<TipoTarjeton> tiposTarjeton() {
        return em.createQuery("SELECT tj FROM TipoTarjeton tj", TipoTarjeton.class).getResultList();
    }

    @Override
    public List<EstatusVehiculo> estatusVehiculo() {
        return em.createQuery("SELECT ev FROM EstatusVehiculo ev", EstatusVehiculo.class).getResultList();
    }

    @Override
    public TipoTarjeton porIdTipoTarjeton(Long id) {
        return em.find(TipoTarjeton.class, id);
    }

    @Override
    public EstatusVehiculo porIdEstatusVehiculo(Long id) {
        return em.find(EstatusVehiculo.class, id);
    }

    @Override
    public CajonEstacionamiento porIdCajonEstacionamiento(Long id) {
        return em.find(CajonEstacionamiento.class, id);
    }

    @Override
    public Vehiculo porMatriculaVehiculo(String matricula) {
        return em.createQuery("SELECT v FROM Vehiculo v " +
                        "LEFT OUTER JOIN FETCH v.estadoProcedencia " +
                        "LEFT OUTER JOIN FETCH v.tipoTarjeton " +
                        "LEFT OUTER JOIN FETCH V.estatusVehiculo " +
                        "WHERE v.matricula=:matricula", Vehiculo.class)
                .setParameter("matricula", matricula)
                .getSingleResult();
    }

    @Override
    public List<Vehiculo> getVehiculosPorMatricula(String matricula) {
        return em.createQuery("SELECT v FROM Vehiculo v " +
                        "LEFT OUTER JOIN FETCH v.estadoProcedencia " +
                        "LEFT OUTER JOIN FETCH v.tipoTarjeton " +
                        "LEFT OUTER JOIN FETCH V.estatusVehiculo " +
                        "WHERE v.matricula LIKE :matricula", Vehiculo.class)
                .setParameter("matricula", matricula+"%")
                .getResultList();
    }
}
