package graph.tree.diameter;
/*
The diameter of an N-ary tree is the longest path present between any two nodes of the tree.
These two nodes must be two leaf nodes.
 */
import java.util.LinkedList;
import java.util.List;

public class NAryTreeDiameterWithBFS
{
    public static int max = 0;
    public static DiameterInfo printNAryTreeDiameter(List<Integer>[] tree)
    {
        int n = tree.length;
        var distance = new int[n];
        var vs = new boolean[n];
        int max;
        int src = BFS(tree, distance, vs, 0);
        distance = new int[n];
        vs = new boolean[n];
        max = 0;
        int des = BFS(tree, distance, vs, src);

        return new DiameterInfo(src, des, max+1);
    }

    private static int BFS(List<Integer>[] tree, int[] distance, boolean[] vs, int src)
    {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.push(src);
        int vertex=0;

        while (queue.size() > 0)
        {
            int p = queue.poll();
            vs[p] = true;
            if (distance[p] > max)
            {
                max = distance[p];
                vertex = p;
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

        return vertex;
    }

    public static class DiameterInfo{
        public int src;
        public int des;
        public int diameter;

        public DiameterInfo(int src, int des, int diameter) {
            this.src = src;
            this.des = des;
            this.diameter = diameter;
        }
    }
}
