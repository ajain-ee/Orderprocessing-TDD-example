package ee.tdd;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DiscountTest {

    @Test
    public void itAddsNewDiscounts(){
        Discounts discounts = new Discounts();
        ProductDiscount productDiscount = new ProductDiscount("CODE1",15);
        discounts.add(productDiscount);

        HashMap<String,ProductDiscount> productDiscounts = discounts.getProductDiscounts();
        assertNotNull(productDiscounts);
        assertTrue(productDiscounts.containsValue(productDiscount));
    }

    @Test
    public void itReturnsDiscountForAProduct(){
        Discounts discounts = new Discounts();
        int price = 25000;
        String productCode = "CODE1";
        int discount = 15;
        ProductDiscount productDiscount = new ProductDiscount(productCode, discount);
        discounts.add(productDiscount);

        float discountInPercentage = discounts.getDiscountForProduct(productCode);
        assertEquals(discount,discountInPercentage,0.0001);

    }
}
