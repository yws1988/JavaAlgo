package graph.connectivity;

import java.util.ArrayList;
import java.util.List;

public class LargestConnectedComponentInGrid
{
    public static int getLargestConnectedComponent(int[][] graph)
    {
        int m = graph.length;
        int n = graph[0].length;
        var vs = new boolean[m][n];

        var area = new int[m][n];
        int areaNum = 1;
        int max = 0;
        int maxAreaNum = 0;
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (!vs[i][j])
                {
                    int val = graph[i][j];
                    int num = search(graph, vs, i, j, val, areaNum, m, n, area);
                    if (num > max)
                    {
                        max = num;
                        maxAreaNum = areaNum;
                    }
                    areaNum++;
                }
            }
        }

        return max;
    }

    public static int search(int[][] graph, boolean[][] vs, int i, int j, int val, int counter, int m, int n, int[][] area)
    {
        if (i < 0 || i >= m || j < 0 || j >= n) return 0;

        if (!vs[i][j] && graph[i][j] == val)
        {
            vs[i][j] = true;
            area[i][j] = counter;
            int[] xMove = { 1, -1, 0, 0 };
            int[] yMove = { 0, 0, 1, -1 };

            int c = 1;
            for (int u = 0; u < 4; u++)
            {
                c += search(graph, vs, i + xMove[u], j + yMove[u], val, counter, m, n, area);
            }
            return c;
        }

        return 0;
    }

        public static List<Pair> getArea(int[][] area, int areaNum)
        {
            var list = new ArrayList<Pair>();
            int m = area.length;
            int n = area[0].length;
            for (int i = 0; i < m; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    if (area[i][j] == areaNum)
                    {
                        list.add(new Pair(i, j));
                    }
                }
            }

            return list;
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
