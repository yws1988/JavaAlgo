package codinggame;

import utils.NumberHelper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParseIntegersInStrings {
    public List<String> parseIntegersInStrings(List<String> strs, int factor) {
       return strs.stream().map(str -> {
           return Arrays.stream(str.split(" ")).map(s -> {
               Integer num = NumberHelper.tryParseInt(s);
               return num == null ? s : String.valueOf(num * factor);
           }).collect(Collectors.joining(" "));
        }).collect(Collectors.toList());
    }
}
