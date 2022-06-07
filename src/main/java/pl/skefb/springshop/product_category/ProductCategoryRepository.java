package pl.skefb.springshop.product_category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    @Query("SELECT c FROM ProductCategory c WHERE c.name = ?1")
    Optional<ProductCategory> findProductCategoryByName(String name);
}
