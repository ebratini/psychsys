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

import org.salvador_dali.psychsys.model.entities.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.JCommandButton.CommandButtonKind;
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
import org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority;
import org.pushingpixels.flamingo.api.ribbon.RibbonTask;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.IconRibbonBandResizePolicy;
import org.pushingpixels.flamingo.api.ribbon.resize.RibbonBandResizePolicy;
import org.pushingpixels.flamingo.internal.ui.common.JRichTooltipPanel;
import org.pushingpixels.flamingo.internal.ui.ribbon.JBandControlPanel;
import org.salvador_dali.psychsys.model.entities.Caso;
import org.salvador_dali.psychsys.model.entities.Estudiante;
import org.salvador_dali.psychsys.model.entities.HistoriaClinica;
import org.salvador_dali.psychsys.model.entities.PruebaPsicologica;
import org.salvador_dali.psychsys.model.entities.Referimiento;
import org.salvador_dali.psychsys.model.entities.Tutor;
import psychsys.PsychSys;

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
    private JPanel pnlBody;
    private JLabel backGroundImagePnlBody = new JLabel();
    // for the status bar
    private JPanel pnlStatusBar;
    private JLabel statusMessageLabel = new JLabel("Ready");
    private JLabel taskSelectedLabel = new JLabel();
    private JLabel usuariologueado = new JLabel("Usuario no logueado");
    private JLabel timeDate = new JLabel("Time/Date");

    public PsychSysDesktop() {
        super("PsychSys");
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                //super.windowClosing(e);
                ((JCommandButton) getRibbon().getTask(0).getBand(0).getControlPanel().getComponent(1)).doActionClick();
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

    private void addGap(int width, int height) {
        JPanel gap = new JPanel();
        gap.setSize(width, height);
        //gap.getBorder()
        add(gap);
    }

    private ResizableIcon getResizableIconFromResource(String resource) {
        return ImageWrapperResizableIcon.getIcon(getClass().getResource(resource), new Dimension(48, 48));
    }

    private void initBodyContent() {
        pnlBody = new JPanel();
        pnlBody.setBorder(new EtchedBorder());
        pnlBody.setBackground(Color.WHITE);
        backGroundImagePnlBody.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/psych_head.png")));
        backGroundImagePnlBody.setBounds(pnlBody.getBounds());
        pnlBody.add(backGroundImagePnlBody);
        add(pnlBody, BorderLayout.CENTER);
    }

    private void initTimeDate() {
        new TimeDateShower(timeDate).start();
    }

    private void initStatusBar() {
        pnlStatusBar = new JPanel(new BorderLayout());
        pnlStatusBar.setPreferredSize(new Dimension(400, 25));
        pnlStatusBar.add(statusMessageLabel, BorderLayout.WEST);

        usuariologueado.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        taskSelectedLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        timeDate.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        JPanel pnl = new JPanel(new BorderLayout(2, 0));
        pnl.add(taskSelectedLabel, BorderLayout.WEST);
        pnl.add(usuariologueado, BorderLayout.CENTER);
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
        JCommandButton jcbVerReferimientos, jcbRegistrarReferimiento, jcbEditarReferimiento, jcbEliminarReferimiento, jcbCrearObservacion;

        jcbVerReferimientos = createJCommandButton("Ver", getResizableIconFromResource("/resources/images/referimientos.png"),
                "jcbVerReferimientos", new RichTooltip("Ver", "Click aqui para ver los referimientos"), buttonActHandler);
        jcbVerReferimientos.setCommandButtonKind(CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
        jcbVerReferimientos.setPopupCallback(new PopupPanelCallback() {

            @Override
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                return new JCommandPopupMenu() {
                    {
                        JCommandMenuButton verReferimientoButton = new JCommandMenuButton("Observacion Referimiento", new EmptyResizableIcon(16));
                        verReferimientoButton.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                verObservacionReferimiento();
                            }
                        });
                        verReferimientoButton.setActionKeyTip("Observacion Referimiento");
                        this.addMenuButton(verReferimientoButton);
                    }
                };
            }
        });
        jcbVerReferimientos.setPopupRichTooltip(new RichTooltip("Ver", "Click aqui para mas opciones"));
        

        jcbRegistrarReferimiento = createJCommandButton("Nuevo", getResizableIconFromResource("/resources/images/add.png"),
                "jcbRegistrarReferimiento", new RichTooltip("Nuevo", "Click aqui para registrar referimiento"), buttonActHandler);
        jcbRegistrarReferimiento.setCommandButtonKind(CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
        //jcbRegistrarReferimiento.setPopupOrientationKind(JCommandButton.CommandButtonPopupOrientationKind.SIDEWARD);
        jcbRegistrarReferimiento.setPopupCallback(new PopupPanelCallback() {

            @Override
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                return new JCommandPopupMenu() {
                    {
                        JCommandMenuButton nuevoReferimientoButton = new JCommandMenuButton("Observacion Referimiento", new EmptyResizableIcon(16));
                        nuevoReferimientoButton.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                registrarEditarObservacionReferimiento(RegistroEdicionModo.REGISTRO, null);
                            }
                        });
                        nuevoReferimientoButton.setActionKeyTip("Observacion Referimiento");
                        this.addMenuButton(nuevoReferimientoButton);
                    }
                };
            }
        });
        jcbRegistrarReferimiento.setPopupRichTooltip(new RichTooltip("Nuevo", "Click aqui para mas opciones"));


        jcbEditarReferimiento = createJCommandButton("Editar", getResizableIconFromResource("/resources/images/editar.png"),
                "jcbEditarReferimiento", new RichTooltip("Editar", "Click aqui para editar referimiento"), buttonActHandler);

        jcbEliminarReferimiento = createJCommandButton("Eliminar", getResizableIconFromResource("/resources/images/delete.png"),
                "jcbEliminarReferimiento", new RichTooltip("Eliminar", "Click aqui para eliminar referimiento(s)"), buttonActHandler);

        //jcbCrearObservacion = createJCommandButton("Crear Observacion", getResizableIconFromResource("/resources/images/referimientos.png"),
        //      "jcbCrearObservacion", new RichTooltip("Crear Observacion", "Click aqui para crear observacion de referimiento"), buttonActHandler);

        // creando la banda
        JRibbonBand jrbReferimientosBand = new JRibbonBand("Referimientos", getResizableIconFromResource("/resources/images/referimientos.png"));
        jrbReferimientosBand.addCommandButton(jcbVerReferimientos, RibbonElementPriority.TOP);
        jrbReferimientosBand.addCommandButton(jcbRegistrarReferimiento, RibbonElementPriority.MEDIUM);
        jrbReferimientosBand.addCommandButton(jcbEditarReferimiento, RibbonElementPriority.MEDIUM);
        jrbReferimientosBand.addCommandButton(jcbEliminarReferimiento, RibbonElementPriority.MEDIUM);
        //jrbReferimientosBand.addCommandButton(jcbCrearObservacion, RibbonElementPriority.MEDIUM);

        //jrbReferimientosBand.setResizePolicies(this.getRibbonBandResizePolicy(jrbReferimientosBand));
        jrbReferimientosBand.setResizePolicies((List) Arrays.asList(new Object[]{
                    /*new CoreRibbonResizePolicies.None(jrbReferimientosBand.getControlPanel()),*/
                    new CoreRibbonResizePolicies.Mirror(jrbReferimientosBand.getControlPanel()),                    
                    new CoreRibbonResizePolicies.High2Mid(jrbReferimientosBand.getControlPanel()),
                    new CoreRibbonResizePolicies.Mid2Low(jrbReferimientosBand.getControlPanel()),
                    new IconRibbonBandResizePolicy(jrbReferimientosBand.getControlPanel())}));


        // on pruebas psicologicas band

        JCommandButton jcbVerPruebasPsicologicas, jcbRegistrarPruebaPsicologica, jcbEditarPruebaPsicologica, jcbEliminarPruebaPsicologica, jcbCorregirPruebaPsicologica;

        jcbVerPruebasPsicologicas = createJCommandButton("Ver", getResizableIconFromResource("/resources/images/pruebas_psicologicas.png"),
                "jcbVerPruebasPsicologicas", new RichTooltip("Ver", "Click aqui para ver las pruebas psicologicas"), buttonActHandler);

        jcbRegistrarPruebaPsicologica = createJCommandButton("Nuevo", getResizableIconFromResource("/resources/images/add.png"),
                "jcbRegistrarPruebaPsicologica", new RichTooltip("Nuevo", "Click aqui para registrar prueba psicologica"), buttonActHandler);

        jcbEditarPruebaPsicologica = createJCommandButton("Editar", getResizableIconFromResource("/resources/images/editar.png"),
                "jcbEditarPruebaPsicologica", new RichTooltip("Editar", "Click aqui para editar prueba psicologica"), buttonActHandler);

        jcbEliminarPruebaPsicologica = createJCommandButton("Eliminar", getResizableIconFromResource("/resources/images/delete.png"),
                "jcbEliminarPruebaPsicologica", new RichTooltip("Eliminar", "Click aqui para eliminar prueba(s) psicologica"), buttonActHandler);

        jcbCorregirPruebaPsicologica = createJCommandButton("Corregir", getResizableIconFromResource("/resources/images/corregir.png"),
                "jcbCorregirPruebaPsicologica", new RichTooltip("Corregir", "Click aqui para corregir prueba(s) psicologica"), buttonActHandler);

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
                    new CoreRibbonResizePolicies.None(jrbCasosBand.getControlPanel()),
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
        new CoreRibbonResizePolicies.None(jrbHistoriaClinicaBand.getControlPanel()),
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
                "jcbListadoEstudiantes", new RichTooltip("Listado Afiliados", "Click aqui para ver reporte listado de estudiantes"), buttonActHandler);

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

    private RibbonTask getToolsTask() {
        return null;
    }

    private RibbonTask getSettingsTask() {
        return null;
    }

    private JCommandButton getPasteButton() {
        JCommandButton PasteButton = new JCommandButton("", getResizableIconFromResource("/resources/images/paste.png"));
        PasteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Taskbar Paste");
            }
        });
        PasteButton.setActionRichTooltip(new RichTooltip("Pegar", "Click aqui para pegar"));

        return PasteButton;
    }

    private void configureTaskBar() {
        // paste button        
        JCommandButton taskbarPasteButton = new JCommandButton("", getResizableIconFromResource("/resources/images/paste.png"));
        taskbarPasteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Taskbar Paste");
            }
        });
        taskbarPasteButton.setActionRichTooltip(new RichTooltip("Pegar", "Click aqui para pegar"));

        // cortar button
        JCommandButton taskbarCortarButton = new JCommandButton("", getResizableIconFromResource("/resources/images/scissors.png"));
        taskbarCortarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Taskbar Cortar");
            }
        });
        taskbarCortarButton.setActionRichTooltip(new RichTooltip("Cortar", "Click aqui para cortar"));

        // copiar button        
        JCommandButton taskbarCopiarButton = new JCommandButton("", getResizableIconFromResource("/resources/images/copy.png"));
        taskbarCopiarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Taskbar Copiar");
            }
        });
        taskbarCopiarButton.setActionRichTooltip(new RichTooltip("Copiar", "Click aqui para copiar"));

        // nuevo button
        JCommandButton taskbarNuevoButton = new JCommandButton("", getResizableIconFromResource("/resources/images/new_15x15.png"));
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
        this.getRibbon().addTaskbarComponent(new JSeparator(SwingConstants.VERTICAL));
        this.getRibbon().addTaskbarComponent(taskbarNuevoButton);
    }

    private void initJRibbon() {
        RibbonApplicationMenu ram = new RibbonApplicationMenu();
        JRibbon jr = getRibbon();
        jr.configureHelp(getResizableIconFromResource("/resources/images/help_icon.png"), new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(getRootPane(), "No Implementado Todavia");
            }
        });
        jr.addTask(getHomeTask());
        jr.addTask(getMantenimientoTask());
        jr.addTask(getReportesTask());

        configureTaskBar();
        jr.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                taskSelectedLabel.setText(getRibbon().getSelectedTask().getTitle());
                JOptionPane.showMessageDialog(rootPane, "No Implementado Todavia");
            }
        });

        jr.setApplicationMenu(ram);
    }

    private void setJRibbonComponentsEnabled(boolean state) {

        for (Component comp : getRibbon().getTaskbarComponents()) {
            comp.setEnabled(state);
        }
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
        initStatusBar();
        initTimeDate();
        setJRibbonComponentsEnabled(false);
        pack();
    }

    private void doLogin(JCommandButton logInOutButton) {
        Login login = new Login(PsychSysDesktop.this, true);
        login.setLocationRelativeTo(PsychSysDesktop.this);
        login.setVisible(true);
        usuario = login.getUsuario();
        if (usuario != null) {
            usuariologueado.setText(String.format(" %s ", usuario.getUsrLogin()));
            JRichTooltipPanel rtp = new JRichTooltipPanel(new RichTooltip("Login", "Bienvenido, " + login.getTxtNombreUsuario().getText()));
            pnlBody.add(rtp, BorderLayout.SOUTH);
            new Thread(new ToolTipShower(rtp)).start();
            logInOutButton.setName("jcbLogOut");
            logInOutButton.setIcon(getResizableIconFromResource("/resources/images/logout.png"));
            logInOutButton.setText("Log Out");
            setJRibbonComponentsEnabled(true);
        }
    }

    private void doLogout(JCommandButton logInOutButton) {
        usuario = null;
        usuariologueado.setText("Usuario no logueado");
        logInOutButton.setName("jcbLogIn");
        logInOutButton.setIcon(getResizableIconFromResource("/resources/images/login.png"));
        logInOutButton.setText("Log In");
        setJRibbonComponentsEnabled(false);
        // TODO: flush log to db and may be close the managers
        //Syscafil.sl.flushToDataBase();
    }

    private void doExit(JCommandButton logInOutButton) {
        if (usuario != null) {
            doLogout(logInOutButton);
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

    private void verTutores() {
    }

    private void registrarEditarTutor(RegistroEdicionModo modo, Tutor tutor) {
        if (modo != null && modo.equals(RegistroEdicionModo.REGISTRO)) {
            RegistroEdicionTutor ret = new RegistroEdicionTutor(modo);
            ret.setLocationRelativeTo(this);
            ret.setVisible(true);
            //ret.requestFocus();
        } else if (modo != null && modo.equals(RegistroEdicionModo.EDICION)) {
            RegistroEdicionTutor ret = new RegistroEdicionTutor(modo);
            ret.setTitle("Editar Tutor");
            ret.setTutorAEditar(tutor);
            ret.setLocationRelativeTo(this);
            ret.setVisible(true);
        }
    }

    private void eliminarTutor(Tutor tutor) {
    }

    private void verEstudiantes() {
    }

    private void registrarEditarEstudiante(RegistroEdicionModo modo, Estudiante est) {
        if (modo != null && modo.equals(RegistroEdicionModo.REGISTRO)) {
            RegistroEdicionEstudiante ree = new RegistroEdicionEstudiante(RegistroEdicionModo.REGISTRO);
            ree.setLocationRelativeTo(this);
            ree.setVisible(true);
        } else if (modo != null && modo.equals(RegistroEdicionModo.EDICION)) {
        }
    }

    private void eliminarEstudiante(Estudiante est) {
    }

    private void verReferimientos() {
    }

    private void registrarEditarReferimiento(RegistroEdicionModo modo, Referimiento ref) {
        if (modo != null && modo.equals(RegistroEdicionModo.REGISTRO)) {
            RegistroEdicionReferimiento rer = new RegistroEdicionReferimiento(RegistroEdicionModo.REGISTRO, usuario);
            rer.setLocationRelativeTo(this);
            rer.setVisible(true);
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
        if (modo != null && modo.equals(RegistroEdicionModo.REGISTRO)) {
            RegistroEdicionObservacionReferimiento reor = new RegistroEdicionObservacionReferimiento(RegistroEdicionModo.REGISTRO, usuario);
            reor.setLocationRelativeTo(this);
            reor.setVisible(true);
        } else if (modo != null && modo.equals(RegistroEdicionModo.EDICION)) {
        }
    }

    private void verObservacionReferimiento() {
    }

    private void eliminarObservacionReferimiento(RegistroEdicionModo modo, Referimiento ref) {
    }

    private void verPruebasPsicologicas() {
    }

    private void registrarEditarPruebasPsicologicas(RegistroEdicionModo modo, PruebaPsicologica pps) {
        if (modo != null && modo.equals(RegistroEdicionModo.REGISTRO)) {
            RegistroEdicionPruebaPsicologica repp = new RegistroEdicionPruebaPsicologica(RegistroEdicionModo.REGISTRO);
            repp.setLocationRelativeTo(this);
            repp.setVisible(true);
        } else if (modo != null && modo.equals(RegistroEdicionModo.EDICION)) {
            RegistroEdicionPruebaPsicologica repp = new RegistroEdicionPruebaPsicologica(RegistroEdicionModo.EDICION);
            repp.setTitle("Editar Prueba Psicologica");
            //repp.setPpsAEditar(pps);
            repp.setLocationRelativeTo(this);
            repp.setVisible(true);
        }
    }

    private void verCasos() {
    }

    private void registrarEditarCaso(RegistroEdicionModo modo, Caso caso) {
        if (modo != null && modo.equals(RegistroEdicionModo.REGISTRO)) {
            RegistroEdicionCaso rec = new RegistroEdicionCaso(modo);
            rec.setLocationRelativeTo(this);
            rec.setVisible(true);
        } else if (modo != null && modo.equals(RegistroEdicionModo.EDICION)) {
        }
    }

    private void eliminarCaso(Caso caso) {
    }

    private void verHistoriaClinica() {
    }

    private void registrarEditarHistoriaClinica(RegistroEdicionModo modo, HistoriaClinica hic) {
        if (modo != null && modo.equals(RegistroEdicionModo.REGISTRO)) {
            RegistroEdicionHistoriaClinica rehic = new RegistroEdicionHistoriaClinica(modo);
            rehic.setLocationRelativeTo(this);
            rehic.setVisible(true);
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
                doExit(buttonClicked);
            } else if (buttonName.equalsIgnoreCase("jcbVerTutores")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbRegistrarTutor")) {
                registrarEditarTutor(RegistroEdicionModo.REGISTRO, null);
            } else if (buttonName.equalsIgnoreCase("jcbEditarTutor")) {
                registrarEditarTutor(RegistroEdicionModo.EDICION, null);
            } else if (buttonName.equalsIgnoreCase("jcbEliminarTutor")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbVerEstudiantes")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbRegistrarEstudiante")) {
                registrarEditarEstudiante(RegistroEdicionModo.REGISTRO, null);
            } else if (buttonName.equalsIgnoreCase("jcbEditarEstudiante")) {
                registrarEditarEstudiante(RegistroEdicionModo.EDICION, null);
            } else if (buttonName.equalsIgnoreCase("jcbEliminarEstudiante")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbVerReferimientos")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbRegistrarReferimiento")) {
                registrarEditarReferimiento(RegistroEdicionModo.REGISTRO, null);
            } else if (buttonName.equalsIgnoreCase("jcbEditarReferimiento")) {
                registrarEditarReferimiento(RegistroEdicionModo.EDICION, null);
            } else if (buttonName.equalsIgnoreCase("jcbEliminarReferimiento")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbVerPruebasPsicologicas")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbRegistrarPruebaPsicologica")) {
                registrarEditarPruebasPsicologicas(RegistroEdicionModo.REGISTRO, null);
            } else if (buttonName.equalsIgnoreCase("jcbEditarPruebaPsicologica")) {
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
