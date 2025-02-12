package graph.hamiltonianPath;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MaximumLengthSimplePath {
    public static int n, m, t;
    public static int[] ns;
    public static String s;
    public static String[] ss;

    public static void solve() throws IOException {
        if (true) {
            FileReader file = new FileReader("D:\\Algo\\JavaAlgo\\excercises\\src\\resources\\test.txt");
            bufferedReader = new BufferedReader(file);
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        }
        t = readInt();

        for (int i = 0; i < t; i++) {
            ns = readIntArray();
            n = ns[0];
            m = ns[1];

            var tmp = readIntMatrix(m);
            boolean[][] graph = new boolean[n][n];
            for (int j = 0; j < m; j++) {
                int a = tmp[j][0]-1;
                int b = tmp[j][1]-1;
                graph[a][b]=true;
                graph[b][a]=true;
            }

            int minSumVertices = Integer.MAX_VALUE;
            int maxSumVertices = 0;

            boolean[][] dp = new boolean[n][];

            for (int k = 0; k < n; k++) {
                dp[k][2 << k] = true;
            }

            int[] sums = new int[n];
            int[] lens = new int[n];
            for (int k = 0; k < n; k++) {
                for (int j = 0; j < (2 << (n)); j++) {
                    dp[k][j] = check(j, k) == 1 && dp[k][j & ~(1 << i)];

                    if(dp[k][j]){
                        int len = countBit(j);


                        if(len>=lens[k]){
                            sums[k]=Math.max(sums[k], )
                        }
                    }
                }
            }


            int gcd = greatestCommonDivisor(minSumVertices, maxSumVertices);
            System.out.println(maxSumVertices / gcd + " " + minSumVertices / gcd);

        }

        bufferedReader.close();
    }

    public static int greatestCommonDivisor(int m, int n) {
        while (n > 0) {
            int t = n;
            n = m % n;
            m = t;
        }

        return m;
    }

    public static int check(int value, int i)
    {
        return 1 & value >> i;
    }

    //count(mask) – the number of non-zero bits in mask
    public static int countBit(int value)
    {
        int c = 0;
        do
        {
            if ((value & 1) == 1) c++;
        }
        while ((value >>= 1) > 0);

        return c;
    }


    static int maxLevel = 0, sumOfVertices = 0;

    static void dfsPathUntil(List<Integer>[] graph, int n, boolean[] vs, int src, int level, int sum) {
        vs[src] = true;
        sum += src + 1;

        if (maxLevel <= level) {
            maxLevel = level;
            sumOfVertices = Math.max(sum, sumOfVertices);
        }

        for (var c : graph[src]) {
            if (!vs[c]) {
                dfsPathUntil(graph, n, vs, c, level + 1, sum);
            }
        }

        vs[src] = false;
    }


    public static <T> List<T>[] buildListArray(int n, int[][] arr, boolean isDirected, int adjust) {
        var graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<T>();
        }

        for (var item : arr) {
            int src = item[0] + adjust;
            int des = item[1] + adjust;
            graph[src].add(des);

            if (!isDirected) {
                graph[des].add(src);
            }
        }

        return graph;
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
        for (int i = 0; i < numberOfRows; i++) matrix[i] = readIntArray();
        return matrix;
    }

    public static class Pair {
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pair clone() {
            return new Pair(x, y);
        }
    }

    public static class PairComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.x - o2.x;
        }
    }

    public static void printEdgeList(HashSet<Integer>[] biComponentGraph) {
        HashSet<List> edges = new HashSet<>();
        List<Integer> edge;
        for (int i = 0; i < biComponentGraph.length; i++) {
            for (int j : biComponentGraph[i]) {
                if (i > j) {
                    edge = List.of(j, i);
                } else {
                    edge = List.of(i, j);
                }

                if (!edges.contains(edge)) {
                    edges.add(edge);
                    System.out.println(edge.get(0) + "-" + edge.get(1));
                }
            }
        }
    }
}

