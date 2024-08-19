package shopping.product.controller;


import org.springframework.web.bind.annotation.*;
import shopping.product.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    public ProductController() {
        products.put(8146027L, new Product( 8146027L, "아이스 카페 아메리카노 T", 4500, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
        products.put(1111111L, new Product( 1111111L,"아이스 라뗴", 4500, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
    }

    @GetMapping("/products")
    Map<Long, Product> getProducts() {
        return products;
    }

    @GetMapping("/products/{id}")
    Product getProduct(@PathVariable Long id) {
        return products.get(id);
    }

    @PostMapping("/products")
    Long putProduct(@RequestBody Product product){

        Long productId = makeProductId();
        while (products.containsKey(productId)) {
            productId = makeProductId();
        }

        System.out.println("print productId:" + productId);
        Product inputProduct = new Product(productId, product.name(), product.price(), product.imgeUrl());
        products.put(productId, inputProduct);
        return productId;
    }

    Long makeProductId() {
        Random random = new Random();
        Long randomNumber = 100000 + random.nextLong(900000);
        return  randomNumber;
    }

    @PutMapping("/products/{id}")
    boolean putProduct(@PathVariable Long id, @RequestBody Product product){
        if(!id.equals(product.id())){
            return false;
        }
        products.put(id, product);
        System.out.println("updateData:"+products);
        return true;
    }

    @DeleteMapping("/products/{id}")
    boolean deleteProduct(@PathVariable Long id){
        if(products.containsKey(id)){
            products.remove(id);
        }
        System.out.println(products);
        return true;
    }
}
