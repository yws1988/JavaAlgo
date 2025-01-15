package graph.connectivity.articulationPoint;

import java.util.*;

public class BiConnected {

    /*** To verify if graph is bi connected graph ***/
    static int step, root;

    public static boolean isGraphBiConnected(List<Integer>[] graph) {
        step = 0;
        int v = graph.length;
        boolean[] visited = new boolean[v];
        int[] parents = new int[v];
        int[] dist = new int[v];
        int[] low = new int[v];
        for (int i = 0; i < v; i++) {
            parents[i] = -1;
        }

        return dfsIsBiConnected(root, graph, visited, dist, low, parents);
    }

    public static boolean dfsIsBiConnected(int s, List<Integer>[] graph, boolean[] vs, int[] dist, int[] low, int[] parents) {
        vs[s] = true;
        dist[s] = ++step;
        low[s] = step;

        int child = 0;
        for (var c : graph[s]) {
            if (!vs[c]) {
                child++;
                parents[c] = s;
                if (!dfsIsBiConnected(c, graph, vs, dist, low, parents)) return false;
                low[s] = Math.min(low[c], low[s]);
                if (parents[s] == -1 && child > 1) {
                    return false;
                }

                if (parents[s] != -1 && low[c] >= dist[s]) {
                    return false;
                }
            } else if (parents[s] != c) {
                low[s] = Math.min(dist[c], low[s]);
            }
        }

        return true;
    }

    /*** Get all the bi connected components ***/

    static Stack<Edge> edges = new Stack<>();
    public static ArrayList<ArrayList<Edge>> biConnectedComponentsList = new ArrayList<ArrayList<Edge>>();

    public static void getBiConnectedComponents(Collection<Integer>[] graph) {
        step = 0;
        int v = graph.length;
        boolean[] visited = new boolean[v];
        int[] parents = new int[v];
        int[] dist = new int[v];
        int[] low = new int[v];
        for (int i = 0; i < v; i++) {
            parents[i] = -1;
        }

        dfsBiConnectedComponents(root, graph, visited, dist, low, parents);

        if(edges.size()>0){
            biConnectedComponentsList.add(new ArrayList<>(edges));
        }
    }

    public static void dfsBiConnectedComponents(int s, Collection<Integer>[] graph, boolean[] visited, int[] dist, int[] low, int[] parents) {
        visited[s] = true;
        dist[s] = ++step;
        low[s] = step;

        int child = 0;
        for (var c : graph[s]) {
            if (!visited[c]) {
                child++;
                parents[c] = s;
                edges.push(new Edge(s, c));
                dfsBiConnectedComponents(c, graph, visited, dist, low, parents);
                low[s] = Math.min(low[c], low[s]);
                if (parents[s] == -1 && child > 1) {
                    popComponentList(s, c);
                }

                if (parents[s] != -1 && low[c] >= dist[s]) {
                    popComponentList(s, c);
                }
            } else if (parents[s] != c) {
                if (dist[s] > dist[c]) {
                    edges.push(new Edge(s, c));
                }

                low[s] = Math.min(dist[c], low[s]);
            }
        }
    }

    public static void popComponentList(int s, int d) {
        ArrayList<Edge> biComponents = new ArrayList<>();

        Edge edge;
        do {
            edge = edges.pop();
            biComponents.add(edge);
        } while (s != edge.src || d != edge.des);

        biConnectedComponentsList.add(biComponents);
    }

    /* build BiConnectedComponentGraph */

    static int totalComponent;
    public static boolean[] areArticulationPoints;
    public static HashMap<Integer, Set<Integer>> componentsByArticulationPoint = new HashMap<>();
    public static int[] biConnectedComponents;

    public static HashSet<Integer>[] buildBiConnectedComponentGraph(Collection<Integer>[] graph)
    {
        step = 0;
        int v = graph.length;
        biConnectedComponents = new int[v];
        areArticulationPoints = new boolean[v];
        boolean[] visited = new boolean[v];
        int[] parents = new int[v];
        int[] dist = new int[v];
        int[] low = new int[v];
        for (int i = 0; i < v; i++)
        {
            parents[i] = -1;
            biConnectedComponents[i]=-1;
        }

        dfsToBuildBiComponentGraph(root, graph, visited, dist, low, parents);
        if(edges.size()>0){
            assignComponentId(-1, root);
        }

        return createBiComponentGraph();
    }

    public static int articulationPointComponentId;
    static HashSet<Integer>[] createBiComponentGraph()
    {
        articulationPointComponentId = totalComponent;

        for (int i : componentsByArticulationPoint.keySet()) {
            biConnectedComponents[i]= totalComponent++;
        }

        var newGraph = new HashSet[totalComponent];

        for (int i = 0; i < totalComponent; i++) {
            newGraph[i] = new HashSet();
        }

        componentsByArticulationPoint.entrySet().forEach(entry -> {
            entry.getValue().forEach(componentId -> {
                newGraph[biConnectedComponents[entry.getKey()]].add(componentId);
                newGraph[componentId].add(biConnectedComponents[entry.getKey()]);
            });
        });

        return newGraph;
    }

    public static void dfsToBuildBiComponentGraph(int s, Collection<Integer>[] graph, boolean[] visited, int[] dist, int[] low, int[] parents) {
        visited[s] = true;
        dist[s] = ++step;
        low[s] = step;

        int child = 0;
        for (var c : graph[s]) {
            if (!visited[c]) {
                child++;
                parents[c] = s;
                edges.push(new Edge(s, c));
                dfsToBuildBiComponentGraph(c, graph, visited, dist, low, parents);
                low[s] = Math.min(low[c], low[s]);
                if (parents[s] == -1 && child > 1) {
                    areArticulationPoints[s]=true;
                    assignComponentId(s, c);
                }

                if (parents[s] != -1 && low[c] >= dist[s]) {
                    areArticulationPoints[s]=true;
                    assignComponentId(s, c);
                }
            } else if (parents[s] != c) {
                if (dist[s] > dist[c]) {
                    edges.push(new Edge(s, c));
                }

                low[s] = Math.min(dist[c], low[s]);
            }
        }
    }

    private static void assignComponentId(int src, int des){
        Edge edge = null;
        do{
            edge = edges.pop();
            assignComponentId(edge.des);
            assignComponentId(edge.src);
        }while(edges.size()>0 && (edge.src != src || edge.des != des));

        totalComponent++;
    }

    private static void assignComponentId(int vertexId) {
        biConnectedComponents[vertexId] = totalComponent;

        if(areArticulationPoints[vertexId]){
            if(!componentsByArticulationPoint.containsKey(vertexId)){
                componentsByArticulationPoint.put(vertexId, new HashSet<>());
            }

            componentsByArticulationPoint.get(vertexId).add(totalComponent);
        }
    }


    public static class Edge {
        public int src;
        public int des;

        public Edge(int s, int d) {
            src = s;
            des = d;
        }
    }
}


