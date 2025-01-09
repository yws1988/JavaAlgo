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

public class MaximumGCDInDirectedGraph {
    public static Scanner scanner;

    public static int n, m;
    static int[] u, v, w, gcd;
    static boolean[] vs;

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

        w = new int[n + 1];
        gcd = new int[n + 1];

        ns = readIntArray();
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            w[i]= ns[i-1];
        }

        u = new int[m + 1];
        v = new int[m + 1];

        vs = new boolean[n + 1];

        int[][] tmp = readIntMatrix(m);
        for (int i = 0; i < m; i++) {
            u[i] = tmp[i][0];
            v[i] = tmp[i][1];
            graph[u[i]].add(i);
        }

        int gdc = 0;
        for (int i = 1; i <= n; i++) {
            if (!vs[i]) {
                dfs(graph, i);
            }
        }

        System.out.println(gdc);
        scanner.close();
    }

    static void dfs(List<Integer>[] graph, int src) {
        vs[src] = true;
        for (var edge : graph[src]) {
            int child = v[edge];
            if (!vs[child]) {
                dfs(graph, child);
                w[src] = Math.min(w[src], greatestCommonDivisor(w[src], w[child]));
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




