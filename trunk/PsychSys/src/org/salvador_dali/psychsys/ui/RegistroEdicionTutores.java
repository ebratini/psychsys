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
 * RegistroEdicionTutores.java
 *
 * Created on Nov 10, 2011, 3:51:06 PM
 */
package org.salvador_dali.psychsys.ui;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;
import org.salvador_dali.psychsys.business.EmailFieldValidator;
import org.salvador_dali.psychsys.business.EmptyFieldValidator;
import org.salvador_dali.psychsys.business.FieldValidator;
import org.salvador_dali.psychsys.business.FormFieldValidator;
import org.salvador_dali.psychsys.business.JpaTutorDao;
import org.salvador_dali.psychsys.business.PhoneFieldValidator;
import org.salvador_dali.psychsys.model.TutorDao;
import org.salvador_dali.psychsys.model.entities.Tutor;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class RegistroEdicionTutores extends javax.swing.JFrame {

    private RegistroEdicionModo modo = RegistroEdicionModo.REGISTRO;
    private Tutor tutorAEditar;

    /** Creates new form RegistroEdicionTutores */
    public RegistroEdicionTutores() {
        initComponents();
    }

    public RegistroEdicionTutores(RegistroEdicionModo modo) {
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

        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        pnlTutInfoPersonal = new javax.swing.JPanel();
        lblDni = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        lblTipoDni = new javax.swing.JLabel();
        cmbTipoDni = new javax.swing.JComboBox();
        lblPrimerNombre = new javax.swing.JLabel();
        txtPrimerNombre = new javax.swing.JTextField();
        lblSegundoNombre = new javax.swing.JLabel();
        txtSegundoNombre = new javax.swing.JTextField();
        lblPrimerApellido = new javax.swing.JLabel();
        txtPrimerApellido = new javax.swing.JTextField();
        lblSegundoApellido = new javax.swing.JLabel();
        txtSegundoApellido = new javax.swing.JTextField();
        lblNacionalidad = new javax.swing.JLabel();
        txtNacionalidad = new javax.swing.JTextField();
        lblGenero = new javax.swing.JLabel();
        cmbGenero = new javax.swing.JComboBox();
        lblEstadoCivil = new javax.swing.JLabel();
        cmbEstadoCivil = new javax.swing.JComboBox();
        lblDniValMarker = new javax.swing.JLabel();
        lblPrimerNombreValMarker = new javax.swing.JLabel();
        lblPrimerApellidoValMarker = new javax.swing.JLabel();
        lblSegApellidoValMarker = new javax.swing.JLabel();
        lblNacionalidadValMarker = new javax.swing.JLabel();
        pnlTutInfoContacto = new javax.swing.JPanel();
        lblTelefono = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        lblTelValMarker = new javax.swing.JLabel();
        lblEmailValMarker = new javax.swing.JLabel();
        lblDirValMarker = new javax.swing.JLabel();
        ftfTelefono = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaDireccion = new javax.swing.JTextArea();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registrar Tutor");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/images/psych logo.png")));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.setNextFocusableComponent(btnCancelar);
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.setNextFocusableComponent(txtDni);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlTutInfoPersonal.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion Personal"));

        lblDni.setText("DNI");

        txtDni.setNextFocusableComponent(cmbTipoDni);

        lblTipoDni.setText("Tipo DNI");

        cmbTipoDni.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cedula", "Pasaporte", "NSS" }));
        cmbTipoDni.setNextFocusableComponent(txtPrimerNombre);

        lblPrimerNombre.setText("Primer Nombre");

        txtPrimerNombre.setNextFocusableComponent(txtSegundoNombre);

        lblSegundoNombre.setText("Segundo Nombre");

        txtSegundoNombre.setNextFocusableComponent(txtPrimerApellido);

        lblPrimerApellido.setText("Primer Apellido");

        txtPrimerApellido.setNextFocusableComponent(txtSegundoApellido);

        lblSegundoApellido.setText("Segundo Apellido");

        txtSegundoApellido.setNextFocusableComponent(txtNacionalidad);

        lblNacionalidad.setText("Nacionalidad");

        txtNacionalidad.setNextFocusableComponent(cmbGenero);

        lblGenero.setText("Genero");

        cmbGenero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Femenino" }));
        cmbGenero.setNextFocusableComponent(cmbEstadoCivil);

        lblEstadoCivil.setText("Estado Civil");

        cmbEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Casado(a)", "Soltero(a)", "Divorciado(a)", "Viudo(a)" }));
        cmbEstadoCivil.setNextFocusableComponent(ftfTelefono);

        lblDniValMarker.setForeground(new java.awt.Color(255, 51, 51));
        lblDniValMarker.setLabelFor(txtDni);
        lblDniValMarker.setText("*");

        lblPrimerNombreValMarker.setForeground(new java.awt.Color(255, 51, 51));
        lblPrimerNombreValMarker.setLabelFor(txtPrimerNombre);
        lblPrimerNombreValMarker.setText("*");

        lblPrimerApellidoValMarker.setForeground(new java.awt.Color(255, 51, 51));
        lblPrimerApellidoValMarker.setLabelFor(txtPrimerApellido);
        lblPrimerApellidoValMarker.setText("*");

        lblSegApellidoValMarker.setForeground(new java.awt.Color(255, 51, 51));
        lblSegApellidoValMarker.setLabelFor(txtSegundoApellido);
        lblSegApellidoValMarker.setText("*");

        lblNacionalidadValMarker.setForeground(new java.awt.Color(255, 51, 51));
        lblNacionalidadValMarker.setLabelFor(txtNacionalidad);
        lblNacionalidadValMarker.setText("*");
        lblNacionalidadValMarker.setToolTipText(null);

        javax.swing.GroupLayout pnlTutInfoPersonalLayout = new javax.swing.GroupLayout(pnlTutInfoPersonal);
        pnlTutInfoPersonal.setLayout(pnlTutInfoPersonalLayout);
        pnlTutInfoPersonalLayout.setHorizontalGroup(
            pnlTutInfoPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTutInfoPersonalLayout.createSequentialGroup()
                .addGroup(pnlTutInfoPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDni)
                    .addComponent(lblTipoDni)
                    .addComponent(lblSegundoNombre)
                    .addComponent(lblPrimerApellido)
                    .addComponent(lblPrimerNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTutInfoPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPrimerApellido)
                    .addComponent(cmbTipoDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlTutInfoPersonalLayout.createSequentialGroup()
                        .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDniValMarker))
                    .addComponent(txtPrimerNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(txtSegundoNombre))
                .addGroup(pnlTutInfoPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTutInfoPersonalLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblPrimerNombreValMarker)
                        .addGap(10, 10, 10)
                        .addGroup(pnlTutInfoPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGenero)
                            .addComponent(lblEstadoCivil)
                            .addComponent(lblSegundoApellido)
                            .addComponent(lblNacionalidad))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTutInfoPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTutInfoPersonalLayout.createSequentialGroup()
                                .addGroup(pnlTutInfoPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNacionalidad)
                                    .addComponent(txtSegundoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlTutInfoPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSegApellidoValMarker)
                                    .addComponent(lblNacionalidadValMarker)))
                            .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlTutInfoPersonalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPrimerApellidoValMarker)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTutInfoPersonalLayout.setVerticalGroup(
            pnlTutInfoPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTutInfoPersonalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTutInfoPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlTutInfoPersonalLayout.createSequentialGroup()
                        .addGroup(pnlTutInfoPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSegundoApellido)
                            .addComponent(txtSegundoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSegApellidoValMarker))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTutInfoPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNacionalidad)
                            .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNacionalidadValMarker))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTutInfoPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGenero)
                            .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTutInfoPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEstadoCivil)
                            .addComponent(cmbEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlTutInfoPersonalLayout.createSequentialGroup()
                        .addGroup(pnlTutInfoPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDni)
                            .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDniValMarker))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTutInfoPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTipoDni)
                            .addComponent(cmbTipoDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTutInfoPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPrimerNombre)
                            .addComponent(txtPrimerNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPrimerNombreValMarker))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTutInfoPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSegundoNombre)
                            .addComponent(txtSegundoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTutInfoPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrimerApellido)
                    .addComponent(txtPrimerApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrimerApellidoValMarker))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlTutInfoContacto.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion de Contacto"));

        lblTelefono.setText("Telefono");

        lblEmail.setText("Email");

        lblDireccion.setText("Direccion");

        lblTelValMarker.setForeground(new java.awt.Color(255, 51, 51));
        lblTelValMarker.setLabelFor(ftfTelefono);
        lblTelValMarker.setText("*");

        lblEmailValMarker.setForeground(new java.awt.Color(255, 51, 51));
        lblEmailValMarker.setLabelFor(txtEmail);
        lblEmailValMarker.setText("*");

        lblDirValMarker.setForeground(new java.awt.Color(255, 51, 51));
        lblDirValMarker.setLabelFor(txaDireccion);
        lblDirValMarker.setText("*");

        try {
            ftfTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###) ###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfTelefono.setNextFocusableComponent(txtEmail);

        txaDireccion.setColumns(20);
        txaDireccion.setLineWrap(true);
        txaDireccion.setRows(3);
        txaDireccion.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txaDireccion);

        javax.swing.GroupLayout pnlTutInfoContactoLayout = new javax.swing.GroupLayout(pnlTutInfoContacto);
        pnlTutInfoContacto.setLayout(pnlTutInfoContactoLayout);
        pnlTutInfoContactoLayout.setHorizontalGroup(
            pnlTutInfoContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTutInfoContactoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTutInfoContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTelefono)
                    .addComponent(lblEmail)
                    .addComponent(lblDireccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTutInfoContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTutInfoContactoLayout.createSequentialGroup()
                        .addComponent(ftfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTelValMarker)
                        .addContainerGap(423, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTutInfoContactoLayout.createSequentialGroup()
                        .addGroup(pnlTutInfoContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmail)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTutInfoContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmailValMarker)
                            .addComponent(lblDirValMarker))
                        .addGap(206, 206, 206))))
        );
        pnlTutInfoContactoLayout.setVerticalGroup(
            pnlTutInfoContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTutInfoContactoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTutInfoContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(ftfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelValMarker))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTutInfoContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmailValMarker))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTutInfoContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDireccion)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDirValMarker))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlTutInfoContacto, javax.swing.GroupLayout.Alignment.LEADING, 0, 618, Short.MAX_VALUE)
                    .addComponent(pnlTutInfoPersonal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTutInfoPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlTutInfoContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        statusMessageLabel.setForeground(new java.awt.Color(0, 153, 51));
        statusMessageLabel.setText("Tutor registrado exitosamente.");

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/circle progress bar 20x20.png"))); // NOI18N
        statusAnimationLabel.setToolTipText("trabajando");

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 472, Short.MAX_VALUE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(488, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addContainerGap())
            .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        // TODO: implementar correctamente el spinning progress bar
        ProgressCircle pc = new ProgressCircle(statusAnimationLabel);
        String trabajoCompletoMensaje = "Tutor registrado exitosamente.";
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

        // crear o editar el objeto tutor
        TutorDao tutDao = new JpaTutorDao();

        if (tutDao.getTutorByDNI(txtDni.getText()) != null) {
            lblDniValMarker.setVisible(true);
            statusMessageLabel.setText("Ya existe un tutor con DNI digitado.");
            statusMessageLabel.setForeground(Color.red);
            statusMessageLabel.setVisible(true);
            return;
        }


        Tutor tutor = new Tutor(null, txtDni.getText(), cmbTipoDni.getSelectedItem().toString(), txtPrimerApellido.getText(), txtSegundoApellido.getText(),
                txtPrimerNombre.getText(), txaDireccion.getText(), txtNacionalidad.getText(), cmbGenero.getSelectedItem().toString().charAt(0),
                cmbEstadoCivil.getSelectedItem().toString(), 'A');

        tutor.setTutSegundoNombre((!txtSegundoNombre.getText().isEmpty() ? txtSegundoNombre.getText() : null));
        tutor.setTutTelefono((!ftfTelefono.getText().isEmpty() ? ftfTelefono.getText().replaceAll("-", "") : null));
        tutor.setTutEmail((!txtEmail.getText().isEmpty() ? txtEmail.getText() : null));

        // crear el objeto tutorDao e invocar el metodo persist del mismo     
        if (this.modo.equals(RegistroEdicionModo.REGISTRO)) {
            tutDao.persist(tutor);
        } else {
            if (tutorAEditar != null) {
                tutor.setTutId(tutorAEditar.getTutId());
                tutDao.update(tutor);
                trabajoCompletoMensaje = trabajoCompletoMensaje.replace("registrado", "editado");

            } else {
                statusMessageLabel.setText("Error al editar tutor, favor cierre y vuelva a intentarlo.");
                statusMessageLabel.setForeground(Color.red);
                return;
            }
        }

        statusMessageLabel.setText(trabajoCompletoMensaje);
        statusMessageLabel.setForeground(Color.GREEN);
        statusMessageLabel.setVisible(true);
        new Thread(new LabelToolTipShower(statusMessageLabel, 3500)).start();
        LimpiadorComponentes.limpiarComponentes(this);
        txtDni.requestFocusInWindow();
        pc.stop();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        statusMessageLabel.setVisible(false);
        statusAnimationLabel.setVisible(false);
        LimpiadorComponentes.limpiarValidationMarkers(this);
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(RegistroEdicionTutores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroEdicionTutores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroEdicionTutores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroEdicionTutores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new RegistroEdicionTutores().setVisible(true);
            }
        });
    }

    private boolean checkFormFields() {
        boolean validFields = true;

        FieldValidator emptynessVal, phoneVal, emailVal;
        emptynessVal = new EmptyFieldValidator();
        phoneVal = new PhoneFieldValidator("\\(\\d\\d\\d\\) \\d\\d\\d-\\d\\d\\d\\d");
        emailVal = new EmailFieldValidator();

        FieldValidator[] emptynessArr = new FieldValidator[]{emptynessVal};

        HashMap<JLabel, FieldValidator[]> campos = new HashMap<JLabel, FieldValidator[]>();
        
        // validando info personal
        campos.put(lblDniValMarker, emptynessArr);
        campos.put(lblPrimerNombreValMarker, emptynessArr);
        campos.put(lblPrimerApellidoValMarker, emptynessArr);
        campos.put(lblSegApellidoValMarker, emptynessArr);
        campos.put(lblNacionalidadValMarker, emptynessArr);
        
        // validando info contacto
        campos.put(lblTelValMarker, new FieldValidator[]{emptynessVal, phoneVal});

        if (!((JTextComponent) lblEmailValMarker.getLabelFor()).getText().isEmpty()) {
            campos.put(lblEmailValMarker, new FieldValidator[]{emailVal});
        }
        campos.put(lblDirValMarker, emptynessArr);

        validFields = FormFieldValidator.verifyFormFields(campos);

        return validFields;
    }

    public RegistroEdicionModo getModo() {
        return modo;
    }

    public void setModo(RegistroEdicionModo modo) {
        this.modo = modo;
    }

    public Tutor getTutorAEditar() {
        return tutorAEditar;
    }

    public void setTutorAEditar(Tutor tutorAEditar) {
        this.tutorAEditar = tutorAEditar;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox cmbEstadoCivil;
    private javax.swing.JComboBox cmbGenero;
    private javax.swing.JComboBox cmbTipoDni;
    private javax.swing.JFormattedTextField ftfTelefono;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDirValMarker;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblDniValMarker;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmailValMarker;
    private javax.swing.JLabel lblEstadoCivil;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblNacionalidad;
    private javax.swing.JLabel lblNacionalidadValMarker;
    private javax.swing.JLabel lblPrimerApellido;
    private javax.swing.JLabel lblPrimerApellidoValMarker;
    private javax.swing.JLabel lblPrimerNombre;
    private javax.swing.JLabel lblPrimerNombreValMarker;
    private javax.swing.JLabel lblSegApellidoValMarker;
    private javax.swing.JLabel lblSegundoApellido;
    private javax.swing.JLabel lblSegundoNombre;
    private javax.swing.JLabel lblTelValMarker;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipoDni;
    private javax.swing.JPanel pnlTutInfoContacto;
    private javax.swing.JPanel pnlTutInfoPersonal;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTextArea txaDireccion;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNacionalidad;
    private javax.swing.JTextField txtPrimerApellido;
    private javax.swing.JTextField txtPrimerNombre;
    private javax.swing.JTextField txtSegundoApellido;
    private javax.swing.JTextField txtSegundoNombre;
    // End of variables declaration//GEN-END:variables
}
