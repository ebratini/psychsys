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
@Table(name = "Referimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Referimiento.findAll", query = "SELECT r FROM Referimiento r"),
    @NamedQuery(name = "Referimiento.findByRefId", query = "SELECT r FROM Referimiento r WHERE r.refId = :refId"),
    @NamedQuery(name = "Referimiento.findByRefFecha", query = "SELECT r FROM Referimiento r WHERE r.refFecha = :refFecha"),
    @NamedQuery(name = "Referimiento.findByRefAnioEscolar", query = "SELECT r FROM Referimiento r WHERE r.refAnioEscolar = :refAnioEscolar"),
    @NamedQuery(name = "Referimiento.findByRefNombreReferidor", query = "SELECT r FROM Referimiento r WHERE r.refNombreReferidor = :refNombreReferidor"),
    @NamedQuery(name = "Referimiento.findByRefEstadoReferimiento", query = "SELECT r FROM Referimiento r WHERE r.refEstadoReferimiento = :refEstadoReferimiento")})
public class Referimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ref_id")
    private Integer refId;
    @Basic(optional = false)
    @Column(name = "ref_fecha")
    @Temporal(TemporalType.DATE)
    private Date refFecha;
    @Basic(optional = false)
    @Column(name = "ref_anio_escolar")
    private String refAnioEscolar;
    @Basic(optional = false)
    @Column(name = "ref_nombre_referidor")
    private String refNombreReferidor;
    @Basic(optional = false)
    @Lob
    @Column(name = "ref_motivo")
    private String refMotivo;
    @Lob
    @Column(name = "ref_acciones_referidor")
    private String refAccionesReferidor;
    @Lob
    @Column(name = "ref_observaciones_orientador")
    private String refObservacionesOrientador;
    @Basic(optional = false)
    @Column(name = "ref_estado_referimiento")
    private char refEstadoReferimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "refId")
    private Collection<Caso> casoCollection;
    @JoinColumn(name = "usr_id", referencedColumnName = "usr_id")
    @ManyToOne(optional = false)
    private Usuario usrId;
    @JoinColumn(name = "est_id", referencedColumnName = "est_id")
    @ManyToOne(optional = false)
    private Estudiante estId;

    public Referimiento() {
    }

    public Referimiento(Integer refId) {
        this.refId = refId;
    }

    public Referimiento(Integer refId, Date refFecha, String refAnioEscolar, String refNombreReferidor, String refMotivo, char refEstadoReferimiento) {
        this.refId = refId;
        this.refFecha = refFecha;
        this.refAnioEscolar = refAnioEscolar;
        this.refNombreReferidor = refNombreReferidor;
        this.refMotivo = refMotivo;
        this.refEstadoReferimiento = refEstadoReferimiento;
    }

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    public Date getRefFecha() {
        return refFecha;
    }

    public void setRefFecha(Date refFecha) {
        this.refFecha = refFecha;
    }

    public String getRefAnioEscolar() {
        return refAnioEscolar;
    }

    public void setRefAnioEscolar(String refAnioEscolar) {
        this.refAnioEscolar = refAnioEscolar;
    }

    public String getRefNombreReferidor() {
        return refNombreReferidor;
    }

    public void setRefNombreReferidor(String refNombreReferidor) {
        this.refNombreReferidor = refNombreReferidor;
    }

    public String getRefMotivo() {
        return refMotivo;
    }

    public void setRefMotivo(String refMotivo) {
        this.refMotivo = refMotivo;
    }

    public String getRefAccionesReferidor() {
        return refAccionesReferidor;
    }

    public void setRefAccionesReferidor(String refAccionesReferidor) {
        this.refAccionesReferidor = refAccionesReferidor;
    }

    public String getRefObservacionesOrientador() {
        return refObservacionesOrientador;
    }

    public void setRefObservacionesOrientador(String refObservacionesOrientador) {
        this.refObservacionesOrientador = refObservacionesOrientador;
    }

    public char getRefEstadoReferimiento() {
        return refEstadoReferimiento;
    }

    public void setRefEstadoReferimiento(char refEstadoReferimiento) {
        this.refEstadoReferimiento = refEstadoReferimiento;
    }

    @XmlTransient
    public Collection<Caso> getCasoCollection() {
        return casoCollection;
    }

    public void setCasoCollection(Collection<Caso> casoCollection) {
        this.casoCollection = casoCollection;
    }

    public Usuario getUsrId() {
        return usrId;
    }

    public void setUsrId(Usuario usrId) {
        this.usrId = usrId;
    }

    public Estudiante getEstId() {
        return estId;
    }

    public void setEstId(Estudiante estId) {
        this.estId = estId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (refId != null ? refId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Referimiento)) {
            return false;
        }
        Referimiento other = (Referimiento) object;
        if ((this.refId == null && other.refId != null) || (this.refId != null && !this.refId.equals(other.refId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.salvador_dali.psychsys.model.entities.Referimiento[ refId=" + refId + " ]";
    }
    
}
