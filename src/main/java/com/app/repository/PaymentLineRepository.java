package com.app.repository;

import com.app.model.PaymentLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentLineRepository extends JpaRepository<PaymentLine,Integer> {
}
