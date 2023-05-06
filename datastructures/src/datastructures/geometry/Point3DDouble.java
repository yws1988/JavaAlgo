package datastructures.geometry;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Point3DDouble
{
    public double x;
    public double y;
    public double z;

    public boolean usePrecision;
    public int precision = 6;

    public Point3DDouble(double x, double y, double z) {
        this(x, y, z, 6, true);
    }

    public Point3DDouble(double x, double y, double z, int precision, boolean usePrecision)
    {
        x = usePrecision ? round(x, precision, RoundingMode.HALF_UP) : x;
        y = usePrecision ? round(y, precision,  RoundingMode.HALF_UP) : y;
        z = usePrecision ? round(z, precision,  RoundingMode.HALF_UP) : z;
    }

    public static double round(double value, int precision, RoundingMode roundingMode){
        return new BigDecimal(Double.toString(value)).setScale(precision, roundingMode).doubleValue();
    }

    @Override
    public String toString()
    {
        return this.x + " " + this.y + " " + this.z;
    }
}
