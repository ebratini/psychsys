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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
@Entity
@Table(name = "Pruebas_Psicologicas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PruebasPsicologica.findAll", query = "SELECT p FROM PruebasPsicologica p"),
    @NamedQuery(name = "PruebasPsicologica.findByPpsId", query = "SELECT p FROM PruebasPsicologica p WHERE p.ppsId = :ppsId"),
    @NamedQuery(name = "PruebasPsicologica.findByPpsFechaAplicacion", query = "SELECT p FROM PruebasPsicologica p WHERE p.ppsFechaAplicacion = :ppsFechaAplicacion"),
    @NamedQuery(name = "PruebasPsicologica.findByPpsNombrePrueba", query = "SELECT p FROM PruebasPsicologica p WHERE p.ppsNombrePrueba = :ppsNombrePrueba"),
    @NamedQuery(name = "PruebasPsicologica.findByPpsCorrecionAutomatica", query = "SELECT p FROM PruebasPsicologica p WHERE p.ppsCorrecionAutomatica = :ppsCorrecionAutomatica")})
public class PruebasPsicologica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pps_id")
    private Integer ppsId;
    @Basic(optional = false)
    @Column(name = "pps_fecha_aplicacion")
    @Temporal(TemporalType.DATE)
    private Date ppsFechaAplicacion;
    @Basic(optional = false)
    @Column(name = "pps_nombre_prueba")
    private String ppsNombrePrueba;
    @Lob
    @Column(name = "pps_resultados")
    private String ppsResultados;
    @Lob
    @Column(name = "pps_interpretacion")
    private String ppsInterpretacion;
    @Basic(optional = false)
    @Column(name = "pps_correcion_automatica")
    private char ppsCorrecionAutomatica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ppsId")
    private Collection<UbicacionPrueba> ubicacionPruebaCollection;
    @JoinColumn(name = "est_id", referencedColumnName = "est_id")
    @ManyToOne(optional = false)
    private Estudiante estId;
    @JoinColumn(name = "cso_id", referencedColumnName = "cso_id")
    @ManyToOne
    private Caso csoId;

    public PruebasPsicologica() {
    }

    public PruebasPsicologica(Integer ppsId) {
        this.ppsId = ppsId;
    }

    public PruebasPsicologica(Integer ppsId, Date ppsFechaAplicacion, String ppsNombrePrueba, char ppsCorrecionAutomatica) {
        this.ppsId = ppsId;
        this.ppsFechaAplicacion = ppsFechaAplicacion;
        this.ppsNombrePrueba = ppsNombrePrueba;
        this.ppsCorrecionAutomatica = ppsCorrecionAutomatica;
    }

    public Integer getPpsId() {
        return ppsId;
    }

    public void setPpsId(Integer ppsId) {
        this.ppsId = ppsId;
    }

    public Date getPpsFechaAplicacion() {
        return ppsFechaAplicacion;
    }

    public void setPpsFechaAplicacion(Date ppsFechaAplicacion) {
        this.ppsFechaAplicacion = ppsFechaAplicacion;
    }

    public String getPpsNombrePrueba() {
        return ppsNombrePrueba;
    }

    public void setPpsNombrePrueba(String ppsNombrePrueba) {
        this.ppsNombrePrueba = ppsNombrePrueba;
    }

    public String getPpsResultados() {
        return ppsResultados;
    }

    public void setPpsResultados(String ppsResultados) {
        this.ppsResultados = ppsResultados;
    }

    public String getPpsInterpretacion() {
        return ppsInterpretacion;
    }

    public void setPpsInterpretacion(String ppsInterpretacion) {
        this.ppsInterpretacion = ppsInterpretacion;
    }

    public char getPpsCorrecionAutomatica() {
        return ppsCorrecionAutomatica;
    }

    public void setPpsCorrecionAutomatica(char ppsCorrecionAutomatica) {
        this.ppsCorrecionAutomatica = ppsCorrecionAutomatica;
    }

    @XmlTransient
    public Collection<UbicacionPrueba> getUbicacionPruebaCollection() {
        return ubicacionPruebaCollection;
    }

    public void setUbicacionPruebaCollection(Collection<UbicacionPrueba> ubicacionPruebaCollection) {
        this.ubicacionPruebaCollection = ubicacionPruebaCollection;
    }

    public Estudiante getEstId() {
        return estId;
    }

    public void setEstId(Estudiante estId) {
        this.estId = estId;
    }

    public Caso getCsoId() {
        return csoId;
    }

    public void setCsoId(Caso csoId) {
        this.csoId = csoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ppsId != null ? ppsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PruebasPsicologica)) {
            return false;
        }
        PruebasPsicologica other = (PruebasPsicologica) object;
        if ((this.ppsId == null && other.ppsId != null) || (this.ppsId != null && !this.ppsId.equals(other.ppsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.salvador_dali.psychsys.model.entities.PruebasPsicologica[ ppsId=" + ppsId + " ]";
    }
    
}
