package math;

/*
    https://www.hackerearth.com/practice/algorithms/dynamic-programming/introduction-to-dynamic-programming-1/practice-problems/algorithm/panda-and-numbers/

    Given a number, he subtracts it with squares of any one particular digit of that number to get new numbers.
    This operation can be applied any number of times (possibly zero) till he obtains a pandatic number.
    If he is able to reach to a pandatic number then he wins.
    A pandatic number is a number which can be expressed in the form Math.Pow(A, A), where A is a positive integer.

    For example: 31 is not pandatic number.
    31-3*3=22
    22-2*2=18
    18-1=17
    17-1=16
    ....
    11-1=10
    10-1=9
*/

public class PandaticNumber {
    static final int MAX = 1000001;

    public static boolean isPandaticNumber(int num)
    {
        var yes = new boolean[MAX];
        for (int i = 1; i < 8; i++)
        {
            int x = (int)Math.pow(i, i);
            yes[x] = true;
        }

        for (int i = 1; i < MAX; i++)
        {
            if (yes[i])
                continue;

            String str = String.valueOf(i);
            for (int j = 0; j < str.length(); j++) {
                Character ch = str.charAt(j);
                int x = i - (ch - '0') * (ch - '0');
                yes[i] |= (x > 0 && yes[x]);
            }
        }

        return yes[num];
    }
}
