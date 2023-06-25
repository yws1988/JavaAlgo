package graph.flow;

/*
    Give a matrix NxN. Our goal is to cross over all the 1s in the matrix.
    Get the minimum number of path to reach (m, n) from (0, 0).
    Each path can only transverse down, right and diagonally lower cells from a given cell,
    for example, from a given cell (i, j), cells (i+1, j), (i, j+1) and (i+1, j+1) can be traversed.

    Example matrix:

    0,0,1,0,0,1,0,0,0,1,
    0,0,1,0,0,1,0,0,0,1,
    0,0,1,0,0,1,0,0,0,1,
    0,0,1,0,0,1,0,0,0,1,
    0,0,1,0,0,1,0,0,0,1,
    0,0,1,0,0,1,0,0,0,1,
    0,0,1,0,0,1,0,0,0,1,
    0,0,1,0,0,1,0,0,0,1,
    0,0,1,0,0,1,0,0,0,1,
    0,0,1,0,0,1,0,0,0,1,

    Output 3 paths can transverse all the 1 points
*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MinimumPathsToTransverseAllPointsInGrid{
    public static int getMinimumNumberOfPaths(int[][] matrix)
    {
        int m = matrix.length;
        int n = matrix[0].length;

        var list = new ArrayList<Pair>();
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (matrix[i][j] == 1)
                {
                    list.add(new Pair(i, j));
                }
            }
        }

        int nn = list.size();

        var matches = new ArrayList[nn];
        for (int i = 0; i < nn; i++) {
            matches[i] = new ArrayList<>();
        }

        for (int i = 0; i < nn; i++)
        {
            for (int j = 0; j < nn; j++)
            {
                if (i != j && list.get(i).x <= list.get(j).x && list.get(i).y <= list.get(j).y)
                {
                    matches[i].add(j);
                }
            }
        }

        var matchesParents = new int[nn];

        int maxMatching = MaximumBiPartiteGraph.getMaximumBitPartiteMatching(matches, matchesParents);

        return nn - maxMatching;
    }

    public static class Pair{
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pair clone(){
            return new Pair(x, y);
        }
    }

    public static class PairComparator implements Comparator<Pair> {

        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.x-o2.x;
        }
    }
}
