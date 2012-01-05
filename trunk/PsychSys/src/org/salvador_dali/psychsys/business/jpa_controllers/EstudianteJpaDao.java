/*
 * The MIT License
 *
 * Copyright 2011 Edwin Bratini <edwin.bratini@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.salvador_dali.psychsys.business.jpa_controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.salvador_dali.psychsys.business.exceptions.IllegalOrphanException;
import org.salvador_dali.psychsys.business.exceptions.NonexistentEntityException;
import org.salvador_dali.psychsys.model.EstudianteDao;
import org.salvador_dali.psychsys.model.entities.Estudiante;
import org.salvador_dali.psychsys.model.entities.HistoriaClinica;
import org.salvador_dali.psychsys.model.entities.PruebaPsicologica;
import org.salvador_dali.psychsys.model.entities.Referimiento;
import org.salvador_dali.psychsys.model.entities.TutorEstudiante;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class EstudianteJpaDao extends JpaDao implements EstudianteDao {

    public EstudianteJpaDao() {
        super(Estudiante.class);
    }

    public EstudianteJpaDao(Map props) {
        super(Estudiante.class, props);
    }

    @Override
    public <E extends Serializable> void persist(E entity) {
        if (entity instanceof Estudiante) {
            Estudiante estudiante = (Estudiante) entity;
            if (estudiante.getHistoriaClinicaCollection() == null) {
                estudiante.setHistoriaClinicaCollection(new ArrayList<HistoriaClinica>());
            }
            if (estudiante.getTutorEstudianteCollection() == null) {
                estudiante.setTutorEstudianteCollection(new ArrayList<TutorEstudiante>());
            }
            if (estudiante.getPruebaPsicologicaCollection() == null) {
                estudiante.setPruebaPsicologicaCollection(new ArrayList<PruebaPsicologica>());
            }
            if (estudiante.getReferimientoCollection() == null) {
                estudiante.setReferimientoCollection(new ArrayList<Referimiento>());
            }
            EntityManager em = null;
            try {
                em = getEntityManager();
                em.getTransaction().begin();
                Collection<HistoriaClinica> attachedHistoriaClinicaCollection = new ArrayList<HistoriaClinica>();
                for (HistoriaClinica historiaClinicaCollectionHistoriaClinicaToAttach : estudiante.getHistoriaClinicaCollection()) {
                    historiaClinicaCollectionHistoriaClinicaToAttach = em.getReference(historiaClinicaCollectionHistoriaClinicaToAttach.getClass(), historiaClinicaCollectionHistoriaClinicaToAttach.getHicId());
                    attachedHistoriaClinicaCollection.add(historiaClinicaCollectionHistoriaClinicaToAttach);
                }
                estudiante.setHistoriaClinicaCollection(attachedHistoriaClinicaCollection);
                Collection<TutorEstudiante> attachedTutorEstudianteCollection = new ArrayList<TutorEstudiante>();
                for (TutorEstudiante tutorEstudianteCollectionTutorEstudianteToAttach : estudiante.getTutorEstudianteCollection()) {
                    tutorEstudianteCollectionTutorEstudianteToAttach = em.getReference(tutorEstudianteCollectionTutorEstudianteToAttach.getClass(), tutorEstudianteCollectionTutorEstudianteToAttach.getTutorEstudiantePK());
                    attachedTutorEstudianteCollection.add(tutorEstudianteCollectionTutorEstudianteToAttach);
                }
                estudiante.setTutorEstudianteCollection(attachedTutorEstudianteCollection);
                Collection<PruebaPsicologica> attachedPruebaPsicologicaCollection = new ArrayList<PruebaPsicologica>();
                for (PruebaPsicologica pruebaPsicologicaCollectionPruebaPsicologicaToAttach : estudiante.getPruebaPsicologicaCollection()) {
                    pruebaPsicologicaCollectionPruebaPsicologicaToAttach = em.getReference(pruebaPsicologicaCollectionPruebaPsicologicaToAttach.getClass(), pruebaPsicologicaCollectionPruebaPsicologicaToAttach.getPpsId());
                    attachedPruebaPsicologicaCollection.add(pruebaPsicologicaCollectionPruebaPsicologicaToAttach);
                }
                estudiante.setPruebaPsicologicaCollection(attachedPruebaPsicologicaCollection);
                Collection<Referimiento> attachedReferimientoCollection = new ArrayList<Referimiento>();
                for (Referimiento referimientoCollectionReferimientoToAttach : estudiante.getReferimientoCollection()) {
                    referimientoCollectionReferimientoToAttach = em.getReference(referimientoCollectionReferimientoToAttach.getClass(), referimientoCollectionReferimientoToAttach.getRefId());
                    attachedReferimientoCollection.add(referimientoCollectionReferimientoToAttach);
                }
                estudiante.setReferimientoCollection(attachedReferimientoCollection);
                em.persist(estudiante);
                for (HistoriaClinica historiaClinicaCollectionHistoriaClinica : estudiante.getHistoriaClinicaCollection()) {
                    Estudiante oldEstudianteOfHistoriaClinicaCollectionHistoriaClinica = historiaClinicaCollectionHistoriaClinica.getEstudiante();
                    historiaClinicaCollectionHistoriaClinica.setEstudiante(estudiante);
                    historiaClinicaCollectionHistoriaClinica = em.merge(historiaClinicaCollectionHistoriaClinica);
                    if (oldEstudianteOfHistoriaClinicaCollectionHistoriaClinica != null) {
                        oldEstudianteOfHistoriaClinicaCollectionHistoriaClinica.getHistoriaClinicaCollection().remove(historiaClinicaCollectionHistoriaClinica);
                        oldEstudianteOfHistoriaClinicaCollectionHistoriaClinica = em.merge(oldEstudianteOfHistoriaClinicaCollectionHistoriaClinica);
                    }
                }
                for (TutorEstudiante tutorEstudianteCollectionTutorEstudiante : estudiante.getTutorEstudianteCollection()) {
                    Estudiante oldEstudianteOfTutorEstudianteCollectionTutorEstudiante = tutorEstudianteCollectionTutorEstudiante.getEstudiante();
                    tutorEstudianteCollectionTutorEstudiante.setEstudiante(estudiante);
                    tutorEstudianteCollectionTutorEstudiante = em.merge(tutorEstudianteCollectionTutorEstudiante);
                    if (oldEstudianteOfTutorEstudianteCollectionTutorEstudiante != null) {
                        oldEstudianteOfTutorEstudianteCollectionTutorEstudiante.getTutorEstudianteCollection().remove(tutorEstudianteCollectionTutorEstudiante);
                        oldEstudianteOfTutorEstudianteCollectionTutorEstudiante = em.merge(oldEstudianteOfTutorEstudianteCollectionTutorEstudiante);
                    }
                }
                for (PruebaPsicologica pruebaPsicologicaCollectionPruebaPsicologica : estudiante.getPruebaPsicologicaCollection()) {
                    Estudiante oldEstudianteOfPruebaPsicologicaCollectionPruebaPsicologica = pruebaPsicologicaCollectionPruebaPsicologica.getEstudiante();
                    pruebaPsicologicaCollectionPruebaPsicologica.setEstudiante(estudiante);
                    pruebaPsicologicaCollectionPruebaPsicologica = em.merge(pruebaPsicologicaCollectionPruebaPsicologica);
                    if (oldEstudianteOfPruebaPsicologicaCollectionPruebaPsicologica != null) {
                        oldEstudianteOfPruebaPsicologicaCollectionPruebaPsicologica.getPruebaPsicologicaCollection().remove(pruebaPsicologicaCollectionPruebaPsicologica);
                        oldEstudianteOfPruebaPsicologicaCollectionPruebaPsicologica = em.merge(oldEstudianteOfPruebaPsicologicaCollectionPruebaPsicologica);
                    }
                }
                for (Referimiento referimientoCollectionReferimiento : estudiante.getReferimientoCollection()) {
                    Estudiante oldEstudianteOfReferimientoCollectionReferimiento = referimientoCollectionReferimiento.getEstudiante();
                    referimientoCollectionReferimiento.setEstudiante(estudiante);
                    referimientoCollectionReferimiento = em.merge(referimientoCollectionReferimiento);
                    if (oldEstudianteOfReferimientoCollectionReferimiento != null) {
                        oldEstudianteOfReferimientoCollectionReferimiento.getReferimientoCollection().remove(referimientoCollectionReferimiento);
                        oldEstudianteOfReferimientoCollectionReferimiento = em.merge(oldEstudianteOfReferimientoCollectionReferimiento);
                    }
                }
                em.getTransaction().commit();
            } finally {
                if (em != null) {
                    em.close();
                }
            }
        }
    }
    
    public void update(Estudiante estudiante) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante persistentEstudiante = em.find(Estudiante.class, estudiante.getEstId());
            Collection<HistoriaClinica> historiaClinicaCollectionOld = persistentEstudiante.getHistoriaClinicaCollection();
            Collection<HistoriaClinica> historiaClinicaCollectionNew = estudiante.getHistoriaClinicaCollection();
            Collection<TutorEstudiante> tutorEstudianteCollectionOld = persistentEstudiante.getTutorEstudianteCollection();
            Collection<TutorEstudiante> tutorEstudianteCollectionNew = estudiante.getTutorEstudianteCollection();
            Collection<PruebaPsicologica> pruebaPsicologicaCollectionOld = persistentEstudiante.getPruebaPsicologicaCollection();
            Collection<PruebaPsicologica> pruebaPsicologicaCollectionNew = estudiante.getPruebaPsicologicaCollection();
            Collection<Referimiento> referimientoCollectionOld = persistentEstudiante.getReferimientoCollection();
            Collection<Referimiento> referimientoCollectionNew = estudiante.getReferimientoCollection();
            List<String> illegalOrphanMessages = null;
            for (HistoriaClinica historiaClinicaCollectionOldHistoriaClinica : historiaClinicaCollectionOld) {
                if (!historiaClinicaCollectionNew.contains(historiaClinicaCollectionOldHistoriaClinica)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HistoriaClinica " + historiaClinicaCollectionOldHistoriaClinica + " since its estudiante field is not nullable.");
                }
            }
            for (TutorEstudiante tutorEstudianteCollectionOldTutorEstudiante : tutorEstudianteCollectionOld) {
                if (!tutorEstudianteCollectionNew.contains(tutorEstudianteCollectionOldTutorEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TutorEstudiante " + tutorEstudianteCollectionOldTutorEstudiante + " since its estudiante field is not nullable.");
                }
            }
            for (PruebaPsicologica pruebaPsicologicaCollectionOldPruebaPsicologica : pruebaPsicologicaCollectionOld) {
                if (!pruebaPsicologicaCollectionNew.contains(pruebaPsicologicaCollectionOldPruebaPsicologica)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PruebaPsicologica " + pruebaPsicologicaCollectionOldPruebaPsicologica + " since its estudiante field is not nullable.");
                }
            }
            for (Referimiento referimientoCollectionOldReferimiento : referimientoCollectionOld) {
                if (!referimientoCollectionNew.contains(referimientoCollectionOldReferimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Referimiento " + referimientoCollectionOldReferimiento + " since its estudiante field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<HistoriaClinica> attachedHistoriaClinicaCollectionNew = new ArrayList<HistoriaClinica>();
            for (HistoriaClinica historiaClinicaCollectionNewHistoriaClinicaToAttach : historiaClinicaCollectionNew) {
                historiaClinicaCollectionNewHistoriaClinicaToAttach = em.getReference(historiaClinicaCollectionNewHistoriaClinicaToAttach.getClass(), historiaClinicaCollectionNewHistoriaClinicaToAttach.getHicId());
                attachedHistoriaClinicaCollectionNew.add(historiaClinicaCollectionNewHistoriaClinicaToAttach);
            }
            historiaClinicaCollectionNew = attachedHistoriaClinicaCollectionNew;
            estudiante.setHistoriaClinicaCollection(historiaClinicaCollectionNew);
            Collection<TutorEstudiante> attachedTutorEstudianteCollectionNew = new ArrayList<TutorEstudiante>();
            for (TutorEstudiante tutorEstudianteCollectionNewTutorEstudianteToAttach : tutorEstudianteCollectionNew) {
                tutorEstudianteCollectionNewTutorEstudianteToAttach = em.getReference(tutorEstudianteCollectionNewTutorEstudianteToAttach.getClass(), tutorEstudianteCollectionNewTutorEstudianteToAttach.getTutorEstudiantePK());
                attachedTutorEstudianteCollectionNew.add(tutorEstudianteCollectionNewTutorEstudianteToAttach);
            }
            tutorEstudianteCollectionNew = attachedTutorEstudianteCollectionNew;
            estudiante.setTutorEstudianteCollection(tutorEstudianteCollectionNew);
            Collection<PruebaPsicologica> attachedPruebaPsicologicaCollectionNew = new ArrayList<PruebaPsicologica>();
            for (PruebaPsicologica pruebaPsicologicaCollectionNewPruebaPsicologicaToAttach : pruebaPsicologicaCollectionNew) {
                pruebaPsicologicaCollectionNewPruebaPsicologicaToAttach = em.getReference(pruebaPsicologicaCollectionNewPruebaPsicologicaToAttach.getClass(), pruebaPsicologicaCollectionNewPruebaPsicologicaToAttach.getPpsId());
                attachedPruebaPsicologicaCollectionNew.add(pruebaPsicologicaCollectionNewPruebaPsicologicaToAttach);
            }
            pruebaPsicologicaCollectionNew = attachedPruebaPsicologicaCollectionNew;
            estudiante.setPruebaPsicologicaCollection(pruebaPsicologicaCollectionNew);
            Collection<Referimiento> attachedReferimientoCollectionNew = new ArrayList<Referimiento>();
            for (Referimiento referimientoCollectionNewReferimientoToAttach : referimientoCollectionNew) {
                referimientoCollectionNewReferimientoToAttach = em.getReference(referimientoCollectionNewReferimientoToAttach.getClass(), referimientoCollectionNewReferimientoToAttach.getRefId());
                attachedReferimientoCollectionNew.add(referimientoCollectionNewReferimientoToAttach);
            }
            referimientoCollectionNew = attachedReferimientoCollectionNew;
            estudiante.setReferimientoCollection(referimientoCollectionNew);
            estudiante = em.merge(estudiante);
            for (HistoriaClinica historiaClinicaCollectionNewHistoriaClinica : historiaClinicaCollectionNew) {
                if (!historiaClinicaCollectionOld.contains(historiaClinicaCollectionNewHistoriaClinica)) {
                    Estudiante oldEstudianteOfHistoriaClinicaCollectionNewHistoriaClinica = historiaClinicaCollectionNewHistoriaClinica.getEstudiante();
                    historiaClinicaCollectionNewHistoriaClinica.setEstudiante(estudiante);
                    historiaClinicaCollectionNewHistoriaClinica = em.merge(historiaClinicaCollectionNewHistoriaClinica);
                    if (oldEstudianteOfHistoriaClinicaCollectionNewHistoriaClinica != null && !oldEstudianteOfHistoriaClinicaCollectionNewHistoriaClinica.equals(estudiante)) {
                        oldEstudianteOfHistoriaClinicaCollectionNewHistoriaClinica.getHistoriaClinicaCollection().remove(historiaClinicaCollectionNewHistoriaClinica);
                        oldEstudianteOfHistoriaClinicaCollectionNewHistoriaClinica = em.merge(oldEstudianteOfHistoriaClinicaCollectionNewHistoriaClinica);
                    }
                }
            }
            for (TutorEstudiante tutorEstudianteCollectionNewTutorEstudiante : tutorEstudianteCollectionNew) {
                if (!tutorEstudianteCollectionOld.contains(tutorEstudianteCollectionNewTutorEstudiante)) {
                    Estudiante oldEstudianteOfTutorEstudianteCollectionNewTutorEstudiante = tutorEstudianteCollectionNewTutorEstudiante.getEstudiante();
                    tutorEstudianteCollectionNewTutorEstudiante.setEstudiante(estudiante);
                    tutorEstudianteCollectionNewTutorEstudiante = em.merge(tutorEstudianteCollectionNewTutorEstudiante);
                    if (oldEstudianteOfTutorEstudianteCollectionNewTutorEstudiante != null && !oldEstudianteOfTutorEstudianteCollectionNewTutorEstudiante.equals(estudiante)) {
                        oldEstudianteOfTutorEstudianteCollectionNewTutorEstudiante.getTutorEstudianteCollection().remove(tutorEstudianteCollectionNewTutorEstudiante);
                        oldEstudianteOfTutorEstudianteCollectionNewTutorEstudiante = em.merge(oldEstudianteOfTutorEstudianteCollectionNewTutorEstudiante);
                    }
                }
            }
            for (PruebaPsicologica pruebaPsicologicaCollectionNewPruebaPsicologica : pruebaPsicologicaCollectionNew) {
                if (!pruebaPsicologicaCollectionOld.contains(pruebaPsicologicaCollectionNewPruebaPsicologica)) {
                    Estudiante oldEstudianteOfPruebaPsicologicaCollectionNewPruebaPsicologica = pruebaPsicologicaCollectionNewPruebaPsicologica.getEstudiante();
                    pruebaPsicologicaCollectionNewPruebaPsicologica.setEstudiante(estudiante);
                    pruebaPsicologicaCollectionNewPruebaPsicologica = em.merge(pruebaPsicologicaCollectionNewPruebaPsicologica);
                    if (oldEstudianteOfPruebaPsicologicaCollectionNewPruebaPsicologica != null && !oldEstudianteOfPruebaPsicologicaCollectionNewPruebaPsicologica.equals(estudiante)) {
                        oldEstudianteOfPruebaPsicologicaCollectionNewPruebaPsicologica.getPruebaPsicologicaCollection().remove(pruebaPsicologicaCollectionNewPruebaPsicologica);
                        oldEstudianteOfPruebaPsicologicaCollectionNewPruebaPsicologica = em.merge(oldEstudianteOfPruebaPsicologicaCollectionNewPruebaPsicologica);
                    }
                }
            }
            for (Referimiento referimientoCollectionNewReferimiento : referimientoCollectionNew) {
                if (!referimientoCollectionOld.contains(referimientoCollectionNewReferimiento)) {
                    Estudiante oldEstudianteOfReferimientoCollectionNewReferimiento = referimientoCollectionNewReferimiento.getEstudiante();
                    referimientoCollectionNewReferimiento.setEstudiante(estudiante);
                    referimientoCollectionNewReferimiento = em.merge(referimientoCollectionNewReferimiento);
                    if (oldEstudianteOfReferimientoCollectionNewReferimiento != null && !oldEstudianteOfReferimientoCollectionNewReferimiento.equals(estudiante)) {
                        oldEstudianteOfReferimientoCollectionNewReferimiento.getReferimientoCollection().remove(referimientoCollectionNewReferimiento);
                        oldEstudianteOfReferimientoCollectionNewReferimiento = em.merge(oldEstudianteOfReferimientoCollectionNewReferimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estudiante.getEstId();
                if (findById(id) == null) {
                    throw new NonexistentEntityException("The estudiante with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public <E extends Serializable> E update(E entity) {
        if (entity instanceof Estudiante) {
            Estudiante estudiante = (Estudiante) entity;
            try {
                update(estudiante);
                return (E) estudiante;
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(EstudianteJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(EstudianteJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (Exception ex) {
                Logger.getLogger(EstudianteJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } else {
            return null;
        }
    }
    
    public void remove(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante estudiante;
            try {
                estudiante = em.getReference(Estudiante.class, id);
                estudiante.getEstId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estudiante with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<HistoriaClinica> historiaClinicaCollectionOrphanCheck = estudiante.getHistoriaClinicaCollection();
            for (HistoriaClinica historiaClinicaCollectionOrphanCheckHistoriaClinica : historiaClinicaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estudiante (" + estudiante + ") cannot be destroyed since the HistoriaClinica " + historiaClinicaCollectionOrphanCheckHistoriaClinica + " in its historiaClinicaCollection field has a non-nullable estudiante field.");
            }
            Collection<TutorEstudiante> tutorEstudianteCollectionOrphanCheck = estudiante.getTutorEstudianteCollection();
            for (TutorEstudiante tutorEstudianteCollectionOrphanCheckTutorEstudiante : tutorEstudianteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estudiante (" + estudiante + ") cannot be destroyed since the TutorEstudiante " + tutorEstudianteCollectionOrphanCheckTutorEstudiante + " in its tutorEstudianteCollection field has a non-nullable estudiante field.");
            }
            Collection<PruebaPsicologica> pruebaPsicologicaCollectionOrphanCheck = estudiante.getPruebaPsicologicaCollection();
            for (PruebaPsicologica pruebaPsicologicaCollectionOrphanCheckPruebaPsicologica : pruebaPsicologicaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estudiante (" + estudiante + ") cannot be destroyed since the PruebaPsicologica " + pruebaPsicologicaCollectionOrphanCheckPruebaPsicologica + " in its pruebaPsicologicaCollection field has a non-nullable estudiante field.");
            }
            Collection<Referimiento> referimientoCollectionOrphanCheck = estudiante.getReferimientoCollection();
            for (Referimiento referimientoCollectionOrphanCheckReferimiento : referimientoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estudiante (" + estudiante + ") cannot be destroyed since the Referimiento " + referimientoCollectionOrphanCheckReferimiento + " in its referimientoCollection field has a non-nullable estudiante field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public <E extends Serializable> void remove(E entity) {
        if (entity instanceof Estudiante) {
            try {
                remove(((Estudiante) entity).getEstId());
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(EstudianteJpaDao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(EstudianteJpaDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public Estudiante getEstudianteByDNI(String dni) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Estudiante.findByEstDni");
            q.setParameter("estDni", dni);
            return (Estudiante) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Estudiante> getEstudiantesByPrimerApellido(String primerApellido) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Estudiante.findByEstPrimerApellido");
            q.setParameter("estPrimerApellido", primerApellido.toLowerCase());
            return (List<Estudiante>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Estudiante> getEstudiantesByPrimerNombre(String primerNombre) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Estudiante.findByEstPrimerNombre");
            q.setParameter("estPrimerNombre", primerNombre.toLowerCase());
            return (List<Estudiante>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Estudiante> getEstudiantesByStatus(char status) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Estudiante.findByEstStatus");
            q.setParameter("estStatus", status);
            return (List<Estudiante>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Estudiante> getEstudiantesByNombreCompleto(String primerNombre, String primerApellido) {
        EntityManager entityManager = getEntityManager();
        try {
            String strQuery = "SELECT e FROM Estudiante e WHERE e.estPrimerNombre = :primerNombre";
            strQuery += " AND e.estPrimerApellido = :primerApellido";
            Query q = entityManager.createQuery(strQuery);
            q.setParameter("primerNombre", primerNombre.toLowerCase());
            q.setParameter("primerApellido", primerApellido.toLowerCase());
            return (List<Estudiante>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
