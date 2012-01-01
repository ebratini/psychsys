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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public final class SecurityService {

    private static SecurityService securityserviceInstance;
    public enum EncriptionMethod {
        SHA
    }

    private SecurityService() {
    }

    public synchronized String encrypt(String plaintext, EncriptionMethod encMethod) {
        MessageDigest md = null;
        
        String strEncMethod = null;
        if (encMethod.equals(EncriptionMethod.SHA)) {
            strEncMethod = "SHA";
        }
        
        try {
            md = MessageDigest.getInstance(strEncMethod);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SecurityService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            md.update(plaintext.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SecurityService.class.getName()).log(Level.SEVERE, null, ex);
        }

        byte raw[] = md.digest();
        String hash = (new BASE64Encoder()).encode(raw);
        return hash;
    }

    public static synchronized SecurityService getInstance() {
        if (securityserviceInstance == null) {
            securityserviceInstance = new SecurityService();
        }
        return securityserviceInstance;
    }
}
