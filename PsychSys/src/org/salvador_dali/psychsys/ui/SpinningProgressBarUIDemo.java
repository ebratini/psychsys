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
import com.bric.plaf.SpinningProgressBarUI;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JApplet;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import com.bric.util.JVM;
import javax.swing.BorderFactory;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class SpinningProgressBarUIDemo extends JApplet {

    private static final long serialVersionUID = 1L;
    public static final boolean isMac = System.getProperty("os.name").toLowerCase().indexOf("mac") != -1;

    public static BufferedImage createBlurbGraphic(Dimension preferredSize) {
        JProgressBar b1 = new JProgressBar();
        JProgressBar b2 = new JProgressBar();
        b1.putClientProperty("forcedFraction", new Float(.25f));
        b2.putClientProperty("forcedFraction", new Float(.25f));
        SpinningProgressBarUI ui1 = new BasicSpinningProgressBarUI();
        SpinningProgressBarUI ui2 = new AquaSpinningProgressBarUI();
        b1.setUI(ui1);
        b2.setUI(ui2);

        int size = Math.min(preferredSize.height * 7 / 10, (preferredSize.width - 5) / 2);

        b1.setSize(new Dimension(size, size));
        b2.setSize(new Dimension(size, size));

        BufferedImage image = new BufferedImage(b1.getWidth() + 5 + b2.getWidth(),
                Math.max(b2.getHeight(), b1.getHeight()), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        int dx = 0;
        int dy = image.getHeight() / 2 - b1.getHeight() / 2;
        g.translate(dx, dy);
        b1.paint(g);
        g.translate(-dx, -dy);
        dx = image.getWidth() - b2.getWidth();
        dy = image.getHeight() / 2 - b2.getHeight() / 2;
        g.translate(dx, dy);
        b2.paint(g);
        g.dispose();
        return image;
    }

    public static void main(String[] args) {
        SpinningProgressBarUIDemo d = new SpinningProgressBarUIDemo();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(d);
        frame.pack();
        frame.setVisible(true);
    }
    /** The small spinning arrows. */
    JProgressBar bar1;
    /** The small aqua UI. */
    JProgressBar bar2;
    /** The optional real aqua UI */
    JProgressBar bar3;
    /** The large spinning arrows. */
    JProgressBar bar4;
    /** The large aqua UI. */
    JProgressBar bar5;
    ZoomPanel imagePanel;
    /** Based on a comment by Matt Nathan, this toggles the use
     * of RenderingHints.VALUE_STROKE_PURE.
     */
    JCheckBox strokeControl = new JCheckBox("Use RenderingHints for STROKE_PURE", true);
    /** This slows each spinning UI down to help in bug testing. */
    JCheckBox slowMode = new JCheckBox("Slow Mode", false);

    class ZoomPanel extends JPanel {

        private static final long serialVersionUID = 1L;
        BufferedImage bi;

        public ZoomPanel() {
            int w = 0;
            int h = 0;

            h = Math.max(bar1.getPreferredSize().height, bar2.getPreferredSize().height);
            w = bar1.getPreferredSize().width + 5 + bar2.getPreferredSize().width;
            if (isMac) {
                w += bar3.getPreferredSize().width + 5;
            }

            bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            setPreferredSize(new Dimension(bi.getWidth() * 4, bi.getHeight() * 4));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) bi.createGraphics();
            g2.setComposite(AlphaComposite.Clear);
            g2.fillRect(0, 0, bi.getWidth(), bi.getHeight());
            g2.setComposite(AlphaComposite.SrcOver);
            bar1.paint(g2);
            g2.translate(bar1.getPreferredSize().width + 5, 0);
            bar2.paint(g2);
            if (isMac) {
                g2.translate(bar2.getPreferredSize().width + 5, 0);
                bar3.paint(g2);
            }

            ((Graphics2D) g).scale(4, 4);
            g.drawImage(bi, 0, 0, null);

        }
    };

    public SpinningProgressBarUIDemo() {
        super();
        if (JVM.isMac) {
            try {
                System.setProperty("apple.awt.graphics.UseQuartz", "false");
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }

        bar1 = new JProgressBar();
        bar2 = new JProgressBar();
        bar3 = new JProgressBar();
        bar4 = new JProgressBar();
        bar5 = new JProgressBar();

        bar1.setUI(new BasicSpinningProgressBarUI());
        bar2.setUI(new AquaSpinningProgressBarUI());
        bar4.setUI(new BasicSpinningProgressBarUI());
        bar5.setUI(new AquaSpinningProgressBarUI());
        bar3.putClientProperty("JProgressBar.style", "circular");
        bar3.setIndeterminate(true);
        getContentPane().setBackground(Color.white);
        getContentPane().setLayout(new GridBagLayout());

        slowMode.setOpaque(false);
        strokeControl.setOpaque(false);

        imagePanel = new ZoomPanel();
        imagePanel.setBackground(Color.white);
        imagePanel.setBorder(BorderFactory.createEtchedBorder());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        getContentPane().add(strokeControl, c);
        c.gridy++;
        getContentPane().add(slowMode, c);
        c.gridy++;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        getContentPane().add(bar1, c);
        c.gridx++;
        getContentPane().add(bar2, c);
        if (isMac) {
            c.gridx++;
            getContentPane().add(bar3, c);
        }
        c.gridy++;
        c.gridx = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        getContentPane().add(imagePanel, c);
        c.gridy++;
        getContentPane().add(bar4, c);
        c.gridy++;
        getContentPane().add(bar5, c);

        bar4.setPreferredSize(new Dimension(140, 140));
        bar5.setPreferredSize(new Dimension(140, 140));

        Timer timer = new Timer(20, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                imagePanel.repaint();
            }
        });
        timer.start();

        strokeControl.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                bar1.putClientProperty("useStrokeControl", new Boolean(strokeControl.isSelected()));
                bar2.putClientProperty("useStrokeControl", new Boolean(strokeControl.isSelected()));
                bar3.putClientProperty("useStrokeControl", new Boolean(strokeControl.isSelected()));
                bar4.putClientProperty("useStrokeControl", new Boolean(strokeControl.isSelected()));
                bar5.putClientProperty("useStrokeControl", new Boolean(strokeControl.isSelected()));
            }
        });
        slowMode.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int m = 1;
                if (slowMode.isSelected()) {
                    m = 4;
                }
                bar1.putClientProperty("period", new Long(BasicSpinningProgressBarUI.DEFAULT_PERIOD.longValue() * m));
                bar2.putClientProperty("period", new Long(AquaSpinningProgressBarUI.DEFAULT_PERIOD.longValue() * m));
                bar4.putClientProperty("period", new Long(BasicSpinningProgressBarUI.DEFAULT_PERIOD.longValue() * m));
                bar5.putClientProperty("period", new Long(AquaSpinningProgressBarUI.DEFAULT_PERIOD.longValue() * m));

            }
        });
    }
}
