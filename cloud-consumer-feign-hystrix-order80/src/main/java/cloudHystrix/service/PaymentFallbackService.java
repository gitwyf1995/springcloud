package cloudHystrix.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public String paymentInfo_ok(Integer id) {
        return "-----------------PaymentFallbackService fall back";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----------------PaymentFallbackService fall back";
    }
}
