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
import org.salvador_dali.psychsys.business.BehaviorNotImplementedException;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private static int appInstances;
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
        super("Syscafil");
        /*addWindowListener(new ExitHandler() {

            @Override
            public void windowClosing(WindowEvent e) {
                //super.windowClosing(e);
                //((JCommandButton) getRibbon().getTask(0).getBand(0).getControlPanel().getComponent(1)).doActionClick();
            }
        });*/
        initComponents();
        //appInstances += 1;
    }

    public static void main(String[] args) {
            PsychSysDesktop sd = new PsychSysDesktop();
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
        backGroundImagePnlBody.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagenes/prebea_banner.png")));
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
        return (List) Arrays.asList(new Object [] {
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
        jcbLogInOut = createJCommandButton("Log In", getResizableIconFromResource("/resources/imagenes/login.png"), "jcbLogIn",
                new RichTooltip("Login/Logout", "Click aqui para abrir/cerrar sesion de usuario"), buttonActHandler);

        jcbSalir = createJCommandButton("Salir", getResizableIconFromResource("/resources/imagenes/salir.png"), "jcbSalir",
                new RichTooltip("Salir", "Click aqui para salir de la aplicacion"), buttonActHandler);

        // creando la banda "Acceso"
        JRibbonBand jrbAccesoBand = new JRibbonBand("Acceso", getResizableIconFromResource("/resources/imagenes/login.png"));
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
        // on Empresas band

        JCommandButton jcbVerEmpresas, jcbCrearEmpresa, jcbEditarEmpresa, jcbEliminarEmpresa;

        jcbVerEmpresas = createJCommandButton("Ver", getResizableIconFromResource("/resources/imagenes/empresas4.png"),
                "jcbVerEmpresas", new RichTooltip("Ver", "Click aqui para ver las empresas"), buttonActHandler);

        jcbCrearEmpresa = createJCommandButton("Nuevo", getResizableIconFromResource("/resources/imagenes/add2.png"),
                "jcbCrearEmpresa", new RichTooltip("Nuevo", "Click aqui para crear empresa"), buttonActHandler);

        jcbEditarEmpresa = createJCommandButton("Editar", getResizableIconFromResource("/resources/imagenes/editar3.png"),
                "jcbEditarEmpresa", new RichTooltip("Editar", "Click aqui para editar empresa"), buttonActHandler);

        jcbEliminarEmpresa = createJCommandButton("Eliminar", getResizableIconFromResource("/resources/imagenes/delete.png"),
                "jcbEliminarEmpresa", new RichTooltip("Eliminar", "Click aqui para eliminar empresa"), buttonActHandler);

        JRibbonBand jrbEmpresasBand = new JRibbonBand("Empresas", getResizableIconFromResource("/resources/imagenes/empresas4.png"));
        jrbEmpresasBand.addCommandButton(jcbVerEmpresas, RibbonElementPriority.TOP);
        jrbEmpresasBand.addCommandButton(jcbCrearEmpresa, RibbonElementPriority.MEDIUM);
        jrbEmpresasBand.addCommandButton(jcbEditarEmpresa, RibbonElementPriority.MEDIUM);
        jrbEmpresasBand.addCommandButton(jcbEliminarEmpresa, RibbonElementPriority.MEDIUM);

        jrbEmpresasBand.setResizePolicies(getRibbonBandResizePolicy(jrbEmpresasBand));

        // on afiliados band

        JCommandButton jcbVerAfiliados, jcbCrearAfiliado, jcbEditarAfiliado, jcbEliminarAfiliado;

        jcbVerAfiliados = createJCommandButton("Ver", getResizableIconFromResource("/resources/imagenes/afiliados.png"),
                "jcbVerAfiliados", new RichTooltip("Ver", "Click aqui para ver los afiliados"), buttonActHandler);

        jcbCrearAfiliado = createJCommandButton("Nuevo", getResizableIconFromResource("/resources/imagenes/add2.png"),
                "jcbCrearAfiliado", new RichTooltip("Nuevo", "Click aqui para crear afiliado"), buttonActHandler);

        jcbEditarAfiliado = createJCommandButton("Editar", getResizableIconFromResource("/resources/imagenes/editar3.png"),
                "jcbEditarAfiliado", new RichTooltip("Editar", "Click aqui para editar afiliado"), buttonActHandler);

        jcbEliminarAfiliado = createJCommandButton("Eliminar", getResizableIconFromResource("/resources/imagenes/delete.png"),
                "jcbEliminarAfiliado", new RichTooltip("Eliminar", "Click aqui para eliminar afiliado"), buttonActHandler);

        // creando la banda
        JRibbonBand jrbAfiliadosBand = new JRibbonBand("Afiliados", getResizableIconFromResource("/resources/imagenes/afiliados.png"));
        jrbAfiliadosBand.addCommandButton(jcbVerAfiliados, RibbonElementPriority.TOP);
        jrbAfiliadosBand.addCommandButton(jcbCrearAfiliado, RibbonElementPriority.MEDIUM);
        jrbAfiliadosBand.addCommandButton(jcbEditarAfiliado, RibbonElementPriority.MEDIUM);
        jrbAfiliadosBand.addCommandButton(jcbEliminarAfiliado, RibbonElementPriority.MEDIUM);

        jrbAfiliadosBand.setResizePolicies(getRibbonBandResizePolicy(jrbAfiliadosBand));

        // on dependientes band

        JCommandButton jcbVerDependientes, jcbCrearDependiente, jcbEditarDependiente, jcbEliminarDependiente;

        jcbVerDependientes = createJCommandButton("Ver", getResizableIconFromResource("/resources/imagenes/dependientes2.png"),
                "jcbVerDependientes", new RichTooltip("Ver", "Click aqui para ver los dependientes"), buttonActHandler);

        jcbCrearDependiente = createJCommandButton("Nuevo", getResizableIconFromResource("/resources/imagenes/add2.png"),
                "jcbCrearDependiente", new RichTooltip("Nuevo", "Click aqui para crear dependiente"), buttonActHandler);

        jcbEditarDependiente = createJCommandButton("Editar", getResizableIconFromResource("/resources/imagenes/editar3.png"),
                "jcbEditarDependiente", new RichTooltip("Editar", "Click aqui para editar dependiente"), buttonActHandler);

        jcbEliminarDependiente = createJCommandButton("Eliminar", getResizableIconFromResource("/resources/imagenes/delete.png"),
                "jcbEliminarDependiente", new RichTooltip("Eliminar", "Click aqui para eliminar dependiente"), buttonActHandler);

        // creando la banda
        JRibbonBand jrbDependientesBand = new JRibbonBand("Dependientes", getResizableIconFromResource("/resources/imagenes/dependientes2.png"));
        jrbDependientesBand.addCommandButton(jcbVerDependientes, RibbonElementPriority.TOP);
        jrbDependientesBand.addCommandButton(jcbCrearDependiente, RibbonElementPriority.MEDIUM);
        jrbDependientesBand.addCommandButton(jcbEditarDependiente, RibbonElementPriority.MEDIUM);
        jrbDependientesBand.addCommandButton(jcbEliminarDependiente, RibbonElementPriority.MEDIUM);

        jrbDependientesBand.setResizePolicies(getRibbonBandResizePolicy(jrbDependientesBand));


        // on planes band

        JCommandButton jcbVerPlanes, jcbCrearPlan, jcbEditarPlan, jcbEliminarPlan;

        jcbVerPlanes = createJCommandButton("Ver", getResizableIconFromResource("/resources/imagenes/paloma3.png"),
                "jcbVerPlanes", new RichTooltip("Ver", "Click aqui para ver los planes"), buttonActHandler);

        jcbCrearPlan = createJCommandButton("Nuevo", getResizableIconFromResource("/resources/imagenes/add2.png"),
                "jcbCrearPlan", new RichTooltip("Nuevo", "Click aqui para crear plan"), buttonActHandler);

        jcbEditarPlan = createJCommandButton("Editar", getResizableIconFromResource("/resources/imagenes/editar3.png"),
                "jcbEditarPlan", new RichTooltip("Editar", "Click aqui para editar plan"), buttonActHandler);

        jcbEliminarPlan = createJCommandButton("Eliminar", getResizableIconFromResource("/resources/imagenes/delete.png"),
                "jcbEliminarPlan", new RichTooltip("Eliminar", "Click aqui para eliminar plan"), buttonActHandler);

        // creando la banda
        JRibbonBand jrbPlanesBand = new JRibbonBand("Planes", getResizableIconFromResource("/resources/imagenes/plan.png"));
        jrbPlanesBand.addCommandButton(jcbVerPlanes, RibbonElementPriority.TOP);
        jrbPlanesBand.addCommandButton(jcbCrearPlan, RibbonElementPriority.MEDIUM);
        jrbPlanesBand.addCommandButton(jcbEditarPlan, RibbonElementPriority.MEDIUM);
        jrbPlanesBand.addCommandButton(jcbEliminarPlan, RibbonElementPriority.MEDIUM);

        jrbPlanesBand.setResizePolicies(getRibbonBandResizePolicy(jrbPlanesBand));

        // creando el la task
        RibbonTask rtMantenimientoTask = new RibbonTask("Mantenimiento", jrbEmpresasBand, jrbAfiliadosBand,
                jrbDependientesBand, jrbPlanesBand);

        return rtMantenimientoTask;
    }
    
    private RibbonTask getReportesTask() {

        // Listados band
        JCommandButton jcbListadoEmpresas, jcbListadoAfiliados, jcbListadoDependientes, jcbListadoPlanes;

        jcbListadoEmpresas = createJCommandButton("Empresas", getResizableIconFromResource("/resources/imagenes/empresas4.png"),
                "jcbListadoEmpresas", new RichTooltip("Listado Empresas", "Click aqui para ver reporte listado de empresas"), buttonActHandler);

        jcbListadoAfiliados = createJCommandButton("Afiliados", getResizableIconFromResource("/resources/imagenes/afiliados.png"),
                "jcbListadoAfiliados", new RichTooltip("Listado Afiliados", "Click aqui para ver reporte listado de afiliados"), buttonActHandler);

        jcbListadoDependientes = createJCommandButton("Dependientes", getResizableIconFromResource("/resources/imagenes/dependientes2.png"),
                "jcbListadoDependientes", new RichTooltip("Listado Dependientes", "Click aqui para ver reporte listado de dependientes"), buttonActHandler);

        jcbListadoPlanes = createJCommandButton("Planes", getResizableIconFromResource("/resources/imagenes/paloma3.png"),
                "jcbListadoPlanes", new RichTooltip("Listado Planes", "Click aqui para ver reporte listado de planes"), buttonActHandler);

        JRibbonBand jrbListadosBand = new JRibbonBand("Listados", getResizableIconFromResource("/resources/imagenes/listado.png"));
        jrbListadosBand.addCommandButton(jcbListadoEmpresas, RibbonElementPriority.TOP);
        jrbListadosBand.addCommandButton(jcbListadoAfiliados, RibbonElementPriority.MEDIUM);
        jrbListadosBand.addCommandButton(jcbListadoDependientes, RibbonElementPriority.MEDIUM);
        jrbListadosBand.addCommandButton(jcbListadoPlanes, RibbonElementPriority.MEDIUM);

        jrbListadosBand.setResizePolicies(getRibbonBandResizePolicy(jrbListadosBand));

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

    private RibbonTask getFacturacionTask() {
        throw new BehaviorNotImplementedException();
    }

    private RibbonTask getSettingsTask() {
        throw new BehaviorNotImplementedException();
    }

    private RibbonTask getAdministracionTask() {
        throw new BehaviorNotImplementedException();
    }

    private Component getTaskBar() {
        JPanel pnlTaskBar = new JPanel(new BorderLayout(2, 0));
        pnlTaskBar.add(new JButton(getResizableIconFromResource("/resources/imagenes/new_15x15.png")));
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
            usuarioLogeado.setText(usuario.getUsrLogin());
            //JRichTooltipPanel rtp = new JRichTooltipPanel(new RichTooltip("Login", "Bienvenido, " + login.getTxtlNombreUsuario().getText()));
            //pnlBody.add(rtp, BorderLayout.SOUTH);
            //new Thread(new ToolTipShower(rtp)).start();
            logInOutButton.setName("jcbLogOut");
            logInOutButton.setIcon(getResizableIconFromResource("/resources/imagenes/th_logout.png"));
            logInOutButton.setText("Log Out");
            setJRibbonComponentsEnabled(true);
        }
    }

    private void doLogout(JCommandButton logInOutButton) {
        usuario = null;
        usuarioLogeado.setText("Usuario no logeado");
        logInOutButton.setName("jcbLogIn");
        logInOutButton.setIcon(getResizableIconFromResource("/resources/imagenes/login.png"));
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
            if (!buttonName.equalsIgnoreCase("jcbLogIn") && !buttonName.equalsIgnoreCase("jcbLogOut") &&
                    !buttonName.equalsIgnoreCase("jcbSalir")) {
                if (!isUsuarioLogged(true)) {
                    return;
                }
            }
            if (buttonName.equalsIgnoreCase("jcbLogIn")) {
                doLogin(buttonClicked);
            } else if (buttonName.equalsIgnoreCase("jcbLogOut")) {
                int op = JOptionPane.showConfirmDialog(PsychSysDesktop.this, "Confirma desea cerrar sesion?", "Syscafil - Log Out",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (op == JOptionPane.OK_OPTION) {
                    doLogout(buttonClicked);
                }
            } else if (buttonName.equalsIgnoreCase("jcbSalir")) {
                doExit(buttonClicked);
            } else if (buttonName.equalsIgnoreCase("jcbVerEmpresas")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbCrearEmpresa")) {
                //RegistroEmpresas regEmpresas = new RegistroEmpresas();
                //regEmpresas.setLocationRelativeTo(PsychSysDesktop.this);
                //regEmpresas.setVisible(true);
            } else if (buttonName.equalsIgnoreCase("jcbEditarEmpresa")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbEliminarEmpresa")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbVerAfiliados")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbCrearAfiliado")) {
                //RegistroAfiliados regAfiliados = new RegistroAfiliados();
                //regAfiliados.setLocationRelativeTo(PsychSysDesktop.this);
                //regAfiliados.setVisible(true);
            } else if (buttonName.equalsIgnoreCase("jcbEditarAfiliado")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbEliminarAfiliado")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbVerDependientes")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbCrearDependiente")) {
                //RegistroDependientes regDependientes = new RegistroDependientes();
                //regDependientes.setLocationRelativeTo(PsychSysDesktop.this);
                //regDependientes.setVisible(true);
            } else if (buttonName.equalsIgnoreCase("jcbEditarDependiente")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbEliminarDependiente")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbVerPlanes")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbCrearPlan")) {
                //RegistroPlanes regPlanes = new RegistroPlanes();
                //regPlanes.setLocationRelativeTo(PsychSysDesktop.this);
                //regPlanes.setVisible(true);
            } else if (buttonName.equalsIgnoreCase("jcbEditarPlan")) {
                throwNoImplMsj();
            } else if (buttonName.equalsIgnoreCase("jcbEliminarPlan")) {
                throwNoImplMsj();
            }
        }
    }
}
