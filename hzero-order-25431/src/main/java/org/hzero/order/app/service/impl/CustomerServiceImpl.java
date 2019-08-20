package org.hzero.order.app.service.impl;

import org.hzero.order.app.service.CustomerService;
import org.hzero.order.domain.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户应用服务默认实现
 *
 * @author yi.liang@hand-china.com 2019-08-06 11:21:16
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public List<Customer> listCustomer() {
        return null;
    }

    @Override
    public int insertCustomer(Customer customer) {
        return 0;
    }
}
