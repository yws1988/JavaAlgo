package graph.connectivities.stronglyConnectedComponents;
/*
 Given a directed graph with vertex weight, find a maximum greatest common divisor of vertex weight
 for all the possible paths in the graph.
 The first line contains two integers n and m
 The next line contains n space separated integers with vertex weight,
 Each of the next m lines contain two integers u and v, denoting a directed edge from u to v.

 3 2
 4 6 8
 1 2
 2 3

 Output 2
 You can choose the walk 1->2->3 to get a gcd of 2
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static graph.connectivity.stronglyConnectedComponent.StronglyConnectedComponentList.getSCC;
import static utils.graph.GraphLIstBuilder.buildListArray;

public class MaximumGCDInDirectedGraph {
    public static BufferedReader bufferedReader;

    public static int n, m, numComponents, res=Integer.MAX_VALUE;
    static int[] w, gcd;
    static boolean[] vs;
    static Set<Integer>[] gcds;

    public static void solve() throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int[] ns = readIntArray();
        n = ns[0];
        m = ns[1];

        w = new int[n];

        ns = readIntArray();
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            w[i]= ns[i];
        }

        int[][] tmp = readIntMatrix(m);
        graph = buildListArray(n, tmp, true, -1);

        int[] scc = getSCC(graph, false);
        gcd = new int[numComponents];
        for (int i = 0; i < n; i++) {
            int componentId = scc[i];
            if(gcd[componentId]==0){
                gcd[componentId]=w[i];
            }else{
                gcd[componentId]=greatestCommonDivisor(gcd[componentId], w[i]);
            }
        }


        List<Integer>[] sccGraph = new ArrayList[numComponents];
        gcds = new Set[numComponents];

        for (int i = 0; i < numComponents; i++) {
            sccGraph[i] = new ArrayList<>();
            gcds[i] = new HashSet<>();
        }

        for (int i = 0; i < m; i++) {
            int u = scc[tmp[i][0]-1];
            int v = scc[tmp[i][1]-1];

            if(u!=v){
                sccGraph[u].add(v);
            }
        }

        vs = new boolean[numComponents];

        for (int i = 0; i < numComponents; i++) {
            if (!vs[i]) {
                dfs(sccGraph, i);
            }
        }

        System.out.println(res);
        bufferedReader.close();
    }

    static void dfs(List<Integer>[] graph, int src) {
        vs[src] = true;
        gcds[src].add(gcd[src]);
        res = Math.min(res, gcd[src]);
        for (var child : graph[src]) {
            if (!vs[child]) {
                dfs(graph, child);
            }

            for (var item : gcds[child]) {
                int parentGcd = greatestCommonDivisor(gcd[src], item);
                gcds[src].add(parentGcd);
                res = Math.min(parentGcd, res);
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

    public static void mainF(String[] argv){
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
