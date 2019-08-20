package org.hzero.order.infra.repository.impl;

import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.hzero.mybatis.common.Criteria;
import org.hzero.order.domain.entity.Company;
import org.hzero.order.domain.repository.CompanyRepository;
import org.hzero.order.infra.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 公司 资源库实现
 *
 * @author yi.liang@hand-china.com 2019-08-06 11:21:16
 */
@Component
public class CompanyRepositoryImpl extends BaseRepositoryImpl<Company> implements CompanyRepository {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public Company selectCompany(String companyName) {
        Company company=new Company();
        company.setCompanyName(companyName);
        return  this.selectOne(company);
    }
}
