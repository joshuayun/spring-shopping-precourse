package shopping.product.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shopping.product.Product;
import shopping.product.service.ProductService;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    Map<Long, Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/products/{id}")
    Product getProduct(@PathVariable("id") Long id) {
        return productService.getProduct(id);
    }

    @PostMapping("/products")
    Long insertProduct(@RequestBody Product product){
        return productService.insertProduct(product);
    }

    @PutMapping("/products/{id}")
    boolean updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/products/{id}")
    boolean deleteProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }
}
