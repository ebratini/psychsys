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
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Estudiantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e"),
    @NamedQuery(name = "Estudiante.findByEstId", query = "SELECT e FROM Estudiante e WHERE e.estId = :estId"),
    @NamedQuery(name = "Estudiante.findByEstDni", query = "SELECT e FROM Estudiante e WHERE e.estDni = :estDni"),
    @NamedQuery(name = "Estudiante.findByEstTipoDni", query = "SELECT e FROM Estudiante e WHERE e.estTipoDni = :estTipoDni"),
    @NamedQuery(name = "Estudiante.findByEstPrimerApellido", query = "SELECT e FROM Estudiante e WHERE e.estPrimerApellido = :estPrimerApellido"),
    @NamedQuery(name = "Estudiante.findByEstSegundoApellido", query = "SELECT e FROM Estudiante e WHERE e.estSegundoApellido = :estSegundoApellido"),
    @NamedQuery(name = "Estudiante.findByEstPrimerNombre", query = "SELECT e FROM Estudiante e WHERE e.estPrimerNombre = :estPrimerNombre"),
    @NamedQuery(name = "Estudiante.findByEstSegundoNombre", query = "SELECT e FROM Estudiante e WHERE e.estSegundoNombre = :estSegundoNombre"),
    @NamedQuery(name = "Estudiante.findByEstApodo", query = "SELECT e FROM Estudiante e WHERE e.estApodo = :estApodo"),
    @NamedQuery(name = "Estudiante.findByEstTelefono", query = "SELECT e FROM Estudiante e WHERE e.estTelefono = :estTelefono"),
    @NamedQuery(name = "Estudiante.findByEstDireccion", query = "SELECT e FROM Estudiante e WHERE e.estDireccion = :estDireccion"),
    @NamedQuery(name = "Estudiante.findByEstNacionalidad", query = "SELECT e FROM Estudiante e WHERE e.estNacionalidad = :estNacionalidad"),
    @NamedQuery(name = "Estudiante.findByEstGenero", query = "SELECT e FROM Estudiante e WHERE e.estGenero = :estGenero"),
    @NamedQuery(name = "Estudiante.findByEstFechaNacimiento", query = "SELECT e FROM Estudiante e WHERE e.estFechaNacimiento = :estFechaNacimiento"),
    @NamedQuery(name = "Estudiante.findByEstLugarNacimiento", query = "SELECT e FROM Estudiante e WHERE e.estLugarNacimiento = :estLugarNacimiento"),
    @NamedQuery(name = "Estudiante.findByEstTalla", query = "SELECT e FROM Estudiante e WHERE e.estTalla = :estTalla"),
    @NamedQuery(name = "Estudiante.findByEstPeso", query = "SELECT e FROM Estudiante e WHERE e.estPeso = :estPeso"),
    @NamedQuery(name = "Estudiante.findByEstNivelEscolar", query = "SELECT e FROM Estudiante e WHERE e.estNivelEscolar = :estNivelEscolar"),
    @NamedQuery(name = "Estudiante.findByEstGradoEscolar", query = "SELECT e FROM Estudiante e WHERE e.estGradoEscolar = :estGradoEscolar"),
    @NamedQuery(name = "Estudiante.findByEstEscuelaProcedencia", query = "SELECT e FROM Estudiante e WHERE e.estEscuelaProcedencia = :estEscuelaProcedencia"),
    @NamedQuery(name = "Estudiante.findByEstCantHermanos", query = "SELECT e FROM Estudiante e WHERE e.estCantHermanos = :estCantHermanos"),
    @NamedQuery(name = "Estudiante.findByEstOrdenHermanos", query = "SELECT e FROM Estudiante e WHERE e.estOrdenHermanos = :estOrdenHermanos"),
    @NamedQuery(name = "Estudiante.findByEstStatus", query = "SELECT e FROM Estudiante e WHERE e.estStatus = :estStatus")})
public class Estudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "est_id")
    private Integer estId;
    @Column(name = "est_dni")
    private String estDni;
    @Column(name = "est_tipo_dni")
    private String estTipoDni;
    @Basic(optional = false)
    @Column(name = "est_primer_apellido")
    private String estPrimerApellido;
    @Basic(optional = false)
    @Column(name = "est_segundo_apellido")
    private String estSegundoApellido;
    @Basic(optional = false)
    @Column(name = "est_primer_nombre")
    private String estPrimerNombre;
    @Column(name = "est_segundo_nombre")
    private String estSegundoNombre;
    @Column(name = "est_apodo")
    private String estApodo;
    @Column(name = "est_telefono")
    private String estTelefono;
    @Basic(optional = false)
    @Column(name = "est_direccion")
    private String estDireccion;
    @Basic(optional = false)
    @Column(name = "est_nacionalidad")
    private String estNacionalidad;
    @Basic(optional = false)
    @Column(name = "est_genero")
    private char estGenero;
    @Basic(optional = false)
    @Column(name = "est_fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date estFechaNacimiento;
    @Basic(optional = false)
    @Column(name = "est_lugar_nacimiento")
    private String estLugarNacimiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "est_talla")
    private BigDecimal estTalla;
    @Column(name = "est_peso")
    private BigDecimal estPeso;
    @Basic(optional = false)
    @Column(name = "est_nivel_escolar")
    private String estNivelEscolar;
    @Basic(optional = false)
    @Column(name = "est_grado_escolar")
    private int estGradoEscolar;
    @Column(name = "est_escuela_procedencia")
    private String estEscuelaProcedencia;
    @Basic(optional = false)
    @Column(name = "est_cant_hermanos")
    private int estCantHermanos;
    @Basic(optional = false)
    @Column(name = "est_orden_hermanos")
    private int estOrdenHermanos;
    @Basic(optional = false)
    @Column(name = "est_status")
    private char estStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiante")
    private Collection<HistoriaClinica> historiaClinicaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiante")
    private Collection<TutorEstudiante> tutorEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiante")
    private Collection<PruebaPsicologica> pruebaPsicologicaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiante")
    private Collection<Referimiento> referimientoCollection;

    public Estudiante() {
    }

    public Estudiante(Integer estId) {
        this.estId = estId;
    }

    public Estudiante(Integer estId, String estPrimerApellido, String estSegundoApellido, String estPrimerNombre, String estDireccion, String estNacionalidad, char estGenero, Date estFechaNacimiento, String estLugarNacimiento, String estNivelEscolar, int estGradoEscolar, int estCantHermanos, int estOrdenHermanos, char estStatus) {
        this.estId = estId;
        this.estPrimerApellido = estPrimerApellido;
        this.estSegundoApellido = estSegundoApellido;
        this.estPrimerNombre = estPrimerNombre;
        this.estDireccion = estDireccion;
        this.estNacionalidad = estNacionalidad;
        this.estGenero = estGenero;
        this.estFechaNacimiento = estFechaNacimiento;
        this.estLugarNacimiento = estLugarNacimiento;
        this.estNivelEscolar = estNivelEscolar;
        this.estGradoEscolar = estGradoEscolar;
        this.estCantHermanos = estCantHermanos;
        this.estOrdenHermanos = estOrdenHermanos;
        this.estStatus = estStatus;
    }

    public Integer getEstId() {
        return estId;
    }

    public void setEstId(Integer estId) {
        this.estId = estId;
    }

    public String getEstDni() {
        return estDni;
    }

    public void setEstDni(String estDni) {
        this.estDni = estDni;
    }

    public String getEstTipoDni() {
        return estTipoDni;
    }

    public void setEstTipoDni(String estTipoDni) {
        this.estTipoDni = estTipoDni;
    }

    public String getEstPrimerApellido() {
        return estPrimerApellido;
    }

    public void setEstPrimerApellido(String estPrimerApellido) {
        this.estPrimerApellido = estPrimerApellido;
    }

    public String getEstSegundoApellido() {
        return estSegundoApellido;
    }

    public void setEstSegundoApellido(String estSegundoApellido) {
        this.estSegundoApellido = estSegundoApellido;
    }

    public String getEstPrimerNombre() {
        return estPrimerNombre;
    }

    public void setEstPrimerNombre(String estPrimerNombre) {
        this.estPrimerNombre = estPrimerNombre;
    }

    public String getEstSegundoNombre() {
        return estSegundoNombre;
    }

    public void setEstSegundoNombre(String estSegundoNombre) {
        this.estSegundoNombre = estSegundoNombre;
    }

    public String getEstApodo() {
        return estApodo;
    }

    public void setEstApodo(String estApodo) {
        this.estApodo = estApodo;
    }

    public String getEstTelefono() {
        return estTelefono;
    }

    public void setEstTelefono(String estTelefono) {
        this.estTelefono = estTelefono;
    }

    public String getEstDireccion() {
        return estDireccion;
    }

    public void setEstDireccion(String estDireccion) {
        this.estDireccion = estDireccion;
    }

    public String getEstNacionalidad() {
        return estNacionalidad;
    }

    public void setEstNacionalidad(String estNacionalidad) {
        this.estNacionalidad = estNacionalidad;
    }

    public char getEstGenero() {
        return estGenero;
    }

    public void setEstGenero(char estGenero) {
        this.estGenero = estGenero;
    }

    public Date getEstFechaNacimiento() {
        return estFechaNacimiento;
    }

    public void setEstFechaNacimiento(Date estFechaNacimiento) {
        this.estFechaNacimiento = estFechaNacimiento;
    }

    public String getEstLugarNacimiento() {
        return estLugarNacimiento;
    }

    public void setEstLugarNacimiento(String estLugarNacimiento) {
        this.estLugarNacimiento = estLugarNacimiento;
    }

    public BigDecimal getEstTalla() {
        return estTalla;
    }

    public void setEstTalla(BigDecimal estTalla) {
        this.estTalla = estTalla;
    }

    public BigDecimal getEstPeso() {
        return estPeso;
    }

    public void setEstPeso(BigDecimal estPeso) {
        this.estPeso = estPeso;
    }

    public String getEstNivelEscolar() {
        return estNivelEscolar;
    }

    public void setEstNivelEscolar(String estNivelEscolar) {
        this.estNivelEscolar = estNivelEscolar;
    }

    public int getEstGradoEscolar() {
        return estGradoEscolar;
    }

    public void setEstGradoEscolar(int estGradoEscolar) {
        this.estGradoEscolar = estGradoEscolar;
    }

    public String getEstEscuelaProcedencia() {
        return estEscuelaProcedencia;
    }

    public void setEstEscuelaProcedencia(String estEscuelaProcedencia) {
        this.estEscuelaProcedencia = estEscuelaProcedencia;
    }

    public int getEstCantHermanos() {
        return estCantHermanos;
    }

    public void setEstCantHermanos(int estCantHermanos) {
        this.estCantHermanos = estCantHermanos;
    }

    public int getEstOrdenHermanos() {
        return estOrdenHermanos;
    }

    public void setEstOrdenHermanos(int estOrdenHermanos) {
        this.estOrdenHermanos = estOrdenHermanos;
    }

    public char getEstStatus() {
        return estStatus;
    }

    public void setEstStatus(char estStatus) {
        this.estStatus = estStatus;
    }

    @XmlTransient
    public Collection<HistoriaClinica> getHistoriaClinicaCollection() {
        return historiaClinicaCollection;
    }

    public void setHistoriaClinicaCollection(Collection<HistoriaClinica> historiaClinicaCollection) {
        this.historiaClinicaCollection = historiaClinicaCollection;
    }

    @XmlTransient
    public Collection<TutorEstudiante> getTutorEstudianteCollection() {
        return tutorEstudianteCollection;
    }

    public void setTutorEstudianteCollection(Collection<TutorEstudiante> tutorEstudianteCollection) {
        this.tutorEstudianteCollection = tutorEstudianteCollection;
    }

    @XmlTransient
    public Collection<PruebaPsicologica> getPruebaPsicologicaCollection() {
        return pruebaPsicologicaCollection;
    }

    public void setPruebaPsicologicaCollection(Collection<PruebaPsicologica> pruebaPsicologicaCollection) {
        this.pruebaPsicologicaCollection = pruebaPsicologicaCollection;
    }

    @XmlTransient
    public Collection<Referimiento> getReferimientoCollection() {
        return referimientoCollection;
    }

    public void setReferimientoCollection(Collection<Referimiento> referimientoCollection) {
        this.referimientoCollection = referimientoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estId != null ? estId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.estId == null && other.estId != null) || (this.estId != null && !this.estId.equals(other.estId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.salvador_dali.psychsys.model.entities.Estudiante[ estId=" + estId + " ]";
    }
    
}
