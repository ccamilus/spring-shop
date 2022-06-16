package pl.skefb.springshop.orderdetails;

import lombok.Data;

@Data
public class OrderDetailsRequest {
    private Long shopUserAddressId;
    private Long shopUserPaymentId;
}
