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
package org.salvador_dali.psychsys.model;

import java.util.Date;
import java.util.List;
import org.salvador_dali.psychsys.model.entities.Caso;
import org.salvador_dali.psychsys.model.entities.Estudiante;
import org.salvador_dali.psychsys.model.entities.PruebaPsicologica;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public interface PruebaPsicologicaDao extends Dao {
    
    public List<PruebaPsicologica> getPruebasPsicologicasByEstudiante(Estudiante estudiante);

    public List<PruebaPsicologica> getPruebasPsicologicasByCaso(Caso caso);

    public List<PruebaPsicologica> getPruebasPsicologicasByFechaAplicacion(Date fechaAplicacion);

    public List<PruebaPsicologica> getPruebasPsicologicasByNombrePrueba(String nombrePrueba);

    public List<PruebaPsicologica> getPruebasPsicologicasByCorreccionAutomatica(char correcionAutomatica);
}
