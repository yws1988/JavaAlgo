package graph.hamiltonianPath;
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
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MaximumLengthSimplePath {
    public static int n, m;
    public static int[] ns;
    public static String s;
    public static String[] ss;

    public static int[] solve() throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ns = readIntArray();
        n = ns[0];
        m = ns[1];

        var tmp = readIntMatrix(m);
        boolean[][] graph = new boolean[n][n];
        for (int j = 0; j < m; j++) {
            int a = tmp[j][0] - 1;
            int b = tmp[j][1] - 1;
            graph[a][b] = true;
            graph[b][a] = true;
        }

        boolean[][] dp = new boolean[n][(1 << n)];

        for (int k = 0; k < n; k++) {
            dp[k][1 << k] = true;
        }

        int[] sums = new int[n];
        int[] lens = new int[n];

        for (int j = 0; j < (1 << n); j++) {
            for (int p = 0; p < n; p++) {
                for (int q = 0; q < n; q++) {
                    if (p != q && graph[p][q] && check(j, p) == 1 && check(j, q) == 0 && dp[p][j]) {
                        dp[q][j | (1 << q)] = true;
                    }
                }
            }

            for (int p = 0; p < n; p++) {
                if (dp[p][j]) {
                    int value = j;
                    int len = 0;
                    int sum = 0;

                    for (int l = 0; l < n; l++) {
                        if ((value & 1) == 1) {
                            len++;
                            sum += (l + 1);
                        }

                        value >>= 1;
                    }

                    if (len >= lens[p]) {
                        lens[p] = len;
                        sums[p] = Math.max(sums[p], sum);
                    }
                }
            }
        }

        bufferedReader.close();

        return sums;
    }

    public static int check(int value, int i) {
        return 1 & value >> i;
    }

    public static BufferedReader bufferedReader;

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
}

