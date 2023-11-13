package branchAndBounds;

/*  Given a 3×3 board with 8 tiles(every tile has one number from 1 to 8) and
    one empty space.The objective is to place the numbers on tiles to match
    final configuration using the empty space.We can slide four adjacent
    (left, right, above and below) tiles into the empty space.

    Matrix from:
    1 2 3
    5 6 0
    7 8 4

    To:
    1 2 3
    5 8 6
    0 7 4

    Output the movement:
    1 2 3
    5 6 0
    7 8 4

    1 2 3
    5 0 6
    7 8 4

    1 2 3
    5 8 6
    7 0 4

    1 2 3
    5 8 6
    0 7 4 */


import java.util.Comparator;
import java.util.PriorityQueue;

public class EightPuzzles
{
    public static int[][] src;
    public static int[][] des;
    public static int N = 3;
    public static int[] Xs = { -1, 0, 1, 0 };
    public static int[] Ys = { 0, 1, 0, -1 };

    public static void search(int[][] src, int[][] des) throws CloneNotSupportedException {
        src = src;
        des = des;

        Node node = new Node(src, 0);
        node.cost = getCost(node);

        PriorityQueue<Node> queue = new PriorityQueue<Node>(new NodeComparator());
        queue.add(node);

        while (queue.size() > 0)
        {
            var n = queue.poll();

            if (n.cost == 0)
            {
                print(n);
                break;
            }

            for (int i = 0; i < 4; i++)
            {
                int tx = Xs[i] + n.X;
                int ty = Ys[i] + n.Y;

                if (n.Parent != null && n.Parent.X == tx && n.Parent.Y == ty) continue;

                if (isSafe(tx, ty))
                {
                    var nextNode = n.clone();
                    nextNode.level++;
                    nextNode.N[nextNode.X][nextNode.Y] = nextNode.N[tx][ty];
                    nextNode.N[tx][ty] = 0;
                    nextNode.X = tx;
                    nextNode.Y = ty;
                    nextNode.cost = getCost(nextNode);
                    queue.add(nextNode);
                }
            }
        }
    }

    static boolean isSafe(int x, int y)
    {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static int getCost(Node node)
    {
        int c = 0;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if (des[i][j] != 0 && des[i][j] != node.N[i][j])
                {
                    c++;
                }
            }
        }

        return c;
    }


    static void print(Node node)
    {
        while (node != null)
        {
            for (int i = 0; i < N; i++)
            {
                for (int j = 0; j < N; j++)
                {
                    System.out.println(node.N[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

            node = node.Parent;
        }
    }

    public static class Node
    {
        public int[][] N;
        public int cost;
        public int level;
        public int X;
        public int Y;
        public Node Parent;
        public Node(int[][] n, int l)
        {
            N = n;
            level = l;
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    if (N[i][j] == 0)
                    {
                        X = i;
                        Y = j;
                        break;
                    }
                }
            }
        }

        public Node clone() throws CloneNotSupportedException {
            var copyNode = (Node) super.clone();
            copyNode.Parent = this;
            int[][] n = new int[3][3];
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    n[i][j] = N[i][j];
                }
            }
            copyNode.N = n;
            return copyNode;
        }
    }

    public static class NodeComparator implements Comparator<Node>{
        @Override
        public int compare(Node o1, Node o2) {
            return (o1.cost + o1.level) - (o2.cost + o2.level);
        }
    }


}

