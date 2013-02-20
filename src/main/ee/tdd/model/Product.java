package ee.tdd.model;

public class Product {
    private String productCode;
    private String name;
    private float price;

    public Product(){

    }

    public Product(String productCode, String name, float price) {
        this.productCode = productCode;
        this.name = name;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(float price) {
		this.price = price;
	}
    
    
}

