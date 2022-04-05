import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        CompletableFuture.supplyAsync(() -> {
            return "Completable";
        })
                .thenApplyAsync(s -> sb.append(s).append("Future").toString())
                .thenAccept(s -> System.out.println("Current value is- " + s))
                .thenRun(() -> System.out.println("Computation finished."));
    }
}
