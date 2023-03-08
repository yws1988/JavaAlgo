package graph.connectivity;

public class NumConnectedGraphInMatrix
{
    // Following matrix have 4 connected components
    //{1, 1, 0, 0, 0},
    //{0, 1, 0, 0, 1},
    //{1, 0, 0, 1, 1},
    //{0, 0, 0, 0, 0},
    //{1, 0, 1, 0, 0}

    public static int getConnectedGraphNum(int[][] graph)
    {
        int v = graph.length;
        boolean[][] vs = new boolean[v][v];

        int count = 0;

        for (int i = 0; i < v; i++)
        {
            for (int j = 0; j < v; j++)
            {
                if(graph[i][j]==1 && !vs[i][j])
                {
                    count++;
                    DFSUtil(graph, i, j, vs, v);
                }
            }
        }

        return count;
    }

    static void DFSUtil(int[][] graph, int i, int j, boolean[][] vs, int v)
    {
        vs[i][j] = true;

        for (int m = -1; m <= 1; m++)
        {
            for (int h = -1; h <= 1; h++)
            {
                if (m == 0 && h == 0) continue;
                int tI = i + m;
                int tJ = j + h;
                if(isSafe(tI, tJ, v) && !vs[tI][tJ] && graph[tI][tJ]==1)
                {
                    DFSUtil(graph, tI, tJ, vs, v);
                }
            }
        }
    }

    static boolean isSafe(int i, int j, int v)
    {
        return i >= 0 && i < v && j >= 0 && j < v;
    }
}

