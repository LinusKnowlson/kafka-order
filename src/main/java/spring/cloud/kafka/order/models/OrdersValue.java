package spring.cloud.kafka.order.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class OrdersValue {

    private Long id;
    private String supplier;
    private String product;
    private String quantity;

    public OrdersValue() {
    }

    public OrdersValue(String supplier, String product, String quantity) {
        this.supplier = supplier;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", supplier='" + supplier + '\'' +
                ", product='" + product + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
