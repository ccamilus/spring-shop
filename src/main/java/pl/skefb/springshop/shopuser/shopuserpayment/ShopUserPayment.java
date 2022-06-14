package pl.skefb.springshop.shopuser.shopuserpayment;

import lombok.ToString;
import pl.skefb.springshop.shopuser.ShopUser;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@ToString
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
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private ShopUser shopUser;
    private String paymentType;
    private String provider;
    private Integer accountNo;
    private Date expiry;

    public ShopUserPayment() {
    }

    public ShopUserPayment(Integer id, String paymentType, String provider, Integer accountNo, Date expiry) {
        this.id = id;
        this.paymentType = paymentType;
        this.provider = provider;
        this.accountNo = accountNo;
        this.expiry = expiry;
    }

    public ShopUserPayment(String paymentType, String provider, Integer accountNo, Date expiry) {
        this.paymentType = paymentType;
        this.provider = provider;
        this.accountNo = accountNo;
        this.expiry = expiry;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Integer accountNo) {
        this.accountNo = accountNo;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }
}
