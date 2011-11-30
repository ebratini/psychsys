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

import java.io.File;
import org.salvador_dali.psychsys.model.FileManager;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public abstract class AppLogger {

    private String baseAppURL;
    private String toFilePath;
    private File toFile;

    public AppLogger() {
    }

    public AppLogger(File toFile) {
        this.toFile = toFile;
        this.toFilePath = toFile.getAbsolutePath();
    }

    public AppLogger(String toFilePath) {
        this.toFilePath = toFilePath;
        this.toFile = new File(toFilePath);
    }

    public String getBaseAppURL() {
        return baseAppURL;
    }

    public void setBaseAppURL(String baseAppURL) {
        this.baseAppURL = baseAppURL;
    }
    
    public File getToFile() {
        return toFile;
    }

    public void setToFile(File toFile) {
        this.toFile = toFile;
        this.toFilePath = toFile.getAbsolutePath();
    }

    public String getToFilePath() {
        return toFilePath;
    }

    public void setToFilePath(String toFilePath) {
        this.toFilePath = toFilePath;
        this.toFile = new File(toFilePath);
    }
    
    public void log(String mensaje, boolean append) {
        log(mensaje, toFile, append);
    }

    public static void log(String mensaje, File toFile, boolean append) {
        FileManager.writeToFile(new StringBuilder(mensaje), toFile, append);
    }

    public static void log(String mensaje, String toFilePath, boolean append) {
        log(mensaje, new File(toFilePath), append);
    }
}
