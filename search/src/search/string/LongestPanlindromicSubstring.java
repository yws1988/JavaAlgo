package search.string;

public class LongestPanlindromicSubstring
{
    public static String getLongestSubstr(String str)
    {
        int maxLength = 1;
        int start = 0;
        int len = str.length();

        int low, high;

        // One by one consider every character as center point
        // of even and length palindromes
        for (int i = 1; i < len; ++i)
        {
            // Find the longest even length palindrome with center points as i-1 and i.
            low = i - 1;
            high = i;
            while (low >= 0 && high < len && str.charAt(low) == str.charAt(high))
            {
                if (high - low + 1 > maxLength)
                {
                    start = low;
                    maxLength = high - low + 1;
                }

                --low;
                ++high;
            }

            // Find the longest odd length palindrome with center point as i
            low = i - 1;
            high = i + 1;
            while (low >= 0 && high < len && str.charAt(low) == str.charAt(high))
            {
                if (high - low + 1 > maxLength)
                {
                    start = low;
                    maxLength = high - low + 1;
                }
                --low;
                ++high;
            }
        }

        return str.substring(start, maxLength);
    }

}
