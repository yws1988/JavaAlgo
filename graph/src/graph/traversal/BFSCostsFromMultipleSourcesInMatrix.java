package graph.traversal;
/*
You are given a matrix of size N*M that contains the digits 0, 1, or 2 only.
All the cells that contain 2 (destination) and are adjacent to any cell that contains 1 (source)
will be converted from 2 to 1,
Simultaneously in 1 second.
Write a program to find the minimum time to convert all the cells having value 2 to 1.
*/

import java.util.LinkedList;

public class BFSCostsFromMultipleSourcesInMatrix {
    public static int n, m;
    public static int[] ns;
    public static int[][] g;

    public int getCost()
    {
        boolean[][] vs = new boolean[n][m];
        LinkedList<Node> queue = new LinkedList<>();
        int num = 0;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (g[i][j] == 1)
                {
                    queue.add(new Node(i, j, 0));
                    vs[i][j] = true;
                }else if (g[i][j] == 2)
                {
                    num++;
                }
            }
        }

        int[] dx = {1, 0, -1, 0 };
        int[] dy = {0, 1, 0, -1 };

        int max = 0;
        while (queue.size() > 0)
        {
            var node = queue.pop();
            int i = node.x;
            int j = node.y;
            int d = node.d;
            for (int m = 0; m < 4; m++)
            {
                int tx = i + dx[m];
                int ty = j + dy[m];
                if(isSafe(tx, ty) && !vs[tx][ty] && g[tx][ty]==2)
                {
                    queue.add(new Node(tx, ty, d + 1));
                    num--;
                    if (d >= max)
                    {
                        max = d + 1;
                    }
                    vs[tx][ty] = true;
                }
            }
        }

        return max;
    }

    static boolean isSafe(int x, int y)
    {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    public static class Node
    {
        public int x;
        public int y;
        public int d;

        public Node(int tx, int ty, int td)
        {
            x = tx;
            y = ty;
            d = td;
        }
    }
}
