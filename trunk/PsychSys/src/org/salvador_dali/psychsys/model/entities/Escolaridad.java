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
@Table(name = "Escolaridad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Escolaridad.findAll", query = "SELECT e FROM Escolaridad e"),
    @NamedQuery(name = "Escolaridad.findByHicId", query = "SELECT e FROM Escolaridad e WHERE e.hicId = :hicId"),
    @NamedQuery(name = "Escolaridad.findByEscEdadInicioEscolaridad", query = "SELECT e FROM Escolaridad e WHERE e.escEdadInicioEscolaridad = :escEdadInicioEscolaridad"),
    @NamedQuery(name = "Escolaridad.findByEscNivelEscolar", query = "SELECT e FROM Escolaridad e WHERE e.escNivelEscolar = :escNivelEscolar"),
    @NamedQuery(name = "Escolaridad.findByEscGradoEscolarActual", query = "SELECT e FROM Escolaridad e WHERE e.escGradoEscolarActual = :escGradoEscolarActual"),
    @NamedQuery(name = "Escolaridad.findByEscTipoEscuelaActual", query = "SELECT e FROM Escolaridad e WHERE e.escTipoEscuelaActual = :escTipoEscuelaActual"),
    @NamedQuery(name = "Escolaridad.findByEscRecuperacion", query = "SELECT e FROM Escolaridad e WHERE e.escRecuperacion = :escRecuperacion"),
    @NamedQuery(name = "Escolaridad.findByEscHaReprobado", query = "SELECT e FROM Escolaridad e WHERE e.escHaReprobado = :escHaReprobado"),
    @NamedQuery(name = "Escolaridad.findByEscCantVecesReprobacion", query = "SELECT e FROM Escolaridad e WHERE e.escCantVecesReprobacion = :escCantVecesReprobacion"),
    @NamedQuery(name = "Escolaridad.findByEscCambioEscuela", query = "SELECT e FROM Escolaridad e WHERE e.escCambioEscuela = :escCambioEscuela")})
public class Escolaridad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "hic_id")
    private Integer hicId;
    @Basic(optional = false)
    @Column(name = "esc_edad_inicio_escolaridad")
    private int escEdadInicioEscolaridad;
    @Basic(optional = false)
    @Column(name = "esc_nivel_escolar")
    private String escNivelEscolar;
    @Basic(optional = false)
    @Column(name = "esc_grado_escolar_actual")
    private int escGradoEscolarActual;
    @Basic(optional = false)
    @Column(name = "esc_tipo_escuela_actual")
    private String escTipoEscuelaActual;
    @Basic(optional = false)
    @Column(name = "esc_recuperacion")
    private char escRecuperacion;
    @Basic(optional = false)
    @Column(name = "esc_ha_reprobado")
    private char escHaReprobado;
    @Basic(optional = false)
    @Column(name = "esc_cant_veces_reprobacion")
    private int escCantVecesReprobacion;
    @Lob
    @Column(name = "esc_rendimiento_actual")
    private String escRendimientoActual;
    @Basic(optional = false)
    @Column(name = "esc_cambio_escuela")
    private char escCambioEscuela;
    @Lob
    @Column(name = "esc_razon_cambio_escuela")
    private String escRazonCambioEscuela;
    @JoinColumn(name = "hic_id", referencedColumnName = "hic_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private HistoriaClinica historiaClinica;

    public Escolaridad() {
    }

    public Escolaridad(Integer hicId) {
        this.hicId = hicId;
    }

    public Escolaridad(Integer hicId, int escEdadInicioEscolaridad, String escNivelEscolar, int escGradoEscolarActual, String escTipoEscuelaActual, char escRecuperacion, char escHaReprobado, int escCantVecesReprobacion, char escCambioEscuela) {
        this.hicId = hicId;
        this.escEdadInicioEscolaridad = escEdadInicioEscolaridad;
        this.escNivelEscolar = escNivelEscolar;
        this.escGradoEscolarActual = escGradoEscolarActual;
        this.escTipoEscuelaActual = escTipoEscuelaActual;
        this.escRecuperacion = escRecuperacion;
        this.escHaReprobado = escHaReprobado;
        this.escCantVecesReprobacion = escCantVecesReprobacion;
        this.escCambioEscuela = escCambioEscuela;
    }

    public Integer getHicId() {
        return hicId;
    }

    public void setHicId(Integer hicId) {
        this.hicId = hicId;
    }

    public int getEscEdadInicioEscolaridad() {
        return escEdadInicioEscolaridad;
    }

    public void setEscEdadInicioEscolaridad(int escEdadInicioEscolaridad) {
        this.escEdadInicioEscolaridad = escEdadInicioEscolaridad;
    }

    public String getEscNivelEscolar() {
        return escNivelEscolar;
    }

    public void setEscNivelEscolar(String escNivelEscolar) {
        this.escNivelEscolar = escNivelEscolar;
    }

    public int getEscGradoEscolarActual() {
        return escGradoEscolarActual;
    }

    public void setEscGradoEscolarActual(int escGradoEscolarActual) {
        this.escGradoEscolarActual = escGradoEscolarActual;
    }

    public String getEscTipoEscuelaActual() {
        return escTipoEscuelaActual;
    }

    public void setEscTipoEscuelaActual(String escTipoEscuelaActual) {
        this.escTipoEscuelaActual = escTipoEscuelaActual;
    }

    public char getEscRecuperacion() {
        return escRecuperacion;
    }

    public void setEscRecuperacion(char escRecuperacion) {
        this.escRecuperacion = escRecuperacion;
    }

    public char getEscHaReprobado() {
        return escHaReprobado;
    }

    public void setEscHaReprobado(char escHaReprobado) {
        this.escHaReprobado = escHaReprobado;
    }

    public int getEscCantVecesReprobacion() {
        return escCantVecesReprobacion;
    }

    public void setEscCantVecesReprobacion(int escCantVecesReprobacion) {
        this.escCantVecesReprobacion = escCantVecesReprobacion;
    }

    public String getEscRendimientoActual() {
        return escRendimientoActual;
    }

    public void setEscRendimientoActual(String escRendimientoActual) {
        this.escRendimientoActual = escRendimientoActual;
    }

    public char getEscCambioEscuela() {
        return escCambioEscuela;
    }

    public void setEscCambioEscuela(char escCambioEscuela) {
        this.escCambioEscuela = escCambioEscuela;
    }

    public String getEscRazonCambioEscuela() {
        return escRazonCambioEscuela;
    }

    public void setEscRazonCambioEscuela(String escRazonCambioEscuela) {
        this.escRazonCambioEscuela = escRazonCambioEscuela;
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
        if (!(object instanceof Escolaridad)) {
            return false;
        }
        Escolaridad other = (Escolaridad) object;
        if ((this.hicId == null && other.hicId != null) || (this.hicId != null && !this.hicId.equals(other.hicId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.salvador_dali.psychsys.model.entities.Escolaridad[ hicId=" + hicId + " ]";
    }
    
}
