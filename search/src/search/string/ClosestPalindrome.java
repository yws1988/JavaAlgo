package search.string;

// Given a number N.our task is to find the closest Palindrome number
// whose absolute difference with given number is minimum and absolute
// difference must be greater than 0.

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ClosestPalindrome {
    public static String getClosestPalindromeString(String str)
    {
        long rN = Long.parseLong(str);
        long len = str.length();

        //number like 1000 expected 999
        if (len == 1 || (rN == (long)Math.pow(10, len - 1)))
        {
            return String.valueOf(rN - 1);
        }

        //number like 999 expected 1001
        if (len>2 && rN == (long)Math.pow(10, len)-1)
        {
            return String.valueOf(rN + 2);
        }

        var pQ = new PriorityQueue<Long>((f, n) -> {
            long v1 = Math.abs(f - rN);
            long v2 = Math.abs(n - rN);
            if (v1 == v2)
            {
                return (int)Math.signum(f - n);
            }
            else
            {
                return (int)Math.signum(v1 - v2);
            }
        });

        int l = str.length() / 2;
        int pl = str.length() % 2 == 0 ? l : l + 1;

        var pStr = str.substring(0, pl);
        var list = getPossibleValues(pStr);
        long value = Long.parseLong(pStr);
        list.addAll(getPossibleValues(String.valueOf(value+1)));

        long valueR1 = value - 1;
        if (valueR1 == 0)
        {
            valueR1 = 9;
        }

        list.addAll(getPossibleValues(String.valueOf(valueR1)));
        list.remove(rN);

        for (var item : list) {
            pQ.add(item);
        }

        return pQ.peek().toString();
    }

    static List<Long> getPossibleValues(String root)
    {
        var list = new ArrayList<Long>();
        int len = root.length();
        long value = Long.parseLong(root);
        list.add(value);
        long rValue = Long.parseLong(new StringBuffer(root).reverse().toString());
        list.add(value * (long)Math.pow(10, len) + rValue);

        if (len > 1)
        {
            list.add(value * (long)Math.pow(10, len-1) + Long.parseLong(new StringBuffer(root.substring(0, len - 1)).reverse().toString()));
        }

        return list;
    }
}
