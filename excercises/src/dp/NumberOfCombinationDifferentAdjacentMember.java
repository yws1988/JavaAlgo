package dp;

import java.util.Arrays;

/***
 Given n strings array of size 2
 Input like this:

 4
 Ecommerce VirtualReality
 Fintech B2BSoftware
 Fintech Ecommerce
 Ecommerce Ecommerce

 The first line is number n with the following n line of string array
 Find the number of combination that all the adjacent member is not duplicated.
 Output 2
 The two solutions are like this:
 Innovation 2 (VirtualReality) Innovation 2 (B2BSoftware) Innovation 1 (Fintech) Innovation 1 (Ecommerce)
 Innovation 2 (VirtualReality) Innovation 2 (B2BSoftware) Innovation 1 (Fintech) Innovation 2 (Ecommerce)

 */

public class NumberOfCombinationDifferentAdjacentMember {
    public static int n;
    public static String[][] strs;

    public static long getNumberOfCombination(){
        if(n==1){
            return 2;
        }

        long[][] dp = new long[n][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        return numberOfCombination(dp);
    }

    public static long numberOfCombination(long[][] dp){

        for (int i = 1; i < n; i++) {
            if(!strs[i][0].equals(strs[i-1][0])){
                dp[i][0] += dp[i-1][0];
            }

            if(!strs[i][0].equals(strs[i-1][1])){
                dp[i][0] += dp[i-1][1];
            }

            if(!strs[i][1].equals(strs[i-1][0])){
                dp[i][1] += dp[i-1][0];
            }

            if(!strs[i][1].equals(strs[i-1][1])){
                dp[i][1] += dp[i-1][1];
            }
        }

        return Arrays.stream(dp[n-1]).sum();
    }
}
