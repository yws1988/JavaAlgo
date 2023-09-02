package graph.utils;

import java.util.*;

public class GraphListHelper {
    public static List<Integer>[] buildListArray(int n)
    {
        var graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        return graph;
    }

    public static List<Integer>[] buildListArray(int n, int[][] arr, boolean isDirected)
    {
        var graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
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

    public static List<Integer>[] getTransposeGraph(List<Integer>[] graph)
    {
        int v = graph.length;
        ArrayList<Integer>[] reversGraph = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            reversGraph[i]=new ArrayList<>();
        }

        for (int i = 0; i < v; i++)
        {
            for (int des : graph[i])
            {
                reversGraph[des].add(i);
            }
        }

        return reversGraph;
    }

    /*
        The following are string edges, convert the edges to graph with numbers, 4 edges:
        club-mate pamplemousse
        pamplemousse grenadine
        mojito club-mate
        fraise club-mate
     */
    public static List<Integer>[] convertStringsToGraph(String[][] orderedStrings, int n, Map<Integer, String> integerToStringGraph){
        var dic = new HashMap<String, Integer>();
        var g = new ArrayList[n];
        Arrays.fill(g, new ArrayList<Integer>());

        int v = 0;
        integerToStringGraph = new HashMap<>();
        for (var item : orderedStrings)
        {
            if (!dic.containsKey(item[0]))
            {
                dic.put(item[0], v++);
                integerToStringGraph.put(v, item[0]);
            }

            if (!dic.containsKey(item[1]))
            {
                dic.put(item[1], v++);
                integerToStringGraph.put(v, item[1]);
            }

            g[dic.get(item[0])].add(dic.get(item[1]));
        }

        return g;
    }
}
