package branchAndBounds;

/*
Let there be N workers and N jobs. Any worker can be assigned to perform any job,
incurring some cost that may vary depending on the work-job assignment.
It is required to perform all jobs by assigning exactly one worker to each job and
exactly one job to each agent in such a way that the total cost of the assignment is minimized.
 */

import java.util.Arrays;
import java.util.Comparator;

public class JobAssignment {
    public static int N;
    public static double[][] G;

    public static void Start(double[][] jobs)
    {
        N = jobs.GetLength(0);
        G = jobs;

        NodeJob root = new NodeJob(0, -1);
        bool[] avs = new bool[N];
        for (int i = 0; i < N; i++)
        {
            avs[i] = true;
        }
        root.available = avs;
        root.parent = null;

        PriorityQueue<NodeJob> queue = new PriorityQueue<NodeJob>();
        queue.Enqueue(root);
        while (queue.Count() > 0)
        {
            var min = queue.Dequeue();
            if (min.Worker == N - 1)
            {
                Print(min);
                return;
            }

            for (int i = 0; i < N; i++)
            {
                if (min.available[i])
                {
                    var child = min.Clone();
                    child.P = min;
                    child.available[i] = false;
                    child.Job = i;
                    child.C = min.C + G[child.Worker][i];
                    child.Bound = GetBound(child);
                    queue.Enqueue(child);
                }
            }
        }
    }

    public static void Print(NodeJob node)
    {
        Console.WriteLine(node.cost);
        while (node.parent != null)
        {
            Console.WriteLine(node.worker +" : "+node.job);
            node = node.parent;
        }
    }

    public static double GetBound(NodeJob n)
    {
        double b = n.cost;
        for (int i = n.worker +1; i < N; i++)
        {
            double min = double.MaxValue;
            for (int j = 0; j < N; j++)
            {
                if (n.available[j] && G[i][j]<min)
                {
                    min = G[i][j];
                }
            }
            b += min;
        }

        return b;
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
