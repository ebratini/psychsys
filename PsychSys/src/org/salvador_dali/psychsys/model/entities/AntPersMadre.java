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
@Table(name = "Ant_Pers_Madre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AntPersMadre.findAll", query = "SELECT a FROM AntPersMadre a"),
    @NamedQuery(name = "AntPersMadre.findByHicId", query = "SELECT a FROM AntPersMadre a WHERE a.hicId = :hicId"),
    @NamedQuery(name = "AntPersMadre.findByApmEmbarazo", query = "SELECT a FROM AntPersMadre a WHERE a.apmEmbarazo = :apmEmbarazo"),
    @NamedQuery(name = "AntPersMadre.findByApmDuracionEmbarazo", query = "SELECT a FROM AntPersMadre a WHERE a.apmDuracionEmbarazo = :apmDuracionEmbarazo"),
    @NamedQuery(name = "AntPersMadre.findByApmAmenazaAborto", query = "SELECT a FROM AntPersMadre a WHERE a.apmAmenazaAborto = :apmAmenazaAborto"),
    @NamedQuery(name = "AntPersMadre.findByApmIntentoAborto", query = "SELECT a FROM AntPersMadre a WHERE a.apmIntentoAborto = :apmIntentoAborto"),
    @NamedQuery(name = "AntPersMadre.findByApmHabitosToxicos", query = "SELECT a FROM AntPersMadre a WHERE a.apmHabitosToxicos = :apmHabitosToxicos"),
    @NamedQuery(name = "AntPersMadre.findByApmEnfermedadesEmbarazo", query = "SELECT a FROM AntPersMadre a WHERE a.apmEnfermedadesEmbarazo = :apmEnfermedadesEmbarazo"),
    @NamedQuery(name = "AntPersMadre.findByApmEmbarazoDeseado", query = "SELECT a FROM AntPersMadre a WHERE a.apmEmbarazoDeseado = :apmEmbarazoDeseado"),
    @NamedQuery(name = "AntPersMadre.findByApmSexoPreferido", query = "SELECT a FROM AntPersMadre a WHERE a.apmSexoPreferido = :apmSexoPreferido"),
    @NamedQuery(name = "AntPersMadre.findByApmAlteracionesPsiquicas", query = "SELECT a FROM AntPersMadre a WHERE a.apmAlteracionesPsiquicas = :apmAlteracionesPsiquicas")})
public class AntPersMadre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "hic_id")
    private Integer hicId;
    @Basic(optional = false)
    @Column(name = "apm_embarazo")
    private char apmEmbarazo;
    @Column(name = "apm_duracion_embarazo")
    private Integer apmDuracionEmbarazo;
    @Basic(optional = false)
    @Column(name = "apm_amenaza_aborto")
    private char apmAmenazaAborto;
    @Basic(optional = false)
    @Column(name = "apm_intento_aborto")
    private char apmIntentoAborto;
    @Column(name = "apm_habitos_toxicos")
    private String apmHabitosToxicos;
    @Column(name = "apm_enfermedades_embarazo")
    private String apmEnfermedadesEmbarazo;
    @Basic(optional = false)
    @Column(name = "apm_embarazo_deseado")
    private char apmEmbarazoDeseado;
    @Basic(optional = false)
    @Column(name = "apm_sexo_preferido")
    private char apmSexoPreferido;
    @Column(name = "apm_alteraciones_psiquicas")
    private String apmAlteracionesPsiquicas;
    @JoinColumn(name = "hic_id", referencedColumnName = "hic_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private HistoriaClinica historiaClinica;

    public AntPersMadre() {
    }

    public AntPersMadre(Integer hicId) {
        this.hicId = hicId;
    }

    public AntPersMadre(Integer hicId, char apmEmbarazo, char apmAmenazaAborto, char apmIntentoAborto, char apmEmbarazoDeseado, char apmSexoPreferido) {
        this.hicId = hicId;
        this.apmEmbarazo = apmEmbarazo;
        this.apmAmenazaAborto = apmAmenazaAborto;
        this.apmIntentoAborto = apmIntentoAborto;
        this.apmEmbarazoDeseado = apmEmbarazoDeseado;
        this.apmSexoPreferido = apmSexoPreferido;
    }

    public Integer getHicId() {
        return hicId;
    }

    public void setHicId(Integer hicId) {
        this.hicId = hicId;
    }

    public char getApmEmbarazo() {
        return apmEmbarazo;
    }

    public void setApmEmbarazo(char apmEmbarazo) {
        this.apmEmbarazo = apmEmbarazo;
    }

    public Integer getApmDuracionEmbarazo() {
        return apmDuracionEmbarazo;
    }

    public void setApmDuracionEmbarazo(Integer apmDuracionEmbarazo) {
        this.apmDuracionEmbarazo = apmDuracionEmbarazo;
    }

    public char getApmAmenazaAborto() {
        return apmAmenazaAborto;
    }

    public void setApmAmenazaAborto(char apmAmenazaAborto) {
        this.apmAmenazaAborto = apmAmenazaAborto;
    }

    public char getApmIntentoAborto() {
        return apmIntentoAborto;
    }

    public void setApmIntentoAborto(char apmIntentoAborto) {
        this.apmIntentoAborto = apmIntentoAborto;
    }

    public String getApmHabitosToxicos() {
        return apmHabitosToxicos;
    }

    public void setApmHabitosToxicos(String apmHabitosToxicos) {
        this.apmHabitosToxicos = apmHabitosToxicos;
    }

    public String getApmEnfermedadesEmbarazo() {
        return apmEnfermedadesEmbarazo;
    }

    public void setApmEnfermedadesEmbarazo(String apmEnfermedadesEmbarazo) {
        this.apmEnfermedadesEmbarazo = apmEnfermedadesEmbarazo;
    }

    public char getApmEmbarazoDeseado() {
        return apmEmbarazoDeseado;
    }

    public void setApmEmbarazoDeseado(char apmEmbarazoDeseado) {
        this.apmEmbarazoDeseado = apmEmbarazoDeseado;
    }

    public char getApmSexoPreferido() {
        return apmSexoPreferido;
    }

    public void setApmSexoPreferido(char apmSexoPreferido) {
        this.apmSexoPreferido = apmSexoPreferido;
    }

    public String getApmAlteracionesPsiquicas() {
        return apmAlteracionesPsiquicas;
    }

    public void setApmAlteracionesPsiquicas(String apmAlteracionesPsiquicas) {
        this.apmAlteracionesPsiquicas = apmAlteracionesPsiquicas;
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
        if (!(object instanceof AntPersMadre)) {
            return false;
        }
        AntPersMadre other = (AntPersMadre) object;
        if ((this.hicId == null && other.hicId != null) || (this.hicId != null && !this.hicId.equals(other.hicId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.salvador_dali.psychsys.model.entities.AntPersMadre[ hicId=" + hicId + " ]";
    }
    
}
