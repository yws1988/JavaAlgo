package graph.flow;
/*
 There are N people, selects P Poets,D Dancers and M Musicians in N persons.
 The strength of each person is the sum of ratings of the people
 in the corresponding field. You have to maximize the sum of their ratings.

 Input Format
 First line contains 4 integers N,P,D,M.
 Next 3 lines contain 3 arrays each of size N denoting the rating of the
 person in the corresponding field. The first one is the ratings of N people on their poetry skill,
 next on their dancing skill and the third one on their music skills.
    3 1 1 1
    1 2 3
    1 3 3
    4 5 6

 Output Format
 10
 Choose the 1st person for Poetry , 2nd for Dancing and 3rd for Music.
 */
import java.util.*;
import java.io.*;

public class TaskAssignmentWithMaximumRatings {
    public static void mainF(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // Read input parameters
        int N = Integer.parseInt(st.nextToken()); // total number of people
        int P = Integer.parseInt(st.nextToken()); // number of poets needed
        int D = Integer.parseInt(st.nextToken()); // number of dancers needed
        int M = Integer.parseInt(st.nextToken()); // number of musicians needed

        // Calculate total vertices needed
        // source(0) + N people + 3 roles + sink
        int V = 1 + N + 3 + 1;
        int source = 0;
        int sink = V - 1;

        // Create MCMF instance
        MinimumCostsMaximumFlow mcmf = new MinimumCostsMaximumFlow(V);

        // Read ratings for each field
        int[] poetryRatings = new int[N];
        int[] danceRatings = new int[N];
        int[] musicRatings = new int[N];

        // Read poetry ratings
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            poetryRatings[i] = Integer.parseInt(st.nextToken());
        }

        // Read dance ratings
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            danceRatings[i] = Integer.parseInt(st.nextToken());
        }

        // Read music ratings
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            musicRatings[i] = Integer.parseInt(st.nextToken());
        }

        // Build the flow network
        // Connect source to people
        for (int i = 0; i < N; i++) {
            mcmf.addEdge(source, i + 1, 1, 0);
        }

        // Connect people to roles (using negative costs for maximization)
        int poetRole = N + 1;
        int danceRole = N + 2;
        int musicRole = N + 3;

        for (int i = 0; i < N; i++) {
            // Person i+1 to roles
            mcmf.addEdge(i + 1, poetRole, 1, -poetryRatings[i]);
            mcmf.addEdge(i + 1, danceRole, 1, -danceRatings[i]);
            mcmf.addEdge(i + 1, musicRole, 1, -musicRatings[i]);
        }

        // Connect roles to sink
        mcmf.addEdge(poetRole, sink, P, 0);
        mcmf.addEdge(danceRole, sink, D, 0);
        mcmf.addEdge(musicRole, sink, M, 0);

        // Find minimum cost maximum flow
        MinimumCostsMaximumFlow.Result result = mcmf.findMinCostMaxFlow(source, sink);

        // Print result (negative because we used negative costs)
        System.out.println(-result.minCost);

        br.close();
    }
}
