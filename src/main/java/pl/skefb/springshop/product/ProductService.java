package pl.skefb.springshop.product;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Optional<Product> getProductById(Long productId) {
        return this.productRepository.findById(productId);
    }

    public void addProduct(Product product) {
        this.productRepository.save(product);
    }

    public void deleteProductById(Long productId) {
        boolean notExist = ! this.productRepository.existsById(productId);

        if ( notExist )
            throw new IllegalStateException("product does not exists!");

        this.productRepository.deleteById(productId);
    }
}
