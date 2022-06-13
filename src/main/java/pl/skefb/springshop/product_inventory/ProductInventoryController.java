package pl.skefb.springshop.product_inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product_inventory")
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
public class ProductInventoryController {
    private final ProductInventoryService productInventoryService;

    @Autowired
    public ProductInventoryController(ProductInventoryService productInventoryService) {
        this.productInventoryService = productInventoryService;
    }

    @GetMapping
    public List<ProductInventory> getAllInventory() {
        return this.productInventoryService.getAllInventory();
    }

    @PostMapping
    public void addInventory(@RequestBody ProductInventory productInventory) {
        this.productInventoryService.addInventory(productInventory);
    }

    @DeleteMapping(path = "{inventoryId}")
    public void deleteInventoryById(@PathVariable("inventoryId") Integer inventoryId) {
        this.productInventoryService.deleteInventoryById(inventoryId);
    }
}
