package com.huihui.o2o.controller.front;

import com.huihui.o2o.pojo.HeadLine;
import com.huihui.o2o.pojo.ShopCategory;
import com.huihui.o2o.service.HeadLineService;
import com.huihui.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/frontend")
public class MainPageController {
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private HeadLineService headLineService;

    @RequestMapping(value = "/listmainpageinfo", method= RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listMainPageInfo(){
        Map<String,Object> modelMap=new HashMap<>();
        List<ShopCategory> shopCategoryList;
        try{
            shopCategoryList=shopCategoryService.getShopCategoryList(null);
            modelMap.put("shopCategoryList",shopCategoryList);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        List<HeadLine> headLineList;
        try{
            HeadLine headLineCondition=new HeadLine();
            headLineCondition.setEnableStatus(1);
            headLineList=headLineService.getHeadLineList(headLineCondition);
            modelMap.put("headLineList",headLineList);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        modelMap.put("success",true);
        return modelMap;
    }
}
