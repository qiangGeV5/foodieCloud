package com.zq.item.mapper;


//import com.zq.pojo.vo.ItemCommentVO;
//import com.zq.pojo.vo.SearchItemsVO;
import com.zq.item.pojo.vo.ShopcartVO;

import com.zq.item.pojo.vo.ItemCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom {


    public List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> map);
//    public List<SearchItemsVO> searchItems(@Param("paramsMap") Map<String, Object> map);
//    public List<SearchItemsVO> searchItemsByThirdCat(@Param("paramsMap") Map<String, Object> map);
    public List<ShopcartVO> queryItemsBySpecIds(@Param("paramsList") List specIdsList);

    public int decreaseItemSpecStock(@Param("specId") String specId, @Param("pendingCounts") Integer pendingCounts);

}