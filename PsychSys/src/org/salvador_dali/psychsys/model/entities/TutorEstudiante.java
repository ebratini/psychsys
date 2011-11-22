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
package org.salvador_dali.psychsys.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
@Entity
@Table(name = "Tutores_Estudiantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TutorEstudiante.findAll", query = "SELECT t FROM TutorEstudiante t"),
    @NamedQuery(name = "TutorEstudiante.findByTutId", query = "SELECT t FROM TutorEstudiante t WHERE t.tutorEstudiantePK.tutId = :tutId"),
    @NamedQuery(name = "TutorEstudiante.findByEstId", query = "SELECT t FROM TutorEstudiante t WHERE t.tutorEstudiantePK.estId = :estId"),
    @NamedQuery(name = "TutorEstudiante.findByTesRelacionFamiliar", query = "SELECT t FROM TutorEstudiante t WHERE t.tesRelacionFamiliar = :tesRelacionFamiliar")})
public class TutorEstudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TutorEstudiantePK tutorEstudiantePK;
    @Basic(optional = false)
    @Column(name = "tes_relacion_familiar")
    private String tesRelacionFamiliar;
    @JoinColumn(name = "tut_id", referencedColumnName = "tut_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tutor tutor;
    @JoinColumn(name = "est_id", referencedColumnName = "est_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estudiante estudiante;

    public TutorEstudiante() {
    }

    public TutorEstudiante(TutorEstudiantePK tutorEstudiantePK) {
        this.tutorEstudiantePK = tutorEstudiantePK;
    }

    public TutorEstudiante(TutorEstudiantePK tutorEstudiantePK, String tesRelacionFamiliar) {
        this.tutorEstudiantePK = tutorEstudiantePK;
        this.tesRelacionFamiliar = tesRelacionFamiliar;
    }

    public TutorEstudiante(int tutId, int estId) {
        this.tutorEstudiantePK = new TutorEstudiantePK(tutId, estId);
    }

    public TutorEstudiantePK getTutorEstudiantePK() {
        return tutorEstudiantePK;
    }

    public void setTutorEstudiantePK(TutorEstudiantePK tutorEstudiantePK) {
        this.tutorEstudiantePK = tutorEstudiantePK;
    }

    public String getTesRelacionFamiliar() {
        return tesRelacionFamiliar;
    }

    public void setTesRelacionFamiliar(String tesRelacionFamiliar) {
        this.tesRelacionFamiliar = tesRelacionFamiliar;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tutorEstudiantePK != null ? tutorEstudiantePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TutorEstudiante)) {
            return false;
        }
        TutorEstudiante other = (TutorEstudiante) object;
        if ((this.tutorEstudiantePK == null && other.tutorEstudiantePK != null) || (this.tutorEstudiantePK != null && !this.tutorEstudiantePK.equals(other.tutorEstudiantePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.salvador_dali.psychsys.model.entities.TutorEstudiante[ tutorEstudiantePK=" + tutorEstudiantePK + " ]";
    }
    
}
