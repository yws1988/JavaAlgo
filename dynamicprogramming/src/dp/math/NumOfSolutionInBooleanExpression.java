package dp.math;
/*
https://www.hackerearth.com/practice/algorithms/dynamic-programming/2-dimensional/practice-problems/algorithm/boolean-expressions-2/

Roy is intrigued by the fact that the evaluated value of boolean expressions can easily vary depending upon the order of evaluation !

For instance][consider the example:

Expression: 1 xor 1 and 0
We can have following two interpretations:
1.  ((1 xor 1) and 0) => (0 and 0) => 0
2.  (1 xor (1 and 0)) => (1 xor 0) => 1
Now][he is further interested into finding the number of possible different parenthesizations such that the result of computation is res.

Input:

The first line of input file contains two space-separated strings. The first string denotes the literals of our boolean expression S][the second string denotes the operators. The next line denotes an integer q][i.e. the number of queries to follow. Each of the next q lines contain two space separated integers l and r and a string res][which is either true or false.

Output:

For each query. output in a new line][the number of ways in which the boolean expression [m][n] of substring [l,r] can be parenthesized so that the expression (l, r) evaluates to res. As the output can be very large][please print the answer modulo 1000000009.

Constraints:

1 <= |S| <= 300
1 <= q <= 90000
1 <= l <= r <= |S|
1 <= l <= m <= n <= r <= |S|
 */
public class NumOfSolutionInBooleanExpression{
    static long[][] trueSolutionNum;
    static long[][] falseSolutionNum;
    static long[][] all;
    
    static final int Max = 1000000009;

    public static long calNumOfSolution(boolean[] expression, char[] operators, int l, int r)
    {
        int n = expression.length;
        trueSolutionNum = new long[n][n];
        falseSolutionNum = new long[n][n];
        all = new long[n][n];

        for (int i = 0; i < n; i++)
        {
            if (expression[i])
            {
                trueSolutionNum[i][i] = 1;
            }
            else
            {
                falseSolutionNum[i][i] = 1;
            }

            all[i][i] = 1;
        }

        for (int interval = 1; interval < n; interval++)
        {
            for (int i = 0; i < n; i++)
            {
                int j = i + interval;
                if (j >= n)
                {
                    break;
                }

                for (int k = i; k < j; k++)
                {
                    if (operators[k] == 'a')
                    {
                        trueSolutionNum[i][j] += (trueSolutionNum[i][k] * trueSolutionNum[k+1][j]);
                        falseSolutionNum[i][j] += ((all[i][k]* all[k + 1][j]) -(trueSolutionNum[i][k] * trueSolutionNum[k + 1][j]));
                    }
                    else if (operators[k] == 'o')
                    {
                        falseSolutionNum[i][j] += (falseSolutionNum[i][k] * falseSolutionNum[k + 1][j]);
                        trueSolutionNum[i][j] += ((all[i][k] * all[k + 1][j]) - (falseSolutionNum[i][k] * falseSolutionNum[k + 1][j]));
                    }
                    else
                    {
                        trueSolutionNum[i][j] += (trueSolutionNum[i][k] * falseSolutionNum[k + 1][j]) + (falseSolutionNum[i][k] * trueSolutionNum[k + 1][j]);
                        falseSolutionNum[i][j] += (trueSolutionNum[i][k] * trueSolutionNum[k + 1][j]) + (falseSolutionNum[i][k] * falseSolutionNum[k + 1][j]);
                    }

                    trueSolutionNum[i][j] %= Max;
                    falseSolutionNum[i][j] %= Max;
                }

                all[i][j] = trueSolutionNum[i][j] + falseSolutionNum[i][j];
            }
        }

        return trueSolutionNum[l][r];
    }
}
