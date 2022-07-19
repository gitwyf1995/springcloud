package cloudFeign.controller;

import cloudFeign.service.PaymentFeignService;
import could3.entity.CommonResult;
import could3.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentId(@PathVariable("id") Long id){

        return paymentFeignService.getPaymentId(id);
    }
}
