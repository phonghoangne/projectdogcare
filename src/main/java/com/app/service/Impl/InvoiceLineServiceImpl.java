package com.app.service.Impl;

import com.app.model.Invoice;
import com.app.model.InvoiceLine;
import com.app.model.User;
import com.app.repository.InvoiceLineRepository;
import com.app.service.InvoiceLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceLineServiceImpl implements InvoiceLineService{
    @Override
    public InvoiceLine findById(Integer integer) {
        return null;
    }

    private final InvoiceLineRepository invoiceLineRepository;
    @Override
    public List<InvoiceLine> findAll() {
        return invoiceLineRepository.findAll();
    }

    @Override
    public InvoiceLine save(InvoiceLine invoiceline) {
        return invoiceLineRepository.save(invoiceline);
    }

    @Override
    public InvoiceLine update(InvoiceLine id) {
        return invoiceLineRepository.save(id);
    }

    @Override
    public void delete(Integer integer) {
        InvoiceLine invoiceLine = invoiceLineRepository.findById(integer).
                orElseThrow(()-> new RuntimeException("InvoiceLine is not found"));
        invoiceLineRepository.delete(invoiceLine);

    }



    @Override
    public List<InvoiceLine> findAllByBill(Invoice invoice) {
        return List.of();
    }
}
