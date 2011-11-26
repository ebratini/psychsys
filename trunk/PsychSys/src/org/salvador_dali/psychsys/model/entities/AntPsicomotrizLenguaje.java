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
@Table(name = "Ant_Psicomotriz_Lenguaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AntPsicomotrizLenguaje.findAll", query = "SELECT a FROM AntPsicomotrizLenguaje a"),
    @NamedQuery(name = "AntPsicomotrizLenguaje.findByHicId", query = "SELECT a FROM AntPsicomotrizLenguaje a WHERE a.hicId = :hicId"),
    @NamedQuery(name = "AntPsicomotrizLenguaje.findByAplEdadInicioMarcha", query = "SELECT a FROM AntPsicomotrizLenguaje a WHERE a.aplEdadInicioMarcha = :aplEdadInicioMarcha")})
public class AntPsicomotrizLenguaje implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "hic_id")
    private Integer hicId;
    @Basic(optional = false)
    @Column(name = "apl_edad_inicio_marcha")
    private int aplEdadInicioMarcha;
    @Lob
    @Column(name = "apl_motricidad_actual")
    private String aplMotricidadActual;
    @Lob
    @Column(name = "apl_primeras_palabras")
    private String aplPrimerasPalabras;
    @Lob
    @Column(name = "apl_vocabulario_actual")
    private String aplVocabularioActual;
    @JoinColumn(name = "hic_id", referencedColumnName = "hic_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private HistoriaClinica historiaClinica;

    public AntPsicomotrizLenguaje() {
    }

    public AntPsicomotrizLenguaje(Integer hicId) {
        this.hicId = hicId;
    }

    public AntPsicomotrizLenguaje(Integer hicId, int aplEdadInicioMarcha) {
        this.hicId = hicId;
        this.aplEdadInicioMarcha = aplEdadInicioMarcha;
    }

    public Integer getHicId() {
        return hicId;
    }

    public void setHicId(Integer hicId) {
        this.hicId = hicId;
    }

    public int getAplEdadInicioMarcha() {
        return aplEdadInicioMarcha;
    }

    public void setAplEdadInicioMarcha(int aplEdadInicioMarcha) {
        this.aplEdadInicioMarcha = aplEdadInicioMarcha;
    }

    public String getAplMotricidadActual() {
        return aplMotricidadActual;
    }

    public void setAplMotricidadActual(String aplMotricidadActual) {
        this.aplMotricidadActual = aplMotricidadActual;
    }

    public String getAplPrimerasPalabras() {
        return aplPrimerasPalabras;
    }

    public void setAplPrimerasPalabras(String aplPrimerasPalabras) {
        this.aplPrimerasPalabras = aplPrimerasPalabras;
    }

    public String getAplVocabularioActual() {
        return aplVocabularioActual;
    }

    public void setAplVocabularioActual(String aplVocabularioActual) {
        this.aplVocabularioActual = aplVocabularioActual;
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
        if (!(object instanceof AntPsicomotrizLenguaje)) {
            return false;
        }
        AntPsicomotrizLenguaje other = (AntPsicomotrizLenguaje) object;
        if ((this.hicId == null && other.hicId != null) || (this.hicId != null && !this.hicId.equals(other.hicId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.salvador_dali.psychsys.model.entities.AntPsicomotrizLenguaje[ hicId=" + hicId + " ]";
    }
    
}
