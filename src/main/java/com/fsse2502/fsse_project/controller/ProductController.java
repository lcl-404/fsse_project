package com.fsse2502.fsse_project.controller;

import com.fsse2502.fsse_project.data.product.domainObject.response.ProductResponseData;
import com.fsse2502.fsse_project.data.product.dto.response.GetAllProductResponseDto;
import com.fsse2502.fsse_project.data.product.dto.response.ProductResponseDto;
import com.fsse2502.fsse_project.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("public/products")
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
}
