package pl.skefb.springshop.shoppingsession.cartitem;

import lombok.Data;

@Data
public class CartItemRequest {
    private Long productId;
    private Integer quantity;
}
