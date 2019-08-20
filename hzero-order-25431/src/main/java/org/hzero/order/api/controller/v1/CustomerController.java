package org.hzero.order.api.controller.v1;

import io.swagger.annotations.Api;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;
import org.hzero.order.config.SwaggerApiConfig;
import org.hzero.order.domain.entity.Company;
import org.hzero.order.domain.entity.Customer;
import org.hzero.order.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.hzero.mybatis.helper.SecurityTokenHelper;

import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 客户 管理 API
 *
 * @author yi.liang@hand-china.com 2019-08-06 11:21:16
 */
@Api(tags = SwaggerApiConfig.CUSTOMER)
@RestController("customerController.v1")
@RequestMapping("/v1/{organizationId}/customers")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerRepository customerRepository;

    @ApiOperation(value = "用户查询")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{customerNumber}&{customerName}")
    public List<Customer> findCompany(@PathVariable String customerNumber, @PathVariable String customerName) {
        return customerRepository.findCustomer(customerNumber,customerName);
    }

    @ApiOperation(value = "客户列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<Customer>> list(Customer customer, @ApiIgnore @SortDefault(value = Customer.FIELD_CUSTOMER_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<Customer> list = customerRepository.pageAndSort(pageRequest, customer);
        return Results.success(list);
    }

    @ApiOperation(value = "客户明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> detail(@PathVariable Long customerId) {
        Customer customer = customerRepository.selectByPrimaryKey(customerId);
        return Results.success(customer);
    }

    @ApiOperation(value = "创建客户")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        validObject(customer);
        customerRepository.insertSelective(customer);
        return Results.success(customer);
    }

    @ApiOperation(value = "修改客户")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<Customer> update(@RequestBody Customer customer) {
        SecurityTokenHelper.validToken(customer);
        customerRepository.updateByPrimaryKeySelective(customer);
        return Results.success(customer);
    }

    @ApiOperation(value = "删除客户")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody Customer customer) {
        SecurityTokenHelper.validToken(customer);
        customerRepository.deleteByPrimaryKey(customer);
        return Results.success();
    }

}
