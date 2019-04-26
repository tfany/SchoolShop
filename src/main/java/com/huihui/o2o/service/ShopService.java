package com.huihui.o2o.service;

import com.huihui.o2o.dto.ShopExecution;
import com.huihui.o2o.pojo.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


public interface ShopService {
    ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg);
}
