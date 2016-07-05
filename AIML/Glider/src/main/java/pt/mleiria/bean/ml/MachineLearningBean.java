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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;
import pt.mleiria.EnvSettings;
import pt.mleiria.LogTypes;
import pt.mleiria.ml.core.GenericDataSet;

/**
 *
 * @author manuel
 */
@ManagedBean(name = "mlBean")
@SessionScoped
public class MachineLearningBean {

    protected static final Logger gliderLog = Logger.getLogger(LogTypes.GLIDER_LOG);

    protected GenericDataSet ds;
    private Part dataFile;
    protected String fname;
    private static boolean showFiles;
    private static boolean showPreProcessChart;
    private static boolean dataLoaded;

    public MachineLearningBean() {
        showFiles = false;
        showPreProcessChart = false;
        dataLoaded = false;
    }
    /**
     * Upload file to server
     *
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
        gliderLog.info("[+] Ficheiro " + fname + " carregado com sucesso!");
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
            if (fileEntry.isFile()) {
                files.add(fileEntry.getName());
            }
        }
        return files;
    }

    /**
     * Button to load the file list
     *
     * @return
     */
    public String selectFile() {
        showFiles = true;
        showPreProcessChart = false;
        ds = null;
        dataLoaded = false;
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

    /**
     *
     * @return
     */
    public boolean isShowFiles() {
        return showFiles;
    }

    /**
     *
     * @param sf
     */
    public void setShowFiles(boolean sf) {
        showFiles = sf;
    }

    /**
     *
     * @param sppc
     */
    public void setShowPreProcessChart(boolean sppc) {
        showPreProcessChart = sppc;
    }

    /**
     *
     * @return
     */
    public boolean isShowPreProcessChart() {
        return showPreProcessChart;
    }
    /**
     * 
     * @return 
     */
    public boolean isDataLoaded() {
        return dataLoaded;
    }
    /**
     * 
     * @param dataLoaded 
     */
    public void setDataLoaded(boolean dataLoaded) {
        MachineLearningBean.dataLoaded = dataLoaded;
    }

    public String getSessionId(){
        String id = ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getId();
        return id;
    }
    
}
