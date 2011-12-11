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

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageUtil {

    public static void main(String[] args) {
        args = new String[]{
            "C:\\Users\\Edwin Bratini\\Documents\\NetBeansProjects\\PsychSys\\src\\resources\\images\\import_data.jpg",
            "C:\\Users\\Edwin Bratini\\Documents\\NetBeansProjects\\PsychSys\\src\\resources\\images\\import_data.png"
        };
        BufferedImage bimImageTransparented = makeColorTransparent(args[0], Color.white);
        saveImage(bimImageTransparented, args[1]);
    }

    public static BufferedImage makeColorTransparent(String ref, Color color) {
        BufferedImage image = loadImage(ref);
        BufferedImage dimg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dimg.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(image, null, 0, 0);
        g.dispose();
        for (int i = 0; i < dimg.getHeight(); i++) {
            for (int j = 0; j < dimg.getWidth(); j++) {
                if (dimg.getRGB(j, i) == color.getRGB()) {
                    dimg.setRGB(j, i, 0x8F1C1C);
                }
            }
        }
        return dimg;
    }

    public static BufferedImage loadImage(String ref) {
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(new File(ref));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bimg;
    }
    
    public static void loadAndDisplayImage(String imagePath, JFrame frame) {
        // Load the img
        BufferedImage loadImg = ImageUtil.loadImage(imagePath);
        frame.setBounds(0, 0, loadImg.getWidth(), loadImg.getHeight());
        // Set the panel visible and add it to the frame
        frame.setVisible(true);
        // Get the surfaces Graphics object
        Graphics2D g = (Graphics2D) frame.getRootPane().getGraphics();
        // Now draw the image
        g.drawImage(loadImg, null, 0, 0);
    }
    
    public static void loadAndDisplayImage(String imagePath, JPanel panel) {
        // Load the img
        BufferedImage loadImg = ImageUtil.loadImage(imagePath);
        
        panel.setBounds(panel.getX(), panel.getY(), loadImg.getWidth(), loadImg.getHeight());
        // Set the panel visible and add it to the frame
        //panel.setVisible(true);
        // Get the surfaces Graphics object
        Graphics2D g = (Graphics2D) panel.getRootPane().getGraphics();
        // Now draw the image
        g.drawImage(loadImg, null, 0, 0);
    }

    /**
     * Saves a BufferedImage to the given file, pathname must not have any
     * periods "." in it except for the one before the format, i.e. C:/images/fooimage.png
     * @param img
     * @param saveFile
     */
    public static void saveImage(BufferedImage img, String ref) {
        try {
            String format = (ref.endsWith(".png")) ? "png" : "jpg";
            ImageIO.write(img, format, new File(ref));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}