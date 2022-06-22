package pl.skefb.springshop.orderdetails;


import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.skefb.springshop.exception.ApiRequestException;
import pl.skefb.springshop.orderdetails.orderitem.OrderItem;
import pl.skefb.springshop.orderdetails.orderitem.OrderItemService;
import pl.skefb.springshop.shoppingsession.ShoppingSession;
import pl.skefb.springshop.shoppingsession.ShoppingSessionService;
import pl.skefb.springshop.shoppingsession.cartitem.CartItem;
import pl.skefb.springshop.shoppingsession.cartitem.CartItemService;
import pl.skefb.springshop.shopuser.shopuseraddress.ShopUserAddress;
import pl.skefb.springshop.shopuser.shopuseraddress.ShopUserAddressService;
import pl.skefb.springshop.shopuser.shopuserpayment.ShopUserPayment;
import pl.skefb.springshop.shopuser.shopuserpayment.ShopUserPaymentService;

import java.time.Instant;
import java.util.List;

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
        ShoppingSession shoppingSession = shoppingSessionService.getCurrentlyActiveShoppingSession(authentication);
        ShopUserAddress shopUserAddress =
                shopUserAddressService.getShopUserAddressById(orderDetailsRequest.getShopUserAddressId());
        ShopUserPayment shopUserPayment =
                shopUserPaymentService.getShopUserPaymentById(orderDetailsRequest.getShopUserPaymentId());
        if (!shopUserAddress.getShopUser().getEmail().equals(authentication.getName())) {
            throw new ApiRequestException("Podany adres o id " + orderDetailsRequest.getShopUserAddressId() +
                    " nie należy do zalogowanego użytkownika " + authentication.getName());
        }
        if (!shopUserPayment.getShopUser().getEmail().equals(authentication.getName())) {
            throw new ApiRequestException("Podana metoda płatności o id " + orderDetailsRequest.getShopUserAddressId() +
                    " nie należy do zalogowanego użytkownika " + authentication.getName());
        }
        OrderDetails orderDetails = new OrderDetails(
                shoppingSession.getShopUser(),
                shoppingSession.getTotal(),
                shopUserAddress,
                shopUserPayment,
                Instant.now());
        orderDetailsRepository.save(orderDetails);
        List<CartItem> cartItems = cartItemService.getCartItemsByShoppingSessionId(authentication);
        for (CartItem cartItem : cartItems) {
            orderItemService.addOrderItem(new OrderItem(orderDetails, cartItem.getProduct(), cartItem.getQuantity()));
        }
        shoppingSessionService.closeCurrentShoppingSession(authentication);
    }
}
