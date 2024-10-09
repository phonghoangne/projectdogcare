package com.app.service.Impl;

import com.app.model.Invoice;
import com.app.repository.InvoiceRepository;
import com.app.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;


    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice update(Invoice id) {
        return invoiceRepository.save(id);
    }

    @Override
    public void delete(Integer integer) {
        Invoice invoice = invoiceRepository.findById(integer).
                orElseThrow(()-> new RuntimeException("Invoice is not found"));
        invoiceRepository.delete(invoice);

    }

    @Override
    public Invoice findById(Integer integer) {
        return invoiceRepository.findById(integer).get();
    }
}
