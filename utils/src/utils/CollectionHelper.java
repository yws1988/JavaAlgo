package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionHelper {
    public static List<Integer> mergeTwoOrderedLists(List<Integer> listA, List<Integer> listB)
    {
        List<Integer> res = new ArrayList<>();
        int aSize = listA.size();
        int bSize = listB.size();

        int i = 0, j = 0;

        while (i < aSize && j < bSize)
        {
            if (listA.get(i).compareTo(listB.get(j)) > 0)
            {
                res.add(listB.get(j));
                j++;
            }
            else
            {
                res.add(listA.get(i));
                i++;
            }
        }

        while (i < aSize)
        {
            res.add(listA.get(i));
            i++;
        }

        while (j < bSize)
        {
            res.add(listB.get(j));
            j++;
        }

        return res;
    }

    // input list {1,2,3}
    // output {1,2,3} {1,3,2} {2,1,3} {2,3,1} {3,1,2} {3,2,1}

    public static Stream<List<Integer>> getPermutations(List<Integer> list, int length)
    {
        if (length == 1) return list.stream().map(element -> List.of(element));

        return getPermutations(list, length - 1)
                .flatMap(subSequence -> list.stream().filter(element -> !subSequence.contains(element)).map(element -> {List<Integer> newList = new ArrayList<>(subSequence); newList.add(element); return newList;}));
    }

    // input list {{1,2}, {3,4}, {5}}
    // output list {{1,3,5},{1,4,5},{2,3,5},{2,4,5}}
    public static Stream<List<Integer>> getCombinations(List<List<Integer>> lists)
    {
        Stream<List<Integer>> result = lists.get(0).stream().map(element -> List.of(element));

        for (var list : lists.stream().skip(1).collect(Collectors.toList()))
        {
            result = result.flatMap(originalList -> list.stream().map(element -> {
                var newList = new ArrayList<>(originalList);
                newList.add(element);
                return newList;
            }));
        }

        return result;
    }
}
