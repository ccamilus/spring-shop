package pl.skefb.springshop.product_inventory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInventoryService {
    private final ProductInventoryRepository productInventoryRepository;

    @Autowired
    public ProductInventoryService(ProductInventoryRepository productInventoryRepository) {
        this.productInventoryRepository = productInventoryRepository;
    }

    public List<ProductInventory> getAllInventory() {
        return this.productInventoryRepository.findAll();
    }

    public void addInventory(ProductInventory productInventory) {
        this.productInventoryRepository.save(productInventory);
    }

    public void deleteInventoryById(Integer inventoryId) {
        boolean notExist = ! this.productInventoryRepository.existsById(inventoryId);

        if ( notExist ) {
            throw new IllegalStateException(
                    "Given inventory id does not exists!"
            );
        }

        this.productInventoryRepository.deleteById(inventoryId);
    }
}
