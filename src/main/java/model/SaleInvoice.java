package model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "sale_invoice", schema = "business", catalog = "")
public class SaleInvoice {
    private int id;
    private Date date;
    private Customer customer;
    private Staff staff;

    private Collection<SaleInvoiceDetail> saleInvoiceDetailCollection;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @JoinColumn(name = "CUSTOMER_ID")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToOne
    @JoinColumn(name = "STAFF_ID")
    public Staff getStaff() {
        return staff;
    }

    @OneToMany(mappedBy = "saleInvoice", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JsonManagedReference
    public Collection<SaleInvoiceDetail> getSaleInvoiceDetailCollection() {
        return saleInvoiceDetailCollection;
    }

    public void setSaleInvoiceDetailCollection(Collection<SaleInvoiceDetail> saleInvoiceDetailCollection) {
        this.saleInvoiceDetailCollection = saleInvoiceDetailCollection;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void merge(SaleInvoice note) {
        this.customer = note.getCustomer();
        this.date = note.getDate();
        this.staff = note.getStaff();
    }
}
