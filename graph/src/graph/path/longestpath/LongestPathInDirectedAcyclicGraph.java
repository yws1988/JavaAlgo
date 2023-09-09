package graph.path.longestpath;

//Given a Weighted Directed Acyclic Graph(DAG) and a source vertex s in it,
//find the longest distances from s to all other vertices in the given graph.

public class LongestPathInDirectedAcyclicGraph
{
    /// <summary>
    /// Get all the longest path from source s to all the other paths
    /// </summary>
    /// <param name="graph"></param>
    /// <param name="s">Source vertex</param>
    /// <returns></returns>
    public static int[] GetLongestPath(List<EdgeNode>[] graph, int s, out int[] parents)
    {
        var stack = new Stack<int>();
        int n = graph.Length;
        TopologicalSorting(graph, stack);
        int[] weights = Enumerable.Range(0, n).Select(i => int.MinValue).ToArray();
        weights[s] = 0;
        parents = Enumerable.Range(0, n).Select(i => -1).ToArray();

        do
        {
            int p = stack.Pop();
            if (weights[p] != int.MinValue)
            {
                foreach (EdgeNode EdgeNode in graph[p])
                {
                    if (weights[p] + EdgeNode.Weight > weights[EdgeNode.Des])
                    {
                        weights[EdgeNode.Des] = weights[p] + EdgeNode.Weight;
                        parents[EdgeNode.Des] = p;
                    }
                }
            }
        } while (stack.Count > 0);

        return weights;
    }

    static void TopologicalSorting(List<EdgeNode>[] graph, Stack<int> stack)
    {
        int n = graph.Length;
        var vs = new bool[n];
        for (int i = 0; i < n; i++)
        {
            if (!vs[i])
            {
                TopologicalSortingUtil(graph, i, vs, stack);
            }
        }
    }

    static void TopologicalSortingUtil(List<EdgeNode>[] graph, int i, bool[] vs, Stack<int> stack)
    {
        vs[i] = true;

        foreach (EdgeNode EdgeNode in graph[i])
        {
            if (!vs[EdgeNode.Des])
            {
                TopologicalSortingUtil(graph, EdgeNode.Des, vs, stack);
            }
        }

        stack.Push(i);
    }
}
