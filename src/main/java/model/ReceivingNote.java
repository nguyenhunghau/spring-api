package model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "receiving_note", schema = "business", catalog = "")
public class ReceivingNote {
    private int id;
    private Date date;
    private Customer customer;

    private Collection<ReceivingNoteDetail> receivingDetailCollection;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "PROVIDER_ID")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    
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

    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "RECEIVING_NOTE_ID")
    public Collection<ReceivingNoteDetail> getReceivingDetailCollection() {
        return receivingDetailCollection;
    }

    public void setReceivingDetailCollection(Collection<ReceivingNoteDetail> receivingDetailCollection) {
        this.receivingDetailCollection = receivingDetailCollection;
    }

    public void merge(ReceivingNote note) {
        this.date = note.getDate();
        this.customer= note.getCustomer();
    }
}
