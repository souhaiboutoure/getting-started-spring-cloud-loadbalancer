package sn.acodewriter.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class UserApplication {

    private final WebClient.Builder loadBalancedWebClientBuilder;
    private final ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction;

    public UserApplication(WebClient.Builder loadBalancedWebClientBuilder, ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
        this.loadBalancedWebClientBuilder = loadBalancedWebClientBuilder;
        this.loadBalancerExchangeFilterFunction = loadBalancerExchangeFilterFunction;
    }

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @GetMapping("/hi")
    public Mono<String> hi(@RequestParam(value = "name", defaultValue = "Someone") String name) {
        return loadBalancedWebClientBuilder.build().get()
                .uri("http://say-hello/greeting")
                .retrieve()
                .bodyToMono(String.class)
                .map( greeting -> String.format("%s, %s!", greeting, name));
    }

    @GetMapping("/hello")
    public Mono<String> hello(@RequestParam(value = "name", defaultValue = "Acodewriter") String name) {
        return WebClient.builder()
                .filter(loadBalancerExchangeFilterFunction)
                .build()
                .get()
                .uri("http://say-hello/greeting")
                .retrieve()
                .bodyToMono(String.class)
                .map( greeting -> String.format("%s, %s !", greeting, name));
    }

}
