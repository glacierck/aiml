package pt.mleiria.machinelearning.matrixAlgebra;

/**
 *
 * @author manuel
 */
public class Matrix {

    protected double[][] components;

    /**
     *
     * @param a
     */
    public Matrix(final double[][] a) {
        components = a;
    }

    /**
     * Creates a zero matrix of given dimensions
     *
     * @param n number of rows
     * @param m number of columns
     */
    public Matrix(final int n, final int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Illegal matrix size.");
        }
        components = new double[n][m];
        clear();
    }

    /**
     * Creates a square matrix
     *
     * @param dimension
     */
    public Matrix(final int dimension) {
        components = new double[dimension][dimension];
        clear();
    }

    /**
     *
     * @return true if the receiver is a square matrix
     */
    public boolean isSquare() {
        return rows() == columns();
    }

    /**
     *
     * @return the number of rows in the Matrix
     */
    public int rows() {
        return components.length;
    }

    /**
     *
     * @return the number of columns in the Matrix
     */
    public int columns() {
        return components[0].length;
    }

    /**
     * @return Matrix	transpose of the receiver
     */
    public Matrix transpose() {
        int n = rows();
        int m = columns();
        double[][] newComponents = new double[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newComponents[j][i] = components[i][j];
            }
        }
        return new Matrix(newComponents);
    }

    /**
     * @return double matrix coords(n,m)
     * @param n int
     * @param m int
     */
    public double component(final int n, final int m) {
        return components[n][m];
    }

    private void clear() {
        //columns size
        int m = components[0].length;
        //rows size
        int n = components.length;
        //for each row
        for (int i = 0; i < n; i++) {
            //for each column
            for (int j = 0; j < m; j++) {
                components[i][j] = 0;
            }
        }
    }

    /**
     * @return double[][]	a copy of the components of the receiver.
     */
    public double[][] toComponents() {
        int n = rows();
        int m = columns();
        double[][] answer = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer[i][j] = components[i][j];
            }
        }
        return answer;
    }

    /**
     * @return true if the supplied matrix is equal to the receiver.
     * @param matrix Matrix
     */
    public boolean equals(final Matrix matrix) {
        int n = this.rows();
        if (n != matrix.rows()) {
            return false;
        }
        int m = this.columns();
        if (m != matrix.columns()) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (components[i][j] != matrix.components[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns a string representation of the Matrix.
     *
     * @return java.lang.String
     */
    @Override
    public String toString() {
        final StringBuffer sb;
        sb = new StringBuffer();
        char[] separator = {'[', ' '};
        int n = rows();
        int m = columns();
        for (int i = 0; i < n; i++) {
            separator[0] = '{';
            for (int j = 0; j < m; j++) {
                sb.append(separator);
                sb.append(components[i][j]);
                separator[0] = ' ';
            }
            sb.append('}');
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * Returns a string representation of the Matrix with numRows
     *
     * @param numRows 
     * @return java.lang.String
     */
    public String toString(final int numRows) {
        final StringBuffer sb;
        sb = new StringBuffer();
        char[] separator = {'[', ' '};
        int n = numRows;
        int m = columns();
        for (int i = 0; i < n; i++) {
            separator[0] = '{';
            for (int j = 0; j < m; j++) {
                sb.append(separator);
                sb.append(components[i][j]);
                separator[0] = ' ';
            }
            sb.append('}');
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     *
     * @param matrix
     * @return
     */
    public Matrix add(final Matrix matrix) {
        if (matrix.rows() != rows() || matrix.columns() != columns()) {
            throw new IllegalArgumentException("Illegal Matrix size");
        }
        final int n = this.rows();
        final int m = this.columns();
        final double[][] newComponents = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newComponents[i][j] = components[i][j] + matrix.components[i][j];
            }
        }
        return new Matrix(newComponents);
    }

    /**
     * Subtract the Matrix matrix from the receiver
     *
     * @param matrix
     * @return
     */
    public Matrix subtract(final Matrix matrix) {
        if (matrix.rows() != rows() || matrix.columns() != columns()) {
            throw new IllegalArgumentException("Illegal Matrix size");
        }
        final Matrix auxMatrix = matrix.multiply(-1);
        return add(auxMatrix);
    }

    /**
     * Wise multiplication
     *
     * @param value
     * @return
     */
    public Matrix multiply(final double value) {
        final int n = this.rows();
        final int m = this.columns();
        final double[][] newComponents = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newComponents[i][j] = components[i][j] * value;
            }
        }
        return new Matrix(newComponents);
    }

    /**
     * Product of the receiver with the supplied matrix
     *
     * @param matrix
     * @return Matrix
     */
    public Matrix multiply(final Matrix matrix) {
        if (matrix.rows() != columns()) {
            throw new IllegalArgumentException("Illegal size.");
        }
        return new Matrix(productComponents(matrix));
    }

    /**
     * @param matrix
     * @return double[][] The components of the product of the receiver with the
     * supplied matrix
     */
    protected double[][] productComponents(final Matrix matrix) {
        int p = this.columns();
        int n = this.rows();
        int m = matrix.columns();
        double[][] newComponents = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                double sum = 0;
                for (int k = 0; k < p; k++) {
                    sum += components[i][k] * matrix.components[k][j];
                }
                newComponents[i][j] = sum;
            }
        }
        return newComponents;
    }

    /**
     * Splits the receiver in two Matrices
     *
     * @param numCols number of columns of the Matrix[1]
     * @return Matrix[2] where Matrix[0] = Matrix[rows, cols-numCols] Matrix[1]
     * = Matrix[rows, numCols
     */
    public Matrix[] split(final int numCols) {
        if (numCols >= columns()) {
            throw new IllegalArgumentException("numCols must be smaller than columns");
        }
        final int rows = this.rows();
        final int cols = this.columns();
        final int diffCols = cols - numCols;
        final double[][] a = new double[rows][diffCols];
        final double[][] b = new double[rows][numCols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j < diffCols) {
                    a[i][j] = components[i][j];
                } else {
                    b[i][diffCols - j] = components[i][j];
                }
            }
        }
        final Matrix[] result = new Matrix[2];
        final Matrix aa = new Matrix(a);
        final Matrix bb = new Matrix(b);
        result[0] = aa;
        result[1] = bb;
        return result;

    }

    /**
     * Add a column of ones in column[0] to the receiver
     *
     * @return
     */
    public Matrix addOnes() {
        final int rows = rows();
        final int columns = columns() + 1;

        double[][] newComponents = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            newComponents[i][0] = 1.0;
            for (int j = 1; j < columns; j++) {
                newComponents[i][j] = component(i, j - 1);
            }
        }
        return new Matrix(newComponents);
    }

    /**
     *
     * @param exponent
     * @return
     */
    public Matrix power(double exponent) {
        final double[][] newComponents = new double[rows()][columns()];
        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < columns(); j++) {
                newComponents[i][j] = Math.pow(components[i][j], exponent);
            }
        }
        return new Matrix(newComponents);
    }

    /**
     * Sum of array elements over a given axis
     *
     * @param axis 1 for rows and 2 for cols
     * @return
     */
    public Matrix sum(int axis) {
        if (axis != 1 && axis != 2) {
            throw new IllegalArgumentException("1 - Rows; 2 - Columns");
        }
        double[][] newComponents = null;
        if (axis == 1) {
            newComponents = new double[rows()][1];
            for (int i = 0; i < rows(); i++) {
                double result = 0;
                for (int j = 0; j < columns(); j++) {
                    result += components[i][j];
                }
                newComponents[i][0] = result;
            }
        }
        if (axis == 2) {
            newComponents = new double[1][columns()];
            for (int i = 0; i < columns(); i++) {
                double result = 0;
                for (int j = 0; j < rows(); j++) {
                    result += components[i][j];
                }
                newComponents[0][i] = result;
            }
        }
        return new Matrix(newComponents);

    }

    /**
     * Creates a matrix with random elements uniformly distributed on the
     * interval (0, 1).
     *
     * @param n
     * @param m
     * @return
     */
    public void rand() {
        final int n = rows();
        final int m = columns();
        //for each row
        for (int i = 0; i < n; i++) {
            //for each column
            for (int j = 0; j < m; j++) {
                components[i][j] = Math.random();
            }
        }
    }

    /**
     *
     * @param dimension
     */
    public void identity() {
        int n = rows();
        int m = columns();
        if (n != m) {
            throw new IllegalArgumentException("Only Square matrix supported.");
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    components[i][j] = 1;
                } else {
                    components[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        Matrix m = new Matrix(3, 4);
        m.rand();
        System.out.println(m.toString());

        Matrix m1 = new Matrix(3);
        m1.identity();
        System.out.println(m1.toString());

    }
}
