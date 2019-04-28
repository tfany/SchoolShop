package com.huihui.o2o.service;

import com.huihui.o2o.dto.ShopExecution;
import com.huihui.o2o.pojo.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


public interface ShopService {
    Shop getShopByShopId(long shopId);
    ShopExecution modifyShop(Shop shop,CommonsMultipartFile shopImg);
    ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg);
    ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
}
