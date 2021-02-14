package com.zq.item.service;

import com.zq.item.pojo.Items;
import com.zq.item.pojo.ItemsImg;
import com.zq.item.pojo.ItemsParam;
import com.zq.item.pojo.ItemsSpec;
import com.zq.item.pojo.vo.CommentLevelCountsVO;
import com.zq.item.pojo.vo.ShopcartVO;
import com.zq.pojo.PagedGridResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("item-api")
public interface ItemService {
    /**
     * 商品详情
     * @param itemId
     * @return
     */
    @GetMapping("item")
    public Items queryItemById(@RequestParam("itemId") String itemId);

    /**
     * 商品图片列表
     * @param itemId
     * @return
     */
    @GetMapping("itemImages")
    public List<ItemsImg> queryItemImgfList(@RequestParam("itemId") String itemId);

    /**
     * 商品规格
     * @param itemId
     * @return
     */
    @GetMapping("itemSpecs")
    public List<ItemsSpec> queryItemSpecList(@RequestParam("itemId") String itemId);


    /**
     * 根据商品id查询商品
     * @param itemId
     * @return
     */
    @GetMapping("itemParam")
    public ItemsParam queryItemParam(@RequestParam("itemId") String itemId);


    /**
     * 根据商品id查询商品评价等级数量
     * @param itemId
     */
    @GetMapping("countComments")
    public CommentLevelCountsVO queryCommentCounts(@RequestParam("itemId") String itemId);

    /**
     * 根据商品id查询商品评价
     * @param itemId
     * @param level
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("pagedComments")
    public PagedGridResult queryPagedComments(@RequestParam("itemId") String itemId,
                                              @RequestParam(value = "level",required = false) Integer level,
                                              @RequestParam(value = "page",required = false) Integer page,
                                              @RequestParam(value = "pageSize",required = false) Integer pageSize);

//    /**
//     * 搜索商品列表
//     * @param keywords
//     * @param sort
//     * @param page
//     * @param pageSize
//     * @return
//     */
//    @GetMapping("item")
//    public PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);
//
//    /**
//     * 根据id搜索商品列表
//     * @param catId
//     * @param sort
//     * @param page
//     * @param pageSize
//     * @return
//     */
//    @GetMapping("item")
//    public PagedGridResult searchItemsByThirdCat(Integer catId, String sort, Integer page, Integer pageSize);

    /**
     * 根据规格id 查询最新的购物车中的商品数据（用于刷新渲染购物车中的商品数据）
     * @param specIds
     * @return
     */
    @GetMapping("getCartBySpecIds")
    public List<ShopcartVO> queryItemsBySpecIds(@RequestParam("specIds") String specIds);


    /**
     * 根据商品规格id，获取商品信息
     * @param specId
     * @return
     */
    @GetMapping("ItemSpec")
    public ItemsSpec queryItemSpecById(@RequestParam("specId") String specId);

    /**
     * 获取商品主图
     * @param itemId
     * @return
     */
    @GetMapping("itemMainImgById")
    public String queryItemMainImgById(@RequestParam("itemId") String itemId);

    /**
     * 减少库存
     * @param specId
     * @param buyCounts
     */
    @PostMapping ("itemSpecStock")
    public void decreaseItemSpecStock(@RequestParam("specId") String specId, @RequestParam("buyCounts") Integer buyCounts);
}
