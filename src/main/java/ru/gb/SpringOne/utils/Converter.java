package ru.gb.SpringOne.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.SpringOne.dto.ProductDto;
import ru.gb.SpringOne.models.Product;

@Component
@RequiredArgsConstructor
public class Converter {
    public Product productDtoToProduct(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .title(productDto.getTitle())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .build();
    }

    public ProductDto productToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
