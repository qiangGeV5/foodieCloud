package com.zq.item.pojo.vo;


import com.zq.item.pojo.Items;
import com.zq.item.pojo.ItemsImg;
import com.zq.item.pojo.ItemsParam;
import com.zq.item.pojo.ItemsSpec;

import java.util.List;

public class ItemInfoVO {
    private Items item ;
    private List<ItemsImg> itemImgList ;
    private List<ItemsSpec> itemSpecList ;
    private ItemsParam itemParams ;

    public ItemsParam getItemParams() {
        return itemParams;
    }

    public void setItemParams(ItemsParam itemParams) {
        this.itemParams = itemParams;
    }

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public List<ItemsImg> getItemImgList() {
        return itemImgList;
    }

    public void setItemImgList(List<ItemsImg> itemImgList) {
        this.itemImgList = itemImgList;
    }

    public List<ItemsSpec> getItemSpecList() {
        return itemSpecList;
    }

    public void setItemSpecList(List<ItemsSpec> itemSpecList) {
        this.itemSpecList = itemSpecList;
    }


}
