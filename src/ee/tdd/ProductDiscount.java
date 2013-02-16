package ee.tdd;

import java.util.Date;

public class ProductDiscount {
    private int discountInPercent;
    private String productCode;
    private Date expiryDate;

    public ProductDiscount(String productCode, int discountInPercent) {

        this.productCode = productCode;
        this.discountInPercent = discountInPercent;
    }

    public int getDiscountInPercent() {
        return discountInPercent;
    }

    public String getProductCode() {
        return productCode;
    }
}
