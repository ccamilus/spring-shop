package pl.skefb.springshop.shop_user_payment;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table
public class ShopUserPayment {
    @Id
    @SequenceGenerator(
            name = "user_payment_sequence",
            sequenceName = "user_payment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_payment_sequence"
    )
    private int id;
    private int userId;
    private String paymentType;
    private String provider;
    private int accountNo;
    private Date expiry;

    public ShopUserPayment() {
    }

    public ShopUserPayment(int id, int userId, String paymentType, String provider, int accountNo, Date expiry) {
        this.id = id;
        this.userId = userId;
        this.paymentType = paymentType;
        this.provider = provider;
        this.accountNo = accountNo;
        this.expiry = expiry;
    }

    public ShopUserPayment(int userId, String paymentType, String provider, int accountNo, Date expiry) {
        this.userId = userId;
        this.paymentType = paymentType;
        this.provider = provider;
        this.accountNo = accountNo;
        this.expiry = expiry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }
}
