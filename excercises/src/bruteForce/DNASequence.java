package bruteForce;

import utils.ArrayHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DNASequence {
    public static void solveSequence(String[] fragments){
        int n = fragments.length;

        var list = IntStream.range(0, n).boxed().collect(Collectors.toList());
        ArrayHelper.generatePermutations(n, list);
        StringBuilder result = new StringBuilder();

        for (var sequence : ArrayHelper.allPermutations) {
            result.setLength(0);
            if(dnaSequenceMatch(fragments, sequence, result)){
                System.out.println(result);
                break;
            }
        }
    }

    private static Map map = Map.of(
            'A', 'T',
            'T', 'A',
            'C', 'G',
            'G', 'C');

    private static boolean dnaSequenceMatch(String[] fragments, List<Integer> sequence, StringBuilder result){
        int len = Arrays.stream(fragments).mapToInt(fragment -> fragment.length()).sum()/2;

        StringBuilder firstSequence = new StringBuilder();
        StringBuilder secondSequence = new StringBuilder();

        for (Integer idx : sequence) {
            if(firstSequence.length()<len){
                firstSequence.append(fragments[idx]);
            }else{
                secondSequence.append(fragments[idx]);
            }

            result.append(fragments[idx]);
            if(firstSequence.length() == len && secondSequence.length() == 0){
                result.append("#");
            }else{
                result.append(" ");
            }
        }

        StringBuilder transformedSequence = new StringBuilder();
        if(firstSequence.length() == secondSequence.length()){
            for (int i = 0; i < len; i++) {
                transformedSequence.append(map.get(firstSequence.charAt(i)));
            }

            return transformedSequence.toString().equals(secondSequence.toString());
        }

        return false;
    }
}
