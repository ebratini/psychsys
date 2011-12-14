/*
 *  The MIT License
 * 
 *  Copyright 2011 Edwin Bratini <edwin.bratini@gmail.com>.
 * 
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 * 
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 * 
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package org.salvador_dali.psychsys.ui;

import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import org.salvador_dali.psychsys.model.entities.Usuario;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import org.pushingpixels.flamingo.api.common.CommandButtonDisplayState;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.JCommandButton.CommandButtonKind;
import org.pushingpixels.flamingo.api.common.JCommandButtonPanel;
import org.pushingpixels.flamingo.api.common.JCommandMenuButton;
import org.pushingpixels.flamingo.api.common.RichTooltip;
import org.pushingpixels.flamingo.api.common.icon.EmptyResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.common.popup.JCommandPopupMenu;
import org.pushingpixels.flamingo.api.common.popup.JPopupPanel;
import org.pushingpixels.flamingo.api.common.popup.PopupPanelCallback;
import org.pushingpixels.flamingo.api.ribbon.JRibbon;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryFooter;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryPrimary;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntrySecondary;
import org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority;
import org.pushingpixels.flamingo.api.ribbon.RibbonTask;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.IconRibbonBandResizePolicy;
import org.pushingpixels.flamingo.api.ribbon.resize.RibbonBandResizePolicy;
import org.pushingpixels.flamingo.internal.ui.ribbon.JBandControlPanel;
import org.salvador_dali.psychsys.business.EntitySearcher;
import org.salvador_dali.psychsys.business.JpaReferimientoDao;
import org.salvador_dali.psychsys.model.entities.Caso;
import org.salvador_dali.psychsys.model.entities.Estudiante;
import org.salvador_dali.psychsys.model.entities.HistoriaClinica;
import org.salvador_dali.psychsys.model.entities.PruebaPsicologica;
import org.salvador_dali.psychsys.model.entities.Referimiento;
import org.salvador_dali.psychsys.model.entities.Tutor;
import test.svg.transcoded.document_new;
import test.svg.transcoded.document_open;
import test.svg.transcoded.document_print;
import test.svg.transcoded.document_properties;
import test.svg.transcoded.document_save;
import test.svg.transcoded.document_save_as;
import test.svg.transcoded.mail_forward;
import test.svg.transcoded.mail_message_new;
import test.svg.transcoded.network_wireless;
import test.svg.transcoded.printer;
import test.svg.transcoded.system_log_out;
import test.svg.transcoded.x_office_document;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class PsychSysDesktop extends JRibbonFrame {

    public static int appInstances;
    // user logged
    private Usuario usuario;
    // button action handler
    private ButtonActionHandler buttonActHandler = new ButtonActionHandler();
    // body content
    //private WelcomePage welcomePage = new WelcomePage();
    private JTabbedPane pnlBody;
    // for the status bar
    private JPanel pnlStatusBar;
    private JLabel statusMessageLabel = new JLabel("Ready");
    private JLabel taskSelectedLabel = new JLabel();
    private JLabel usuarioLogueado = new JLabel("Usuario no logueado");
    private JLabel timeDate = new JLabel("Time/Date");
    // jpas
    private JpaReferimientoDao jpaRefDao = new JpaReferimientoDao();

    public PsychSysDesktop() {
        super("PsychSys");
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                //super.windowClosing(e);
                doExit();
                //((JCommandButton) getRibbon().getTask(0).getBand(0).getControlPanel().getComponent(1)).doActionClick();
            }
        });
        initComponents();
    }

    public static void main(String[] args) {
        PsychSysDesktop sd = new PsychSysDesktop();
        sd.setLocationByPlatform(true);
        sd.setLocationRelativeTo(null);
        sd.setVisible(true);
    }

    private ResizableIcon getResizableIconFromResource(String resource) {
        return ImageWrapperResizableIcon.getIcon(getClass().getResource(resource), new Dimension(48, 48));
    }

    private void initBodyContent() {
        final JPopupMenu tabPopupMenu = new JPopupMenu() {

            {
                add(new JMenuItem("Cerrar Tab") {

                    {
                        addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (pnlBody.getSelectedIndex() != 0) {
                                    pnlBody.remove(pnlBody.getSelectedIndex());
                                }
                            }
                        });
                    }
                });
            }
        };
        pnlBody = new JTabbedPane() {

            {
                addTab("Start", new JScrollPane(new WelcomePage()));
                addMouseListener(new MouseAdapter() {

                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.isPopupTrigger()) {
                            if (pnlBody.getSelectedIndex() == 0) {
                                tabPopupMenu.getComponent(0).setEnabled(false);
                            } else {
                                tabPopupMenu.getComponent(0).setEnabled(true);
                            }
                            tabPopupMenu.show(e.getComponent(), e.getX(), e.getY());
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (e.isPopupTrigger()) {
                            if (pnlBody.getSelectedIndex() == 0) {
                                tabPopupMenu.getComponent(0).setEnabled(false);
                            } else {
                                tabPopupMenu.getComponent(0).setEnabled(true);
                            }
                            tabPopupMenu.show(e.getComponent(), e.getX(), e.getY());
                        }
                    }
                });
            }
        };
        add(pnlBody);
    }

    private void initTimeDate() {
        new TimeDateShower(timeDate).start();
    }

    private void initStatusBar() {
        pnlStatusBar = new JPanel(new BorderLayout());
        pnlStatusBar.setPreferredSize(new Dimension(400, 25));
        pnlStatusBar.add(statusMessageLabel, BorderLayout.WEST);

        usuarioLogueado.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        taskSelectedLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        timeDate.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        JPanel pnl = new JPanel(new BorderLayout(2, 0));
        pnl.add(taskSelectedLabel, BorderLayout.WEST);
        pnl.add(usuarioLogueado, BorderLayout.CENTER);
        pnl.add(timeDate, BorderLayout.EAST);

        pnlStatusBar.add(pnl, BorderLayout.EAST);

        pnlStatusBar.setBorder(new EtchedBorder());
        add(pnlStatusBar, BorderLayout.SOUTH);
    }

    private JCommandButton createJCommandButton(String title, ResizableIcon icon, String name, RichTooltip rToolTip, ActionListener aListener) {
        JCommandButton commandButton = new JCommandButton(title, icon);
        commandButton.setName(name);
        commandButton.setActionRichTooltip(rToolTip);
        commandButton.addActionListener(aListener);
        return commandButton;
    }

    private List<RibbonBandResizePolicy> getRibbonBandResizePolicy(JRibbonBand jRibbonBand) {
        return (List) Arrays.asList(new Object[]{
                    //new CoreRibbonResizePolicies.None(jrbAfiliadosBand.getControlPanel()),
                    new CoreRibbonResizePolicies.Mirror(jRibbonBand.getControlPanel()),
                    /*new CoreRibbonResizePolicies.High2Mid(jRibbonBand.getControlPanel()),*/
                    new CoreRibbonResizePolicies.Mid2Low(jRibbonBand.getControlPanel()), //new CoreRibbonResizePolicies.High2Low(jrbAfiliadosBand.getControlPanel()),
                    new IconRibbonBandResizePolicy(jRibbonBand.getControlPanel())});
    }

    private RibbonTask getHomeTask() {
        // on home task
        // creando los botones

        JCommandButton jcbLogInOut, jcbSalir;
        jcbLogInOut = createJCommandButton("Log In", getResizableIconFromResource("/resources/images/login.png"), "jcbLogIn",
                new RichTooltip("Login/Logout", "Click aqui para abrir/cerrar sesion de usuario"), buttonActHandler);

        jcbSalir = createJCommandButton("Salir", getResizableIconFromResource("/resources/images/salir.png"), "jcbSalir",
                new RichTooltip("Salir", "Click aqui para salir de la aplicacion"), buttonActHandler);

        // creando la banda "Acceso"
        JRibbonBand jrbAccesoBand = new JRibbonBand("Acceso", getResizableIconFromResource("/resources/images/login.png"));
        jrbAccesoBand.addCommandButton(jcbLogInOut, RibbonElementPriority.TOP);
        jrbAccesoBand.addCommandButton(jcbSalir, RibbonElementPriority.TOP);

        jrbAccesoBand.setResizePolicies((List) Arrays.asList(
                new CoreRibbonResizePolicies.Mirror(jrbAccesoBand.getControlPanel()),
                new CoreRibbonResizePolicies.Mid2Low(jrbAccesoBand.getControlPanel()),
                new IconRibbonBandResizePolicy(jrbAccesoBand.getControlPanel())));

        JCommandButton jcbPegar, jcbCortar, jcbCopiar;
        jcbPegar = new JCommandButton("Pegar", getResizableIconFromResource("/resources/images/paste.png"));
        jcbPegar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Paste");
            }
        });
        jcbPegar.setActionRichTooltip(new RichTooltip("Pegar", "Click aqui para pegar"));

        jcbCortar = new JCommandButton("Cortar", getResizableIconFromResource("/resources/images/scissors.png"));
        jcbCortar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cortar");
            }
        });
        jcbCortar.setActionRichTooltip(new RichTooltip("Cortar", "Click aqui para cortar"));

        jcbCopiar = new JCommandButton("Copiar", getResizableIconFromResource("/resources/images/copy.png"));
        jcbCopiar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Copiar");
            }
        });
        jcbCopiar.setActionRichTooltip(new RichTooltip("Copiar", "Click aqui para copiar"));

        JRibbonBand jrbClipboard = new JRibbonBand("Clipboard", new EmptyResizableIcon(16));

        jrbClipboard.addCommandButton(jcbPegar, RibbonElementPriority.TOP);
        jrbClipboard.addCommandButton(jcbCortar, RibbonElementPriority.TOP);
        jrbClipboard.addCommandButton(jcbCopiar, RibbonElementPriority.TOP);

        jrbClipboard.setResizePolicies((List) Arrays.asList(
                new CoreRibbonResizePolicies.Mirror(jrbClipboard.getControlPanel()),
                new CoreRibbonResizePolicies.Mid2Low(jrbClipboard.getControlPanel()),
                new CoreRibbonResizePolicies.High2Mid(jrbClipboard.getControlPanel()),
                new IconRibbonBandResizePolicy(jrbClipboard.getControlPanel())));

        RibbonTask rtHomeTask = new RibbonTask("Home", jrbAccesoBand, jrbClipboard);

        return rtHomeTask;
    }

    private RibbonTask getMantenimientoTask() {
        // on Tutores band
        JCommandButton jcbVerTutores, jcbRegistrarTutor, jcbEditarTutor, jcbEliminarTutor;

        jcbVerTutores = createJCommandButton("Ver", getResizableIconFromResource("/resources/images/tutores.png"),
                "jcbVerTutores", new RichTooltip("Ver", "Click aqui para ver los tutores"), buttonActHandler);

        jcbRegistrarTutor = createJCommandButton("Nuevo", getResizableIconFromResource("/resources/images/add.png"),
                "jcbRegistrarTutor", new RichTooltip("Nuevo", "Click aqui para registrar tutor"), buttonActHandler);

        jcbEditarTutor = createJCommandButton("Editar", getResizableIconFromResource("/resources/images/editar.png"),
                "jcbEditarTutor", new RichTooltip("Editar", "Click aqui para editar tutor"), buttonActHandler);

        jcbEliminarTutor = createJCommandButton("Eliminar", getResizableIconFromResource("/resources/images/delete.png"),
                "jcbEliminarTutor", new RichTooltip("Eliminar", "Click aqui para eliminar tutor(es)"), buttonActHandler);

        JRibbonBand jrbTutoresBand = new JRibbonBand("Tutores", getResizableIconFromResource("/resources/images/tutores.png"));
        jrbTutoresBand.addCommandButton(jcbVerTutores, RibbonElementPriority.TOP);
        jrbTutoresBand.addCommandButton(jcbRegistrarTutor, RibbonElementPriority.MEDIUM);
        jrbTutoresBand.addCommandButton(jcbEditarTutor, RibbonElementPriority.MEDIUM);
        jrbTutoresBand.addCommandButton(jcbEliminarTutor, RibbonElementPriority.MEDIUM);

        jrbTutoresBand.setResizePolicies(getRibbonBandResizePolicy(jrbTutoresBand));

        // on estudiantes band
        JCommandButton jcbVerEstudiantes, jcbRegistrarEstudiante, jcbEditarEstudiante, jcbEliminarEstudiante;

        jcbVerEstudiantes = createJCommandButton("Ver", getResizableIconFromResource("/resources/images/estudiantes.png"),
                "jcbVerEstudiantes", new RichTooltip("Ver", "Click aqui para ver los estudiantes"), buttonActHandler);

        jcbRegistrarEstudiante = createJCommandButton("Nuevo", getResizableIconFromResource("/resources/images/add.png"),
                "jcbRegistrarEstudiante", new RichTooltip("Nuevo", "Click aqui para registrar estudiante"), buttonActHandler);

        jcbEditarEstudiante = createJCommandButton("Editar", getResizableIconFromResource("/resources/images/editar.png"),
                "jcbEditarEstudiante", new RichTooltip("Editar", "Click aqui para editar estudiante"), buttonActHandler);

        jcbEliminarEstudiante = createJCommandButton("Eliminar", getResizableIconFromResource("/resources/images/delete.png"),
                "jcbEliminarEstudiante", new RichTooltip("Eliminar", "Click aqui para eliminar estudiante(s)"), buttonActHandler);

        // creando la banda
        JRibbonBand jrbEstudiantesBand = new JRibbonBand("Estudiantes", getResizableIconFromResource("/resources/images/estudiantes.png"));
        jrbEstudiantesBand.addCommandButton(jcbVerEstudiantes, RibbonElementPriority.TOP);
        jrbEstudiantesBand.addCommandButton(jcbRegistrarEstudiante, RibbonElementPriority.MEDIUM);
        jrbEstudiantesBand.addCommandButton(jcbEditarEstudiante, RibbonElementPriority.MEDIUM);
        jrbEstudiantesBand.addCommandButton(jcbEliminarEstudiante, RibbonElementPriority.MEDIUM);

        jrbEstudiantesBand.setResizePolicies(getRibbonBandResizePolicy(jrbEstudiantesBand));

        // on referimientos band
        JCommandButton jcbVerReferimientos, jcbRegistrarReferimiento, jcbEditarReferimiento,
                jcbEliminarReferimiento, jcbObservacionReferimiento, jcbCambiarEstadoReferimiento;

        jcbVerReferimientos = createJCommandButton("Ver", getResizableIconFromResource("/resources/images/referimientos.png"),
                "jcbVerReferimientos", new RichTooltip("Ver", "Click aqui para ver los referimientos"), buttonActHandler);

        jcbRegistrarReferimiento = createJCommandButton("Nuevo", getResizableIconFromResource("/resources/images/add.png"),
                "jcbRegistrarReferimiento", new RichTooltip("Nuevo", "Click aqui para registrar referimiento"), buttonActHandler);

        jcbEditarReferimiento = createJCommandButton("Editar", getResizableIconFromResource("/resources/images/editar.png"),
                "jcbEditarReferimiento", new RichTooltip("Editar", "Click aqui para editar referimiento"), buttonActHandler);

        jcbEliminarReferimiento = createJCommandButton("Eliminar", getResizableIconFromResource("/resources/images/delete.png"),
                "jcbEliminarReferimiento", new RichTooltip("Eliminar", "Click aqui para eliminar referimiento(s)"), buttonActHandler);

        jcbObservacionReferimiento = createJCommandButton("Observacion", getResizableIconFromResource("/resources/images/referimientos.png"),
                "jcbObservacionReferimiento", new RichTooltip("Observacion Referimiento", "Click aqui para crear observacion de referimiento"), buttonActHandler);
        jcbObservacionReferimiento.setCommandButtonKind(CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
        jcbObservacionReferimiento.setPopupCallback(new PopupPanelCallback() {

            @Override
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                return new JCommandPopupMenu() {

                    {
                        JCommandMenuButton verObservacionRefButton, editarObservacionRefButton, eliminarObservacionRefButton;

                        // ver
                        verObservacionRefButton = new JCommandMenuButton("Ver", new EmptyResizableIcon(16));
                        verObservacionRefButton.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                verObservacionReferimiento();
                            }
                        });
                        verObservacionRefButton.setActionKeyTip("Ver Observacion Referimiento");
                        verObservacionRefButton.setEnabled(false);

                        // editar
                        editarObservacionRefButton = new JCommandMenuButton("Editar", new EmptyResizableIcon(16));
                        editarObservacionRefButton.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                registrarEditarObservacionReferimiento(RegistroEdicionModo.EDICION, null);
                            }
                        });
                        editarObservacionRefButton.setActionKeyTip("Editar Observacion Referimiento");

                        // eliminar                        
                        eliminarObservacionRefButton = new JCommandMenuButton("Eliminar", new EmptyResizableIcon(16));
                        eliminarObservacionRefButton.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                eliminarObservacionReferimiento();
                            }
                        });
                        eliminarObservacionRefButton.setActionKeyTip("Eliminar Observacion Referimiento");
                        eliminarObservacionRefButton.setEnabled(false);

                        this.addMenuButton(verObservacionRefButton);
                        this.addMenuButton(editarObservacionRefButton);
                        this.addMenuButton(eliminarObservacionRefButton);
                    }
                };
            }
        });
        jcbObservacionReferimiento.setPopupRichTooltip(new RichTooltip("Observacion Referimiento", "Click aqui para mas opciones"));

        jcbCambiarEstadoReferimiento = createJCommandButton("Cambiar Estado", getResizableIconFromResource("/resources/images/delete.png"),
                "jcbCambiarEstadoReferimiento", new RichTooltip("Cambiar Estado", "Click aqui para cambiar el estado del referimiento"), buttonActHandler);

        // creando la banda
        JRibbonBand jrbReferimientosBand = new JRibbonBand("Referimientos", getResizableIconFromResource("/resources/images/referimientos.png"));
        jrbReferimientosBand.addCommandButton(jcbVerReferimientos, RibbonElementPriority.TOP);
        jrbReferimientosBand.addCommandButton(jcbRegistrarReferimiento, RibbonElementPriority.MEDIUM);
        jrbReferimientosBand.addCommandButton(jcbEditarReferimiento, RibbonElementPriority.MEDIUM);
        jrbReferimientosBand.addCommandButton(jcbEliminarReferimiento, RibbonElementPriority.MEDIUM);
        jrbReferimientosBand.startGroup();
        jrbReferimientosBand.addCommandButton(jcbObservacionReferimiento, RibbonElementPriority.MEDIUM);
        jrbReferimientosBand.addCommandButton(jcbCambiarEstadoReferimiento, RibbonElementPriority.MEDIUM);

        //jrbReferimientosBand.setResizePolicies(this.getRibbonBandResizePolicy(jrbReferimientosBand));
        jrbReferimientosBand.setResizePolicies((List) Arrays.asList(new Object[]{
                    /*new CoreRibbonResizePolicies.None(jrbReferimientosBand.getControlPanel()),*/
                    new CoreRibbonResizePolicies.Mirror(jrbReferimientosBand.getControlPanel()),
                    new CoreRibbonResizePolicies.High2Mid(jrbReferimientosBand.getControlPanel()),
                    new CoreRibbonResizePolicies.Mid2Low(jrbReferimientosBand.getControlPanel()),
                    new IconRibbonBandResizePolicy(jrbReferimientosBand.getControlPanel())}));

        // on pruebas psicologicas band

        JCommandButton jcbVerPruebasPsicologicas, jcbRegistrarPruebaPsicologica, jcbEditarPruebaPsicologica, jcbEliminarPruebaPsicologica, jcbCorregirPruebaPsicologica;

        jcbCorregirPruebaPsicologica = createJCommandButton("Corregir", getResizableIconFromResource("/resources/images/corregir.png"),
                "jcbCorregirPruebaPsicologica", new RichTooltip("Corregir", "Click aqui para corregir prueba(s) psicologica"), buttonActHandler);

        jcbVerPruebasPsicologicas = createJCommandButton("Ver", getResizableIconFromResource("/resources/images/pruebas_psicologicas.png"),
                "jcbVerPruebasPsicologicas", new RichTooltip("Ver", "Click aqui para ver las pruebas psicologicas"), buttonActHandler);

        jcbRegistrarPruebaPsicologica = createJCommandButton("Nuevo", getResizableIconFromResource("/resources/images/add.png"),
                "jcbRegistrarPruebaPsicologica", new RichTooltip("Nuevo", "Click aqui para registrar prueba psicologica"), buttonActHandler);

        jcbEditarPruebaPsicologica = createJCommandButton("Editar", getResizableIconFromResource("/resources/images/editar.png"),
                "jcbEditarPruebaPsicologica", new RichTooltip("Editar", "Click aqui para editar prueba psicologica"), buttonActHandler);

        jcbEliminarPruebaPsicologica = createJCommandButton("Eliminar", getResizableIconFromResource("/resources/images/delete.png"),
                "jcbEliminarPruebaPsicologica", new RichTooltip("Eliminar", "Click aqui para eliminar prueba(s) psicologica"), buttonActHandler);

        // creando la banda
        JRibbonBand jrbPruebasPsicologicasBand = new JRibbonBand("Pruebas Psicologicas", getResizableIconFromResource("/resources/images/pruebas_psicologicas.png"));

        jrbPruebasPsicologicasBand.addCommandButton(jcbCorregirPruebaPsicologica, RibbonElementPriority.TOP);
        jrbPruebasPsicologicasBand.startGroup();
        jrbPruebasPsicologicasBand.addCommandButton(jcbVerPruebasPsicologicas, RibbonElementPriority.TOP);
        jrbPruebasPsicologicasBand.addCommandButton(jcbRegistrarPruebaPsicologica, RibbonElementPriority.MEDIUM);
        jrbPruebasPsicologicasBand.addCommandButton(jcbEditarPruebaPsicologica, RibbonElementPriority.MEDIUM);
        jrbPruebasPsicologicasBand.addCommandButton(jcbEliminarPruebaPsicologica, RibbonElementPriority.MEDIUM);

        jrbPruebasPsicologicasBand.setResizePolicies((List) Arrays.asList(new Object[]{
                    new CoreRibbonResizePolicies.Mirror(jrbPruebasPsicologicasBand.getControlPanel()),
                    new CoreRibbonResizePolicies.Mid2Low(jrbPruebasPsicologicasBand.getControlPanel()),
                    new IconRibbonBandResizePolicy(jrbPruebasPsicologicasBand.getControlPanel())}));

        // casos band
        JCommandButton jcbVerCasos, jcbRegistrarCaso, jcbEditarCaso, jcbEliminarCaso;

        jcbVerCasos = createJCommandButton("Ver", getResizableIconFromResource("/resources/images/casos.png"),
                "jcbVerCasos", new RichTooltip("Ver", "Click aqui para ver los casos"), buttonActHandler);

        jcbRegistrarCaso = createJCommandButton("Nuevo", getResizableIconFromResource("/resources/images/add.png"),
                "jcbRegistrarCaso", new RichTooltip("Nuevo", "Click aqui para registrar caso"), buttonActHandler);

        jcbEditarCaso = createJCommandButton("Editar", getResizableIconFromResource("/resources/images/editar.png"),
                "jcbEditarCaso", new RichTooltip("Editar", "Click aqui para editar caso"), buttonActHandler);

        jcbEliminarCaso = createJCommandButton("Eliminar", getResizableIconFromResource("/resources/images/delete.png"),
                "jcbEliminarCaso", new RichTooltip("Eliminar", "Click aqui para eliminar caso(s)"), buttonActHandler);

        // creando la banda
        JRibbonBand jrbCasosBand = new JRibbonBand("Casos", getResizableIconFromResource("/resources/images/casos.png"));
        jrbCasosBand.addCommandButton(jcbVerCasos, RibbonElementPriority.TOP);
        jrbCasosBand.addCommandButton(jcbRegistrarCaso, RibbonElementPriority.MEDIUM);
        jrbCasosBand.addCommandButton(jcbEditarCaso, RibbonElementPriority.MEDIUM);
        jrbCasosBand.addCommandButton(jcbEliminarCaso, RibbonElementPriority.MEDIUM);

        jrbCasosBand.setResizePolicies((List) Arrays.asList(new Object[]{
                    //new CoreRibbonResizePolicies.None(jrbCasosBand.getControlPanel()),
                    new CoreRibbonResizePolicies.Mirror(jrbCasosBand.getControlPanel()),
                    new CoreRibbonResizePolicies.Mid2Low(jrbCasosBand.getControlPanel()),
                    new IconRibbonBandResizePolicy(jrbCasosBand.getControlPanel())}));


        // historia clinica band
        JCommandButton jcbVerHistoriasClinica, jcbRegistrarHistoriaClinica, jcbEditarHistoriaClinica, jcbEliminarHistoriaClinica;

        jcbVerHistoriasClinica = createJCommandButton("Ver", getResizableIconFromResource("/resources/images/historia_clinica.png"),
                "jcbVerHics", new RichTooltip("Ver", "Click aqui para ver historias clinicas"), buttonActHandler);

        jcbRegistrarHistoriaClinica = createJCommandButton("Nuevo", getResizableIconFromResource("/resources/images/add.png"),
                "jcbRegistrarHic", new RichTooltip("Nuevo", "Click aqui para registrar referimiento"), buttonActHandler);

        jcbEditarHistoriaClinica = createJCommandButton("Editar", getResizableIconFromResource("/resources/images/editar.png"),
                "jcbEditarHic", new RichTooltip("Editar", "Click aqui para editar referimiento"), buttonActHandler);

        jcbEliminarHistoriaClinica = createJCommandButton("Eliminar", getResizableIconFromResource("/resources/images/delete.png"),
                "jcbEliminarHic", new RichTooltip("Eliminar", "Click aqui para eliminar historia(s) clinica(s)"), buttonActHandler);

        // creando la banda
        JRibbonBand jrbHistoriaClinicaBand = new JRibbonBand("Historias Clinicas", getResizableIconFromResource("/resources/images/historia_clinica.png"));
        jrbHistoriaClinicaBand.addCommandButton(jcbVerHistoriasClinica, RibbonElementPriority.TOP);
        jrbHistoriaClinicaBand.addCommandButton(jcbRegistrarHistoriaClinica, RibbonElementPriority.MEDIUM);
        jrbHistoriaClinicaBand.addCommandButton(jcbEditarHistoriaClinica, RibbonElementPriority.MEDIUM);
        jrbHistoriaClinicaBand.addCommandButton(jcbEliminarHistoriaClinica, RibbonElementPriority.MEDIUM);

        jrbHistoriaClinicaBand.setResizePolicies((List) Arrays.asList(new Object[]{
                    //new CoreRibbonResizePolicies.None(jrbHistoriaClinicaBand.getControlPanel()),
                    new CoreRibbonResizePolicies.Mirror(jrbHistoriaClinicaBand.getControlPanel()),
                    new CoreRibbonResizePolicies.High2Mid(jrbHistoriaClinicaBand.getControlPanel()),
                    new CoreRibbonResizePolicies.Mid2Low(jrbHistoriaClinicaBand.getControlPanel()),
                    new IconRibbonBandResizePolicy(jrbHistoriaClinicaBand.getControlPanel())}));

        // creando el la task
        RibbonTask rtMantenimientoTask = new RibbonTask("Mantenimiento", jrbTutoresBand, jrbEstudiantesBand,
                jrbReferimientosBand, jrbPruebasPsicologicasBand, jrbCasosBand, jrbHistoriaClinicaBand);

        return rtMantenimientoTask;
    }

    private RibbonTask getReportesTask() {

        // Listados band
        JCommandButton jcbListadoTutores, jcbListadoEstudiantes, jcbListadoReferimientos, jcbListadoCasos;

        jcbListadoTutores = createJCommandButton("Tutores", getResizableIconFromResource("/resources/images/tutores.png"),
                "jcbListadoTutores", new RichTooltip("Listado Tutores", "Click aqui para ver reporte listado de tutores"), buttonActHandler);

        jcbListadoEstudiantes = createJCommandButton("Estudiantes", getResizableIconFromResource("/resources/images/estudiantes.png"),
                "jcbListadoEstudiantes", new RichTooltip("Listado Estudiantes", "Click aqui para ver reporte listado de estudiantes"), buttonActHandler);

        jcbListadoReferimientos = createJCommandButton("Referimientos", getResizableIconFromResource("/resources/images/referimientos.png"),
                "jcbListadoReferimientos", new RichTooltip("Listado Referimientos", "Click aqui para ver reporte listado de referimientos"), buttonActHandler);

        jcbListadoCasos = createJCommandButton("Casos", getResizableIconFromResource("/resources/images/casos.png"),
                "jcbListadoCasos", new RichTooltip("Listado Casos", "Click aqui para ver reporte listado de casos"), buttonActHandler);

        JRibbonBand jrbListadosBand = new JRibbonBand("Listados", getResizableIconFromResource("/resources/images/listados.png"));
        jrbListadosBand.addCommandButton(jcbListadoTutores, RibbonElementPriority.TOP);
        jrbListadosBand.addCommandButton(jcbListadoEstudiantes, RibbonElementPriority.MEDIUM);
        jrbListadosBand.addCommandButton(jcbListadoReferimientos, RibbonElementPriority.MEDIUM);
        jrbListadosBand.addCommandButton(jcbListadoCasos, RibbonElementPriority.MEDIUM);

        jrbListadosBand.setResizePolicies(getRibbonBandResizePolicy(jrbListadosBand));

        // informes especiales

        JCommandButton jcbInformePsicologico, jcbHicInfantil;

        jcbInformePsicologico = createJCommandButton("Informe Psicologico", getResizableIconFromResource("/resources/images/casos.png"),
                "jcbInformePsicologico", new RichTooltip("Informe Psicologico", "Click aqui para ver reporte de informe psicologico"), buttonActHandler);

        jcbHicInfantil = createJCommandButton("Historia Clinica", getResizableIconFromResource("/resources/images/historia_clinica.png"),
                "jcbHicInfantil", new RichTooltip("Reporte Historia Clinica Infantil", "Click aqui para ver reporte de historia clinica infantil"), buttonActHandler);

        JRibbonBand jrbInfEspeciales = new JRibbonBand("Informes Especiales", getResizableIconFromResource("/resources/images/listados.png"));
        jrbInfEspeciales.addCommandButton(jcbInformePsicologico, RibbonElementPriority.TOP);
        jrbInfEspeciales.addCommandButton(jcbHicInfantil, RibbonElementPriority.TOP);
        jrbInfEspeciales.setResizePolicies((List) Arrays.asList(new Object[]{
                    new CoreRibbonResizePolicies.None(jrbInfEspeciales.getControlPanel()),
                    new CoreRibbonResizePolicies.Mirror(jrbInfEspeciales.getControlPanel()),
                    new CoreRibbonResizePolicies.High2Mid(jrbInfEspeciales.getControlPanel()),
                    new IconRibbonBandResizePolicy(jrbInfEspeciales.getControlPanel())}));

        // creando el la task
        RibbonTask rtReportesTask = new RibbonTask("Reportes", jrbListadosBand, jrbInfEspeciales);

        return rtReportesTask;
    }

    private RibbonTask getToolsAndSettingsTask() {
        // Roles & Permisos Band
        JCommandButton jcbManRolesPermisos = createJCommandButton("Administrar", getResizableIconFromResource("/resources/images/corregir.png"),
                "jcbManRolesPermisos", new RichTooltip("Administrar", "Click aqui para manejar los roles y permisos del sistema"), buttonActHandler);
        JRibbonBand jrbRolesPermisos = new JRibbonBand("Roles & Permisos", getResizableIconFromResource("/resources/images/listados.png"));
        jrbRolesPermisos.addCommandButton(jcbManRolesPermisos, RibbonElementPriority.TOP);
        jrbRolesPermisos.setResizePolicies((List) Arrays.asList(new Object[]{
                    new CoreRibbonResizePolicies.Mirror(jrbRolesPermisos.getControlPanel()),
                    new CoreRibbonResizePolicies.Mid2Low(jrbRolesPermisos.getControlPanel())}));

        // Usuarios band
        JCommandButton jcbVerUsuarios, jcbRegistrarUsuario, jcbEditarUsuario, jcbEliminarUsuario;

        jcbVerUsuarios = createJCommandButton("Ver", getResizableIconFromResource("/resources/images/corregir.png"),
                "jcbVerUsuarios", new RichTooltip("Ver", "Click aqui para ver usuarios"), buttonActHandler);

        jcbRegistrarUsuario = createJCommandButton("Nuevo", getResizableIconFromResource("/resources/images/add.png"),
                "jcbRegistrarUsuario", new RichTooltip("Nuevo", "Click aqui para registrar usuario"), buttonActHandler);

        jcbEditarUsuario = createJCommandButton("Editar", getResizableIconFromResource("/resources/images/editar.png"),
                "jcbEditarUsuario", new RichTooltip("Editar", "Click aqui para editar usuario"), buttonActHandler);

        jcbEliminarUsuario = createJCommandButton("Eliminar", getResizableIconFromResource("/resources/images/delete.png"),
                "jcbEliminarUsuario", new RichTooltip("Eliminar", "Click aqui para eliminar usuario(s)"), buttonActHandler);

        JRibbonBand jrbUsuarios = new JRibbonBand("Usuarios", getResizableIconFromResource("/resources/images/listados.png"));
        jrbUsuarios.addCommandButton(jcbVerUsuarios, RibbonElementPriority.TOP);
        jrbUsuarios.addCommandButton(jcbRegistrarUsuario, RibbonElementPriority.MEDIUM);
        jrbUsuarios.addCommandButton(jcbEditarUsuario, RibbonElementPriority.MEDIUM);
        jrbUsuarios.addCommandButton(jcbEliminarUsuario, RibbonElementPriority.MEDIUM);

        jrbUsuarios.setResizePolicies((List) Arrays.asList(new Object[]{
                    new CoreRibbonResizePolicies.Mirror(jrbUsuarios.getControlPanel()),
                    new CoreRibbonResizePolicies.Mid2Low(jrbUsuarios.getControlPanel()),
                    new IconRibbonBandResizePolicy(jrbUsuarios.getControlPanel())}));

        // Look and feel band

        JCommandButton jcbLaf;

        jcbLaf = createJCommandButton("Configurar", getResizableIconFromResource("/resources/images/corregir.png"), "jcbLaf", null, buttonActHandler);
        jcbLaf.setCommandButtonKind(CommandButtonKind.POPUP_ONLY);
        jcbLaf.setPopupCallback(new PopupPanelCallback() {

            @Override
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                return new JCommandPopupMenu() {

                    {
                        JCommandMenuButton windowsLaf = new JCommandMenuButton("Windows", new EmptyResizableIcon(16));
                        windowsLaf.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                java.awt.EventQueue.invokeLater(new Runnable() {

                                    @Override
                                    public void run() {
                                        LookAndFeelSelector.setLookAndFeel(LookAndFeelSelector.LAF.WINDOWS);
                                    }
                                });
                            }
                        });
                        windowsLaf.setActionKeyTip("Windows LAF");
                        this.addMenuButton(windowsLaf);

                        JCommandMenuButton nimbusLaf = new JCommandMenuButton("Nimbus", new EmptyResizableIcon(16));
                        nimbusLaf.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                java.awt.EventQueue.invokeLater(new Runnable() {

                                    @Override
                                    public void run() {
                                        LookAndFeelSelector.setLookAndFeel(LookAndFeelSelector.LAF.NIMBUS);
                                    }
                                });
                            }
                        });
                        nimbusLaf.setActionKeyTip("Nimbus LAF");
                        this.addMenuButton(nimbusLaf);

                        JCommandMenuButton metalLaf = new JCommandMenuButton("Metal", new EmptyResizableIcon(16));
                        metalLaf.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                java.awt.EventQueue.invokeLater(new Runnable() {

                                    @Override
                                    public void run() {
                                        LookAndFeelSelector.setLookAndFeel(LookAndFeelSelector.LAF.METAL);
                                    }
                                });
                            }
                        });
                        metalLaf.setActionKeyTip("Metal LAF");
                        this.addMenuButton(metalLaf);
                    }
                };
            }
        });
        jcbLaf.setPopupOrientationKind(JCommandButton.CommandButtonPopupOrientationKind.SIDEWARD);
        jcbLaf.setPopupRichTooltip(new RichTooltip("Configurar", "Click aqui para seleccionar look and feel del sistema"));

        JRibbonBand jrbLookAndFeel = new JRibbonBand("Look & Feel", getResizableIconFromResource("/resources/images/listados.png"));
        jrbLookAndFeel.addCommandButton(jcbLaf, RibbonElementPriority.TOP);
        jrbLookAndFeel.setResizePolicies((List) Arrays.asList(new Object[]{
                    new CoreRibbonResizePolicies.Mirror(jrbLookAndFeel.getControlPanel()),
                    new CoreRibbonResizePolicies.Mid2Low(jrbLookAndFeel.getControlPanel()),
                    new IconRibbonBandResizePolicy(jrbLookAndFeel.getControlPanel())}));

        RibbonTask rtToolsAndSettings = new RibbonTask("Tools & Settings", jrbRolesPermisos, jrbUsuarios, jrbLookAndFeel);

        return rtToolsAndSettings;
    }

    private void configureTaskBar() {
        // botones
        JCommandButton taskbarPasteButton, taskbarCortarButton, taskbarCopiarButton, taskbarGuardarButton, taskbarNuevoButton;

        // paste button        
        taskbarPasteButton = new JCommandButton("", getResizableIconFromResource("/resources/images/paste.png"));
        taskbarPasteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Taskbar Paste");
            }
        });
        taskbarPasteButton.setActionRichTooltip(new RichTooltip("Pegar", "Click aqui para pegar"));

        // cortar button
        taskbarCortarButton = new JCommandButton("", getResizableIconFromResource("/resources/images/scissors.png"));
        taskbarCortarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Taskbar Cortar");
            }
        });
        taskbarCortarButton.setActionRichTooltip(new RichTooltip("Cortar", "Click aqui para cortar"));

        // copiar button        
        taskbarCopiarButton = new JCommandButton("", getResizableIconFromResource("/resources/images/copy.png"));
        taskbarCopiarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Taskbar Copiar");
            }
        });
        taskbarCopiarButton.setActionRichTooltip(new RichTooltip("Copiar", "Click aqui para copiar"));

        // guardar button
        taskbarGuardarButton = new JCommandButton("Guardar", getResizableIconFromResource("/resources/images/save.png"));
        taskbarGuardarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Taskbar Guardar");
            }
        });
        taskbarGuardarButton.setActionRichTooltip(new RichTooltip("Guardar", "Click aqui para guardar"));


        // nuevo button
        taskbarNuevoButton = new JCommandButton("", getResizableIconFromResource("/resources/images/new_15x15.png"));
        taskbarNuevoButton.setCommandButtonKind(CommandButtonKind.POPUP_ONLY);
        taskbarNuevoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Taskbar Nuevo");
            }
        });
        taskbarNuevoButton.setPopupCallback(new PopupPanelCallback() {

            @Override
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                return new TaskbarNuevoPopupMenu();
            }
        });
        taskbarNuevoButton.setActionRichTooltip(new RichTooltip("Nuevo", "Click aqui para registrar nueva entidad"));
        taskbarNuevoButton.setPopupRichTooltip(new RichTooltip("Nuevo", "Click aqui para mas opciones"));
        taskbarNuevoButton.setActionKeyTip("Nuevo Taskbar");

        this.getRibbon().addTaskbarComponent(taskbarPasteButton);
        this.getRibbon().addTaskbarComponent(taskbarCortarButton);
        this.getRibbon().addTaskbarComponent(taskbarCopiarButton);
        this.getRibbon().addTaskbarComponent(taskbarGuardarButton);
        this.getRibbon().addTaskbarComponent(new JSeparator(SwingConstants.VERTICAL));
        this.getRibbon().addTaskbarComponent(taskbarNuevoButton);
    }

    private void configureApplicationMenu() {

        // entry nuevo
        RibbonApplicationMenuEntryPrimary amEntryNew = new RibbonApplicationMenuEntryPrimary(new document_new(), "Nuevo",
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Invoked creating new document");
                    }
                }, CommandButtonKind.ACTION_ONLY);
        amEntryNew.setActionKeyTip("N");
        amEntryNew.setEnabled(false);

        // entry abrir
        RibbonApplicationMenuEntryPrimary amEntryOpen = new RibbonApplicationMenuEntryPrimary(new document_open(), "Abrir",
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Invoked opening document");
                    }
                }, CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);

        amEntryOpen.setActionKeyTip("O");
        amEntryOpen.setRolloverCallback(new RibbonApplicationMenuEntryPrimary.PrimaryRolloverCallback() {

            @Override
            public void menuEntryActivated(JPanel targetPanel) {
                targetPanel.removeAll();
                JCommandButtonPanel openHistoryPanel = new JCommandButtonPanel(CommandButtonDisplayState.MEDIUM);
                String groupName = "Documentos Recientes";
                openHistoryPanel.addButtonGroup(groupName);

                for (int i = 0; i < 5; i++) {
                    JCommandButton historyButton = new JCommandButton(String.format("Recent Doc %d", i), new EmptyResizableIcon(5));
                    historyButton.setHorizontalAlignment(SwingUtilities.LEFT);
                    //historyButton.setEnabled(false);
                    openHistoryPanel.addButtonToLastGroup(historyButton);
                }
                openHistoryPanel.setMaxButtonColumns(1);
                targetPanel.setLayout(new BorderLayout());
                targetPanel.add(openHistoryPanel, BorderLayout.CENTER);
            }
        });
        amEntryOpen.setEnabled(false);

        RibbonApplicationMenuEntryPrimary amEntrySave = new RibbonApplicationMenuEntryPrimary(new document_save(), "Guardar",
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Invoked saving document");
                    }
                }, CommandButtonKind.ACTION_ONLY);
        amEntrySave.setEnabled(false);
        amEntrySave.setActionKeyTip("S");
        amEntrySave.setEnabled(false);

        RibbonApplicationMenuEntryPrimary amEntrySaveAs = new RibbonApplicationMenuEntryPrimary(new document_save_as(), "Guardar Como",
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Invoked saving document as");
                    }
                }, CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
        amEntrySaveAs.setActionKeyTip("A");
        amEntrySaveAs.setPopupKeyTip("F");

        RibbonApplicationMenuEntrySecondary amEntrySaveAsWord = new RibbonApplicationMenuEntrySecondary(new x_office_document(), "Word", null,
                CommandButtonKind.ACTION_ONLY);
        amEntrySaveAsWord.setDescriptionText("Guarda el documento en el formato por defecto");
        amEntrySaveAsWord.setActionKeyTip("W");
        amEntrySaveAsWord.setEnabled(false);

        RibbonApplicationMenuEntrySecondary amEntrySaveAsOtherFormats = new RibbonApplicationMenuEntrySecondary(new document_save_as(), "Otros Formatos",
                null, CommandButtonKind.ACTION_ONLY);
        amEntrySaveAsOtherFormats.setDescriptionText("Abre la ventana de dialogo guardar como, para seleccionar los posibles formatos de archivo");
        amEntrySaveAsOtherFormats.setActionKeyTip("O");

        amEntrySaveAs.addSecondaryMenuGroup("Guarda una copia del documento", amEntrySaveAsWord, amEntrySaveAsOtherFormats);
        amEntrySaveAs.setEnabled(false);

        RibbonApplicationMenuEntryPrimary amEntryPrint = new RibbonApplicationMenuEntryPrimary(new document_print(), "Imprimir",
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Invoked printing document");
                    }
                }, CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
        amEntryPrint.setActionKeyTip("P");
        amEntryPrint.setPopupKeyTip("W");

        RibbonApplicationMenuEntrySecondary amEntryPrintSelect = new RibbonApplicationMenuEntrySecondary(new printer(), "Imprimir", null,
                CommandButtonKind.ACTION_ONLY);
        amEntryPrintSelect.setDescriptionText("Selecciona impresora, numero de copias y otras opciones de impresion antes de imprimir");
        amEntryPrintSelect.setActionKeyTip("P");
        RibbonApplicationMenuEntrySecondary amEntryPrintDefault = new RibbonApplicationMenuEntrySecondary(new document_print(), "Ipresion Rapida", null,
                CommandButtonKind.ACTION_ONLY);
        amEntryPrintDefault.setDescriptionText("Envia el documento directo a la impresora por defecto sin hacer cambios");
        amEntryPrintDefault.setActionKeyTip("Q");

        amEntryPrint.addSecondaryMenuGroup("Imprime el documento", amEntryPrintSelect, amEntryPrintDefault);
        amEntryPrint.setEnabled(false);

        RibbonApplicationMenuEntryPrimary amEntrySend = new RibbonApplicationMenuEntryPrimary(new mail_forward(), "Enviar",
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Invoked sending document");
                    }
                }, CommandButtonKind.POPUP_ONLY);
        amEntrySend.setPopupKeyTip("D");

        RibbonApplicationMenuEntrySecondary amEntrySendMail = new RibbonApplicationMenuEntrySecondary(new mail_message_new(), "E-Mail", null,
                CommandButtonKind.ACTION_ONLY);
        amEntrySendMail.setDescriptionText("Envia una copia del documento como un atajo, en un mensaje de correo");
        amEntrySendMail.setActionKeyTip("E");

        RibbonApplicationMenuEntrySecondary amEntrySendDoc = new RibbonApplicationMenuEntrySecondary(
                new x_office_document(), "Enviar por correo como un atajo de word", null,
                CommandButtonKind.ACTION_ONLY);
        amEntrySendDoc.setDescriptionText("Envia una copia del documento como un atajo de word, en un mensaje de correo");
        amEntrySendDoc.setActionKeyTip("W");
        RibbonApplicationMenuEntrySecondary amEntrySendWireless = new RibbonApplicationMenuEntrySecondary(new network_wireless(), "Wireless", null,
                CommandButtonKind.POPUP_ONLY);
        amEntrySendWireless.setPopupKeyTip("X");

        amEntrySendWireless.setPopupCallback(new PopupPanelCallback() {

            @Override
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JCommandPopupMenu wirelessChoices = new JCommandPopupMenu();

                JCommandMenuButton wiFiMenuButton = new JCommandMenuButton("Via WiFi", new EmptyResizableIcon(16));
                wiFiMenuButton.setActionKeyTip("W");
                wiFiMenuButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("WiFi activated");
                    }
                });
                wirelessChoices.addMenuButton(wiFiMenuButton);

                JCommandMenuButton blueToothMenuButton = new JCommandMenuButton("Via BlueTooth", new EmptyResizableIcon(16));
                blueToothMenuButton.setActionKeyTip("B");
                blueToothMenuButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("BlueTooth activated");
                    }
                });
                wirelessChoices.addMenuButton(blueToothMenuButton);
                return wirelessChoices;
            }
        });

        amEntrySendWireless.setDescriptionText("Localiza un dispositivo wireless y envia una copia del documento a el");
        amEntrySend.addSecondaryMenuGroup("Envia una copia del documento a otra(s) persona(s)", amEntrySendMail, amEntrySendDoc, amEntrySendWireless);
        amEntrySend.setEnabled(false);

        // import export
        RibbonApplicationMenuEntryPrimary amEntryImportExport = new RibbonApplicationMenuEntryPrimary(getResizableIconFromResource("/resources/images/data-import-export.png"), "Importar/Exportar",
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Invoked printing document");
                    }
                }, CommandButtonKind.POPUP_ONLY);
        amEntryImportExport.setActionKeyTip("I");
        amEntryImportExport.setPopupKeyTip("U");

        RibbonApplicationMenuEntrySecondary amEntryImport = new RibbonApplicationMenuEntrySecondary(getResizableIconFromResource("/resources/images/import_data.png"), "Importar", null,
                CommandButtonKind.ACTION_ONLY);
        amEntryImport.setDescriptionText("Selecciona entidad y ubicacion de archivo a importar");
        amEntryImport.setActionKeyTip("I");

        RibbonApplicationMenuEntrySecondary amEntryExport = new RibbonApplicationMenuEntrySecondary(getResizableIconFromResource("/resources/images/export_data.png"), "Exportar", null,
                CommandButtonKind.ACTION_ONLY);
        amEntryExport.setDescriptionText("Selecciona entidad y nombre de archivo a exportar");
        amEntryExport.setActionKeyTip("E");

        amEntryImportExport.addSecondaryMenuGroup("Importa/Exporta Entidades", amEntryImport, amEntryExport);
        amEntryImportExport.setEnabled(false);

        // cerrar entry
        RibbonApplicationMenuEntryPrimary amEntryClose = new RibbonApplicationMenuEntryPrimary(getResizableIconFromResource("/resources/images/folder-close-icon.png"),
                "Cerrar", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarTabs();
            }
        }, CommandButtonKind.ACTION_ONLY);
        amEntryClose.setActionKeyTip("C");
        amEntryClose.setEnabled(false);


        // creando app menu
        RibbonApplicationMenu applicationMenu = new RibbonApplicationMenu();
        applicationMenu.addMenuEntry(amEntryNew);
        applicationMenu.addMenuEntry(amEntryOpen);
        applicationMenu.addMenuEntry(amEntrySave);
        applicationMenu.addMenuEntry(amEntrySaveAs);
        applicationMenu.addMenuSeparator();
        applicationMenu.addMenuEntry(amEntryPrint);
        applicationMenu.addMenuEntry(amEntrySend);
        applicationMenu.addMenuEntry(amEntryImportExport);
        applicationMenu.addMenuSeparator();
        applicationMenu.addMenuEntry(amEntryClose);

        // the footer
        RibbonApplicationMenuEntryFooter amFooterProps = new RibbonApplicationMenuEntryFooter(new document_properties(), "Opciones",
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Invoked Options");
                    }
                });
        amFooterProps.setEnabled(false);

        RibbonApplicationMenuEntryFooter amFooterExit = new RibbonApplicationMenuEntryFooter(new system_log_out(), "Salir",
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doExit();
                    }
                });
        applicationMenu.addFooterEntry(amFooterProps);
        applicationMenu.addFooterEntry(amFooterExit);

        this.getRibbon().setApplicationMenu(applicationMenu);

        RichTooltip appMenuRichTooltip = new RichTooltip();
        appMenuRichTooltip.setTitle("PsychSys Button");
        appMenuRichTooltip.addDescriptionSection("Click aqui para abrir, guardar, imprimir y demas opciones del sistema");
        try {
            appMenuRichTooltip.setMainImage(ImageIO.read(getClass().getResource("/resources/images/psychsys_app_menu_174x156.png")));
            appMenuRichTooltip.setFooterImage(ImageIO.read(getClass().getResource("/resources/images/help-browser.png")));
        } catch (IOException ioe) {
        }
        appMenuRichTooltip.addFooterSection("Presione F1 para ayuda");
        this.getRibbon().setApplicationMenuRichTooltip(appMenuRichTooltip);
        this.getRibbon().setApplicationMenuKeyTip("P");
    }

    private void initJRibbon() {
        RibbonApplicationMenu ram = new RibbonApplicationMenu();
        JRibbon jr = getRibbon();
        jr.setApplicationMenu(ram);
        configureTaskBar();
        configureApplicationMenu();

        jr.configureHelp(getResizableIconFromResource("/resources/images/help_icon.png"), new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(getRootPane(), "No Implementado Todavia");
            }
        });

        jr.addTask(getHomeTask());
        jr.addTask(getMantenimientoTask());
        jr.addTask(getReportesTask());
        jr.addTask(getToolsAndSettingsTask());

        /*jr.addChangeListener(new ChangeListener() {
        
        @Override
        public void stateChanged(ChangeEvent e) {
        taskSelectedLabel.setText(getRibbon().getSelectedTask().getTitle());
        JOptionPane.showMessageDialog(rootPane, "No Implementado Todavia");
        }
        });*/
    }

    private void setJRibbonComponentEnabled(JComponent comp, boolean state) {
        comp.setEnabled(state);
    }

    private JComponent getJRibbonCompByName(String name) {
        return null;
    }

    private JComponent getJRibbonCompByCaption(String caption) {
        return null;
    }

    private void disableJRibbonComponents() {
        // disable taskbar
        for (Component comp : getRibbon().getTaskbarComponents()) {
            comp.setEnabled(false);
        }

        // disable app menu
        for (List<RibbonApplicationMenuEntryPrimary> lamp : getRibbon().getApplicationMenu().getPrimaryEntries()) {
            for (RibbonApplicationMenuEntryPrimary amp : lamp) {
                amp.setEnabled(false);
            }
        }
        for (RibbonApplicationMenuEntryFooter amf : getRibbon().getApplicationMenu().getFooterEntries()) {
            if (!amf.getText().equalsIgnoreCase("salir")) {
                amf.setEnabled(false);
            }
        }
    }

    private void enableJRibbonComponents() {
        // enable taskbar
        getRibbon().getTaskbarComponents().get(5).setEnabled(true);

        // enable app menu
        for (List<RibbonApplicationMenuEntryPrimary> lamp : getRibbon().getApplicationMenu().getPrimaryEntries()) {
            for (RibbonApplicationMenuEntryPrimary amp : lamp) {
                if (!Arrays.asList(new String[]{"guardar", "guardar como", "imprimir", "enviar"}).contains(amp.getText().toLowerCase())) {
                    amp.setEnabled(true);
                }
            }
        }
        for (RibbonApplicationMenuEntryFooter amf : getRibbon().getApplicationMenu().getFooterEntries()) {
            amf.setEnabled(true);
        }

        // enable bands and/or bands' jcommand buttons
    }

    private void setJRibbonComponentsEnabled(boolean state) {
        if (state) {
            enableJRibbonComponents();
            JRibbon ribbon = getRibbon();
            for (int i = 0; i < ribbon.getTaskCount(); i++) {
                RibbonTask rt = ribbon.getTask(i);
                for (int j = 0; j < rt.getBandCount(); j++) {
                    JRibbonBand banda = (JRibbonBand) rt.getBand(j);
                    if (rt.getTitle().equalsIgnoreCase("home") && banda.getTitle().equalsIgnoreCase("acceso")) {
                        continue;
                    }
                    JBandControlPanel jbcp = banda.getControlPanel();
                    jbcp.setEnabled(state);
                    for (Component comp : jbcp.getComponents()) {
                        if (comp instanceof JCommandButton) {
                            String btnText = ((JCommandButton) comp).getText();
                            if (Arrays.asList(new String[]{"editar", "eliminar", "cambiar estado", "pegar", "copiar", "cortar"}).contains(btnText.toLowerCase())) {
                                continue;
                            }
                            /*if (btnText.equalsIgnoreCase("ver")) {
                            }*/
                        }
                        comp.setEnabled(state);
                    }
                }
            }
        } else {
            disableJRibbonComponents();

            JRibbon ribbon = getRibbon();
            for (int i = 0; i < ribbon.getTaskCount(); i++) {
                RibbonTask rt = ribbon.getTask(i);
                for (int j = 0; j < rt.getBandCount(); j++) {
                    JRibbonBand banda = (JRibbonBand) rt.getBand(j);
                    if (rt.getTitle().equalsIgnoreCase("home") && banda.getTitle().equalsIgnoreCase("acceso")) {
                        continue;
                    }
                    JBandControlPanel jbcp = banda.getControlPanel();
                    for (Component comp : jbcp.getComponents()) {
                        comp.setEnabled(state);
                    }
                }
            }
        }
    }

    private void initComponents() {
        //LookAndFeelSelector.printAvailableLAF();
        PsychSysDesktop.appInstances += 1;
        if (PsychSysDesktop.appInstances > 1) {
            return;
        }

        LookAndFeelSelector.setLookAndFeel(LookAndFeelSelector.LAF.WINDOWS);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/images/psych logo.png")));
        setApplicationIcon(getResizableIconFromResource("/resources/images/psych logo2 65x65.png"));
        setPreferredSize(new Dimension(1100, 600));
        setLocationByPlatform(true);
        initJRibbon();
        initBodyContent();
        initTimeDate();
        initStatusBar();
        setJRibbonComponentsEnabled(false);
        pack();
    }

    private void doLogin(JCommandButton logInOutButton) {
        Login login = new Login(this, true);
        login.setLocationRelativeTo(this);
        login.setVisible(true);
        usuario = login.getUsuario();
        if (usuario != null) {
            usuarioLogueado.setText(String.format(" %s ", usuario.getUsrLogin()));
            JViewport startVP = (JViewport) ((JScrollPane) pnlBody.getComponent(0)).getComponent(0);
            JLabel lblBienvMsj = ((WelcomePage) startVP.getComponent(0)).getLblBienvenidaMensaje();
            lblBienvMsj.setText("Bienvenid@, " + login.getTxtNombreUsuario().getText());
            new Thread(new LabelToolTipShower(lblBienvMsj, 3500)).start();
            logInOutButton.setName("jcbLogOut");
            logInOutButton.setIcon(getResizableIconFromResource("/resources/images/logout.png"));
            logInOutButton.setText("Log Out");
            setJRibbonComponentsEnabled(true);
        }
    }

    private void doLogout(JCommandButton logInOutButton) {
        usuario = null;
        cerrarTabs();
        usuarioLogueado.setText("Usuario no logueado");
        logInOutButton.setName("jcbLogIn");
        logInOutButton.setIcon(getResizableIconFromResource("/resources/images/login.png"));
        logInOutButton.setText("Log In");
        setJRibbonComponentsEnabled(false);
        // TODO: flush log to db and may be close the managers
        //PsychSys.sl.flushToDataBase();
    }

    private void cerrarTabs() {
        int tabCount = pnlBody.getTabCount();
        for (int i = tabCount - 1; i >= 1; i--) {
            pnlBody.remove(i);
        }
    }

    private void doExit() {
        JCommandButton loginLogoutButton = (JCommandButton) getRibbon().getTask(0).getBand(0).getControlPanel().getComponent(1);
        if (usuario != null) {
            doLogout(loginLogoutButton);
        }
        System.exit(0);
    }

    private boolean isUsuarioLogged(boolean showWarnMessage) {
        boolean logged = false;
        if (usuario != null) {
            logged = true;
        } else {
            logged = false;
            if (showWarnMessage) {
                JOptionPane.showMessageDialog(PsychSysDesktop.this, "Usuario no logueado", "Login / Logout", JOptionPane.ERROR_MESSAGE);
            }
        }
        return logged;
    }

    private List<String> getTabCaptions() {
        List<String> caps = new ArrayList<String>();
        for (int i = 0; i < pnlBody.getComponentCount(); i++) {
            caps.add(pnlBody.getTitleAt(i));
        }
        return caps;
    }

    private void verTutores() {
        // crear/enviar entity searcher (TutorEntityDetailedSearcher)
        if (!getTabCaptions().contains("Vista: Tutores")) {
            VistaGeneralEntidades vgTutores = new VistaGeneralEntidades(new EntitySearcher.TutorBasicEntitySearcher());
            
            // TODO: Revisar implementacion
            final JTable tbl = vgTutores.getTblEntidades();
            tbl.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {
                    if (tbl.getSelectedRowCount() == 1) {
                        getRibbon().getTask(1).getBand(0).getControlPanel().getComponent(2).setEnabled(true);
                        getRibbon().getTask(1).getBand(0).getControlPanel().getComponent(3).setEnabled(true);
                    } else if (tbl.getSelectedRowCount() > 1) {
                        getRibbon().getTask(1).getBand(0).getControlPanel().getComponent(3).setEnabled(true);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    getRibbon().getTask(1).getBand(0).getControlPanel().getComponent(2).setEnabled(false);
                    getRibbon().getTask(1).getBand(0).getControlPanel().getComponent(3).setEnabled(false);
                }
            });
            pnlBody.addTab("Vista: Tutores", new JScrollPane(new VistaGeneralEntidades(new EntitySearcher.TutorBasicEntitySearcher())));
        } else {
            getToolkit().beep();
        }
    }

    private void registrarEditarTutor(RegistroEdicionModo modo, Tutor tutor) {
        if (modo != null && modo.equals(RegistroEdicionModo.REGISTRO)) {
            final RegistroEdicionTutor ret = new RegistroEdicionTutor(modo);
            ret.setLocationRelativeTo(this);
            java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    ret.setVisible(true);
                }
            });
        } else if (modo != null && modo.equals(RegistroEdicionModo.EDICION)) {
            final RegistroEdicionTutor ret = new RegistroEdicionTutor(modo);
            ret.setTitle("Editar Tutor");
            ret.setTutorAEditar(tutor);
            ret.setLocationRelativeTo(this);
            java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    ret.setVisible(true);
                }
            });
        }
    }

    private void eliminarTutor(Tutor tutor) {
    }

    private void verEstudiantes() {
        // crear/enviar entity searcher
        if (!getTabCaptions().contains("Vista: Estudiantes")) {
            pnlBody.addTab("Vista: Estudiantes", new JScrollPane(new VistaGeneralEntidades(new EntitySearcher.EstudianteBasicEntitySearcher())));
        } else {
            getToolkit().beep();
        }
    }

    private void registrarEditarEstudiante(RegistroEdicionModo modo, Estudiante est) {
        if (modo != null && modo.equals(RegistroEdicionModo.REGISTRO)) {
            final RegistroEdicionEstudiante ree = new RegistroEdicionEstudiante(RegistroEdicionModo.REGISTRO);
            ree.setLocationRelativeTo(this);
            java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    ree.setVisible(true);
                }
            });
        } else if (modo != null && modo.equals(RegistroEdicionModo.EDICION)) {
        }
    }

    private void eliminarEstudiante(Estudiante est) {
    }

    private void verReferimientos() {
        // crear/enviar entity searcher
        if (!getTabCaptions().contains("Vista: Referimientos")) {
            pnlBody.addTab("Vista: Referimientos", new JScrollPane(new VistaGeneralEntidades(new EntitySearcher.ReferimientoBasicEntitySearcher())));
        } else {
            getToolkit().beep();
        }
    }

    private void registrarEditarReferimiento(RegistroEdicionModo modo, Referimiento ref) {
        if (modo != null && modo.equals(RegistroEdicionModo.REGISTRO)) {
            final RegistroEdicionReferimiento rer = new RegistroEdicionReferimiento(RegistroEdicionModo.REGISTRO, usuario);
            rer.setLocationRelativeTo(this);
            java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    rer.setVisible(true);
                }
            });
        } else if (modo != null && modo.equals(RegistroEdicionModo.EDICION)) {
            RegistroEdicionReferimiento rer = new RegistroEdicionReferimiento(RegistroEdicionModo.EDICION, usuario);
            rer.setTitle("Editar Referimiento");
            //rer.setRefAEditar(referimiento);
            rer.setLocationRelativeTo(this);
            rer.setVisible(true);
        }
    }

    private void eliminarReferimiento(Referimiento ref) {
    }

    private void registrarEditarObservacionReferimiento(RegistroEdicionModo modo, Referimiento ref) {
        if (modo != null) {
            final RegistroEdicionObservacionReferimiento reor = new RegistroEdicionObservacionReferimiento(modo, usuario);
            if (!modo.equals(RegistroEdicionModo.REGISTRO)) {
                // setear el txtReremiento desde aqui
            }
            reor.setLocationRelativeTo(this);
            java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    reor.setVisible(true);
                }
            });
        }
    }

    private void verObservacionReferimiento() {
    }

    private void eliminarObservacionReferimiento() {
    }

    private void cambiarEstadoReferimiento(Referimiento ref) {
        CambiarEstadoRefermiento cer = new CambiarEstadoRefermiento(ref, jpaRefDao, this, true);
        cer.setLocationRelativeTo(this);
        cer.setVisible(true);
    }

    private void verPruebasPsicologicas() {
        // crear/enviar entity searcher
        if (!getTabCaptions().contains("Vista: Pruebas Psicologicas")) {
            pnlBody.addTab("Vista: Pruebas Psicologicas", new JScrollPane(new VistaGeneralEntidades()));
        } else {
            getToolkit().beep();
        }
    }

    private void registrarEditarPruebasPsicologicas(RegistroEdicionModo modo, PruebaPsicologica pps) {
        if (modo != null && modo.equals(RegistroEdicionModo.REGISTRO)) {
            final RegistroEdicionPruebaPsicologica repp = new RegistroEdicionPruebaPsicologica(RegistroEdicionModo.REGISTRO);
            repp.setLocationRelativeTo(this);
            java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    repp.setVisible(true);
                }
            });
        } else if (modo != null && modo.equals(RegistroEdicionModo.EDICION)) {
            final RegistroEdicionPruebaPsicologica repp = new RegistroEdicionPruebaPsicologica(RegistroEdicionModo.EDICION);
            repp.setTitle("Editar Prueba Psicologica");
            //repp.setPpsAEditar(pps);
            repp.setLocationRelativeTo(this);
            java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    repp.setVisible(true);
                }
            });
        }
    }

    private void verCasos() {
        // crear/enviar entity searcher
        if (!getTabCaptions().contains("Vista: Casos")) {
            pnlBody.addTab("Vista: Casos", new JScrollPane(new VistaGeneralEntidades(new EntitySearcher.CasoBasicEntitySearcher())));
        } else {
            getToolkit().beep();
        }
    }

    private void registrarEditarCaso(RegistroEdicionModo modo, Caso caso) {
        if (modo != null && modo.equals(RegistroEdicionModo.REGISTRO)) {
            final RegistroEdicionCaso rec = new RegistroEdicionCaso(modo);
            rec.setLocationRelativeTo(this);
            java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    rec.setVisible(true);
                }
            });
        } else if (modo != null && modo.equals(RegistroEdicionModo.EDICION)) {
        }
    }

    private void eliminarCaso(Caso caso) {
    }

    private void verHistoriasClinicas() {
        // crear/enviar entity searcher
        if (!getTabCaptions().contains("Vista: Historias Clinicas")) {
            pnlBody.addTab("Vista: Historias Clinicas", new JScrollPane(new VistaGeneralEntidades(new EntitySearcher.HistoriaClinicaEntitySearcher())));
        } else {
            getToolkit().beep();
        }
    }

    private void registrarEditarHistoriaClinica(RegistroEdicionModo modo, HistoriaClinica hic) {
        if (modo != null && modo.equals(RegistroEdicionModo.REGISTRO)) {
            final RegistroEdicionHistoriaClinica rehic = new RegistroEdicionHistoriaClinica(modo);
            rehic.setUsuario(usuario);
            rehic.setLocationRelativeTo(this);
            java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    rehic.setVisible(true);
                }
            });
        } else if (modo != null && modo.equals(RegistroEdicionModo.EDICION)) {
        }
    }

    private void eliminarHistoriaClinica(HistoriaClinica hic) {
    }

    private void throwNoImplMsj() {
        JOptionPane.showMessageDialog(this, "No Implementado Todavia");
    }

    private class ButtonActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JCommandButton buttonClicked = ((JCommandButton) e.getSource());
            String buttonName = buttonClicked.getName();
            if (!buttonName.equalsIgnoreCase("jcbLogIn") && !buttonName.equalsIgnoreCase("jcbLogOut")
                    && !buttonName.equalsIgnoreCase("jcbSalir")) {
                if (!isUsuarioLogged(true)) {
                    return;
                }
            }
            if (buttonName.equalsIgnoreCase("jcbLogIn")) {
                doLogin(buttonClicked);
            } else if (buttonName.equalsIgnoreCase("jcbLogOut")) {
                int op = JOptionPane.showConfirmDialog(PsychSysDesktop.this, "Confirma desea cerrar sesion?", "Logout",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (op == JOptionPane.OK_OPTION) {
                    doLogout(buttonClicked);
                }
            } else if (buttonName.equalsIgnoreCase("jcbSalir")) {
                doExit();
            } else if (buttonName.equalsIgnoreCase("jcbVerTutores")) {
                verTutores();
            } else if (buttonName.equalsIgnoreCase("jcbRegistrarTutor")) {
                registrarEditarTutor(RegistroEdicionModo.REGISTRO, null);
            } else if (buttonName.equalsIgnoreCase("jcbEditarTutor")) {
                registrarEditarTutor(RegistroEdicionModo.EDICION, null);
            } else if (buttonName.equalsIgnoreCase("jcbEliminarTutor")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbVerEstudiantes")) {
                verEstudiantes();
            } else if (buttonName.equalsIgnoreCase("jcbRegistrarEstudiante")) {
                registrarEditarEstudiante(RegistroEdicionModo.REGISTRO, null);
            } else if (buttonName.equalsIgnoreCase("jcbEditarEstudiante")) {
                registrarEditarEstudiante(RegistroEdicionModo.EDICION, null);
            } else if (buttonName.equalsIgnoreCase("jcbEliminarEstudiante")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbVerReferimientos")) {
                verReferimientos();
            } else if (buttonName.equalsIgnoreCase("jcbRegistrarReferimiento")) {
                registrarEditarReferimiento(RegistroEdicionModo.REGISTRO, null);
            } else if (buttonName.equalsIgnoreCase("jcbEditarReferimiento")) {
                registrarEditarReferimiento(RegistroEdicionModo.EDICION, null);
            } else if (buttonName.equalsIgnoreCase("jcbEliminarReferimiento")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbObservacionReferimiento")) {
                registrarEditarObservacionReferimiento(RegistroEdicionModo.REGISTRO, null);
            } else if (buttonName.equalsIgnoreCase("jcbCambiarEstadoReferimiento")) {
                cambiarEstadoReferimiento(null);
            } else if (buttonName.equalsIgnoreCase("jcbCorregirPruebaPsicologica")) {
                CorreccionPruebaPsicologica cpps = new CorreccionPruebaPsicologica();
                cpps.setLocationRelativeTo(PsychSysDesktop.this);
                cpps.setVisible(true);
            } else if (buttonName.equalsIgnoreCase("jcbVerPruebasPsicologicas")) {
                verPruebasPsicologicas();
            } else if (buttonName.equalsIgnoreCase("jcbRegistrarPruebaPsicologica")) {
                registrarEditarPruebasPsicologicas(RegistroEdicionModo.REGISTRO, null);
            } else if (buttonName.equalsIgnoreCase("jcbEditarPruebaPsicologica")) {
            } else if (buttonName.equalsIgnoreCase("jcbEliminarPruebaPsicologica")) {
            } else if (buttonName.equalsIgnoreCase("jcbVerCasos")) {
                verCasos();
            } else if (buttonName.equalsIgnoreCase("jcbRegistrarCaso")) {
                registrarEditarCaso(RegistroEdicionModo.REGISTRO, null);
            } else if (buttonName.equalsIgnoreCase("jcbEditarCaso")) {
            } else if (buttonName.equalsIgnoreCase("jcbEliminarCaso")) {
            } else if (buttonName.equalsIgnoreCase("jcbVerHics")) {
                verHistoriasClinicas();
            } else if (buttonName.equalsIgnoreCase("jcbRegistrarHic")) {
                registrarEditarHistoriaClinica(RegistroEdicionModo.REGISTRO, null);
            } else if (buttonName.equalsIgnoreCase("jcbEditarHic")) {
            } else if (buttonName.equalsIgnoreCase("jcbEliminarHic")) {
            }
        }
    }

    private class TaskbarNuevoPopupMenu extends JCommandPopupMenu {

        public TaskbarNuevoPopupMenu() {

            JCommandMenuButton tutorButton = new JCommandMenuButton("Tutor", new EmptyResizableIcon(16));
            tutorButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    registrarEditarTutor(RegistroEdicionModo.REGISTRO, null);
                }
            });
            tutorButton.setActionKeyTip("Tutor");
            this.addMenuButton(tutorButton);

            JCommandMenuButton estudianteButton = new JCommandMenuButton("Estudiante", new EmptyResizableIcon(16));
            estudianteButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    registrarEditarEstudiante(RegistroEdicionModo.REGISTRO, null);
                }
            });
            estudianteButton.setActionKeyTip("Estudiante");
            this.addMenuButton(estudianteButton);

            JCommandMenuButton refButton = new JCommandMenuButton("Referimiento", new EmptyResizableIcon(16));
            refButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    registrarEditarReferimiento(RegistroEdicionModo.REGISTRO, null);
                }
            });
            refButton.setActionKeyTip("Referimiento");
            this.addMenuButton(refButton);

            this.addMenuSeparator();

            JCommandMenuButton ppsButton = new JCommandMenuButton("Prueba Psicologica", new EmptyResizableIcon(16));
            ppsButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    registrarEditarPruebasPsicologicas(RegistroEdicionModo.REGISTRO, null);
                }
            });
            ppsButton.setActionKeyTip("Prueba Psicologica");
            this.addMenuButton(ppsButton);

            JCommandMenuButton casoButton = new JCommandMenuButton("Caso", new EmptyResizableIcon(16));
            casoButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    registrarEditarCaso(RegistroEdicionModo.REGISTRO, null);
                }
            });
            casoButton.setActionKeyTip("Caso");
            this.addMenuButton(casoButton);

            JCommandMenuButton hicButton = new JCommandMenuButton("Historia Clinica", new EmptyResizableIcon(16));
            hicButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    registrarEditarHistoriaClinica(RegistroEdicionModo.REGISTRO, null);
                }
            });
            hicButton.setActionKeyTip("Historia Clinica");
            this.addMenuButton(hicButton);
        }
    }
}
