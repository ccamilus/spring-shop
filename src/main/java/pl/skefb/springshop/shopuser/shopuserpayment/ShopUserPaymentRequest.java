package pl.skefb.springshop.shopuser.shopuserpayment;

import lombok.Data;

import java.time.YearMonth;

@Data
public class ShopUserPaymentRequest {
    private String cardNumber;
    private YearMonth expirationDate;
    private String cvvCode;
}
