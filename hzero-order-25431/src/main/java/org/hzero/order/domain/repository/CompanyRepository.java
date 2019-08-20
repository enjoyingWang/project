package org.hzero.order.domain.repository;

import org.hzero.mybatis.base.BaseRepository;
import org.hzero.order.domain.entity.Company;

import java.util.List;

/**
 * 公司资源库
 *
 * @author yi.liang@hand-china.com 2019-08-06 11:21:16
 */
public interface CompanyRepository extends BaseRepository<Company> {

    Company selectCompany(String companyName);
}
