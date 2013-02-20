package ee.tdd.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class ProductDiscount {
    private float discountInPercent;
    private String productCode;

    public ProductDiscount(String productCode, float discountInPercent) {

        this.productCode = productCode;
        this.discountInPercent = discountInPercent;
    }

    public float getDiscountInPercent() {
        return discountInPercent;
    }

    public String getProductCode() {
        return productCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDiscount)) return false;

        ProductDiscount that = (ProductDiscount) o;

        if (Float.compare(that.discountInPercent, discountInPercent) != 0) return false;
        if (!productCode.equals(that.productCode)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (discountInPercent != +0.0f ? Float.floatToIntBits(discountInPercent) : 0);
        result = 31 * result + productCode.hashCode();
        return result;
    }
}
