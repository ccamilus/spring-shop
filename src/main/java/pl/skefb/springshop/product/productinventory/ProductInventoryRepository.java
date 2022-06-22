package pl.skefb.springshop.product.productinventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Long> {
}
