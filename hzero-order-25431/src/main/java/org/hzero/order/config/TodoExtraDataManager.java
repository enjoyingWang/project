package org.hzero.order.config;

import io.choerodon.core.swagger.ChoerodonRouteData;
import io.choerodon.swagger.annotation.ChoerodonExtraData;
import io.choerodon.swagger.swagger.extra.ExtraData;
import io.choerodon.swagger.swagger.extra.ExtraDataManager;

@ChoerodonExtraData
public class TodoExtraDataManager implements ExtraDataManager {
    @Override
    public ExtraData getData() {
        ChoerodonRouteData choerodonRouteData = new ChoerodonRouteData();
        choerodonRouteData.setName("hord-25431");
        choerodonRouteData.setPath("/hord-25431/**");
        choerodonRouteData.setServiceId("hzero-order-25431");
        extraData.put(ExtraData.ZUUL_ROUTE_DATA, choerodonRouteData);
        return extraData;
    }
}
