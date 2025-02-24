package graph.flow;
/*
Given an undirected graph: a simple path is a path in a graph which does not have repeating vertices.
S = max (sum of all vertices lying in the maximum length simple path ending at that vertex)
For example, given a graph 1->2->3
S[1]=1+2+3=6
S[2]=Max(1+2,3+2)=5
S[3]=1+2+3=6

Calculate the S for all the vertices.

Given first line n, m, n number of vertices and m number of edges
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class MaximumFlowStoT {
    public static int e;
    public static int[] ns;
    public static String s;
    public static String[] ss;

    public static void solve() throws IOException {
        if(true){
            FileReader file = new FileReader("D:\\Algo\\JavaAlgo\\excercises\\src\\resources\\test.txt");
            bufferedReader = new BufferedReader(file);
        }else{
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        }

        e = readInt();
        int[][] graph = new int[26][26];
        for (int i = 0; i < e; i++) {
            var tmp = readStringArray();
            int src = tmp[0].charAt(0)-'A';
            int des = tmp[1].charAt(0)-'A';
            graph[src][des]=Integer.parseInt(tmp[2]);
        }

        int res = getMaximunFlow(graph, 'S'-'A', 'T'-'A');
        System.out.println(res);

        bufferedReader.close();

    }

    public static int getMaximunFlow(int[][] rGraph, int s, int d)
    {
        //rGraph is residual graph

        int v = rGraph.length;

        int maxFlow = 0;
        int[] parent = new int[v];
        while (isReachableByBFS(rGraph, s, d, parent))
        {
            int p = parent[d];
            int c = d;
            int minFlow = rGraph[p][c];
            while (p != s)
            {
                c = p;
                p = parent[c];
                minFlow = Math.min(minFlow, rGraph[p][c]);
            }

            p = parent[d];
            c = d;
            do
            {
                rGraph[p][c] -= minFlow;
                rGraph[c][p] += minFlow;
                c = p;
                p = parent[c];
            } while (p != s);

            maxFlow += minFlow;
        }

        return maxFlow;
    }

    static boolean isReachableByBFS(int[][] graph, int s, int d, int[] parent)
    {
        int v = graph.length;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(s);
        boolean[] visited = new boolean[v];
        visited[s] = true;

        while (queue.size() > 0)
        {
            int p = queue.remove();
            for (int i = 0; i < v; i++)
            {
                if (!visited[i] && graph[p][i] > 0)
                {
                    queue.add(i);
                    visited[i] = true;
                    parent[i] = p;
                    if (i == d)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }


    public static BufferedReader bufferedReader;

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

    public static int readInt() throws IOException {
        int tmp = Integer.parseInt(bufferedReader.readLine());
        return tmp;
    }

    public static int[] readIntArray() throws IOException {
        return Arrays.stream(readStringArray()).mapToInt(Integer::parseInt).toArray();
    }

    public static String readString() throws IOException {
        return bufferedReader.readLine();
    }

    public static String[] readStringArray() throws IOException {
        return bufferedReader.readLine().split("[ \t]");
    }

    public static String[] readLines(int quantity) throws IOException {
        String[] lines = new String[quantity];
        for (int i = 0; i < quantity; i++) lines[i] = bufferedReader.readLine().trim();
        return lines;
    }

    public static int[][] readIntMatrix(int numberOfRows) throws IOException {
        int[][] matrix = new int[numberOfRows][];
        for (int i = 0; i < numberOfRows; i++) {
            matrix[i] = readIntArray();
        }
        return matrix;
    }

    public static String[][] readStringMatrix(int numberOfRows) throws IOException {
        String[][] matrix = new String[numberOfRows][];
        for (int i = 0; i < numberOfRows; i++) {
            matrix[i] = readStringArray();
        }
        return matrix;
    }


}

