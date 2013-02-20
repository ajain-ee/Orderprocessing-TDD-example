package ee.tdd.controllers;

import ee.tdd.model.ProductDiscount;
import ee.tdd.service.DiscountService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/admin")
public class DiscountsController {

    private DiscountService discountService;

    public DiscountsController(DiscountService discountService) {

        this.discountService = discountService;
    }

    @RequestMapping(value = "discounts", method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<ProductDiscount> getAllDiscounts() {
        return discountService.getDiscounts();
    }

    @RequestMapping(value = "discounts", method = RequestMethod.POST)
    public
    @ResponseBody
    void addDiscount(@RequestParam(value = "productCode") String productCode,
                     @RequestParam(value = "discount") Float discount) {
        discountService.addProductDiscount(productCode, discount);
    }

    @RequestMapping(value = "discounts/{productCode}", method = RequestMethod.GET)
    public
    @ResponseBody
    ProductDiscount getDiscount(@PathVariable(value = "productCode") String productCode) {
        return discountService.getDiscountForProductCode(productCode);
    }

    @RequestMapping(value = "discounts/{productCode}/discount", method = RequestMethod.GET)
    public
    @ResponseBody
    Float getDiscountAmount(@PathVariable(value = "productCode") String productCode) {
        return discountService.getDiscountAmountForProductCode(productCode);
    }
}
