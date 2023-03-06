/*
 * Given a 2D array, find the maximum sum subarray in it. 
 * For example, in the following 2D array, the maximum sum subarray is 
 * highlighted with blue rectangle and sum of this subarray is 29.
 * {{1, 2, -1, -4, -20},  
 *  {-8, -3, 4, 2, 1},  
 *  {3, 8, 10, 1, 3},  
 *  {-4, -1, 1, 7, -6}};  
 */
package dp.collections.array;
public class MaximumSumRectangleInMatrix
{
    static int mL, mR, mT, mB;
    static double Max = Double.MIN_VALUE;

    public static double getMaxSum(double[][] arr)
    {
        int row = arr.length;
        int col = arr[0].length;

        for (int left = 0; left < col; left++)
        {
            double[] temp = new double[row];

            for (int right = left; right < col; right++)
            {
                for (int i = 0; i < row; i++)
                {
                    temp[i] += arr[i][right];
                }

                maximumSumOfSubArray(temp, left, right);
            }
        }

        //Console.WriteLine($"Left top {mL}:{mT} Right bottom {mR}:{mB}");

        return Max;
    }

    private static void maximumSumOfSubArray(double[] temp, int left, int right)
    {
        double maxEnding=0, max=temp[0];

        int s = 0;
        int start = 0;
        int end = 0;

        for (int i = 0; i < temp.length; i++)
        {
            maxEnding += temp[i];

            if (max < maxEnding)
            {
                max = maxEnding;
                start = s;
                end = i;
            }

            if (maxEnding < 0)
            {
                maxEnding = 0;
                s = i + 1;
            }
        }

        if (Max < max)
        {
            Max = max;
            mL = left;
            mR = right;
            mT = start;
            mB = end;
        }
    }
}
