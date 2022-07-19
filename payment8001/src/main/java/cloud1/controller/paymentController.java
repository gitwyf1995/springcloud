package cloud1.controller;

import cloud1.service.PaymentService;
import could3.entity.CommonResult;
import could3.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Slf4j
public class paymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        log.info("payment:{}",payment);
        int result = paymentService.create(payment);
        log.info("结果：{}", result);

        if (result > 0) {
            return new CommonResult(200, "出入数据成功,serverPort:"+serverPort, result);
        } else {
            return new CommonResult(444, "出入数据失败,serverPort:"+serverPort, null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentId(@PathVariable("id") Long id) {
        log.info("id:{}",id);
        Payment payment = paymentService.getPaymentId(id);
        log.info("结果：{}", payment);

        if (payment != null) {
            return new CommonResult(200, "查询数据成功,serverPort:"+serverPort, payment);
        } else {
            return new CommonResult(444, "查询数据失败,serverPort:"+serverPort, null);
        }
    }

    @GetMapping("/payment/lb")
    public String paymentLb(){
        return serverPort;
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();

        for (String element : services){
            log.info("*************element:{}",element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("PAYMENT-SERVICE");
        for (ServiceInstance instance : instances){
            log.info(instance.getInstanceId()+"\t"+instance.getHost()+"\t"
                    +instance.getScheme()+"\t"+instance.getServiceId()+"\t"
            +instance.getMetadata()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return this.discoveryClient;

    }


}
