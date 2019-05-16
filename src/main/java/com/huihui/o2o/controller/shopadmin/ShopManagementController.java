package com.huihui.o2o.controller.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huihui.o2o.dto.ShopExecution;
import com.huihui.o2o.enums.ShopStateEnum;
import com.huihui.o2o.pojo.Area;
import com.huihui.o2o.pojo.PersonInfo;
import com.huihui.o2o.pojo.Shop;
import com.huihui.o2o.pojo.ShopCategory;
import com.huihui.o2o.service.AreaService;
import com.huihui.o2o.service.ShopCategoryService;
import com.huihui.o2o.service.ShopService;
import com.huihui.o2o.util.CodeUtil;
import com.huihui.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shop")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private ShopCategoryService shopCategoryService;

    /**
     * 获取店铺信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getshopinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId > 0) {
            Shop shop = shopService.getShopByShopId(shopId);
            List<Area> areaList = areaService.getAreaList();
            List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryService(new ShopCategory());
            modelMap.put("shop", shop);
            modelMap.put("areaList", areaList);
            modelMap.put("CategoryList", shopCategoryList);
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopId");
        }
        return modelMap;
    }

    /**
     * 从服务器获取初始化参数
     *
     * @return json串
     */
    @RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopInitInfo() {
        Map<String, Object> modelMap = new HashMap<>();
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        List<Area> areaList = new ArrayList<>();
        try {
            shopCategoryList = shopCategoryService
                    .getShopCategoryService(new ShopCategory());
            areaList = areaService.getAreaList();
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        modelMap.put("shopCategoryList", shopCategoryList);
        modelMap.put("areaList", areaList);
        modelMap.put("success", true);
        return modelMap;
    }


    /**
     * 注册店铺
     *
     * @param request 用户请求
     * @return json
     */
    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        //1.接收并转换相应的参数 包括店铺信息以及图片信息
        String s = request.getParameter("shopStr");
        Map<String, Object> modelMap = new HashMap<>();
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "验证码错误");
            return modelMap;
        }
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            //获取前端传来的信息 并将其转换成实体类
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            //接收前端传来的文件
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }
        //2.注册店铺
        if (shop != null && shopImg != null) {
            //session todo
            PersonInfo personInfo = (PersonInfo) request.getSession().getAttribute("user");
            shop.setOwnerId(personInfo.getUserId());
            ShopExecution se = shopService.addShop(shop, shopImg);
            if (se.getState() == ShopStateEnum.CHECK.getState()) {
                modelMap.put("success", true);
                //用户可以操作的店铺列表
                List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
                if (shopList == null || shopList.size() == 0) {
                    shopList = new ArrayList<>();
                }
                shopList.add(se.getShop());
                request.getSession().setAttribute("shopList", shopList);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", se.getStateInfo());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
        }
        //3.返回结果
        return modelMap;
    }

    /**
     * 更新店铺信息
     *
     * @param request 用户请求
     * @return json
     */
    @RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyShop(HttpServletRequest request) {
        //1.接收并转换相应的参数 包括店铺信息以及图片信息
        String s = request.getParameter("shopStr");
        Map<String, Object> modelMap = new HashMap<>();
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "验证码错误");
            return modelMap;
        }
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            //获取前端传来的信息 并将其转换成实体类
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            //接收前端传来的文件
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        }
        //2.修改店铺
        if (shop != null && shop.getShopId() != null) {
            ShopExecution se;
            if (shopImg != null) {
                se = shopService.modifyShop(shop, shopImg);
            } else {
                se = shopService.modifyShop(shop, null);
            }
            if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", se.getStateInfo());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
        }
        //3.返回结果
        return modelMap;
    }


    /**
     * 获取分页列表数据
     *
     * @param request 用户请求
     * @return json数据
     */
    @RequestMapping(value = "/getshoplist", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> getShopList(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        PersonInfo user = new PersonInfo();
        user.setUserId(8L);
        user.setName("同学");
        request.getSession().setAttribute("user", user);
        PersonInfo personInfo = (PersonInfo) request.getSession().getAttribute("user");
        Shop shop = new Shop();
        shop.setOwnerId(personInfo.getUserId());
        List<Shop> shopList;
        try {
            ShopExecution execution = shopService.getShopList(shop, 0, 100);
            shopList = execution.getShopList();
            modelMap.put("shopList", shopList);
            modelMap.put("user", user);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("essMsg", e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping(value = "/getshopmanagementinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopManagementInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId <= 0) {
            modelMap.put("redirect", false);
            return modelMap;
        }
        Object currentShopObj = request.getSession().getAttribute("currentShop");
        if (currentShopObj != null) {
            modelMap.put("redirect", true);
            modelMap.put("url", "/o2o/shop/shoplist");
        } else {
            Shop currentShop = new Shop();
            currentShop.setShopId(shopId);
            request.getSession().setAttribute("currentShop", currentShop);
            modelMap.put("redirect", false);
        }

        return modelMap;
    }
}
