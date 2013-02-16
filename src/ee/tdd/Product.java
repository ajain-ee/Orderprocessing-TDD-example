package ee.tdd;

public class Product {
    private String productCode;
    private String name;
    private float price;
    private String code;

    public Product(String productCode, String name, float price) {
        this.productCode = productCode;
        this.name = name;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }
}

