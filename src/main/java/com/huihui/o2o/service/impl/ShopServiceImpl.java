package com.huihui.o2o.service.impl;

import com.huihui.o2o.dao.ShopDao;
import com.huihui.o2o.dto.ShopExecution;
import com.huihui.o2o.enums.ShopStateEnum;
import com.huihui.o2o.exceptions.ShopOperationException;
import com.huihui.o2o.pojo.Shop;
import com.huihui.o2o.service.ShopService;
import com.huihui.o2o.util.ImageUtil;
import com.huihui.o2o.util.PageCalculator;
import com.huihui.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private  ShopDao shopDao;

    @Override
    public Shop getShopByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    @Transactional
    public ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImg) {
       //空值判断
        if (shop==null || shop.getShopId()==null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }else {
            //1.判断是否需要更换图片
            if(shopImg!=null){
                PathUtil.deleteFileOrPath(shop.getShopImg());
                addShopImg(shop,shopImg);
            }
            //2.更新店铺信息
           shop.setLastEditTime(new Date());
            int effectedNum=shopDao.updateShop(shop);
            if(effectedNum<=0){
                return new ShopExecution(ShopStateEnum.INNER_ERROR);
            }else{
                shop=shopDao.queryByShopId(shop.getShopId());
                return new ShopExecution(ShopStateEnum.SUCCESS);
            }

        }

    }
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
        int effectedNum= shopDao.insertShop(shop);
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
                effectedNum= shopDao.updateShop(shop);
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

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex= PageCalculator.calculateRowIndex(pageIndex,pageSize);
        List<Shop> shopList=shopDao.queryShopList(shopCondition,rowIndex,pageSize);
        int count=shopDao.queryShopCount(shopCondition);
        ShopExecution se=new ShopExecution();
        if(shopList!=null){
            se.setShopList(shopList);
            se.setCount(count);
        }else{
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }
}
