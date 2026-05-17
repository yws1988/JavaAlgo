package collections.array;
/***
 * Given an integer array and an integer windowSize,
 * find all unique triplets at indices i < j < k such that array[i] + array[j] + array[k] = 0 and k - i + 1 <= windowSize.
 * Return the triplets without duplicates.
 * Input:
 * values = [1, -2, 1, 0, 5]
 * windowSize = 3
 * Output: [[1, -2, 1]]
 */

import java.util.*;

public class FindZeroSumTripletsInWindow {
    public static List<List<Integer>> findZeroSumTripletsInWindow(List<Integer> values, int windowSize) {
        int n = values.size();
        if(n<3 || windowSize<3) return Collections.emptyList();

        Set<List<Integer>> resultSet = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int target = -values.get(i);
            int end = Math.min(i+windowSize, n);
            HashSet<Integer> set = new HashSet<>();
            for (int j = i+1; j < end; j++) {
                int complement = target-values.get(j);
                if(set.contains(complement)){
                    List<Integer> list = new ArrayList<>(List.of(values.get(i), values.get(j), complement));
                    Collections.sort(list);
                    resultSet.add(list);
                }

                set.add(values.get(j));
            }
        }

        return new ArrayList<>(resultSet);
    }
}
