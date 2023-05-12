package math;

public class ModularExponentiation {
    // calculate pow(a, b)/mod
    public static long ModularOfPow(long a, long b, int mod)
    {
        long ans = 1;
        while (b > 0)
        {
            if ((b & 1) == 1)
            {
                ans = (ans * a) % mod;
            }

            a = (a * a) % mod;
            b >>= 1;
        }

        return ans;
    }
}
