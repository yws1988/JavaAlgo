package graph.traversal;

/*
 Given a directed graph with weight, get the maximum value of path which equals
 to (Maximum Weight of Vertex - Minimum Weight of Vertex) in the path, minimum weight vertex
 should at the beginning of the path

 First line contains two integers n, m number of graph's vertices and number of graph's edges.
 next line contains n integers of vertex weights
 The following m lines contains u, v separated space represents edge of the graph

 2 1
 3 9
 1 2
 3 2

 Output should be 9-3=6
 */


import utils.graph.GraphLIstBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MaximumMinimumWeightDifferenceInDAG {
    public static Scanner scanner;

    public static int n, m, t;
    static int[] ws;
    static int maxWeight, minWeight;

    public static void solve() throws FileNotFoundException {
        if (true) {
            File file = new File("D:\\Algo\\JavaAlgo\\excercises\\src\\resources\\test.txt");
            scanner = new Scanner(file);
        } else {
            scanner = new Scanner(System.in);
        }

        int[] ns = readIntArray();
        n = ns[0];
        m = ns[1];
        ws = readIntArray();

        var tmp = readIntMatrix(m);

        List<Integer>[] graph = GraphLIstBuilder.buildListArray(n, tmp, true, -1);
        List<Pair> vertexWithWeights = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            vertexWithWeights.add(new Pair(j, ws[j]));
        }

        Collections.sort(vertexWithWeights);

        int diff = 0;
        boolean[] vs = new boolean[n];
        for (int j = 0; j < n; j++) {
            var vertexWithWeight = vertexWithWeights.get(j);
            int src = vertexWithWeight.idx;

            if (!vs[src]) {
                minWeight = vertexWithWeight.weight;
                maxWeight = minWeight;
                dfs(graph, src, vs);
                diff = Math.max(diff, maxWeight - minWeight);
            }
        }

        System.out.println(diff);
        scanner.close();
    }

    static void dfs(List<Integer>[] graph, int src, boolean[] vs) {
        vs[src]=true;
        for (var child : graph[src]) {
            if (!vs[child]) {
                maxWeight = Math.max(maxWeight, ws[child]);
                dfs(graph, child, vs);
            }
        }
    }

    public static void mainF(String[] argv) throws Exception {
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

    public static class Pair implements Comparable<Pair> {
        public int idx;
        public int weight;

        public Pair(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }

        @Override
        public int compareTo(Pair o) {
            return this.weight - o.weight;
        }
    }

    public static int readInt() { int tmp = scanner.nextInt(); scanner.nextLine(); return tmp;}

    public static int[] readIntArray() {
        return Arrays.stream(readStringArray()).mapToInt(Integer::parseInt).toArray();
    }

    public static String[] readStringArray() {
        return scanner.nextLine().split("[ \t]");
    }

    public static int[][] readIntMatrix(int numberOfRows) {
        int[][] matrix = new int[numberOfRows][];
        for (int i = 0; i < numberOfRows; i++) matrix[i] = readIntArray();
        return matrix;
    }

}




