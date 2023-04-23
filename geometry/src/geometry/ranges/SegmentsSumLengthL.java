package geometry.ranges;

/*
Give n segments, find if it is possible to find the subset of segments whose continuous area are exactly
the length of L.
 */

import java.util.List;

public class SegmentsSumLengthL
{
    public static boolean exist(List<Segment> segments, int L){
        segments.sort((segA, segB)->{return (segA.x-segB.x)==0 ? segA.y-segB.y : (segA.x-segB.x);});
        int len=segments.size();
        boolean find = false;
        for (int a = 0; a < len; a++) {
            int endmax = segments.get(a).x+L;
            int currentend=segments.get(a).y;

            if(currentend>endmax){
                continue;
            }else if(currentend==endmax){
                return true;
            }

            for (int b = a+1; b < len; b++) {
                Segment seg = segments.get(b);
                if(seg.y<=endmax && currentend>=seg.x  && currentend<=seg.y){
                    currentend = Math.max(currentend, seg.y);
                }
            }

            if(currentend==endmax){
                return true;
            }
        }

       return false;
    }

    public static class Segment{
        public int x;
        public int y;
        public Segment(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}