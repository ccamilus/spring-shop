package pl.skefb.springshop.product;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.skefb.springshop.response.ResponseHandler;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Object> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseHandler
                .generateResponse("Sukces", HttpStatus.OK, products);
    }

    @GetMapping(path = "{productId}")
    public ResponseEntity<Object> getProductById(@PathVariable("productId") Long productId) {
        Product product = productService.getProductById(productId);
        return ResponseHandler
                .generateResponse("Sukces", HttpStatus.OK, product);
    }
}
