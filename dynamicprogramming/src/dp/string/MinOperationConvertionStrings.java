/*
    MinOperationConvertionStrings with equal costs:

    Given two strings str1 and str2 and below operations that can performed on str1.
    Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2’.
    You can do the following operations:
    Insert
    Remove
    Replace
    All of the above operations are of equal cost.

    Examples:

    Input:   str1 = "geek", str2 = "gesek"
    Output:  1
    We can convert str1 into str2 by inserting a 's'.
 */

/*
    MinOperationConvertionStrings with different costs:

    Given two strings str1 and str2 and below operations that can performed on str1.
    Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2’.
    You can do the following operations:
    Insert : cost 2
    Remove : cost 2
    Replace : cost 3
    Exchange consecutif characters : cost 3

    Examples:
    Input:   str1 = "algorithme ", str2 = "rythme"
    Output:  11
    We can convert str1 into str2 by delete 4 characters and replace one.
 */

package dp.string;

public class MinOperationConvertionStrings {
    public static int GetMinimumOperationWithEqualCosts(String str1, String str2)
    {
        int m = str1.length()+1;
        int n = str2.length()+1;

        var dp = new int[m][n];

        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (i == 0 || j==0)
                {
                    dp[i][j] = i + j;
                }
            }
        }

        for (int i = 1; i < m; i++)
        {
            for (int j = 1; j < n; j++)
            {
                if (str1.charAt(i - 1)==str2.charAt(j - 1))
                {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else
                {
                    dp[i][j] = getMinValue(dp[i - 1][j - 1] + 3, dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    public static int getMinimumOperationWithDifferentCosts(String str1, String str2)
    {
        int m = str1.length() + 1;
        int n = str2.length() + 1;

        var dp = new int[m][n];

        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (i == 0 || j == 0)
                {
                    dp[i][j] = (i + j)*2;
                }
            }
        }

        for (int i = 1; i < m; i++)
        {
            for (int j = 1; j < n; j++)
            {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else
                {
                    dp[i][j] = getMinValue(dp[i - 1][j - 1] + 3, dp[i - 1][j] + 2, dp[i][j - 1] + 2);

                    if(i>=2 && j >= 2 && str1.charAt(i - 1) == str2.charAt(j - 2) && str1.charAt(i - 2) == str2.charAt(j - 1))
                    {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 2][j - 2] + 3);
                    }
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    static int getMinValue(int n1, int n2, int n3)
    {
        return n1 > n2 ? (n2 > n3 ? n3 : n2) : (n1 > n3 ? n3 : n1);
    }
}
