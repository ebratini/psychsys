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
import java.math.BigDecimal;
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
@Table(name = "Ant_Neonatal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AntNeonatal.findAll", query = "SELECT a FROM AntNeonatal a"),
    @NamedQuery(name = "AntNeonatal.findByHicId", query = "SELECT a FROM AntNeonatal a WHERE a.hicId = :hicId"),
    @NamedQuery(name = "AntNeonatal.findByAneTipoParto", query = "SELECT a FROM AntNeonatal a WHERE a.aneTipoParto = :aneTipoParto"),
    @NamedQuery(name = "AntNeonatal.findByAnePeso", query = "SELECT a FROM AntNeonatal a WHERE a.anePeso = :anePeso"),
    @NamedQuery(name = "AntNeonatal.findByAneLloro", query = "SELECT a FROM AntNeonatal a WHERE a.aneLloro = :aneLloro"),
    @NamedQuery(name = "AntNeonatal.findByAneAsistenciaMedica", query = "SELECT a FROM AntNeonatal a WHERE a.aneAsistenciaMedica = :aneAsistenciaMedica"),
    @NamedQuery(name = "AntNeonatal.findByAneColaboracion", query = "SELECT a FROM AntNeonatal a WHERE a.aneColaboracion = :aneColaboracion")})
public class AntNeonatal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "hic_id")
    private Integer hicId;
    @Basic(optional = false)
    @Column(name = "ane_tipo_parto")
    private String aneTipoParto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ane_peso")
    private BigDecimal anePeso;
    @Column(name = "ane_lloro")
    private Character aneLloro;
    @Basic(optional = false)
    @Column(name = "ane_asistencia_medica")
    private char aneAsistenciaMedica;
    @Column(name = "ane_colaboracion")
    private Character aneColaboracion;
    @Lob
    @Column(name = "ane_estado_general")
    private String aneEstadoGeneral;
    @JoinColumn(name = "hic_id", referencedColumnName = "hic_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private HistoriaClinica historiaClinica;

    public AntNeonatal() {
    }

    public AntNeonatal(Integer hicId) {
        this.hicId = hicId;
    }

    public AntNeonatal(Integer hicId, String aneTipoParto, char aneAsistenciaMedica) {
        this.hicId = hicId;
        this.aneTipoParto = aneTipoParto;
        this.aneAsistenciaMedica = aneAsistenciaMedica;
    }

    public Integer getHicId() {
        return hicId;
    }

    public void setHicId(Integer hicId) {
        this.hicId = hicId;
    }

    public String getAneTipoParto() {
        return aneTipoParto;
    }

    public void setAneTipoParto(String aneTipoParto) {
        this.aneTipoParto = aneTipoParto;
    }

    public BigDecimal getAnePeso() {
        return anePeso;
    }

    public void setAnePeso(BigDecimal anePeso) {
        this.anePeso = anePeso;
    }

    public Character getAneLloro() {
        return aneLloro;
    }

    public void setAneLloro(Character aneLloro) {
        this.aneLloro = aneLloro;
    }

    public char getAneAsistenciaMedica() {
        return aneAsistenciaMedica;
    }

    public void setAneAsistenciaMedica(char aneAsistenciaMedica) {
        this.aneAsistenciaMedica = aneAsistenciaMedica;
    }

    public Character getAneColaboracion() {
        return aneColaboracion;
    }

    public void setAneColaboracion(Character aneColaboracion) {
        this.aneColaboracion = aneColaboracion;
    }

    public String getAneEstadoGeneral() {
        return aneEstadoGeneral;
    }

    public void setAneEstadoGeneral(String aneEstadoGeneral) {
        this.aneEstadoGeneral = aneEstadoGeneral;
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
        if (!(object instanceof AntNeonatal)) {
            return false;
        }
        AntNeonatal other = (AntNeonatal) object;
        if ((this.hicId == null && other.hicId != null) || (this.hicId != null && !this.hicId.equals(other.hicId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.salvador_dali.psychsys.model.entities.AntNeonatal[ hicId=" + hicId + " ]";
    }
    
}
