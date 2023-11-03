package dp.collections.array;
/*
    Given a String str which contains only character A and B.
    Find the maximum value (number of A - number of B) in subString

    for example: BBABAAABABBB the maximum value is 3 in subString AAABA
*/

public class MaximumNumOfDifferenceAThanBInSubArray {
    public static int getMaximumDifference(String str)
    {
        int bn=0, an=0, grn=0;
        int max = 0;

        int len = str.length();
        for (int i = 0; i < len; i++)
        {
            if (str.charAt(i) == 'B')
            {
                bn++;

                if (bn >= an)
                {
                    bn = 0;
                    an = 0;
                }
            }
            else
            {
                an++;

                if (an > bn)
                {
                    max = Math.max(max, an-bn);
                }
            }
        }

        return max;
    }
}
