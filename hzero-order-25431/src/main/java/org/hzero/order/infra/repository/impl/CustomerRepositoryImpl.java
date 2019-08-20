package org.hzero.order.infra.repository.impl;

import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.hzero.order.domain.entity.Customer;
import org.hzero.order.domain.repository.CustomerRepository;
import org.hzero.order.infra.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 客户 资源库实现
 *
 * @author yi.liang@hand-china.com 2019-08-06 11:21:16
 */
@Component
public class CustomerRepositoryImpl extends BaseRepositoryImpl<Customer> implements CustomerRepository {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> findCustomer(String customerNumber,String customerName) {
        return customerMapper.findCustomer(customerNumber,customerName);
    }
}
