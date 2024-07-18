package graph.tree;

/*
Given a tree with n point, each point has a cost weight, given a point src, start from this point,
go through a point p, find the shortest path to one point that the shortest path cost is between x and y,
return the index of the first point satisfy this condition.
 */

import utils.CollectionHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class ShortestPathToDestinationThroughOnePoint {
    public static int n, src, dis = 0;
    public static long[] costs;

    public static int solve(List<Integer>[] graph, int point, long x, long y) {

        var visited = new boolean[n];
        var queue = new PriorityQueue<Pair>(new PairComparator());

        List<Pair> pairs = new ArrayList<>();
        int indexOfPoint = 0;
        long costOfPoint = 0;
        var startPair = new Pair(src, 0);
        queue.add(startPair);

        while (queue.size() > 0) {
            var nodeParent = queue.poll();
            int s = nodeParent.index;
            if (visited[s]) continue;
            visited[s] = true;

            if (nodeParent.index == point) {
                indexOfPoint = pairs.size();
                costOfPoint = nodeParent.cost;
            }
            pairs.add(nodeParent);

            for (int i = 0; i < graph[s].size(); i++) {
                int cNode = graph[s].get(i);
                if (!visited[cNode]) {
                    queue.add(new Pair(cNode, nodeParent.cost + costs[cNode]));
                }
            }
        }

        var costOfAllPoints = pairs.stream().map(p -> p.cost).collect(Collectors.toList());

        int indexOfX = CollectionHelper.binarySearch(costOfAllPoints, x);

        if (indexOfX == -1) {
            return 0;
        }

        if (indexOfX >= indexOfPoint) {
            return indexOfX + 1;
        }

        if (costOfPoint > y) {
            return 0;
        }

        return indexOfPoint + 1;
    }

    public static class Pair {
        public long cost;

        public int index;

        public Pair(int index, long cost) {
            this.index = index;
            this.cost = cost;
        }
    }

    public static class PairComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair o1, Pair o2) {
            return (int) Math.signum(o1.cost - o2.cost);
        }
    }
}
