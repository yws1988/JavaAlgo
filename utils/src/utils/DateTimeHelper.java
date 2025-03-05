package utils;

import java.util.Arrays;

public class DateTimeHelper {
    // Time format: 23:59:30
    public static int getSecondsFromTime(String s){
        int[] time = Arrays.stream(s.split(":")).mapToInt(Integer::parseInt).toArray();
        return time[0]*3600+time[1]*60+time[2];
    }
}
