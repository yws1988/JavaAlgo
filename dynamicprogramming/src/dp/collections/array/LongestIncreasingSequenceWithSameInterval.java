/*
 * Given a set of numbers, find the Length of the Longest Arithmetic Progression(LLAP) in it(increasing array with the same interval)
 * set[] = {1, 7, 10, 15, 27, 29}
 * output = 3
 * The longest arithmetic progression is {1, 15, 29}
 */

package dp.collections.array;

import java.util.HashMap;

public class LongestIncreasingSequenceWithSameInterval
{
    public static int getMaxLength(int[] arr)
    {
        int len = arr.length;
        var dic = new HashMap<Item, Integer>();
        for (int i = 1; i < len; i++)
        {
            for (int j = i - 1; j >= 0; j--)
            {
                int margin = arr[i] - arr[j];
                if (margin > 0)
                {
                    dic.put(new Item(i, margin), dic.getOrDefault(new Item(j, margin), 1)+1);
                }
            }
        }

        return dic.values().stream().max(Integer::compare).get();
    }

    public static class Item{
        public int index;
        public int margin;

        public Item(int index, int margin) {
            this.index = index;
            this.margin = margin;
        }
    }
}
