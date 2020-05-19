package model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.xml.soap.Detail;
import java.util.Objects;

@Entity
@Table(name = "sale_invoice_detail", schema = "business", catalog = "")
public class SaleInvoiceDetail {
    private int id;
    private Integer quantity;
    private Double price;
    private SaleInvoice saleInvoice;
    private Product product;

    @Id
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
    @Column(name = "PRICE")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
    @JoinColumn(name = "SALE_INVOICE_ID")
    @JsonBackReference
    public SaleInvoice getSaleInvoice() {
        return saleInvoice;
    }

    public void setSaleInvoice(SaleInvoice saleInvoice) {
        this.saleInvoice = saleInvoice;
    }

    public void merge(SaleInvoiceDetail detail) {
        this.quantity = detail.getQuantity();
        this.price = detail.getPrice();
        this.product = detail.getProduct();
    }
}
