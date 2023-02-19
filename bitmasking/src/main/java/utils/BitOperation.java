package utils;

public class BitOperation {
    public static int set(int value, int i)
    {
        return value | (1 << i);
    }

    public static int unset(int value, int i)
    {
        return value & ~(1 << i);
    }

    public static int check(int value, int i)
    {
        return 1 & value >> i;
    }

    //count(mask) – the number of non-zero bits in mask
    public static int countBit(int value)
    {
        int c = 0;
        do
        {
            if ((value & 1) == 1) c++;
        }
        while ((value >>= 1) > 0);

        return c;
    }
}
