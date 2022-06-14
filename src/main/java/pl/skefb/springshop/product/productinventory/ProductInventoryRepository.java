package pl.skefb.springshop.product.productinventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE ProductInventory pi SET pi.quantity = ?2 WHERE pi.id = ?1")
    void updateProductInventoryQuantity(Long id, Long quantity);
}
