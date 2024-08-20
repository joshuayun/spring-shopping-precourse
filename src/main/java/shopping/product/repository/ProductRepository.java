package shopping.product.repository;

import org.springframework.stereotype.Repository;
import shopping.product.Product;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();
    public ProductRepository() {
        products.put(8146027L, new Product( 8146027L, "아이스아메리카노", 4500, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
        products.put(1111111L, new Product( 1111111L,"아이스라뗴", 4500, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
    }

    public Map<Long, Product> findAll() {
        return products;
    }

    public Product findById(Long id) {
        return products.get(id);
    }

    public void save(Long productId, Product inputProduct) {
        products.put(productId, inputProduct);
    }

    public boolean findByIdContains(Long productId) {
        return products.containsKey(productId);
    }

    public Product update(Long id, Product product) {
        return products.replace(id, product);
    }

    public void deleteById(Long id) {
        products.remove(id);
    }
}
