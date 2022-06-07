package pl.skefb.springshop.product_category;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.skefb.springshop.product.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<ProductCategory> getAllCategories() {
        return this.productCategoryRepository.findAll();
    }

    public Optional<ProductCategory> getCategoryById(Integer categoryId) {
        return this.productCategoryRepository.findById(categoryId);
    }

    public Optional<ProductCategory> getCategoryByName(String categoryName) {
        return this.productCategoryRepository.findProductCategoryByName(categoryName);
    }

    public void addCategory(ProductCategory productCategory) {
        this.productCategoryRepository.save(productCategory);
    }

    public void deleteCategory(Integer categoryId ) {
        boolean notExist = ! this.productCategoryRepository.existsById(categoryId);

        if ( notExist ) {
            throw new IllegalStateException(
                    "Given category does not exist!"
            );
        }

        this.productCategoryRepository.deleteById(categoryId);
    }
}
