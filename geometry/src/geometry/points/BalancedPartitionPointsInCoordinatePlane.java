package geometry.points;

/*
 There are n points in (xi, yi) in the Cartesian coordinate plane. Each points has a value vi,
 Now we will separate these points with a line y=x; Determine if it is possible to separate these
 points with the same weights in the left and right side
*/

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BalancedPartitionPointsInCoordinatePlane {

    public static boolean isPossible(List<Point> points){
        points.sort((pointA, pointB)->{return (pointA.value-pointB.value);});

        int sum = points.stream().mapToInt(s -> s.h).sum();
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();

        points.forEach(point -> {
            Integer value = Integer.valueOf(point.value);
            if(map.containsKey(value)){
                map.put(value, map.get(value) + point.h);
            }else{
                map.put(value, point.h);
            }
        });

        int leftSum = 0;
        int rightSum = sum-leftSum;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int pointSum = entry.getValue();
            if(leftSum == rightSum || leftSum==(rightSum-pointSum)){
                return true;
            }

            leftSum+=pointSum;
            rightSum-=pointSum;
        }

        return false;
    }

    public static class Point{
        public int x;
        public int y;
        public int h;
        public int value;
        public Point(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
            value=x-y;
        }
    }
}
