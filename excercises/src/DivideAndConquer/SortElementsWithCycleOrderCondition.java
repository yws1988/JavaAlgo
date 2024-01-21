package DivideAndConquer;

/*
    Given a list of node, each node contains three integer, sort the node that should satisfy the following condition,
    nodeA > nodeB if at least two fields of nodeA is lower than nodeB. Give at least one solution of nodes.
    For example:
    Nodes:
    node1 : 20 2 53
    node2 : 10 39 1
    node3 : 9 3 9999
    Outuput: node2 node1 node3
    Here, the number 2 node can be ranked right ahead of the number 1 node because. The number 1 node can be right ahead of
    number 3.
 */

import java.util.ArrayList;
import java.util.List;

public class SortElementsWithCycleOrderCondition {
    public static int n;

    public static List<Integer> sort(List<Node> nodes)
    {
        var res = new ArrayList<Integer>();
        divide(nodes, res);
        return res;
    }

    static void divide(List<Node> list, List<Integer> res)
    {
        if (list.size() == 0)
        {
            return;
        }

        var leftList = new ArrayList<Node>();
        var rightList = new ArrayList<Node>();


        for (int i = 1; i < list.size(); i++)
        {
            if (list.get(0).compareTo(list.get(i)) <= 0)
            {
                rightList.add(list.get(i));
            }
            else
            {
                leftList.add(list.get(i));
            }
        }

        divide(leftList, res);
        res.add(list.get(0).idx);
        divide(rightList, res);
    }


    public static class Node implements Comparable<Node>
    {
        public int idx;
        public int a;
        public int b;
        public int c;

        public Node(int idx, int a, int b, int c) {
            this.idx = idx;
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Node other) {
            return (int)(Math.signum(a - other.a) + Math.signum(b - other.b) + Math.signum(c - other.c));
        }
    }
}
