package math;

public class CatalanNumber {
    public static long getNumberWithDpSolution(int n)
    {
        int[] catalan = new int[n + 2];

        // Initialize first two values in table
        catalan[0] = catalan[1] = 1;

        for (int i = 2; i <= n; i++)
        {
            catalan[i] = 0;
            for (int j = 0; j < i; j++)
                catalan[i] += catalan[j] * catalan[i - j - 1];
        }

        return catalan[n];
    }

    //Binomial Coefficient
    //Catalan value = C(2n, n)/n+1
    public static long GetNumberWithBinomialCoefficient(int n)
    {
        return BinomialCoefficient.binominalCoefficient(2 * n, n) / (n+1);
    }
}
