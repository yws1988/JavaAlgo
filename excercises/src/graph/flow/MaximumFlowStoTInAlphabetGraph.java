package graph.flow;
/*
Given a directed non cycle graph vertex by Alphabet A...Z. Given e number of edges with weights.
Output the maximum flow from S to T.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MaximumFlowStoTInAlphabetGraph {
    public static int e;

    public static void solve() throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        e = readInt();
        int[][] graph = new int[26][26];
        for (int i = 0; i < e; i++) {
            var tmp = readStringArray();
            int src = tmp[0].charAt(0)-'A';
            int des = tmp[1].charAt(0)-'A';
            graph[src][des]=Integer.parseInt(tmp[2]);
        }

        int res = FordFulkersonMaximumFlow.getMaximumFlow(graph, 'S'-'A', 'T'-'A');
        System.out.println(res);

        bufferedReader.close();
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
}

