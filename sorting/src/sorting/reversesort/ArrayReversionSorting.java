package sorting.reversesort;

/*
 * Give an array int, Sort the array from small value to big value use the following method:
 * To Sort element k, all the elements from 1 to k will be reversed. Get the minimum steps to sort
 * the given array to be an ordered array.
 * For example, for array(3, 2, 1, 4)
 * The minimum step will be 1, we can sort(3) to get ordered array (1, 2, 3, 4)
 */

import utils.ArrayHelper;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class ArrayReversionSorting {
    public static int getMinimumSteps(int[] array)
    {
        int n = array.length;
        var sortedArray = Arrays.copyOf(array, array.length);
        Arrays.sort(sortedArray);

        var queue = new PriorityQueue<Pair>(new PairComparator());

        queue.add(new Pair(array, 0));
        var vs = new HashSet<Integer>();
        int maxStep = 2 * (n - 1);

        while (queue.size() > 0)
        {
            var childPair = queue.poll();
            if (childPair.level >= maxStep) return maxStep;

            if (Arrays.equals(childPair.array,sortedArray))
            {
                return childPair.level;
            }

            vs.add(array.hashCode());

            for (int i = 1; i < n; i++)
            {
                var next = ArrayHelper.inverse(childPair.array, i);
                if (!vs.contains(next.hashCode()))
                {
                    queue.add(new Pair(next, childPair.level + 1));
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    public static class Pair{
        public int[] array;
        public int level;

        public Pair(int[] array, int level) {
            this.array = array;
            this.level = level;
        }
    }

    public static class PairComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.level-o2.level;
        }
    }
}
