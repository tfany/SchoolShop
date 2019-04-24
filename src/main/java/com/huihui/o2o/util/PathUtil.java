package com.huihui.o2o.util;

public class PathUtil {
    /**
     *获取存放图片的绝对路径
     *
     * @return path
     * windows和linux位置不同
     */
    public static String getImgBasePath(){
        String basePath="";
        String os=System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            basePath="e:/schoolshop/images/";
        }else{
            basePath="/home/schoolshop/images/";
        }
        return basePath;
    }

    /**
     * 获取店铺存放图片的地址
     *
     * @param shopId 店铺id
     * @return  imagePath 相随地址
     */
    public static String getShopImagePath(long shopId){
        return "/upload/item/shop" + shopId + "/";
    }
}
