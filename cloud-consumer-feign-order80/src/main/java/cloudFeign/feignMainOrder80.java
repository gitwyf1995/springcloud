package cloudFeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class feignMainOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(feignMainOrder80.class,args);
    }
}
