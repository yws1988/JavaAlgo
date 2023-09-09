package graph.path.shortestpath;
// Unsolved
/*
    Give n points number from 0 to n-1, the matrix[i, j] reprensents distances from i to j.
    Create a circle start from point 0, go through the points to n-1(ascending order by numbers),
    then back to 0 (descending order by numbers), every point should be visited once.
*/

public class ShortestCirclePathForOrderedPoints
{
    public static double getShortestPath(double[][] distances, int n)
    {
        var dp = new double[n][n];
        distances = new double[n][n];

        dp[0][1] = distances[0][1];

        for (int i = 0; i < n-1; i++)
        {
            for (int j = i + 2; j < n; j++)
            {
                dp[i][j] = dp[i][j-1] + distances[j-1][j];
            }

            for (int j = i + 2; j < n; j++)
            {
                double q = dp[i][j - 1] + distances[i][j];

                if(i==0 || q < dp[j-1][j])
                {
                    dp[j - 1][j] = q;
                }
            }
        }

        dp[n - 1][n - 1] = dp[n - 2][n - 1] + distances[n-2][n-1];

        return dp[n - 1][n - 1];
    }
}
