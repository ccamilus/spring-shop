package pl.skefb.springshop.shopuser.shopuseraddress;

import lombok.Data;

@Data
public class ShopUserAddressRequest {
    private String addressLine;
    private String telephone;
    private String city;
    private String postalCode;
    private String country;
}
