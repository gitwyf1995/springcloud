package cloud1.service;
import could3.entity.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentId(@Param("id") Long id);
}
