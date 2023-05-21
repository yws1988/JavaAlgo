package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper
{
    /// <summary>
    /// Replace the string characters with specified mapped character array
    /// </summary>
    /// <param name="input">The origninal string</param>
    /// <param name="src">Searched characters</param>
    /// <param name="des">Replaced characters</param>
    /// <returns></returns>
    public static String strReplace(String input, Character[] src, Character[] des)
    {
        Map<Character, Character> dic = new HashMap<>();
        for (int i = 0; i < src.length; i++)
        {
            dic.put(src[i], des[i]);
        }

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < input.length(); i++)
        {
            if (dic.containsKey(input.charAt(i)))
            {
                str.append(dic.get(input.charAt(i)));
            }
            else
            {
                str.append(input.charAt(i));
            }
        }

        return str.toString();
    }

    public static String strReplace(String input, Map<Character, Character> dic)
    {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < input.length(); i++)
        {
            if (dic.containsKey(input.charAt(i)))
            {
                str.append(dic.get(input.charAt(i)));
            }
            else
            {
                str.append(input.charAt(i));
            }
        }

        return str.toString();
    }

    /// <summary>
    /// Determine if string contains the sequence
    /// </summary>
    /// <param name="sequence"></param>
    /// <param name="str"></param>
    /// <returns></returns>
    public static boolean IsSequenceInString(String sequence, String str)
    {
        int i = 0, j = 0;

        while (i < sequence.length() && j < str.length())
        {
            if (sequence.charAt(i) == str.charAt(j))
            {
                i++;
                j++;
            }
            else
            {
                j++;
            }
        }

        if (i == sequence.length())
        {
            return true;
        }

        return false;
    }

    /**
     * String "abaca", reg "a.a"
     * returns [aba, aca]
     * @param text
     * @param regex
     * @return
     */
    public static List<String> getAllMatches(String text, String regex) {
        List<String> matches = new ArrayList<String>();
        Matcher m = Pattern.compile("(?=(" + regex + "))").matcher(text);
        while(m.find()) {
            matches.add(m.group(1));
        }
        return matches;
    }
}
