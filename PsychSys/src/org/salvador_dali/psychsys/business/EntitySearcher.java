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
package org.salvador_dali.psychsys.business;

import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import org.salvador_dali.psychsys.model.JpaDao;
import org.salvador_dali.psychsys.model.entities.Estudiante;
import org.salvador_dali.psychsys.model.entities.Tutor;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public abstract class EntitySearcher {

    private JpaDao jpDao;
    private Object[] fieldsToSearch;
    private Object[] tableCols;
    private ComboBoxModel defComboBoxModel;
    private TableModel defTableModel;
    private TableColumnModel defTableColumnModel;

    {
        setDefTableColumnModel(new DefaultTableColumnModel() {

            @Override
            public void moveColumn(int columnIndex, int newIndex) {
                if (columnIndex == 0 || newIndex == 0) {
                    return;
                }
                super.moveColumn(columnIndex, newIndex);
            }
        });
    }

    public EntitySearcher() {
    }

    public EntitySearcher(JpaDao jpDao, Object[] fieldsToSearch, ComboBoxModel defComboBoxModel, TableModel defTableModel, TableColumnModel defTableColumnModel) {
        this.jpDao = jpDao;
        this.fieldsToSearch = fieldsToSearch;
        this.defComboBoxModel = defComboBoxModel;
        this.defTableModel = defTableModel;
        this.defTableColumnModel = defTableColumnModel;
    }

    public ComboBoxModel getDefComboBoxModel() {
        return defComboBoxModel;
    }

    public void setDefComboBoxModel(ComboBoxModel comboBoxModel) {
        this.defComboBoxModel = comboBoxModel;
    }

    public TableModel getDefTableModel() {
        return defTableModel;
    }

    public void setDefTableModel(TableModel tableModel) {
        this.defTableModel = tableModel;
    }

    public TableColumnModel getDefTableColumnModel() {
        return defTableColumnModel;
    }

    public void setDefTableColumnModel(TableColumnModel tableColumnModel) {
        this.defTableColumnModel = tableColumnModel;
    }

    public Object[] getFieldsToSearch() {
        return fieldsToSearch;
    }

    public void setFieldsToSearch(Object[] fieldsToSearch) {
        this.fieldsToSearch = fieldsToSearch;
    }

    public Object[] getTableCols() {
        return tableCols;
    }

    public void setTableCols(Object[] tableCols) {
        this.tableCols = tableCols;
    }

    public JpaDao getJpDao() {
        return jpDao;
    }

    public void setJpDao(JpaDao jpDao) {
        this.jpDao = jpDao;
    }

    protected boolean isIdFieldValid(String field) {
        if (!new NumberFieldValidator().validate(field)) {
            return false;
        } else {
            return true;
        }
    }

    public abstract TableModel doSearch(String fieldToSearch, String value);

    // Clases
    public static class TutorEntitySearcher extends EntitySearcher {

        {
            setJpDao(new JpaTutorDao());
            setFieldsToSearch(new Object[]{"Id", "DNI", "Primer Nombre", "Primer Apellido"});
            setTableCols(new Object[]{"Id", "DNI", "Nombre Tutor"});
            setDefComboBoxModel(new DefaultComboBoxModel(getFieldsToSearch()));
            setDefTableModel(new DefaultTableModel(new Object[][]{}, getTableCols()) {

                @Override
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            });
        }

        public TutorEntitySearcher() {
        }

        public TutorEntitySearcher(JpaDao jpDao, Object[] fieldsToSearch, ComboBoxModel defComboBoxModel, TableModel defTableModel, TableColumnModel defTableColumnModel) {
            super(jpDao, fieldsToSearch, defComboBoxModel, defTableModel, defTableColumnModel);
        }

        @Override
        public TableModel doSearch(String fieldToSearch, String value) {
            JpaTutorDao jpaTutDao = (JpaTutorDao) getJpDao();
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
                    if (isIdFieldValid(value)) {
                        Tutor tutSearched = jpaTutDao.findById(Integer.parseInt(value));
                        if (tutSearched != null) {
                            data = new Object[][]{new Object[]{tutSearched.getTutId(), tutSearched.getTutDni(), tutSearched.toString()}};
                        }
                    }
                } else if (fieldToSearch.equalsIgnoreCase("dni")) {
                    Tutor tutSearched = jpaTutDao.getTutorByDNI(value);
                    if (tutSearched != null) {
                        data = new Object[][]{new Object[]{tutSearched.getTutId(), tutSearched.getTutDni(), tutSearched.toString()}};
                    }
                } else if (fieldToSearch.equalsIgnoreCase("primer nombre")) {
                    List<Tutor> tutores = (List<Tutor>) jpaTutDao.getTutoresByPrimerNombre(value);
                    if (tutores != null && tutores.size() > 0) {
                        data = new Object[tutores.size()][];
                        for (Tutor tut : tutores) {
                            data[i] = new Object[]{tut.getTutId(), tut.getTutDni(), tut.toString()};
                            i++;
                        }
                    }
                } else if (fieldToSearch.equalsIgnoreCase("primer apellido")) {
                    List<Tutor> tutores = (List<Tutor>) jpaTutDao.getTutoresByPrimerApellido(value);
                    if (tutores != null && tutores.size() > 0) {
                        data = new Object[tutores.size()][];
                        for (Tutor tut : tutores) {
                            data[i] = new Object[]{tut.getTutId(), tut.getTutDni(), tut.toString()};
                            i++;
                        }
                    }
                }
            }

            DefaultTableModel dtm = null;
            if (data != null) {
                dtm = new DefaultTableModel(data, new Object[]{getFieldsToSearch()[0], getFieldsToSearch()[1], "Nombre Tutor"}) {

                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
            }

            return dtm;
        }
    }

    public static class EstudianteEntitySearcher extends EntitySearcher {

        {
            setJpDao(new JpaEstudianteDao());
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

        public EstudianteEntitySearcher() {
        }

        public EstudianteEntitySearcher(JpaDao jpDao, Object[] fieldsToSearch, ComboBoxModel defComboBoxModel, TableModel defTableModel, TableColumnModel defTableColumnModel) {
            super(jpDao, fieldsToSearch, defComboBoxModel, defTableModel, defTableColumnModel);
        }

        @Override
        public TableModel doSearch(String fieldToSearch, String value) {
            JpaEstudianteDao jpaEstDao = (JpaEstudianteDao) getJpDao();
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
                    List<Estudiante> estudiantes = (List<Estudiante>) jpaEstDao.getEstudiantesByPrimerNombre(value);
                    if (estudiantes != null && estudiantes.size() > 0) {
                        data = new Object[estudiantes.size()][];
                        for (Estudiante est : estudiantes) {
                            data[i] = new Object[]{est.getEstId(), est.getEstDni(), est.toString()};
                            i++;
                        }
                    }
                } else if (fieldToSearch.equalsIgnoreCase("primer apellido")) {
                    List<Estudiante> estudiantes = (List<Estudiante>) jpaEstDao.getEstudiantesByPrimerApellido(value);
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
                dtm = new DefaultTableModel(data, new Object[]{getFieldsToSearch()[0], getFieldsToSearch()[1], "Nombre Tutor"}) {

                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
            }

            return dtm;
        }
    }

    public class ReferimientoEntitySearcher extends EntitySearcher {

        {
            Object[] fields = new Object[]{"Id", "Primer Nombre", "Primer Apellido"};
            setDefComboBoxModel(new DefaultComboBoxModel(fields));
            setDefTableModel(new DefaultTableModel(new Object[][]{}, fields) {

                @Override
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            });
        }

        @Override
        public TableModel doSearch(String fieldToSearch, String value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public class CasoEntitySearcher extends EntitySearcher {

        {
            Object[] fields = new Object[]{"Id", "Primer Nombre", "Primer Apellido"};
            setDefComboBoxModel(new DefaultComboBoxModel(fields));
            setDefTableModel(new DefaultTableModel(new Object[][]{}, fields) {

                @Override
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            });
        }

        @Override
        public TableModel doSearch(String fieldToSearch, String value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public class HistoriaClinicaEntitySearcher extends EntitySearcher {

        {
            Object[] fields = new Object[]{"Id", "Primer Nombre", "Primer Apellido"};
            setDefComboBoxModel(new DefaultComboBoxModel(fields));
            setDefTableModel(new DefaultTableModel(new Object[][]{}, fields) {

                @Override
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            });
        }

        @Override
        public TableModel doSearch(String fieldToSearch, String value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
