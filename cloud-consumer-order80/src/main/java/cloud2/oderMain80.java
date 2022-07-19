package cloud2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import ribbonMyRule.MySelfRule;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(value = "PAYMENT-SERVICE",configuration = MySelfRule.class)
public class oderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(oderMain80.class,args);
    }
}
