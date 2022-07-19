package cloud5.controller;


import cloud5.service.PaymentService;
import could3.entity.CommonResult;
import could3.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class paymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

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
}
