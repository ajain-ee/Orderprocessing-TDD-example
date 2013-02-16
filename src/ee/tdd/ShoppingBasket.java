package ee.tdd;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {
    private static final float VAT = 0.12f;
    Discounts discounts;
    private List<Item> items;

    public ShoppingBasket(Discounts discounts) {
        this.discounts = discounts;
        items = new ArrayList<Item>();
    }

    public float getVAT() {
        return VAT;
    }

    public void addItems(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public float getTotalPrice() {
        float totalPrice = 0;
        for (Item item : items) {
            float productPrice = item.calculatePrice();
            float discountedPrice = productPrice * (1 - discounts.getDiscountForProduct(item.getProduct().getCode()) / 100);
            totalPrice += discountedPrice;
        }
        return totalPrice + totalPrice * VAT;
    }
}
