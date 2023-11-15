package math;

import java.util.Arrays;
import java.util.stream.Collectors;

/*
https://www.hackerearth.com/problem/algorithm/next-lucky-number/?source=list_view

Lucky numbers are defined as the numbers consisting only of digits 3 and 5. So,
 given a number N, you have to print the least lucky number strictly greater than N.
 */
public class ClosestNumberGreaterThanNumber {
    public static int[] digits;
    static int firstValue;

    public static long getClosestNumber(long N)
    {
        String str = String.valueOf(N);
        int len = str.length();

        digits = new int[len];
        for (int i = 0; i < len; i++) {
            digits[i]=str.charAt(i)-'0';
        }

        firstValue = 0;

        int index = 0;
        while (index < len)
        {
            if (digits[index] == 3 || digits[index] == 5)
            {
                index++;
            }
            else
            {
                break;
            }
        }

        if (index == len)
        {
            assignValueFromIndex(len - 1, 1);
        }
        else
        {
            assign(index);
        }

        var digitsStr = Arrays.stream(digits).mapToObj(s -> String.valueOf(s)).collect(Collectors.joining());

        if (firstValue > 0)
        {
            return Long.parseLong(String.valueOf(firstValue)+digitsStr);
        }
        else
        {
            return Long.parseLong(digitsStr);
        }
    }

    static void assignValueFromIndex(int idx, int add)
    {
        if (idx < 0 && add == 1) firstValue = 3;
        if (idx < 0) return;

        int nn = digits[idx];
        if (add == 1)
        {
            if (nn == 3)
            {
                digits[idx] = 5;
                assignValueFromIndex(idx - 1, 0);
            }
            else
            {
                digits[idx] = 3;
                assignValueFromIndex(idx - 1, 1);
            }
        }
    }

    static void assign(int idx)
    {
        int digit = digits[idx];
        if (digit < 3)
        {
            digits[idx] = 3;
        }
        else if (digit > 3 && digit < 5)
        {
            digits[idx] = 5;
        }
        else
        {
            digits[idx] = 3;
            assignValueFromIndex(idx - 1, 1);
        }

        for (int i = idx+1; i < digits.length; i++)
        {
            digits[i] = 3;
        }
    }
}
