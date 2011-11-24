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
@Table(name = "Ubicacion_Pruebas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UbicacionPrueba.findAll", query = "SELECT u FROM UbicacionPrueba u"),
    @NamedQuery(name = "UbicacionPrueba.findByUbpId", query = "SELECT u FROM UbicacionPrueba u WHERE u.ubpId = :ubpId"),
    @NamedQuery(name = "UbicacionPrueba.findByUbpUrl", query = "SELECT u FROM UbicacionPrueba u WHERE u.ubpUrl = :ubpUrl")})
public class UbicacionPrueba implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ubp_id")
    private Integer ubpId;
    @Basic(optional = false)
    @Column(name = "ubp_url")
    private String ubpUrl;
    @JoinColumn(name = "pps_id", referencedColumnName = "pps_id")
    @ManyToOne(optional = false)
    private PruebaPsicologica pruebaPsicologica;

    public UbicacionPrueba() {
    }

    public UbicacionPrueba(Integer ubpId) {
        this.ubpId = ubpId;
    }

    public UbicacionPrueba(Integer ubpId, String ubpUrl) {
        this.ubpId = ubpId;
        this.ubpUrl = ubpUrl;
    }

    public Integer getUbpId() {
        return ubpId;
    }

    public void setUbpId(Integer ubpId) {
        this.ubpId = ubpId;
    }

    public String getUbpUrl() {
        return ubpUrl;
    }

    public void setUbpUrl(String ubpUrl) {
        this.ubpUrl = ubpUrl;
    }

    public PruebaPsicologica getPruebaPsicologica() {
        return pruebaPsicologica;
    }

    public void setPruebaPsicologica(PruebaPsicologica pruebaPsicologica) {
        this.pruebaPsicologica = pruebaPsicologica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ubpId != null ? ubpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UbicacionPrueba)) {
            return false;
        }
        UbicacionPrueba other = (UbicacionPrueba) object;
        if ((this.ubpId == null && other.ubpId != null) || (this.ubpId != null && !this.ubpId.equals(other.ubpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.salvador_dali.psychsys.model.entities.UbicacionPrueba[ ubpId=" + ubpId + " ]";
    }
    
}
