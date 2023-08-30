package graph.connectivity;

//Given a graph G and an integer K, K-cores of the graph are connected components that
//are left after all vertices of degree less than k have been removed (Source wiki)

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KCoreGraphUndirectedGraphDFSSolution {
    public static List<Integer> getKCoreVertex(List<Integer>[] graph, int k)
    {
        int v = graph.length;
        int startPoint = 0;
        boolean hasKeyCore = false;
        int[] degrees = new int[v];

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < v; i++)
        {
            degrees[i] = graph[i].size();
            if (degrees[i] < min)
            {
                min = degrees[i];
                startPoint = i;
            }
        }

        boolean[] vs = new boolean[v];
        if (degrees[startPoint] < k)
        {
            DFSUtil(graph, startPoint, degrees, k, vs);
        }else{
            return IntStream.range(0, v).boxed().collect(Collectors.toList());
        }

        for (int i = 0; i < v; i++)
        {
            if (!vs[i])
            {
                DFSUtil(graph, i, degrees, k, vs);
            }
        }

        hasKeyCore = Arrays.stream(degrees).anyMatch(s -> s >= k);

        if (hasKeyCore)
        {
            return IntStream.range(0, v).filter(s -> degrees[s]>=k).boxed().collect(Collectors.toList());
        }

        return null;
    }

    private static boolean DFSUtil(List<Integer>[] graph, int s, int[] degrees, int k, boolean[] vs)
    {
        vs[s] = true;

        for (int c : graph[s])
        {
            if (degrees[s] < k)
            {
                degrees[c]--;
            }

            if (!vs[c] && DFSUtil(graph, c, degrees, k, vs))
            {
                degrees[s]--;
            }
        }

        return degrees[s] < k ? true : false;
    }
}
