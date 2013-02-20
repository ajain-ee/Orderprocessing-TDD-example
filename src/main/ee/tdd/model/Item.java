package ee.tdd.model;

public class Item {

    private Product product;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private int quantity;

    public Item(){
    	
    }
    public Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public float calculatePrice() {
        return product.getPrice()*quantity;
    }
}
