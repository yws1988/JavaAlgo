package graph.connectivity;
/*
 Give n value ranges, for range A {x1, y1} and range B {x2, y2}, if x1>=x2 && x1<=y2 or
 y1>=x2 && y1<=y2, we consider the two ranges are intersected. Calculate the peak of intersections,
 that is to say the maximum number of the ranges contains the same intersection.
 input intersection matrix like this:
    1 1 0 1
    1 1 1 1
    0 1 1 0
    1 1 0 1
 if (i, j) element is 1, it means ith Range is intersectant with jth Range.

 Expected Output is 3
 */
public class MaxCliqueOfRanges
{
    public static int getMaxClique(int[][] ns)
    {
        int n = ns.length;

        var visited = new boolean[n];
        var visitedNeighbours = new int[n];

        int maxclique = 0;

        for (int i = 0; i < n; i++)
        {
            int u = -1;
            int m = -1;
            for (int j = 0; j < n; j++)
            {
                if (!visited[j] && visitedNeighbours[j] > m)
                {
                    u = j;
                    m = visitedNeighbours[j];
                }
            }

            visited[u] = true;

            for (int v = 0; v < n; v++)
            {
                if (ns[u][v] == 1)
                visitedNeighbours[v]++;
            }

            maxclique = Math.max(maxclique, visitedNeighbours[u]);
        }

        return maxclique;
    }
}
