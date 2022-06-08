package pl.skefb.springshop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Optional<Product> getProductById(Integer productId) {
        return this.productRepository.findById(productId);
    }

    // TODO : create get product by category

    public void addProduct(Product product) {
        this.productRepository.save(product);
    }

    // TODO : create put product

    public void deleteProductById(Integer productId) {
        boolean notExist = ! this.productRepository.existsById(productId);

        if ( notExist )
            throw new IllegalStateException("product does not exists!");

        this.productRepository.deleteById(productId);
    }
}
