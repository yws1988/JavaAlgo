package bruteForce;

import java.util.Arrays;
import java.util.Comparator;

/***
 * N student number
 */
public class StudentClassesArrangementWithBruteForce {
    public static int N;
    public static Pair[] studentClass;

    public static int getMax()
    {
        studentClass = new Pair[N*2];
//        for (int i = 0; i < N; i++)
//        {
//            studentClass[i * 2] = new Pair(temp[0], i);
//            studentClass[i * 2+1] = new Pair(temp[1], i);
//        }

        boolean[] vs = new boolean[N];

        Arrays.sort(studentClass, new PairComparator());

        return getMax(vs, -1, 0);
    }

    public static int getMax(boolean[] vs, int time, int idx)
    {
        if (idx == 2 * N) return 0;
        int r = 0;

        if (!vs[studentClass[idx].index] && studentClass[idx].time > time)
        {
            var copyVs = Arrays.copyOf(vs, vs.length);
            copyVs[studentClass[idx].index] = true;
            r = getMax(copyVs, studentClass[idx].time+60, idx+1) + 1;
        }

        return Math.max(getMax(vs, time, idx + 1), r);
    }

    public static class Pair{
        public int time;
        public int index;

        public Pair(int x, int y) {
            this.time = x;
            this.index = y;
        }
    }

    public static class PairComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.time -o2.time == 0 ? o1.index - o2.index : o1.time -o2.time;
        }
    }
}
