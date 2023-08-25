package sn.acodewriter.user;

import java.util.Arrays;
import java.util.List;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;

public class DemoServiceInstanceListSupplier implements ServiceInstanceListSupplier {

    private final String serveId;

    public DemoServiceInstanceListSupplier(String serveId) {
        this.serveId = serveId;
    }

    @Override
    public String getServiceId() {
        return serveId;
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(Arrays.asList(
                new DefaultServiceInstance(serveId + "1", serveId, "localhost", 8090, false)
//                new DefaultServiceInstance(serveId + "2", serveId, "localhost", 9092, false)
//                new DefaultServiceInstance(serveId + "3", serveId, "localhost", 9999, false)
        ));
    }
}
