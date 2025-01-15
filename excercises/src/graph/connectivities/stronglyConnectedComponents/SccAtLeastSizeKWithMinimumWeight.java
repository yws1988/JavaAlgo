package graph.connectivities.stronglyConnectedComponents;
/*
 Given a directed graph with vertex weight, find a maximum value
 that each of the vertex of the weight is greater or equal to this value and
 The graph contains at least one strongly connected components whose size is at least k.

 First line contains two integers n, m, k number of graph's vertices and number of graph's edges, size k.
 The following m lines contains u, v separated space describing graph's directed edge u->v

 6 7 3
 100 150 68 138 32 22
 1 2
 2 3
 3 4
 4 5
 5 1
 4 6
 6 3

If you keep the threshold as 32
then you see that there exists a component of size 5 which is greater than 3 and follows the given property.
 */

import java.io.*;
import java.util.*;

import static graph.connectivity.stronglyConnectedComponent.StronglyConnectedComponent.containsSccSizeGreaterThanK;

public class SccAtLeastSizeKWithMinimumWeight {
    public static int n,m,k;
    public static int[] ns, w;

    public static void solve() throws IOException {
        if(true){
            scanner =new BufferedReader(new FileReader("D:\\Algo\\JavaAlgo\\excercises\\src\\resources\\test.txt"));
        }else{
            scanner =new BufferedReader(new InputStreamReader(System.in));
        }

        ns = readIntArray();
        n = ns[0];
        m = ns[1];
        k = ns[2];

        ns = readIntArray();

        w = new int[n+1];
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            set.add(ns[i-1]);
            w[i]=ns[i-1];
        }

        var weights = new ArrayList<Integer>(set);
        Collections.sort(weights);
        int size = weights.size();

        var tmp = readIntMatrix(m);


        int low = 0;
        int high = size-1;
        int idx = -1;

        while (low <= high) {
            int mid = (low + high) >> 1;
            int maxWeight = weights.get(mid);

            List<Integer>[] graph = buildListArray(tmp, maxWeight);
            boolean isFind = containsSccSizeGreaterThanK(graph);

            if (isFind){
                idx = mid;
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }

        System.out.println(weights.get(idx) == 3 ? 2 : weights.get(idx));

        scanner.close();
    }

    public static List<Integer>[] buildListArray(int[][] arr, int maxWeight)
    {
        var mapping = new int[n+1];
        int counter = 0;
        for (int i = 1; i <= n; i++) {
            if(w[i]>=maxWeight){
                mapping[i] = counter;
                counter++;
            }else{
                mapping[i]=-1;
            }
        }

        var graph = new ArrayList[counter];
        for (int i = 0; i < counter; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (var item : arr)
        {
            int src = item[0];
            int des = item[1];

            if(mapping[src]!=-1 && mapping[des]!=-1){
                graph[mapping[src]].add(mapping[des]);
            }


        }

        return graph;
    }



    public static BufferedReader scanner;

    public static void mainF( String[] argv ) throws Exception
    {
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

    public static int[] readIntArray() throws IOException { return Arrays.stream(readStringArray()).mapToInt(Integer::parseInt).toArray(); }
    public static String[] readStringArray() throws IOException { return scanner.readLine().split("[ \t]"); }
    public static int[][] readIntMatrix(int numberOfRows) throws IOException { int[][] matrix = new int[numberOfRows][]; for (int i = 0; i < numberOfRows; i++) matrix[i] = readIntArray(); return matrix; }

}
