package cloudHystrix.controller;

import cloudHystrix.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @DefaultProperties  全局服务降级fallback方法
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "globalHandler")
public class paymentHystrixController {

    @Resource
    private PaymentService paymentService;

    /**
     * @HystrixCommand  fallbackMethod: 服务降级的方法
     * commandProperties 设置多少秒后执行服务降级方法  使用@HystrixProperty注解设置
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_okHandler"
    ,commandProperties = {
         @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds"
         ,value = "1500")
    })
    public String paymentInfo_ok(@PathVariable("id") Integer id){

        String result = paymentService.paymentInfo_ok(id);

        log.info("*********result:{}",result);

        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeOut/{id}")
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_TimeOut(id);
        return result;
    }

    public String paymentInfo_okHandler(Integer id){
        return "系统繁忙，请稍后重试,id:"+id;
    }

    public String globalHandler(){
        return "系统繁忙，请稍后重试,id:";
    }
}
