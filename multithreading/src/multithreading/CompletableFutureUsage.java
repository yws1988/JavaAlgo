import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompletableFutureUsage {
    private static ExecutorService executor= Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public static void calculate(){
        List<Integer> positions= IntStream.range(0, 100).boxed().collect(Collectors.toList());
        List<CompletableFuture<Double>> futures= positions.stream()
                .map(pos -> CompletableFuture.supplyAsync(()->calculateRisk(pos), executor)
                        .handle((res, e)->{
                            if(e!=null) return 0.0;
                            return res;
                        })).collect(Collectors.toList());

        CompletableFuture<Void> allDone = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );

        double totalRisk = allDone.thenApply(v ->
                futures.stream()
                        .mapToDouble(CompletableFuture::join)
                        .sum()
        ).join();

        System.out.println("Total Portfolio Risk: " + totalRisk);
        executor.shutdown();

    }

    private static Double calculateRisk(int positionId) {
        // Simulate heavy Monte Carlo computation
        return positionId * 1.5;
    }

}
