package geometry;

public class Line {
    public double A;
    public double B;
    public double C;

    public Line(double a, double b, double c)
    {
        A = a;
        B = b;
        C = c;
    }

    @Override
    public String toString() {
        return String.format("%fx+%fy+%f=0", this.A, this.B, this.C);
    }
}
