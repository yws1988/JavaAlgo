package graph.path.shortestpath;

/*
 * Given a set of cities and distance between every pair of cities,
 * the problem is to find the shortest possible tour that visits every
 * city exactly once and returns to the starting point.
 */

public class TravellingSalesman
{
    public static int getShortestPath(int[][] graph)
    {
        int v = graph.length;

        for (int i = 0; i < v; i++)
        {
            for (int j = 0; j < v; j++)
            {
                if (graph[i][j] == 0)
                {
                    graph[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int max = (int)Math.pow(2, v);
        int[][] dp = new int[v][max];

        for (int i = 0; i < v; i++)
        {
            for (int j = 0; j < max; j++)
            {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 1; i < v; i++)
        {
            if (graph[i][0] != Integer.MAX_VALUE)
            {
                dp[i][1 | (1 << i)] = graph[i][0];
            }
        }

        for (int mask = 1; mask < max; mask++)
        {
            for (int d = 1; d < v; d++)
            {
                for (int s = 1; s < v; s++)
                {
                    if (graph[d][s] != Integer.MAX_VALUE && (mask >> d & 1) == 1 && (mask >> s & 1) == 1 && dp[s][mask & ~(1 << d)] != Integer.MAX_VALUE)
                    {
                        dp[d][mask] = Math.min(dp[d][mask], dp[s][mask & ~(1 << d)] + graph[d][s]);
                    }
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 1; i < v; i++)
        {
            if (graph[0][i] != Integer.MAX_VALUE && dp[i][max - 1] != Integer.MAX_VALUE)
            {
                result = Math.min(dp[i][max - 1] + graph[0][i], result);
            }
        }

        return result;
    }
}
