package graph.path;

import java.util.LinkedList;

public class FloodFillFromSourceToDestinationInMatrix
{
    public static int n;
    public static char[][] cs;

    public static void solve()
    {
        int srcx = 0, srcy = 0;
        var queue = new LinkedList<Triple>();

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (cs[i][j] == 'M')
                {
                    queue.add(new Triple(i, j, 0));
                }
                else if (cs[i][j] == 'C')
                {
                    srcx = i;
                    srcy = j;
                }
            }
        }

        int[] xMove = { 1, 0, -1, 0 };
        int[] yMove = { 0, -1, 0, 1 };

        queue.add(new Triple(srcx, srcy, 0));

        while (queue.size()>0)
        {
            var p = queue.pop();
            int x = p.x;
            int y = p.y;
            char pChar = cs[x][y];

            for (int u = 0; u < 4; u++)
            {
                int tx = xMove[u] + x;
                int ty = yMove[u] + y;

                tx = tx == -1 ? n - 1 : tx;
                tx = tx == n ? 0 : tx;
                ty = ty == -1 ? n - 1 : ty;
                ty = ty == n ? 0 : ty;

                char cChar = cs[tx][ty];

                if (pChar == 'C')
                {
                    if (cChar == '.')
                    {
                        cs[tx][ty] = 'C';
                        queue.add(new Triple(tx, ty, p.z + 1));
                    }
                    else if (cChar == 'O')
                    {
                        System.out.println(p.z + 1);
                        return;
                    }
                }
                else
                {
                    if (cChar == 'O')
                    {
                        System.out.println(0);
                        return;
                    }
                    else if (cChar != '#')
                    {
                        cs[tx][ty] = '#';
                        queue.add(new Triple(tx, ty, p.z + 1));
                    }
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
