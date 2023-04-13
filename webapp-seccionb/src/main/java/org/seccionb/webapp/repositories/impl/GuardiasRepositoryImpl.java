package org.seccionb.webapp.repositories.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.seccionb.webapp.entities.*;
import org.seccionb.webapp.repositories.GuardiasRepository;
import org.seccionb.webapp.repositories.VehiculoRepository;

import java.util.List;


@RequestScoped
public class GuardiasRepositoryImpl implements GuardiasRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<RolDeGuardias> listar() {
        return em.createQuery("SELECT rg FROM RolDeGuardias rg " +
                "LEFT OUTER JOIN FETCH rg.estatus ORDER BY rg.id DESC", RolDeGuardias.class).getResultList();
    }

    @Override
    public RolDeGuardias porId(Long id) {
        return em.createQuery("SELECT rg FROM RolDeGuardias rg " +
                        "LEFT OUTER JOIN FETCH rg.estatus " +
                        "WHERE rg.id=:id", RolDeGuardias.class)
                .setParameter("id", id)
                .getSingleResult();

    }

    @Override
    public void guardar(RolDeGuardias rolDeGuardias) {
        if(rolDeGuardias.getId()!=null && rolDeGuardias.getId()>0){
            em.merge(rolDeGuardias);
        }else{
            em.persist(rolDeGuardias);
        }
    }

    @Override
    public void eliminar(Long id) {
        RolDeGuardias rolDeGuardias = porId(id);
        em.remove(rolDeGuardias);
    }


    @Override
    public RolDeGuardias porIdRolDeGuardias(Long id) {
        return em.find(RolDeGuardias.class, id);
    }

    @Override
    public EstatusRolGuardia porIdEstatusRolDeGuardia(Long id) {
        return em.find(EstatusRolGuardia.class, id);
    }

    @Override
    public List<EstatusRolGuardia> estatusRolesDeGuardia() {
        return em.createQuery("SELECT er FROM EstatusRolGuardia er", EstatusRolGuardia.class).getResultList();
    }

    @Override
    public TipoGuardia porIdTipoGuardia(Long id) {
        return em.find(TipoGuardia.class, id);
    }

    @Override
    public List<TipoGuardia> tiposGuardia() {
        return em.createQuery("SELECT tg FROM TipoGuardia tg", TipoGuardia.class).getResultList();
    }

    @Override
    public List<Guardia> guardiasPorIdRol(Long idRol) {
        return em.createQuery("SELECT g FROM Guardia g " +
                        "LEFT OUTER JOIN FETCH g.rolDeGuardias " +
                        "LEFT OUTER JOIN FETCH g.persona " +
                        "LEFT OUTER JOIN FETCH g.tipoGuardia " +
                        "WHERE g.rolDeGuardias.id=:id", Guardia.class)
                .setParameter("id", idRol)
                .getResultList();
    }

    @Override
    public List<Guardia> listarGuardias() {
        return em.createQuery("SELECT g FROM Guardia g " +
                "LEFT OUTER JOIN FETCH g.rolDeGuardias " +
                "LEFT OUTER JOIN FETCH g.persona " +
                "LEFT OUTER JOIN FETCH g.tipoGuardia " +
                "ORDER BY g.id DESC", Guardia.class).getResultList();
    }

    @Override
    public Guardia porIdGuardia(Long id) {
        return em.createQuery("SELECT g FROM Guardia g " +
                        "LEFT OUTER JOIN FETCH g.rolDeGuardias " +
                        "LEFT OUTER JOIN FETCH g.persona " +
                        "LEFT OUTER JOIN FETCH g.tipoGuardia " +
                        "WHERE g.id=:id", Guardia.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void guardarGuardia(Guardia guardia) {
        if(guardia.getId()!=null && guardia.getId()>0){
            em.merge(guardia);
        }else{
            em.persist(guardia);
        }

    }

    @Override
    public void eliminarGuardia(Long id) {
        Guardia guardia = porIdGuardia(id);
        em.remove(guardia);
    }
}
