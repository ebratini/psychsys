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
import java.util.Date;
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
import org.salvador_dali.psychsys.model.HistoriaClinicaDao;
import org.salvador_dali.psychsys.model.entities.AntNeonatal;
import org.salvador_dali.psychsys.model.entities.AntPersMadre;
import org.salvador_dali.psychsys.model.entities.AntPsicomotrizLenguaje;
import org.salvador_dali.psychsys.model.entities.AntPsicosocialSexual;
import org.salvador_dali.psychsys.model.entities.AntRecienNacido;
import org.salvador_dali.psychsys.model.entities.Escolaridad;
import org.salvador_dali.psychsys.model.entities.Estudiante;
import org.salvador_dali.psychsys.model.entities.HistoriaClinica;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class HistoriaClinicaJpaDao extends JpaDao implements HistoriaClinicaDao {

    public HistoriaClinicaJpaDao() {
        super(HistoriaClinica.class);
    }
    
    public HistoriaClinicaJpaDao(Map properties) {
        super(HistoriaClinica.class, properties);
    }

    @Override
    public <E extends Serializable> void persist(E entity) {
        if (entity instanceof HistoriaClinica) {
            HistoriaClinica historiaClinica = (HistoriaClinica) entity;
            EntityManager em = null;
            try {
                em = getEntityManager();
                em.getTransaction().begin();
                Estudiante estudiante = historiaClinica.getEstudiante();
                if (estudiante != null) {
                    estudiante = em.getReference(estudiante.getClass(), estudiante.getEstId());
                    historiaClinica.setEstudiante(estudiante);
                }
                AntPsicosocialSexual antPsicosocialSexual = historiaClinica.getAntPsicosocialSexual();
                if (antPsicosocialSexual != null) {
                    antPsicosocialSexual = em.getReference(antPsicosocialSexual.getClass(), antPsicosocialSexual.getHicId());
                    historiaClinica.setAntPsicosocialSexual(antPsicosocialSexual);
                }
                AntPersMadre antPersMadre = historiaClinica.getAntPersMadre();
                if (antPersMadre != null) {
                    antPersMadre = em.getReference(antPersMadre.getClass(), antPersMadre.getHicId());
                    historiaClinica.setAntPersMadre(antPersMadre);
                }
                AntPsicomotrizLenguaje antPsicomotrizLenguaje = historiaClinica.getAntPsicomotrizLenguaje();
                if (antPsicomotrizLenguaje != null) {
                    antPsicomotrizLenguaje = em.getReference(antPsicomotrizLenguaje.getClass(), antPsicomotrizLenguaje.getHicId());
                    historiaClinica.setAntPsicomotrizLenguaje(antPsicomotrizLenguaje);
                }
                Escolaridad escolaridad = historiaClinica.getEscolaridad();
                if (escolaridad != null) {
                    escolaridad = em.getReference(escolaridad.getClass(), escolaridad.getHicId());
                    historiaClinica.setEscolaridad(escolaridad);
                }
                AntRecienNacido antRecienNacido = historiaClinica.getAntRecienNacido();
                if (antRecienNacido != null) {
                    antRecienNacido = em.getReference(antRecienNacido.getClass(), antRecienNacido.getHicId());
                    historiaClinica.setAntRecienNacido(antRecienNacido);
                }
                AntNeonatal antNeonatal = historiaClinica.getAntNeonatal();
                if (antNeonatal != null) {
                    antNeonatal = em.getReference(antNeonatal.getClass(), antNeonatal.getHicId());
                    historiaClinica.setAntNeonatal(antNeonatal);
                }
                em.persist(historiaClinica);
                if (estudiante != null) {
                    estudiante.getHistoriaClinicaCollection().add(historiaClinica);
                    estudiante = em.merge(estudiante);
                }
                if (antPsicosocialSexual != null) {
                    HistoriaClinica oldHistoriaClinicaOfAntPsicosocialSexual = antPsicosocialSexual.getHistoriaClinica();
                    if (oldHistoriaClinicaOfAntPsicosocialSexual != null) {
                        oldHistoriaClinicaOfAntPsicosocialSexual.setAntPsicosocialSexual(null);
                        oldHistoriaClinicaOfAntPsicosocialSexual = em.merge(oldHistoriaClinicaOfAntPsicosocialSexual);
                    }
                    antPsicosocialSexual.setHistoriaClinica(historiaClinica);
                    antPsicosocialSexual = em.merge(antPsicosocialSexual);
                }
                if (antPersMadre != null) {
                    HistoriaClinica oldHistoriaClinicaOfAntPersMadre = antPersMadre.getHistoriaClinica();
                    if (oldHistoriaClinicaOfAntPersMadre != null) {
                        oldHistoriaClinicaOfAntPersMadre.setAntPersMadre(null);
                        oldHistoriaClinicaOfAntPersMadre = em.merge(oldHistoriaClinicaOfAntPersMadre);
                    }
                    antPersMadre.setHistoriaClinica(historiaClinica);
                    antPersMadre = em.merge(antPersMadre);
                }
                if (antPsicomotrizLenguaje != null) {
                    HistoriaClinica oldHistoriaClinicaOfAntPsicomotrizLenguaje = antPsicomotrizLenguaje.getHistoriaClinica();
                    if (oldHistoriaClinicaOfAntPsicomotrizLenguaje != null) {
                        oldHistoriaClinicaOfAntPsicomotrizLenguaje.setAntPsicomotrizLenguaje(null);
                        oldHistoriaClinicaOfAntPsicomotrizLenguaje = em.merge(oldHistoriaClinicaOfAntPsicomotrizLenguaje);
                    }
                    antPsicomotrizLenguaje.setHistoriaClinica(historiaClinica);
                    antPsicomotrizLenguaje = em.merge(antPsicomotrizLenguaje);
                }
                if (escolaridad != null) {
                    HistoriaClinica oldHistoriaClinicaOfEscolaridad = escolaridad.getHistoriaClinica();
                    if (oldHistoriaClinicaOfEscolaridad != null) {
                        oldHistoriaClinicaOfEscolaridad.setEscolaridad(null);
                        oldHistoriaClinicaOfEscolaridad = em.merge(oldHistoriaClinicaOfEscolaridad);
                    }
                    escolaridad.setHistoriaClinica(historiaClinica);
                    escolaridad = em.merge(escolaridad);
                }
                if (antRecienNacido != null) {
                    HistoriaClinica oldHistoriaClinicaOfAntRecienNacido = antRecienNacido.getHistoriaClinica();
                    if (oldHistoriaClinicaOfAntRecienNacido != null) {
                        oldHistoriaClinicaOfAntRecienNacido.setAntRecienNacido(null);
                        oldHistoriaClinicaOfAntRecienNacido = em.merge(oldHistoriaClinicaOfAntRecienNacido);
                    }
                    antRecienNacido.setHistoriaClinica(historiaClinica);
                    antRecienNacido = em.merge(antRecienNacido);
                }
                if (antNeonatal != null) {
                    HistoriaClinica oldHistoriaClinicaOfAntNeonatal = antNeonatal.getHistoriaClinica();
                    if (oldHistoriaClinicaOfAntNeonatal != null) {
                        oldHistoriaClinicaOfAntNeonatal.setAntNeonatal(null);
                        oldHistoriaClinicaOfAntNeonatal = em.merge(oldHistoriaClinicaOfAntNeonatal);
                    }
                    antNeonatal.setHistoriaClinica(historiaClinica);
                    antNeonatal = em.merge(antNeonatal);
                }
                em.getTransaction().commit();
            } finally {
                if (em != null) {
                    em.close();
                }
            }
        }
    }
    
    public void update(HistoriaClinica historiaClinica) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HistoriaClinica persistentHistoriaClinica = em.find(HistoriaClinica.class, historiaClinica.getHicId());
            Estudiante estudianteOld = persistentHistoriaClinica.getEstudiante();
            Estudiante estudianteNew = historiaClinica.getEstudiante();
            AntPsicosocialSexual antPsicosocialSexualOld = persistentHistoriaClinica.getAntPsicosocialSexual();
            AntPsicosocialSexual antPsicosocialSexualNew = historiaClinica.getAntPsicosocialSexual();
            AntPersMadre antPersMadreOld = persistentHistoriaClinica.getAntPersMadre();
            AntPersMadre antPersMadreNew = historiaClinica.getAntPersMadre();
            AntPsicomotrizLenguaje antPsicomotrizLenguajeOld = persistentHistoriaClinica.getAntPsicomotrizLenguaje();
            AntPsicomotrizLenguaje antPsicomotrizLenguajeNew = historiaClinica.getAntPsicomotrizLenguaje();
            Escolaridad escolaridadOld = persistentHistoriaClinica.getEscolaridad();
            Escolaridad escolaridadNew = historiaClinica.getEscolaridad();
            AntRecienNacido antRecienNacidoOld = persistentHistoriaClinica.getAntRecienNacido();
            AntRecienNacido antRecienNacidoNew = historiaClinica.getAntRecienNacido();
            AntNeonatal antNeonatalOld = persistentHistoriaClinica.getAntNeonatal();
            AntNeonatal antNeonatalNew = historiaClinica.getAntNeonatal();
            List<String> illegalOrphanMessages = null;
            if (antPsicosocialSexualOld != null && !antPsicosocialSexualOld.equals(antPsicosocialSexualNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain AntPsicosocialSexual " + antPsicosocialSexualOld + " since its historiaClinica field is not nullable.");
            }
            if (antPersMadreOld != null && !antPersMadreOld.equals(antPersMadreNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain AntPersMadre " + antPersMadreOld + " since its historiaClinica field is not nullable.");
            }
            if (antPsicomotrizLenguajeOld != null && !antPsicomotrizLenguajeOld.equals(antPsicomotrizLenguajeNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain AntPsicomotrizLenguaje " + antPsicomotrizLenguajeOld + " since its historiaClinica field is not nullable.");
            }
            if (escolaridadOld != null && !escolaridadOld.equals(escolaridadNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Escolaridad " + escolaridadOld + " since its historiaClinica field is not nullable.");
            }
            if (antRecienNacidoOld != null && !antRecienNacidoOld.equals(antRecienNacidoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain AntRecienNacido " + antRecienNacidoOld + " since its historiaClinica field is not nullable.");
            }
            if (antNeonatalOld != null && !antNeonatalOld.equals(antNeonatalNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain AntNeonatal " + antNeonatalOld + " since its historiaClinica field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estudianteNew != null) {
                estudianteNew = em.getReference(estudianteNew.getClass(), estudianteNew.getEstId());
                historiaClinica.setEstudiante(estudianteNew);
            }
            if (antPsicosocialSexualNew != null) {
                antPsicosocialSexualNew = em.getReference(antPsicosocialSexualNew.getClass(), antPsicosocialSexualNew.getHicId());
                historiaClinica.setAntPsicosocialSexual(antPsicosocialSexualNew);
            }
            if (antPersMadreNew != null) {
                antPersMadreNew = em.getReference(antPersMadreNew.getClass(), antPersMadreNew.getHicId());
                historiaClinica.setAntPersMadre(antPersMadreNew);
            }
            if (antPsicomotrizLenguajeNew != null) {
                antPsicomotrizLenguajeNew = em.getReference(antPsicomotrizLenguajeNew.getClass(), antPsicomotrizLenguajeNew.getHicId());
                historiaClinica.setAntPsicomotrizLenguaje(antPsicomotrizLenguajeNew);
            }
            if (escolaridadNew != null) {
                escolaridadNew = em.getReference(escolaridadNew.getClass(), escolaridadNew.getHicId());
                historiaClinica.setEscolaridad(escolaridadNew);
            }
            if (antRecienNacidoNew != null) {
                antRecienNacidoNew = em.getReference(antRecienNacidoNew.getClass(), antRecienNacidoNew.getHicId());
                historiaClinica.setAntRecienNacido(antRecienNacidoNew);
            }
            if (antNeonatalNew != null) {
                antNeonatalNew = em.getReference(antNeonatalNew.getClass(), antNeonatalNew.getHicId());
                historiaClinica.setAntNeonatal(antNeonatalNew);
            }
            historiaClinica = em.merge(historiaClinica);
            if (estudianteOld != null && !estudianteOld.equals(estudianteNew)) {
                estudianteOld.getHistoriaClinicaCollection().remove(historiaClinica);
                estudianteOld = em.merge(estudianteOld);
            }
            if (estudianteNew != null && !estudianteNew.equals(estudianteOld)) {
                estudianteNew.getHistoriaClinicaCollection().add(historiaClinica);
                estudianteNew = em.merge(estudianteNew);
            }
            if (antPsicosocialSexualNew != null && !antPsicosocialSexualNew.equals(antPsicosocialSexualOld)) {
                HistoriaClinica oldHistoriaClinicaOfAntPsicosocialSexual = antPsicosocialSexualNew.getHistoriaClinica();
                if (oldHistoriaClinicaOfAntPsicosocialSexual != null) {
                    oldHistoriaClinicaOfAntPsicosocialSexual.setAntPsicosocialSexual(null);
                    oldHistoriaClinicaOfAntPsicosocialSexual = em.merge(oldHistoriaClinicaOfAntPsicosocialSexual);
                }
                antPsicosocialSexualNew.setHistoriaClinica(historiaClinica);
                antPsicosocialSexualNew = em.merge(antPsicosocialSexualNew);
            }
            if (antPersMadreNew != null && !antPersMadreNew.equals(antPersMadreOld)) {
                HistoriaClinica oldHistoriaClinicaOfAntPersMadre = antPersMadreNew.getHistoriaClinica();
                if (oldHistoriaClinicaOfAntPersMadre != null) {
                    oldHistoriaClinicaOfAntPersMadre.setAntPersMadre(null);
                    oldHistoriaClinicaOfAntPersMadre = em.merge(oldHistoriaClinicaOfAntPersMadre);
                }
                antPersMadreNew.setHistoriaClinica(historiaClinica);
                antPersMadreNew = em.merge(antPersMadreNew);
            }
            if (antPsicomotrizLenguajeNew != null && !antPsicomotrizLenguajeNew.equals(antPsicomotrizLenguajeOld)) {
                HistoriaClinica oldHistoriaClinicaOfAntPsicomotrizLenguaje = antPsicomotrizLenguajeNew.getHistoriaClinica();
                if (oldHistoriaClinicaOfAntPsicomotrizLenguaje != null) {
                    oldHistoriaClinicaOfAntPsicomotrizLenguaje.setAntPsicomotrizLenguaje(null);
                    oldHistoriaClinicaOfAntPsicomotrizLenguaje = em.merge(oldHistoriaClinicaOfAntPsicomotrizLenguaje);
                }
                antPsicomotrizLenguajeNew.setHistoriaClinica(historiaClinica);
                antPsicomotrizLenguajeNew = em.merge(antPsicomotrizLenguajeNew);
            }
            if (escolaridadNew != null && !escolaridadNew.equals(escolaridadOld)) {
                HistoriaClinica oldHistoriaClinicaOfEscolaridad = escolaridadNew.getHistoriaClinica();
                if (oldHistoriaClinicaOfEscolaridad != null) {
                    oldHistoriaClinicaOfEscolaridad.setEscolaridad(null);
                    oldHistoriaClinicaOfEscolaridad = em.merge(oldHistoriaClinicaOfEscolaridad);
                }
                escolaridadNew.setHistoriaClinica(historiaClinica);
                escolaridadNew = em.merge(escolaridadNew);
            }
            if (antRecienNacidoNew != null && !antRecienNacidoNew.equals(antRecienNacidoOld)) {
                HistoriaClinica oldHistoriaClinicaOfAntRecienNacido = antRecienNacidoNew.getHistoriaClinica();
                if (oldHistoriaClinicaOfAntRecienNacido != null) {
                    oldHistoriaClinicaOfAntRecienNacido.setAntRecienNacido(null);
                    oldHistoriaClinicaOfAntRecienNacido = em.merge(oldHistoriaClinicaOfAntRecienNacido);
                }
                antRecienNacidoNew.setHistoriaClinica(historiaClinica);
                antRecienNacidoNew = em.merge(antRecienNacidoNew);
            }
            if (antNeonatalNew != null && !antNeonatalNew.equals(antNeonatalOld)) {
                HistoriaClinica oldHistoriaClinicaOfAntNeonatal = antNeonatalNew.getHistoriaClinica();
                if (oldHistoriaClinicaOfAntNeonatal != null) {
                    oldHistoriaClinicaOfAntNeonatal.setAntNeonatal(null);
                    oldHistoriaClinicaOfAntNeonatal = em.merge(oldHistoriaClinicaOfAntNeonatal);
                }
                antNeonatalNew.setHistoriaClinica(historiaClinica);
                antNeonatalNew = em.merge(antNeonatalNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = historiaClinica.getHicId();
                if (findById(id) == null) {
                    throw new NonexistentEntityException("The historiaClinica with id " + id + " no longer exists.");
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
        if (entity instanceof HistoriaClinica) {
            HistoriaClinica historiaClinica = (HistoriaClinica) entity;
            try {
                update(historiaClinica);
                return (E) historiaClinica;
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(HistoriaClinicaJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(HistoriaClinicaJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (Exception ex) {
                Logger.getLogger(HistoriaClinicaJpaDao.class.getName()).log(Level.SEVERE, null, ex);
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
            HistoriaClinica historiaClinica;
            try {
                historiaClinica = em.getReference(HistoriaClinica.class, id);
                historiaClinica.getHicId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historiaClinica with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            AntPsicosocialSexual antPsicosocialSexualOrphanCheck = historiaClinica.getAntPsicosocialSexual();
            if (antPsicosocialSexualOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HistoriaClinica (" + historiaClinica + ") cannot be destroyed since the AntPsicosocialSexual " + antPsicosocialSexualOrphanCheck + " in its antPsicosocialSexual field has a non-nullable historiaClinica field.");
            }
            AntPersMadre antPersMadreOrphanCheck = historiaClinica.getAntPersMadre();
            if (antPersMadreOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HistoriaClinica (" + historiaClinica + ") cannot be destroyed since the AntPersMadre " + antPersMadreOrphanCheck + " in its antPersMadre field has a non-nullable historiaClinica field.");
            }
            AntPsicomotrizLenguaje antPsicomotrizLenguajeOrphanCheck = historiaClinica.getAntPsicomotrizLenguaje();
            if (antPsicomotrizLenguajeOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HistoriaClinica (" + historiaClinica + ") cannot be destroyed since the AntPsicomotrizLenguaje " + antPsicomotrizLenguajeOrphanCheck + " in its antPsicomotrizLenguaje field has a non-nullable historiaClinica field.");
            }
            Escolaridad escolaridadOrphanCheck = historiaClinica.getEscolaridad();
            if (escolaridadOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HistoriaClinica (" + historiaClinica + ") cannot be destroyed since the Escolaridad " + escolaridadOrphanCheck + " in its escolaridad field has a non-nullable historiaClinica field.");
            }
            AntRecienNacido antRecienNacidoOrphanCheck = historiaClinica.getAntRecienNacido();
            if (antRecienNacidoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HistoriaClinica (" + historiaClinica + ") cannot be destroyed since the AntRecienNacido " + antRecienNacidoOrphanCheck + " in its antRecienNacido field has a non-nullable historiaClinica field.");
            }
            AntNeonatal antNeonatalOrphanCheck = historiaClinica.getAntNeonatal();
            if (antNeonatalOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HistoriaClinica (" + historiaClinica + ") cannot be destroyed since the AntNeonatal " + antNeonatalOrphanCheck + " in its antNeonatal field has a non-nullable historiaClinica field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Estudiante estudiante = historiaClinica.getEstudiante();
            if (estudiante != null) {
                estudiante.getHistoriaClinicaCollection().remove(historiaClinica);
                estudiante = em.merge(estudiante);
            }
            em.remove(historiaClinica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public <E extends Serializable> void remove(E entity) {
        if (entity instanceof HistoriaClinica) {
            try {
                remove(((HistoriaClinica) entity).getHicId());
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(HistoriaClinicaJpaDao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(HistoriaClinicaJpaDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public HistoriaClinica getHistoriaClinicaByEstudiante(Estudiante estudiante) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createQuery("SELECT hic FROM HistoriaClinica hic WHERE hic.estudiante = :hicEstudiante");
            q.setParameter("hicEstudiante", estudiante);
            return (HistoriaClinica) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<HistoriaClinica> getHistoriasClinicasByFechaCreacion(Date fechaCreacion) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("HistoriaClinica.findByHicFechaCreacion");
            q.setParameter("hicFechaCreacion", fechaCreacion);
            return (List<HistoriaClinica>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<HistoriaClinica> getHistoriasClinicasByStatus(char status) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("HistoriaClinica.findByHicStatus");
            q.setParameter("hicStatus", status);
            return (List<HistoriaClinica>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
