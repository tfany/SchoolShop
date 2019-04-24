package com.huihui.o2o.dto;

import com.huihui.o2o.enums.ShopStateEnum;
import com.huihui.o2o.pojo.Shop;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShopExecution {
    //结果标识
    private int state;

    //状态标识
    private String stateInfo;

    //店铺数量
    private int count;

    //操作shop(增删改查店铺用)
    private Shop shop;

    //shop列表
    private List<Shop> shopList;

    public ShopExecution() {
    }

    //店铺操作失败时的构造器
    public  ShopExecution(ShopStateEnum stateEnum){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
    }

    //店铺操作成功时的构造器
    public  ShopExecution(ShopStateEnum stateEnum,Shop shop){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.shop=shop;
    }
    //店铺操作成功时的构造器
    public  ShopExecution(ShopStateEnum stateEnum,List<Shop> shopList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
    }

}
