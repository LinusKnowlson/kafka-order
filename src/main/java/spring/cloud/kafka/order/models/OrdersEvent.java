package spring.cloud.kafka.order.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrdersEvent {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String supplier;

    @Column(name = "product")
    private String supplierRecord;

    public OrdersEvent() {
    }

    public OrdersEvent(Orders orders) {
        super();
        this.setSupplier(orders.getSupplier());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSupplierRecord() {
        return supplierRecord;
    }

    public void setSupplierRecord(String supplierRecord) {
        this.supplierRecord = supplierRecord;
    }
}
