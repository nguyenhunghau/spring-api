package model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "delivery_note", schema = "business", catalog = "")
public class DeliveryNote {
    private int id;
    private Date date;
    private Customer customer;
    private Collection<DeliveryNoteDetail> deliveryNoteDetailCollection;

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

    @OneToMany(mappedBy = "deliveryNote", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JsonManagedReference
    public Collection<DeliveryNoteDetail> getDeliveryNoteDetailCollection() {
        return deliveryNoteDetailCollection;
    }

    public void setDeliveryNoteDetailCollection(Collection<DeliveryNoteDetail> deliveryNoteDetailCollection) {
        this.deliveryNoteDetailCollection = deliveryNoteDetailCollection;
    }

    public void merge(DeliveryNote note) {
        this.customer = note.getCustomer();
        this.date = note.getDate();
    }
}
