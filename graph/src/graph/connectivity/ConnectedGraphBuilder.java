package graph.connectivity;

import datastructures.graph.EdgeWithWeight;

import java.util.*;

public class ConnectedGraphBuilder
{
    // Following matrix have 4 connected components
    //{1, 1, 0, 0, 0},
    //{0, 1, 0, 0, 1},
    //{1, 0, 0, 1, 1},
    //{0, 0, 0, 0, 0},
    //{1, 0, 1, 0, 0}

    public static int getConnectedGraphNum(int[][] graph)
    {
        int v = graph.length;
        boolean[][] vs = new boolean[v][v];

        int count = 0;

        for (int i = 0; i < v; i++)
        {
            for (int j = 0; j < v; j++)
            {
                if(graph[i][j]==1 && !vs[i][j])
                {
                    count++;
                    DFSUtil(graph, i, j, vs, v);
                }
            }
        }

        return count;
    }

    static void DFSUtil(int[][] graph, int i, int j, boolean[][] vs, int v)
    {
        vs[i][j] = true;

        for (int m = -1; m <= 1; m++)
        {
            for (int h = -1; h <= 1; h++)
            {
                if (m == 0 && h == 0) continue;
                int tI = i + m;
                int tJ = j + h;
                if(isSafe(tI, tJ, v) && !vs[tI][tJ] && graph[tI][tJ]==1)
                {
                    DFSUtil(graph, tI, tJ, vs, v);
                }
            }
        }
    }

    static boolean isSafe(int i, int j, int v)
    {
        return i >= 0 && i < v && j >= 0 && j < v;
    }

    /***
     * Transform a graph into a component graph
     * Given a graph with edge, each edge has a weight, if the weight of the edge is 1, the vertex belongs to
     * the same component, if the edge weight is 0, the two edge vertex belongs to different component
     * transform the graph into a new connected component graph
     */
    public static int[] connectedComponents;

    public static HashSet<Integer>[] transformToConnectedComponentGraph(List<EdgeWithWeight>[] graph)
    {
        int v = graph.length;
        boolean[] vs = new boolean[v];
        Arrays.fill(connectedComponents, -1);

        int componentId = 0;

        for (int i = 0; i < v; i++) {
            if(!vs[i]){
                bfs(graph, i, connectedComponents, componentId, vs);
                componentId++;
            }
        }

        var newGraph = new HashSet[componentId];

        for (int i = 0; i < componentId; i++) {
            newGraph[i] = new HashSet();
        }

        for (int i = 0; i < v; i++) {
            for (var edge : graph[i]) {
                if(connectedComponents[i]!= connectedComponents[edge.des]){
                    newGraph[connectedComponents[i]].add(connectedComponents[edge.des]);
                }
            }
        }

        return newGraph;
    }

    public static void bfs(List<EdgeWithWeight>[] graph, int src, int[] components, int componentId, boolean[] vs)
    {
        var queue = new LinkedList<Integer>();
        queue.add(src);

        while (queue.size() > 0)
        {
            int next = queue.poll();
            vs[next] = true;
            components[next] = componentId;

            for (var c : graph[next])
            {
                if (!vs[c.des] && c.weight==1)
                {
                    queue.add(c.des);
                }
            }
        }
    }

    /***
     * Given a graph, an array components, the component id of vertex v equals to components[v]
     * transform the graph into a new connected component graph
     */

    public static HashSet<Integer>[] transformComponentGraphWithComponentsIds(List<Integer>[] graph, int[] components)
    {
        int numOfComponents = Arrays.stream(components).max().getAsInt()+1;

        var newGraph = new HashSet[numOfComponents];

        for (int i = 0; i < numOfComponents; i++) {
            newGraph[i] = new HashSet<Integer>();
        }

        for (int p = 0; p < graph.length; p++) {
            for (var c : graph[p]) {
                if(components[p]!=components[c]){
                    newGraph[components[p]].add(components[c]);
                }
            }
        }

        return newGraph;
    }


}

