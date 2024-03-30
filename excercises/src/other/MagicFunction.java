package other;

import java.util.Arrays;

public class MagicFunction {
    public static int n, k;
    public static int[] fs;

    public static int getTimesToReachPointHalfOfN()
    {
        var mapping = new int[n + 1][n + 1];

        for (int i = 0; i < n; i++)
        {
            int min = Math.min(fs[i], fs[i + 1]);
            int max = Math.max(fs[i], fs[i + 1]);

            for (int j = min; j < max; j++)
            {
                // map from i to j
                mapping[i][j] = 1;
            }
        }

        int[] timesToReachPoint = new int[n];

        for (int ki = 0; ki < k; ki++)
        {
            int[] temp = new int[n];
            Arrays.fill(temp, 1);

            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    temp[j] += mapping[i][j] * timesToReachPoint[i];
                    temp[j] %= 1000;
                }
            }

            timesToReachPoint = Arrays.copyOf(temp, n);
        }

        return timesToReachPoint[n/2];
    }
}
