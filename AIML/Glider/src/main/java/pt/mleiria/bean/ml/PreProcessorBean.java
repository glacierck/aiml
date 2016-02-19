/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.bean.ml;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pt.mleiria.EnvSettings;
import pt.mleiria.ml.vo.CsvTable;
import pt.mleiria.ml.vo.HeaderObjectMapper;
import pt.mleiria.ml.vo.TruckBusinessVO;
import pt.mleiria.vo.UtilsVO;

/**
 *
 * @author manuel
 */
@ManagedBean(name = "preProcessorBean")
@SessionScoped
public class PreProcessorBean extends MachineLearningBean{

    private List<CsvTable> dataList;

    protected HeaderObjectMapper hom;
    protected String fileName;

    public PreProcessorBean() {
    }

    public void loadFile(final String fileName) throws IOException {
        this.fileName = fileName;
        if (fileName.equals("TruckBusinessVO.json")) {
            ObjectMapper mapper = new ObjectMapper();
            dataList = mapper.readValue(new File(EnvSettings.DATA_DIR + fileName),
                    new TypeReference<List<TruckBusinessVO>>() {
                    });

            for (CsvTable tb : dataList) {
                LOG.info("Profit:" + ((TruckBusinessVO) tb).getProfit());
            }
        }
        if (fileName.equals("ex1data1.txt")) {
            hom = UtilsVO.loadFileToObjMapper(EnvSettings.DATA_DIR + fileName, ",", true, true);
            LOG.info("HOM loaded");
            /*
            for (GenericObj go : hom.getLst()) {
                Object[] objl = go.getValues();
                LOG.log(Level.INFO, "{0};{1}", new Object[]{Double.parseDouble(objl[0].toString()), Double.parseDouble(objl[1].toString())});
                
            }
            */
        }
    }

}
