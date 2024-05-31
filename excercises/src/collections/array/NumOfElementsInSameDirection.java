package collections.array;

public class NumOfElementsInSameDirection {
    public static int[] ns;
    public static int n;
    static int[] values;
    static int[] reverseValues;

    public void generateArrayValue()
    {
        values = new int[n];
        process();

        for (int i = 0; i < n-1; i++)
        {
            ns[i] = ns[i] == 1 ? 0 : 1;
        }
        reverseValues = new int[n];
        process();
    }

    static void process()
    {
        values[0] = 1;

        int seed = 1;
        for (int i = 0; i < n - 1; i++)
        {
            if (ns[i] == 0)
            {
                seed++;
            }
            else
            {
                seed = 1;
            }

            values[i + 1] = seed;
        }

        seed = 0;

        for (int i = n - 2; i >= 0; i--)
        {
            if (ns[i] == 1)
            {
                seed++;
            }
            else
            {
                seed = 0;
            }
            values[i] += seed;
        }
    }

}
