package sn.acodewriter.user;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class SayHelloConfiguration {

    @Bean
    @Primary
    ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new DemoServiceInstanceListSupplier("say-hello");
    }
}
