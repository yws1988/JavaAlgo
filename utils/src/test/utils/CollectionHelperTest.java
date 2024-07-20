package utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class CollectionHelperTest {

    @Test
   public void getPermutation(){
       var list = List.of(1, 2, 3, 4);

       var permutations = CollectionHelper.getPermutations(list, 4);

        Assertions.assertEquals(24, permutations.collect(Collectors.toList()).size());
   }

   @Test
    public void getCombinations(){
        var lists = List.of(List.of(1, 3), List.of(3, 2, 6), List.of(8, 7));

        var combinations = CollectionHelper.getCombinations(lists);

        Assertions.assertEquals(12, combinations.collect(Collectors.toList()).size());
   }

    @Test
    void binarySearchNumElementsBetweenMinAndMax() {
        List<Integer> sortedList = Arrays.asList(1,3,3,5,5,7,7,9,9,11,11,11);

        int result = CollectionHelper.binarySearchNumElementsBetweenMinAndMax(sortedList, 4, 11);

        Assertions.assertEquals(9, result);
    }
}