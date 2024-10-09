package com.app.service.Impl;

import com.app.model.Payment;
import com.app.model.PaymentLine;
import com.app.repository.PaymentLineRepository;
import com.app.service.PaymentLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentLineServiceImpl implements PaymentLineService {
    private final PaymentLineRepository paymentLineRepository;

    @Override
    public List<PaymentLine> findAll() {
        return paymentLineRepository.findAll();
    }

    @Override
    public PaymentLine save(PaymentLine paymentLine) {
        return paymentLineRepository.save(paymentLine) ;
    }

    @Override
    public PaymentLine update(PaymentLine id) {
        return paymentLineRepository.save(id);
    }

    @Override
    public void delete(Integer integer) {
        PaymentLine paymentLine = paymentLineRepository.findById(integer).
                orElseThrow(()-> new RuntimeException("PaymentLine is not found"));
        paymentLineRepository.delete(paymentLine);

    }

    @Override
    public PaymentLine findById(Integer integer) {
        return paymentLineRepository.findById(integer).get();
    }
}
