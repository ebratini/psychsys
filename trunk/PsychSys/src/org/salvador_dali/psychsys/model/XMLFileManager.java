/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.salvador_dali.psychsys.model;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class XMLFileManager extends FileManager {

    public static Element getElemento(String rutaArchivoXML, String xpathElemento) {
        SAXBuilder xmlDocBuilder = new SAXBuilder();
        Element xmlElement = null;

        try {
            Document doc = xmlDocBuilder.build(new File(rutaArchivoXML));
            xmlElement = (Element) XPath.selectSingleNode(doc, xpathElemento);
        } catch (JDOMException ex) {
            Logger.getLogger(XMLFileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLFileManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return xmlElement;
    }
}
