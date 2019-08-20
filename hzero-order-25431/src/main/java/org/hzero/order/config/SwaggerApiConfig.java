package org.hzero.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.Tag;

@Configuration
public class SwaggerApiConfig {

    public static final String ITEM = "ITEM";
    public static final String COMPANY = "COMPANY";
    public static final String CUSTOMER = "CUSTOMER";


    @Autowired
    public SwaggerApiConfig(Docket docket) {
        docket.tags(
                new Tag(ITEM, "物料信息"),
                new Tag(COMPANY, "公司信息"),
                new Tag(CUSTOMER, "客户数据")
        );
    }
}
