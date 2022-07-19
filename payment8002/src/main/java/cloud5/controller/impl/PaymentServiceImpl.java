package cloud5.controller.impl;


import cloud5.dao.PaymentMapper;
import cloud5.service.PaymentService;
import could3.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentDao;

    public int create(Payment payment){

        return paymentDao.create(payment);
    }

    public Payment getPaymentId(Long id){
        return paymentDao.getPaymentId(id);
    }
}
