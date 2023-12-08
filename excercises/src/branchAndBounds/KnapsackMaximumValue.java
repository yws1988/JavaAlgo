package branchAndBounds;

/// <summary>
/// Given two double arrays val[0..n-1] and wt[0..n-1] that represent values and weights associated with n items
/// respectively. Find out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal
/// to Knapsack capacity cap.
/// </summary>

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class KnapsackMaximumValue {
    public static double getMaxValue(double[] weights, double[] values, double capacity)
    {
        var orderedNodesByAverageValue = new Node[weights.length];
        for (int i = 0; i < weights.length; i++)
        {
            orderedNodesByAverageValue[i] = new Node(weights[i], values[i],  values[i] / weights[i]);
        }

        Arrays.sort(orderedNodesByAverageValue, new NodeComparator());

        LinkedList<CumulativeNode> queue = new LinkedList<>();

        CumulativeNode u, v;
        u = new CumulativeNode(0, 0, -1);
        queue.add(u);
        double maxP = getInitialPossibleMaxValue(orderedNodesByAverageValue, capacity);
        int n = orderedNodesByAverageValue.length;

        while (queue.size() > 0)
        {
            u = queue.poll();
            if (u.level == n - 1) continue;

            int cLevel = u.level + 1;

            v = new CumulativeNode(u.cumulativeWeight + orderedNodesByAverageValue[cLevel].weight, u.cumulativeValue + orderedNodesByAverageValue[cLevel].value, cLevel);
            if (v.cumulativeWeight <= capacity && v.cumulativeValue > maxP)
            {
                maxP = v.cumulativeValue;
            }

            v.bound = getBound(orderedNodesByAverageValue, v, capacity, n);

            if (v.bound > maxP)
            {
                queue.add(v);
            }

            v = new CumulativeNode(u.cumulativeWeight, u.cumulativeValue, cLevel);
            v.bound = getBound(orderedNodesByAverageValue, v, capacity, n);

            if (v.bound > maxP)
            {
                queue.add(v);
            }
        }

        return maxP;
    }

    static double getBound(Node[] orderedNodesByAverageValue, CumulativeNode cumulativeNode, double capacity, int n)
    {
        double w = cumulativeNode.cumulativeWeight;
        if (w > capacity) return 0;

        double bound = cumulativeNode.cumulativeValue;
        int l = cumulativeNode.level + 1;

        while (l < n && w + orderedNodesByAverageValue[l].weight <= capacity)
        {
            w += orderedNodesByAverageValue[l].weight;
            bound += orderedNodesByAverageValue[l].value;
            l++;
        }

        if (l < n)
        {
            bound += orderedNodesByAverageValue[l].average * (capacity - w);
        }

        return bound;
    }

    static double getInitialPossibleMaxValue(Node[] orderedNodesByAverageValue, double capacity)
    {
        double weight = 0;
        double value = 0;

        for (int i = 0; i < orderedNodesByAverageValue.length; i++)
        {
            weight += orderedNodesByAverageValue[i].weight;

            if (weight <= capacity)
            {
                value += orderedNodesByAverageValue[i].value;
            }
            else
            {
                break;
            }
        }

        return value;
    }

    public static class Node
    {
        public double weight;
        public double value;
        public double average;

        public Node(double weight, double value, double average)
        {
            this.weight = weight;
            this.value = value;
            this.average = average;
        }
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            double res = o2.average-o1.average;
            return res > 0 ? 1: res==0 ? 0 : -1;
        }
    }

    public static class CumulativeNode
    {
        public double cumulativeWeight;
        public double cumulativeValue;
        public double bound;
        public int level;

        public CumulativeNode(double cumulativeWeight, double cumulativeValue, int level)
        {
            cumulativeWeight = cumulativeWeight;
            cumulativeValue = cumulativeValue;
            level = level;
        }
    }
}
