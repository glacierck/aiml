/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.bean.ml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import pt.mleiria.machinelearning.matrixAlgebra.Matrix;
import pt.mleiria.machinelearning.preprocess.DataSet2Matrix;
import pt.mleiria.ml.core.Feature;
import pt.mleiria.preprocess.CurrentDataStats;

/**
 *
 * @author manuel
 */
@ManagedBean
@SessionScoped
public class CurrentDataBean extends PreProcessorBean {

    private String selFeatureName;
    private String selFeatureNameLessY;
    private CurrentDataStats cds;
    private LineChartModel lineModel1;
    private double yAxmin;
    private double yAxmax;

    /**
     * Creates a new instance of CurrentDataBean
     */
    public CurrentDataBean() {
    }

    @PostConstruct
    public void init() {

    }

    @Override
    public void loadFile(String fileName) throws IOException {
        super.loadFile(fileName);
        setShowFiles(false);
        if (null != ds) {
            setShowPreProcessChart(true);
            createLineModel1();
        }

    }

    @Override
    public void setShowPreProcessChart(boolean sppc) {
        super.setShowPreProcessChart(sppc); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setShowFiles(boolean showFiles) {
        super.setShowFiles(showFiles);
    }

    /**
     *
     * @return
     */
    public String[] getFeatureNames() {
        if (null != ds) {
            return ds.getFeatureNames();
        }
        return null;
    }

    /**
     *
     * @return
     */
    public String[] getFeatureNamesLessY() {
        if (null != ds) {
            final int N = ds.getFeatureNames().length;
            String[] featureNamesLessY = new String[N - 1];
            System.arraycopy(ds.getFeatureNames(), 0, featureNamesLessY, 0, N - 1);
            return featureNamesLessY;
        }
        return null;
    }

    /**
     *
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     *
     * @return
     */
    public int getInstances() {
        if (null != ds) {
            return ds.getInstancesCount();
        }
        return 0;
    }

    /**
     *
     * @return
     */
    public int getFeatureCount() {
        if (null != ds) {
            return ds.getFeatureCount();
        }
        return 0;
    }

    /**
     *
     * @return
     */
    public String getSelFeatureName() {
        return selFeatureName;
    }

    /**
     *
     * @return
     */
    public String getSelFeatureNameLessY() {
        return selFeatureNameLessY;
    }

    /**
     *
     * @param selFeatureNameLessY
     */
    public void setSelFeatureNameLessY(String selFeatureNameLessY) {
        this.selFeatureNameLessY = selFeatureNameLessY;
    }

    /**
     *
     * @param selFeatureName
     */
    public void setSelFeatureName(String selFeatureName) {
        this.selFeatureName = selFeatureName;
        if (null != selFeatureName) {
            cds = new CurrentDataStats(getFeatureFromList(ds.getFeatureList(), selFeatureName));
        }
   }

    /**
     *
     * @return
     */
    public CurrentDataStats getCds() {
        return cds;
    }

    /**
     *
     */
    public void onAttChange() {
        createLineModel1();
    }

    /**
     *
     */
    private void createLineModel1() {
        if (null == selFeatureNameLessY) {
            selFeatureNameLessY = getFeatureNamesLessY()[0];
        }
        lineModel1 = initLinearModel();
        lineModel1.setTitle(selFeatureNameLessY);
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(yAxmin);
        yAxis.setMax(yAxmax);
    }

    /**
     *
     * @return
     */
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
        LineChartSeries series = new LineChartSeries();
        
        final List<Feature> fList = ds.getFeatureList();
        final List<Feature> newFList = new ArrayList<Feature>();
        newFList.add(getFeatureFromList(fList, selFeatureNameLessY));
        selFeatureNameLessY = newFList.get(0).getFeatureName();
        series.setLabel(selFeatureNameLessY);
        
        newFList.add(fList.get(fList.size() - 1));
        final Matrix matrix = DataSet2Matrix.transform(newFList);
        LOG.info(matrix.toString());
        int rows = matrix.rows();
        for (int i = 0; i < rows; i++) {
            series.set(matrix.component(i, 0), matrix.component(i, 1));
        }
        setYAxisLimits(matrix.getColumn(1));
        
        model.addSeries(series);
        return model;
    }
    /**
     * 
     * @param y 
     */
    private void setYAxisLimits(final double[] y){
        Arrays.sort(y);
        this.yAxmin = y[0];
        this.yAxmax = y[y.length - 1];
    }
    /**
     *
     * @return
     */
    public LineChartModel getLineModel1() {
        return lineModel1;
    }

    /**
     *
     * @param lineModel1
     */
    public void setLineModel1(LineChartModel lineModel1) {
        this.lineModel1 = lineModel1;
    }
    /**
     * 
     * @param fl
     * @param fname
     * @return 
     */
    private Feature getFeatureFromList(final List<Feature> fl, final String fname) {
        
        for (Feature f : fl) {
            LOG.log(Level.INFO, f.getFeatureName());
            if (f.getFeatureName().equals(fname)) {
                return f;
            }
        }
        return fl.get(0);
    }
}
