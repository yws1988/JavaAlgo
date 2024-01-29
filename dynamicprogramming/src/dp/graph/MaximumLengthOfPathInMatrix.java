package dp.graph;

/*
    Given a grid of numbers, find maximum length Snake sequence and print it. If multiple snake sequences exists with the maximum length,
    print any one of them.

    A snake sequence is made up of adjacent numbers in the grid such that for each number,
    the number on the right or the number below it is +1 or -1 its value. For example,
    if you are at location (x, y) in the grid, you can either move right i.e.
    (x, y+1) if that number is ± 1 or move down i.e. (x+1, y) if that number is ± 1.

    For example,

    9, 6, 5, 2
    8, 7, 6, 5
    7, 3, 1, 6
    1, 1, 1, 7

    In above grid, the longest snake sequence is: (9, 8, 7, 6, 5, 6, 7)
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MaximumLengthOfPathInMatrix {

    public static List<Pair> getPath(int[][] graph)
    {
        int m = graph.length;
        int n = graph[0].length;

        int[][] dp = new int[m][n];
        int max = 0;
        int[] maxPoint = new int[2];
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (i > 0 || j > 0)
                {
                    if (j > 0 && isAjacentCell(graph[i][j - 1], graph[i][j]))
                    {
                        dp[i][j] = Math.max(dp[i][j - 1] + 1, dp[i][j]);
                    }

                    if (i > 0 && isAjacentCell(graph[i - 1][j], graph[i][j]))
                    {
                        dp[i][j] = Math.max(dp[i - 1][j] + 1, dp[i][j]);
                    }
                }

                if (dp[i][j] == 0) dp[i][j] = 1;

                if (dp[i][j] > max)
                {
                    max = dp[i][j];
                    maxPoint[0] = i;
                    maxPoint[1] = j;
                }
            }
        }

        var stack = new Stack<Pair>();

        int x = maxPoint[0];
        int y = maxPoint[1];

        while (max > 0)
        {
            stack.push(new Pair(x, y));
            max--;

            if (y > 0 && dp[x][y - 1] == max)
            {
                y--;
            }
            else if (x > 0 && dp[x - 1][y] == max)
            {
                x--;
            }
        }

        return new ArrayList<>(stack);
    }

    static boolean isAjacentCell(int x, int y)
    {
        return Math.abs(x - y) == 1 ? true : false;
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
