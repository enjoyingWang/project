package org.hzero.order.app.service;

import org.hzero.order.domain.entity.Customer;

import java.util.List;


public interface CustomerService {

    List<Customer> listCustomer();

    int insertCustomer(Customer customer);
}
