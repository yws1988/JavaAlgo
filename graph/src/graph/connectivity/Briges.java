package graph.connectivity;

import java.util.*;

public class Briges
{
    public static int step;

    public static List<Edge> getAllBriges(List<Integer>[] graph)
    {
        var briges = new ArrayList<Edge>();
        step = 0;
        int v = graph.length;
        boolean[] visited = new boolean[v];
        int[] parents = new int[v];
        int[] dist = new int[v];
        int[] low = new int[v];

        for (int i = 0; i < v; i++)
        {
            parents[i] = -1;
        }

        DFS(0, graph, visited, dist, low, parents, briges);

        return briges;
    }

    public static void DFS(int s, List<Integer>[] graph, boolean[] vs, int[] dist, int[] low, int[] parents, List<Edge> briges)
    {
        vs[s] = true;
        dist[s] = ++step;
        low[s] = step;

        for (var c : graph[s])
        {
            if (!vs[c])
            {
                parents[c] = s;
                DFS(c, graph, vs, dist, low, parents, briges);
                low[s] = Math.min(low[c], low[s]);

                if (low[c] > dist[s])
                {
                    briges.add(new Edge(s, c));
                }
            }
            else if (parents[s] != c)
            {
                low[s] = Math.min(dist[c], low[s]);
            }
        }
    }

    public static class Edge
    {
        public int src;
        public int des;

        public Edge(int s, int d)
        {
            src = s;
            des = d;
        }

    }

    /**
     * Create the bridge tree, the tree vertex id is the component id
     * DFS method
     */

    public static int root=0;
    static int[] dist, low, componentIds, parents, depth;
    static int time, totalComponentId;
    public static ArrayList<Integer> bridgeTree[];
    public static void buildBridgeTree(List<Integer>[] graph){
        int n = graph.length;
        boolean vs[] = new boolean[n];
        totalComponentId = 1;
        componentIds = new int[n + 1];
        parents = new int[n + 1];
        depth = new int[n + 1];
        dfs(graph, root, vs);
        bridgeTree = new ArrayList[n + 1];
        bridgeTree[1] = new ArrayList<Integer>();

        Arrays.fill(vs, false);
        buildBridgeTreeWithDfs(graph, root, totalComponentId, vs);
    }

    public static void dfs(List<Integer>[] graph, int s, boolean[] vs)
    {
        vs[s] = true;
        dist[s] = ++step;
        low[s] = step;

        for (var c : graph[s])
        {
            if (!vs[c])
            {
                parents[c] = s;
                dfs(graph, c, vs);
                low[s] = Math.min(low[c], low[s]);
            }
            else if (parents[s] != c)
            {
                low[s] = Math.min(dist[c], low[s]);
            }
        }
    }

    static void buildBridgeTreeWithDfs(List<Integer>[] graph, int curr, int componentId, boolean[] vs) {
        componentIds[curr] = componentId;
        vs[curr] = true;
        for (int next : graph[curr]) {
            if (!vs[next]) {
                if (low[next] > dist[curr]) {
                    totalComponentId++;
                    parents[totalComponentId] = componentId;
                    depth[totalComponentId] = depth[componentId] + 1;
                    bridgeTree[componentId].add(totalComponentId);
                    bridgeTree[totalComponentId] = new ArrayList<Integer>();
                    buildBridgeTreeWithDfs(graph, next, totalComponentId, vs);
                } else
                    buildBridgeTreeWithDfs(graph, next, componentId, vs);
            }
        }
    }
}
