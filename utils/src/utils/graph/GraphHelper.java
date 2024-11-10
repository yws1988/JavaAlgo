package utils.graph;

import datastructures.tuple.Pair;
import datastructures.tuple.Triple;

import java.util.*;

public class GraphHelper {

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

    public static int[][] getTransposeGraph(int[][] graph)
    {
        int n = graph.length;

        var rGraph = new int[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                rGraph[i][j] = graph[j][i];
            }
        }

        return rGraph;
    }

    public static int[][] convertTripleToGraphMatrix(List<Triple> list, int v, boolean isDirected)
    {
        int[][] graph = new int[v][v];
        for (var item : list)
        {
            graph[item.x][item.y] = item.z;

            if (!isDirected)
            {
                graph[item.y][item.x] = item.z;
            }
        }

        return graph;
    }

    public static int[][] convertListToGraphMatrix(List<Pair> list, int v, boolean isDirected)
    {
        int[][] graph = new int[v][v];
        for (var item : list)
        {
            graph[item.x][item.y] = 1;

            if (!isDirected)
            {
                graph[item.y][item.x] = 1;
            }
        }

        return graph;
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


    /// <summary>
    /// Get the vertex which contains the most common neighbours of the
    /// specified vertex
    /// </summary>
    /// <param name="graph">matrix graph with n vertex</param>
    /// <param name="vertex">the specified Vertex</param>
    /// <returns>index of the vertex, the number of the common neighbours</returns>
    public static Pair GetVertexWithMaxNumberOfCommonNeighbours(int[][] graph, int vertex)
    {
        int n = graph.length;
        int max = 0;
        int idx = -1;

        for (int i = 0; i < n; i++)
        {
            if (vertex != i)
            {
                int num = 0;
                for (int j = 0; j < n; j++)
                {
                    if (j != vertex && j != i && graph[vertex][j] == 1 && graph[i][j] == 1)
                    {
                        num++;
                    }
                }

                if (num >= max)
                {
                    idx = i;
                    max = num;
                }
            }
        }

        return new Pair(idx, max);
    }

    /***
     * Calculate all the points whose distance is greater than the point i
     * The result is ins[i] and outs[i]
     * @param graph
     */
    public static void calculateInOutDistanceOfPoints(List<Integer>[] graph){
        int n = graph.length;
        int[] ins = new int[n];
        int[] outs = new int[n];
        boolean[] vs = new boolean[n];

        getDistanceInOutWithDFS(graph, 0, vs, ins, outs);
    }

    public static int distance = 0;

    public static void getDistanceInOutWithDFS(List<Integer>[] graph, int src, boolean[] vs, int[] ins, int[] outs)
    {
        vs[src] = true;
        ins[src] = distance++;

        for (int child : graph[src])
        {
            if (!vs[child])
            {
                getDistanceInOutWithDFS(graph, child, vs, ins, outs);
            }
        }

        outs[src] = distance-1;
    }


}
