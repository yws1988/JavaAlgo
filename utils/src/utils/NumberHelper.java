package utils;

import java.util.ArrayList;
import java.util.List;

public class NumberHelper {
    public static Integer tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static int greatestCommonDivisor(int m, int n)
    {
        while (n > 0)
        {
            int t = n;
            n = m % n;
            m = t;
        }

        return m;
    }

    public static List<Integer> getDigits(int source)
    {
        List<Integer> res = new ArrayList<>();
        while (source > 0)
        {
            var digit = source % 10;
            source /= 10;
            res.add(digit);
        }
        return res;
    }
}
