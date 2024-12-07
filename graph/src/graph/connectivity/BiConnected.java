package graph.connectivity;


import java.util.*;

public class BiConnected {
    public static int Step;

    public static boolean isGraphBiConnected(List<Integer>[] graph) {
        Step = 0;
        int v = graph.length;
        boolean[] visited = new boolean[v];
        int[] parents = new int[v];
        int[] dist = new int[v];
        int[] low = new int[v];
        for (int i = 0; i < v; i++) {
            parents[i] = -1;
        }

        return dfsComponents(0, graph, visited, dist, low, parents);
    }

    public static boolean dfsComponents(int s, List<Integer>[] graph, boolean[] vs, int[] dist, int[] low, int[] parents) {
        vs[s] = true;
        dist[s] = ++Step;
        low[s] = Step;

        int child = 0;
        for (var c : graph[s]) {
            if (!vs[c]) {
                child++;
                parents[c] = s;
                if (!dfsComponents(c, graph, vs, dist, low, parents)) return false;
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

    static ArrayList<ArrayList<Edge>> BiConnectedCompenents = new ArrayList<ArrayList<Edge>>();

    public static void getBiconnectedComponents(List<Integer>[] graph) {
        Step = 0;
        int v = graph.length;
        boolean[] visited = new boolean[v];
        int[] parents = new int[v];
        int[] dist = new int[v];
        int[] low = new int[v];
        for (int i = 0; i < v; i++) {
            parents[i] = -1;
        }
        var edges = new Stack<Edge>();

        dfsComponents(edges, 0, graph, visited, dist, low, parents);
    }

    public static void pushComponent(int s, int d, Stack<Edge> edges) {
        ArrayList<Edge> biComponents = new ArrayList<>();

        Edge edge;
        do {
            edge = edges.pop();
            biComponents.add(edge);
        } while (s != edge.src || d != edge.des);

        BiConnectedCompenents.add(biComponents);
    }

    public static void dfsComponents(Stack<Edge> edges, int s, List<Integer>[] graph, boolean[] visited, int[] dist, int[] low, int[] parents) {
        visited[s] = true;
        dist[s] = ++Step;
        low[s] = Step;

        int child = 0;
        for (var c : graph[s]) {
            if (!visited[c]) {
                child++;
                parents[c] = s;
                edges.push(new Edge(s, c));
                dfsComponents(edges, c, graph, visited, dist, low, parents);
                low[s] = Math.min(low[c], low[s]);
                if (parents[s] == -1 && child > 1) {
                    pushComponent(s, c, edges);
                }

                if (parents[s] != -1 && low[c] >= dist[s]) {
                    pushComponent(s, c, edges);
                }
            } else if (parents[s] != c) {
                if (dist[s] > dist[c]) {
                    edges.push(new Edge(s, c));
                }

                low[s] = Math.min(dist[c], low[s]);
            }
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

    /*** Create a new graph with vertex represents BiConnectedComponent ***/

    public static int step;
    public static boolean[] areArticulationPoints;
    public static HashMap<Integer, Set<Integer>> componentsByArticulationPoint = new HashMap<>();
    public static Stack<Edge> stackComponents = new Stack<>();
    public static int[] biConnectedComponents;

    public static HashSet<Integer>[] getBiConnectedComponentGraph(HashSet<Integer>[] graph)
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

        var listGraph = new ArrayList[graph.length];

        for (int i = 0; i < graph.length; i++) {
            listGraph[i] = new ArrayList(graph[i]);
        }

        DfsWithStack(listGraph, visited, dist, low, parents);

        return createBiComponentGraph();
    }

    static int root=0;

    static void DfsWithStack(List<Integer>[] graph, boolean[] visited, int[] dist, int[] low, int[] parents)
    {
        Stack<Node> stackVertex = new Stack<Node>();
        stackVertex.push(new Node(0, 0));
        visited[root] = true;
        dist[root] = ++step;
        low[root] = step;
        int child = 0;

        while (stackVertex.size() > 0)
        {
            var currentNode = stackVertex.peek();
            var node = currentNode.node;
            var childIndex = currentNode.childIndex;

            boolean isPop = true;

            for (int i = childIndex; i < graph[node].size(); i++) {
                int childNode = graph[node].get(i);
                currentNode.setChildIndex(i+1);
                if(!visited[childNode]){
                    visited[childNode] = true;
                    dist[childNode] = ++step;
                    low[childNode] = step;

                    parents[childNode]=node;
                    stackVertex.push(new Node(childNode, 0));
                    stackComponents.push(new Edge(node, childNode));
                    low[node] = Math.min(dist[childNode], low[node]);
                    isPop = false;
                    break;
                }else if(parents[node]!=childNode){
                    if(dist[node]>dist[childNode]){
                        stackComponents.push(new Edge(node, childNode));
                    }

                    low[node] = Math.min(dist[childNode], low[node]);
                }
            }

            if (isPop)
            {
                currentNode = stackVertex.pop();
                node = currentNode.node;

                if(node!=root){
                    int parentNode = parents[node];
                    low[parentNode]=Math.min(low[parentNode], low[node]);
                }

                if(parents[node] == root){
                    child++;
                    if(child>1){
                        areArticulationPoints[root]=true;
                        assignComponentId(root, node);
                    }
                }else if(node!=root && low[node]>=dist[parents[node]]){
                    areArticulationPoints[parents[node]]=true;
                    assignComponentId(parents[node], node);
                }
            }
        }

        if(stackComponents.size()>0){
            assignComponentId(-1, root);
        }
    }

    public static int ArticulationPointComponentId;
    static HashSet<Integer>[] createBiComponentGraph()
    {
        ArticulationPointComponentId = componentId;

        for (int i : componentsByArticulationPoint.keySet()) {
            biConnectedComponents[i]=componentId++;
        }

        var newGraph = new HashSet[componentId];

        for (int i = 0; i < componentId; i++) {
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

    public static int componentId = 0;
    private static void assignComponentId(int src, int des){
        Edge edge = null;
        do{
            edge = stackComponents.pop();
            assignComponentId(edge.des);
            assignComponentId(edge.src);
        }while(stackComponents.size()>0 && (edge.src != src || edge.des != des));

        componentId++;
    }

    private static void assignComponentId(int vertexId) {
        biConnectedComponents[vertexId] = componentId;

        if(areArticulationPoints[vertexId]){
            if(!componentsByArticulationPoint.containsKey(vertexId)){
                componentsByArticulationPoint.put(vertexId, new HashSet<>());
            }

            componentsByArticulationPoint.get(vertexId).add(componentId);
        }
    }

    public static class Node {
        public int node;
        public int childIndex;

        public Node(int x, int y) {
            this.node = x;
            this.childIndex = y;
        }

        public void setChildIndex(int childIndex) {
            this.childIndex = childIndex;
        }
    }
}


