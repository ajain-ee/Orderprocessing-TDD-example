package ee.tdd.controllers;

import ee.tdd.model.Item;
import ee.tdd.model.Product;
import ee.tdd.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/cart")
public class ShoppingController {
    // private ShoppingBasket shoppingBasket;
    private ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @RequestMapping(value = "items", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public
    @ResponseBody
    String addItemToBasket(@RequestBody Item item) {
        System.out.println("Inside Controller" + item.getQuantity());
        shoppingService.addItem(item);
        return "Added Succesfully";
    }

    @RequestMapping(value = "items", method = RequestMethod.PUT)
    public
    @ResponseBody
    String addItemToBasket(ModelMap map) {
        shoppingService.addItem(new Item(new Product("CODE1", "Mobile", 5000),
                2));
        return "Added Succesfully";
    }

    @RequestMapping(value = "items", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Item> getItemFromBasket() {
        return shoppingService.getItems();
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public
    @ResponseBody
    String helthCheck(ModelMap map) {
        return "Shopping Controller Working";
    }
}
