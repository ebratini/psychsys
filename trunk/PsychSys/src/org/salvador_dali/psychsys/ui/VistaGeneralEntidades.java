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

/*
 * VistaGeneralEntidades.java
 *
 * Created on Dec 13, 2011, 12:45:54 PM
 */
package org.salvador_dali.psychsys.ui;

import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.salvador_dali.psychsys.business.EntitySearcher;
import org.salvador_dali.psychsys.business.NumberFieldValidator;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class VistaGeneralEntidades extends javax.swing.JPanel {

    private Object entitySelectedId;
    private EntitySearcher entitySearcher;

    /** Creates new form VistaGeneralEntidades */
    public VistaGeneralEntidades() {
        initComponents();
        lblResultadoBusqueda.setVisible(false);
        setComboAndTblModel();
    }

    public VistaGeneralEntidades(EntitySearcher entitySearcher) {
        this.entitySearcher = entitySearcher;
        initComponents();
        lblResultadoBusqueda.setVisible(false);
        setComboAndTblModel();
    }

    public EntitySearcher getEntitySearcher() {
        return entitySearcher;
    }

    public void setEntitySearcher(EntitySearcher entitySearcher) {
        this.entitySearcher = entitySearcher;
    }

    public Object getEntitySelectedId() {
        return entitySelectedId;
    }

    public void setEntitySelectedId(Object entitySelectedId) {
        this.entitySelectedId = entitySelectedId;
    }

    public JTable getTblEntidades() {
        return tblEntidades;
    }

    public void setTblEntidades(JTable tblEntidades) {
        this.tblEntidades = tblEntidades;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlOpBusqueda = new javax.swing.JPanel();
        lblCampo = new javax.swing.JLabel();
        cmbCampoBuscar = new javax.swing.JComboBox();
        txtBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lblResultadoBusqueda = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEntidades = new javax.swing.JTable();

        pnlOpBusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones de Busqueda"));

        lblCampo.setText("Campo Buscar");

        cmbCampoBuscar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyPressed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        lblResultadoBusqueda.setText("Resultado Busqueda");

        javax.swing.GroupLayout pnlOpBusquedaLayout = new javax.swing.GroupLayout(pnlOpBusqueda);
        pnlOpBusqueda.setLayout(pnlOpBusquedaLayout);
        pnlOpBusquedaLayout.setHorizontalGroup(
            pnlOpBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpBusquedaLayout.createSequentialGroup()
                .addGroup(pnlOpBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCampoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCampo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlOpBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOpBusquedaLayout.createSequentialGroup()
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar))
                    .addGroup(pnlOpBusquedaLayout.createSequentialGroup()
                        .addComponent(lblResultadoBusqueda)
                        .addContainerGap())))
        );
        pnlOpBusquedaLayout.setVerticalGroup(
            pnlOpBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpBusquedaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlOpBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCampo)
                    .addComponent(lblResultadoBusqueda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlOpBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCampoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        tblEntidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblEntidades.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(tblEntidades);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlOpBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlOpBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void setComboAndTblModel() {
        if (entitySearcher != null) {
            cmbCampoBuscar.setModel(entitySearcher.getDefComboBoxModel());
            tblEntidades.setColumnModel(entitySearcher.getDefTableColumnModel());
            tblEntidades.setModel(entitySearcher.getDefTableModel());
        } else {
            String[] colNames = new String[]{"Id", "Col 2", "Col 3"};
            cmbCampoBuscar.setModel(new DefaultComboBoxModel(colNames));
            tblEntidades.setColumnModel(new DefaultTableColumnModel() {

                @Override
                public void moveColumn(int columnIndex, int newIndex) {
                    if (columnIndex == 0 || newIndex == 0) {
                        return;
                    }
                    super.moveColumn(columnIndex, newIndex);
                }
            });

            DefaultTableModel dtm = new DefaultTableModel(new Object[][]{{}}, colNames) {

                @Override
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            };

            tblEntidades.setModel(dtm);
        }
    }

    private void doSearch() {
        if (cmbCampoBuscar.getSelectedItem().toString().equalsIgnoreCase("id") && !txtBusqueda.getText().equals("*")) {
            if (!new NumberFieldValidator().validate(txtBusqueda.getText())) {
                JOptionPane.showMessageDialog(this, "Este campo solo acepta numeros", "Busqueda", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        TableModel tm = getEntitySearcher().doSearch(cmbCampoBuscar.getSelectedItem().toString(), txtBusqueda.getText());
        if (tm != null) {
            tblEntidades.setModel(tm);
            tblEntidades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        } else {
            lblResultadoBusqueda.setText("No hubo resultados");
            lblResultadoBusqueda.setForeground(Color.red);
            new Thread(new LabelToolTipShower(lblResultadoBusqueda, 3000)).start();
            tblEntidades.setModel(getEntitySearcher().getDefTableModel());
            return;
        }
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        if (txtBusqueda.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Introduzca texto a buscar", "Busqueda", JOptionPane.ERROR_MESSAGE);
            return;
        }
        doSearch();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnBuscar.doClick();
        }
    }//GEN-LAST:event_txtBusquedaKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox cmbCampoBuscar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCampo;
    private javax.swing.JLabel lblResultadoBusqueda;
    private javax.swing.JPanel pnlOpBusqueda;
    private javax.swing.JTable tblEntidades;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
