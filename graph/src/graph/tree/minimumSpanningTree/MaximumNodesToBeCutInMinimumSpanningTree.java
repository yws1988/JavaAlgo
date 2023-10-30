package graph.tree.minimumSpanningTree;

import java.util.LinkedList;
import java.util.List;

public class MaximumNodesToBeCutInMinimumSpanningTree {
    public static int getMinimumNode(List<Integer>[] graph)
    {
        int n = graph.length;
        var numOfNodes = new int[n];
        var degrees = new int[n];
        var queue = new LinkedList<Integer>();

        for (int i = 0; i < n; i++)
        {
            var degree = graph[i].size();
            numOfNodes[i] = 1;

            if (degree == 1)
            {
                queue.add(i);
            }

            degrees[i] = graph[i].size();
        }

        int record = 0;

        while (queue.size() > 0)
        {
            var node = queue.pop();
            int number = Math.min(numOfNodes[node], n - numOfNodes[node]);
            record = Math.max(number, record);
            degrees[node]--;

            for (var cnode : graph[node])
            {
                if (degrees[cnode] > 1)
                {
                    numOfNodes[cnode] += numOfNodes[node];
                    degrees[cnode]--;

                    if (degrees[cnode] == 1)
                    {
                        queue.add(cnode);
                    }
                }
            }
        }

        return record;
    }
}
