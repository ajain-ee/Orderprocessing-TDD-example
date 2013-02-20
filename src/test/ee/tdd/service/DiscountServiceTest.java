package ee.tdd.service;

import ee.tdd.dao.DiscountRepository;
import ee.tdd.model.Item;
import ee.tdd.model.Product;
import ee.tdd.model.ProductDiscount;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DiscountServiceTest {

    private Discounts discounts;
    private DiscountService discountService;

    @Before
    public void before() {
        discounts = mock(Discounts.class);
        discountService = new DiscountService(discounts, mock(DiscountRepository.class));
    }

    @Test
    public void itShouldGetDiscountForItem() {

        Item item = mock(Item.class);
        String productCode = "productCode";
        Product product = mock(Product.class);

        given(product.getProductCode()).willReturn(productCode);
        given(item.getProduct()).willReturn(product);
        float discountInPercentage = 20;
        given(discounts.getDiscountForProduct(productCode)).willReturn(discountInPercentage);

        float discount = discountService.getDiscountForItem(item);

        verify(discounts).getDiscountForProduct(productCode);
        assertEquals(discountInPercentage, discount);
    }

    @Test
    public void itShouldAddDiscountForItem() {
        String productCode = "productCode";
        float discount = 10f;
        ProductDiscount productDiscount = new ProductDiscount(productCode, discount);
        discountService.addProductDiscount(productCode, discount);
        verify(discounts).add(productDiscount);
    }

    @Test
    public void itShouldReturnAllDiscounts() {

        HashMap<String, ProductDiscount> expectedDiscounts = new HashMap<String, ProductDiscount>();
        expectedDiscounts.put("code", mock(ProductDiscount.class));

        given(discounts.getProductDiscounts()).willReturn(expectedDiscounts);

        Collection<ProductDiscount> productDiscounts = discountService.getDiscounts();

        verify(discounts).getProductDiscounts();
        assertEquals(expectedDiscounts.values(), productDiscounts);
    }

}
