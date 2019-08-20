package org.hzero.order.api.controller.v1;

import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;
import org.hzero.order.domain.entity.SoLine;
import org.hzero.order.domain.repository.SoLineRepository;
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
 * 销售订单行 管理 API
 *
 * @author yi.liang@hand-china.com 2019-08-06 11:21:16
 */
@RestController("soLineController.v1")
@RequestMapping("/v1/{organizationId}/so-lines")
public class SoLineController extends BaseController {

    @Autowired
    private SoLineRepository soLineRepository;

    @ApiOperation(value = "销售订单行列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<SoLine>> list(SoLine soLine, @ApiIgnore @SortDefault(value = SoLine.FIELD_SO_LINE_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<SoLine> list = soLineRepository.pageAndSort(pageRequest, soLine);
        return Results.success(list);
    }

    @ApiOperation(value = "销售订单行明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{soLineId}")
    public ResponseEntity<SoLine> detail(@PathVariable Long soLineId) {
        SoLine soLine = soLineRepository.selectByPrimaryKey(soLineId);
        return Results.success(soLine);
    }

    @ApiOperation(value = "创建销售订单行")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<SoLine> create(@RequestBody SoLine soLine) {
        validObject(soLine);
        soLineRepository.insertSelective(soLine);
        return Results.success(soLine);
    }

    @ApiOperation(value = "修改销售订单行")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<SoLine> update(@RequestBody SoLine soLine) {
        SecurityTokenHelper.validToken(soLine);
        soLineRepository.updateByPrimaryKeySelective(soLine);
        return Results.success(soLine);
    }

    @ApiOperation(value = "删除销售订单行")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody SoLine soLine) {
        SecurityTokenHelper.validToken(soLine);
        soLineRepository.deleteByPrimaryKey(soLine);
        return Results.success();
    }

}
