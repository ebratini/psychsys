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
package org.salvador_dali.psychsys.business;

import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.salvador_dali.psychsys.model.HistoriaClinicaDao;
import org.salvador_dali.psychsys.model.JpaDao;
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
public class JpaHistoriaClinicaDao extends JpaDao implements HistoriaClinicaDao {

    @Override
    public Estudiante getHistoriaClinicaByEstudiante(Estudiante estudiante) {
        try {
            Query q = entityManager.createQuery("SELECT hc FROM Caso hc WHERE hc.estudiante = :hicEstudiante");
            q.setParameter("hicEstudiante", estudiante);
            return (Estudiante) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List getHistoriasClinicasByFechaCreacion(Date fechaCreacion) {
        Query q = entityManager.createNamedQuery("HistoriaClinica.findByHicFechaCreacion");
        q.setParameter("hicFechaCreacion", fechaCreacion);
        return q.getResultList();
    }

    @Override
    public List getHistoriasClinicasByStatus(char status) {
        Query q = entityManager.createNamedQuery("HistoriaClinica.findByHicStatus");
        q.setParameter("hicStatus", status);
        return q.getResultList();
    }

    @Override
    public AntPersMadre getAntPersMadre(HistoriaClinica historiaClinica) {
        try {
            Query q = entityManager.createQuery("SELECT apm FROM AntPersMadre apm WHERE apm.historiaClinica = :apmHistoriaClinica");
            q.setParameter("apmHistoriaClinica", historiaClinica);
            return (AntPersMadre) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public AntNeonatal getAntNeonatal(HistoriaClinica historiaClinica) {
        try {
            Query q = entityManager.createQuery("SELECT ane FROM AntNeonatal ane WHERE ane.historiaClinica = :aneHistoriaClinica");
            q.setParameter("aneHistoriaClinica", historiaClinica);
            return (AntNeonatal) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public AntRecienNacido getAntRecienNacido(HistoriaClinica historiaClinica) {
        try {
            Query q = entityManager.createQuery("SELECT arn FROM AntRecienNacido arn WHERE arn.historiaClinica = :arnHistoriaClinica");
            q.setParameter("arnHistoriaClinica", historiaClinica);
            return (AntRecienNacido) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public AntPsicomotrizLenguaje getAntPsicomotrizLenguaje(HistoriaClinica historiaClinica) {
        try {
            Query q = entityManager.createQuery("SELECT apl FROM AntPsicomotrizLenguaje apl WHERE apl.historiaClinica = :aplHistoriaClinica");
            q.setParameter("aplHistoriaClinica", historiaClinica);
            return (AntPsicomotrizLenguaje) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public AntPsicosocialSexual getAntPsicosocialSexual(HistoriaClinica historiaClinica) {
        try {
            Query q = entityManager.createQuery("SELECT aps FROM AntPsicosocialSexual apl WHERE aps.historiaClinica = :apsHistoriaClinica");
            q.setParameter("apsHistoriaClinica", historiaClinica);
            return (AntPsicosocialSexual) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public Escolaridad getEscolaridad(HistoriaClinica historiaClinica) {
        try {
            Query q = entityManager.createQuery("SELECT esc FROM Escolaridad esc WHERE esc.historiaClinica = :escHistoriaClinica");
            q.setParameter("escHistoriaClinica", historiaClinica);
            return (Escolaridad) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
