package ee.tdd.service;

import ee.tdd.dao.DiscountRepository;
import ee.tdd.model.Item;
import ee.tdd.model.ProductDiscount;

import java.util.Collection;

public class DiscountService {
    private DiscountRepository repository;

    public DiscountService( DiscountRepository repository) {
        this.repository = repository;
    }

    public float getDiscountForItem(Item item) {
        return repository.getDiscountForProductCode(item.getProduct().getProductCode());
    }

    public void addProductDiscount(String productCode, float discount) {
        ProductDiscount productDiscount = new ProductDiscount(productCode, discount);
        repository.save(productDiscount);
    }

    public Collection<ProductDiscount> getDiscounts() {
        return repository.findAll();
    }

    public ProductDiscount getDiscountForProductCode(String productCode) {
        return repository.findByProductCode(productCode);
    }

    public Float getDiscountAmountForProductCode(String productCode) {
        return repository.getDiscountForProductCode(productCode);
    }
}
