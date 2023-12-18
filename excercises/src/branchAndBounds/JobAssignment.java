package branchAndBounds;

/*
Let there be N workers and N jobs. Any worker can be assigned to perform any job,
incurring some cost that may vary depending on the work-job assignment.
It is required to perform all jobs by assigning exactly one worker to each job and
exactly one job to each agent in such a way that the total cost of the assignment is minimized.
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class JobAssignment {
    public static int len;
    public static double[][] graph;

    public static double getLowestCosts(double[][] jobs) throws CloneNotSupportedException {
        len = jobs.length;
        graph = jobs;

        NodeJob root = new NodeJob(0, -1);
        boolean[] avs = new boolean[len];
        for (int i = 0; i < len; i++)
        {
            avs[i] = true;
        }
        root.available = avs;
        root.parent = null;

        var queue = new PriorityQueue<NodeJob>(new NodeJobComparator());
        queue.add(root);
        while (queue.size() > 0)
        {
            var currentNode = queue.poll();
            if (currentNode.worker == len - 1)
            {
                print(currentNode);
                return currentNode.cost;
            }

            for (int i = 0; i < len; i++)
            {
                if (currentNode.available[i])
                {
                    var child = currentNode.clone();
                    child.parent = currentNode;
                    child.available[i] = false;
                    child.job = i;
                    child.cost = currentNode.cost + graph[child.worker][i];
                    child.bound = getBound(child);
                    queue.add(child);
                }
            }
        }

        return Double.MAX_VALUE;
    }

    public static void print(NodeJob node)
    {
        System.out.println(node.cost);
        while (node.parent != null)
        {
            System.out.println(node.worker +" : "+node.job);
            node = node.parent;
        }
    }

    public static double getBound(NodeJob node)
    {
        double bound = node.cost;
        var availableCopy = Arrays.copyOf(node.available, node.available.length);
        for (int i = node.worker +1; i < len; i++)
        {
            double min = Double.MAX_VALUE;
            for (int j = 0; j < len; j++)
            {
                if (availableCopy[j] && graph[i][j]<min)
                {
                    min = graph[i][j];
                    availableCopy[j]=false;
                }
            }
            bound += min;
        }

        return bound;
    }

    public static class NodeJob
    {
        public double cost;
        public double bound;
        public int worker;
        public int job;
        public boolean[] available;
        public NodeJob parent;

        public NodeJob(double cost, int worker)
        {
            this.cost = cost;
            this.worker = worker;
        }

        public NodeJob clone() throws CloneNotSupportedException {
            var node = (NodeJob)super.clone();
            node.available = Arrays.copyOf(this.available, this.available.length);
            node.worker = this.worker + 1;
            node.parent = null;

            return node;
        }
    }

    public static class NodeJobComparator implements Comparator<NodeJob>{

        @Override
        public int compare(NodeJob o1, NodeJob o2) {
            double result = o1.bound - o2.bound;
            if (result > 0) return 1;
            else if (result == 0) return 0;
            else return -1;
        }
    }
}
