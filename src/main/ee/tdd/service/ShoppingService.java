package ee.tdd.service;

import java.util.ArrayList;
import java.util.List;

import ee.tdd.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ee.tdd.model.ShoppingBasket;

@Service
public class ShoppingService {

	public ShoppingBasket shoppingBasket;

	@Autowired
	public ShoppingService(ShoppingBasket shoppingBasket) {
		this.shoppingBasket = shoppingBasket;
	}

	public void addItem(Item item){
		shoppingBasket.addItems(item);
	}

	public List<Item> getItems(){
	  return shoppingBasket.getItems();
	}
}
