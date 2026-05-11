package search.string;

import java.util.HashMap;

/***
 * Max Unique Substring Length in a Session
 * Given a string of lowercase letters with sessions separated by '*' characters,
 * find the maximum length of a substring with all distinct letters within any single session.
 * Input: abcabcbb*abcabcdbb
 * Output: 4
 */
public class MaxUniqueSubstringLength {
    public static int maxDistinctSubstringLengthInSessions(String sessionString) {
        if(sessionString.length()==0 || sessionString.equals("*")) return 0;
        var charIndexMap=new HashMap<Character, Integer>();
        int max=0;
        int left=0;
        for (int right = 0; right < sessionString.length(); right++) {
            Character c=sessionString.charAt(right);
            if(c.equals('*')){
                charIndexMap.clear();
                left=right+1;
            }

            if(charIndexMap.containsKey(c)){
                left=charIndexMap.get(c)+1;
            }

            charIndexMap.put(c, right);

            max=Math.max(right-left+1, max);
        }


        return max;
    }
}
