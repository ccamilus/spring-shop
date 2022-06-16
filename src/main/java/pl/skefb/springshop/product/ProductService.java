package pl.skefb.springshop.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.skefb.springshop.exception.ProductNotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }
}
