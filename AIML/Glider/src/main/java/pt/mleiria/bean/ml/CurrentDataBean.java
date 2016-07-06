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
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import pt.mleiria.machinelearning.matrixAlgebra.Matrix;
import pt.mleiria.machinelearning.preprocess.DataSet2Matrix;
import pt.mleiria.ml.core.Feature;
import pt.mleiria.preprocess.CurrentDataStats;
import pt.mleiria.utils.ioutils.HeaderObjectMapper;
import pt.mleiria.utils.ioutils.IOUtils;

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
    private BarChartModel barModel1;
    private HeaderObjectMapper hom;
    private double yAxmin;
    private double yAxmax;

    /**
     * Creates a new instance of CurrentDataBean
     */
    public CurrentDataBean() {
    }

    private void reset() {
        selFeatureName = "";
        selFeatureNameLessY = "";
        cds = null;
        ds = null;
        hom = null;

    }

    @PostConstruct
    public void init() {

    }

    @Override
    public void loadFile(String fileName) throws IOException {
        reset();
        super.loadFile(fileName);
        setShowFiles(false);
        if (null != ds) {
            setShowPreProcessChart(true);
            createInitPlot();
            if (ds.isYNominal()) {
                cds = new CurrentDataStats((ds.getYFeature()));
            }
        }

    }

    /**
     *
     * @param fileName
     * @throws Exception
     */
    public String showFileContents(String fileName) throws Exception {
        gliderLog.info(path + fileName);
        hom = IOUtils.loadRawData(path + fileName, true, true);
        return "rawDataWindow";
    }

    /**
     *
     * @return
     */
    public HeaderObjectMapper getRawData() {
        if (null != hom) {
            return hom;
        } else {
            return null;
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
    public boolean isYNumeric() {
        if (null != ds) {
            return ds.isYNumeric();
        }
        return false;
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
        if (null != selFeatureName && !selFeatureName.isEmpty()) {
            final Feature f = ds.getFeatureFromList(selFeatureName);
            cds = new CurrentDataStats(f);
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
        createInitPlot();
    }

    /**
     *
     */
    private void createInitPlot() {
        if (null == selFeatureNameLessY || selFeatureNameLessY.isEmpty()) {
            selFeatureNameLessY = getFeatureNamesLessY()[0];
        }
        if (isYNumeric()) {
            lineModel1 = initLinearChartModel();
            lineModel1.setTitle(selFeatureNameLessY);
            lineModel1.setLegendPosition("e");
            Axis yAxis = lineModel1.getAxis(AxisType.Y);
            yAxis.setMin(yAxmin);
            yAxis.setMax(yAxmax);
        } else {
            initBarCharModel();
        }
    }

    private BarChartModel initBarCharModel() {
        return null;
    }

    /**
     *
     * @return
     */
    private LineChartModel initLinearChartModel() {
        LineChartModel model = new LineChartModel();
        LineChartSeries series = new LineChartSeries();

        final List<Feature> newFList = new ArrayList<Feature>();
        newFList.add(ds.getFeatureFromList(selFeatureNameLessY));
        selFeatureNameLessY = newFList.get(0).getFeatureName();
        series.setLabel(selFeatureNameLessY);

        newFList.add(ds.getYFeature());
        final Matrix matrix = DataSet2Matrix.transform(newFList);
        //LOG.info(matrix.toString());
        int rows = matrix.rows();
        for (int i = 0; i < rows; i++) {
            series.set(matrix.component(i, 0), matrix.component(i, 1));
        }
        setYAxisLimits(matrix.getColumn(1));
        model.setShowPointLabels(true);
        model.addSeries(series);
        return model;
    }

    /**
     *
     * @param y
     */
    private void setYAxisLimits(final double[] y) {
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
     * @return
     */
    public BarChartModel getBarModel1() {
        return barModel1;
    }

    /**
     *
     * @param barModel1
     */
    public void setBarModel1(BarChartModel barModel1) {
        this.barModel1 = barModel1;
    }

    /**
     *
     * @param fl
     * @param fname
     * @return
     */
}
