package graph.tree;

/*
9 2
1 2
2 1
5 2
4 1
10 0
15 0
21 0
23 1
42 100
 */

import utils.IO;

import java.util.LinkedList;

public class FillTreeWithNodeInOrder {
    public static void printTree(int n, int initialCapacity)
    {
        var tmp = IO.readIntArray();
        n = tmp[0];
        initialCapacity = tmp[1];

        var queue = new LinkedList<Node>();
        queue.add(new Node(0, -1, 0, 1, initialCapacity));

        int numOfElement = 1;

        while (queue.size() > 0)
        {
            var node = queue.poll();

            if (node.parent != -1)
            {
                System.out.println(node.parent + ":" + node.level + ":" + node.sibling);
            }

            int sibling = 1;
            while (sibling <= node.capacity && numOfElement <= n)
            {
                var temp = IO.readIntArray();
                queue.add(new Node(temp[0], node.num, node.level + 1, sibling, temp[1]));
                sibling++;
                numOfElement++;
            }
        }
    }

    public static class Node
    {
        public int num;
        public int parent;
        public int level;
        public int sibling;
        public int capacity;

        public Node(int num, int parent, int level, int sibling, int capacity)
        {
            this.num = num;
            this.parent = parent;
            this.level = level;
            this.sibling = sibling;
            this.capacity = capacity;
        }
    }
}
