package search.string;

/*
Given a string str, the task is to count non-empty substrings with equal number of ‘a’, ‘b’, ‘c’.
Example:
Input: str = “abcdef”
Output: 6
Explanation:
Substring consisting of equal number of ‘a’, ‘b’, ‘c’, and ‘d’ are { “abcd”, “abcde”, “abcdf”, “abcdef”, “e”, “f” }.
 */

import java.util.HashMap;

public class NumOfSubstringsWithEqualsABC {
    public static int count(String str)
    {
        var map=new HashMap<String, Integer>();
        long cnt=0;

        map.put("0#0#0", 0);
        int a=0,b=0,c=0;
        int n=str.length();
        for (int j = 0; j < n; j++) {
            if(str.charAt(j)=='a'){
                a++;
            }else if(str.charAt(j)=='b'){
                b++;
            }else if(str.charAt(j)=='c'){
                c++;
            }

            int min = Math.min(Math.min(a, b), c);
            a-=min;
            b-=min;
            c-=min;

            var key = a+"#"+b+"#"+c;
            if(map.containsKey(key)){
                map.put(key, map.get(key)+1);
            }else{
                map.put(key, 0);
            }
        }

        int result = 0;
        for (var entryset : map.entrySet()) {
            int numElements = entryset.getValue();
            result+=numElements*(numElements+1)/2;
        }

       return result;
    }
}
