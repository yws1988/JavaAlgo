package search.string;

import java.util.Stack;

public class LongestCommonSubstring
{
    public static String getLCS(String str1, String str2)
    {
        int len1 = str1.length();
        int len2 = str2.length();
        var dp = new int[len1][len2];
        int max = -1;
        int maxX = -1;
        int maxY = -1;

        for (int i = 0; i < str1.length(); i++)
        {
            for (int j = 0; j < str2.length(); j++)
            {
                if(i == 0 || j == 0)
                {
                    if (str1.charAt(i) == str2.charAt(j))
                    {
                        dp[i][j] = 1;
                    }
                    else
                    {
                        dp[i][j] = 0;
                    }
                }
                else
                {
                    if(str1.charAt(i) == str2.charAt(j))
                    {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    else
                    {
                        dp[i][j] = 0;
                    }
                }

                if(dp[i][j] > max)
                {
                    max = dp[i][j];
                    maxX = i;
                    maxY = j;
                }


            }
        }

        var lcs = new StringBuilder();

        while (max > 0)
        {
            lcs.append(str1.charAt(maxX));
            maxX--;
            max--;
        }

        return lcs.reverse().toString();
    }
}
