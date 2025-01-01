package graph.traversal;

/*
 Given a directed graph with weight, get the maximum value of path which equals
 to (Maximum Weight Edge - Minimum Weight Edge) in the path

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


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MaximumMinimumEdgeWeightDifferenceInDirectedGraph {
    public static Scanner scanner;

    public static int n, m;
    static int[] u, v, w;
    static boolean[] vs;

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

        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        u = new int[m + 1];
        v = new int[m + 1];
        w = new int[m + 1];
        vs = new boolean[m + 1];

        List<Pair> vertexWithWeights = new ArrayList<>();
        int[][] tmp = readIntMatrix(m);
        for (int i = 0; i < m; i++) {
            u[i] = tmp[i][0];
            v[i] = tmp[i][1];
            w[i] = tmp[i][2];
            vertexWithWeights.add(new Pair(i, w[i]));
            graph[u[i]].add(i);
        }

        Collections.sort(vertexWithWeights);

        int diff = 0;
        for (int i = 0; i < m; i++) {
            var edgeWithWeight = vertexWithWeights.get(i);
            int edge = edgeWithWeight.idx;

            if (!vs[edge]) {
                vs[edge]=true;
                int des = v[edge];
                minWeight = edgeWithWeight.weight;
                maxWeight = minWeight;
                vs[edge]=true;
                dfs(graph, des, "asc");
                diff = Math.max(diff, maxWeight - minWeight);
            }
        }


        Collections.reverse(vertexWithWeights);
        Arrays.fill(vs, false);

        for (int i = 0; i < m; i++) {
            var edgeWithWeight = vertexWithWeights.get(i);
            int edge = edgeWithWeight.idx;

            if (!vs[edge]) {
                vs[edge]=true;
                int des = v[edge];
                maxWeight = edgeWithWeight.weight;
                minWeight = maxWeight;
                vs[edge]=true;
                dfs(graph, des, "desc");
                diff = Math.max(diff, maxWeight - minWeight);
            }
        }

        System.out.println(diff);
        scanner.close();
    }

    static void dfs(List<Integer>[] graph, int src, String order) {
        for (var child : graph[src]) {
            if (!vs[child]) {
                vs[child]=true;
                if (order.equals("asc")) {
                    maxWeight = Math.max(maxWeight, w[child]);
                } else {
                    minWeight = Math.min(minWeight, w[child]);
                }

                dfs(graph, v[child], order);
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




