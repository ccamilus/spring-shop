package pl.skefb.springshop.shopuser.shopuseraddress;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.skefb.springshop.shopuser.ShopUser;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class ShopUserAddress {
    @Id
    @SequenceGenerator(
            name = "shop_user_address_sequence",
            sequenceName = "shop_user_address_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "shop_user_address_sequence"
    )
    private Integer id;
    @ManyToOne
    private ShopUser shopUser;
    private String addressLine;
    private String telephone;
    private String city;
    private String postalCode;
    private String country;

    public ShopUserAddress(ShopUser shopUser, String addressLine, String telephone, String city, String postalCode, String country) {
        this.shopUser = shopUser;
        this.addressLine = addressLine;
        this.telephone = telephone;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }
}
