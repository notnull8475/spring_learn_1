package ru.gb.SpringOne.api;


import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.SpringOne.dto.ProductDto;
import ru.gb.SpringOne.services.ProductService;
import ru.gb.SpringOne.utils.Converter;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final Converter converter;

    public ProductController(ProductService productService, Converter converter) {
        this.productService = productService;
        this.converter = converter;
    }


    @GetMapping
    public Page<ProductDto> getProducts(
            @RequestParam(name = "rows", required = false, defaultValue = "5") int rows,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "min_price", required = false) Long minPrice,
            @RequestParam(name = "max_price", required = false) Long maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (rows < 0) rows = 5;
        if (page < 0) page = 1;
        return productService.getProducts(rows, page, minPrice, maxPrice, titlePart).map(it -> converter.productToProductDto(it));
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return converter.productToProductDto(productService.getProduct(id));
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto) {
        return converter.productToProductDto(productService.save(converter.productDtoToProduct(productDto)));
    }

    @PutMapping
    public ProductDto update(@RequestBody ProductDto productDto) {
        return converter.productToProductDto(productService.update(converter.productDtoToProduct(productDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        productService.deleteProduct(id);
    }


    @GetMapping("/change_price")
    public void changePrice(@RequestParam int productId, @RequestParam String delta) {
        productService.changePrice(productId, Integer.parseInt(delta));
    }
}
