package com.huihui.o2o.service.impl;

import com.huihui.o2o.dao.ShopCategoryDao;
import com.huihui.o2o.pojo.ShopCategory;
import com.huihui.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCategoryServiceImp implements ShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Override
    public List<ShopCategory> getShopCategoryService(ShopCategory shopCategory) {
        return shopCategoryDao.queryShopCategory(shopCategory);
    }
}
