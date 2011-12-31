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

    private String psychSysConfFilePath = "META-INF\\psychsys_conf.xml";

    public String getPsychSysConfFilePath() {
        return psychSysConfFilePath;
    }

    public void setPsychSysConfFilePath(String psychSysConfFilePath) {
        this.psychSysConfFilePath = psychSysConfFilePath;
    }
}
