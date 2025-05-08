package com.fsse2502.fsse_project.controller;

import com.fsse2502.fsse_project.data.product.domainObject.response.ProductResponseData;
import com.fsse2502.fsse_project.data.product.dto.response.GetAllProductResponseDto;
import com.fsse2502.fsse_project.data.product.dto.response.ProductResponseDto;
import com.fsse2502.fsse_project.service.ProductService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("public/products")
@CrossOrigin("http://localhost:5173")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<GetAllProductResponseDto> getAllProducts(){
        List<GetAllProductResponseDto> getAllProductResponseDtoList = new ArrayList<>();
        List<ProductResponseData> productResponseDataList = productService.getAllProducts();

        for (ProductResponseData productResponseData :  productResponseDataList){
            getAllProductResponseDtoList.add(new GetAllProductResponseDto(productResponseData));
        }
        return getAllProductResponseDtoList;
    }

    @GetMapping("/{pid}")
    public ProductResponseDto getProductByPid(@PathVariable Integer pid){
        return new ProductResponseDto(productService.findByPid(pid));
    }

    @GetMapping("/cat/{category}")
    public List<ProductResponseDto> getByCategory(@PathVariable String category) {
        List <ProductResponseDto> productResponseDtoList = new ArrayList<>();
        List<ProductResponseData> productResponseDataList = productService.getByCategory(category);

        for (ProductResponseData productResponseData : productResponseDataList){
            productResponseDtoList.add(new ProductResponseDto(productResponseData));
        }
        return productResponseDtoList;
    }




}
