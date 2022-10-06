package unionFind.excercises;

import unionFind.UnionFind;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
You are given number of nodes N, and number of edges M of an undirected connected graph.
After adding all the edges, print the size of all the connected components (in increasing order).

Input:
First line contains two integers, N and M, number of nodes and number of edges.
Next M lines contains two integers each, X and Y, denoting that there is an edge between X and Y.
 */
public class ConnectedComponentsSizeInGraph {
    public static int n, m;

    public static List<Integer> getConnectedComponentSizes(int[][] lines){
        var subsets = UnionFind.createSubsets(n);
        for (int i = 0; i < m; i++) {
            int a = lines[i][0];
            int b = lines[i][1];
            UnionFind.union(subsets, a, b);
        }

        return Arrays.stream(subsets).filter(s-> s.size > 0).map(s -> s.size).sorted().collect(Collectors.toList());
    }
}
