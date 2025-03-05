package utils.graph;

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

    /***
     Given a list of strings, convert character . to 1
     ##..#
     #...#
     #...#
     */
    public static int[][] getMatrixFromStrings(String[] strs){
        int row = strs.length;
        int col = strs[0].length();
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = strs[i].charAt(j)=='.' ? 1:0;
            }
        }

        return matrix;
    }

    /***
     Given arrays of edges like this:
     {{0,8}, {1,7}, {5,6}, {6,8}, {8,9}, {5,9}, {3, 9}}
     Build a matrix graph
     */
    public static int[][] getMatrixFromArrays(int[][] arrays, int n, boolean isDirected){
        int[][] graph = new int[n][n];

        for (int i = 0; i < arrays.length; i++) {
            graph[arrays[i][0]][arrays[i][1]] = 1;
            if(!isDirected){
                graph[arrays[i][1]][arrays[i][0]] = 1;
            }
        }

        return graph;
    }
}
