package model;

import javax.persistence.*;

@Entity
@Table(name = "receiving_note_detail", schema = "business", catalog = "")
public class ReceivingNoteDetail {
    private int id;
    private Integer quantity;
    private Product product;

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

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void merge(ReceivingNoteDetail detail) {
        this.quantity = detail.getQuantity();
        this.product = detail.getProduct();
    }
}
