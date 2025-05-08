package com.fsse2502.fsse_project.service.impl;

import com.fsse2502.fsse_project.data.cartItem.entity.CartItemEntity;
import com.fsse2502.fsse_project.data.product.domainObject.response.ProductResponseData;
import com.fsse2502.fsse_project.data.product.entity.ProductEntity;
import com.fsse2502.fsse_project.exception.product.ProductNotFoundException;
import com.fsse2502.fsse_project.exception.product.ProductOutOfStockException;
import com.fsse2502.fsse_project.repository.ProductRepository;
import com.fsse2502.fsse_project.service.CartItemService;
import com.fsse2502.fsse_project.service.ProductService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponseData> getAllProducts(){
        List <ProductResponseData> productResponseDataList = new ArrayList<>();
        for (ProductEntity productEntity : productRepository.findAll()){
            productResponseDataList.add(new ProductResponseData(productEntity));
        }
        return  productResponseDataList;
    }

    @Override
    public List<ProductResponseData> getByCategory(String category){
        List <ProductResponseData> productResponseDataList = new ArrayList<>();
        for (ProductEntity productEntity: productRepository.findByCategory(category)){
            productResponseDataList.add(new ProductResponseData(productEntity));
        }
        return productResponseDataList;
    }

    @Override
    public ProductResponseData findByPid(Integer pid){
        try{
            return new ProductResponseData(getEntityByPid(pid));
        }catch (Exception e){
            log.warn("Get product by pid failed: " + e.getMessage());
            throw e;
        }
    }



    @Override
    public boolean productHasStock(ProductEntity productEntity, Integer quantity){
        if(productEntity.getStock() > 0 &&
                productEntity.getStock() >= quantity){
            return true;
        }
        throw new ProductOutOfStockException(productEntity.getPid());
    }
    @Transactional
    @Override
    public void deductProductStock(CartItemEntity cartItemEntity){
        productHasStock(cartItemEntity.getProduct(), cartItemEntity.getCartQuantity());
        cartItemEntity.getProduct().setStock(
                cartItemEntity.getProduct().getStock() - cartItemEntity.getCartQuantity()
        );
    }



    public ProductEntity getEntityByPid(Integer pid){
        return productRepository.findById(pid).orElseThrow(
                ()-> new ProductNotFoundException(pid)
        );
    }




}
