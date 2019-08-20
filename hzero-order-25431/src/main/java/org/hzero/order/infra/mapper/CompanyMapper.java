package org.hzero.order.infra.mapper;

import org.hzero.order.domain.entity.Company;
import io.choerodon.mybatis.common.BaseMapper;

import java.util.List;

/**
 * 公司Mapper
 *
 * @author yi.liang@hand-china.com 2019-08-06 11:21:16
 */
public interface CompanyMapper extends BaseMapper<Company> {

    List<Company> selectCompany(String companyName);
}
