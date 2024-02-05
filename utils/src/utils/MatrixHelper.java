package utils;

public class MatrixHelper {
    public static String getHashString(int[][] matrix)
    {
        int n = matrix.length;

        var sb = new StringBuilder();
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                sb.append(matrix[i][j]);
            }
        }

        return sb.toString();
    }

    public static int[][] getMatrixFromString(String hashString, int n)
    {
        var matrix = new int[n][n];
        for (int i = 0; i < hashString.length(); i++)
        {
            matrix[i / n][i % n] = hashString.charAt(i);
        }

        return matrix;
    }

    /// <summary>
    /// Get the matrix to calculate the max costs for Hungarian Matching
    /// </summary>
    /// <param name="matrix">cost matrix</param>
    /// <returns></returns>
    public static int[][] getMaxMatrixForHungarianMatching(int[][] matrix, int max)
    {
        int n = matrix.length;

        var rGraph = new int[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                rGraph[i][j] = max - matrix[i][j];
            }
        }

        return rGraph;
    }
}
