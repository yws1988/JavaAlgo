package graph.connectivity;

//Given a graph G and an integer K, K-cores of the graph are connected components that
//are left after all vertices of degree less than k have been removed (Source wiki)

import graph.flow.MinimumPathsToTransverseAllPointsInGrid;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KCoreGraphUndirectedGraphPriorityQueueSolution {
    public static List<Integer> getKCoreVertex(List<Integer>[] graph, int k)
    {
        int v = graph.length;
        int[] degrees = new int[v];

        for (int i = 0; i < v; i++)
        {
            degrees[i] = graph[i].size();
        }

        var pairs = IntStream.range(0, v).mapToObj(s -> new Pair(s, degrees[s])).collect(Collectors.toList());

        var pq = new PriorityQueue<Pair>(new PairComparator());
        pq.addAll(pairs);
        var set = new HashSet<Integer>(IntStream.range(0, v).boxed().collect(Collectors.toList()));

        while(pq.size()>0 || set.size()>0){
            var pair = pq.poll();
            if(pair.y>=k){
                break;
            }

            if(set.contains(pair.x)){
                set.remove(pair.x);
                for (Integer y : graph[pair.x]) {
                     degrees[y]--;
                     if(degrees[y]>0){
                         pq.add(new Pair(y, degrees[y]));
                     }
                }
            }
        }

        if (set.size()==0){
            return null;
        }

        return new ArrayList<Integer>(set);
    }

    public static class Pair{
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class PairComparator implements Comparator<Pair> {

        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.y-o2.y;
        }
    }
}
