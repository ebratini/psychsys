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
package org.salvador_dali.psychsys.ui;

import org.salvador_dali.psychsys.business.*;
import org.salvador_dali.psychsys.business.jpa_controllers.TutorJpaDao;
import org.salvador_dali.psychsys.business.jpa_controllers.EstudianteJpaDao;
import org.salvador_dali.psychsys.business.jpa_controllers.ReferimientoJpaDao;
import org.salvador_dali.psychsys.business.jpa_controllers.PruebaPsicologicaJpaDao;
import org.salvador_dali.psychsys.business.jpa_controllers.HistoriaClinicaJpaDao;
import org.salvador_dali.psychsys.business.jpa_controllers.CasoJpaDao;
import org.salvador_dali.psychsys.business.validators.NumberFieldValidator;
import org.salvador_dali.psychsys.business.validators.DateFieldValidator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import org.mozilla.javascript.edu.emory.mathcs.backport.java.util.Arrays;
import org.salvador_dali.psychsys.business.jpa_controllers.JpaDao;
import org.salvador_dali.psychsys.model.entities.Caso;
import org.salvador_dali.psychsys.model.entities.Estudiante;
import org.salvador_dali.psychsys.model.entities.HistoriaClinica;
import org.salvador_dali.psychsys.model.entities.PruebaPsicologica;
import org.salvador_dali.psychsys.model.entities.Referimiento;
import org.salvador_dali.psychsys.model.entities.Tutor;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public abstract class EntitySearcher {

    private JpaDao jpDao;
    private List fieldsToSearch = new ArrayList<String>();
    private Object[][] dataSearch;
    private List dtmTableCols = new ArrayList<String>();
    private TableModel defTableModel = new DefaultTableModel(new Object[][]{}, dtmTableCols.toArray()) {

                @Override
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            };
    private TableColumnModel defTableColumnModel = new DefaultTableColumnModel() {

            @Override
            public void moveColumn(int columnIndex, int newIndex) {
                if (columnIndex == 0 || newIndex == 0) {
                    return;
                }
                super.moveColumn(columnIndex, newIndex);
            }
        };

    public EntitySearcher() {
    }

    public EntitySearcher(JpaDao jpDao, List fieldsToSearch, TableModel defTableModel, TableColumnModel defTableColumnModel) {
        this.jpDao = jpDao;
        this.fieldsToSearch = fieldsToSearch;
        this.defTableModel = defTableModel;
        this.defTableColumnModel = defTableColumnModel;
    }

    public TableColumnModel getDefTableColumnModel() {
        return defTableColumnModel;
    }

    public void setDefTableColumnModel(TableColumnModel defTableColumnModel) {
        this.defTableColumnModel = defTableColumnModel;
    }

    public TableModel getDefTableModel() {
        return defTableModel;
    }

    public void setDefTableModel(TableModel defTableModel) {
        this.defTableModel = defTableModel;
    }

    public List getDtmTableCols() {
        return dtmTableCols;
    }

    public void setDtmTableCols(List dtmTableCols) {
        this.dtmTableCols = dtmTableCols;
    }

    public List getFieldsToSearch() {
        return fieldsToSearch;
    }

    public void setFieldsToSearch(List fieldsToSearch) {
        this.fieldsToSearch = fieldsToSearch;
    }

    public JpaDao getJpDao() {
        return jpDao;
    }

    public void setJpDao(JpaDao jpDao) {
        this.jpDao = jpDao;
    }

    public Object[][] getDataSearch() {
        return dataSearch;
    }

    public abstract void doSearch(String fieldToSearch, String value);
    public abstract TableModel getTableModelFromSearch();
    //public abstract TableModel doSearch(String fieldToSearch, String value);

    // Clases
    
    // Basic Searchers
    public static class TutorSearcher extends EntitySearcher {
        
        {
            setJpDao(new TutorJpaDao());
            setFieldsToSearch(Arrays.asList(new Object[]{"Id", "DNI", "Tipo DNI", "Primer Apellido", "Segundo Apellido", "Primer Nombre",
                "Segundo Nombre", "Nombre Tutor", "Telefono", "Nacionalidad", "Genero", "Estado Civil", "Status"}));
            setDtmTableCols(Arrays.asList(new Object[]{"Id", "DNI", "Tipo DNI", "Primer Apellido", "Segundo Apellido", "Primer Nombre",
                "Segundo Nombre", "Nombre Tutor", "Telefono", "Direccion", "Email", "Nacionalidad", "Genero", "Estado Civil", "Status"}));
            setDefTableModel(new DefaultTableModel(new Object[][]{}, getDtmTableCols().toArray()) {

                @Override
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            });
        }

        public TutorSearcher() {
        }

        
        @Override
        public void doSearch(String fieldToSearch, String value) {
            TutorJpaDao jpaTutDao = (TutorJpaDao) getJpDao();
            Object[][] data = null;
            int i = 0;
            if (value.equals("*")) {
                List<Tutor> tutores = (List<Tutor>) jpaTutDao.retrieve();
                if (tutores != null && tutores.size() > 0) {
                    data = new Object[tutores.size()][];
                    for (Tutor tut : tutores) {
                        data[i] = new Object[]{tut.getTutId(), tut.getTutDni(), tut.toString()};
                        i++;
                    }
                }
            } else {
                if (fieldToSearch.equalsIgnoreCase("id")) {
                    Tutor tutSearched = jpaTutDao.findById(Integer.parseInt(value));
                    if (tutSearched != null) {
                        data = new Object[][]{new Object[]{tutSearched.getTutId(), tutSearched.getTutDni(), tutSearched.toString()}};
                    }
                } else if (fieldToSearch.equalsIgnoreCase("dni")) {
                    Tutor tutSearched = jpaTutDao.getTutorByDNI(value);
                    if (tutSearched != null) {
                        data = new Object[][]{new Object[]{tutSearched.getTutId(), tutSearched.getTutDni(), tutSearched.toString()}};
                    }
                } else if (fieldToSearch.equalsIgnoreCase("primer nombre")) {
                    List<Tutor> tutores = jpaTutDao.getTutoresByPrimerNombre(value);
                    if (tutores != null && tutores.size() > 0) {
                        data = new Object[tutores.size()][];
                        for (Tutor tut : tutores) {
                            data[i] = new Object[]{tut.getTutId(), tut.getTutDni(), tut.toString()};
                            i++;
                        }
                    }
                } else if (fieldToSearch.equalsIgnoreCase("primer apellido")) {
                    List<Tutor> tutores = jpaTutDao.getTutoresByPrimerApellido(value);
                    if (tutores != null && tutores.size() > 0) {
                        data = new Object[tutores.size()][];
                        for (Tutor tut : tutores) {
                            data[i] = new Object[]{tut.getTutId(), tut.getTutDni(), tut.toString()};
                            i++;
                        }
                    }
                }
            }
            super.dataSearch = data;
        }

        @Override
        public TableModel getTableModelFromSearch() {
            DefaultTableModel dtm = null;
            if (getDataSearch() != null) {
                dtm = new DefaultTableModel(getDataSearch(), getDtmTableCols().toArray()) {

                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                dtm.setColumnIdentifiers(getDtmTableCols().toArray());
            }

            return dtm;
        }
    }

    /*
    public static class EstudianteBasicEntitySearcher extends EntitySearcher {

        {
            setJpDao(new EstudianteJpaDao());
            setFieldsToSearch(new Object[]{"Id", "DNI", "Primer Nombre", "Primer Apellido"});
            setTableCols(new Object[]{"Id", "DNI", "Nombre Estudiante"});
            setDefComboBoxModel(new DefaultComboBoxModel(getFieldsToSearch()));
            setDefTableModel(new DefaultTableModel(new Object[][]{}, getTableCols()) {

                @Override
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            });
        }

        public EstudianteBasicEntitySearcher() {
        }

        public EstudianteBasicEntitySearcher(JpaDao jpDao, Object[] fieldsToSearch, ComboBoxModel defComboBoxModel, TableModel defTableModel, TableColumnModel defTableColumnModel) {
            super(jpDao, fieldsToSearch, defComboBoxModel, defTableModel, defTableColumnModel);
        }

        @Override
        public TableModel doSearch(String fieldToSearch, String value) {
            EstudianteJpaDao jpaEstDao = (EstudianteJpaDao) getJpDao();
            Object[][] data = null;
            int i = 0;
            if (value.equals("*")) {
                List<Estudiante> estudiantes = (List<Estudiante>) jpaEstDao.retrieve();
                if (estudiantes != null && estudiantes.size() > 0) {
                    data = new Object[estudiantes.size()][];
                    for (Estudiante est : estudiantes) {
                        data[i] = new Object[]{est.getEstId(), est.getEstDni(), est.toString()};
                        i++;
                    }
                }
            } else {
                if (fieldToSearch.equalsIgnoreCase("id")) {
                    if (isIdFieldValid(value)) {
                        Estudiante estSearched = jpaEstDao.findById(Integer.parseInt(value));
                        if (estSearched != null) {
                            data = new Object[][]{new Object[]{estSearched.getEstId(), estSearched.getEstDni(), estSearched.toString()}};
                        }
                    }
                } else if (fieldToSearch.equalsIgnoreCase("dni")) {
                    Estudiante estSearched = jpaEstDao.getEstudianteByDNI(value);
                    if (estSearched != null) {
                        data = new Object[][]{new Object[]{estSearched.getEstId(), estSearched.getEstDni(), estSearched.toString()}};
                    }
                } else if (fieldToSearch.equalsIgnoreCase("primer nombre")) {
                    List<Estudiante> estudiantes = jpaEstDao.getEstudiantesByPrimerNombre(value);
                    if (estudiantes != null && estudiantes.size() > 0) {
                        data = new Object[estudiantes.size()][];
                        for (Estudiante est : estudiantes) {
                            data[i] = new Object[]{est.getEstId(), est.getEstDni(), est.toString()};
                            i++;
                        }
                    }
                } else if (fieldToSearch.equalsIgnoreCase("primer apellido")) {
                    List<Estudiante> estudiantes = jpaEstDao.getEstudiantesByPrimerApellido(value);
                    if (estudiantes != null && estudiantes.size() > 0) {
                        data = new Object[estudiantes.size()][];
                        for (Estudiante est : estudiantes) {
                            data[i] = new Object[]{est.getEstId(), est.getEstDni(), est.toString()};
                            i++;
                        }
                    }
                }
            }

            DefaultTableModel dtm = null;

            if (data != null) {
                dtm = new DefaultTableModel(data, getTableCols()) {

                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
            }

            return dtm;
        }

        @Override
        public List<Serializable> doSearch() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public TableModel getTableModelFromSearch() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public static class ReferimientoBasicEntitySearcher extends EntitySearcher {

        {
            setJpDao(new ReferimientoJpaDao());
            setFieldsToSearch(new Object[]{"Id", "Fecha Referimiento", "Año Escolar", "Estudiante", "Referidor"}); // estudiante = 1er nombre + 1er apellido
            setDefComboBoxModel(new DefaultComboBoxModel(getFieldsToSearch()));
            setTableCols(getFieldsToSearch());
            setDefTableModel(new DefaultTableModel(new Object[][]{}, getTableCols()) {

                @Override
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            });
        }

        @Override
        public TableModel doSearch(String fieldToSearch, String value) {
            ReferimientoJpaDao jpaRefDao = (ReferimientoJpaDao) getJpDao();
            Object[][] data = null;
            int i = 0;
            if (value.equals("*")) {
                List<Referimiento> referimientos = (List<Referimiento>) jpaRefDao.retrieve();
                if (referimientos != null && referimientos.size() > 0) {
                    data = new Object[referimientos.size()][];
                    for (Referimiento ref : referimientos) {
                        data[i] = new Object[]{ref.getRefId(), String.format("%1$td-%1$tm-%1$tY", ref.getRefFecha()), ref.getRefAnioEscolar(),
                            ref.getEstudiante().toString(), ref.getRefNombreReferidor()};
                        i++;
                    }
                }
            } else {
                if (fieldToSearch.equalsIgnoreCase("id")) {
                    if (isIdFieldValid(value)) {
                        Referimiento refSearched = jpaRefDao.findById(Integer.parseInt(value));
                        if (refSearched != null) {
                            data = new Object[][]{new Object[]{refSearched.getRefId(), String.format("%1$td-%1$tm-%1$tY", refSearched.getRefFecha()),
                                    refSearched.getRefAnioEscolar(), refSearched.getEstudiante().toString(), refSearched.getRefNombreReferidor()}};
                        }
                    }
                } else if (fieldToSearch.equalsIgnoreCase("fecha referimiento")) {
                    if (new DateFieldValidator().validate(value)) {
                        List<Referimiento> referimientos = jpaRefDao.getReferimientosByFecha(DateUtils.parseDate(value));
                        if (referimientos != null && referimientos.size() > 0) {
                            data = new Object[referimientos.size()][];
                            for (Referimiento ref : referimientos) {
                                data[i] = new Object[]{ref.getRefId(), String.format("%1$td-%1$tm-%1$tY", ref.getRefFecha()), ref.getRefAnioEscolar(),
                                    ref.getEstudiante().toString(), ref.getRefNombreReferidor()};
                                i++;
                            }
                        }
                    }
                } else if (fieldToSearch.equalsIgnoreCase("año escolar")) {
                    List<Referimiento> referimientos = jpaRefDao.getReferimientosByAnioEscolar(value);
                    if (referimientos != null && referimientos.size() > 0) {
                        data = new Object[referimientos.size()][];
                        for (Referimiento ref : referimientos) {
                            data[i] = new Object[]{ref.getRefId(), String.format("%1$td-%1$tm-%1$tY", ref.getRefFecha()), ref.getRefAnioEscolar(),
                                ref.getEstudiante().toString(), ref.getRefNombreReferidor()};
                            i++;
                        }
                    }
                } else if (fieldToSearch.equalsIgnoreCase("estudiante")) {
                    String[] arrNombreEstudiante = value.trim().split(" ");
                    List<Referimiento> referimientos = new ArrayList<Referimiento>();
                    if (arrNombreEstudiante.length == 2) {
                        List<Estudiante> estudiantes = new EstudianteJpaDao().getEstudiantesByNombreCompleto(arrNombreEstudiante[0], arrNombreEstudiante[1]);
                        for (Estudiante est : estudiantes) {
                            List<Referimiento> refList = jpaRefDao.getReferimientosByEstudiante(est);
                            if (refList != null) {
                                referimientos.addAll(refList);
                            }
                        }
                    }
                    
                    if (referimientos != null && referimientos.size() > 0) {
                        data = new Object[referimientos.size()][];
                        for (Referimiento ref : referimientos) {
                            data[i] = new Object[]{ref.getRefId(), String.format("%1$td-%1$tm-%1$tY", ref.getRefFecha()), ref.getRefAnioEscolar(),
                                ref.getEstudiante().toString(), ref.getRefNombreReferidor()};
                            i++;
                        }
                    }
                } else if (fieldToSearch.equalsIgnoreCase("referidor")) {
                    List<Referimiento> referimientos = jpaRefDao.getReferimientosByReferidor(value);
                    if (referimientos != null && referimientos.size() > 0) {
                        data = new Object[referimientos.size()][];
                        for (Referimiento ref : referimientos) {
                            data[i] = new Object[]{ref.getRefId(), String.format("%1$td-%1$tm-%1$tY", ref.getRefFecha()), ref.getRefAnioEscolar(),
                                ref.getEstudiante().toString(), ref.getRefNombreReferidor()};
                            i++;
                        }
                    }
                }
            }

            DefaultTableModel dtm = null;
            if (data != null) {
                dtm = new DefaultTableModel(data, getTableCols()) {

                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
            }

            return dtm;
        }

        @Override
        public List<Serializable> doSearch() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public TableModel getTableModelFromSearch() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public static class CasoBasicEntitySearcher extends EntitySearcher {

        {
            setJpDao(new CasoJpaDao());
            setFieldsToSearch(new Object[]{"Id", "Fecha Caso", "Año Escolar", "Referimiento"}); // estudiante = 1er nombre + 1er apellido
            setDefComboBoxModel(new DefaultComboBoxModel(getFieldsToSearch()));
            setTableCols(getFieldsToSearch());
            setDefTableModel(new DefaultTableModel(new Object[][]{}, getTableCols()) {

                @Override
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            });
        }

        @Override
        public TableModel doSearch(String fieldToSearch, String value) {
            CasoJpaDao jpaCasoDao = (CasoJpaDao) getJpDao();
            Object[][] data = null;
            int i = 0;
            if (value.equals("*")) {
                List<Caso> casos = (List<Caso>) jpaCasoDao.retrieve();
                if (casos != null && casos.size() > 0) {
                    data = new Object[casos.size()][];
                    for (Caso caso : casos) {
                        data[i] = new Object[]{caso.getCsoId(), String.format("%1$td-%1$tm-%1$tY", caso.getCsoFecha()), caso.getCsoAnioEscolar(), caso.getReferimiento().toString()};
                        i++;
                    }
                }
            } else {
                if (fieldToSearch.equalsIgnoreCase("id")) {
                    if (isIdFieldValid(value)) {
                        Caso casoSearched = jpaCasoDao.findById(Integer.parseInt(value));
                        if (casoSearched != null) {
                            data = new Object[][]{new Object[]{casoSearched.getCsoId(), String.format("%1$td-%1$tm-%1$tY", casoSearched.getCsoFecha()), casoSearched.getCsoAnioEscolar(),
                                    casoSearched.getReferimiento().toString()}};
                        }
                    }
                } else if (fieldToSearch.equalsIgnoreCase("fecha caso")) {
                    if (new DateFieldValidator().validate(value)) {
                        List<Caso> casos = jpaCasoDao.getCasosByFecha(DateUtils.parseDate(value));
                        if (casos != null && casos.size() > 0) {
                            data = new Object[casos.size()][];
                            for (Caso caso : casos) {
                                data[i] = new Object[]{caso.getCsoId(), String.format("%1$td-%1$tm-%1$tY", caso.getCsoFecha()), caso.getCsoAnioEscolar(),
                                    caso.getReferimiento().toString()};
                                i++;
                            }
                        }
                    }
                } else if (fieldToSearch.equalsIgnoreCase("año escolar")) {
                    List<Caso> casos = jpaCasoDao.getCasosByAnioEscolar(value);
                    if (casos != null && casos.size() > 0) {
                        data = new Object[casos.size()][];
                        for (Caso caso : casos) {
                            data[i] = new Object[]{caso.getCsoId(), String.format("%1$td-%1$tm-%1$tY", caso.getCsoFecha()), caso.getCsoAnioEscolar(),
                                caso.getReferimiento().toString()};
                            i++;
                        }
                    }
                } else if (fieldToSearch.equalsIgnoreCase("referimiento")) {
                    String refId = value.split(" ")[0];
                    Referimiento ref = (Referimiento) new ReferimientoJpaDao().findById(refId);

                    Caso caso = jpaCasoDao.getCasoByReferimiento(ref);
                    if (caso != null) {
                        data = new Object[][]{new Object[]{caso.getCsoId(), String.format("%1$td-%1$tm-%1$tY", caso.getCsoFecha()), caso.getCsoAnioEscolar(), caso.getReferimiento().toString()}};
                    }
                }
            }

            DefaultTableModel dtm = null;
            if (data != null) {
                dtm = new DefaultTableModel(data, getTableCols()) {

                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
            }

            return dtm;
        }

        @Override
        public List<Serializable> doSearch() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public TableModel getTableModelFromSearch() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
    // prueba psicologica
    public static class PruebaPsicologicaBasicEntitySearcher extends EntitySearcher {

        {
            setJpDao(new PruebaPsicologicaJpaDao());
            setFieldsToSearch(new Object[]{"Id", "Fecha Aplicacion", "Nombre Prueba", "Caso", "Estudiante"}); // estudiante = 1er nombre + 1er apellido
            setDefComboBoxModel(new DefaultComboBoxModel(getFieldsToSearch()));
            setTableCols(getFieldsToSearch());
            setDefTableModel(new DefaultTableModel(new Object[][]{}, getTableCols()) {

                @Override
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            });
        }

        @Override
        public TableModel doSearch(String fieldToSearch, String value) {
            PruebaPsicologicaJpaDao jpaPPSDao = (PruebaPsicologicaJpaDao) getJpDao();
            Object[][] data = null;
            int i = 0;
            if (value.equals("*")) {
                List<PruebaPsicologica> pruebas = (List<PruebaPsicologica>) jpaPPSDao.retrieve();
                if (pruebas != null && pruebas.size() > 0) {
                    data = new Object[pruebas.size()][];
                    for (PruebaPsicologica pp : pruebas) {
                        data[i] = new Object[]{pp.getPpsId(), String.format("%1$td-%1$tm-%1$tY", pp.getPpsFechaAplicacion()), pp.getPpsNombrePrueba(), (pp.getCaso() != null ? pp.getCaso().toString() : ""), pp.getEstudiante().toString()};
                        i++;
                    }
                }
            } else {
                if (fieldToSearch.equalsIgnoreCase("id")) {
                    if (isIdFieldValid(value)) {
                        PruebaPsicologica pp = jpaPPSDao.findById(Integer.parseInt(value));
                        if (pp != null) {
                            data = new Object[][]{new Object[]{pp.getPpsId(), String.format("%1$td-%1$tm-%1$tY", pp.getPpsFechaAplicacion()), pp.getPpsNombrePrueba(), (pp.getCaso() != null ? pp.getCaso().toString() : ""), pp.getEstudiante().toString()}};
                        }
                    }
                } else if (fieldToSearch.equalsIgnoreCase("fecha aplicacion")) {
                    if (new DateFieldValidator().validate(value)) {
                        List<PruebaPsicologica> pruebas = jpaPPSDao.getPruebasPsicologicasByFechaAplicacion(DateUtils.parseDate(value));
                        if (pruebas != null && pruebas.size() > 0) {
                            data = new Object[pruebas.size()][];
                            for (PruebaPsicologica pp : pruebas) {
                                data[i] = new Object[]{pp.getPpsId(), String.format("%1$td-%1$tm-%1$tY", pp.getPpsFechaAplicacion()), pp.getPpsNombrePrueba(), (pp.getCaso() != null ? pp.getCaso().toString() : ""), pp.getEstudiante().toString()};
                                i++;
                            }
                        }
                    }
                } else if (fieldToSearch.equalsIgnoreCase("nombre prueba")) {
                    List<PruebaPsicologica> pruebas = jpaPPSDao.getPruebasPsicologicasByNombrePrueba(value);
                    if (pruebas != null && pruebas.size() > 0) {
                        data = new Object[pruebas.size()][];
                        for (PruebaPsicologica pp : pruebas) {
                            data[i] = new Object[]{pp.getPpsId(), String.format("%1$td-%1$tm-%1$tY", pp.getPpsFechaAplicacion()), pp.getPpsNombrePrueba(), (pp.getCaso() != null ? pp.getCaso().toString() : ""), pp.getEstudiante().toString()};
                            i++;
                        }
                    }
                } else if (fieldToSearch.equalsIgnoreCase("caso")) {
                    Caso caso = (Caso) new CasoJpaDao().findById(Integer.parseInt(value));

                    List<PruebaPsicologica> pruebas = jpaPPSDao.getPruebasPsicologicasByCaso(caso);
                    if (pruebas != null && pruebas.size() > 0) {
                        data = new Object[pruebas.size()][];
                        for (PruebaPsicologica pp : pruebas) {
                            data[i] = new Object[]{pp.getPpsId(), String.format("%1$td-%1$tm-%1$tY", pp.getPpsFechaAplicacion()), pp.getPpsNombrePrueba(), (pp.getCaso() != null ? pp.getCaso().toString() : ""), pp.getEstudiante().toString()};
                            i++;
                        }
                    }
                } else if (fieldToSearch.equalsIgnoreCase("estudiante")) {
                    String[] chrEstudiante = value.split(" ");
                    
                    List<PruebaPsicologica> pruebas = new ArrayList<PruebaPsicologica>();
                    if (chrEstudiante.length == 2) {
                        List<Estudiante> estudiantes = new EstudianteJpaDao().getEstudiantesByNombreCompleto(chrEstudiante[0], chrEstudiante[1]);
                        
                        for (Estudiante est : estudiantes) {
                            List<PruebaPsicologica> ppList = jpaPPSDao.getPruebasPsicologicasByEstudiante(est);
                            if (ppList != null) {
                                pruebas.addAll(ppList);
                            }
                        }
                    }
                    
                    if (pruebas != null && pruebas.size() > 0) {
                        data = new Object[pruebas.size()][];
                        for (PruebaPsicologica pp : pruebas) {
                            data[i] = new Object[]{pp.getPpsId(), String.format("%1$td-%1$tm-%1$tY", pp.getPpsFechaAplicacion()), pp.getPpsNombrePrueba(), (pp.getCaso() != null ? pp.getCaso().toString() : ""), pp.getEstudiante().toString()};
                            i++;
                        }
                    }
                }
            }

            DefaultTableModel dtm = null;
            if (data != null) {
                dtm = new DefaultTableModel(data, getTableCols()) {

                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
            }

            return dtm;
        }

        @Override
        public List<Serializable> doSearch() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public TableModel getTableModelFromSearch() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
    
    // historia clinica

    public static class HistoriaClinicaEntitySearcher extends EntitySearcher {

        {
            setJpDao(new HistoriaClinicaJpaDao());
            setFieldsToSearch(new Object[]{"Id", "Fecha Creacion", "Estudiante"}); // estudiante = 1er nombre + 1er apellido
            setDefComboBoxModel(new DefaultComboBoxModel(getFieldsToSearch()));
            setTableCols(getFieldsToSearch());
            setDefTableModel(new DefaultTableModel(new Object[][]{}, getTableCols()) {

                @Override
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            });
        }

        @Override
        public TableModel doSearch(String fieldToSearch, String value) {
            HistoriaClinicaJpaDao jpaHicDao = (HistoriaClinicaJpaDao) getJpDao();
            Object[][] data = null;
            int i = 0;
            if (value.equals("*")) {
                List<HistoriaClinica> hics = (List<HistoriaClinica>) jpaHicDao.retrieve();
                if (hics != null && hics.size() > 0) {
                    data = new Object[hics.size()][];
                    for (HistoriaClinica hic : hics) {
                        data[i] = new Object[]{hic.getHicId(), String.format("%1$td-%1$tm-%1$tY", hic.getHicFechaCreacion()), hic.getEstudiante().toString()};
                        i++;
                    }
                }
            } else {
                if (fieldToSearch.equalsIgnoreCase("id")) {
                    if (isIdFieldValid(value)) {
                        HistoriaClinica hicSearched = jpaHicDao.findById(Integer.parseInt(value));
                        if (hicSearched != null) {
                            data = new Object[][]{new Object[]{hicSearched.getHicId(), String.format("%1$td-%1$tm-%1$tY", hicSearched.getHicFechaCreacion()), hicSearched.getEstudiante().toString()}};
                        }
                    }
                } else if (fieldToSearch.equalsIgnoreCase("fecha creacion")) {
                    if (new DateFieldValidator().validate(value)) {
                        List<HistoriaClinica> hics = jpaHicDao.getHistoriasClinicasByFechaCreacion(DateUtils.parseDate(value));
                        if (hics != null && hics.size() > 0) {
                            data = new Object[hics.size()][];
                            for (HistoriaClinica hic : hics) {
                                data[i] = new Object[]{hic.getHicId(), String.format("%1$td-%1$tm-%1$tY", hic.getHicFechaCreacion()), hic.getEstudiante().toString()};
                                i++;
                            }
                        }
                    }
                } else if (fieldToSearch.equalsIgnoreCase("estudiante")) {
                    String[] arrNombreEstudiante = value.trim().split(" ");
                    
                    List<HistoriaClinica> hics = new ArrayList<HistoriaClinica>();
                    if (arrNombreEstudiante.length == 2) {
                        List<Estudiante> estudiantes = new EstudianteJpaDao().getEstudiantesByNombreCompleto(arrNombreEstudiante[0], arrNombreEstudiante[1]);
                        
                        for (Estudiante est : estudiantes) {
                            HistoriaClinica hic = jpaHicDao.getHistoriaClinicaByEstudiante(est);
                            if (hic != null) {
                                hics.add(hic);
                            }
                        }
                    }
                    
                    if (hics != null && hics.size() > 0) {
                        data = new Object[hics.size()][];
                        for (HistoriaClinica hic : hics) {
                            data[i] = new Object[]{hic.getHicId(), String.format("%1$td-%1$tm-%1$tY", hic.getHicFechaCreacion()), hic.getEstudiante().toString()};
                            i++;
                        }
                    }
                }
            }

            DefaultTableModel dtm = null;
            if (data != null) {
                dtm = new DefaultTableModel(data, getTableCols()) {

                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
            }

            return dtm;
        }

        @Override
        public List<Serializable> doSearch() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public TableModel getTableModelFromSearch() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }*/
}
