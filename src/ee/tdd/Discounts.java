package ee.tdd;

import java.util.HashMap;

public class Discounts {

    private HashMap<String, ProductDiscount> productDiscounts;

    public Discounts() {
        productDiscounts = new HashMap<String, ProductDiscount>();
    }

    public void add(ProductDiscount productDiscount) {
        productDiscounts.put(productDiscount.getProductCode(), productDiscount);
    }

    public HashMap<String, ProductDiscount> getProductDiscounts() {
        return productDiscounts;
    }

    public float getDiscountForProduct(String code) {
        if (productDiscounts.containsKey(code)) {
            ProductDiscount productDiscount = productDiscounts.get(code);
            return productDiscount.getDiscountInPercent();
        }
        return 0f;
    }
}
