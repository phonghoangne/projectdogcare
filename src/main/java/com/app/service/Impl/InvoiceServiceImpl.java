package com.app.service.Impl;

import com.app.model.Invoice;
import com.app.repository.InvoiceRepository;
import com.app.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    @Override
    public Invoice findById(Integer integer) {
        return null;
    }

    private final InvoiceRepository invoiceRepository;


    @Override
    public Page<Invoice> findAll(Pageable pageable) {
        return null;
    }

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



}