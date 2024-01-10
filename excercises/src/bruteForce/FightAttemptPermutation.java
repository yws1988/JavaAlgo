package bruteForce;

import utils.ArrayHelper;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FightAttemptPermutation  {
    // E = Water, F = Fire, P = Plant
    public static void solve(String mySequence, String oponnentSequence)
    {
        var dic = Map.of('E', 'F', 'F', 'P', 'P', 'E');

        int n = mySequence.length();
        int m = oponnentSequence.length();

        String win = null, draw = null, lose = null;

        var list = new char[] { 'f', 'e', 'd', 'd' };

        var idxList = IntStream.range(0, n).boxed().collect(Collectors.toList());
        ArrayHelper.generatePermutations(n, idxList);

        for (var permutation : ArrayHelper.allPermutations)
        {
            var newString = permutation.stream().map(s -> mySequence.charAt(s)).collect(Collectors.toList());

            int i = 0, j = 0;
            while (i < n && j < m)
            {
                if (newString.get(i) == oponnentSequence.charAt(j))
                {
                    i++;
                    j++;
                }
                else
                {
                    if (dic.get(newString.get(i)) == oponnentSequence.charAt(j))
                    {
                        j++;
                    }
                    else
                    {
                        i++;
                    }
                }
            }

            String result = newString.stream().map(String::valueOf).collect(Collectors.joining());

            if (i == n && j == m)
            {
                draw = result;
            }
            else if (j == m)
            {
                win = result;
                break;
            }
            else
            {
                lose = result;
            }
        }

        if (win != null)
        {
            System.out.println("+" + win);
        }
        else if (draw != null)
        {
            System.out.println("=" + draw);
        }
        else
        {
            System.out.println("-" + lose);
        }
    }
}
