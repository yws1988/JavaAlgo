/*
 * Given a directed graph and two vertices ‘u’ and ‘v’ in it
 * count all possible walks from ‘u’ to ‘v’ with exactly k edges on the walk.
 */

package graph;

public class NumOfPathKEdgesInGraph
{
    public static int GetNumOfPaths(int[][] graph, int s, int d, int k)
    {
        int v = graph.length;

        int[][][] dp= new int[v][v][k+1];

        for (int i = 0; i < v; i++)
        {
            for (int j = 0; j < v; j++)
            {
                if (i == j)
                {
                    dp[i][j][0] = 1;
                }else if(graph[i][j] == 1)
                {
                    dp[i][j][1] = 1;
                }
            }
        }

        for (int e = 2; e <= k; e++)
        {
            for (int i = 0; i < v; i++)
            {
                for (int j = 0; j < v; j++)
                {
                    for (int h = 0; h < v; h++)
                    {
                        //if i == j then the value of the graph[i][j] = 0
                        if (i != j && graph[j][h] == 1)
                        {
                            dp[i][h][e] += dp[i][j][e-1];
                        }
                    }
                }
            }
        }

        return dp[s][d][k];
    }
}

