package graph.matching;

/*
 Given two graph contains the same edges, the vertexes of the first graph is labled by numbers, the vetexes
 of the second graph is labeled by strings, give the matching relations between the numbers and strings to
 make the graphs to be same.

 For example, first graph contains 14 vertexes with following edges:

 edges : {
            ( 0, 5), ( 0, 9), ( 0, 12),( 1, 10), ( 1, 2), ( 1, 7),
            ( 2, 3), ( 2, 6), ( 3, 6), ( 3, 11), ( 4, 12), ( 4, 6),
            ( 4, 13), ( 5, 8), ( 5, 10), ( 7, 8), ( 7, 9),
            ( 8, 11), ( 9, 13), ( 10, 11), ( 12, 13)
        };

  The second graph contains following edges:
    Earaindir Rithralas
    Hilad Fioldor
    Delanduil Rithralas
    Urarion Elrebrimir
    Elrebrimir Fioldor
    Eowul Fioldor
    Beladrieng Anaramir
    Urarion Eowul
    Earaindir Sanakil
    Delanduil Isilmalad
    Earylas Isilmalad
    Rithralas Sanakil
    Unithral Elrebrimir
    Earylas Eowul
    Beladrieng Hilad
    Isilmalad Sanakil
    Unithral Earylas
    Earaindir Anaramir
    Unithral Beladrieng
    Hilad Anaramir
    Delanduil Urarion

    Output:
    Returns the number and string matching relations
 */

import java.util.*;

public class IntegerToStringGraphMatching
{
    private static int n;
    /// <summary>
    /// Get the number to string matches array
    /// </summary>
    /// <param name="oldGraph">number index graph</param>
    /// <param name="newGraph">string index graph</param>
    /// <returns></returns>
    public static String[] getMatches(HashSet<Integer>[] oldGraph, Map<String, HashSet<String>> newGraph)
    {
        var availableNodes = new LinkedList<String>(newGraph.keySet());
        n = oldGraph.length;
        var match = new String[n];

        dfs(0, match, availableNodes, oldGraph, newGraph);

        return match;
    }

    public static boolean dfs(int src, String[] match, LinkedList<String> availableNodes, HashSet<Integer>[] oldGraph, Map<String, HashSet<String>> newGraph)
    {
        var used = new ArrayList<String>();

        while (availableNodes.size() > 0)
        {
            var node = availableNodes.poll();
            var hypoMatch = Arrays.copyOf(match, n);
            hypoMatch[src] = node;

            boolean isConsistent = oldGraph[src].size() == newGraph.get(node).size();

            if (isConsistent)
            {
                for (var c : oldGraph[src])
                {
                    if (hypoMatch[c]!=null)
                    {
                        if (!newGraph.get(hypoMatch[c]).contains(hypoMatch[src]))
                        {
                            isConsistent = false;
                            break;
                        }
                    }
                    else
                    {
                        var mergeLists = new ArrayList<String>();
                        mergeLists.addAll(used);
                        mergeLists.addAll(availableNodes);

                        if (!dfs(c, hypoMatch, new LinkedList<>(mergeLists), oldGraph, newGraph))
                        {
                            isConsistent = false;
                            break;
                        }
                    }
                }
            }

            if (isConsistent)
            {
                match = Arrays.copyOf(hypoMatch, n);
                return true;
            }
            else
            {
                used.add(node);
            }
        }

        return false;
    }
}
