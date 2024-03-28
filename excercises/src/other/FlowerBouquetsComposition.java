package other;

/***
 * Your website "The Bouquet of the Week" is a huge hit. Each week you sell only
 * one type of bouquet consisting of flowers of 4 colors: purple, red, yellow and
 * orange. This simplifies logistics a lot. To avoid that your customers get bored,
 * you change the composition of the bouquet each week by varying the number of
 * flowers of each color.
 * You receive daily quantities of each flowers that correspond more or less to what you ordered.
 * In general, in the evening, there are flowers of some colors that have not been used for the day’s
 * bouquets that can be reused the next day. But beware, they fade after 48 hours
 * so it is not possible to reuse them two days later.
 * You must determine the maximum number of bouquets achievable in a week
 * based on daily arrivals.
 * Data
 * Input
 * Row 1: four integers P, R, Y and O between 1 and 50, separated by a space,
 * respectively corresponding to the number of flowers of the type purple, red,
 * yellow and orange which will compose the bouquet of the week.
 * Rows 2 to 8: four integers P, R, Y and O between 1 and 1000, separated by a
 * space, respectively corresponding to the number of flowers of the type purple,
 * red, yellow and orange delivered on the 7 consecutives days of the week.
 * Output
 * An integer corresponding to the maximum number of bouquets achievable during the week.
 */

import java.util.stream.IntStream;

public class FlowerBouquetsComposition {
    public static int getNumOfBouquetsSold(int[] numberOfFlowersForBouquet, int[][] numberOfFlowers) {
        var restFlowers = new int[4];
        int res = 0;

        for (int i = 0; i < 7; i++) {
            var sumNn = new int[4];

            for (int j = 0; j < 4; j++) {
                sumNn[j] = numberOfFlowers[i][j] + restFlowers[j];
            }

            int min = IntStream.range(0, 4).map(idx -> sumNn[idx] / numberOfFlowersForBouquet[idx]).min().getAsInt();

            res += min;

            for (int j = 0; j < 4; j++) {
                int consumedNum = min * numberOfFlowersForBouquet[j];

                restFlowers[j] = consumedNum > restFlowers[j] ? sumNn[j] - consumedNum : numberOfFlowers[i][j];
            }
        }

        return res;
    }
}
