package com.app.service;

import com.app.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InvoiceService extends BaseModel<Invoice,Integer>{
    Page<Invoice> findAll(Pageable pageable);
    List<Invoice> findAll();
    Invoice save(Invoice invoice);

}
