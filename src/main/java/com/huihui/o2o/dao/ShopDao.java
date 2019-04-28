package com.huihui.o2o.dao;

import com.huihui.o2o.pojo.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {

    /**
     * 分页查询
     * @param shopCondition shop实例
     * @param rowIndex 行数
     * @param page 页数
     * @return shop
     */
    List<Shop> queryShopList(@Param("shopCondition")Shop shopCondition, @Param("rowIndex")int rowIndex, @Param("pageSize")int page);

    int queryShopCount(@Param("shopCondition")Shop shopCondition);


    /**
     * 通过id查询店铺
     * @param shopId id
     * @return Shop实例
     */
    Shop queryByShopId(Long shopId);

    /**
     * 新增店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
