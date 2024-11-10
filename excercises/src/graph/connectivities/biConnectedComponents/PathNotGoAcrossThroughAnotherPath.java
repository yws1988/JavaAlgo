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
 */

import datastructures.graph.EdgeWithWeight;
import graph.connectivity.BiConnected;
import graph.connectivity.ConnectedGraphBuilder;
import graph.tree.binarytree.LowestCommonAncestorWithDpSolution;
import utils.graph.GraphBuilder;

import java.io.File;
import java.util.*;

import static graph.connectivity.BiConnected.ArticulationPointComponentId;
import static graph.connectivity.BiConnected.biConnectedComponents;
import static graph.connectivity.ConnectedGraphBuilder.connectedComponents;
import static graph.tree.binarytree.LowestCommonAncestorWithDpSolution.Lca;

public class PathNotGoAcrossThroughAnotherPath
{
    public static int n,m,q;
    public static int[] ns;
    public static String s;
    public static String[] ss;

    public static void solve()
    {
        int[][] tmp = readIntMatrix(m);
        List<EdgeWithWeight>[] graph = GraphBuilder.buildListArrayWithWeight(n, tmp, false);

        HashSet<Integer>[] componentGraph = ConnectedGraphBuilder.transformToConnectedComponentGraph(graph);
        HashSet<Integer>[] biComponentGraph = BiConnected.getBiConnectedComponentGraph(componentGraph);

        LowestCommonAncestorWithDpSolution.buildLcaDpLevel(biComponentGraph, 0);

        for (int i = 0; i < q; i++) {
            var points = readIntArray();
            int a = points[0]-1;
            int b = points[1]-1;
            int c = points[2]-1;
            int d = points[3]-1;
            int biComponentA = biConnectedComponents[connectedComponents[a]];
            int biComponentB = biConnectedComponents[connectedComponents[b]];
            int biComponentC = biConnectedComponents[connectedComponents[c]];
            int biComponentD = biConnectedComponents[connectedComponents[d]];

            if(connectedComponents[c] == connectedComponents[d] || (connectedComponents[a]==connectedComponents[c] && connectedComponents[b]==connectedComponents[d]) || (connectedComponents[a]==connectedComponents[d] && connectedComponents[b]==connectedComponents[c])){
                System.out.println("No");
                continue;
            }

            int ancestorAB = Lca(biComponentA, biComponentB);

            if(biComponentC >= ArticulationPointComponentId && biComponentD >= ArticulationPointComponentId){
              if(isPointInPath(biComponentA, biComponentB, biComponentC, ancestorAB) && isPointInPath(biComponentA, biComponentB, biComponentD, ancestorAB)){
                   System.out.println("No");
                    continue;
                }
            }

            System.out.println("Yes");
        }
    }

    static boolean isPointInPath(int a, int b, int x, int ancestor){
        return (Lca(a, x)==x || Lca(b, x)==x) && (Lca(x, ancestor)==ancestor);
    }

    public static Scanner scanner;

    public static void mainF(String[] argv) throws Exception
    {
        if(true){
            File file = new File("C:\\Users\\shen\\Desktop\\delete\\input.txt");
            scanner = new Scanner(file);
        }else{
            scanner = new Scanner(System.in);
        }

        ns = readIntArray();
        n=ns[0];
        m=ns[1];
        q=ns[2];

        solve();
        scanner.close();
    }

    public static int[] readIntArray() { return Arrays.stream(readStringArray()).mapToInt(s->Integer.parseInt(s)).toArray(); }
    public static String[] readStringArray() { return scanner.nextLine().split("[ \t]"); }
    public static int[][] readIntMatrix(int numberOfRows) { int[][] matrix = new int[numberOfRows][]; for (int i = 0; i < numberOfRows; i++){matrix[i] = readIntArray(); matrix[i][0]--;matrix[i][1]--;} return matrix; }
}

