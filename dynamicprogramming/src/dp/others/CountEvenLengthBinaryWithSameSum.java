/*
 * Given a number n, find count of all binary sequences of length 2n such that sum of first
   n bits is same as sum of last n bits.

   Examples:
   Input:  n = 1
   Output: 2
   There are 2 sequences of length 2*n, the
   sequences are 00 and 11

    Input:  n = 2
    Output: 2
    There are 6 sequences of length 2*n, the
    sequences are 0101, 0110, 1010, 1001, 0000
    and 1111
 */

package dp.others;

public class CountEvenLengthBinaryWithSameSum {
    //S(n) = Cn0* Cn0 + ...+ Cnn* Cnn
    //Cnk = Cn(k-1)*(n-k+1)/k

    public static int getNumOfSolutions(int n)
    {
        var dp = new int[n+1];
        dp[0] = 1;

        int result = 1;

        for (int i = 1; i <= n; i++)
        {
            dp[i] = dp[i - 1] * (n + 1 - i) / i;
            result += dp[i]*dp[i];
        }

        return result;
    }
}
