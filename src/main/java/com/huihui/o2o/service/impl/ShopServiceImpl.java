package com.huihui.o2o.service.impl;

import com.huihui.o2o.dao.ShopDao;
import com.huihui.o2o.dto.ShopExecution;
import com.huihui.o2o.enums.ShopStateEnum;
import com.huihui.o2o.exceptions.ShopOperationException;
import com.huihui.o2o.pojo.Shop;
import com.huihui.o2o.service.ShopService;
import com.huihui.o2o.util.ImageUtil;
import com.huihui.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private  ShopDao shopdao;
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) {
        //空值判断
        if (shop==null){
        return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        //设置店铺初值
        shop.setEnableStatus(0);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        int effectedNum=shopdao.insertShop(shop);
        //添加店铺信息
        if(effectedNum<=0){
            throw new ShopOperationException("创建店铺失败");
        }
        else {
            if(shopImg!=null){
                try {
                    addShopImg(shop, shopImg);
                }catch (Exception e){
                    throw new ShopOperationException("addShopImg error "+e.getMessage());
                }
                //更新店铺图片地址
                effectedNum= shopdao.updateShop(shop);
                if(effectedNum<=0){
                    throw  new ShopOperationException("更新店铺地址失败");
                }

            }
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    private void addShopImg(Shop shop, CommonsMultipartFile shopImg) {
        String dest= PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr= ImageUtil.generateThumbnail(shopImg,dest);
        shop.setShopImg(shopImgAddr);
    }
}
