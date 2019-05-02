package com.huihui.o2o.dao;

import com.huihui.o2o.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {

	/**
	 * 查询商品列表
	 * @param productCondition 查询条件
	 * @param rowIndex 首页
	 * @param pageSize 分页大小
	 * @return list
	 */
	List<Product> queryProductList(
            @Param("productCondition") Product productCondition,
            @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	/**
	 * 查询对应的商品总数
	 *
	 * @param productCondition
	 * @return
	 */
	int queryProductCount(@Param("productCondition") Product productCondition);

	/**
	 *
	 * @param productId
	 * @return
	 */
	Product queryProductByProductId(long productId);

	/**
	 * 插入商品
	 *
	 * @param product
	 * @return
	 */
	int insertProduct(Product product);

	/**
	 * 更新商品信息
	 *
	 * @param product
	 * @return
	 */
	int updateProduct(Product product);

	/**
	 * 删除商品类别之前，将商品类别ID置为空
	 *
	 * @param productCategoryId
	 * @return
	 */
	int updateProductCategoryToNull(long productCategoryId);

	/**
	 * 删除商品
	 *
	 * @param productId
	 * @return
	 */
	int deleteProduct(@Param("productId") long productId,
                      @Param("shopId") long shopId);
}
