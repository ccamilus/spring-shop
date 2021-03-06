package pl.skefb.springshop.orderdetails;


import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.skefb.springshop.email.EmailSenderService;
import pl.skefb.springshop.exception.ApiRequestException;
import pl.skefb.springshop.orderdetails.orderitem.OrderItem;
import pl.skefb.springshop.orderdetails.orderitem.OrderItemService;
import pl.skefb.springshop.shoppingsession.ShoppingSession;
import pl.skefb.springshop.shoppingsession.ShoppingSessionService;
import pl.skefb.springshop.shoppingsession.cartitem.CartItem;
import pl.skefb.springshop.shoppingsession.cartitem.CartItemService;
import pl.skefb.springshop.shopuser.ShopUser;
import pl.skefb.springshop.shopuser.ShopUserService;
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
    private final EmailSenderService emailSenderService;
    private final ShopUserService shopUserService;

    public void makeOrder(OrderDetailsRequest orderDetailsRequest, Authentication authentication) {
        ShoppingSession shoppingSession = shoppingSessionService.getCurrentlyActiveShoppingSession(authentication);
        ShopUserAddress shopUserAddress =
                shopUserAddressService.getShopUserAddressById(orderDetailsRequest.getShopUserAddressId());
        ShopUserPayment shopUserPayment =
                shopUserPaymentService.getShopUserPaymentById(orderDetailsRequest.getShopUserPaymentId());
        List<CartItem> cartItems = cartItemService.getCartItemsByShoppingSessionId(authentication);
        if (!shopUserAddress.getShopUser().getEmail().equals(authentication.getName())) {
            throw new ApiRequestException("Podany adres o id " + orderDetailsRequest.getShopUserAddressId() +
                    " nie nale??y do zalogowanego u??ytkownika " + authentication.getName());
        }
        if (!shopUserPayment.getShopUser().getEmail().equals(authentication.getName())) {
            throw new ApiRequestException("Podana metoda p??atno??ci o id " + orderDetailsRequest.getShopUserAddressId() +
                    " nie nale??y do zalogowanego u??ytkownika " + authentication.getName());
        }
        if (cartItems.isEmpty()) {
            throw new ApiRequestException("Nie mo??na zrealizowa?? zam??wienia, brak produkt??w w koszyku");
        }
        OrderDetails orderDetails = new OrderDetails(
                shoppingSession.getShopUser(),
                shoppingSession.getTotal(),
                shopUserAddress,
                shopUserPayment,
                Instant.now());
        orderDetailsRepository.save(orderDetails);
        for (CartItem cartItem : cartItems) {
            orderItemService.addOrderItem(new OrderItem(orderDetails, cartItem.getProduct(), cartItem.getQuantity()));
        }
        ShopUser shopUser = shopUserService.findByEmail(authentication.getName());
        StringBuilder emailBody = new StringBuilder();
        emailBody
                .append("Dzie?? dobry ")
                .append(shopUser.getFirstName())
                .append("! Twoje zam??wienie zosta??o zatwierdzone.\n\n")
                .append("Adres wysy??ki towaru:\n")
                .append("Odbiorca: ")
                .append(shopUserAddress.getShopUser().getFirstName())
                .append(" ")
                .append(shopUserAddress.getShopUser().getLastName())
                .append("\nUlica: ")
                .append(shopUserAddress.getAddressLine())
                .append("\nKod pocztowy: ")
                .append(shopUserAddress.getPostalCode())
                .append("\nMiasto: ")
                .append(shopUserAddress.getCity())
                .append("\n\nProdukty:\n");
        for (CartItem cartItem : cartItems) {
            emailBody
                    .append("Nazwa: ")
                    .append(cartItem.getProduct().getName())
                    .append("\nIlo????: ")
                    .append(cartItem.getQuantity())
                    .append("\nWarto????: ")
                    .append(cartItem.getTotal())
                    .append("\n");
        }
        emailBody
                .append("Ca??kowita warto???? zam??wienia: ")
                .append(orderDetails.getTotal())
                .append("z??.\nDzi??kujemy za zakupy w naszym sklepie!");
        shoppingSessionService.closeCurrentShoppingSession(authentication);
        emailSenderService.sendEmail(
                authentication.getName(),
                "Zam??wienie zatwierdzone",
                emailBody.toString()
        );
    }
}
