package pl.skefb.springshop.shop_user_address;

import pl.skefb.springshop.user.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "shop_user_address")
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
    @JoinColumn(name = "user_id")
    private User user;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postalCode;
    private String country;
    private String telephone;
    private String mobilePhone;

    public ShopUserAddress(Integer id, String addressLine1, String addressLine2, String city, String postalCode, String country, String telephone, String mobilePhone) {
        this.id = id;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.telephone = telephone;
        this.mobilePhone = mobilePhone;
    }

    public ShopUserAddress(String addressLine1, String addressLine2, String city, String postalCode, String country, String telephone, String mobilePhone) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.telephone = telephone;
        this.mobilePhone = mobilePhone;
    }

    public ShopUserAddress() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
