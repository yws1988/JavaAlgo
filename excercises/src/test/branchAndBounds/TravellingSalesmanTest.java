package branchAndBounds;

import org.junit.jupiter.api.Test;

import static branchAndBounds.TravellingSalesman.final_path;
import static branchAndBounds.TravellingSalesman.final_res;
import static org.junit.jupiter.api.Assertions.*;

class TravellingSalesmanTest {

    @Test
    public void solve() {
        //Adjacency matrix for the given graph
        int adj[][] = {{0, 10, 15, 20},
                        {10, 0, 35, 25},
                        {15, 35, 0, 30},
                        {20, 25, 30, 0} };

        TravellingSalesman.solve(adj);

        System.out.printf("Minimum cost : %d\n", final_res);
        System.out.printf("Path Taken : ");
        for (int i = 0; i <= 4; i++)
        {
            System.out.printf("%d ", final_path[i]);
        }
    }
}