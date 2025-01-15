package graph.connectivity.stronglyConnectedComponent;

import utils.graph.GraphHelper;

import java.util.List;
import java.util.Stack;

public class StronglyConnectedComponent
{
    public static int numCompoents = 0, k =2;

    /// <summary>
    /// Verify if the graph contains a strongly Connected Component whose size is greater than k
    /// </summary>
    /// <param name="graph"></param>
    /// <returns></returns>
    public static boolean containsSccSizeGreaterThanK(List<Integer>[] graph)
    {
        var stack = new Stack<Integer>();
        int v = graph.length;

        boolean[] visited = new boolean[v];

        for (int i = 0; i < v; i++)
        {
            if (!visited[i])
            {
                DFS(i, visited, stack, graph);
            }
        }

        var rGraph = GraphHelper.getTransposeGraph(graph);

        int[] scc = new int[v];
        for (int i = 0; i < v; i++)
        {
            scc[i] = -1;
        }

        int[] counter = new int[v];

        while (stack.size() > 0)
        {
            int i = stack.pop();
            if (scc[i]==-1)
            {
                if(DFSComponents(i, scc, rGraph, numCompoents, counter)){
                    return true;
                }
                numCompoents++;
            }
        }

        return false;
    }

    static void DFS(int s, boolean[] vs, Stack<Integer> stack, List<Integer>[] graph)
    {
        vs[s] = true;

        for (int c : graph[s])
        {
            if (!vs[c])
            {
                DFS(c, vs, stack, graph);
            }
        }

        stack.push(s);
    }

    static boolean DFSComponents(int s, int[] scc, List<Integer>[] graph, int num, int[] counter)
    {
        counter[num]++;
        if(counter[num]>=k){
            return true;
        }

        scc[s] = num;

        for (int c : graph[s])
        {
            if (scc[c]==-1)
            {
                if(DFSComponents(c, scc, graph, num, counter)){
                    return true;
                }
            }
        }

        return false;
    }
}

