package org.samtonyclarke.test.driver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class DriveIt {
    public static void main(String args[]) throws InterruptedException {
        Flux<Integer> intFlux = Flux.range(1, 200000);
        intFlux.flatMap(i ->
                WebClient.create("http://localhost:8080")
                        .get()
                        .uri("integrationTest/1.0/app-name")
                        .retrieve()
                        .bodyToMono(String.class))
                .subscribe();
    }
}
