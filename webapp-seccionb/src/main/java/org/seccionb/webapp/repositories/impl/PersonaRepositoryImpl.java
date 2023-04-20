package org.seccionb.webapp.repositories.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.seccionb.webapp.entities.FotoTemporal;
import org.seccionb.webapp.entities.Persona;
import org.seccionb.webapp.repositories.PersonaRepository;

import java.util.List;


@RequestScoped
public class PersonaRepositoryImpl implements PersonaRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<Persona> listar() {
        return em.createQuery("SELECT p FROM Persona p " +
                "ORDER BY p.id DESC", Persona.class).getResultList();
    }

    @Override
    public Persona porId(Long id) {
        return em.find(Persona.class, id);
    }

    @Override
    public void guardar(Persona persona) {
        if(persona.getId()!=null && persona.getId()>0){
            em.merge(persona);
        }else{
            em.persist(persona);
        }
    }

    @Override
    public void eliminar(Long id) {
        Persona persona = porId(id);
        em.remove(persona);
    }

    @Override
    public List<Persona> listarUsuariosEstacionamiento() {
//        List<Estudiante> estudiantes = estudianteRepository.listar();
//        List<Persona> personas = this.listar();
//        return em.createQuery("SELECT p FROM Persona p WHERE NOT EXISTS (SELECT e.persona FROM Estudiante e LEFT OUTER JOIN FETCH e.persona)", Persona.class).getResultList();
        List<Persona> estudiantes = em.createQuery("SELECT e.persona FROM Interno e LEFT OUTER JOIN e.persona ORDER BY e.id DESC", Persona.class).getResultList();
        List<Persona> usuariosEstacionamiento =  em.createQuery("SELECT p FROM Persona p ORDER BY p.id DESC", Persona.class).getResultList();

        usuariosEstacionamiento.removeAll(estudiantes);
        return usuariosEstacionamiento;
    }

    @Override
    public Persona porIdPersona(Long id) {
        return em.find(Persona.class, id);
    }

    @Override
    public List<Persona> listarPersonasEstudiantes() {
        return em.createQuery("SELECT e.persona FROM Interno e " +
                " ORDER BY e.id DESC", Persona.class).getResultList();
    }

    @Override
    public List<Persona> listarPersonasEstudiantesActivos() {
        return em.createQuery("SELECT e.persona FROM Interno e " +
                " WHERE e.estatusEstudiante.id=1 " +
                " ORDER BY e.id DESC", Persona.class).getResultList();
    }

    @Override
    public List<Persona> getPersonasPorCurp(String curp) {
        return em.createQuery("SELECT p FROM Persona p " +
                "WHERE p.curp LIKE :curp " +
                "ORDER BY p.id DESC", Persona.class)
                .setParameter("curp", curp+"%")
                .getResultList();
    }


    @Override
    public void guardarImagenTemporalBase64(FotoTemporal fotoTemporal) {
        eliminarImagenTemporalBase64();
        if(fotoTemporal.getId()!=null && fotoTemporal.getId()>0){
            em.merge(fotoTemporal);
        }else{
            em.persist(fotoTemporal);
        }
    }


    public FotoTemporal porIdFotoTemporal(Long id) {
        return em.find(FotoTemporal.class, id);
    }

    @Override
    public void eliminarImagenTemporalBase64() {
        List<FotoTemporal> fotos = em.createQuery("SELECT ft FROM FotoTemporal ft ", FotoTemporal.class).getResultList();
        fotos.forEach(f->{
            FotoTemporal aux = porIdFotoTemporal(f.getId());
            em.remove(aux);
        });
    }

    @Override
    public FotoTemporal getImagenTemporalBase64() {
        try {
            return em.createQuery("SELECT ft FROM FotoTemporal ft ", FotoTemporal.class).getSingleResult();
        }catch (Exception e){
//            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void eliminarFotoBase64PorId(Long personaId) {
        em.createQuery("UPDATE Persona p SET p.fotoBase64='' WHERE p.id=:id")
                .setParameter("id", personaId)
                .executeUpdate();
    }
}
