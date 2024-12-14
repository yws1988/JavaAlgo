package graph.connectivities.biConnectedComponents;

/*
Given an undirected connected graph with n vertices and m edges. Each edge is one of the two types: white and black.
There are q queries denoted by four vertices a, b, c and d. Each query asks:
can some of the black edges be deleted, so that a is reachable from b, but c is not reachable from d ?

Input format

The first line contains three integers n, m and q (number of vertices, number of edges and number of queries, respectively).
Then m lines follow.
The i-th of them contains three integers ai, bi and ti (0 for black edge and 1 for white edge).
Then q lines follow.
The i-th of them contains four integers ai, bi, ci, di
Output format
For each query print Yes if it's possible to delete some black edges and satisfy the condition. Print No otherwise.

Example:
5 6 4
1 2 0
1 2 0
2 3 1
1 3 0
3 4 0
4 5 0
1 2 4 5
2 5 1 4
2 5 3 4
1 1 2 2

Output:

Yes
Yes
No
No

 */

import datastructures.graph.EdgeWithWeight;
import graph.connectivity.BiConnected;
import graph.connectivity.ConnectedGraphBuilder;
import graph.tree.binarytree.LowestCommonAncestorWithDpSolution;
import utils.graph.GraphBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static graph.connectivity.BiConnected.articulationPointComponentId;
import static graph.connectivity.BiConnected.biConnectedComponents;
import static graph.connectivity.ConnectedGraphBuilder.connectedComponents;
import static graph.tree.binarytree.LowestCommonAncestorWithDpSolution.lca;

public class PathNotGoAcrossThroughAnotherPath
{
    public static int n,m,q;
    public static int[] ns;
    public static String s;
    public static String[] ss;

    public static void solve() throws FileNotFoundException {
        if(true){
            FileReader file = new FileReader("D:\\Algo\\JavaAlgo\\excercises\\src\\resources\\test.txt");
            scanner = new Scanner(file);
        }else{
            scanner = new Scanner(System.in);
        }

        ns = readIntArray();
        n=ns[0];
        m=ns[1];
        q=ns[2];

        int[][] tmp = readIntMatrix(m);
        List<EdgeWithWeight>[] graph = GraphBuilder.buildListArrayWithWeight(n, tmp, false);

        HashSet<Integer>[] componentGraph = ConnectedGraphBuilder.transformToConnectedComponentGraph(graph);
        HashSet<Integer>[] biComponentGraph = BiConnected.buildBiConnectedComponentGraph(componentGraph);

        LowestCommonAncestorWithDpSolution.preprocessLcaDpLevel(biComponentGraph, 0);

        for (int i = 0; i < q; i++) {
            var points = readIntArray();
            int a = points[0]-1;
            int b = points[1]-1;
            int c = points[2]-1;
            int d = points[3]-1;

            if(c==a || c==b){
                c=-1;
            }

            if(d==a || d==b){
                d=-1;
            }

            if(c==-1 && d==-1){
                System.out.println("No");
                continue;
            }

            int cca = connectedComponents[a];
            int ccb = connectedComponents[b];

            int biComponentA = biConnectedComponents[cca];
            int biComponentB = biConnectedComponents[ccb];

            int ancestorAB = lca(biComponentA, biComponentB);

            if(c!=-1 && d!=-1){
                int ccc = connectedComponents[c];
                int ccd = connectedComponents[d];

                if(ccc == ccd || (cca==ccc && ccb==ccd) || (cca==ccd && ccb==ccc))
                {
                    System.out.println("No");
                    continue;
                }

                int biComponentC = biConnectedComponents[ccc];
                int biComponentD = biConnectedComponents[ccd];

                if(biComponentC >= articulationPointComponentId && biComponentD >= articulationPointComponentId){
                    if(isPointInPath(biComponentA, biComponentB, biComponentC, ancestorAB) && isPointInPath(biComponentA, biComponentB, biComponentD, ancestorAB)){
                        System.out.println("No");
                        continue;
                    }
                }
            }else{
                int e= c!=-1 ? c : d;
                int cce = connectedComponents[e];

                if(cca==cce || ccb==cce)
                {
                    System.out.println("No");
                    continue;
                }

                int biComponentE = biConnectedComponents[cce];

                if(biComponentE >= articulationPointComponentId){
                    if(isPointInPath(biComponentA, biComponentB, biComponentE, ancestorAB)){
                        System.out.println("No");
                        continue;
                    }
                }
            }

            System.out.println("Yes");
        }

        scanner.close();
    }

    static boolean isPointInPath(int a, int b, int x, int ancestor){
        return (lca(a, x)==x || lca(b, x)==x) && (lca(x, ancestor)==ancestor);
    }

    public static Scanner scanner;

    public static void mainF(String[] argv) throws Exception
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

    public static int[] readIntArray() { return Arrays.stream(readStringArray()).mapToInt(s->Integer.parseInt(s)).toArray(); }
    public static String[] readStringArray() { return scanner.nextLine().split("[ \t]"); }
    public static int[][] readIntMatrix(int numberOfRows) { int[][] matrix = new int[numberOfRows][]; for (int i = 0; i < numberOfRows; i++){matrix[i] = readIntArray(); matrix[i][0]--;matrix[i][1]--;} return matrix; }
}

