package bitmasking;/*
    There are 100 different types of caps each having a unique id from 1 to 100. Also,
    there are ‘n’ persons each having a collection of a variable number of caps.
    One day all of these persons decide to go in a party wearing a cap but to look unique they decided that none of them will
    wear the same type of cap.
    Count the total number of arrangements or ways such that none of them is wearing the same type of cap.
    Constraints: 1 <= n <= 10 Example:
    The first line contains the value of n, next n lines contain collections
    of all the n persons.
    Input:
    3
    5 100 1     // Collection of the first person.
    2           // Collection of the second person.
    5 100       // Collection of the third person.
    Output:
    4
    Explanation: All valid possible ways are (5, 2, 100),  (100, 2, 5), (1, 2, 5) and  (1, 2, 100)
*/

import java.util.ArrayList;
import java.util.List;

public class NumberOfUniqueCollections
{
    static int[][] dp;

    public static int getNumOfCollections(List<Integer>[] lists)
    {
        int n = lists.length;
        int size = 1 << n;
        int maxId = 100;
        dp = new int[1025][maxId + 1];
        var positionsOfIdList = new ArrayList[maxId + 1];

        for (int i = 0; i < n; i++)
        {
            for (var num : lists[i])
            {
                positionsOfIdList[num].add(i);
            }
        }

        //  ----------------------------------------------------
        //   All mask is used to check of all persons
        //   are included or not, set all n bits as 1

        int allmask = ((1 << n) - 1);

        //   Initialize all entries in dp as -1
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j <= maxId; j++)
            {
                dp[i][j] = -1;
            }
        }

        return countWaysUtil(allmask, maxId, positionsOfIdList);
    }

    static int countWaysUtil(int mask, int i, ArrayList<Integer>[] positionsOfIdList)
    {
        if (dp[mask][i] != -1) return dp[mask][i];

        if (i == 0 && mask != 0) return dp[mask][i] = 0;

        if (mask == 0) return dp[mask][i] = 1;

        var positionsOfId = positionsOfIdList[i];
        int total = 0;
        for (var p : positionsOfId)
        {
            if ((mask >> p & 1) == 1)
            {
                total += countWaysUtil(mask & (~(1 << p)), i - 1, positionsOfIdList);
            }
        }

        return dp[mask][i] = countWaysUtil(mask, i - 1, positionsOfIdList) + total;
    }
}
