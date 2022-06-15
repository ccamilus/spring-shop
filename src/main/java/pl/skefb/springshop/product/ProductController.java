package pl.skefb.springshop.product;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.skefb.springshop.exception.ApiRequestException;
import pl.skefb.springshop.exception.ProductNotFoundException;
import pl.skefb.springshop.product.productinventory.ProductInventory;
import pl.skefb.springshop.product.productinventory.ProductInventoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping(path = "{productId}")
    public Optional<Product> getProductById(@PathVariable("productId") Long productId) {
        return this.productService.getProductById(productId);
    }
}
