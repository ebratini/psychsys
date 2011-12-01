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
 * RegistroEdicionReferimiento.java
 *
 * Created on Nov 10, 2011, 10:18:15 AM
 */
package org.salvador_dali.psychsys.ui;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import org.salvador_dali.psychsys.business.DateUtils;
import org.salvador_dali.psychsys.business.EntitySearcher;
import org.salvador_dali.psychsys.business.JpaEstudianteDao;
import org.salvador_dali.psychsys.business.JpaReferimientoDao;
import org.salvador_dali.psychsys.model.entities.Estudiante;
import org.salvador_dali.psychsys.model.entities.Referimiento;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class RegistroEdicionReferimiento extends javax.swing.JFrame {

    private RegistroEdicionModo modo = RegistroEdicionModo.REGISTRO;
    private JpaEstudianteDao jpaEstDao = new JpaEstudianteDao();
    private Referimiento refAEditar;

    /** Creates new form RegistroEdicionReferimiento */
    public RegistroEdicionReferimiento() {
        initComponents();
    }

    public RegistroEdicionReferimiento(RegistroEdicionModo modo) {
        this();
        this.modo = modo;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblEstudiante = new javax.swing.JLabel();
        txtEstudiante = new javax.swing.JTextField();
        lblAnioEscolar = new javax.swing.JLabel();
        lblReferidor = new javax.swing.JLabel();
        txtReferidor = new javax.swing.JTextField();
        btnBuscarEstudiante = new javax.swing.JButton();
        lblMotivoReferimiento = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaMotivoReferimiento = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaAccionesRefeidor = new javax.swing.JTextArea();
        lblAccionesReferidor = new javax.swing.JLabel();
        lblEstudianteValMarker = new javax.swing.JLabel();
        lblMotivoRefValMarker = new javax.swing.JLabel();
        lblReferidorValMarker = new javax.swing.JLabel();
        ftfAnioEscolar = new javax.swing.JFormattedTextField();
        lblAnioEscolarValMarker = new javax.swing.JLabel();
        ftfFecha = new javax.swing.JFormattedTextField();
        lblFecha = new javax.swing.JLabel();
        lblFechaValMarker = new javax.swing.JLabel();
        lblAnioEscolarValMarker1 = new javax.swing.JLabel();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crear Referimiento");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/images/psych logo.png")));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblEstudiante.setText("Estudiante");

        txtEstudiante.setEditable(false);

        lblAnioEscolar.setText("Año Escolar");

        lblReferidor.setText("Referidor");

        btnBuscarEstudiante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/detalles.png"))); // NOI18N
        btnBuscarEstudiante.setBorderPainted(false);
        btnBuscarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEstudianteActionPerformed(evt);
            }
        });

        lblMotivoReferimiento.setText("Motivo Referimiento");

        txaMotivoReferimiento.setColumns(20);
        txaMotivoReferimiento.setRows(5);
        jScrollPane1.setViewportView(txaMotivoReferimiento);

        txaAccionesRefeidor.setColumns(20);
        txaAccionesRefeidor.setRows(5);
        jScrollPane2.setViewportView(txaAccionesRefeidor);

        lblAccionesReferidor.setText("Sus Acciones");
        lblAccionesReferidor.setToolTipText("");

        lblEstudianteValMarker.setForeground(new java.awt.Color(255, 51, 51));
        lblEstudianteValMarker.setLabelFor(lblEstudiante);
        lblEstudianteValMarker.setToolTipText("");

        lblMotivoRefValMarker.setForeground(new java.awt.Color(255, 51, 51));
        lblMotivoRefValMarker.setLabelFor(txaMotivoReferimiento);
        lblMotivoRefValMarker.setToolTipText("");

        lblReferidorValMarker.setForeground(new java.awt.Color(255, 51, 51));
        lblReferidorValMarker.setLabelFor(txtReferidor);
        lblReferidorValMarker.setToolTipText("");

        try {
            ftfAnioEscolar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblAnioEscolarValMarker.setForeground(new java.awt.Color(255, 51, 51));
        lblAnioEscolarValMarker.setLabelFor(ftfAnioEscolar);
        lblAnioEscolarValMarker.setToolTipText("");

        try {
            ftfFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblFecha.setText("Fecha");

        lblFechaValMarker.setForeground(new java.awt.Color(255, 51, 51));
        lblFechaValMarker.setLabelFor(ftfFecha);

        lblAnioEscolarValMarker1.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEstudiante)
                    .addComponent(lblAccionesReferidor)
                    .addComponent(lblReferidor)
                    .addComponent(lblAnioEscolar)
                    .addComponent(lblMotivoReferimiento)
                    .addComponent(lblFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ftfAnioEscolar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAnioEscolarValMarker1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ftfFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFechaValMarker))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(txtReferidor)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMotivoRefValMarker)
                    .addComponent(lblReferidorValMarker)
                    .addComponent(lblEstudianteValMarker)
                    .addComponent(lblAnioEscolarValMarker))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblAnioEscolarValMarker)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEstudianteValMarker))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFecha)
                            .addComponent(ftfFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaValMarker))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAnioEscolar)
                            .addComponent(ftfAnioEscolar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAnioEscolarValMarker1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblEstudiante)
                                .addComponent(txtEstudiante)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReferidor)
                    .addComponent(txtReferidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblReferidorValMarker))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMotivoReferimiento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAccionesReferidor)))
                    .addComponent(lblMotivoRefValMarker))
                .addGap(11, 11, 11))
        );

        statusMessageLabel.setForeground(new java.awt.Color(0, 153, 51));
        statusMessageLabel.setText("Referimiento creado exitosamente.");

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/circle progress bar 20x20.png"))); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 237, Short.MAX_VALUE)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(statusPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(272, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        statusMessageLabel.setVisible(false);
        statusAnimationLabel.setVisible(false);
        LimpiadorComponentes.limpiarValidationMarkers(this);
        Date currDate = new Date();
        ftfFecha.setText(String.format("%1$td%1$tm%1$tY", currDate));
        Integer year = new Integer(String.format("%tY", currDate));
        ftfAnioEscolar.setText(year.toString() + (year + 1));
    }//GEN-LAST:event_formWindowOpened

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        // TODO: implementar correctamente el spinning progress bar
        ProgressCircle pc = new ProgressCircle(statusAnimationLabel);
        String trabajoCompletoMensaje = "Referimiento registrado exitosamente.";
        pc.start();
        LimpiadorComponentes.limpiarValidationMarkers(this);
        if (!checkFormFields()) {
            statusMessageLabel.setText("Por favor corriga los campos marcados.");
            statusMessageLabel.setForeground(Color.red);
            new Thread(new LabelToolTipShower(statusMessageLabel, 3000)).start();
            return;
        }
        
        // si todo esta bien
        statusMessageLabel.setVisible(false);
        
        // creando el objeto referimiento
        Referimiento referimiento = new Referimiento(null, DateUtils.parseDate(ftfFecha.getText()), ftfAnioEscolar.getText(), txtReferidor.getText(), txaMotivoReferimiento.getText(), 'A');
        referimiento.setRefAccionesReferidor((!txaAccionesRefeidor.getText().isEmpty() ? txaAccionesRefeidor.getText() : null));
        
        JpaReferimientoDao jpaRefDao = new JpaReferimientoDao();
        try {
            jpaRefDao.persist(referimiento);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, String.format("<html><p>Error al crear registro de referimiento<br />%s</p></html>",
                    e.getMessage()), "Registrar Referimiento", JOptionPane.INFORMATION_MESSAGE);
        }

        statusMessageLabel.setText(trabajoCompletoMensaje);
        statusMessageLabel.setForeground(Color.GREEN);
        statusMessageLabel.setVisible(true);
        new Thread(new LabelToolTipShower(statusMessageLabel, 3500)).start();
        LimpiadorComponentes.limpiarComponentes(this);
        ftfFecha.requestFocusInWindow();
        pc.stop();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnBuscarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEstudianteActionPerformed
        // TODO add your handling code here:
        BusquedaRapida bre = new BusquedaRapida(this, true);
        bre.setTitle("Buscar Estudiante");
        bre.setEntitySearcher(new EntitySearcher.EstudianteEntitySearcher());
        bre.getLblEntidades().setText("Estudiantes");
        bre.setLocationRelativeTo(this);
        bre.setVisible(true);
        
        Object estId = bre.getEntitySelectedId();        
        if (estId != null) {
            Estudiante estSelected = jpaEstDao.findById(estId);
            txtEstudiante.setText(estSelected.toString());
        }
    }//GEN-LAST:event_btnBuscarEstudianteActionPerformed

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
            java.util.logging.Logger.getLogger(RegistroEdicionReferimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroEdicionReferimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroEdicionReferimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroEdicionReferimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RegistroEdicionReferimiento().setVisible(true);
            }
        });
    }

    public RegistroEdicionModo getModo() {
        return modo;
    }

    public void setModo(RegistroEdicionModo modo) {
        this.modo = modo;
    }

    public Referimiento getRefAEditar() {
        return refAEditar;
    }

    public void setRefAEditar(Referimiento refAEditar) {
        this.refAEditar = refAEditar;
    }

    private boolean checkFormFields() {
        boolean validFields = true;

        /*FieldValidator emptynessVal, phoneVal, emailVal;
        emptynessVal = new EmptyFieldValidator();
        phoneVal = new PhoneFieldValidator();
        emailVal = new EmailFieldValidator();
        
        FieldValidator[] emptynessArr = new FieldValidator[]{emptynessVal};
        
        HashMap<JLabel, FieldValidator[]> campos = new HashMap<JLabel, FieldValidator[]>();
        campos.put(lblDniValMarker, emptynessArr);
        campos.put(lblPrimerNombreValMarker, emptynessArr);
        campos.put(lblPrimerApellidoValMarker, emptynessArr);
        campos.put(lblSegApellidoValMarker, emptynessArr);
        campos.put(lblNacionalidadValMarker, emptynessArr);
        campos.put(lblTelValMarker, new FieldValidator[]{emptynessVal, phoneVal});
        
        if (!((JTextComponent) lblEmailValMarker.getLabelFor()).getText().isEmpty()) {
        campos.put(lblEmailValMarker, new FieldValidator[]{emailVal});
        }
        campos.put(lblDirValMarker, emptynessArr);
        
        validFields = FormFieldValidator.verifyFormFields(campos);*/

        return validFields;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscarEstudiante;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JFormattedTextField ftfAnioEscolar;
    private javax.swing.JFormattedTextField ftfFecha;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAccionesReferidor;
    private javax.swing.JLabel lblAnioEscolar;
    private javax.swing.JLabel lblAnioEscolarValMarker;
    private javax.swing.JLabel lblAnioEscolarValMarker1;
    private javax.swing.JLabel lblEstudiante;
    private javax.swing.JLabel lblEstudianteValMarker;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFechaValMarker;
    private javax.swing.JLabel lblMotivoRefValMarker;
    private javax.swing.JLabel lblMotivoReferimiento;
    private javax.swing.JLabel lblReferidor;
    private javax.swing.JLabel lblReferidorValMarker;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTextArea txaAccionesRefeidor;
    private javax.swing.JTextArea txaMotivoReferimiento;
    private javax.swing.JTextField txtEstudiante;
    private javax.swing.JTextField txtReferidor;
    // End of variables declaration//GEN-END:variables
}
