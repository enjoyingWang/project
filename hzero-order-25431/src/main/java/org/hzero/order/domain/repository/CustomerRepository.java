package org.hzero.order.domain.repository;

import org.hzero.mybatis.base.BaseRepository;
import org.hzero.order.domain.entity.Customer;

import java.util.List;

/**
 * 客户资源库
 *
 * @author yi.liang@hand-china.com 2019-08-06 11:21:16
 */
public interface CustomerRepository extends BaseRepository<Customer> {

    List<Customer> findCustomer(String customerNumber,String customerName);
}
