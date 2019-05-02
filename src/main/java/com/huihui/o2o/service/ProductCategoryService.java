package com.huihui.o2o.service;

import com.huihui.o2o.dto.ProductCategoryExecution;
import com.huihui.o2o.pojo.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> getProductCategoryList(Long shopId);
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList);
    ProductCategoryExecution deleteProductCategory(long productCategoryId,
                                                   long shopId) throws RuntimeException;
}
