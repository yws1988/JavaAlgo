package graph.path;


/**
 * There are n lines of subways number from 1 to n, m subways stations num from 1 to m,
 * Given an array stations, stations[i] contains a list of all the stations for line i.
 * Given a departure station src, and the destination station des, returns
 * an integer representing the minimum number of lines you will use on the route from src to des.
 * In the case where no path would be possible from one station to another, return -1!
 */

import java.util.*;
import java.util.stream.IntStream;
import static graph.traversal.BFSIteration.bfs;


public class SubwayStationIntersection  {
    public static int n, m;

    public static int getLeastNumOfLines(List<Integer>[] stations, int src, int des)
    {
        // Create an array
        // IntStream.range(0, n).mapToObj(s -> new HashSet<Integer>()).toArray();

        HashSet<Integer>[] graph = IntStream.range(0, n).mapToObj(s -> new HashSet<Integer>()).toArray(HashSet[]::new);

        for (int i = 1; i <= m; i++)
        {
            int num = stations[i].size();
            if (stations[i].size() > 1)
            {
                for (int j = 0; j < num; j++)
                {
                    for (int k = j + 1; k < num; k++)
                    {
                        graph[stations[i].get(j)].add(stations[i].get(k));
                        graph[stations[i].get(k)].add(stations[i].get(j));
                    }
                }
            }
        }

        var srcs = stations[src];
        var dess = stations[des];

        if (!Collections.disjoint(srcs, dess))
        {
            return 1;
        }

        int min = Integer.MAX_VALUE;

        var vs = new Boolean[n];

        for (var si : srcs)
        {
            vs[si] = true;
        }

        for (var si : srcs)
        {
            for (var di : dess)
            {
                var vsTemp = Arrays.copyOf(vs, vs.length);

                min = Math.min(min, bfs(graph, si, di, vsTemp));
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
