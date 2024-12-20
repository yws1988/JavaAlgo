package graph.connectivities.biConnectedComponents;

/***
Given a connected graph with weight 0 or 1, verify if it is possible to draw a
Spanning Tree that the sum of all the weights are odd.
First line contains two integers n, m number of graph's vertices and number of graph's edges.
The following m lines contains u, v, w separated space describing graph's edge's vertices with weight

 5 4
 3 5 1
 4 5 1
 1 2 0
 5 1 1
 */

import graph.connectivity.BiConnected;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import utils.graph.GraphBuilder;

import static graph.connectivity.BiConnected.biConnectedComponentsList;
import static utils.graph.GraphBuilder.buildListArray;

public class SpanningTreeWithOddWeightSum
{
    public static int n, m;
    public static Scanner scanner;
    public static void solve() throws FileNotFoundException {
        if(true){
            File file = new File("D:\\Algo\\JavaAlgo\\excercises\\src\\resources\\test.txt");
            scanner = new Scanner(file);
        }else{
            scanner = new Scanner(System.in);
        }

        int[] ns = readIntArray();
        n=ns[0];
        m=ns[1];

        int[][] tmp = readIntMatrix(m);
        HashMap<List, Integer> edgesWithWeight = GraphBuilder.buildSetEdgesWithWeight(tmp, false);
        List<Integer>[] graph = buildListArray(n+1, tmp, false);
        BiConnected.getBiConnectedComponents(graph);

        int componentSize = biConnectedComponentsList.size();

        boolean[][] mark = new boolean[2][componentSize];

        int all=0;
        Set<Integer> vertexes = new HashSet<>();
        for (int i = 0; i < componentSize; i++) {
            for (var edge : biConnectedComponentsList.get(i)) {
                mark[edgesWithWeight.get(List.of(edge.src, edge.des))][i]=true;
                vertexes.add(edge.src);
                vertexes.add(edge.des);
            }

            if(mark[0][i] && mark[1][i]){
                System.out.println("YES");
                return;
            }

            if(mark[1][i]){
                all^= (vertexes.size()-1);
            }

            vertexes.clear();
        }

        System.out.println(all%2==1 ? "YES":"NO");

        scanner.close();
    }

    public static void main( String[] argv ) throws Exception
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

    public static int[] readIntArray() { return Arrays.stream(readStringArray()).mapToInt(Integer::parseInt).toArray(); }
    public static String[] readStringArray() { return scanner.nextLine().split("[ \t]"); }
    public static int[][] readIntMatrix(int numberOfRows) { int[][] matrix = new int[numberOfRows][]; for (int i = 0; i < numberOfRows; i++) matrix[i] = readIntArray(); return matrix; }

}




