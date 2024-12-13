package com.app.service.Impl;

import com.app.model.Invoice;
import com.app.model.OrderLine;
import com.app.repository.OrderLineRepository;
import com.app.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImpl implements OrderLineService {
    private final OrderLineRepository orderLineRepository;
    @Override
    public List<OrderLine> findAll() {
        return orderLineRepository.findAll();
    }

    @Override
    public OrderLine save(OrderLine orderline) {
        return orderLineRepository.save(orderline);
    }

    @Override
    public OrderLine update(OrderLine id) {
        return orderLineRepository.save(id);

    }
    @Override
        public void delete(Integer integer) {
            OrderLine orderLine = orderLineRepository.findById(integer).
                    orElseThrow(()-> new RuntimeException("OrderLine is not found"));
            orderLineRepository.delete(orderLine);

        }

    @Override
    public Optional<OrderLine> findById(Integer integer) {
        return Optional.empty();
    }


}
