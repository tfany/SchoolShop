package com.huihui.o2o.dao;

import com.huihui.o2o.pojo.ProductImg;

import java.util.List;

public interface ProductImgDao {
    /**
     * 批量查询产品图片
     * @param productId 产品id
     * @return 产品图片列表
     */
    List<ProductImg> queryProductImgList(Long productId);

    /**
     * 批量添加产品图片
     * @param productImgList 产品列表
     * @return 影响行数
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    int deleteProductImgByProductId(Long ProductId);
}
