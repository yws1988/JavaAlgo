package search.collections.array;

/*
Given an array of integers nums and an integer k,
return the total number of continuous segments whose sum equals to k.
if the length of segment[l, r] is even:
sum = Cl*C(l+1)+  C(l+2)*C(l+3)+......+C(r-1)*C(r)
if the length of segment[l, r] is odd:
sum = Cl*C(l+1)+  C(l+2)*C(l+3)+......+C(r-1)*1

For example array: 5 0 5 0
if k = 0
The number of segments is 7
*/

import java.util.HashMap;

public class NumOfCustomSubArraysEqualsToK {
    public static long getNum(Long[] array, int k)
    {
        var dic1 = new HashMap<Long, Long>();
        var dic2 = new HashMap<Long, Long>();

        dic1.put(0L, 1L);
        dic2.put(0L, 1L);

        long sum1 = 0;
        long sum2 = 0;
        long res = 0;

        for (int i = 1; i < array.length; i++)
        {
            if (i % 2 == 1)
            {
                sum1 += array[i] * array[i - 1];
                res += dic1.getOrDefault(sum1 - k, 0L);
                dic1.put(sum1, dic1.getOrDefault(sum1, 0L) + 1);
            }
            else
            {
                sum2 += array[i] * array[i - 1];
                res += dic2.getOrDefault(sum2 - k, 0L);
                dic2.put(sum2, dic2.getOrDefault(sum2, 0L)+1);
            }
        }

        sum1 = 0;
        sum2 = 0;
        dic1.clear();
        dic2.clear();

        dic1.put(0L, 1L);
        dic2.put(0L, 1L);

        if (array[0] == k) res++;
        if (array[1] == k) res++;

        for (int i = 2; i < array.length; i++)
        {
            if (i % 2 == 1)
            {
                sum1 += array[i - 1] * array[i - 2];
                dic1.put(sum1, dic1.getOrDefault(sum1, 0L)+1);
                res += dic1.getOrDefault(sum1 + array[i] - k, 0L);

            }
            else
            {
                sum2 += array[i - 1] * array[i - 2];
                dic2.put(sum2, dic2.getOrDefault(sum2, 0L));
                res += dic2.getOrDefault(sum2 + array[i] - k, 0L);
            }
        }

        return res;
    }
}
