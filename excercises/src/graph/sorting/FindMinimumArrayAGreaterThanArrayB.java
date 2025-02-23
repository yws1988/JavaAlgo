package graph.sorting;
/*
Given two positive arrays A and B with the same length N.
you know A is lexicographically greater than B.
You’ll be given M information about A. Each of the information will be given
like i>j or i<j, means ith element is greater than or less than jth element.
find the lexicographically smallest one among them.
First line number of test case:
2
7 3
8 5 9 7 1 6 7
4 > 6
7 < 2
4 > 7
7 5
6 5 1 6 3 5 4
1 > 7
7 > 2
5 > 7
2 < 5
1 > 2

Result:
YES
8 5 9 7 2 1 1
YES
7 1 1 1 3 1 2
 */

import utils.ArrayHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static graph.sorting.TopologicalSorting.topologicalSort;
import static utils.ArrayHelper.inverse;

public class FindMinimumArrayAGreaterThanArrayB {
    public static int n, m, t;

    public static void solve() throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        t = readInt();
        for (int i = 0; i < t; i++) {
            int[] ns = readIntArray();
            n = ns[0];
            m = ns[1];

            int[] bs = readIntArray();
            var tmp = readIntMatrix(m);

            List<Integer>[] graph = new ArrayList[n];

            for (int k = 0; k < n; k++) {
                graph[k] = new ArrayList<>();
            }

            for (var item : tmp) {
                int src = item[0] - 1;
                int des = item[1] - 1;
                graph[src].add(des);
            }

            int[] rorder = topologicalSort(graph);

            if (rorder == null) {
                System.out.println("NO");
            } else {
                int[] order = inverse(rorder, n);
                int low = 0, high = n - 1;
                int k = 0;
                while (low <= high) {
                    int mid = (low + high) / 2;
                    if (check(mid, bs, order, graph)) {
                        low = mid + 1;
                        k = mid;
                    } else {
                        high = mid - 1;
                    }
                }

                int[] res = createArray(k, bs, order, graph);

                if (k>=0 && k<n) {
                    System.out.println("YES");
                    System.out.println(Arrays.stream(res).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
                }else{
                    System.out.println("NO");
                }

            }
        }

        bufferedReader.close();
    }



    static boolean check(int mid, int[] bs, int[] order, List<Integer>[] graph) {
        int[] asMinimum = new int[n];
        int[] asMax = new int[n];
        for (int i = 0; i < n; i++) {
            if (i < mid) {
                asMinimum[i] = bs[i];
                asMax[i] = bs[i];
            } else {
                asMinimum[i] = 1;
                asMax[i] = Integer.MAX_VALUE;
            }
        }

        for (int parent : order) {
            for (int child : graph[parent]) {
                asMinimum[parent] = Math.max(asMinimum[parent], asMinimum[child] + 1);
            }
        }

        int[] rOrder = inverse(order, n);

        for (int parent : rOrder) {
            for (int child : graph[parent]) {
                asMax[child] = Math.min(asMax[child], asMax[parent] - 1);
            }
        }

        for (int i = 0; i < n; i++) {
            if(asMinimum[i]>asMax[i]){
                return false;
            }
        }

        for (int i = 0; i < n; i++) {
            if(asMax[i]<bs[i]){
                return false;
            }

            if(asMax[i]>bs[i]){
                return true;
            }
        }

        return false;
    }

    static int[] createArray(int k, int[] bs, int[] order, List<Integer>[] graph) {
        int[] asMinimum = new int[n];
        for (int i = 0; i < n; i++) {
            if (i < k) {
                asMinimum[i] = bs[i];
            } else {
                asMinimum[i] = 1;
            }
        }

        for (int parent : order) {
            for (int child : graph[parent]) {
                asMinimum[parent] = Math.max(asMinimum[parent], asMinimum[child] + 1);
            }
        }

        if(asMinimum[k]<=bs[k]){
            asMinimum[k] = bs[k]+1;
        }

        return asMinimum;
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
        for (int i = 0; i < numberOfRows; i++) {
            var tmp = readStringArray();
            if (tmp[1].equals(">")) {
                matrix[i] = new int[]{Integer.parseInt(tmp[0]), Integer.parseInt(tmp[2])};
            } else {
                matrix[i] = new int[]{Integer.parseInt(tmp[2]), Integer.parseInt(tmp[0])};
            }

        }
        return matrix;
    }
}

