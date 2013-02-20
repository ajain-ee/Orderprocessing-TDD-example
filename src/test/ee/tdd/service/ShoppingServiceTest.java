package ee.tdd.service;

import ee.tdd.model.Item;
import ee.tdd.model.ShoppingBasket;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ShoppingServiceTest {

    private ShoppingBasket basket;
    private ShoppingService shoppingService;

    @Before
    public void before() {
        basket = mock(ShoppingBasket.class);
        shoppingService = new ShoppingService(basket);
    }

    @Test
    public void itShouldAddItemToCart() {
        Item item = mock(Item.class);

        shoppingService.addItem(item);

        verify(basket).addItems(item);
    }

    @Test
    public void itShouldGetAllItemsFromBasket() {

        ArrayList<Item> expectedItems = new ArrayList<Item>();
        expectedItems.add(mock(Item.class));

        given(basket.getItems()).willReturn(expectedItems);

        List<Item> items = shoppingService.getItems();

        verify(basket).getItems();
        assertEquals(expectedItems, items);


    }
}
