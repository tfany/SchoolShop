package com.huihui.o2o.dao;

import com.huihui.o2o.pojo.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {
    /**
     * 查询商品所属类别
     * @param shopId id
     * @return list
     */
    List<ProductCategory> queryProductCategoryList(Long shopId);

    /**
     * 批量增加商品类别
     * @param productCategoryList list
     * @return 影响行数
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    int deleteProductCategory(
            @Param("productCategoryId") long productCategoryId,
            @Param("shopId") long shopId);
}
