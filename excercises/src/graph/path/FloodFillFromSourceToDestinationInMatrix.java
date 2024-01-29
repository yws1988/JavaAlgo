package graph.path;

public class FloodFillFromSourceToDestinationInMatrix
{
    public static int n;
    public static char[][] cs;

    public static void solve()
    {
        int srcx = 0, srcy = 0;
        var queue = new Queue<(int, int, int)>();

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (cs[i][j] == 'M')
                {
                    queue.Enqueue((i, j, 0));
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

        queue.Enqueue((srcx, srcy, 0));

        while (queue.Any())
        {
            var p = queue.Dequeue();
            int x = p.Item1;
            int y = p.Item2;
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
                        queue.Enqueue((tx, ty, p.Item3 + 1));
                    }
                    else if (cChar == 'O')
                    {
                        Console.WriteLine(p.Item3 + 1);
                        return;
                    }
                }
                else
                {
                    if (cChar == 'O')
                    {
                        Console.WriteLine(0);
                        return;
                    }
                    else if (cChar != '#')
                    {
                        cs[tx][ty] = '#';
                        queue.Enqueue((tx, ty, p.Item3 + 1));
                    }
                }
            }
        }
    }
}
