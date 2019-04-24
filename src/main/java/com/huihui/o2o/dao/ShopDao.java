package com.huihui.o2o.dao;

import com.huihui.o2o.pojo.Shop;

public interface ShopDao {
    /**
     * 新增店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 跟新店铺信息
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
