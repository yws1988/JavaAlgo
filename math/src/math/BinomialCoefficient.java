package math;

public class BinomialCoefficient
{
    public static long binominalCoefficient(int n, int k)
    {
        // Since C(n, k) = C(n, n-k)
        if (n - k < k) k = n - k;
        int res = 1;
        for (int i = 0; i < k; i++)
        {
            res *= (n - i);
            res /= (i + 1);
        }

        return res;
    }
}
