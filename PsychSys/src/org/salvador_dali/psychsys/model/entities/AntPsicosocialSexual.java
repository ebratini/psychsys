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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
@Entity
@Table(name = "Ant_Psicosocial_Sexual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AntPsicosocialSexual.findAll", query = "SELECT a FROM AntPsicosocialSexual a"),
    @NamedQuery(name = "AntPsicosocialSexual.findByHicId", query = "SELECT a FROM AntPsicosocialSexual a WHERE a.hicId = :hicId"),
    @NamedQuery(name = "AntPsicosocialSexual.findByApsEsfinterAnal", query = "SELECT a FROM AntPsicosocialSexual a WHERE a.apsEsfinterAnal = :apsEsfinterAnal"),
    @NamedQuery(name = "AntPsicosocialSexual.findByApsEsfinterVecical", query = "SELECT a FROM AntPsicosocialSexual a WHERE a.apsEsfinterVecical = :apsEsfinterVecical"),
    @NamedQuery(name = "AntPsicosocialSexual.findByApsMenarquia", query = "SELECT a FROM AntPsicosocialSexual a WHERE a.apsMenarquia = :apsMenarquia"),
    @NamedQuery(name = "AntPsicosocialSexual.findByApsEyaculacion", query = "SELECT a FROM AntPsicosocialSexual a WHERE a.apsEyaculacion = :apsEyaculacion")})
public class AntPsicosocialSexual implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "hic_id")
    private Integer hicId;
    @Column(name = "aps_esfinter_anal")
    private Integer apsEsfinterAnal;
    @Column(name = "aps_esfinter_vecical")
    private Integer apsEsfinterVecical;
    @Lob
    @Column(name = "aps_habitos_alimenticios")
    private String apsHabitosAlimenticios;
    @Column(name = "aps_menarquia")
    private Integer apsMenarquia;
    @Column(name = "aps_eyaculacion")
    private Integer apsEyaculacion;
    @Lob
    @Column(name = "aps_curiosidad_sexual_actual")
    private String apsCuriosidadSexualActual;
    @Lob
    @Column(name = "aps_suenio")
    private String apsSuenio;
    @JoinColumn(name = "hic_id", referencedColumnName = "hic_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private HistoriaClinica historiaClinica;

    public AntPsicosocialSexual() {
    }

    public AntPsicosocialSexual(Integer hicId) {
        this.hicId = hicId;
    }

    public Integer getHicId() {
        return hicId;
    }

    public void setHicId(Integer hicId) {
        this.hicId = hicId;
    }

    public Integer getApsEsfinterAnal() {
        return apsEsfinterAnal;
    }

    public void setApsEsfinterAnal(Integer apsEsfinterAnal) {
        this.apsEsfinterAnal = apsEsfinterAnal;
    }

    public Integer getApsEsfinterVecical() {
        return apsEsfinterVecical;
    }

    public void setApsEsfinterVecical(Integer apsEsfinterVecical) {
        this.apsEsfinterVecical = apsEsfinterVecical;
    }

    public String getApsHabitosAlimenticios() {
        return apsHabitosAlimenticios;
    }

    public void setApsHabitosAlimenticios(String apsHabitosAlimenticios) {
        this.apsHabitosAlimenticios = apsHabitosAlimenticios;
    }

    public Integer getApsMenarquia() {
        return apsMenarquia;
    }

    public void setApsMenarquia(Integer apsMenarquia) {
        this.apsMenarquia = apsMenarquia;
    }

    public Integer getApsEyaculacion() {
        return apsEyaculacion;
    }

    public void setApsEyaculacion(Integer apsEyaculacion) {
        this.apsEyaculacion = apsEyaculacion;
    }

    public String getApsCuriosidadSexualActual() {
        return apsCuriosidadSexualActual;
    }

    public void setApsCuriosidadSexualActual(String apsCuriosidadSexualActual) {
        this.apsCuriosidadSexualActual = apsCuriosidadSexualActual;
    }

    public String getApsSuenio() {
        return apsSuenio;
    }

    public void setApsSuenio(String apsSuenio) {
        this.apsSuenio = apsSuenio;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
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
        if (!(object instanceof AntPsicosocialSexual)) {
            return false;
        }
        AntPsicosocialSexual other = (AntPsicosocialSexual) object;
        if ((this.hicId == null && other.hicId != null) || (this.hicId != null && !this.hicId.equals(other.hicId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.salvador_dali.psychsys.model.entities.AntPsicosocialSexual[ hicId=" + hicId + " ]";
    }
    
}
