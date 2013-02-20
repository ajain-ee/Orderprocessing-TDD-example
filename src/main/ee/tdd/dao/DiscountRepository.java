package ee.tdd.dao;

import ee.tdd.model.ProductDiscount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends MongoRepository<ProductDiscount, String>, DiscountRepositoryCustom {

    ProductDiscount findByProductCode(String productCode);

}
