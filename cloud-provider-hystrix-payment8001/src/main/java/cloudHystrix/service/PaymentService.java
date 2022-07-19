package cloudHystrix.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_ok(Integer id){
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_ok,id:"+id;
    }


    /**
     * @HystrixCommand 超过三秒调用Hystrix服务降级方法，三秒以内走正常的业务逻辑
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",
            commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds"
                    ,value = "3000")
            })
    public String paymentInfo_TimeOut(Integer id){
        int timeNum = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNum);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()
                +"   paymentInfo_TimeOut,id:"+id+",耗时"+timeNum+"秒钟";
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()
                +"   paymentInfo_TimeOut,系统繁忙，请稍后再试,id:"+id;
    }

    /**
     * 服务熔断
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",
    commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数超过了峰值
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("id不能为负数");
        }

        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t调用成功，流水号："+serialNumber;

    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){

        return "id 不能为负数，请稍后再试"+id;
    }
}
