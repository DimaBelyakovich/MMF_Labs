package task1;

public class Matrix {
    private long[][] matrix;
    private long n;

    public Matrix(long n){
        this.n = n;
        matrix = new long[(int) this.n][(int) this.n];
    }

    public long getElement(Integer i, Integer j) {
        if (i <= this.n && j <= this.n) {
            return matrix[i][j];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public long getN() {
        return n;
    }

    public long[][] getMatrix() {
        return matrix;
    }

    public void setElement(long value, Integer i, Integer j) {
        if (i <= this.n && j <= this.n) {
            this.matrix[i][j] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
