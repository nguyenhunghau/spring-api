package model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "delivery_note", schema = "business", catalog = "")
public class DeliveryNote {
    private int id;
    private Integer quantity;
    private Date date;
    private Product product;
    private Customer customer;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "QUANTITY")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "DATE")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @OneToOne
    @JoinColumn(name = "CUSTOMER_ID")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void merge(DeliveryNote note) {
        this.customer = note.getCustomer();
        this.date = note.getDate();
        this.product = note.getProduct();
        this.quantity = note.getQuantity();
    }
}
