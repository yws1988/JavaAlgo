package graph.tree.diameter;
/*
The diameter of an N-ary tree is the longest path present between any two nodes of the tree.
These two nodes must be two leaf nodes. Verify if all the edges of the tree are belongs to the
path of tree diameters.
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AllLeafsInTreeDiameterPath {
    static int diameter;
    static HashSet<Integer> visitedLeafs = new HashSet<>();

    public static boolean Solve(List<Integer>[] tree)
    {
        int n = tree.length;

        diameter = NAryTreeDiameter.getNAryTreeDiameter(tree);

        var leafs = IntStream.range(0, n).filter(s -> tree[s].size()==1).boxed().collect(Collectors.toSet());

        for (var src : leafs)
        {
            if(!visitedLeafs.contains(src)){
                var vs = new boolean[n];
                int[] distance = new int[n];
                if(!BFS(tree, distance, vs, src)){
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean BFS(List<Integer>[] tree, int[] distance, boolean[] vs, int src)
    {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.push(src);
        boolean isFound = false;

        while (queue.size() > 0)
        {
            int p = queue.poll();
            vs[p] = true;
            if (distance[p] == diameter)
            {
                visitedLeafs.add(p);
                isFound = true;
            }

            for (var c : tree[p])
            {
                if (!vs[c])
                {
                    distance[c] = distance[p] + 1;
                    queue.push(c);
                }
            }
        }

        return isFound;
    }
}
