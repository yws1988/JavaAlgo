package graph.path;

import utils.graph.GraphLIstBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/*
Given a chess board with the size N*M
A few of these cells have Horses placed over them.
Each horse is unique. Now these horses are not the usual horses which could jump to any of the 8 positions
they usually jump in. They can move only if there is another horse on one of the 8-positions that it can
go to usually and then both the horses will swap their positions. This swapping can happen infinitely times.

A photographer was assigned to take a picture of all the different ways that the horses occupy the board!
Given the state of the board, calculate answer. Since this answer may be quite large, calculate in modulo
 */
public class ConnectedHouseInChessBoard {
    public static int[] ns;
    public static int n, m;
    static List<Integer>[] graph;
    static int[] dx = { 1, 2, 2, 1, -1, -2, -2, -1 };
    static int[] dy = { 2, 1, -1, -2, -2, -1, 1, 2 };
    long mod = (long)Math.pow(10, 9)+7;

    public long getNumberOfHorsePermutation(HashSet<Integer> horses)
    {
        graph = GraphLIstBuilder.buildListArray(n*m);

        for (var p : horses)
        {
            int tx = p/m;
            int ty = p%m;

            for (int j = 0; j < 8; j++)
            {
                int cx = tx + dx[j];
                int cy = ty + dy[j];
                int pa = cx * m + cy;
                if (isSafe(cx, cy) && horses.contains(pa))
                {
                    graph[p].add(pa);
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        boolean[] vs = new boolean[n*m];

        for (var p : horses)
        {
            if (!vs[p])
            {
                res.add(bfs(p, vs));
            }
        }

        long sum = 1;

        for (int r : res)
        {
            for (int i = 2; i <= r; i++)
            {
                sum = ((sum % mod )* i) % mod ;
            }
        }

        return sum;
    }

    static int bfs(int r, boolean[] vs)
    {
        vs[r] = true;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(r);
        int res = 0;
        while (queue.size() > 0)
        {
            res++;
            int p=queue.pop();
            for (var c : graph[p])
            {
                if (!vs[c])
                {
                    queue.add(c);
                    vs[c] = true;
                }
            }
        }

        return res;
    }

    static boolean isSafe(int x, int y)
    {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
