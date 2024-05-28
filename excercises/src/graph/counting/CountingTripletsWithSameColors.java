package graph.counting;

import datastructures.tuple.Pair;

import java.util.List;

public class CountingTripletsWithSameColors {
    public static long getNumOfTriples(int n, List<Pair> edges){
        long[] degree = new long[n];

        for (var edge : edges) {
            degree[edge.x]++;
            degree[edge.y]++;
        }

        long numOfMixedColorTriplets = 0;
        for (int i = 1; i < n; i++)
        {
            numOfMixedColorTriplets += degree[i] * (n- 2 - degree[i]);
        }

        long numOfSameColorTriplets = (n-1)*(n-2)*(n-3)/6 - numOfMixedColorTriplets/2;
        return numOfSameColorTriplets;
    }


}
