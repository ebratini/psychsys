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
@Table(name = "Casos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caso.findAll", query = "SELECT c FROM Caso c"),
    @NamedQuery(name = "Caso.findByCsoId", query = "SELECT c FROM Caso c WHERE c.csoId = :csoId"),
    @NamedQuery(name = "Caso.findByCsoAnioEscolar", query = "SELECT c FROM Caso c WHERE c.csoAnioEscolar = :csoAnioEscolar"),
    @NamedQuery(name = "Caso.findByCsoDiagnosticoDefinitivo", query = "SELECT c FROM Caso c WHERE c.csoDiagnosticoDefinitivo = :csoDiagnosticoDefinitivo"),
    @NamedQuery(name = "Caso.findByCsoEstadoCaso", query = "SELECT c FROM Caso c WHERE c.csoEstadoCaso = :csoEstadoCaso")})
public class Caso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cso_id")
    private Integer csoId;
    @Basic(optional = false)
    @Column(name = "cso_anio_escolar")
    @Temporal(TemporalType.DATE)
    private Date csoAnioEscolar;
    @Lob
    @Column(name = "cso_analisis_resultados_pruebas")
    private String csoAnalisisResultadosPruebas;
    @Basic(optional = false)
    @Lob
    @Column(name = "cso_juicio_clinico")
    private String csoJuicioClinico;
    @Lob
    @Column(name = "cso_diagnostico")
    private String csoDiagnostico;
    @Basic(optional = false)
    @Column(name = "cso_diagnostico_definitivo")
    private char csoDiagnosticoDefinitivo;
    @Lob
    @Column(name = "cso_tratamiento")
    private String csoTratamiento;
    @Lob
    @Column(name = "cso_resumen_evolucion")
    private String csoResumenEvolucion;
    @Lob
    @Column(name = "cso_recomendaciones")
    private String csoRecomendaciones;
    @Basic(optional = false)
    @Column(name = "cso_estado_caso")
    private char csoEstadoCaso;
    @JoinColumn(name = "ref_id", referencedColumnName = "ref_id")
    @ManyToOne(optional = false)
    private Referimiento refId;
    @OneToMany(mappedBy = "csoId")
    private Collection<PruebasPsicologica> pruebasPsicologicaCollection;

    public Caso() {
    }

    public Caso(Integer csoId) {
        this.csoId = csoId;
    }

    public Caso(Integer csoId, Date csoAnioEscolar, String csoJuicioClinico, char csoDiagnosticoDefinitivo, char csoEstadoCaso) {
        this.csoId = csoId;
        this.csoAnioEscolar = csoAnioEscolar;
        this.csoJuicioClinico = csoJuicioClinico;
        this.csoDiagnosticoDefinitivo = csoDiagnosticoDefinitivo;
        this.csoEstadoCaso = csoEstadoCaso;
    }

    public Integer getCsoId() {
        return csoId;
    }

    public void setCsoId(Integer csoId) {
        this.csoId = csoId;
    }

    public Date getCsoAnioEscolar() {
        return csoAnioEscolar;
    }

    public void setCsoAnioEscolar(Date csoAnioEscolar) {
        this.csoAnioEscolar = csoAnioEscolar;
    }

    public String getCsoAnalisisResultadosPruebas() {
        return csoAnalisisResultadosPruebas;
    }

    public void setCsoAnalisisResultadosPruebas(String csoAnalisisResultadosPruebas) {
        this.csoAnalisisResultadosPruebas = csoAnalisisResultadosPruebas;
    }

    public String getCsoJuicioClinico() {
        return csoJuicioClinico;
    }

    public void setCsoJuicioClinico(String csoJuicioClinico) {
        this.csoJuicioClinico = csoJuicioClinico;
    }

    public String getCsoDiagnostico() {
        return csoDiagnostico;
    }

    public void setCsoDiagnostico(String csoDiagnostico) {
        this.csoDiagnostico = csoDiagnostico;
    }

    public char getCsoDiagnosticoDefinitivo() {
        return csoDiagnosticoDefinitivo;
    }

    public void setCsoDiagnosticoDefinitivo(char csoDiagnosticoDefinitivo) {
        this.csoDiagnosticoDefinitivo = csoDiagnosticoDefinitivo;
    }

    public String getCsoTratamiento() {
        return csoTratamiento;
    }

    public void setCsoTratamiento(String csoTratamiento) {
        this.csoTratamiento = csoTratamiento;
    }

    public String getCsoResumenEvolucion() {
        return csoResumenEvolucion;
    }

    public void setCsoResumenEvolucion(String csoResumenEvolucion) {
        this.csoResumenEvolucion = csoResumenEvolucion;
    }

    public String getCsoRecomendaciones() {
        return csoRecomendaciones;
    }

    public void setCsoRecomendaciones(String csoRecomendaciones) {
        this.csoRecomendaciones = csoRecomendaciones;
    }

    public char getCsoEstadoCaso() {
        return csoEstadoCaso;
    }

    public void setCsoEstadoCaso(char csoEstadoCaso) {
        this.csoEstadoCaso = csoEstadoCaso;
    }

    public Referimiento getRefId() {
        return refId;
    }

    public void setRefId(Referimiento refId) {
        this.refId = refId;
    }

    @XmlTransient
    public Collection<PruebasPsicologica> getPruebasPsicologicaCollection() {
        return pruebasPsicologicaCollection;
    }

    public void setPruebasPsicologicaCollection(Collection<PruebasPsicologica> pruebasPsicologicaCollection) {
        this.pruebasPsicologicaCollection = pruebasPsicologicaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (csoId != null ? csoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caso)) {
            return false;
        }
        Caso other = (Caso) object;
        if ((this.csoId == null && other.csoId != null) || (this.csoId != null && !this.csoId.equals(other.csoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.salvador_dali.psychsys.model.entities.Caso[ csoId=" + csoId + " ]";
    }
    
}
