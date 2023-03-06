package bitmasking;

public class BridgeAndTorch
{
    public static int[][] dp;

    public static int getMinimunTime(int[] times)
    {
        int n = times.length;
        int len = 1 << n;

        // represent the left mask and right mask
        dp = new int[len][2];
        for (int i = 0; i < len; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        int fullMask = len - 1;

        // 0 go cross river, 1 go back to river
        dp[0][0] = 0;
        dp[0][1] = 0;

        return getMinTime(times, fullMask, 0, n);
    }

    static int getMinTime(int[] times, int leftMask, int turn, int n)
    {
        if (dp[leftMask][turn] != Integer.MAX_VALUE)
        {
            return dp[leftMask][turn];
        }

        if (turn == 0)
        {
            for (int m = 0; m < n; m++)
            {
                for (int h = m + 1; h < n; h++)
                {
                    if ((leftMask & (1 << m)) > 0 && (leftMask & (1 << h)) > 0)
                    {
                        dp[leftMask][0] = Math.min(dp[leftMask][0], Math.max(times[m], times[h]) + getMinTime(times, leftMask ^ (1 << m) ^ (1 << h), 1, n));
                    }
                }
            }
        }
        else if (turn == 1)
        {
            int minIndex = getMinIndex(times, ((1 << n) - 1) ^ leftMask, n);
            dp[leftMask][1] = Math.min(dp[leftMask][1], times[minIndex] + getMinTime(times, leftMask | 1 << minIndex, 0, n));
        }

        return dp[leftMask][turn];
    }

    static int getMinIndex(int[] arr, int mask, int n)
    {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < n; i++)
        {
            if ((mask & (1 << i)) > 0)
            {
                if (arr[i] < min)
                {
                    min = arr[i];
                    index = i;
                }
            }
        }

        return index;
    }
}