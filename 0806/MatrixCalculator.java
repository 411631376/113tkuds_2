public class MatrixCalculator {
    public static void main(String[] args) {
        int[][] A = {{1, 2}, {3, 4}};
        int[][] B = {{5, 6}, {7, 8}};
        int[][] add = new int[2][2];
        int[][] mult = new int[2][2];
        int[][] transpose = new int[2][2];

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                add[i][j] = A[i][j] + B[i][j];
                transpose[j][i] = A[i][j];
            }

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                for (int k = 0; k < 2; k++)
                    mult[i][j] += A[i][k] * B[k][j];

        int max = A[0][0], min = A[0][0];
        for (int[] row : A)
            for (int v : row) {
                if (v > max) max = v;
                if (v < min) min = v;
            }
    }
}