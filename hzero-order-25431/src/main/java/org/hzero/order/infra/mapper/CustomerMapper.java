package org.hzero.order.infra.mapper;

import org.hzero.order.domain.entity.Customer;
import io.choerodon.mybatis.common.BaseMapper;

import java.util.List;

/**
 * 客户Mapper
 *
 * @author yi.liang@hand-china.com 2019-08-06 11:21:16
 */
public interface CustomerMapper extends BaseMapper<Customer> {
    List<Customer> findCustomer(String customerNumber,String customerName);
}
