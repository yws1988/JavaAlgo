package graph.traversal;
/*
Given a tree with n points and n-1 connections, start from any point x, do dfs transverse.
Return all the start points who can generate the most possible number of different traversal paths.
For example for a graph with 4 points.
1 2
2 3
3 4

The sequences that can be produced by
   Starting point 1:
     1،2،3،4

   Starting point 2:
     2،1،3،4
     2،3،4،1

   Starting point 3:
     3،2،1،4
     3،4،2،1

   Starting point 4:
     4،3،2،1

So the point 2 and 3 are the good start points
 */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StartPointsForMaximumDFSPaths {
    public static List<Integer> getPoints(List<Integer>[] tree){
        int max = Arrays.stream(tree).mapToInt(s -> s.size()).max().getAsInt();

        List<Integer> parents = IntStream.range(0, tree.length).filter(idx -> tree[idx].size() == max).boxed().collect(Collectors.toList());

        return parents;
    }
}
