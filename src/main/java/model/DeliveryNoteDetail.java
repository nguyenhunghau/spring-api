package model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "delivery_note_detail", schema = "business", catalog = "")
public class DeliveryNoteDetail {
    private int id;
    private Integer quantity;
    private Product product;
    private DeliveryNote deliveryNote;

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

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DELIVERY_NOTE_ID")
    @JsonBackReference
    public DeliveryNote getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(DeliveryNote deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    public void merge(DeliveryNoteDetail detail) {
        this.quantity = detail.getQuantity();
        this.product = detail.getProduct();
    }
}
