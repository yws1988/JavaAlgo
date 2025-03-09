package dp;

import dp.others.MaximumValueToReachAtMostWeight;

import java.util.Arrays;
import java.util.Comparator;

public class StonesAndPowderKnapsack {
    public static int getMaxValue(MaximumValueToReachAtMostWeight.Pair[] stones, MaximumValueToReachAtMostWeight.Pair[] powders, int capacity)
    {
        MaximumValueToReachAtMostWeight.getMaxValue(stones, capacity);

        int[][] dp = MaximumValueToReachAtMostWeight.dp;

        Arrays.sort(powders, new PairComparator());
        int result = 0;
        int num = stones.length;
        for (int i = 0; i < capacity + 1; i++)
        {
            result = Math.max(result, dp[i][num - 1] + getMaxValue(capacity - i, powders));
        }

        return result;
    }

    static int getMaxValue(int leftCapacity, MaximumValueToReachAtMostWeight.Pair[] powders)
    {
        int value = 0;

        for (int i = 0; i < powders.length; i++)
        {
            if (leftCapacity >= powders[i].weight)
            {
                value += powders[i].value * powders[i].weight;
                leftCapacity -= powders[i].weight;
            }
            else
            {
                value += powders[i].value * leftCapacity;
                break;
            }
        }

        return value;
    }

    public static class PairComparator implements Comparator<MaximumValueToReachAtMostWeight.Pair> {
        @Override
        public int compare(MaximumValueToReachAtMostWeight.Pair o1, MaximumValueToReachAtMostWeight.Pair o2) {
            return o2.value-o1.value;
        }
    }
}
