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
    private String dbUser;
    private String password;
    private Connection dbConnection;

    public ReportingService() {
    }

    public ReportingService(Connection conn) {
        this.dbConnection = conn;
    }

    public ReportingService(String dbDriver, String urlHost, String user, String pass) {
        this.dbDriver = dbDriver;
        this.urlHost = urlHost;
        this.dbUser = user;
        this.password = pass;
        initConn();
    }

    public Connection getDbConnection() {
        return dbConnection;
    }

    public void setDbConnection(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrlHost() {
        return urlHost;
    }

    public void setUrlHost(String urlHost) {
        this.urlHost = urlHost;
    }
    
    private void initConn() {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        try {
            dbConnection = DriverManager.getConnection(urlHost, dbUser, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public JPanel runReport(String reportFile, Map parameters) {
        try {
            JasperPrint jp = JasperFillManager.fillReport(reportFile, parameters, dbConnection);
            JRViewer jvReport = new JRViewer(jp);
            if (jp.getPages().size() < 1) {
                return null;
            }
            return jvReport;
        } catch (JRException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
