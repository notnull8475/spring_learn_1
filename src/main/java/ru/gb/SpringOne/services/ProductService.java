package ru.gb.SpringOne.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.SpringOne.exceptions.ResourceNotFoundException;
import ru.gb.SpringOne.models.Product;
import ru.gb.SpringOne.repositories.ProductsRepository;
import ru.gb.SpringOne.repositories.specifications.ProductSpecifications;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductsRepository productsRepository;

    public Page<Product> getProducts(int rows, int page, Long minPrice, Long maxPrice, String titlePart) {
        Specification<Product> specification = Specification.where(null);
        if (minPrice != null)
            specification = specification.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        if (maxPrice != null)
            specification = specification.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
        if (titlePart != null) specification = specification.and(ProductSpecifications.titleLike(titlePart));

        return productsRepository.findAll(specification, PageRequest.of(page, rows));
    }

    public Product getProduct(Long id) {
        return productsRepository.getReferenceById(id);
    }

    public Product save(Product product) {
        assert product.getId()==null;
        return productsRepository.save(product);
    }

    @Transactional
    public Product update(Product product) {
        product = productsRepository.findById(product.getId()).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить, продукт не найден"));
        productsRepository.save(product);
        return product;
    }

    public void deleteProduct(Long id) {
        productsRepository.deleteById(id);
    }

    @Transactional
    public void changePrice(long productId, int delta) {
        Product p = productsRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить, продукт не найден"));
        p.setPrice(p.getPrice() + delta);
    }

}
