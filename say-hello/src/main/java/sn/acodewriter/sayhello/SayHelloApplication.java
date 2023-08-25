package sn.acodewriter.sayhello;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SayHelloApplication {

    private static Logger logger = LoggerFactory.getLogger(SayHelloApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SayHelloApplication.class, args);
    }

    @GetMapping("/greeting")
    public String greet() {
        logger.info("Acces /greeting");
        List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations");
        Random random = new Random();
        int randomGreetIndex = random.nextInt(greetings.size());
        return greetings.get(randomGreetIndex);
    }

    @GetMapping("/")
    public String home() {
        logger.info("Access /");
        return "Hi !";
    }
}
