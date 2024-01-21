package patternSearching;

/// <summary>
/// Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s)
/// from beginWord to endWord, such that:
/// Only one letter can be changed at a time
/// Each transformed word must exist in the word list.Note that beginWord is not a transformed word.
/// </summary>

//string beginWord = "hit";
//string endWord = "cog";
//var wordList = new string[] { "hot", "dot", "dog", "lot", "log", "cog" };

//var result = WordLadder.FindLadders(beginWord, endWord, wordList);

// Expected Output:
// [
//  ["hit", "hot", "dot", "dog", "cog"],
//  ["hit","hot","lot","log","cog"]
// ]

import java.util.*;

public class WordLadder {
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList)
    {
        var dictionary = new HashSet<String>(wordList);
        if (!dictionary.contains(endWord))
        {
            return new ArrayList<List<String>>();
        }

        int length = beginWord.length();

        LinkedList<List<String>> queue = new LinkedList<>();

        queue.add(List.of(beginWord));

        int min = Integer.MAX_VALUE;
        var result = new ArrayList<List<String>>();
        Map<String, Integer> positionMap = new HashMap<>();
        positionMap.put(beginWord, 0);

        nextE:
        while (queue.size() > 0)
        {
            var path = queue.pop();
            int currentLevel = path.size() - 1;
            var currentStr = path.get(path.size()-1);

            if (currentLevel >= min) break;

            int nextLevel = currentLevel + 1;
            var currentCharArray = currentStr.toCharArray();
            for (int i = 0; i < length; i++)
            {
                for (char j = 'a'; j <= 'z'; j++)
                {
                    if (j != currentCharArray[i])
                    {
                        char currentChar = currentCharArray[i];
                        currentCharArray[i] = j;
                        String replaceStr = new String(currentCharArray);
                        currentCharArray[i] = currentChar;

                        if (dictionary.contains(replaceStr))
                        {
                            if (replaceStr == endWord)
                            {
                                min = nextLevel;
                                var newPath = new ArrayList<String>(path);
                                newPath.add(replaceStr);
                                result.add(newPath);
                                 continue nextE;
                            }
                            else
                            {
                                if (nextLevel == min || (positionMap.containsKey(replaceStr) && nextLevel != positionMap.get(replaceStr)))
                                {
                                    continue;
                                }

                                positionMap.put(replaceStr, nextLevel);

                                var newPath = new ArrayList<String>(path);
                                newPath.add(replaceStr);
                                queue.add(newPath);
                            }
                        }
                    }
                }
            }
        }

        return result;
    }
}
