package java8;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 寮傛CompletableFuture
 */
public class AsynDemo {

    public static Integer cal(Integer cal) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return cal*cal;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> cal(50));
        System.out.println(future.get());
    }

    /**
     * 寮傛娴佸紡澶勭悊
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void streamCal() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> fu = CompletableFuture.supplyAsync(() -> cal(50)).thenApply((i) -> Integer.toString(i)).
                thenApply((str) -> "--" + str + "--").thenAccept(System.out::println);
        fu.get();
    }


    /**
     * 寮傚父澶勭悊
     */
    @Test
    public void handleException() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> fu = CompletableFuture.supplyAsync(() -> cal(50))
                .exceptionally(ex -> {
                    System.out.println(ex.toString());
                    return 0;
                }).thenApply((i) -> Integer.toString(i))
                .thenApply((str) -> "--" + str + "--")
                .thenAccept(System.out::println);
        fu.get();
    }


    /**
     * 缁勫悎澶氫釜CompletableFuture
     */
    @Test
    public void unionCompletableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> fu = CompletableFuture.supplyAsync(() -> cal(50))
                .thenCompose((i) -> CompletableFuture.supplyAsync(() -> cal(i)))
                .thenApply((str) -> "--" + str + "---")
                .thenAccept(System.out::println);
        fu.get();
    }

    /**
     * 缁勫悎澶氫釜CompletableFuture
     */
    @Test
    public void combineCompletableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> intFuture = CompletableFuture.supplyAsync(() -> cal(50));
        CompletableFuture<Integer> intFuture2 = CompletableFuture.supplyAsync(() -> cal(25));

        CompletableFuture<Void> fu = intFuture.thenCombine(intFuture, (i, j) -> (i + j))
                .thenApply((str) -> "--" + str + "--")
                .thenAccept(System.out::println);
        fu.get();
    }

}