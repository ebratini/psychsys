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
package org.salvador_dali.psychsys.ui;

import com.bric.plaf.AquaSpinningProgressBarUI;
import com.bric.plaf.BasicSpinningProgressBarUI;
import java.util.Timer;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class ProgressCircle {

    private JLabel progressCircleLabel;
    private JComponent progressComp;
    private JProgressBar progressBar = new JProgressBar();
    private Timer progressTimer = new Timer();
    private javax.swing.Timer timer;
    //private AquaSpinningProgressBarUI aspb = new AquaSpinningProgressBarUI();

    public ProgressCircle() {
    }

    public ProgressCircle(JLabel progressCircleLabel) {
        this.progressCircleLabel = progressCircleLabel;
    }
    
    public ProgressCircle(JComponent progressComp) {
        this.progressComp = progressComp;
    }

    public JLabel getProgressCircleImage() {
        return progressCircleLabel;
    }

    public void setProgressCircleImage(JLabel progressCircleLabel) {
        this.progressCircleLabel = progressCircleLabel;
    }
    
    public void start() {
        progressBar.setUI(new AquaSpinningProgressBarUI());
        progressBar.putClientProperty("period", new Long(BasicSpinningProgressBarUI.DEFAULT_PERIOD.longValue()*4));
        
        //progressCircleLabel.add(progressBar);
        progressComp.add(progressBar);
        /*progressTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                progressComp.repaint();
            }
        }, 20);*/
        /*timer = new javax.swing.Timer(2, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                progressComp.repaint();
            }
        });
        timer.start();**/
        //progressCircleLabel.setVisible(true);
        progressComp.setVisible(true);
        //AquaSpinningProgressBarUI aqpb = new AquaSpinningProgressBarUI();
        //aspb.installUI(progressCircleLabel);
    }
    
    public void stop() {
        //progressTimer.cancel();
        /*timer.stop();*/
        //progressCircleLabel.setVisible(false);
        progressComp.setVisible(false);
        //aspb.uninstallUI(progressCircleLabel);
    }
}
