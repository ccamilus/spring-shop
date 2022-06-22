package pl.skefb.springshop.shopuser;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.skefb.springshop.response.ResponseHandler;
import pl.skefb.springshop.shopuser.shopuseraddress.ShopUserAddressRequest;
import pl.skefb.springshop.shopuser.shopuseraddress.ShopUserAddressService;
import pl.skefb.springshop.shopuser.shopuserpayment.ShopUserPaymentRequest;
import pl.skefb.springshop.shopuser.shopuserpayment.ShopUserPaymentService;

@RestController
@RequestMapping(path = "api/v1/shop-users")
@AllArgsConstructor
public class ShopUserController {
    private final ShopUserService shopUserService;
    private final ShopUserAddressService shopUserAddressService;
    private final ShopUserPaymentService shopUserPaymentService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getUsers() {
        return ResponseHandler
                .generateResponse("Sukces", HttpStatus.OK, shopUserService.getUsers());
    }

    @GetMapping(path = "/account-information")
    public ResponseEntity<Object> getShopUserInformation(Authentication authentication) {
        return ResponseHandler
                .generateResponse("Sukces", HttpStatus.OK,
                        shopUserService.getShopUserInformation(authentication));
    }

    @GetMapping(path = "/addresses")
    public ResponseEntity<Object> getShopUserAddresses(Authentication authentication) {
        return ResponseHandler
                .generateResponse("Sukces", HttpStatus.OK,
                        shopUserAddressService.getShopUserAddresses(authentication));
    }

    @GetMapping(path = "/payments")
    public ResponseEntity<Object> getShopUserPayments(Authentication authentication) {
        return ResponseHandler
                .generateResponse("Sukces", HttpStatus.OK,
                        shopUserPaymentService.getShopUserPayments(authentication));
    }

    @PostMapping(path = "/addresses/save")
    public ResponseEntity<Object> saveNewShopUserAddress(@RequestBody ShopUserAddressRequest shopUserAddressRequest,
                                                         Authentication authentication) {
        if (shopUserAddressRequest.getAddressLine() == null) {
            return ResponseHandler
                    .generateResponseWithoutData("Nie podano adresu", HttpStatus.BAD_REQUEST);
        }
        if (shopUserAddressRequest.getTelephone() == null) {
            return ResponseHandler
                    .generateResponseWithoutData("Nie podano telefonu", HttpStatus.BAD_REQUEST);
        }
        if (shopUserAddressRequest.getCity() == null) {
            return ResponseHandler
                    .generateResponseWithoutData("Nie podano miasta", HttpStatus.BAD_REQUEST);
        }
        if (shopUserAddressRequest.getPostalCode() == null) {
            return ResponseHandler
                    .generateResponseWithoutData("Nie podano kodu pocztowego", HttpStatus.BAD_REQUEST);
        }
        if (shopUserAddressRequest.getCountry() == null) {
            return ResponseHandler
                    .generateResponseWithoutData("Nie podano państwa", HttpStatus.BAD_REQUEST);
        }
        shopUserService.saveNewShopUserAddress(shopUserAddressRequest, authentication);
        return ResponseHandler
                .generateResponseWithoutData("Dodano nowy adres", HttpStatus.CREATED);
    }

    @PostMapping(path = "/payments/save")
    public ResponseEntity<Object> saveNewShopUserPayment(@RequestBody ShopUserPaymentRequest shopUserPaymentRequest,
                                                         Authentication authentication) {
        if (shopUserPaymentRequest.getCardNumber() == null) {
            return ResponseHandler
                    .generateResponseWithoutData("Nie podano numeru karty", HttpStatus.BAD_REQUEST);
        }
        if (shopUserPaymentRequest.getExpirationDate() == null) {
            return ResponseHandler
                    .generateResponseWithoutData("Nie podano daty wygaśnięcia", HttpStatus.BAD_REQUEST);
        }
        if (shopUserPaymentRequest.getCvvCode() == null) {
            return ResponseHandler
                    .generateResponseWithoutData("Nie podano kodu CVV", HttpStatus.BAD_REQUEST);
        }
        shopUserService.saveNewShopUserPayment(shopUserPaymentRequest, authentication);
        return ResponseHandler
                .generateResponseWithoutData("Dodano nową metodę płatności", HttpStatus.CREATED);
    }
}
