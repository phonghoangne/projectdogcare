package com.app.service;

import com.app.model.Invoice;
import com.app.model.InvoiceLine;

import java.util.List;

public interface InvoiceLineService extends BaseModel<InvoiceLine,Integer> {
    List<InvoiceLine> findAllByBill(Invoice invoice);
}
