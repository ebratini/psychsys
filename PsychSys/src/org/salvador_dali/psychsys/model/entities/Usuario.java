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
@Table(name = "Usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsrId", query = "SELECT u FROM Usuario u WHERE u.usrId = :usrId"),
    @NamedQuery(name = "Usuario.findByUsrLogin", query = "SELECT u FROM Usuario u WHERE u.usrLogin = :usrLogin"),
    @NamedQuery(name = "Usuario.findByUsrPassword", query = "SELECT u FROM Usuario u WHERE u.usrPassword = :usrPassword"),
    @NamedQuery(name = "Usuario.findByUsrFechaCreacion", query = "SELECT u FROM Usuario u WHERE u.usrFechaCreacion = :usrFechaCreacion"),
    @NamedQuery(name = "Usuario.findByUsrVerificado", query = "SELECT u FROM Usuario u WHERE u.usrVerificado = :usrVerificado"),
    @NamedQuery(name = "Usuario.findByUsrUpdateBy", query = "SELECT u FROM Usuario u WHERE u.usrUpdateBy = :usrUpdateBy"),
    @NamedQuery(name = "Usuario.findByUsrUpdateDate", query = "SELECT u FROM Usuario u WHERE u.usrUpdateDate = :usrUpdateDate"),
    @NamedQuery(name = "Usuario.findByUsrStatus", query = "SELECT u FROM Usuario u WHERE u.usrStatus = :usrStatus")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "usr_id")
    private Integer usrId;
    @Basic(optional = false)
    @Column(name = "usr_login")
    private String usrLogin;
    @Basic(optional = false)
    @Column(name = "usr_password")
    private String usrPassword;
    @Basic(optional = false)
    @Column(name = "usr_fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date usrFechaCreacion;
    @Basic(optional = false)
    @Column(name = "usr_verificado")
    private char usrVerificado;
    @Basic(optional = false)
    @Column(name = "usr_update_by")
    private String usrUpdateBy;
    @Basic(optional = false)
    @Column(name = "usr_update_date")
    @Temporal(TemporalType.DATE)
    private Date usrUpdateDate;
    @Basic(optional = false)
    @Column(name = "usr_status")
    private char usrStatus;
    @JoinColumn(name = "rol_id", referencedColumnName = "rol_id")
    @ManyToOne(optional = false)
    private Rol rol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<Referimiento> referimientoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<Bitacora> bitacoraCollection;

    public Usuario() {
    }

    public Usuario(Integer usrId) {
        this.usrId = usrId;
    }

    public Usuario(Integer usrId, String usrLogin, String usrPassword, Date usrFechaCreacion, char usrVerificado, String usrUpdateBy, Date usrUpdateDate, char usrStatus) {
        this.usrId = usrId;
        this.usrLogin = usrLogin;
        this.usrPassword = usrPassword;
        this.usrFechaCreacion = usrFechaCreacion;
        this.usrVerificado = usrVerificado;
        this.usrUpdateBy = usrUpdateBy;
        this.usrUpdateDate = usrUpdateDate;
        this.usrStatus = usrStatus;
    }

    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }

    public String getUsrLogin() {
        return usrLogin;
    }

    public void setUsrLogin(String usrLogin) {
        this.usrLogin = usrLogin;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    public Date getUsrFechaCreacion() {
        return usrFechaCreacion;
    }

    public void setUsrFechaCreacion(Date usrFechaCreacion) {
        this.usrFechaCreacion = usrFechaCreacion;
    }

    public char getUsrVerificado() {
        return usrVerificado;
    }

    public void setUsrVerificado(char usrVerificado) {
        this.usrVerificado = usrVerificado;
    }

    public String getUsrUpdateBy() {
        return usrUpdateBy;
    }

    public void setUsrUpdateBy(String usrUpdateBy) {
        this.usrUpdateBy = usrUpdateBy;
    }

    public Date getUsrUpdateDate() {
        return usrUpdateDate;
    }

    public void setUsrUpdateDate(Date usrUpdateDate) {
        this.usrUpdateDate = usrUpdateDate;
    }

    public char getUsrStatus() {
        return usrStatus;
    }

    public void setUsrStatus(char usrStatus) {
        this.usrStatus = usrStatus;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @XmlTransient
    public Collection<Referimiento> getReferimientoCollection() {
        return referimientoCollection;
    }

    public void setReferimientoCollection(Collection<Referimiento> referimientoCollection) {
        this.referimientoCollection = referimientoCollection;
    }

    @XmlTransient
    public Collection<Bitacora> getBitacoraCollection() {
        return bitacoraCollection;
    }

    public void setBitacoraCollection(Collection<Bitacora> bitacoraCollection) {
        this.bitacoraCollection = bitacoraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usrId != null ? usrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usrId == null && other.usrId != null) || (this.usrId != null && !this.usrId.equals(other.usrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.salvador_dali.psychsys.model.entities.Usuario[ usrId=" + usrId + " ]";
    }
    
}
