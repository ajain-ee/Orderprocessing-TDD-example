package ee.tdd.model;

import java.util.ArrayList;
import java.util.List;

import ee.tdd.dao.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class ShoppingBasket {
    private static final float VAT = 0.12f;
    private List<Item> items;
    private  DiscountRepository discountRepository;

    @Autowired()
    public ShoppingBasket(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
       /* discounts.add(new ProductDiscount("CODE1", 10));
        discounts.add(new ProductDiscount("CODE2", 15));
        discounts.add(new ProductDiscount("CODE3", 20));*/

        items = new ArrayList<Item>();
    }

    public float getVAT() {
        return VAT;
    }

    public void addItems(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
    	for(Item item:items){
    		System.out.println(item.getProduct().getProductCode());
    	}
        return items;
    }

    public float getTotalPrice() {
        float totalPrice = 0;
        for (Item item : items) {
            float productPrice = item.calculatePrice();
            float discountedPrice = productPrice * (1 - discountRepository.getDiscountForProductCode(item.getProduct().getProductCode()) / 100);
            totalPrice += discountedPrice;
        }
        return totalPrice + totalPrice * VAT;
    }
}
