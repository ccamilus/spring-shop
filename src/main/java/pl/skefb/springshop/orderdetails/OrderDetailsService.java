package pl.skefb.springshop.orderdetails;


import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.skefb.springshop.orderdetails.orderitem.OrderItem;
import pl.skefb.springshop.orderdetails.orderitem.OrderItemService;
import pl.skefb.springshop.shoppingsession.ShoppingSession;
import pl.skefb.springshop.shoppingsession.ShoppingSessionService;
import pl.skefb.springshop.shoppingsession.cartitem.CartItem;
import pl.skefb.springshop.shoppingsession.cartitem.CartItemService;
import pl.skefb.springshop.shopuser.shopuseraddress.ShopUserAddressService;
import pl.skefb.springshop.shopuser.shopuserpayment.ShopUserPaymentService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;
    private final CartItemService cartItemService;
    private final OrderItemService orderItemService;
    private final ShoppingSessionService shoppingSessionService;
    private final ShopUserAddressService shopUserAddressService;
    private final ShopUserPaymentService shopUserPaymentService;

    public void makeOrder(OrderDetailsRequest orderDetailsRequest, Authentication authentication) {
        Optional<ShoppingSession> shoppingSession =
                shoppingSessionService.getCurrentlyActiveShoppingSession(authentication);
        if (shoppingSession.isEmpty()) {
            throw new IllegalStateException("shopping session not found");
        }

        OrderDetails orderDetails = new OrderDetails(
                shoppingSession.get().getShopUser(),
                shoppingSession.get().getTotal(),
                shopUserAddressService.getShopUserAddressById(orderDetailsRequest.getShopUserAddressId()),
                shopUserPaymentService.getShopUserPaymentById(orderDetailsRequest.getShopUserPaymentId()),
                Instant.now()
        );

        orderDetailsRepository.save(orderDetails);

        Optional<List<CartItem>> cartItems = cartItemService.getAllCartItemByShoppingSessionId(authentication);
        if (cartItems.isEmpty()) {
            throw new IllegalStateException("there is no cart items");
        }

        for (CartItem cartItem: cartItems.get()) {
            orderItemService.addOrderItem(
                    new OrderItem(
                            orderDetails,
                            cartItem.getProduct(),
                            cartItem.getQuantity()
                    )
            );
        }

        shoppingSessionService.closeCurrentShoppingSession(authentication);
    }
}
