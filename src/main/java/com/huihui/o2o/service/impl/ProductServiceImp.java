package com.huihui.o2o.service.impl;
import com.huihui.o2o.dao.ProductDao;
import com.huihui.o2o.dao.ProductImgDao;
import com.huihui.o2o.dto.ProductExecution;
import com.huihui.o2o.enums.ProductStateEnum;
import com.huihui.o2o.pojo.Product;
import com.huihui.o2o.pojo.ProductImg;
import com.huihui.o2o.service.ProductService;
import com.huihui.o2o.util.ImageUtil;
import com.huihui.o2o.util.PageCalculator;
import com.huihui.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    @Override
    @Transactional
    public ProductExecution addProduct(Product product, CommonsMultipartFile file, List<CommonsMultipartFile> productImgs) {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            //给商品设置默认属性
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            //默认为上架状态
            product.setEnableStatus(1);
            if (file != null) {
                addThumbnail(product, file);

                try {
                    int effectedNum = productDao.insertProduct(product);
                    if (effectedNum <= 0) {
                        throw new RuntimeException("添加商品失败");
                    }
                } catch (Exception e) {
                    throw new RuntimeException("添加商品失败" + e.getMessage());
                }

                if (productImgs != null) {
                    addProductImgList(product, productImgs);
                }

                return new ProductExecution(ProductStateEnum.SUCCESS, product);
            }else{
                return new ProductExecution(ProductStateEnum.EMPTY);
            }
        }
        return null;
    }

    @Override
    public Product getProductById(Long productId) {
        return productDao.queryProductByProductId(productId);
    }

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
        int count = productDao.queryProductCount(productCondition);
        ProductExecution pe = new ProductExecution();
        pe.setProductList(productList);
        pe.setCount(count);
        return pe;
    }

    private void addThumbnail(Product product, CommonsMultipartFile thumbnail) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        product.setImgAddr(thumbnailAddr);
    }

    @Override
    public ProductExecution modifyProduct(Product product, CommonsMultipartFile thumbnail, List<CommonsMultipartFile> productImgs) {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            product.setLastEditTime(new Date());
            if (thumbnail != null) {
                Product tempProduct = productDao.queryProductByProductId(product.getProductId());
                if (tempProduct.getImgAddr() != null) {
                    PathUtil.deleteFile(tempProduct.getImgAddr());
                }
                addThumbnail(product, thumbnail);
            }
            if (productImgs != null && productImgs.size() > 0) {
                deleteProductImgs(product.getProductId());
                addProductImgList(product, productImgs);
            }
            try {
                int effectedNum = productDao.updateProduct(product);
                if (effectedNum <= 0) {
                    throw new RuntimeException("更新商品信息失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS, product);
            } catch (Exception e) {
                throw new RuntimeException("更新商品信息失败:" + e.toString());
            }
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    private void addProductImgList(Product product, List<CommonsMultipartFile> productImgs) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<String> imgAddrList = ImageUtil.generateNormalImgs(productImgs, dest);
        if (imgAddrList != null && imgAddrList.size() > 0) {
            List<ProductImg> productImgList = new ArrayList<>();
            for (String imgAddr : imgAddrList) {
                ProductImg productImg = new ProductImg();
                productImg.setImgAddr(imgAddr);
                productImg.setProductId(product.getProductId());
                productImg.setCreateTime(new Date());
                productImgList.add(productImg);
            }
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if (effectedNum <= 0) {
                    throw new RuntimeException("创建商品详情图片失败");
                }
            } catch (Exception e) {
                throw new RuntimeException("创建商品详情图片失败:" + e.toString());
            }
        }
    }

    private void deleteProductImgs(long productId) {
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        for (ProductImg productImg : productImgList) {
            PathUtil.deleteFile(productImg.getImgAddr());
        }
        productImgDao.deleteProductImgByProductId(productId);
    }
}


