package search.ternarySearch;

/*
  return the maximum value of the function f(x) = -1*1*x*x + 2*x +3
  between interval double [start, end].
 */
public class TernarySearchForUnimodalFunction {
    public static double func(double x)
    {
        return -1*1*x*x + 2*x +3;
    }


    public static double ternarySearch(double start, double end)
    {
        double l = start, r = end;

        for(int i=0; i<200; i++) {
            double l1 = (l*2+r)/3;
            double l2 = (l+2*r)/3;
            //cout<<l1<<" "<<l2<<endl;
            if(func(l1) > func(l2)) r = l2; else l = l1;
        }

        double x = l;
        return func(x);
    }
}
