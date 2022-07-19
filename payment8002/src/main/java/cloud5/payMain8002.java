package cloud5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class payMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(payMain8002.class,args);
    }
}
