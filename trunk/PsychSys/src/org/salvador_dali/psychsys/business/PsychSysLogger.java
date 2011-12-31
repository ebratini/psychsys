/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.salvador_dali.psychsys.business;

import org.salvador_dali.psychsys.business.jpa_controllers.BitacoraJpaDao;
import org.salvador_dali.psychsys.model.FileManager;
import org.salvador_dali.psychsys.model.XMLFileManager;
import org.salvador_dali.psychsys.model.entities.Bitacora;
import org.salvador_dali.psychsys.model.entities.Usuario;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import psychsys.PsychSys;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class PsychSysLogger extends AppLogger {

    private BitacoraJpaDao jpaBitDao = new BitacoraJpaDao();
    private JpaUsuarioDao jpUsrDao = new JpaUsuarioDao();

    public PsychSysLogger() {
        createAppFileDir();
        setBaseAppURL(XMLFileManager.getElemento(PsychSys.scm.getSyscafilConfFilePath(), "PsychSys").getAttribute("BaseAppURL").getValue());
        setToFilePath(String.format("%1$s%2$sapp_files%2$sbitlogger.psychsys", getBaseAppURL(), System.getProperty("path.separator").toString()));
        setToFile(new File(getToFilePath()));
    }

    private void createAppFileDir() {
        File appFileDir = new File(String.format("%1$s%2$sapp_files", getBaseAppURL(), System.getProperty("path.separator").toString()));
        if (appFileDir.exists() != true) {
            appFileDir.mkdir();
        }
    }

    public void logBitacora(String... bitFields) {
        if (bitFields.length > 0) {
            String mensaje = String.format("%s|%s|%s|%s|%s%s", bitFields[0], bitFields[1], bitFields[2], bitFields[3], bitFields[4], System.getProperty("line.separator").toString());
            log(mensaje, true);
        }
    }

    public void logBitacora(Date fecha, Integer userId, String fuente, String categoria, String descripcion) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);

        logBitacora(new String[]{String.format("%1$tD %1$tr", cal), userId.toString(), fuente, categoria, descripcion});
    }

    public void flushToDataBase() {
        StringBuilder sbBits = FileManager.loadFile(getToFile());
        String[] bits = sbBits.toString().split(System.getProperty("line.separator").toString());

        for (String bitLine : bits) {
            try {
                String[] bitFields = bitLine.split("|");
                Bitacora bit = new Bitacora(null, DateFormat.getDateInstance().parse(bitFields[0]), bitFields[2], bitFields[3], bitFields[4]);
                Usuario usuario = jpUsrDao.findById(Integer.parseInt(bitFields[1]));

                // TODO: revisar / test lo de relaciones bidereccionales (si se agrega la bit a la coleccion del usuario)
                bit.setUsuario(usuario);
                usuario.getBitacoraCollection().add(bit);
                // ...
                jpaBitDao.persist(bit);
            } catch (ParseException ex) {
                Logger.getLogger(PsychSysLogger.class.getName()).log(Level.SEVERE, null, ex);
            }/* finally {
                um.close();
                bm.close();
            }*/ // TODO: probar el close de los manager: ?que sucede?
        }
    }
}
