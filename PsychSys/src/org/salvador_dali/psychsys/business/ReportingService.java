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

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class ReportingService {

    private String dbDriver;
    private String urlHost;
    private String user;
    private String pass;
    private Connection conn;

    private ReportingService() {
    }

    public ReportingService(Connection conn) {
        this.conn = conn;
    }

    public ReportingService(String dbDriver, String urlHost, String user, String pass) {
        this.dbDriver = dbDriver;
        this.urlHost = urlHost;
        this.user = user;
        this.pass = pass;
        initConn();
    }
    
    private void initConn() {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(urlHost, user, pass);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public JPanel runReport(String reportFile, Map parameters) {
        //InputStream is = this.getClass().getClassLoader().getResourceAsStream(reportFile);
        try {
            JasperPrint jp = JasperFillManager.fillReport(reportFile, parameters, conn);
            JRViewer jvReport = new JRViewer(jp);
            return jvReport;
        } catch (JRException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
