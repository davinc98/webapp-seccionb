package org.seccionb.webapp.repositories.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.seccionb.webapp.entities.*;
import org.seccionb.webapp.repositories.EstudianteRepository;

import java.util.List;

@RequestScoped
public class EstudianteRepositoryImpl implements EstudianteRepository {

    @Inject
    private EntityManager em;


    @Override
    public List<Interno> listar() {
        return em.createQuery("SELECT e FROM Estudiante e " +
                "LEFT OUTER JOIN FETCH e.persona " +
                "LEFT OUTER JOIN FETCH e.escuela " +
                "LEFT OUTER JOIN FETCH e.carrera " +
                "LEFT OUTER JOIN FETCH e.estatusEstudiante " +
                "LEFT OUTER JOIN FETCH e.estadoProcedencia " +
                "LEFT OUTER JOIN FETCH e.situacionEscolar ORDER BY e.id DESC", Interno.class).getResultList();
    }

    @Override
    public Interno porId(Long id) {
        return em.createQuery("SELECT e FROM Estudiante e " +
                        "LEFT OUTER JOIN FETCH e.persona " +
                        "LEFT OUTER JOIN FETCH e.escuela " +
                        "LEFT OUTER JOIN FETCH e.carrera " +
                        "LEFT OUTER JOIN FETCH e.estatusEstudiante " +
                        "LEFT OUTER JOIN FETCH e.estadoProcedencia " +
                        "LEFT OUTER JOIN FETCH e.situacionEscolar WHERE e.id=:id", Interno.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void guardar(Interno interno) {
        if(interno.getId()!=null && interno.getId()>0){
            em.merge(interno);
        }else{
            em.persist(interno);
        }
    }

    @Override
    public void eliminar(Long id) {
        Interno interno = porId(id);
        em.remove(interno);
    }

    @Override
    public List<Escuela> listarEscuelas() {
        return em.createQuery("SELECT e FROM Escuela e", Escuela.class).getResultList();
    }

    @Override
    public List<UnidadAcademica> listarUnidadesAcademicas() {
        return em.createQuery("SELECT e FROM UnidadAcademica e", UnidadAcademica.class).getResultList();
    }

    @Override
    public List<Carrera> listarCarreras() {
        return em.createQuery("SELECT e FROM Carrera e ORDER BY e.nombre ASC", Carrera.class).getResultList();
    }

    @Override
    public List<SituacionEscolar> listarSituacionesAcademicas() {
        return em.createQuery("SELECT e FROM SituacionEscolar e", SituacionEscolar.class).getResultList();
    }

    @Override
    public List<EstadoProcedencia> listarEstadosDeProcedencia() {
        return em.createQuery("SELECT e FROM EstadoProcedencia e ORDER BY e.descripcion ASC", EstadoProcedencia.class).getResultList();
    }

    @Override
    public EstadoProcedencia porIdEstadoProcedencia(Long id) {
        return em.find(EstadoProcedencia.class, id);
    }

    @Override
    public Escuela porIdEscuela(Long id) {
        return em.find(Escuela.class, id);
    }

    @Override
    public SituacionEscolar porIdSituacionEscolar(Long id) {
        return em.find(SituacionEscolar.class, id);
    }

    @Override
    public Carrera porIdCarrera(Long id) {
        return em.find(Carrera.class, id);
    }

    @Override
    public Persona getPersonaPorIdEstudiante(Long id) {
        return em.createQuery("SELECT p FROM Persona p INNER JOIN Estudiante e ON e.persona.id=p.id  WHERE e.id=:id", Persona.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Interno getEstudiantePorNombreUsuario(String username) {
        return em.createQuery("SELECT e FROM Estudiante e " +
                        "LEFT OUTER JOIN FETCH e.persona WHERE e.usuario=:usuario", Interno.class)
                .setParameter("usuario", username)
                .getSingleResult();
    }

    @Override
    public List<EstatusInterno> listarEstatusEstudiantes() {
        return em.createQuery("SELECT ee FROM EstatusEstudiante ee", EstatusInterno.class).getResultList();
    }

    @Override
    public EstatusInterno porIdEstatusEstudiante(Long id) {
        return em.find(EstatusInterno.class, id);
    }

    @Override
    public List<Interno> listarEstudiantesActivos() {
        return em.createQuery("SELECT e FROM Estudiante e " +
                "LEFT OUTER JOIN FETCH e.persona " +
                "LEFT OUTER JOIN FETCH e.escuela " +
                "LEFT OUTER JOIN FETCH e.carrera " +
                "LEFT OUTER JOIN FETCH e.estatusEstudiante " +
                "LEFT OUTER JOIN FETCH e.estadoProcedencia " +
                "LEFT OUTER JOIN FETCH e.situacionEscolar WHERE e.estatusEstudiante.id=1 " +
                "ORDER BY e.id DESC", Interno.class).getResultList();
    }

    @Override
    public List<Interno> listarEstudiantesEgresados() {
        return em.createQuery("SELECT e FROM Estudiante e " +
                "LEFT OUTER JOIN FETCH e.persona " +
                "LEFT OUTER JOIN FETCH e.escuela " +
                "LEFT OUTER JOIN FETCH e.carrera " +
                "LEFT OUTER JOIN FETCH e.estatusEstudiante " +
                "LEFT OUTER JOIN FETCH e.estadoProcedencia " +
                "LEFT OUTER JOIN FETCH e.situacionEscolar WHERE e.estatusEstudiante.id=2 " +
                "ORDER BY e.id DESC", Interno.class).getResultList();
    }

    @Override
    public List<Interno> listarEstudiantesBajaTemporal() {
        return em.createQuery("SELECT e FROM Estudiante e " +
                "LEFT OUTER JOIN FETCH e.persona " +
                "LEFT OUTER JOIN FETCH e.escuela " +
                "LEFT OUTER JOIN FETCH e.carrera " +
                "LEFT OUTER JOIN FETCH e.estatusEstudiante " +
                "LEFT OUTER JOIN FETCH e.estadoProcedencia " +
                "LEFT OUTER JOIN FETCH e.situacionEscolar WHERE e.estatusEstudiante.id=3 " +
                "ORDER BY e.id DESC", Interno.class).getResultList();
    }

    @Override
    public List<Interno> listarEstudiantesBajaDefinitiva() {
        return em.createQuery("SELECT e FROM Estudiante e " +
                "LEFT OUTER JOIN FETCH e.persona " +
                "LEFT OUTER JOIN FETCH e.escuela " +
                "LEFT OUTER JOIN FETCH e.carrera " +
                "LEFT OUTER JOIN FETCH e.estatusEstudiante " +
                "LEFT OUTER JOIN FETCH e.estadoProcedencia " +
                "LEFT OUTER JOIN FETCH e.situacionEscolar WHERE e.estatusEstudiante.id=4 " +
                "ORDER BY e.id DESC", Interno.class).getResultList();
    }

    @Override
    public List<Interno> getEstudiantesPorCurp(String curp) {
        return em.createQuery("SELECT e FROM Estudiante e " +
                        "LEFT OUTER JOIN FETCH e.persona " +
                        "LEFT OUTER JOIN FETCH e.escuela " +
                        "LEFT OUTER JOIN FETCH e.carrera " +
                        "LEFT OUTER JOIN FETCH e.estatusEstudiante " +
                        "LEFT OUTER JOIN FETCH e.estadoProcedencia " +
                        "LEFT OUTER JOIN FETCH e.persona WHERE e.persona.curp LIKE :curp", Interno.class)
                .setParameter("curp", curp+"%")
                .getResultList();
    }

    @Override
    public Interno getEstudiantePorIdPersona(Long personaId) {
        return em.createQuery("SELECT e FROM Estudiante e " +
                        "LEFT OUTER JOIN FETCH e.persona " +
                        "LEFT OUTER JOIN FETCH e.escuela " +
                        "LEFT OUTER JOIN FETCH e.carrera " +
                        "LEFT OUTER JOIN FETCH e.estatusEstudiante " +
                        "LEFT OUTER JOIN FETCH e.estadoProcedencia " +
                        "LEFT OUTER JOIN FETCH e.situacionEscolar WHERE e.persona.id=:id", Interno.class)
                .setParameter("id", personaId)
                .getSingleResult();
    }
}
