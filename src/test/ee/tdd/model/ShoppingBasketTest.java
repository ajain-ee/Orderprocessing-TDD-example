package ee.tdd.model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class ShoppingBasketTest {

    private ShoppingBasket shoppingBasket;
    private Product product;
    private Discounts discounts;

    @Before
    public void before() {
        discounts = mock(Discounts.class);
        shoppingBasket = new ShoppingBasket(discounts);
        product = new Product("CODE2", "Mobile", 5000);
    }

    @Test
    public void itShouldAddItems() {
        Item item = new Item(product, 1);
        shoppingBasket.addItems(item);
        List<Item> items = shoppingBasket.getItems();
        assertNotNull(items);
        assertEquals(1, items.size());
        assertTrue(items.contains(item));
    }

    @Test
    public void itShouldAddMultipleItemWithQuantity() {
        Item item = new Item(product, 3);
        shoppingBasket.addItems(item);
        List<Item> items = shoppingBasket.getItems();
        assertNotNull(items);
        assertEquals(1, items.size());
        assertEquals(3, items.get(0).getQuantity());
    }

    @Test
    public void testFinalPriceAfterTax() {
        Item item = mock(Item.class);
        shoppingBasket.addItems(item);
        shoppingBasket.addItems(item);
        float expectedPrice = 100;
        String productCode = "productCode";
        Product product = mock(Product.class);

        given(item.calculatePrice()).willReturn(expectedPrice);
        given(item.getProduct()).willReturn(product);
        given(product.getProductCode()).willReturn(productCode);
        float discountInPercentage = 10f;
        given(discounts.getDiscountForProduct(productCode)).willReturn(discountInPercentage);

        float totalPrice = shoppingBasket.getTotalPrice();

        verify(item, times(2)).calculatePrice();
        verify(item, times(2)).getProduct();
        verify(product, times(2)).getProductCode();
        verify(discounts, times(2)).getDiscountForProduct(productCode);

        double VAT = 12;
        int totalItems = shoppingBasket.getItems().size();
        float discount = 1 - discountInPercentage / 100;
        double vat = 1 + VAT / 100;
        assertEquals(expectedPrice * totalItems * discount * vat, totalPrice, 0.001);
    }

    @Test
    public void itGetsDiscountForProduct() {
        Discounts discounts = mock(Discounts.class);
        shoppingBasket = new ShoppingBasket(discounts);
        Item item = mock(Item.class);
        shoppingBasket.addItems(item);

        float productPrice = 100;
        given(item.calculatePrice()).willReturn(productPrice);

        Product product = mock(Product.class);
        given(item.getProduct()).willReturn(product);

        float discount = 15;
        String productCode = "CODE1";
        given(product.getProductCode()).willReturn(productCode);
        given(discounts.getDiscountForProduct(productCode)).willReturn(discount);

        float totalPrice = shoppingBasket.getTotalPrice();

        verify(item).getProduct();
        verify(product).getProductCode();
        verify(discounts).getDiscountForProduct(productCode);

        float expected = productPrice * (1 - discount / 100) * (1 + shoppingBasket.getVAT());
        assertEquals(expected, totalPrice, 0.001);

    }
}
