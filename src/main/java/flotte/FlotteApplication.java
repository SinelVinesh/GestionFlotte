package flotte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "flotte")
public class FlotteApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlotteApplication.class, args);
    }

}
