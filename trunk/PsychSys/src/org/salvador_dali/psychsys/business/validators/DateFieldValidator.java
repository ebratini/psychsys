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
package org.salvador_dali.psychsys.business.validators;

import java.util.regex.Pattern;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class DateFieldValidator extends DatePhoneFieldValidator {

    public DateFieldValidator() {
        this("\\d\\d-\\d\\d-\\d\\d\\d\\d");
    }

    public DateFieldValidator(String patternToMatch) {
        super(patternToMatch);
    }

    @Override
    public boolean validate(String textToValidate) {
        boolean valid = false;
        String[] dateSplitted = textToValidate.split("-");

        if (!textToValidate.isEmpty()) {
            int dia = Integer.parseInt(dateSplitted[0]);
            int mes = Integer.parseInt(dateSplitted[1]);
            int anio = Integer.parseInt(dateSplitted[2]);
            if ((anio >= 1) && (mes >= 1 && mes <= 12) && (dia >= 1 && dia <= 31)) {
                if (Pattern.matches(getPatternToMatch(), textToValidate)) {
                    valid = true;
                } else {
                    valid = false;
                }
            }
        }
        return valid;
    }

    @Override
    public String getValidationMessage() {
        return "Fecha no valida.";
    }
}
