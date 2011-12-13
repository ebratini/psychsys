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
 * RegistroEdicionHistoriaClinica.java
 *
 * Created on Nov 15, 2011, 4:38:09 PM
 */
package org.salvador_dali.psychsys.ui;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.salvador_dali.psychsys.business.DateFieldValidator;
import org.salvador_dali.psychsys.business.DateUtils;
import org.salvador_dali.psychsys.business.EmptyFieldValidator;
import org.salvador_dali.psychsys.business.EntityEditor;
import org.salvador_dali.psychsys.business.EntitySearcher;
import org.salvador_dali.psychsys.business.FieldValidator;
import org.salvador_dali.psychsys.business.FormFieldValidator;
import org.salvador_dali.psychsys.business.JpaEstudianteDao;
import org.salvador_dali.psychsys.business.JpaHistoriaClinicaDao;
import org.salvador_dali.psychsys.business.JpaUsuarioDao;
import org.salvador_dali.psychsys.model.JpaDao;
import org.salvador_dali.psychsys.model.entities.AntNeonatal;
import org.salvador_dali.psychsys.model.entities.AntPersMadre;
import org.salvador_dali.psychsys.model.entities.AntPsicomotrizLenguaje;
import org.salvador_dali.psychsys.model.entities.AntPsicosocialSexual;
import org.salvador_dali.psychsys.model.entities.AntRecienNacido;
import org.salvador_dali.psychsys.model.entities.Escolaridad;
import org.salvador_dali.psychsys.model.entities.Estudiante;
import org.salvador_dali.psychsys.model.entities.HistoriaClinica;
import org.salvador_dali.psychsys.model.entities.Usuario;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class RegistroEdicionHistoriaClinica extends javax.swing.JFrame {

    private RegistroEdicionModo modo = RegistroEdicionModo.REGISTRO;
    private JpaHistoriaClinicaDao jpaHicDao = new JpaHistoriaClinicaDao();
    private EntityEditor enityEditor = new EntityEditor(jpaHicDao);
    private Usuario usuario;
    private Estudiante estudiante;
    private HistoriaClinica hicAEditar;
    private AntPersMadre antPersMadre;
    private AntNeonatal antNeonatal;
    private AntRecienNacido antRecienNacido;
    private AntPsicomotrizLenguaje antPL;
    private AntPsicosocialSexual antPS;
    private Escolaridad escolaridad;

    /** Creates new form RegistroEdicionHistoriaClinica */
    public RegistroEdicionHistoriaClinica() {
        initComponents();
    }

    public RegistroEdicionHistoriaClinica(RegistroEdicionModo modo) {
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

        pnlInformacionHistoria = new javax.swing.JPanel();
        lblFecha = new javax.swing.JLabel();
        ftfFecha = new javax.swing.JFormattedTextField();
        lblFechaValMarker = new javax.swing.JLabel();
        lblEstudiante = new javax.swing.JLabel();
        txtEstudiante = new javax.swing.JTextField();
        lblEstudianteValMarker = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        pnlAntecedentesEscolaridad = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstAntecedentes = new javax.swing.JList();
        btnRegistrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar Historia Clinica Infantil");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/images/psych logo.png")));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pnlInformacionHistoria.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion Historia"));

        lblFecha.setText("Fecha");

        try {
            ftfFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblFechaValMarker.setForeground(new java.awt.Color(255, 51, 51));
        lblFechaValMarker.setLabelFor(ftfFecha);

        lblEstudiante.setText("Estudiante");

        txtEstudiante.setEditable(false);

        lblEstudianteValMarker.setForeground(new java.awt.Color(255, 51, 51));
        lblEstudianteValMarker.setLabelFor(txtEstudiante);

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/detalles.png"))); // NOI18N
        btnBuscar.setBorderPainted(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInformacionHistoriaLayout = new javax.swing.GroupLayout(pnlInformacionHistoria);
        pnlInformacionHistoria.setLayout(pnlInformacionHistoriaLayout);
        pnlInformacionHistoriaLayout.setHorizontalGroup(
            pnlInformacionHistoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionHistoriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformacionHistoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecha)
                    .addComponent(lblEstudiante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacionHistoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInformacionHistoriaLayout.createSequentialGroup()
                        .addComponent(ftfFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFechaValMarker))
                    .addGroup(pnlInformacionHistoriaLayout.createSequentialGroup()
                        .addComponent(txtEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEstudianteValMarker)))
                .addContainerGap())
        );
        pnlInformacionHistoriaLayout.setVerticalGroup(
            pnlInformacionHistoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionHistoriaLayout.createSequentialGroup()
                .addGroup(pnlInformacionHistoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEstudianteValMarker)
                    .addGroup(pnlInformacionHistoriaLayout.createSequentialGroup()
                        .addGroup(pnlInformacionHistoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ftfFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaValMarker)
                            .addComponent(lblFecha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInformacionHistoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                            .addComponent(txtEstudiante, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblEstudiante))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlAntecedentesEscolaridad.setBorder(javax.swing.BorderFactory.createTitledBorder("Antecedentes y Escolaridad"));

        lstAntecedentes.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Antecedentes Personales Madre", "Antecedentes Neonatal", "Antecedentes Recien Nacido", "Antecedentes Psicomotriz-Lenguaje", "Antecedentes Psicosocial-Sexual", "Escolaridad" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstAntecedentes);

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAntecedentesEscolaridadLayout = new javax.swing.GroupLayout(pnlAntecedentesEscolaridad);
        pnlAntecedentesEscolaridad.setLayout(pnlAntecedentesEscolaridadLayout);
        pnlAntecedentesEscolaridadLayout.setHorizontalGroup(
            pnlAntecedentesEscolaridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAntecedentesEscolaridadLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAntecedentesEscolaridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlAntecedentesEscolaridadLayout.setVerticalGroup(
            pnlAntecedentesEscolaridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAntecedentesEscolaridadLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnlAntecedentesEscolaridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlAntecedentesEscolaridadLayout.createSequentialGroup()
                        .addComponent(btnRegistrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        statusMessageLabel.setForeground(new java.awt.Color(0, 153, 51));
        statusMessageLabel.setText("Historia clinica registrada exitosamente.");

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlInformacionHistoria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                    .addComponent(pnlAntecedentesEscolaridad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar)))
                .addContainerGap())
            .addComponent(statusPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlInformacionHistoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAntecedentesEscolaridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        statusMessageLabel.setVisible(false);
        statusAnimationLabel.setVisible(false);
        Date currDate = new Date();
        ftfFecha.setText(String.format("%1$td%1$tm%1$tY", currDate));

        if (modo != null && modo.equals(RegistroEdicionModo.EDICION)) {
            btnBuscar.setEnabled(false);
        }
    }//GEN-LAST:event_formWindowOpened

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        // TODO: implementar correctamente el spinning progress bar
        ProgressCircle pc = new ProgressCircle(statusAnimationLabel);
        pc.start();
        String trabajoCompletoMensaje = "Historia clinica registrada exitosamente.";
        LimpiadorComponentes.limpiarValidationMarkers(this);
        if (!checkFormFields()) {
            statusMessageLabel.setText("Por favor corriga los campos marcados.");
            statusMessageLabel.setForeground(Color.red);
            new Thread(new LabelToolTipShower(statusMessageLabel, 3000)).start();
            return;
        }

        // si todo esta bien
        statusMessageLabel.setVisible(false);
        String accion = null;
        try {
            if (modo != null && modo.equals(RegistroEdicionModo.REGISTRO)) {
                accion = "crear";

                if (jpaHicDao.getHistoriaClinicaByEstudiante(estudiante) != null) {
                    throw new Exception("El estudiante ya tiene historia clinica");
                }

                HistoriaClinica hic = new HistoriaClinica(null, DateUtils.parseDate(ftfFecha.getText()), usuario.getUsrLogin(), new Date(), 'A');
                hic.setEstudiante(estudiante);

                jpaHicDao.persist(hic);

                // manejar los antecedentes
                if (antPersMadre != null || antNeonatal != null || antRecienNacido != null || antPL != null || antPS != null || escolaridad != null) {
                    List<HistoriaClinica> hics = (List<HistoriaClinica>) jpaHicDao.getHistoriasClinicasByFechaCreacion(DateUtils.parseDate(ftfFecha.getText()));
                    int last = hics.size() - 1;

                    HistoriaClinica hicConfirmada = hics.get(last);

                    if (antPersMadre != null) {
                        antPersMadre.setHistoriaClinica(hicConfirmada);
                        antPersMadre.setHicId(hicConfirmada.getHicId());
                        jpaHicDao.persist(antPersMadre);
                    }
                    if (antNeonatal != null) {
                        antNeonatal.setHistoriaClinica(hicConfirmada);
                        antNeonatal.setHicId(hicConfirmada.getHicId());
                        jpaHicDao.persist(antNeonatal);
                    }
                    if (antRecienNacido != null) {
                        antRecienNacido.setHistoriaClinica(hicConfirmada);
                        antRecienNacido.setHicId(hicConfirmada.getHicId());
                        jpaHicDao.persist(antRecienNacido);
                    }
                    if (antPL != null) {
                        antPL.setHistoriaClinica(hicConfirmada);
                        antPL.setHicId(hicConfirmada.getHicId());
                        jpaHicDao.persist(antPL);
                    }
                    if (antPS != null) {
                        antPS.setHistoriaClinica(hicConfirmada);
                        antPS.setHicId(hicConfirmada.getHicId());
                        jpaHicDao.persist(antPS);
                    }
                    if (escolaridad != null) {
                        escolaridad.setHistoriaClinica(hicConfirmada);
                        escolaridad.setHicId(hicConfirmada.getHicId());
                        jpaHicDao.persist(antPS);
                    }
                }
            } else if (modo != null && modo.equals(RegistroEdicionModo.EDICION)) {
                if (hicAEditar == null) {
                    throw new Exception("Historia clinica a editar no ha sido establecida");
                }
                accion = "editar";
                trabajoCompletoMensaje = trabajoCompletoMensaje.replace("registrada", "editada");

                hicAEditar.setEstudiante(estudiante);
                hicAEditar.setHicFechaCreacion(DateUtils.parseDate(ftfFecha.getText()));
                hicAEditar.setHicUpdateBy(usuario.getUsrLogin());
                hicAEditar.setHicUpdateDate(new Date());

                // manejar los antecedentes

                
                jpaHicDao.update(hicAEditar);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, String.format("<html><p>Error al " + accion + " registro de historia clinica<br /><br />%s</html>",
                    e.getMessage()), "Historia Clinica Infantil", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            
            return;
        }

        statusMessageLabel.setText(trabajoCompletoMensaje);
        statusMessageLabel.setForeground(Color.GREEN);
        statusMessageLabel.setVisible(true);
        new Thread(new LabelToolTipShower(statusMessageLabel, 3500)).start();
        LimpiadorComponentes.limpiarComponentes(this);
        ftfFecha.requestFocusInWindow();
        pc.stop();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        BusquedaRapida bre = new BusquedaRapida(this, true);
        bre.setTitle("Buscar Estudiante");
        bre.setEntitySearcher(new EntitySearcher.EstudianteEntitySearcher());
        bre.getLblEntidades().setText("Estudiantes");
        bre.setLocationRelativeTo(this);
        bre.setVisible(true);

        Object estId = bre.getEntitySelectedId();
        if (estId != null) {
            estudiante = new JpaEstudianteDao().findById(estId);
            txtEstudiante.setText(estudiante.toString());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        switch (lstAntecedentes.getSelectedIndex()) {
            case 0:
                RegistroEdicionAntPersMadre apm = new RegistroEdicionAntPersMadre(this, true);
                apm.setLocationRelativeTo(this);
                apm.setVisible(true);
                antPersMadre = apm.getAntPersMadre();
                break;
            case 1:
                RegistroEdicionAntNeonatal an = new RegistroEdicionAntNeonatal(this, true);
                an.setLocationRelativeTo(this);
                an.setVisible(true);
                antNeonatal = an.getAntNeonatal();
                break;
            case 2:
                RegistroEdicionAntRecienNacido arn = new RegistroEdicionAntRecienNacido(this, true);
                arn.setLocationRelativeTo(this);
                arn.setVisible(true);
                antRecienNacido = arn.getArn();
                break;
            case 3:
                RegistroEdicionAntPsicomotrizLenguaje apl = new RegistroEdicionAntPsicomotrizLenguaje(this, true);
                apl.setLocationRelativeTo(this);
                apl.setVisible(true);
                antPL = apl.getApl();
                break;
            case 4:
                RegistroEdicionAntPsicosocialSexual aps = new RegistroEdicionAntPsicosocialSexual(this, true);
                aps.setLocationRelativeTo(this);
                aps.setVisible(true);
                antPS = aps.getAps();
                break;
            case 5:
                RegistroEdicionEscolaridad esc = new RegistroEdicionEscolaridad(this, true);
                esc.setLocationRelativeTo(this);
                esc.setVisible(true);
                escolaridad = esc.getEsc();
                break;
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        switch (lstAntecedentes.getSelectedIndex()) {
            case 0:
                RegistroEdicionAntPersMadre apm = new RegistroEdicionAntPersMadre(this, true);
                apm.setModo(RegistroEdicionModo.EDICION);
                apm.setTitle(apm.getTitle().replace("Registrar", "Editar"));
                apm.setLocationRelativeTo(this);
                apm.setVisible(true);
                
                if (apm.getAntPersMadre() != null) {
                    enityEditor.agregarEditado(apm.getAntPersMadre());
                }
                break;
            case 1:
                RegistroEdicionAntNeonatal an = new RegistroEdicionAntNeonatal(this, true);
                an.setModo(RegistroEdicionModo.EDICION);
                an.setTitle(an.getTitle().replace("Registrar", "Editar"));
                an.setLocationRelativeTo(this);
                an.setVisible(true);
                break;
            case 2:
                RegistroEdicionAntRecienNacido arn = new RegistroEdicionAntRecienNacido(this, true);
                arn.setModo(RegistroEdicionModo.EDICION);
                arn.setTitle(arn.getTitle().replace("Registrar", "Editar"));
                arn.setLocationRelativeTo(this);
                arn.setVisible(true);
                break;
            case 3:
                RegistroEdicionAntPsicomotrizLenguaje apl = new RegistroEdicionAntPsicomotrizLenguaje(this, true);
                apl.setModo(RegistroEdicionModo.EDICION);
                apl.setTitle(apl.getTitle().replace("Registrar", "Editar"));
                apl.setLocationRelativeTo(this);
                apl.setVisible(true);
                break;
            case 4:
                RegistroEdicionAntPsicosocialSexual aps = new RegistroEdicionAntPsicosocialSexual(this, true);
                aps.setModo(RegistroEdicionModo.EDICION);
                aps.setTitle(aps.getTitle().replace("Registrar", "Editar"));
                aps.setLocationRelativeTo(this);
                aps.setVisible(true);
                break;
            case 5:
                RegistroEdicionEscolaridad esc = new RegistroEdicionEscolaridad(this, true);
                esc.setModo(RegistroEdicionModo.EDICION);
                esc.setTitle(esc.getTitle().replace("Registrar", "Editar"));
                esc.setLocationRelativeTo(this);
                esc.setVisible(true);
                break;
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        switch (lstAntecedentes.getSelectedIndex()) {
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private boolean checkFormFields() {
        boolean validFields = true;

        FieldValidator emptynessVal, dateVal;
        emptynessVal = new EmptyFieldValidator();
        dateVal = new DateFieldValidator();

        FieldValidator[] emptynessArr = new FieldValidator[]{emptynessVal};

        HashMap<JLabel, FieldValidator[]> campos = new HashMap<JLabel, FieldValidator[]>();
        campos.put(lblFechaValMarker, new FieldValidator[]{emptynessVal, dateVal});
        campos.put(lblEstudianteValMarker, emptynessArr);

        validFields &= FormFieldValidator.verifyFormFields(campos);

        return validFields;
    }

    public HistoriaClinica getHicAEditar() {
        return hicAEditar;
    }

    public void setHicAEditar(HistoriaClinica hicAEditar) {
        this.hicAEditar = hicAEditar;
    }

    public RegistroEdicionModo getModo() {
        return modo;
    }

    public void setModo(RegistroEdicionModo modo) {
        this.modo = modo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

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
            java.util.logging.Logger.getLogger(RegistroEdicionHistoriaClinica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroEdicionHistoriaClinica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroEdicionHistoriaClinica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroEdicionHistoriaClinica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RegistroEdicionHistoriaClinica().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JFormattedTextField ftfFecha;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEstudiante;
    private javax.swing.JLabel lblEstudianteValMarker;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFechaValMarker;
    private javax.swing.JList lstAntecedentes;
    private javax.swing.JPanel pnlAntecedentesEscolaridad;
    private javax.swing.JPanel pnlInformacionHistoria;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTextField txtEstudiante;
    // End of variables declaration//GEN-END:variables
}
