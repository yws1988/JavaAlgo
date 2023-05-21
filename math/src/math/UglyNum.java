package math;

/*
 Ugly numbers are numbers whose only prime factors are 2, 3 or 5.
 The sequence 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, … shows the first 11 ugly numbers. By convention,
 1 is included.
 */

public class UglyNum {
    public static int get(int index)
    {
        var uglyNums = new int[index];

        uglyNums[0] = 1;
        int index1 = 0, index2 = 0, index3 = 0;

        int newUglyNums1 = uglyNums[index1] * 2;
        int newUglyNums2 = uglyNums[index2] * 3;
        int newUglyNums3 = uglyNums[index3] * 5;

        int i = 1;
        while(i < index)
        {
            int newUglyNum = Math.min(newUglyNums1, Math.min(newUglyNums2, newUglyNums3));
            if (uglyNums[i-1] != newUglyNum)
            {
                uglyNums[i++] = newUglyNum;
            }

            if (newUglyNums1 == newUglyNum)
            {
                newUglyNums1 = uglyNums[++index1] * 2;
            }
            else if (newUglyNums2 == newUglyNum)
            {
                newUglyNums2 = uglyNums[++index2] * 3;
            }
            else
            {
                newUglyNums3 = uglyNums[++index3] * 5;
            }
        }

        return uglyNums[index - 1];
    }
}
