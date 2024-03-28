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

            List<Integer> childrenSrc1 = graph.get(graphKey1);
            List<Integer> childrenSrc2 = graph.get(graphKey2);

            if(childrenSrc1 == null || childrenSrc2 == null){
                continue;
            }


            if (!Collections.disjoint(childrenSrc1, childrenSrc2))
            {
                resPath = childNode.path;
                found = true;
                break;
            }

            for (var csr1 : childrenSrc1)
            {
                for (var csr2 : childrenSrc2)
                {
                    for (var d : direction)
                    {
                        var nodeKey = new NodeKey(csr1, csr2, d);
                        if (!vs.contains(nodeKey) && graph.containsKey(new GraphKey(csr1, d)) && graph.containsKey(new GraphKey(csr2, d)))
                        {
                            var node = new Node(nodeKey, null);
                            node.path = new ArrayList<>(childNode.path);
                            node.path.add(d);
                            queue.add(node);

                            vs.add(nodeKey);
                            vs.add(new NodeKey(csr2, csr1, d));
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
