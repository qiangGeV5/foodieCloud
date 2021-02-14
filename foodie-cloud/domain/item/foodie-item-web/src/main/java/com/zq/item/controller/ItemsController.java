package com.zq.item.controller;


import com.zq.controller.BaseController;
import com.zq.enums.CommentLevel;
import com.zq.item.pojo.Items;
import com.zq.item.pojo.ItemsImg;
import com.zq.item.pojo.ItemsParam;
import com.zq.item.pojo.ItemsSpec;
import com.zq.item.pojo.vo.CommentLevelCountsVO;
import com.zq.item.pojo.vo.ItemInfoVO;
import com.zq.item.pojo.vo.ShopcartVO;
import com.zq.item.service.ItemService;
import com.zq.pojo.PagedGridResult;
import com.zq.pojo.ZQJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "商品接口",tags = "商品信息相关接口")
@RestController
@RequestMapping("items")
public class ItemsController extends BaseController {

    @Autowired
    private ItemService itemService;
    @ApiOperation(value = "商品详情", notes = "商品详情",httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public ZQJSONResult info(
            @ApiParam(name = "itemId",value = "商品id",required = true)
            @PathVariable String itemId){
        if (StringUtils.isBlank(itemId)){
            return ZQJSONResult.errorMsg(null);
        }
        Items items = itemService.queryItemById(itemId);
        List<ItemsImg> itemsImgs = itemService.queryItemImgfList(itemId);
        List<ItemsSpec> itemsSpecs = itemService.queryItemSpecList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);

        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(items);
        itemInfoVO.setItemImgList(itemsImgs);
        itemInfoVO.setItemParams(itemsParam);
        itemInfoVO.setItemSpecList(itemsSpecs);
        return ZQJSONResult.ok(itemInfoVO);
    }

    @ApiOperation(value = "商品评价等级", notes = "商品评价等级",httpMethod = "GET")
    @GetMapping("/commentLevel")
    public ZQJSONResult commentLevel(
            @ApiParam(name = "itemId",value = "商品id",required = true)
            @RequestParam String itemId){
        if (StringUtils.isBlank(itemId)){
            return ZQJSONResult.errorMsg(null);
        }
        CommentLevelCountsVO commentLevelCountsVO = itemService.queryCommentCounts(itemId);
        return ZQJSONResult.ok(commentLevelCountsVO);
    }

    @ApiOperation(value = "商品评价", notes = "商品评价",httpMethod = "GET")
    @GetMapping("/comments")
    public ZQJSONResult comments(
            @ApiParam(name = "itemId",value = "商品id",required = true)
            @RequestParam String itemId,
            @ApiParam(name = "level",value = "评价等级",required = false)
            @RequestParam Integer level,
            @ApiParam(name = "page",value = "查询的页数",required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize",value = "每页条数",required = false)
            @RequestParam Integer pageSize
    ){
        if (StringUtils.isBlank(itemId)){
            return ZQJSONResult.errorMsg("itemId==NUll");
        }
        if (level == null){
            level = CommentLevel.GOOD.type;
        }
        if (page == null){
            page = 1;
        }
        if (pageSize == null){
            pageSize = COMMENT_PAGE_SIZE;
        }
        PagedGridResult pagedGridResult = itemService.queryPagedComments(itemId, level, page, pageSize);
        return ZQJSONResult.ok(pagedGridResult);
    }

    //用于用户长时间未登录，刷新商品价格
    @ApiOperation(value = "通过规格ids查找最新的商品", notes = "通过规格ids查找最新的商品",httpMethod = "GET")
    @GetMapping("/refresh")
    public ZQJSONResult refresh(
            @ApiParam(name = "itemSpecIds",value = "拼接的规格id",required = true,example = "1001,1003,1005")
            @RequestParam String itemSpecIds){
        if (StringUtils.isBlank(itemSpecIds)){
            return ZQJSONResult.errorMsg("specIds==NUll");
        }

        List<ShopcartVO> shopcartVOS = itemService.queryItemsBySpecIds(itemSpecIds);

        return ZQJSONResult.ok(shopcartVOS);
    }
}
