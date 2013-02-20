package ee.tdd.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import ee.tdd.model.ProductDiscount;
import org.springframework.data.mongodb.core.MongoTemplate;

public class DiscountRepositoryImpl implements DiscountRepositoryCustom {

    private MongoTemplate mongoTemplate;

    public DiscountRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public float getDiscountForProductCode(String code) {

        DBCollection collection = mongoTemplate.getCollection(mongoTemplate.getCollectionName(ProductDiscount.class));
        BasicDBObject query = new BasicDBObject("productCode", code);
        DBObject discount = collection.findOne(query);
        if (discount != null)
           return Float.parseFloat(discount.get("discountInPercent").toString());

        return 0;
    }
}
