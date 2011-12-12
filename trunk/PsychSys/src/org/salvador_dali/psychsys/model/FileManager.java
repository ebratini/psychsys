/*
 *  The MIT License
 *
 *  Copyright 2011 Edwin Bratini <edwin.bratini@gmail.com>.
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
package org.salvador_dali.psychsys.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import org.salvador_dali.psychsys.business.BehaviorNotImplementedException;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class FileManager {

    public static void makeFolder(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    // This method is used to load the file into a StringBuilder object for later use.
    /**
     *
     * Method used to load the file into a StringBuilder object for later use.
     *
     * @param inputFile a File object from wich to read.
     * @return a StringBuilder object with the all the lines of the file argument read.
     */
    public static StringBuilder loadFile(File inputFile) {
        StringBuilder sbInputFile = new StringBuilder();
        String recLine = "";

        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(inputFile);
            br = new BufferedReader(fr);

            while ((recLine = br.readLine()) != null) {
                sbInputFile.append(String.format("%s%s", recLine, System.getProperty("line.separator")));
            }
        } catch (IOException ioe) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ioe);
        } finally {
            try {
                br.close();
            } catch (IOException ioe) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ioe);
            }
        }

        return sbInputFile;
    }

    // This method write the files into physical directory structure.
    /**
     *
     * Method used to write the files into physical directory structure.
     *
     * @param content a StringBuilder object with the future content of the file
     * @param file a File object to which the content is to be intended to be written.
     */
    public static void writeToFile(StringBuilder content, File file, boolean append) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            if (!append) {
                bw.write(content.toString());
            } else {
                bw.append(content);
            }
        } catch (IOException ioe) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ioe);
        } finally {
            try {
                bw.flush();
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void copy(String sourcePath, String destPath) {
        FileReader in = null;
        FileWriter out = null;
        try {
            in = new FileReader(new File(sourcePath));
            out = new FileWriter(new File(destPath));
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void extractJarFile(JarFile jarFile, String destPath) {
        throw new BehaviorNotImplementedException();
    }

    public static File extractFileFromJar(JarFile jarFile, String destPath, String entryName) {
        ZipEntry entry = jarFile.getEntry(entryName);
        File entryFile = new File(destPath, entry.getName());

        new File(entryFile.getParent()).mkdirs();

        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(jarFile.getInputStream(entry));
            out = new BufferedOutputStream(new FileOutputStream(entryFile));

            byte[] buffer = new byte[2048];
            for (;;) {
                int nBytes = in.read(buffer);
                if (nBytes <= 0) {
                    break;
                }
                out.write(buffer, 0, nBytes);
            }
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return entryFile;
    }
}
