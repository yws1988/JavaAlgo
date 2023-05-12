package math;
/*
 * Given to integers a and b. Your task is to print the sum of all the digits appearing in the integers between a and b.
 * For example if a = 5 and b = 11, then answer is 38 (5 + 6 + 7 + 8 + 9 + 1 + 0 + 1 + 1)
 */

import java.util.ArrayList;
import java.util.List;

public class DigitSum {
    public static long getDigitSumOfAllRangeNumbers(int start, int end)
    {
        // long 64-bit signed integer contains maximum 19 digits
        // tight value 0 or 1
        // maximum sum of all the 19 digits 19*9 = 171

        var dp = new long[19][2][171];

        for (int i = 0; i < 19; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                for (int k = 0; k < 171; k++)
                {
                    dp[i][j][k] = -1;
                }
            }
        }

        var maxValueByIndex = new ArrayList<Integer>();
        calculateIndexValues(end, maxValueByIndex);
        long resultEnd = getDigitSumUntil(maxValueByIndex.size() - 1, 1, 0, dp, maxValueByIndex);

        calculateIndexValues(start - 1, maxValueByIndex);
        long resultStart = getDigitSumUntil(maxValueByIndex.size() - 1, 1, 0, dp, maxValueByIndex);

        return resultEnd - resultStart;
    }

    public static int getDigitsSumOfNumber(long number)
    {
        int sum = 0;
        do
        {
            sum += (int)number % 10;
            number /= 10;
        } while (number > 0);

        return sum;
    }

    static long getDigitSumUntil(int index, int tight, int sum, long[][][] dp, List<Integer> maxValueByIndex)
    {
        if (index <= -1) return sum;
        if (dp[index][tight][sum] != -1) return dp[index][tight][sum];
        long result = 0;

        int upperLimit = tight == 0 ? 9 : maxValueByIndex.get(index);

        for (int i = 0; i <= upperLimit; i++)
        {
            int newTight = (tight == 1 && i == upperLimit) ? 1 : 0;
            result += getDigitSumUntil(index - 1, newTight, sum + i, dp, maxValueByIndex);
        }

        return dp[index][tight][sum] = result;
    }

    static void calculateIndexValues(long value, List<Integer> maxValueByIndex)
    {
        maxValueByIndex.clear();
        do
        {
            maxValueByIndex.add((int)value % 10);
            value /= 10;
        } while (value > 0);
    }
}
