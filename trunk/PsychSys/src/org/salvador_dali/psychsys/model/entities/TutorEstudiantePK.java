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
import javax.persistence.Embeddable;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
@Embeddable
public class TutorEstudiantePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "tut_id")
    private int tutId;
    @Basic(optional = false)
    @Column(name = "est_id")
    private int estId;

    public TutorEstudiantePK() {
    }

    public TutorEstudiantePK(int tutId, int estId) {
        this.tutId = tutId;
        this.estId = estId;
    }

    public int getTutId() {
        return tutId;
    }

    public void setTutId(int tutId) {
        this.tutId = tutId;
    }

    public int getEstId() {
        return estId;
    }

    public void setEstId(int estId) {
        this.estId = estId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tutId;
        hash += (int) estId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TutorEstudiantePK)) {
            return false;
        }
        TutorEstudiantePK other = (TutorEstudiantePK) object;
        if (this.tutId != other.tutId) {
            return false;
        }
        if (this.estId != other.estId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.salvador_dali.psychsys.model.entities.TutorEstudiantePK[ tutId=" + tutId + ", estId=" + estId + " ]";
    }
    
}
