package com.fsse2502.fsse_project.service;

import com.fsse2502.fsse_project.data.product.domainObject.response.ProductResponseData;
import com.fsse2502.fsse_project.data.product.entity.ProductEntity;

import java.util.List;


public interface ProductService {
    List<ProductResponseData> getAllProducts();

    ProductResponseData findByPid(Integer pid);

    boolean productHasStock(ProductEntity productEntity, Integer quantity);

    ProductEntity getEntityByPid(Integer pid);
}
