/*
 * A general structure for managing iterations
 */
package pt.mleiria.machinelearning.iterations;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public abstract class IteratorProcessor {
    protected final static Logger LOG = Logger.getLogger(IteratorProcessor.class.getName());

    /**
     * Number of iterations performed.
     */
    protected int iterations;
    /**
     * Maximum allowed number of iterations.
     */
    private int maximumIterations = 5000;
    /**
     * Desired precision.
     */
    private double desiredPrecision;
    /**
     * Achieved precision.
     */
    private double precision;

    /**
     * Generic constructor.
     */
    public IteratorProcessor() {
    }

    /**
     * Performs the iterative process
     */
    public void evaluate() {
        iterations = 0;
        initializeIterations();
        while (iterations++ < maximumIterations) {
            precision = evaluateIteration();
            if (hasConverged()) {
                LOG.log(Level.INFO, "Converged at iteration: [{0}]", iterations);
                LOG.log(Level.INFO, "Converged with precision:[{0}]", precision);
                break;
            }
        }
        finalizeIterations();
    }

    /**
     * Evaluate the result of the current iteration.
     *
     * @return the estimated precision of the result.
     */
    abstract public double evaluateIteration();

    /**
     * Clean-up operations
     */
    public void finalizeIterations() {
        LOG.log(Level.INFO, "Iterations:[{0}]", iterations );
    }

    /**
     * @return desired precision.
     */
    public double getDesiredPrecision() {
        return desiredPrecision;
    }

    /**
     * @return the number of iterations performed.
     */
    public int getIterations() {
        return iterations;
    }

    /**
     * @return the maximum allowed number of iterations.
     */
    public int getMaximumIterations() {
        return maximumIterations;
    }

    /**
     * @return the attained precision.
     */
    public double getPrecision() {
        return precision;
    }

    /**
     * Check to see if the result has been attained.
     *
     * @return boolean
     */
    public boolean hasConverged() {
        return precision < desiredPrecision;
    }

    /**
     * Initializes internal parameters to start the iterative process.
     */
    public void initializeIterations() {
    }

    /**
     * Defines the desired precision.
     *
     * @param prec
     */
    public void setDesiredPrecision(double prec)
            throws IllegalArgumentException {
        if (prec <= 0) {
            throw new IllegalArgumentException("Non-positive precision: " + prec);
        }
        desiredPrecision = prec;
    }

    /**
     * Defines the maximum allowed number of iterations.
     *
     * @param maxIter
     */
    public void setMaximumIterations(int maxIter)
            throws IllegalArgumentException {
        if (maxIter < 1) {
            throw new IllegalArgumentException("Non-positive maximum iteration: " + maxIter);
        }
        maximumIterations = maxIter;
    }
}
