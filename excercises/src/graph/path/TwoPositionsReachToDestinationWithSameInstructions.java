package graph.path;

import java.util.*;

public class TwoPositionsReachToDestinationWithSameInstructions  {

    /***
     * D => Droit, G=>Gauche, T=>Tout droit
     */
    static List<String> direction =List.of("D", "G", "T");

    public static List<String> getPath(HashMap<GraphKey, List<Integer>> graph, int srcX, int srcY)
    {
        var vs = new HashSet<NodeKey>();
        var path = new ArrayList<String>();

        var queue = new LinkedList<Node>();

        for (var d : direction)
        {
            if (graph.containsKey(new GraphKey(srcX, d)) && graph.containsKey(new GraphKey(srcY, d)))
            {
                var nodeKey = new NodeKey(srcX, srcY, d);
                var node = new Node(nodeKey, List.of(d));

                queue.add(node);

                vs.add(nodeKey);
                vs.add(new NodeKey(srcY, srcX, d));
            }
        }

        boolean found = false;
        List<String> resPath = null;

        while (queue.size() > 0)
        {
            var childNode = queue.pop();

            var graphKey1 = new GraphKey(childNode.key.src1, childNode.key.dir);
            var graphKey2 = new GraphKey(childNode.key.src2, childNode.key.dir);

            Intersect these two collections
            if (graph.get(graphKey1).(graph.get(graphKey2)).Any())
            {
                res = cNode.Path;
                found = true;
                break;
            }

            foreach (var csr1 in dic[graphKey1])
            {
                foreach (var csr2 in dic[graphKey2])
                {
                    foreach (var d in direction)
                    {
                        if (!vs.Contains((csr1, csr2, d)) && dic.ContainsKey((csr1, d)) && dic.ContainsKey((csr2, d)))
                        {
                            var node = new Node(csr1, csr2, d);
                            node.path = new List<string>(cNode.Path);
                            node.path.Add(d);
                            queue.Enqueue(node);

                            vs.Add((csr1, csr2, d));
                            vs.Add((csr2, csr1, d));
                        }
                    }
                }
            }
        }

        return resPath;
    }

    public static class GraphKey{
        public int src;
        public String dir;

        public GraphKey(int src, String dir) {
            this.src = src;
            this.dir = dir;
        }
    }

    public static class NodeKey{
        public int src1;
        public int src2;
        public String dir;

        public NodeKey(int x, int y, String direction)
        {
            src1 = x;
            src2 = y;
            dir = direction;
        }
    }

    public static class Node
    {
        public NodeKey key;
        public List<String> path;

        public Node(NodeKey key, List<String> path) {
            this.key = key;
            this.path = path;
        }
    }
}
