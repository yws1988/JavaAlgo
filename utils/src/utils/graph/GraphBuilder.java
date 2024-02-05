package utils.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class GraphBuilder
{
    public static List<ArrayList<Integer>> createListArray(int n)
    {
        return IntStream.rangeClosed(0, n).boxed().map(s -> new ArrayList<Integer>()).toList();
    }

    public static List<ArrayList<Integer>> createListArray(int n, int[][] arr, boolean isDirected)
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

    public static List<HashSet<Integer>> createSetArray(int n)
    {
        return IntStream.rangeClosed(0, n).boxed().map(s -> new HashSet<Integer>()).toList();
    }
}