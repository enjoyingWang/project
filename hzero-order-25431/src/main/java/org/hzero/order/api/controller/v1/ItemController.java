package org.hzero.order.api.controller.v1;

import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;
import org.hzero.order.domain.entity.Item;
import org.hzero.order.domain.repository.ItemRepository;
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
 * 物料 管理 API
 *
 * @author yi.liang@hand-china.com 2019-08-06 11:21:16
 */
@RestController("itemController.v1")
@RequestMapping("/v1/{organizationId}/items")
public class ItemController extends BaseController {

    @Autowired
    private ItemRepository itemRepository;

    @ApiOperation(value = "物料列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<Item>> list(Item item, @ApiIgnore @SortDefault(value = Item.FIELD_ITEM_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<Item> list = itemRepository.pageAndSort(pageRequest, item);
        return Results.success(list);
    }

    @ApiOperation(value = "物料明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{itemId}")
    public ResponseEntity<Item> detail(@PathVariable Long itemId) {
        Item item = itemRepository.selectByPrimaryKey(itemId);
        return Results.success(item);
    }

    @ApiOperation(value = "创建物料")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<Item> create(@RequestBody Item item) {
        validObject(item);
        itemRepository.insertSelective(item);
        return Results.success(item);
    }

    @ApiOperation(value = "修改物料")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<Item> update(@RequestBody Item item) {
        SecurityTokenHelper.validToken(item);
        itemRepository.updateByPrimaryKeySelective(item);
        return Results.success(item);
    }

    @ApiOperation(value = "删除物料")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody Item item) {
        SecurityTokenHelper.validToken(item);
        itemRepository.deleteByPrimaryKey(item);
        return Results.success();
    }

}
