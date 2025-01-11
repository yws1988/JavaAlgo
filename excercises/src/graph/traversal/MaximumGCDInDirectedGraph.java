package graph.traversal;

/*
 Given a directed graph with weight, get the maximum value of path which equals

 First line contains two integers n, m number of graph's vertices and number of graph's edges.
 The following m lines contains u, v, w separated space describing graph's edge's vertices with weight

 6 8
 1 2 9
 2 3 7
 3 1 1
 2 4 5
 3 4 4
 4 5 6
 5 6 3
 6 4 10

 One of the optimal paths is: 2->3->1->2->4->5->6->4
 Which gives value equal to 10-1=9
 */


import utils.graph.GraphHelper;

import java.io.*;
import java.util.*;

public class MaximumGCDInDirectedGraph {
    public static BufferedReader bufferedReader;

    public static int n, m, numComponents, res=Integer.MAX_VALUE;
    static int[] w, gcd;
    static boolean[] vs;
    static Set<Integer>[] gcds;

    public static void solve() throws IOException {
        if(true){
            FileReader file = new FileReader("D:\\Algo\\JavaAlgo\\excercises\\src\\resources\\test.txt");
            bufferedReader = new BufferedReader(file);
        }else{
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        }

        int[] ns = readIntArray();
        n = ns[0];
        m = ns[1];

        w = new int[n + 1];

        ns = readIntArray();
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            w[i]= ns[i-1];
        }

        int[][] tmp = readIntMatrix(m);
        graph = buildListArray(n+1, tmp, true);

        int[] scc = getSCC(graph, false);
        gcd = new int[numComponents];
        for (int i = 1; i <= n; i++) {
            if(gcd[scc[i]]==0){
                gcd[scc[i]]=w[i];
            }else{
                gcd[scc[i]]=greatestCommonDivisor(w[i], gcd[scc[i]]);
            }
        }

        List<Integer>[] sccGraph = new ArrayList[numComponents];
        gcds = new Set[numComponents];

        for (int i = 0; i < numComponents; i++) {
            sccGraph[i] = new ArrayList<>();
            gcds[i] = new HashSet<>();
        }

        for (int i = 0; i < m; i++) {
            int u = scc[tmp[i][0]];
            int v = scc[tmp[i][1]];

            if(u!=v){
                sccGraph[u].add(v);
            }
        }

        vs = new boolean[numComponents];

        for (int i = 0; i < numComponents; i++) {
            if (!vs[i]) {
                dfs(graph, i);
            }
        }

        System.out.println(res);
        bufferedReader.close();
    }

    static void dfs(List<Integer>[] graph, int src) {
        vs[src] = true;
        for (var child : graph[src]) {
            if (!vs[child]) {
                dfs(graph, child);
                if(gcds[child].size()==0){
                    gcds[child].add(w[child]);
                    res = Math.min(res, w[child]);
                }else{
                    for (var item : gcds[child]) {
                        int parentGcd = greatestCommonDivisor(w[src], item);
                        gcds[src].add(parentGcd);
                        res = Math.min(parentGcd, res);
                    }
                }
            }
        }
    }

    public static int[] getSCC(List<Integer>[] graph, boolean useRootNodeAsComponentId)
    {
        var stack = new Stack<Integer>();
        int v = graph.length;

        boolean[] visited = new boolean[v];

        for (int i = 0; i < v; i++)
        {
            if (!visited[i])
            {
                DFS(i, visited, stack, graph);
            }
        }

        var rGraph = GraphHelper.getTransposeGraph(graph);

        int[] scc = new int[v];
        for (int i = 0; i < v; i++)
        {
            scc[i] = -1;
        }

        numComponents = 0;
        while (stack.size() > 0)
        {
            int i = stack.pop();
            if (scc[i]==-1)
            {
                DFSComponents(i, scc, rGraph, useRootNodeAsComponentId ? i : numComponents);
                numComponents++;
            }
        }

        return scc;
    }

    static void DFS(int s, boolean[] vs, Stack<Integer> stack, List<Integer>[] graph)
    {
        vs[s] = true;

        for (int c : graph[s])
        {
            if (!vs[c])
            {
                DFS(c, vs, stack, graph);
            }
        }

        stack.push(s);
    }

    static void DFSComponents(int s, int[] scc, List<Integer>[] graph, int num)
    {
        scc[s] = num;

        for (int c : graph[s])
        {
            if (scc[c]==-1)
            {
                DFSComponents(c, scc, graph, num);
            }
        }
    }

    public static int greatestCommonDivisor(int m, int n)
    {
        while (n > 0)
        {
            int t = n;
            n = m % n;
            m = t;
        }

        return m;
    }

    public static <T> List<T>[] buildListArray(int n, int[][] arr, boolean isDirected)
    {
        var graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<T>();
        }

        for (var item : arr)
        {
            int src = item[0];
            int des = item[1];
            graph[src].add(des);

            if (!isDirected)
            {
                graph[des].add(src);
            }
        }

        return graph;
    }


    public static void main(String[] argv) throws Exception {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    solve();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "1", 1 << 26).start();
    }

    public static int readInt() throws IOException { int tmp = Integer.parseInt(bufferedReader.readLine()); return tmp;}
    public static int[] readIntArray() throws IOException { return Arrays.stream(readStringArray()).mapToInt(Integer::parseInt).toArray(); }
    public static String readString() throws IOException { return bufferedReader.readLine(); }
    public static String[] readStringArray() throws IOException { return bufferedReader.readLine().split("[ \t]"); }
    public static String[] readLines(int quantity) throws IOException { String[] lines = new String[quantity]; for (int i = 0; i < quantity; i++) lines[i] = bufferedReader.readLine().trim(); return lines; }
    public static int[][] readIntMatrix(int numberOfRows) throws IOException { int[][] matrix = new int[numberOfRows][]; for (int i = 0; i < numberOfRows; i++) matrix[i] = readIntArray(); return matrix; }


}




