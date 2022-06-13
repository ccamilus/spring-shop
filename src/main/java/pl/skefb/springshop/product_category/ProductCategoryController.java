package pl.skefb.springshop.product_category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.skefb.springshop.product.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/product_category")
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public List<ProductCategory> getAllCategories() {
        return productCategoryService.getAllCategories();
    }

    @GetMapping(path = "{categoryName}")
    public Optional<ProductCategory> getCategoryByName(@PathVariable("categoryName") String categoryName) {
        return this.productCategoryService.getCategoryByName(categoryName);
    }

    @GetMapping(path = "{categoryId}")
    public Optional<ProductCategory> getCategoryById(@PathVariable("categoryId") Integer categoryId) {
        return this.productCategoryService.getCategoryById(categoryId);
    }

    @PostMapping
    public void addCategory(@RequestBody ProductCategory productCategory) {
        this.productCategoryService.addCategory(productCategory);
    }

    @DeleteMapping(path = "{categoryId}")
    public void deleteCategoryById(@PathVariable("categoryId") Integer categoryId) {
        this.productCategoryService.deleteCategory(categoryId);
    }
}
