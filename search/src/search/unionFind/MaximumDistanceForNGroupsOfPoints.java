package search.unionFind;

/*
There are N points in space, they are asked to form M distinct groups.
Make the distance between groups to be maximum.
(Any two points which are from different groups should be at least D distance apart.)
Output the maximun distance.
*/

import java.util.HashSet;

public class MaximumDistanceForNGroupsOfPoints
{
    static int max = 3000000;

    /// <summary>
    /// get maximum distance
    /// </summary>
    /// <param name="distances">the matrix present all the distances between points</param>
    /// <param name="m">number of Groups</param>
    /// <returns></returns>
    public static int getMaxDistance(int[][] distances, int m)
    {
        int n = distances.length;
        var subsets = UnionFind.createSubsets(n);

        int min = 0;
        int d = (min + max) / 2;

        while (max >= min)
        {
            for (int k = 0; k < n; k++)
            {
                subsets[k].parent = k;
                subsets[k].rank = 0;
            }

            for (int k = 0; k < n; k++)
            {
                for (int h = k + 1; h < n; h++)
                {
                    if (distances[k][h] < d)
                    {
                        UnionFind.union(subsets, k, h);
                    }
                }
            }

            var set = new HashSet<Integer>();

            for (int k = 0; k < n; k++)
            {
                set.add(UnionFind.find(subsets, k));
            }

            int numOfGroup = set.size();

            if (numOfGroup >= m)
            {
                min = d + 1;
            }
            else
            {
                max = d - 1;
            }

            d = (min + max) / 2;
        }

        return max;
    }
}
