package ee.tdd;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ShoppingBasketTest {

    private ShoppingBasket shoppingBasket;
    private Product product;

    @Before
    public void before() {
        shoppingBasket = new ShoppingBasket(null);
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
        given(item.calculatePrice()).willReturn(expectedPrice);
        float totalPrice = shoppingBasket.getTotalPrice();
        assertEquals(expectedPrice * 2 * 1.12, totalPrice, 0.001);
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
        given(product.getCode()).willReturn(productCode);
        given(discounts.getDiscountForProduct(productCode)).willReturn(discount);

        float totalPrice = shoppingBasket.getTotalPrice();

        verify(item).getProduct();
        verify(product).getCode();
        verify(discounts).getDiscountForProduct(productCode);

        float expected = productPrice * (1 - discount / 100) * (1 + shoppingBasket.getVAT());
        assertEquals(expected, totalPrice , 0.001);

    }
}
