package org.seccionb.webapp.repositories.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.seccionb.webapp.entities.Cargo;
import org.seccionb.webapp.entities.Contacto;
import org.seccionb.webapp.entities.TipoContacto;
import org.seccionb.webapp.repositories.CargoRepository;
import org.seccionb.webapp.repositories.ContactoRepository;

import java.util.List;

@RequestScoped
public class ContactoRepositoryImpl implements ContactoRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<Contacto> listar() {
        return em.createQuery("SELECT c FROM Contacto c LEFT OUTER JOIN FETCH c.tipoContacto", Contacto.class).getResultList();
    }

    @Override
    public Contacto porId(Long id) {
        return em.createQuery("SELECT c FROM Contacto c LEFT OUTER JOIN FETCH c.tipoContacto WHERE c.id=:id", Contacto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void guardar(Contacto contacto) {
        if(contacto.getId()!=null && contacto.getId()>0){
            em.merge(contacto);
        }else{
            em.persist(contacto);
        }
    }

    @Override
    public void eliminar(Long id) {
        Contacto contacto = porId(id);
        em.remove(contacto);
    }

    @Override
    public List<Contacto> contactosPorIdPersona(Long id) {
        return em.createQuery("SELECT c FROM Contacto c WHERE c.personaId=:personaId ORDER BY c.id DESC", Contacto.class)
                .setParameter("personaId", id)
                .getResultList();
    }

    @Override
    public List<TipoContacto> listarTiposContacto() {
        return em.createQuery("SELECT c FROM TipoContacto c", TipoContacto.class).getResultList();
    }

    @Override
    public TipoContacto porIdTipoContacto(Long id) {
        return em.createQuery("SELECT c FROM TipoContacto c WHERE c.id=:id", TipoContacto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void guardarTipoContacto(TipoContacto tipoContacto) {
        if(tipoContacto.getId()!=null && tipoContacto.getId()>0){
            em.merge(tipoContacto);
        }else{
            em.persist(tipoContacto);
        }
    }

    @Override
    public void eliminarTipoContacto(Long id) {
        TipoContacto tipoContacto = porIdTipoContacto(id);
        em.remove(tipoContacto);
    }
}
