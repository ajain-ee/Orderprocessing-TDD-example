package ee.tdd.model;


import ee.tdd.model.Item;
import ee.tdd.model.Product;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {

    @Test
      public void itShouldCalculatePrice(){
        Product book = new Product("CODE1", "Book", 10.0f);
        Item item = new Item(book, 2);
        float itemPrice = item.calculatePrice();
        assertEquals(20,itemPrice,0);
    }
}
