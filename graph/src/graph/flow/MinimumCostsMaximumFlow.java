package graph.flow;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MinimumCostsMaximumFlow {
    private final int V; // number of vertices
    private final int INF = Integer.MAX_VALUE / 2;
    private int[][] capacity, cost, flow;
    private int[] dist, parent;
    private boolean[] isInQueue;

    public MinimumCostsMaximumFlow(int vertices) {
        this.V = vertices;
        capacity = new int[V][V];
        cost = new int[V][V];
        flow = new int[V][V];
        dist = new int[V];
        parent = new int[V];
        isInQueue = new boolean[V];
    }

    // Add an edge to the flow network
    public void addEdge(int from, int to, int cap, int edgeCost) {
        capacity[from][to] = cap;
        cost[from][to] = edgeCost;
        cost[to][from] = -edgeCost; // Add reverse edge with negative cost
    }

    // Shortest Path Faster Algorithm (SPFA) for finding negative cost paths
    private boolean spfa(int source, int sink) {
        Arrays.fill(dist, INF);
        Arrays.fill(parent, -1);
        Arrays.fill(isInQueue, false);

        dist[source] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        isInQueue[source] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            isInQueue[u] = false;

            for (int v = 0; v < V; v++) {
                if (capacity[u][v] - flow[u][v] > 0 &&
                        dist[v] > dist[u] + cost[u][v]) {
                    dist[v] = dist[u] + cost[u][v];
                    parent[v] = u;
                    if (!isInQueue[v]) {
                        queue.offer(v);
                        isInQueue[v] = true;
                    }
                }
            }
        }

        return dist[sink] != INF;
    }

    // Find minimum cost maximum flow
    public Result findMinCostMaxFlow(int source, int sink) {
        int totalCost = 0;
        int totalFlow = 0;

        while (spfa(source, sink)) {
            // Find minimum flow on the augmenting path
            int pathFlow = INF;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, capacity[u][v] - flow[u][v]);
            }

            // Update flow and cost
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                flow[u][v] += pathFlow;
                flow[v][u] -= pathFlow;
                totalCost += pathFlow * cost[u][v];
            }

            totalFlow += pathFlow;
        }

        return new Result(totalFlow, totalCost);
    }

    // Clear all flows in the network
    public void clearFlow() {
        flow = new int[V][V];
    }

    // Get the current flow on an edge
    public int getFlow(int from, int to) {
        return flow[from][to];
    }

    // Result class to hold both flow and cost
    public static class Result {
        public final int maxFlow;
        public final int minCost;

        public Result(int maxFlow, int minCost) {
            this.maxFlow = maxFlow;
            this.minCost = minCost;
        }
    }
}
