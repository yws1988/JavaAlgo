package ternarySearch;

/*
You have been given an Unimodal function:
f(x)=2*x*x-12*x+7;
Give an interval l and r, find the minimum value of f(x) where x will be in the range  (both inclusive).
 */
public class TernarySearchForUnimodalFunctionWithInergerInterval {

    public static int search(int l, int r)
    {
        while(true) {
            int interval = r - l;
            int l1 = l + interval / 3;
            int r1 = r - interval / 3;

            if (interval > 2) {
                if (funcUnimodal(r1) > funcUnimodal(l1)) {
                    r = r1;
                } else {
                    l = l1;
                }
            } else if (interval == 2) {
                return Math.min(Math.min(funcUnimodal(l), funcUnimodal(r)), funcUnimodal(l + 1));
            } else {
               return Math.min(funcUnimodal(l), funcUnimodal(r));
            }
        }
    }

    static int funcUnimodal(int x){
        return 2*x*x-12*x+7;
    }
}
