package graph.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GraphListHelper {
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
    public static List<Integer>[] convertStringsToGraph(String[][] orderedStrings, int n){
        var dic = new HashMap<String, Integer>();
        var g = new ArrayList[n];
        Arrays.fill(g, new ArrayList<Integer>());

        int v = 0;
        for (var item : orderedStrings)
        {
            if (!dic.containsKey(item[0]))
            {
                dic.put(item[0], v++);
            }

            if (!dic.containsKey(item[1]))
            {
                dic.put(item[1], v++);
            }

            g[dic.get(item[0])].add(dic.get(item[1]));
        }

        return g;
    }
}
