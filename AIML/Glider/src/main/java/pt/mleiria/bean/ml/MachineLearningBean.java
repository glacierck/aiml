/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.bean.ml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import pt.mleiria.EnvSettings;


/**
 *
 * @author manuel
 */
@ManagedBean(name = "mlBean")
@SessionScoped
public class MachineLearningBean {

    protected final static Logger LOG = Logger.getLogger(MachineLearningBean.class.getName());

    private Part dataFile;  
    private final List<String[]> dataList = new ArrayList<String[]>();
    protected String fname;
    private boolean showFiles;
    

    public MachineLearningBean() {
        showFiles = false;
    }
    
    
    /**
     * Upload file to server
     * @return
     * @throws IOException 
     */
    public String upload() throws IOException {
        this.fname = getFilename(getDataFile());
        final InputStream inputStream = getDataFile().getInputStream();
        final FileOutputStream outputStream = new FileOutputStream(EnvSettings.DATA_DIR + fname);

        byte[] buffer = new byte[4096];
        int bytesRead = 0;
        while (true) {
            bytesRead = inputStream.read(buffer);
            if (bytesRead > 0) {
                outputStream.write(buffer, 0, bytesRead);
            } else {
                break;
            }
        }
        outputStream.close();
        inputStream.close();
        LOG.log(Level.INFO, "[+] Ficheiro {0} carregado com sucesso!", fname);
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Ficheiro carregado com sucesso!",
                        ""));
        return "";
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    public List<String> getFilesInDir() {
        final File folder = new File(EnvSettings.DATA_DIR);
        final List<String> files = new ArrayList<String>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isFile()){
                files.add(fileEntry.getName());
            }
        }
        return files;
    }

    public String selectFile(){
        showFiles = true;
        return "";
    }
    

    /**
     * @return the dataFile
     */
    public Part getDataFile() {
        return dataFile;
    }

    /**
     * @param dataFile
     */
    public void setDataFile(Part dataFile) {
        this.dataFile = dataFile;
    }

    public boolean isShowFiles() {
        return showFiles;
    }

    
    
}
