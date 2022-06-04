package pl.skefb.springshop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping(path = "{productId}")
    public Optional<Product> getProductById(@PathVariable("productId") Integer productId) {
        return this.productService.getProductById(productId);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        this.productService.addProduct(product);
    }

    @DeleteMapping(path = "{productId}")
    public void deleteProductById(@PathVariable("productId") Integer productId) {
        this.productService.deleteProductById(productId);
    }
}
