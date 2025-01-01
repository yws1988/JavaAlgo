package utils.graph;

import datastructures.graph.EdgeWithWeight;

import java.util.*;
import java.util.stream.IntStream;

public class GraphBuilder
{
    public static <T> List<T>[] buildListArray(int n)
    {
        var graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<T>();
        }

        return graph;
    }

    public static <T> List<T>[] buildListArray(int n, int[][] arr, boolean isDirected)
    {
        var graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<T>();
        }

        for (var item : arr)
        {
            int src = item[0];
            int des = item[1];
            graph[src].add(des);

            if (!isDirected)
            {
                graph[des].add(src);
            }
        }

        return graph;
    }

    public static List<EdgeWithWeight>[] buildListArrayWithWeight(int n, int[][] arr, boolean isDirected)
    {
        var graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<EdgeWithWeight>();
        }

        for (var item : arr)
        {
            int src = item[0];
            int des = item[1];
            int weight = item[2];
            graph[src].add(new EdgeWithWeight(src, des, weight));

            if (!isDirected)
            {
                graph[des].add(new EdgeWithWeight(des, src, weight));
            }
        }

        return graph;
    }

    public static HashMap<List, Integer> buildSetEdgesWithWeight(int[][] arr, boolean isDirected)
    {
        var edgesMap = new HashMap<List, Integer>();

        for (var item : arr)
        {
            int src = item[0];
            int des = item[1];
            int weight = item[2];
            edgesMap.put(List.of(src, des), weight);

            if (!isDirected)
            {
                edgesMap.put(List.of(des, src), weight);
            }
        }

        return edgesMap;
    }

    public static List<ArrayList<Integer>> buildListList(int n)
    {
        return IntStream.rangeClosed(0, n).boxed().map(s -> new ArrayList<Integer>()).toList();
    }

    public static List<ArrayList<Integer>> buildListList(int n, int[][] arr, boolean isDirected)
    {
        var graph = IntStream.rangeClosed(0, n).boxed().map(s -> new ArrayList<Integer>()).toList();
        for (var item : arr)
        {
            int src = item[0];
            int des = item[1];
            graph.get(src).add(des);

            if (!isDirected)
            {
                graph.get(des).add(src);
            }
        }

        return graph;
    }

    public static List<HashSet<Integer>> buildSetList(int n)
    {
        return IntStream.rangeClosed(0, n).boxed().map(s -> new HashSet<Integer>()).toList();
    }

    /***
     * Build graph List<Integer>[], index i is vertex id, value is the index of edges
     * there are m edges, each edge presents three integers by src, des, weight
     */
    public static int[] u, v,w;
    static boolean[] vs;
    public static List<Integer>[] buildListArrayWithEdges(int n, int m, int[][] edges, boolean isDirected)
    {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i <n; i++) {
            graph[i] = new ArrayList<>();
        }

        u = new int[m];
        v = new int[m];
        w = new int[m];
        vs = new boolean[m];

        for (int i = 0; i < m; i++) {
            u[i] = edges[i][0];
            v[i] = edges[i][1];
            w[i] = edges[i][2];
            graph[u[i]].add(i);
            if(!isDirected){
                graph[v[i]].add(i);
            }
        }

        return graph;
    }

}