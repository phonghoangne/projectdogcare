package com.app.service.Impl;

import com.app.model.Customer;
import com.app.model.Invoice;
import com.app.repository.CustomerRepository;
import com.app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer id) {
        return customerRepository.save(id);
    }

    @Override
    public void delete(Integer integer) {
Customer customer = customerRepository.findById(integer).orElseThrow(()-> new RuntimeException("Customer is not  found"));
            customerRepository.delete(customer);

    }

    @Override
    public Optional<Customer> findById(Integer integer) {
        return Optional.empty();
    }


}
