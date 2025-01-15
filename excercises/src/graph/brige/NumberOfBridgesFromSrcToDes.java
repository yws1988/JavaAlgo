package graph.brige;
/*
Given a connected graph with n nodes and m edges, where m <= n.You are also given two nodes src and des of the graph.
You need to find the number of briges which are present in the paths between src and des.

Input format:
5 5
2 5
1 3
3 5
3 4
2 3
1 2

The First line contains two space separated integers N and M, the number of nodes and edges respectively.
The second line contains two space separated integers a and b.
Then m lines follow, the i-th line contains two integers xi and yi the endpoints of the i-th edge.

OUTPUT FORMAT :
The output should contain a single number which is the number of bridges present in the path between src and des.
1
The edge (3,5) lies in all paths between node 2 and 5. This is the only edge which occurs in every path between 2 and 5.
 */

import graph.connectivity.bridge.Briges;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class NumberOfBridgesFromSrcToDes
{
    public static int solve(List<Integer>[] graph, int src, int des)
    {
//        int[][] edges = readIntMatrix(m);
//        List<Integer>[] graph = GraphBuilder.buildListArray(n, edges, false);

        int n = graph.length;
        List<Briges.Edge> briges = Briges.getAllBriges(graph);

        var sets = new HashSet<List>();

        for (var edge : briges) {
            sets.add(List.of(edge.src, edge.des));
            sets.add(List.of(edge.des, edge.src));
        }

        var queue = new LinkedList<datastructures.tuple.Pair>();
        queue.add(new datastructures.tuple.Pair(src, 0));

        boolean[] vs = new boolean[n];

        while (queue.size() > 0)
        {
            var parent = queue.pop();
            for (var child : graph[parent.x])
            {
                if (!vs[child])
                {
                    var edge = List.of(parent.x, child);
                    if (child == des){
                       return sets.contains(edge) ? parent.y + 1 : parent.y;
                    }else{
                        vs[child] = true;
                        queue.add(new datastructures.tuple.Pair(child, sets.contains(edge) ? parent.y + 1 : parent.y));
                    }
                }
            }
        }

        return -1;
    }
}

