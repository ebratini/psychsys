/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.salvador_dali.psychsys.business;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class PsychSysConfManager {

    private String syscafilConfFilePath = "META-INF\\psychsys_conf.xml";

    public String getSyscafilConfFilePath() {
        return syscafilConfFilePath;
    }

    public void setSyscafilConfFilePath(String syscafilConfFilePath) {
        this.syscafilConfFilePath = syscafilConfFilePath;
    }
}
