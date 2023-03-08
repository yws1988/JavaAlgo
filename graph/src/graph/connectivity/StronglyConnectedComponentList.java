package graph.connectivity;

import java.util.List;
import java.util.Stack;
import graph.utils.GraphListHelper;

public class StronglyConnectedComponentList
{
    /// <summary>
    /// Get strongly Connected Component, return array vetex number and componenet id
    /// </summary>
    /// <param name="graph"></param>
    /// <returns></returns>
    public static int[] getSCC(List<Integer>[] graph)
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

        var rGraph = GraphListHelper.getTransposeGraph(graph);

        int[] scc = new int[v];
        for (int i = 0; i < v; i++)
        {
            scc[i] = -1;
        }

        int numCompoents = 0;
        while (stack.size() > 0)
        {
            int i = stack.pop();
            if (scc[i]==-1)
            {
                DFSComponents(i, scc, rGraph, numCompoents);
            }
            numCompoents++;
        }

        return scc;
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

    static void DFSComponents(int s, int[] scc, List<Integer>[] graph, int num)
    {
        scc[s] = num;

        for (int c : graph[s])
        {
            if (scc[c]==-1)
            {
                DFSComponents(c, scc, graph, num);
            }
        }
    }
}

