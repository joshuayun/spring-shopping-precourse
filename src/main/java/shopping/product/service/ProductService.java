package shopping.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import shopping.product.CleanTextApiClient;
import shopping.product.Product;
import shopping.product.repository.ProductRepository;

import java.util.Map;
import java.util.Random;

@Service
public class ProductService {


    private final ProductRepository productRepository;



    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Map<Long, Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id);
    }

    public Long insertProduct(Product product) {
        chceckCleanText(product);

        Long productId = makeProductId();

        while (productRepository.findByIdContains(productId)) {
            productId = makeProductId();
        }

        Product inputProduct = new Product(productId, product.name(), product.price(), product.imgeUrl());
        productRepository.save(productId, inputProduct);

        return productId;
    }

    public boolean updateProduct(Long id, Product product) {
        chceckCleanText(product);

        if (!id.equals(product.id())) {
            return false;
        }

        Product ret = productRepository.update(id, product);
        if (ObjectUtils.isEmpty(ret)) {
            return false;
        }

        return true;
    }

    public boolean deleteProduct(Long id) {
        if(productRepository.findByIdContains(id)){
            productRepository.deleteById(id);
        }

        return true;
    }

    Long makeProductId() {
        Random random = new Random();
        Long randomNumber = 100000 + random.nextLong(900000);
        return  randomNumber;
    }

    void chceckCleanText(Product product) {
        CleanTextApiClient client = new CleanTextApiClient();
        if(client.isProfanityText(product.name())) {
            throw new IllegalArgumentException("상품명에는 비속어를 포함할 수 없습니다.");
        }
    }


}
