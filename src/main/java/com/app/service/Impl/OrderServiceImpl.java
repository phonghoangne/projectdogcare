package com.app.service.Impl;

import com.app.model.Invoice;
import com.app.model.Order;
import com.app.repository.OrderRepository;
import com.app.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order id) {
        return orderRepository.save(id);
    }

    @Override
    public void delete(Integer integer) {
        Order order = orderRepository.findById(integer).
                orElseThrow(()-> new RuntimeException("Order is not found"));
        orderRepository.delete(order);

    }

    @Override
    public Order findById(Integer integer) {
        return null;
    }


}
