package graph.tree.diameter;
/*
The diameter of an N-ary tree is the longest path present between any two nodes of the tree.
These two nodes must be two leaf nodes.
 */
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class NAryTreeDiameter
{
    public static int getNAryTreeDiameter(List<Integer>[] tree)
    {
        int n = tree.length;
        var depth = new int[n];

        return getDiameter(tree, n, depth);
    }

    private static int getDiameter(List<Integer>[] tree, int n, int[] depth)
    {
        Stack<Integer> stack = new Stack<Integer>();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        var vs = new boolean[n];
        vs[0] = true;

        while (queue.size() > 0)
        {
            int p = queue.poll();
            vs[p] = true;
            stack.push(p);
            for (var c : tree[p])
            {
                if (!vs[c])
                {
                    queue.push(c);
                }
            }
        }

        vs = new boolean[n];
        int diameter = 0;

        while (stack.size() > 0)
        {
            int p = stack.pop();
            vs[p] = true;

            int d = 1;
            for (var c : tree[p])
            {
                if (vs[c])
                {
                    d = Math.max(d, 1 + depth[c]);
                }
            }

            depth[p] = d;
            diameter = Math.max(diameter, getSumOfTowGreatestChildDepth(tree, n, depth, p));
        }

        return diameter;
    }

    private static int getSumOfTowGreatestChildDepth(List<Integer>[] tree, int n, int[] depth, int root)
    {
        int max1 = 0, max2 = 0;
        for (var c : tree[root])
        {
            if (max1 < depth[c])
            {
                max2 = max1;
                max1 = depth[c];
            }
        }

        return max1 + max2 + 1;
    }
}
