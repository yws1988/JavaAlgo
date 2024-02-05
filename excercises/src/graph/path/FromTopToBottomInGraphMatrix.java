package graph.path;

/*
 Given a string matrix, The goal is to determine the minimum number of moves you have to make to reach the bottom of the matrix.
 You can choose any starting point on the top of the matrix and arrive at any point at the bottom of the track.
 In the matrix 'X' means a wall, '.' means it is crossable

 Exemples with following matrix 13*7:
    X..X...
    X.X..XX
    X.X....
    .XXX.X.
    X..X...
    XXXX...
    X.X..X.
    X.X.XXX
    .XX..XX
    X.X....
    X..X..X
    X.X..X.
    X.X..XX

    Output
    14
 */


import java.util.LinkedList;

public class FromTopToBottomInGraphMatrix {
    public static int getMinimumSteps(String[] graph)
    {
        int n = graph.length;
        int m = graph[0].length();
        var distance = new int[n][m];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (i == 0 && graph[i].charAt(j) == '.')
                {
                    distance[i][j] = 0;
                }
                else
                {
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < m; i++)
        {
            if (distance[0][i] == 0)
            {
                BFS(i, graph, distance);
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++)
        {
            res = Math.min(res, distance[n - 1][i]);
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    static void BFS(int idx, String[] graph, int[][] distance)
    {
        int n = graph.length;
        int m = graph[0].length();
        var queue = new LinkedList<Triple>();
        queue.push(new Triple(0, idx, 0));

        while (queue.size() > 0)
        {
            var c = queue.pop();
            int x = c.x;
            int y = c.y;
            int level = c.z;

            int[] dx = { 0, 1, 0 };
            int[] dy = { -1, 0, 1 };

            for (int i = 0; i < 3; i++)
            {
                int tx = x + dx[i];
                int ty = y + dy[i];

                if (ty >= 0 && ty < m && tx < n && graph[tx].charAt(ty) == '.' && distance[tx][ty] > 1 + level)
                {
                    distance[tx][ty] = 1 + level;
                    queue.push(new Triple(tx, ty, 1 + level));
                }
            }
        }
    }

    public static class Triple{
        public int x;
        public int y;
        public int z;

        public Triple(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
