package com.app.service.Impl;

import com.app.model.Invoice;
import com.app.model.Payment;
import com.app.repository.PaymentRepository;
import com.app.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment update(Payment id) {
        return paymentRepository.save(id);
    }

    @Override
    public void delete(Integer id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        paymentRepository.delete(payment);
    }

    @Override
    public Optional<Payment> findById(Integer integer) {
        return Optional.empty();
    }


}
