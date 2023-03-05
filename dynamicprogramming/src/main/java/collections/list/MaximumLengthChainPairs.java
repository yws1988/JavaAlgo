/*
 Given n pairs of numbers. In every pair, the first number is always smaller than the second number.
 A pair (c, d) can follow another pair (a, b) if b < c. Chain of pairs can be formed in this fashion.
 Find the longest chain which can be formed from a given set of pairs. Source:

 For example, if the given pairs are {{5, 24}, {39, 60}, {15, 28}, {27, 40}, {50, 90} },
 then the longest chain that can be formed is of length 3, and the chain is {{5, 24}, {27, 40}, {50, 90}}
 */

package collections.list;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaximumLengthChainPairs {
    public static int getMaximumLength(List<Pair> pairs)
    {
        int len = pairs.size();
        var dp = new int[len];

        Collections.sort(pairs, (obj1, obj2)->{return obj1.x-obj2.x;});

        dp[0] = 1;
        for (int i = 1; i < pairs.size(); i++)
        {
            for (int j = 0; j < i; j++)
            {
                if(pairs.get(i).x > pairs.get(j).y)
                {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }

                if (dp[i] == 0) dp[i] = 1;
            }
        }

        return Arrays.stream(dp).max().getAsInt();
    }

    public static class Pair{
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
