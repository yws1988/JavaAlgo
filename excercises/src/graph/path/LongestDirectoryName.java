package graph.path;

/*
In computer, given the directory hierarchy as a list of pairs, find the path which contains the most number
of characters:

Example

Given the input:
5
C: WINDOWS
FOO BAR
WINDOWS SYSTEM32
C: FOO
BAR XYZZY

The answer is C:WINDOWSSYSTEM32 since its length 19 is greater than 16 (the length of C:FOOBARXYZZY).
*/

import datastructures.tuple.PairStr;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LongestDirectoryName {
    public static String GetLongestDirectoryName(HashMap<String, List<String>> directories, String root)
    {
        var queue = new LinkedList<PairStr>();
        queue.push(new PairStr(root, root));

        String maxDirectoryName = root;

        while (queue.size() > 0)
        {
            var cNode = queue.pop();

            if (cNode.y.length() > maxDirectoryName.length())
            {
                maxDirectoryName = cNode.y;
            }

            if (directories.containsKey(cNode.x))
            {
                for (var childDirectory : directories.get(cNode.x))
                {
                    queue.push(new PairStr(childDirectory, cNode.y + childDirectory));
                }
            }
        }

        return maxDirectoryName;
    }
}
