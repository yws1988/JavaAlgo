package graph.path;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class FloodFillMaximumAreaInMatrix
{
    public static int n;
    public static char[][] cs;

    public static void solve()
    {
        var queue = new LinkedList<Pair>();

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (cs[i][j] >= '0' && cs[i][j] <= '9')
                {
                    queue.add(new Pair(i, j));
                }
            }
        }

        int[] xMove = { 1, -1, 0, 0 };
        int[] yMove = { 0, 0, 1, -1 };

        while (queue.size()>0)
        {
            var p = queue.pop();
            int x = p.x;
            int y = p.y;
            char parentChar = cs[x][y];

            for (int u = 0; u < 4; u++)
            {
                int tx = xMove[u] + x;
                int ty = yMove[u] + y;

                if (!isSafe(tx, ty, n))
                {
                    continue;
                }

                char childChar = cs[tx][ty];

                if (childChar != '#' && childChar != '=' && childChar != parentChar)
                {
                    if (parentChar == '=' && childChar == '.')
                    {
                        cs[tx][ty] = '=';
                        queue.push(new Pair(tx, ty));
                    }
                    else if (parentChar != '=' && cs[tx][ty] == '.')
                    {
                        cs[tx][ty] = parentChar;
                        queue.push(new Pair(tx, ty));
                    }
                    else if (parentChar != '=' && cs[tx][ty] != '.')
                    {
                        cs[tx][ty] = '=';
                        queue.push(new Pair(tx, ty));
                    }
                }
            }
        }

        var dic = new HashMap<Character, Integer>();

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (cs[i][j] >= '0' && cs[i][j] <= '9')
                {
                    if (dic.containsKey(cs[i][j]))
                    {
                        dic.put(cs[i][j], cs[i][j]+1);
                    }
                    else
                    {
                        dic.put(cs[i][j], 1);
                    }
                }
            }
        }

        System.out.println(Collections.max(dic.values()));
    }

    static boolean isSafe(int i, int j, int v)
    {
        return i >= 0 && i < v && j >= 0 && j < v;
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
