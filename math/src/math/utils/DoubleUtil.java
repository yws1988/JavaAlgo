package math.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class DoubleUtil {
    public static double epsilon = 0.000001d;

    public static int compare(double x, double y){
        if(Math.abs(x-y)<epsilon) return 0;
        return x-y>0 ? 1:-1;
    }

    public static double round(double value, int precision, RoundingMode roundingMode){
        return new BigDecimal(Double.toString(value)).setScale(precision, roundingMode).doubleValue();
    }
}
