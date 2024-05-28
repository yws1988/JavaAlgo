package dp.collections.matrix;

/*
    Given a matrix[m][n], define the value of (i, j) is all the sum of elements
    matrix[x][y] where 0=<x<=i and 0<=y<=j.
    Calculate all the value (i, j) in the matrix
 */

public class MatrixSumCalculation {
    public long[][] calculateSum(int[][] matrix){
        int row = matrix.length;
        int col = matrix[0].length;

        long[][] dp = new long[row+1][col+1];

        for (int i = 1; i <= row; i++)
        {
            for (int j = 1; j <= col; j++)
            {
                if(i!=1 || j != 1)
                {
                    dp[i][j] = matrix[i][j] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
                }
            }
        }

        return dp;
    }
}
