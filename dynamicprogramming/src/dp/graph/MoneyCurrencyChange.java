package dp.graph;

public class MoneyCurrencyChange {

    /***
     * @param currencyExchange
     * @param level num of times to do the exchange
     * @param num number of the currencies
     * @return
     */
    public static double getMaximumMoney(double[][] currencyExchange, int level, int num)
    {
        var dp = new double[level + 1][num + 1];

        for (int i = 0; i < num; i++)
        {
            dp[1][i] = currencyExchange[0][i];
        }

        for (int i = 1; i < level; i++)
        {
            for (int j = 0; j < num; j++)
            {
                for (int k = 0; k < num; k++)
                {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] * currencyExchange[k][j]);
                }
            }
        }

        for (int k = 0; k < num; k++)
        {
            dp[level][0] = Math.max(dp[level][0], dp[level - 1][k] * currencyExchange[k][0]);
        }

       return dp[level][0] * 10000;
    }
}
