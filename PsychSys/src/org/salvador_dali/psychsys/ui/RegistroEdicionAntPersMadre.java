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
 * RegistroEdicionAntPersMadre.java
 *
 * Created on Nov 15, 2011, 5:05:25 PM
 */
package org.salvador_dali.psychsys.ui;

import java.awt.Component;
import java.awt.Container;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import org.salvador_dali.psychsys.model.entities.AntPersMadre;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class RegistroEdicionAntPersMadre extends javax.swing.JDialog {

    private RegistroEdicionModo modo = RegistroEdicionModo.REGISTRO;
    private AntPersMadre antPersMadre;
    private AntPersMadre apmEditar;

    /** Creates new form RegistroEdicionAntPersMadre */
    public RegistroEdicionAntPersMadre(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public AntPersMadre getAntPersMadre() {
        return antPersMadre;
    }

    public void setAntPersMadre(AntPersMadre antPersMadre) {
        this.antPersMadre = antPersMadre;
    }

    public RegistroEdicionModo getModo() {
        return modo;
    }

    public void setModo(RegistroEdicionModo modo) {
        this.modo = modo;
    }

    public AntPersMadre getApmEditar() {
        return apmEditar;
    }

    public void setApmEditar(AntPersMadre apmEditar) {
        this.apmEditar = apmEditar;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgrEmbarazoDeseado = new javax.swing.ButtonGroup();
        bgrAmenazaAborto = new javax.swing.ButtonGroup();
        bgrIntentoAborto = new javax.swing.ButtonGroup();
        bgrSexoPreferido = new javax.swing.ButtonGroup();
        pnlAntPersMadre = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblEmbarazoDeseado = new javax.swing.JLabel();
        rbnEmbDesSi = new javax.swing.JRadioButton();
        rbnEmbDesNo = new javax.swing.JRadioButton();
        lblAmenazaAborto = new javax.swing.JLabel();
        rbnAmAbSi = new javax.swing.JRadioButton();
        rbnAmAbNo = new javax.swing.JRadioButton();
        lblIntentoAborto = new javax.swing.JLabel();
        rbnInAbSi = new javax.swing.JRadioButton();
        rbnInAbNo = new javax.swing.JRadioButton();
        lblSexoPreferido = new javax.swing.JLabel();
        rbnSexPrefFem = new javax.swing.JRadioButton();
        rbnSexPrefMas = new javax.swing.JRadioButton();
        lblDuracion = new javax.swing.JLabel();
        cmbDuracionEmbarazo = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaAlteracionesPsiquicas = new javax.swing.JTextArea();
        lblHabitosToxicos = new javax.swing.JLabel();
        lblEnfermedadesEnEmbarazo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        chkEmbarazo = new javax.swing.JCheckBox();
        rbnSexPrefInd = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaHabitosToxicos = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaEnfDuranteEmbarazo = new javax.swing.JTextArea();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar Antecedentes Personales Madre");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pnlAntPersMadre.setBorder(javax.swing.BorderFactory.createTitledBorder("Antecedentes Personales Madre"));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblEmbarazoDeseado.setText("Embarazo Deseado");

        bgrEmbarazoDeseado.add(rbnEmbDesSi);
        rbnEmbDesSi.setSelected(true);
        rbnEmbDesSi.setText("Si");

        bgrEmbarazoDeseado.add(rbnEmbDesNo);
        rbnEmbDesNo.setText("No");
        rbnEmbDesNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnEmbDesNoActionPerformed(evt);
            }
        });

        lblAmenazaAborto.setText("Amenaza de Aborto");

        bgrAmenazaAborto.add(rbnAmAbSi);
        rbnAmAbSi.setText("Si");

        bgrAmenazaAborto.add(rbnAmAbNo);
        rbnAmAbNo.setSelected(true);
        rbnAmAbNo.setText("No");
        rbnAmAbNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnAmAbNoActionPerformed(evt);
            }
        });

        lblIntentoAborto.setText("Intento de Aborto");

        bgrIntentoAborto.add(rbnInAbSi);
        rbnInAbSi.setText("Si");

        bgrIntentoAborto.add(rbnInAbNo);
        rbnInAbNo.setSelected(true);
        rbnInAbNo.setText("No");
        rbnInAbNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnInAbNoActionPerformed(evt);
            }
        });

        lblSexoPreferido.setText("Sexo Preferido");

        bgrSexoPreferido.add(rbnSexPrefFem);
        rbnSexPrefFem.setText("Femenino");

        bgrSexoPreferido.add(rbnSexPrefMas);
        rbnSexPrefMas.setText("Masculino");
        rbnSexPrefMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnSexPrefMasActionPerformed(evt);
            }
        });

        lblDuracion.setText("Duracion");

        cmbDuracionEmbarazo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        txaAlteracionesPsiquicas.setColumns(20);
        txaAlteracionesPsiquicas.setLineWrap(true);
        txaAlteracionesPsiquicas.setRows(3);
        txaAlteracionesPsiquicas.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txaAlteracionesPsiquicas);

        lblHabitosToxicos.setText("Habitos Toxicos");

        lblEnfermedadesEnEmbarazo.setText("Enfermedades Durante Embarazo");

        jLabel8.setText("Alteraciones Psiquicas");

        chkEmbarazo.setSelected(true);
        chkEmbarazo.setText("Embarazo");
        chkEmbarazo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEmbarazoActionPerformed(evt);
            }
        });

        bgrSexoPreferido.add(rbnSexPrefInd);
        rbnSexPrefInd.setSelected(true);
        rbnSexPrefInd.setText("Indiferente");

        txaHabitosToxicos.setColumns(20);
        txaHabitosToxicos.setLineWrap(true);
        txaHabitosToxicos.setRows(3);
        txaHabitosToxicos.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txaHabitosToxicos);

        txaEnfDuranteEmbarazo.setColumns(20);
        txaEnfDuranteEmbarazo.setLineWrap(true);
        txaEnfDuranteEmbarazo.setRows(3);
        txaEnfDuranteEmbarazo.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txaEnfDuranteEmbarazo);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(jScrollPane2))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblAmenazaAborto)
                                        .addComponent(lblIntentoAborto)
                                        .addComponent(lblSexoPreferido)
                                        .addComponent(lblEmbarazoDeseado)
                                        .addComponent(lblDuracion))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cmbDuracionEmbarazo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(rbnEmbDesSi)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(rbnEmbDesNo))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(rbnAmAbSi)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(rbnAmAbNo))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(rbnInAbSi)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(rbnInAbNo))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(rbnSexPrefFem)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(rbnSexPrefMas)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(rbnSexPrefInd))
                                        .addComponent(chkEmbarazo)))
                                .addComponent(lblHabitosToxicos)
                                .addComponent(lblEnfermedadesEnEmbarazo)
                                .addComponent(jLabel8)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(chkEmbarazo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmbarazoDeseado)
                    .addComponent(rbnEmbDesSi)
                    .addComponent(rbnEmbDesNo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAmenazaAborto)
                    .addComponent(rbnAmAbSi)
                    .addComponent(rbnAmAbNo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIntentoAborto)
                    .addComponent(rbnInAbSi)
                    .addComponent(rbnInAbNo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSexoPreferido)
                    .addComponent(rbnSexPrefFem)
                    .addComponent(rbnSexPrefMas)
                    .addComponent(rbnSexPrefInd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDuracion)
                    .addComponent(cmbDuracionEmbarazo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHabitosToxicos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEnfermedadesEnEmbarazo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlAntPersMadreLayout = new javax.swing.GroupLayout(pnlAntPersMadre);
        pnlAntPersMadre.setLayout(pnlAntPersMadreLayout);
        pnlAntPersMadreLayout.setHorizontalGroup(
            pnlAntPersMadreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAntPersMadreLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAntPersMadreLayout.setVerticalGroup(
            pnlAntPersMadreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAntPersMadreLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        statusMessageLabel.setForeground(new java.awt.Color(0, 153, 51));
        statusMessageLabel.setText("Antecedentes personales madre registrado exitosamente.");

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/circle progress bar 20x20.png"))); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(229, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAntPersMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAntPersMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void rbnEmbDesNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnEmbDesNoActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_rbnEmbDesNoActionPerformed

private void rbnAmAbNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnAmAbNoActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_rbnAmAbNoActionPerformed

private void rbnInAbNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnInAbNoActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_rbnInAbNoActionPerformed

private void rbnSexPrefMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnSexPrefMasActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_rbnSexPrefMasActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void setEnabledState(Container cont, boolean state) {
        for (Component comp : cont.getComponents()) {
            if (comp instanceof JCheckBox) {
                continue;
            }
            if (comp instanceof Container) {
                setEnabledState((Container) comp, state);
            }
            comp.setEnabled(state);
        }
    }

    private void chkEmbarazoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEmbarazoActionPerformed
        // TODO add your handling code here:
        if (!chkEmbarazo.isSelected()) {
            setEnabledState(pnlAntPersMadre, false);
        } else {
            setEnabledState(pnlAntPersMadre, true);
        }
    }//GEN-LAST:event_chkEmbarazoActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        statusMessageLabel.setVisible(false);
        statusAnimationLabel.setVisible(false);
    }//GEN-LAST:event_formWindowOpened

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        ProgressCircle pc = new ProgressCircle(statusAnimationLabel);
        String trabajoCompletoMensaje = "Antecedentes Personales de Madre registrado exitosamente.";
        pc.start();

        // si todo esta bien
        statusMessageLabel.setVisible(false);
        
        if (modo != null && modo.equals(RegistroEdicionModo.REGISTRO)) { 
            antPersMadre = new AntPersMadre(null, chkEmbarazo.isSelected() ? 'S' : 'N', rbnAmAbSi.isSelected() ? 'S' : 'N',
                    rbnInAbSi.isSelected() ? 'S' : 'N', rbnEmbDesSi.isSelected() ? 'S' : 'N',
                    rbnSexPrefFem.isSelected() ? 'F' : (rbnSexPrefMas.isSelected() ? 'M' : 'I'));

            antPersMadre.setApmDuracionEmbarazo(Integer.parseInt(cmbDuracionEmbarazo.getSelectedItem().toString()));
            antPersMadre.setApmHabitosToxicos(txaHabitosToxicos.getText());
            antPersMadre.setApmEnfermedadesEmbarazo(!txaEnfDuranteEmbarazo.getText().isEmpty() ? txaEnfDuranteEmbarazo.getText() : null);
            antPersMadre.setApmAlteracionesPsiquicas(!txaAlteracionesPsiquicas.getText().isEmpty() ? txaAlteracionesPsiquicas.getText() : null);
            
            // se registra cuando le dan aceptar en ventana de historia clinica
        } else if (modo != null && modo.equals(RegistroEdicionModo.EDICION)) {
            apmEditar.setApmEmbarazo(chkEmbarazo.isSelected() ? 'S' : 'N');
            apmEditar.setApmAmenazaAborto(rbnAmAbSi.isSelected() ? 'S' : 'N');
            apmEditar.setApmIntentoAborto(rbnInAbSi.isSelected() ? 'S' : 'N');
            apmEditar.setApmEmbarazoDeseado(rbnEmbDesSi.isSelected() ? 'S' : 'N');
            apmEditar.setApmSexoPreferido(rbnSexPrefFem.isSelected() ? 'F' : (rbnSexPrefMas.isSelected() ? 'M' : 'I'));
            
            apmEditar.setApmDuracionEmbarazo(Integer.parseInt(cmbDuracionEmbarazo.getSelectedItem().toString()));
            apmEditar.setApmHabitosToxicos(txaHabitosToxicos.getText());
            apmEditar.setApmEnfermedadesEmbarazo(!txaEnfDuranteEmbarazo.getText().isEmpty() ? txaEnfDuranteEmbarazo.getText() : null);
            apmEditar.setApmAlteracionesPsiquicas(!txaAlteracionesPsiquicas.getText().isEmpty() ? txaAlteracionesPsiquicas.getText() : null);
            
            // se actualiza cuando le dan aceptar en ventana de historia clinica
        }

        pc.stop();
        JOptionPane.showMessageDialog(this, trabajoCompletoMensaje, "Antecedentes Personales Madre", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistroEdicionAntPersMadre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroEdicionAntPersMadre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroEdicionAntPersMadre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroEdicionAntPersMadre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                RegistroEdicionAntPersMadre dialog = new RegistroEdicionAntPersMadre(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrAmenazaAborto;
    private javax.swing.ButtonGroup bgrEmbarazoDeseado;
    private javax.swing.ButtonGroup bgrIntentoAborto;
    private javax.swing.ButtonGroup bgrSexoPreferido;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JCheckBox chkEmbarazo;
    private javax.swing.JComboBox cmbDuracionEmbarazo;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAmenazaAborto;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblEmbarazoDeseado;
    private javax.swing.JLabel lblEnfermedadesEnEmbarazo;
    private javax.swing.JLabel lblHabitosToxicos;
    private javax.swing.JLabel lblIntentoAborto;
    private javax.swing.JLabel lblSexoPreferido;
    private javax.swing.JPanel pnlAntPersMadre;
    private javax.swing.JRadioButton rbnAmAbNo;
    private javax.swing.JRadioButton rbnAmAbSi;
    private javax.swing.JRadioButton rbnEmbDesNo;
    private javax.swing.JRadioButton rbnEmbDesSi;
    private javax.swing.JRadioButton rbnInAbNo;
    private javax.swing.JRadioButton rbnInAbSi;
    private javax.swing.JRadioButton rbnSexPrefFem;
    private javax.swing.JRadioButton rbnSexPrefInd;
    private javax.swing.JRadioButton rbnSexPrefMas;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTextArea txaAlteracionesPsiquicas;
    private javax.swing.JTextArea txaEnfDuranteEmbarazo;
    private javax.swing.JTextArea txaHabitosToxicos;
    // End of variables declaration//GEN-END:variables
}
