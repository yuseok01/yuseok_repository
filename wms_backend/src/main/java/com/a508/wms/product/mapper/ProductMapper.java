package com.a508.wms.product.mapper;

import com.a508.wms.product.dto.ProductResponseDto;
import com.a508.wms.product.domain.Product;
import com.a508.wms.productdetail.mapper.ProductDetailMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    /**
     * Product -> ProductResponseDto
     *
     * @param product
     * @return
     */
    public static ProductResponseDto fromProduct(Product product) {
        return ProductResponseDto.builder()
            .comment(product.getComment())
            .expirationDate(product.getExpirationDate())
            .comment(product.getComment())
            .productDetail(ProductDetailMapper.fromProductDetail(product.getProductDetail()))
            .build();
    }
}
