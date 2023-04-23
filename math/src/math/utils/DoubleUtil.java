package math.utils;

public class DoubleUtil {
    public static double epsilon = 0.000001d;

    public static int compare(double x, double y){
        if(Math.abs(x-y)<epsilon) return 0;
        return x-y>0 ? 1:-1;
    }
}
