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
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
@Entity
@Table(name = "Tutores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tutor.findAll", query = "SELECT t FROM Tutor t"),
    @NamedQuery(name = "Tutor.findByTutId", query = "SELECT t FROM Tutor t WHERE t.tutId = :tutId"),
    @NamedQuery(name = "Tutor.findByTutDni", query = "SELECT t FROM Tutor t WHERE t.tutDni = :tutDni"),
    @NamedQuery(name = "Tutor.findByTutTipoDni", query = "SELECT t FROM Tutor t WHERE t.tutTipoDni = :tutTipoDni"),
    @NamedQuery(name = "Tutor.findByTutPrimerApellido", query = "SELECT t FROM Tutor t WHERE lower(t.tutPrimerApellido) = :tutPrimerApellido"),
    @NamedQuery(name = "Tutor.findByTutSegundoApellido", query = "SELECT t FROM Tutor t WHERE t.tutSegundoApellido = :tutSegundoApellido"),
    @NamedQuery(name = "Tutor.findByTutPrimerNombre", query = "SELECT t FROM Tutor t WHERE lower(t.tutPrimerNombre) = :tutPrimerNombre"),
    @NamedQuery(name = "Tutor.findByTutSegundoNombre", query = "SELECT t FROM Tutor t WHERE t.tutSegundoNombre = :tutSegundoNombre"),
    @NamedQuery(name = "Tutor.findByTutTelefono", query = "SELECT t FROM Tutor t WHERE t.tutTelefono = :tutTelefono"),
    @NamedQuery(name = "Tutor.findByTutDireccion", query = "SELECT t FROM Tutor t WHERE t.tutDireccion = :tutDireccion"),
    @NamedQuery(name = "Tutor.findByTutEmail", query = "SELECT t FROM Tutor t WHERE t.tutEmail = :tutEmail"),
    @NamedQuery(name = "Tutor.findByTutNacionalidad", query = "SELECT t FROM Tutor t WHERE t.tutNacionalidad = :tutNacionalidad"),
    @NamedQuery(name = "Tutor.findByTutGenero", query = "SELECT t FROM Tutor t WHERE t.tutGenero = :tutGenero"),
    @NamedQuery(name = "Tutor.findByTutEstadoCivil", query = "SELECT t FROM Tutor t WHERE t.tutEstadoCivil = :tutEstadoCivil"),
    @NamedQuery(name = "Tutor.findByTutStatus", query = "SELECT t FROM Tutor t WHERE t.tutStatus = :tutStatus")})
public class Tutor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tut_id")
    private Integer tutId;
    @Basic(optional = false)
    @Column(name = "tut_dni")
    private String tutDni;
    @Basic(optional = false)
    @Column(name = "tut_tipo_dni")
    private String tutTipoDni;
    @Basic(optional = false)
    @Column(name = "tut_primer_apellido")
    private String tutPrimerApellido;
    @Basic(optional = false)
    @Column(name = "tut_segundo_apellido")
    private String tutSegundoApellido;
    @Basic(optional = false)
    @Column(name = "tut_primer_nombre")
    private String tutPrimerNombre;
    @Column(name = "tut_segundo_nombre")
    private String tutSegundoNombre;
    @Column(name = "tut_telefono")
    private String tutTelefono;
    @Basic(optional = false)
    @Column(name = "tut_direccion")
    private String tutDireccion;
    @Column(name = "tut_email")
    private String tutEmail;
    @Basic(optional = false)
    @Column(name = "tut_nacionalidad")
    private String tutNacionalidad;
    @Basic(optional = false)
    @Column(name = "tut_genero")
    private char tutGenero;
    @Basic(optional = false)
    @Column(name = "tut_estado_civil")
    private String tutEstadoCivil;
    @Basic(optional = false)
    @Column(name = "tut_status")
    private char tutStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutor")
    private Collection<TutorEstudiante> tutorEstudianteCollection;

    public Tutor() {
    }

    public Tutor(Integer tutId) {
        this.tutId = tutId;
    }

    public Tutor(Integer tutId, String tutDni, String tutTipoDni, String tutPrimerApellido, String tutSegundoApellido, String tutPrimerNombre, String tutDireccion, String tutNacionalidad, char tutGenero, String tutEstadoCivil, char tutStatus) {
        this.tutId = tutId;
        this.tutDni = tutDni;
        this.tutTipoDni = tutTipoDni;
        this.tutPrimerApellido = tutPrimerApellido;
        this.tutSegundoApellido = tutSegundoApellido;
        this.tutPrimerNombre = tutPrimerNombre;
        this.tutDireccion = tutDireccion;
        this.tutNacionalidad = tutNacionalidad;
        this.tutGenero = tutGenero;
        this.tutEstadoCivil = tutEstadoCivil;
        this.tutStatus = tutStatus;
    }

    public Integer getTutId() {
        return tutId;
    }

    public void setTutId(Integer tutId) {
        this.tutId = tutId;
    }

    public String getTutDni() {
        return tutDni;
    }

    public void setTutDni(String tutDni) {
        this.tutDni = tutDni;
    }

    public String getTutTipoDni() {
        return tutTipoDni;
    }

    public void setTutTipoDni(String tutTipoDni) {
        this.tutTipoDni = tutTipoDni;
    }

    public String getTutPrimerApellido() {
        return tutPrimerApellido;
    }

    public void setTutPrimerApellido(String tutPrimerApellido) {
        this.tutPrimerApellido = tutPrimerApellido;
    }

    public String getTutSegundoApellido() {
        return tutSegundoApellido;
    }

    public void setTutSegundoApellido(String tutSegundoApellido) {
        this.tutSegundoApellido = tutSegundoApellido;
    }

    public String getTutPrimerNombre() {
        return tutPrimerNombre;
    }

    public void setTutPrimerNombre(String tutPrimerNombre) {
        this.tutPrimerNombre = tutPrimerNombre;
    }

    public String getTutSegundoNombre() {
        return tutSegundoNombre;
    }

    public void setTutSegundoNombre(String tutSegundoNombre) {
        this.tutSegundoNombre = tutSegundoNombre;
    }

    public String getTutTelefono() {
        return tutTelefono;
    }

    public void setTutTelefono(String tutTelefono) {
        this.tutTelefono = tutTelefono;
    }

    public String getTutDireccion() {
        return tutDireccion;
    }

    public void setTutDireccion(String tutDireccion) {
        this.tutDireccion = tutDireccion;
    }

    public String getTutEmail() {
        return tutEmail;
    }

    public void setTutEmail(String tutEmail) {
        this.tutEmail = tutEmail;
    }

    public String getTutNacionalidad() {
        return tutNacionalidad;
    }

    public void setTutNacionalidad(String tutNacionalidad) {
        this.tutNacionalidad = tutNacionalidad;
    }

    public char getTutGenero() {
        return tutGenero;
    }

    public void setTutGenero(char tutGenero) {
        this.tutGenero = tutGenero;
    }

    public String getTutEstadoCivil() {
        return tutEstadoCivil;
    }

    public void setTutEstadoCivil(String tutEstadoCivil) {
        this.tutEstadoCivil = tutEstadoCivil;
    }

    public char getTutStatus() {
        return tutStatus;
    }

    public void setTutStatus(char tutStatus) {
        this.tutStatus = tutStatus;
    }

    @XmlTransient
    public Collection<TutorEstudiante> getTutorEstudianteCollection() {
        return tutorEstudianteCollection;
    }

    public void setTutorEstudianteCollection(Collection<TutorEstudiante> tutorEstudianteCollection) {
        this.tutorEstudianteCollection = tutorEstudianteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tutId != null ? tutId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tutor)) {
            return false;
        }
        Tutor other = (Tutor) object;
        if ((this.tutId == null && other.tutId != null) || (this.tutId != null && !this.tutId.equals(other.tutId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.salvador_dali.psychsys.model.entities.Tutor[ tutId=" + tutId + " ]";
    }
    
}
