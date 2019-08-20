package org.hzero.order.api.controller.v1;

import io.swagger.annotations.Api;
import org.hzero.core.base.Result;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;
import org.hzero.order.config.SwaggerApiConfig;
import org.hzero.order.domain.entity.Company;
import org.hzero.order.domain.repository.CompanyRepository;
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

/**
 * 公司 管理 API
 *
 * @author yi.liang@hand-china.com 2019-08-06 11:21:16
 */
@Api(tags = SwaggerApiConfig.COMPANY)
@RestController("companyController.v1")
@RequestMapping("/v1/{organizationId}/companys")
public class CompanyController extends BaseController {

    @Autowired
    private CompanyRepository companyRepository;

    @ApiOperation(value = "单个公司查询")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{companyName}")
    public ResponseEntity<Company> findCompany(@PathVariable String companyName) {
        return Results.success(companyRepository.selectCompany(companyName));
    }

    @ApiOperation(value = "公司列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<Company>> list(Company company, @ApiIgnore @SortDefault(value = Company.FIELD_COMPANY_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<Company> list = companyRepository.pageAndSort(pageRequest, company);
        return Results.success(list);
    }

    @ApiOperation(value = "公司明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{companyId}")
    public ResponseEntity<Company> detail(@PathVariable Long companyId) {
        Company company = companyRepository.selectByPrimaryKey(companyId);
        return Results.success(company);
    }

    @ApiOperation(value = "创建公司")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<Company> create(@RequestBody Company company) {
        validObject(company);
        companyRepository.insertSelective(company);
        return Results.success(company);
    }

    @ApiOperation(value = "修改公司")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<Company> update(@RequestBody Company company) {
        SecurityTokenHelper.validToken(company);
        companyRepository.updateByPrimaryKeySelective(company);
        return Results.success(company);
    }

    @ApiOperation(value = "删除公司")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody Company company) {
        SecurityTokenHelper.validToken(company);
        companyRepository.deleteByPrimaryKey(company);
        return Results.success();
    }

}
