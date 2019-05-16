package com.huihui.o2o.service;

import com.huihui.o2o.dto.ProductExecution;
import com.huihui.o2o.pojo.Product;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

public interface ProductService {
    ProductExecution addProduct(Product product, CommonsMultipartFile file, List<CommonsMultipartFile> productImgs);
    ProductExecution modifyProduct(Product product, CommonsMultipartFile file, List<CommonsMultipartFile> productImgs);
    Product getProductById(Long productId);
    ProductExecution getProductList(Product productCondition,int pageIndex,int pageSize);
}
