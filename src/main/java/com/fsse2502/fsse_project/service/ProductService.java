package com.fsse2502.fsse_project.service;

import com.fsse2502.fsse_project.data.cartItem.entity.CartItemEntity;
import com.fsse2502.fsse_project.data.product.domainObject.response.ProductResponseData;
import com.fsse2502.fsse_project.data.product.entity.ProductEntity;
import com.fsse2502.fsse_project.data.transactionProduct.entity.TransactionProductEntity;
import jakarta.transaction.Transactional;

import java.util.List;


public interface ProductService {
    List<ProductResponseData> getAllProducts();

    List<ProductResponseData> getByCategory(String category);

    ProductResponseData findByPid(Integer pid);

    @Transactional
    void deductProductStock(CartItemEntity cartItemEntity);

    boolean productHasStock(ProductEntity productEntity, Integer quantity);

    ProductEntity getEntityByPid(Integer pid);
}
