package spring.cloud.kafka.order.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Orders {

    @Id
    @GeneratedValue
    private Long id;
    private String supplier;
    private String product;
    private int quantity;
    private Long customerId;
    private Long productId;

    public Orders() {
    }

    public Orders(String supplier, String product, int quantity,Long customerId, Long productId) {
        this.supplier = supplier;
        this.product = product;
        this.quantity = quantity;
        this.customerId = customerId;
        this.productId = productId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", supplier='" + supplier + '\'' +
                ", product='" + product + '\'' +
                ", quantity='" + quantity + '\'' +
                ", customer='" + customerId + '\'' +
                ", productId='" + productId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Orders))
            return false;
        Orders Orders = (Orders) o;
        return Objects.equals(this.id, Orders.id)
                && Objects.equals(this.supplier, Orders.supplier)
                && Objects.equals(this.product, Orders.product)
                && Objects.equals(this.quantity, Orders.quantity)
                && Objects.equals(this.customerId, Orders.customerId)
                && Objects.equals(this.productId, Orders.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.supplier, this.product, this.quantity, this.customerId, this.productId);
    }
}
