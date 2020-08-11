package matrix;

import java.util.Scanner;

public class Matrix {
    final int rows;
    final int cols;
    final double[][] matrix;

    public Matrix(final int size) {
        this(size, size);
    }

    public Matrix(final int rows, final int cols) {
        this.rows = rows;
        this.cols = cols;
        matrix = new double[rows][cols];
    }

    public void input(final Scanner scanner) {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                matrix[i][j] = scanner.nextDouble();
    }

    public void print() {
        System.out.println("The matrix is:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                System.out.printf("%.2f ", matrix[i][j]);
            System.out.println();
        }
        System.out.println();
    }

    public Matrix add(final Matrix other) {
        final Matrix out = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                out.matrix[i][j] = this.matrix[i][j] + other.matrix[i][j];
        return out;
    }

    public Matrix mult(final double scalar) {
        final Matrix out = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                out.matrix[i][j] = this.matrix[i][j] * scalar;
        return out;
    }

    public Matrix mult(final Matrix other) {
        final int rows = this.rows;
        final int cols = other.cols;
        final int iter = this.cols;
        final Matrix out = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                for (int k = 0; k < iter; k++)
                    out.matrix[i][j] += this.matrix[i][k] * other.matrix[k][j];
        return out;
    }

    public Matrix transMainDiag() {
        final Matrix out = new Matrix(cols, rows);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                out.matrix[j][i] = this.matrix[i][j];
        return out;
    }

    public Matrix transSideDiag() {
        final Matrix out = new Matrix(cols, rows);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                out.matrix[cols - j - 1][rows - i - 1] = this.matrix[i][j];
        return out;
    }

    public Matrix transVer() {
        final Matrix out = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                out.matrix[i][cols - j - 1] = this.matrix[i][j];
        return out;
    }

    public Matrix transHor() {
        final Matrix out = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                out.matrix[rows - i - 1][j] = this.matrix[i][j];
        return out;
    }

    public double determinant() {
        double det = 0;
        if (rows == 1)
            return matrix[0][0];
        for (int i = 0; i < rows; i++)
            det += matrix[0][i] * cofactor(0, i);
        return det;
    }

    public double cofactor(final int i, final int j) {
        final int sign = (i + j) % 2 == 0 ? 1 : -1;
        return sign * minor(i, j);
    }

    public double minor(final int x, final int y) {
        final Matrix out = new Matrix(rows - 1, cols - 1);
        int r = 0;
        int c = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (i != x && j != y) {
                    out.matrix[r][c++] = this.matrix[i][j];
                    if (c == cols - 1) {
                        c = 0;
                        r++;
                    }
                }
        return out.determinant();
    }

    public Matrix inverse() {
        final double det = determinant();
        if (det == 0)
            return null;
        return adjugate().mult(1 / det);
    }

    public Matrix adjugate() {
        return cofactor().transMainDiag();
    }

    public Matrix cofactor() {
        final Matrix out = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                out.matrix[i][j] = clean(cofactor(i, j));
        return out;
    }

    private double clean(final double n) {
        if (n == -0)
            return 0;
        else
            return n;
    }
}