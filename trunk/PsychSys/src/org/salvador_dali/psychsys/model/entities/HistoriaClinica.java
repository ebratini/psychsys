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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
@Entity
@Table(name = "Historia_Clinica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoriaClinica.findAll", query = "SELECT h FROM HistoriaClinica h"),
    @NamedQuery(name = "HistoriaClinica.findByHicId", query = "SELECT h FROM HistoriaClinica h WHERE h.hicId = :hicId"),
    @NamedQuery(name = "HistoriaClinica.findByHicFechaCreacion", query = "SELECT h FROM HistoriaClinica h WHERE h.hicFechaCreacion = :hicFechaCreacion"),
    @NamedQuery(name = "HistoriaClinica.findByHicUpdateBy", query = "SELECT h FROM HistoriaClinica h WHERE h.hicUpdateBy = :hicUpdateBy"),
    @NamedQuery(name = "HistoriaClinica.findByHicUpdateDate", query = "SELECT h FROM HistoriaClinica h WHERE h.hicUpdateDate = :hicUpdateDate"),
    @NamedQuery(name = "HistoriaClinica.findByHicStatus", query = "SELECT h FROM HistoriaClinica h WHERE h.hicStatus = :hicStatus")})
public class HistoriaClinica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hic_id")
    private Integer hicId;
    @Basic(optional = false)
    @Column(name = "hic_fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date hicFechaCreacion;
    @Basic(optional = false)
    @Column(name = "hic_update_by")
    private String hicUpdateBy;
    @Basic(optional = false)
    @Column(name = "hic_update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hicUpdateDate;
    @Basic(optional = false)
    @Column(name = "hic_status")
    private char hicStatus;
    @JoinColumn(name = "est_id", referencedColumnName = "est_id")
    @ManyToOne(optional = false)
    private Estudiante estudiante;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "historiaClinica")
    private AntPsicosocialSexual antPsicosocialSexual;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "historiaClinica")
    private AntPersMadre antPersMadre;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "historiaClinica")
    private AntPsicomotrizLenguaje antPsicomotrizLenguaje;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "historiaClinica")
    private Escolaridad escolaridad;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "historiaClinica")
    private AntRecienNacido antRecienNacido;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "historiaClinica")
    private AntNeonatal antNeonatal;

    public HistoriaClinica() {
    }

    public HistoriaClinica(Integer hicId) {
        this.hicId = hicId;
    }

    public HistoriaClinica(Integer hicId, Date hicFechaCreacion, String hicUpdateBy, Date hicUpdateDate, char hicStatus) {
        this.hicId = hicId;
        this.hicFechaCreacion = hicFechaCreacion;
        this.hicUpdateBy = hicUpdateBy;
        this.hicUpdateDate = hicUpdateDate;
        this.hicStatus = hicStatus;
    }

    public Integer getHicId() {
        return hicId;
    }

    public void setHicId(Integer hicId) {
        this.hicId = hicId;
    }

    public Date getHicFechaCreacion() {
        return hicFechaCreacion;
    }

    public void setHicFechaCreacion(Date hicFechaCreacion) {
        this.hicFechaCreacion = hicFechaCreacion;
    }

    public String getHicUpdateBy() {
        return hicUpdateBy;
    }

    public void setHicUpdateBy(String hicUpdateBy) {
        this.hicUpdateBy = hicUpdateBy;
    }

    public Date getHicUpdateDate() {
        return hicUpdateDate;
    }

    public void setHicUpdateDate(Date hicUpdateDate) {
        this.hicUpdateDate = hicUpdateDate;
    }

    public char getHicStatus() {
        return hicStatus;
    }

    public void setHicStatus(char hicStatus) {
        this.hicStatus = hicStatus;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public AntPsicosocialSexual getAntPsicosocialSexual() {
        return antPsicosocialSexual;
    }

    public void setAntPsicosocialSexual(AntPsicosocialSexual antPsicosocialSexual) {
        this.antPsicosocialSexual = antPsicosocialSexual;
    }

    public AntPersMadre getAntPersMadre() {
        return antPersMadre;
    }

    public void setAntPersMadre(AntPersMadre antPersMadre) {
        this.antPersMadre = antPersMadre;
    }

    public AntPsicomotrizLenguaje getAntPsicomotrizLenguaje() {
        return antPsicomotrizLenguaje;
    }

    public void setAntPsicomotrizLenguaje(AntPsicomotrizLenguaje antPsicomotrizLenguaje) {
        this.antPsicomotrizLenguaje = antPsicomotrizLenguaje;
    }

    public Escolaridad getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(Escolaridad escolaridad) {
        this.escolaridad = escolaridad;
    }

    public AntRecienNacido getAntRecienNacido() {
        return antRecienNacido;
    }

    public void setAntRecienNacido(AntRecienNacido antRecienNacido) {
        this.antRecienNacido = antRecienNacido;
    }

    public AntNeonatal getAntNeonatal() {
        return antNeonatal;
    }

    public void setAntNeonatal(AntNeonatal antNeonatal) {
        this.antNeonatal = antNeonatal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hicId != null ? hicId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoriaClinica)) {
            return false;
        }
        HistoriaClinica other = (HistoriaClinica) object;
        if ((this.hicId == null && other.hicId != null) || (this.hicId != null && !this.hicId.equals(other.hicId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.salvador_dali.psychsys.model.entities.HistoriaClinica[ hicId=" + hicId + " ]";
    }
    
}
