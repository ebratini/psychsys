/*
 *  The MIT License
 * 
 *  Copyright 2011 Edwin Bratini.
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
package org.salvador_dali.psychsys.business;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public abstract class FormFieldValidator {

    private static void turnValidationMarker(JLabel outLabel, boolean onOff) {
        if (outLabel != null) {
            if (onOff) {
                outLabel.setText("*");
                outLabel.setForeground(Color.red);
                outLabel.setVisible(true);
            } else {
                outLabel.setText("");
            }
        }
    }

    public static boolean performValidation(FieldValidator[] fieldValidators, Component componente, JLabel validationMarker) {
        boolean valid = true;
        for (FieldValidator fv : fieldValidators) {
            valid &= performValidation(fv, componente, validationMarker);
        }

        return valid;
    }

    public static boolean performValidation(FieldValidator fieldValidator, Component componente, JLabel validationMarker) {
        boolean valid = true;
        String strToValidate = "";

        if (componente instanceof JTextComponent) {
            strToValidate = ((JTextComponent) componente).getText();
        } else if (componente instanceof JComboBox) {
            strToValidate = ((JComboBox) componente).getSelectedItem().toString();
        }

        if (!fieldValidator.validate(strToValidate)) {
            valid = false;
            validationMarker.setToolTipText(validationMarker.getToolTipText() == null ? fieldValidator.getValidationMessage()
                    : validationMarker.getToolTipText() + "; " + fieldValidator.getValidationMessage());
            turnValidationMarker(validationMarker, true);
        } else {
            turnValidationMarker(validationMarker, false);
        }

        return valid;
    }

    public static boolean verifyFormFields(HashMap<JLabel, FieldValidator[]> campos) {
        boolean validFields = true;
        boolean[] bFields = new boolean[campos.size()];

        int i = 0;
        for (Map.Entry entry : campos.entrySet()) {
            JLabel label = (JLabel) entry.getKey();
            Component comp = label.getLabelFor();
            bFields[i] = FormFieldValidator.performValidation(((FieldValidator[]) entry.getValue()), comp, label);
            i++;
        }

        for (boolean b : bFields) {
            validFields &= b;
        }

        return validFields;
    }
}
