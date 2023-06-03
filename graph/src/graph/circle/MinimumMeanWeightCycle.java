package graph.circle;
/*
https://www.geeksforgeeks.org/karps-minimum-mean-average-weight-cycle-algorithm/

Given a directed and strongly connected graph with non negative edge weighs.
We define mean weight of a cycle as the summation of all the edge weights of the cycle
divided by the no. of edges.
Our task is to find the minimum mean weight among all the directed cycles of the graph.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class MinimumMeanWeightCycle
{
    public static double getMinimumMeanWeightCycle(int[][] graph)
    {
        int v = graph.length;
        var dp = new int[v + 1][v];
        var edges = new ArrayList<Pair>();

        for (int i = 0; i <= v; i++)
        {
            for (int j = 0; j < v; j++)
            {
                dp[i][j] = Integer.MAX_VALUE;

                if (i != v && graph[i][j] != Integer.MAX_VALUE)
                {
                    edges.add(new Pair(i, j));
                }
            }
        }

        dp[0][0] = 0;

        for (int i = 1; i <= v; i++)
        {
            for (var e : edges)
            {
                int s = e.x;
                int d = e.y;
                if (dp[i - 1][s] != Integer.MAX_VALUE)
                {
                    dp[i][d] = Math.min(dp[i][d], dp[i-1][s]+graph[s][d]);
                }
            }
        }

        double[] avg = new double[v];
        for (int i = 0; i < v; i++)
        {
            avg[i] = Double.MAX_VALUE;
        }

        for (int j = 0; j < v; j++)
        {
            if (dp[v][j] != Integer.MAX_VALUE)
            {
                int minIndex = -1;
                int min = Integer.MAX_VALUE;
                for (int i = 0; i < v; i++)
                {
                    if (dp[i][j] < min)
                    {
                        minIndex = i;
                        min = dp[i][j];
                    }
                }

                if (minIndex != -1)
                {
                    avg[j] = (double)(dp[v][j] - dp[minIndex][j]) / (v - minIndex);
                }
            }
        }

        return Arrays.stream(avg).min().getAsDouble();
    }

    public static class Pair{
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
