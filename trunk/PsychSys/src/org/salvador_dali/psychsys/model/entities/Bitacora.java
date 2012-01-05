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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
@Entity
@Table(name = "Bitacora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bitacora.findAll", query = "SELECT b FROM Bitacora b"),
    @NamedQuery(name = "Bitacora.findByBitId", query = "SELECT b FROM Bitacora b WHERE b.bitId = :bitId"),
    @NamedQuery(name = "Bitacora.findByBitFecha", query = "SELECT b FROM Bitacora b WHERE b.bitFecha = :bitFecha"),
    @NamedQuery(name = "Bitacora.findByBitFuente", query = "SELECT b FROM Bitacora b WHERE b.bitFuente = :bitFuente"),
    @NamedQuery(name = "Bitacora.findByBitCategoria", query = "SELECT b FROM Bitacora b WHERE b.bitCategoria = :bitCategoria"),
    @NamedQuery(name = "Bitacora.findByBitDescripcion", query = "SELECT b FROM Bitacora b WHERE b.bitDescripcion = :bitDescripcion")})
public class Bitacora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bit_id")
    private Integer bitId;
    @Basic(optional = false)
    @Column(name = "bit_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bitFecha;
    @Basic(optional = false)
    @Column(name = "bit_fuente")
    private String bitFuente;
    @Basic(optional = false)
    @Column(name = "bit_categoria")
    private String bitCategoria;
    @Basic(optional = false)
    @Column(name = "bit_descripcion")
    private String bitDescripcion;
    @JoinColumn(name = "usr_id", referencedColumnName = "usr_id")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Bitacora() {
    }

    public Bitacora(Integer bitId) {
        this.bitId = bitId;
    }

    public Bitacora(Integer bitId, Date bitFecha, String bitFuente, String bitCategoria, String bitDescripcion) {
        this.bitId = bitId;
        this.bitFecha = bitFecha;
        this.bitFuente = bitFuente;
        this.bitCategoria = bitCategoria;
        this.bitDescripcion = bitDescripcion;
    }

    public Integer getBitId() {
        return bitId;
    }

    public void setBitId(Integer bitId) {
        this.bitId = bitId;
    }

    public Date getBitFecha() {
        return bitFecha;
    }

    public void setBitFecha(Date bitFecha) {
        this.bitFecha = bitFecha;
    }

    public String getBitFuente() {
        return bitFuente;
    }

    public void setBitFuente(String bitFuente) {
        this.bitFuente = bitFuente;
    }

    public String getBitCategoria() {
        return bitCategoria;
    }

    public void setBitCategoria(String bitCategoria) {
        this.bitCategoria = bitCategoria;
    }

    public String getBitDescripcion() {
        return bitDescripcion;
    }

    public void setBitDescripcion(String bitDescripcion) {
        this.bitDescripcion = bitDescripcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bitId != null ? bitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bitacora)) {
            return false;
        }
        Bitacora other = (Bitacora) object;
        if ((this.bitId == null && other.bitId != null) || (this.bitId != null && !this.bitId.equals(other.bitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.salvador_dali.psychsys.model.entities.Bitacora[ bitId=" + bitId + " ]";
    }
}
