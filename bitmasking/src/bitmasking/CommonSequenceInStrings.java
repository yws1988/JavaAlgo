package bitmasking;

import utils.StringHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
Common sequence in serveral strings, number of strings is greater than 2
For example:
abdddcdef
dcdefgh
acdcdegfmg

Output: dcdef
*/
public class CommonSequenceInStrings
{
    public static int n;
    public static String[] strs;

    public static String getCommonSequence()
    {
        int len = (int)Math.pow(2, 10) - 1;
        int max = 0;
        String str = "";

        for (int i = len; i >= 0; i--)
        {
            List<Character> cs = new ArrayList<>();

            for (int j = 0; j < 10; j++)
            {
                if (check(j, i) == 1)
                {
                    cs.add(strs[0].charAt(j));
                }

                if (cs.size() > max)
                {

                    var sequenceStr = cs.stream().map(s -> String.valueOf(s)).collect(Collectors.joining());

                    boolean isValid = true;
                    for (int k = 1; k < n; k++)
                    {
                        if (!StringHelper.isSequenceInString(sequenceStr, strs[k]))
                        {
                            isValid = false;
                            break;
                        }
                    }

                    if (isValid)
                    {
                        str = sequenceStr;
                        max = str.length();
                    }
                }
            }
        }

        return str == "" ? "KO" : str;
    }

    static int check(int i, int value)
    {
        return 1 & value >> i;
    }
}
