package graph.sorting;
/*
Given a list of the ordered the strings, for example AA BB means AA is prioritized
than string BB.

Output
A list of  describing an order with priority, if such an order does not exist, return KO.
Note: There are several solutions, you can return any.
Example
Input
5 4
club-mate pamplemousse
pamplemousse grenadine
mojito club-mate
fraise club-mate
Output
mojito < fraise < club-mate < pamplemousse < grenadine
 */


import graph.circle.CycleInDirectedGraph;
import graph.utils.GraphListHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TopologicalOrderedStrings {
    /// <summary>
    /// Get the topological order
    /// </summary>
    /// <param name="orderedStrings">the ordered ingredients</param>
    /// <param name="n">the number of ingredients</param>
    public static String getTopologicalOrder(String[][] orderedStrings, int n)
    {
        var g = GraphListHelper.convertStringsToGraph(orderedStrings, n);

        if (CycleInDirectedGraph.doesGraphContainsCycle(g))
        {
            return "KO";
        }

        var stack = TopologicalSorting.getSortingOrder(g);

        return String.join(" ", stack.stream().toList());
    }
}
