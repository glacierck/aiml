/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.machinelearning;

import junit.framework.Assert;
import junit.framework.TestCase;
import pt.mleiria.EnvSettings;
import pt.mleiria.LogTypes;
import pt.mleiria.machinelearning.iterations.GradientDescent;
import pt.mleiria.machinelearning.matrixAlgebra.Matrix;
import pt.mleiria.machinelearning.preprocess.DataSet2Matrix;
import pt.mleiria.machinelearning.preprocess.FeatNormMeanStdev;
import pt.mleiria.machinelearning.preprocess.FeatureNormalization;
import pt.mleiria.ml.core.GenericDataSet;
import pt.mleiria.ml.core.TextDataSet;
import pt.mleiria.utils.ioutils.IOUtils;
import pt.mleiria.utils.viewutils.ViewUtils;

/**
 *
 * @author manuel
 */
public class GradientDescentTest extends TestCase {

    private static final org.apache.log4j.Logger mlearningLog = org.apache.log4j.Logger.getLogger(LogTypes.MLEARNING_LOG);
    private Matrix a;
    private Matrix aMulti;
    private Matrix featuresX;
    private Matrix outputY;
    final static String path = EnvSettings.DATA_DIR;
    private GenericDataSet ds;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        String separator = ",";
        try {
            ds = new TextDataSet.TextDataSetBuilder(EnvSettings.EX1DATA1).separator(separator).build();
            ds.loadData();
            a = DataSet2Matrix.transform(ds.getFeatureList());
            mlearningLog.info("EX1DATA1:\n" + a.toString());
            ds = new TextDataSet.TextDataSetBuilder(EnvSettings.EX1DATA2).separator(separator).build();
            ds.loadData();
            aMulti = DataSet2Matrix.transform(ds.getFeatureList());
            mlearningLog.info("EX1DATA2:\n" + aMulti.toString());

            Matrix[] splitedM = aMulti.split(1);
            featuresX = splitedM[0];
            outputY = splitedM[1];

        } catch (Exception ex) {
            mlearningLog.error(ex);
        }

    }

    public void testFeatureNormalization() {
        final FeatNormMeanStdev ftn = new FeatNormMeanStdev();
        final Matrix normalizedM = ftn.normalize(featuresX);
        mlearningLog.info("Mu " + ViewUtils.showArrayContents(ftn.getMean()));
        mlearningLog.info("Sigma " + ViewUtils.showArrayContents(ftn.getStdev()));
        Assert.assertEquals(2000.6808510638298, ftn.getMean()[0]);
        Assert.assertEquals(3.1702127659574466, ftn.getMean()[1]);
        Assert.assertEquals(0.13000986907454057, normalizedM.component(0, 0));
    }

    public void testGradientDescentMulti() {
        double alpha = 0.01;
        int numIter = 400;
        //Normalize featuresX
        final FeatureNormalization ftn = new FeatNormMeanStdev();
        final Matrix featuresXNorm = ftn.normalize(this.featuresX);
        mlearningLog.info("Normalized Matrix (10 rows) \n" + featuresXNorm.toString(10));

        GradientDescent gd = new GradientDescent(featuresXNorm, outputY, alpha, numIter);
        gd.evaluate();
        IOUtils.saveArrayToFile(path + "JGDM.txt", gd.getCostHistory());
        //LOGGER.log(Level.INFO, "Cost History \n{0}", ViewUtils.viewArrayContents(gd.getCostHistory()));
        Assert.assertEquals(334302.06399327697, gd.getTheta().component(0, 0));
        Assert.assertEquals(100087.11600584642, gd.getTheta().component(1, 0));
        Assert.assertEquals(3673.548450928262, gd.getTheta().component(2, 0));

    }

    public void testGradientDescent() {
        double alpha = 0.01;
        int numIter = 1500;
        final Matrix[] splitedM = a.split(1);
        final Matrix featuresX = splitedM[0];
        final Matrix outputY = splitedM[1];
        GradientDescent gd = new GradientDescent(featuresX, outputY, alpha, numIter);

        gd.evaluate();
        final double[] costH = gd.getCostHistory();
        IOUtils.saveArrayToFile(path + "JGD.txt", costH);
        Assert.assertEquals(32.072733877455654, costH[0]);
        Assert.assertEquals(-3.63029143940436, gd.getTheta().component(0, 0));
        Assert.assertEquals(1.166362350335582, gd.getTheta().component(1, 0));

        double expected = 4519.7678677017675;
        double[][] predict = new double[1][2];
        predict[0][0] = 1;
        predict[0][1] = 3.5;
        Matrix m = new Matrix(predict);
        mlearningLog.info("Final Theta " + gd.getTheta().toString());
        mlearningLog.info("Prediction Matrix \n" + m.toString());
        Assert.assertEquals(expected, m.multiply(gd.getTheta()).component(0, 0) * 10000.0);

    }

}
