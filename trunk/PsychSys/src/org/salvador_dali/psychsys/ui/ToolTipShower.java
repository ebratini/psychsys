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

import java.awt.Container;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.pushingpixels.flamingo.internal.ui.common.JRichTooltipPanel;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class ToolTipShower implements Runnable {

    private JRichTooltipPanel rtp;
    private long time = 2000;

    public ToolTipShower() {
    }

    public ToolTipShower(JRichTooltipPanel rtp) {
        this.rtp = rtp;
    }

    public ToolTipShower(JRichTooltipPanel rtp, long time) {
        this.rtp = rtp;
        this.time = time;
    }

    public JRichTooltipPanel getRtp() {
        return rtp;
    }

    public void setRtp(JRichTooltipPanel rtp) {
        this.rtp = rtp;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public void run() {
        try {
            rtp.setVisible(true);
            Thread.sleep(time);
            //this.rtp.setVisible(false);
            Container parent = rtp.getParent();
            parent.remove(rtp);
            parent.validate();
            parent.repaint();
        } catch (InterruptedException ex) {
            Logger.getLogger(ToolTipShower.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

