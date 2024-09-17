package utils;

import java.io.File;
import java.util.*;

public class TwoPath
{
    public static int n,m,q;
    public static int[] ns;
    public static String s;
    public static String[] ss;

    public static void solve()
    {
        int[][] tmp = readIntMatrix(m);
        List<EdgeWithWeight>[] graph = buildListArrayWithWeight(n, tmp, false);

        int[] components = new int[n];
        HashSet<Integer>[] componentGraph = transformConnectedGraph(graph, components);

        int[] biConnectedComponents = getBiConnectedComponents(componentGraph);
        HashSet<Integer>[] biComponentGraph = getComponentGraphWithComponentsIds(componentGraph, biConnectedComponents);

        for (int i = 0; i < q; i++) {
            var points = readIntArray();
            int a = biConnectedComponents[components[points[0]-1]];
            int b = biConnectedComponents[components[points[1]-1]];
            int c = biConnectedComponents[components[points[2]-1]];
            int d = biConnectedComponents[components[points[3]-1]];

            if(a==b){
                System.out.println("Yes");
                continue;
            }


        }

    }

    public static HashSet<Integer>[] getComponentGraphWithComponentsIds(HashSet<Integer>[] graph, int[] components)
    {
        int numOfComponents = Arrays.stream(components).max().getAsInt()+1;

        var newGraph = new HashSet[numOfComponents];

        for (int i = 0; i < numOfComponents; i++) {
            newGraph[i] = new HashSet<Integer>();
        }

        for (int p = 0; p < graph.length; p++) {
            for (var c : graph[p]) {
                if(components[p]!=components[c]){
                    newGraph[components[p]].add(components[c]);
                }
            }
        }

        return newGraph;
    }

    public static int[] getBiConnectedComponents(HashSet<Integer>[] graph) {
        step = 0;
        int v = graph.length;
        boolean[] visited = new boolean[v];
        int[] parents = new int[v];
        int[] dist = new int[v];
        int[] low = new int[v];
        int[] components = new int[v];
        for (int i = 0; i < v; i++) {
            parents[i] = -1;
        }

        dfsComponents(0, graph, visited, dist, low, parents, components);

        return components;
    }

    public static int step = 0;
    public static int componentId = 0;
    public static void dfsComponents(int s, HashSet<Integer>[] graph, boolean[] vs, int[] dist, int[] low, int[] parents, int[] components) {
        vs[s] = true;
        dist[s] = ++step;
        low[s] = step;

        int child = 0;
        for (var c : graph[s]) {
            if (!vs[c]) {
                child++;
                components[c] = componentId;
                parents[c] = s;
                dfsComponents(c, graph, vs, dist, low, parents, components);
                low[s] = Math.min(low[c], low[s]);
                if (parents[s] == -1 && child > 1) {
                    components[s] = ++componentId;
                    componentId++;
                } else if (parents[s] != -1 && low[c] >= dist[s]) {
                    components[s] = ++componentId;
                    componentId++;
                }
            } else if (parents[s] != c) {
                low[s] = Math.min(dist[c], low[s]);
            }
        }
    }


    public static List<EdgeWithWeight>[] buildListArrayWithWeight(int n, int[][] arr, boolean isDirected)
    {
        var graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<EdgeWithWeight>();
        }

        for (var item : arr)
        {
            int src = item[0];
            int des = item[1];
            int weight = item[2];
            graph[src].add(new EdgeWithWeight(src, des, weight));

            if (!isDirected)
            {
                graph[des].add(new EdgeWithWeight(des, src, weight));
            }
        }

        return graph;
    }

    public static HashSet<Integer>[] transformConnectedGraph(List<EdgeWithWeight>[] graph, int[] components)
    {
        int v = graph.length;
        boolean[] vs = new boolean[v];
        Arrays.fill(components, -1);

        int componentId = 0;

        for (int i = 0; i < v; i++) {
            if(!vs[i]){
                bfs(graph, i, components, componentId, vs);
                componentId++;
            }
        }

        var newGraph = new HashSet[componentId];

        for (int i = 0; i < componentId; i++) {
            newGraph[i] = new HashSet();
        }

        for (int i = 0; i < v; i++) {
            for (var edge : graph[i]) {
                if(components[i]!=components[edge.des]){
                    newGraph[components[i]].add(components[edge.des]);
                }
            }
        }

        return newGraph;
    }

    public static void bfs(List<EdgeWithWeight>[] graph, int src, int[] components, int componentId, boolean[] vs)
    {
        var queue = new LinkedList<Integer>();
        queue.add(src);

        while (queue.size() > 0)
        {
            int next = queue.poll();
            vs[next] = true;
            components[next] = componentId;

            for (var c : graph[next])
            {
                if (!vs[c.des] && c.weight==1)
                {
                    queue.add(c.des);
                }
            }
        }
    }

    public static Scanner scanner;

    public static void main( String[] argv ) throws Exception
    {
        if(true){
            File file = new File("D:\\Algo\\JavaAlgo\\excercises\\src\\resources\\test.txt");
            scanner = new Scanner(file);
        }else{
            scanner = new Scanner(System.in);
        }

        ns = readIntArray();
        n=ns[0];
        m=ns[1];
        q=ns[2];

        solve();
        scanner.close();
    }

    public static int readInt() { int tmp = scanner.nextInt(); scanner.nextLine(); return tmp;}
    public static int[] readIntArray() { return Arrays.stream(readStringArray()).mapToInt(s->Integer.parseInt(s)).toArray(); }
    public static String readString() { return scanner.nextLine(); }
    public static String[] readStringArray() { return scanner.nextLine().split("[ \t]"); }
    public static String[] readLines(int quantity) { String[] lines = new String[quantity]; for (int i = 0; i < quantity; i++) lines[i] = scanner.nextLine().trim(); return lines; }
    public static int[][] readIntMatrix(int numberOfRows) { int[][] matrix = new int[numberOfRows][]; for (int i = 0; i < numberOfRows; i++){matrix[i] = readIntArray(); matrix[i][0]--;matrix[i][1]--;} return matrix; }

    public static class EdgeWithWeight implements Comparable<EdgeWithWeight>{
        public int src;
        public int des;
        public int weight;

        public EdgeWithWeight(int src, int des, int weight) {
            this.src = src;
            this.des = des;
            this.weight = weight;
        }

        @Override
        public int compareTo(EdgeWithWeight o) {
            return this.weight-o.weight;
        }
    }

}

