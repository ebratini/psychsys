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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.RichTooltip;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
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
    private JLabel usuarioLogeado = new JLabel("Usuario no logeado");
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

        usuarioLogeado.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        taskSelectedLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        timeDate.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        JPanel pnl = new JPanel(new BorderLayout(2, 0));
        pnl.add(taskSelectedLabel, BorderLayout.WEST);
        pnl.add(usuarioLogeado, BorderLayout.CENTER);
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
                    new CoreRibbonResizePolicies.High2Mid(jRibbonBand.getControlPanel()),
                    new CoreRibbonResizePolicies.Mid2Low(jRibbonBand.getControlPanel())} //new CoreRibbonResizePolicies.High2Low(jrbAfiliadosBand.getControlPanel()),
                /*new IconRibbonBandResizePolicy(jrbAfiliadosBand.getControlPanel())*/);
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

        RibbonTask rtHomeTask = new RibbonTask("Home", jrbAccesoBand);

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
                "jcbEliminarTutor", new RichTooltip("Eliminar", "Click aqui para eliminar tutor"), buttonActHandler);

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
                "jcbEliminarEstudiante", new RichTooltip("Eliminar", "Click aqui para eliminar estudiante"), buttonActHandler);

        // creando la banda
        JRibbonBand jrbEstudiantesBand = new JRibbonBand("Estudiantes", getResizableIconFromResource("/resources/images/estudiantes.png"));
        jrbEstudiantesBand.addCommandButton(jcbVerEstudiantes, RibbonElementPriority.TOP);
        jrbEstudiantesBand.addCommandButton(jcbRegistrarEstudiante, RibbonElementPriority.MEDIUM);
        jrbEstudiantesBand.addCommandButton(jcbEditarEstudiante, RibbonElementPriority.MEDIUM);
        jrbEstudiantesBand.addCommandButton(jcbEliminarEstudiante, RibbonElementPriority.MEDIUM);

        jrbEstudiantesBand.setResizePolicies(getRibbonBandResizePolicy(jrbEstudiantesBand));

        // on referimientos band
        JCommandButton jcbVerReferimientos, jcbRegistrarReferimiento, jcbEditarReferimiento, jcbEliminarReferimiento;

        jcbVerReferimientos = createJCommandButton("Ver", getResizableIconFromResource("/resources/images/referimientos.png"),
                "jcbVerReferimientos", new RichTooltip("Ver", "Click aqui para ver los referimientos"), buttonActHandler);

        jcbRegistrarReferimiento = createJCommandButton("Nuevo", getResizableIconFromResource("/resources/images/add.png"),
                "jcbRegistrarReferimiento", new RichTooltip("Nuevo", "Click aqui para registrar referimiento"), buttonActHandler);

        jcbEditarReferimiento = createJCommandButton("Editar", getResizableIconFromResource("/resources/images/editar.png"),
                "jcbEditarReferimiento", new RichTooltip("Editar", "Click aqui para editar referimiento"), buttonActHandler);

        jcbEliminarReferimiento = createJCommandButton("Eliminar", getResizableIconFromResource("/resources/images/delete.png"),
                "jcbEliminarReferimiento", new RichTooltip("Eliminar", "Click aqui para eliminar referimiento"), buttonActHandler);

        // creando la banda
        JRibbonBand jrbReferimientosBand = new JRibbonBand("Referimientos", getResizableIconFromResource("/resources/images/referimientos.png"));
        jrbReferimientosBand.addCommandButton(jcbVerReferimientos, RibbonElementPriority.TOP);
        jrbReferimientosBand.addCommandButton(jcbRegistrarReferimiento, RibbonElementPriority.MEDIUM);
        jrbReferimientosBand.addCommandButton(jcbEditarReferimiento, RibbonElementPriority.MEDIUM);
        jrbReferimientosBand.addCommandButton(jcbEliminarReferimiento, RibbonElementPriority.MEDIUM);

        jrbReferimientosBand.setResizePolicies(getRibbonBandResizePolicy(jrbReferimientosBand));


        // on pruebas psicologicas band

        JCommandButton jcbVerPruebasPsicologicas, jcbRegistrarPruebaPsicologica, jcbEditarPruebaPsicologica, jcbEliminarPruebaPsicologica;

        jcbVerPruebasPsicologicas = createJCommandButton("Ver", getResizableIconFromResource("/resources/images/pruebas_psicologicas.png"),
                "jcbVerPruebasPsicologicas", new RichTooltip("Ver", "Click aqui para ver las pruebas psicologicas"), buttonActHandler);

        jcbRegistrarPruebaPsicologica = createJCommandButton("Nuevo", getResizableIconFromResource("/resources/images/add.png"),
                "jcbRegistrarPruebaPsicologica", new RichTooltip("Nuevo", "Click aqui para registrar prueba psicologica"), buttonActHandler);

        jcbEditarPruebaPsicologica = createJCommandButton("Editar", getResizableIconFromResource("/resources/images/editar.png"),
                "jcbEditarPruebaPsicologica", new RichTooltip("Editar", "Click aqui para editar prueba psicologica"), buttonActHandler);

        jcbEliminarPruebaPsicologica = createJCommandButton("Eliminar", getResizableIconFromResource("/resources/images/delete.png"),
                "jcbEliminarPruebaPsicologica", new RichTooltip("Eliminar", "Click aqui para eliminar prueba psicologica"), buttonActHandler);

        // creando la banda
        JRibbonBand jrbPruebasPsicologicasBand = new JRibbonBand("Pruebas Psicologicas", getResizableIconFromResource("/resources/images/pruebas_psicologicas.png"));
        jrbPruebasPsicologicasBand.addCommandButton(jcbVerPruebasPsicologicas, RibbonElementPriority.TOP);
        jrbPruebasPsicologicasBand.addCommandButton(jcbRegistrarPruebaPsicologica, RibbonElementPriority.MEDIUM);
        jrbPruebasPsicologicasBand.addCommandButton(jcbEditarPruebaPsicologica, RibbonElementPriority.MEDIUM);
        jrbPruebasPsicologicasBand.addCommandButton(jcbEliminarPruebaPsicologica, RibbonElementPriority.MEDIUM);

        jrbPruebasPsicologicasBand.setResizePolicies(getRibbonBandResizePolicy(jrbPruebasPsicologicasBand));

        // casos band

        // historia clinica band


        // creando el la task
        RibbonTask rtMantenimientoTask = new RibbonTask("Mantenimiento", jrbTutoresBand, jrbEstudiantesBand,
                jrbReferimientosBand, jrbPruebasPsicologicasBand);

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
        
        
        // Master/Details Band
        /*JCommandButton jcbEmpresasAfiliados, jcbAfiliadosDependientes, jcbAfiliadosFacturas, jcbPlanesAfiliados;
        
        jcbEmpresasAfiliados = createJCommandButton("Empresas - Afiliados", getResizableIconFromResource("/resources/imagenes/empresas4.png"),
        "jcbEmpresasAfiliados", new RichTooltip("Master/Details", "Click aqui para ver reporte master/detail de empresas y afiliados"), buttonActHandler);
        
        jcbAfiliadosDependientes = createJCommandButton("Afiliados - Dependientes", getResizableIconFromResource("/resources/imagenes/afiliados.png"),
        "jcbAfiliadosDependientes", new RichTooltip("Master/Details", "Click aqui para ver reporte master/detail de afiliados y dependientes"), buttonActHandler);
        
        jcbAfiliadosFacturas = createJCommandButton("Afiliados - Facturas", getResizableIconFromResource("/resources/imagenes/dependientes2.png"),
        "jcbAfiliadosFacturas", new RichTooltip("Master/Details", "Click aqui para ver reporte master/detail de afiliados y facturas"), buttonActHandler);
        
        jcbPlanesAfiliados = createJCommandButton("Planes - Afiliados", getResizableIconFromResource("/resources/imagenes/paloma3.png"),
        "jcbPlanesAfiliados", new RichTooltip("Master/Details", "Click aqui para ver reporte master/detail de planes y afiliados"), buttonActHandler);
        
        JRibbonBand jrbMasterDetailsBand = new JRibbonBand("Master/Details", getResizableIconFromResource("/resources/imagenes/listado.png"));
        jrbListadosBand.addCommandButton(jcbEmpresasAfiliados, RibbonElementPriority.TOP);
        jrbListadosBand.addCommandButton(jcbAfiliadosDependientes, RibbonElementPriority.TOP);
        jrbListadosBand.addCommandButton(jcbAfiliadosFacturas, RibbonElementPriority.TOP);
        jrbListadosBand.addCommandButton(jcbPlanesAfiliados, RibbonElementPriority.TOP);
        
        jrbMasterDetailsBand.setResizePolicies((List) Arrays.asList(
        new CoreRibbonResizePolicies.Mirror(jrbMasterDetailsBand.getControlPanel()),
        //new CoreRibbonResizePolicies.Mid2Low(jrbMasterDetailsBand.getControlPanel()),
        new IconRibbonBandResizePolicy(jrbMasterDetailsBand.getControlPanel())));
        
        jrbMasterDetailsBand.setResizePolicies(getRibbonBandResizePolicy(jrbMasterDetailsBand));*/

        // creando el la task
        RibbonTask rtReportesTask = new RibbonTask("Reportes", jrbListadosBand);

        return rtReportesTask;
    }

    private RibbonTask getToolsTask() {
        return null;
    }

    private RibbonTask getSettingsTask() {
        return null;
    }

    private Component getTaskBar() {
        JPanel pnlTaskBar = new JPanel(new BorderLayout(2, 0));
        pnlTaskBar.add(new JButton(getResizableIconFromResource("/resources/images/new_15x15.png")));
        return pnlTaskBar;
    }

    private void initJRibbon() {
        RibbonApplicationMenu ram = new RibbonApplicationMenu();
        JRibbon jr = getRibbon();
        jr.addTask(getHomeTask());
        jr.addTask(getMantenimientoTask());
        jr.addTask(getReportesTask());

        jr.addTaskbarComponent(getTaskBar());
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
        JRibbon ribbon = getRibbon();
        for (int i = 0; i < ribbon.getTaskCount(); i++) {
            RibbonTask rt = ribbon.getTask(i);
            if (rt.getTitle().equalsIgnoreCase("home")) {
                continue;
            }
            for (int j = 0; j < rt.getBandCount(); j++) {
                JRibbonBand banda = (JRibbonBand) rt.getBand(j);
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
            usuarioLogeado.setText(String.format(" %s ", usuario.getUsrLogin()));
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
        usuarioLogeado.setText("Usuario no logeado");
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
                JOptionPane.showMessageDialog(PsychSysDesktop.this, "Usuario no logeado",
                        "Login / Logout", JOptionPane.ERROR_MESSAGE);
            }
        }
        return logged;
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
                RegistroEdicionTutor ret = new RegistroEdicionTutor(RegistroEdicionModo.REGISTRO);
                ret.setLocationRelativeTo(PsychSysDesktop.this);
                ret.setVisible(true);
            } else if (buttonName.equalsIgnoreCase("jcbEditarTutor")) {
                RegistroEdicionTutor ret = new RegistroEdicionTutor(RegistroEdicionModo.EDICION);
                ret.setTitle("Editar Tutor");
                ret.setLocationRelativeTo(PsychSysDesktop.this);
                ret.setVisible(true);
            } else if (buttonName.equalsIgnoreCase("jcbEliminarTutor")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbVerEstudiantes")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbRegistrarEstudiante")) {
                RegistroEdicionEstudiante ree = new RegistroEdicionEstudiante(RegistroEdicionModo.REGISTRO);
                ree.setLocationRelativeTo(PsychSysDesktop.this);
                ree.setVisible(true);
            } else if (buttonName.equalsIgnoreCase("jcbEditarEstudiante")) {
                /*RegistroEdicionEstudiante ree = new RegistroEdicionEstudiante(RegistroEdicionModo.EDICION);
                ree.setTitle("Editar Estudiante");
                ree.setLocationRelativeTo(PsychSysDesktop.this);
                ree.setVisible(true);*/
            } else if (buttonName.equalsIgnoreCase("jcbEliminarEstudiante")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbVerReferimientos")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbRegistrarReferimiento")) {
                RegistroEdicionReferimiento rer = new RegistroEdicionReferimiento(RegistroEdicionModo.REGISTRO);
                rer.setLocationRelativeTo(PsychSysDesktop.this);
                rer.setVisible(true);
            } else if (buttonName.equalsIgnoreCase("jcbEditarReferimiento")) {
                RegistroEdicionReferimiento rer = new RegistroEdicionReferimiento(RegistroEdicionModo.EDICION);
                rer.setTitle("Editar Referimiento");
                rer.setLocationRelativeTo(PsychSysDesktop.this);
                rer.setVisible(true);
            } else if (buttonName.equalsIgnoreCase("jcbEliminarReferimiento")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbVerPruebasPsicologicas")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbRegistrarPruebaPsicologica")) {
                RegistroEdicionPruebaPsicologica repp = new RegistroEdicionPruebaPsicologica(RegistroEdicionModo.REGISTRO);
                repp.setLocationRelativeTo(PsychSysDesktop.this);
                repp.setVisible(true);
            } else if (buttonName.equalsIgnoreCase("jcbEditarPruebaPsicologica")) {
                RegistroEdicionPruebaPsicologica repp = new RegistroEdicionPruebaPsicologica(RegistroEdicionModo.EDICION);
                repp.setTitle("Editar Prueba Psicologica");
                repp.setLocationRelativeTo(PsychSysDesktop.this);
                repp.setVisible(true);
            }
        }
    }
}
