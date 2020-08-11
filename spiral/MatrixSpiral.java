package spiral;

public class MatrixSpiral {
    private final int[][] matrix;
    private final int n;

    public MatrixSpiral(final int n) {
        this.n = n;
        this.matrix = new int[n][n];
    }

    public void make() {
        int minRow = 0;
        int minCol = 0;
        int maxRow = n - 1;
        int maxCol = n - 1;
        int count = 1;
        while (count <= n * n) {
            for (int i = minCol; i <= maxCol; i++)
                matrix[minRow][i] = count++;
            minRow++;
            for (int i = minRow; i <= maxRow; i++)
                matrix[i][maxCol] = count++;
            maxCol--;
            for (int i = maxCol; i >= minCol; i--)
                matrix[maxRow][i] = count++;
            maxRow--;
            for (int i = maxRow; i >= minRow; i--)
                matrix[i][minCol] = count++;
            minCol++;
        }
    }

    public void print() {
        for (final int[] arr : matrix) {
            for (final int ele : arr) {
                if (n > 9 && ele < 10)
                    System.out.print("00" + ele + " ");
                else if (n > 9 && ele < 100 || n > 3 && ele < 10)
                    System.out.print("0" + ele + " ");
                else
                    System.out.print(ele + " ");
            }
            System.out.println();
        }
    }
}
