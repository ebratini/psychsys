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

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class LookAndFeelSelector {
    public enum LAF {
        NIMBUS, METAL, CDE_MOTIF, WINDOWS, CLASIC_WINDOWS
    }

    public static void setLookAndFeel(LAF laf) {
        String strLaf = "Windows";
        switch (laf) {
            case NIMBUS:
                strLaf = "Nimbus";
                break;
            case CDE_MOTIF:
                strLaf = "CDE/Motif";
                break;
            case CLASIC_WINDOWS:
                strLaf = "Windows Classic";
                break;
            case METAL:
                strLaf = "Metal";
                break;
            case WINDOWS:
                strLaf = "Windows";
                break;
        }

        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (strLaf.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
            e.printStackTrace();
        }
    }

    public static StringBuilder getAvailableLAF() {
        // imprimiendo available laf
        StringBuilder sbAvailableLaf = new StringBuilder();
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            sbAvailableLaf.append(info.getName()).append("\n");
        }

        return sbAvailableLaf;
    }

    public static void printAvailableLAF() {
        System.out.println(getAvailableLAF());
    }
}
