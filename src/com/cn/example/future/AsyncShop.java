package cn.example.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class AsyncShop {

    private final String name;
    private final Random random;

    public AsyncShop(String name) {
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public Future<Double> getPrice(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        Util.delay();
        if (false) {
            throw new RuntimeException("product not available");
        }
        return Util.format(random.nextDouble() * product.charAt(0) + product.charAt(1));
    }

}