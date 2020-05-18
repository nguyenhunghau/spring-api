package model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "receiving_note", schema = "business", catalog = "")
public class ReceivingNote {
    private int id;
    private Date date;
    private Customer provider;

    private Collection<ReceivingNoteDetail> receivingDetailCollection;

    public ReceivingNote() {

    }

    public ReceivingNote(int id, Customer provider, List<ReceivingNoteDetail> detailList) {
        this.id = id;
        java.util.Date dateTime = new java.util.Date();
        this.date = new Date(dateTime.getTime());
        this.provider = provider;
        this.receivingDetailCollection = detailList;
    }

    @OneToOne
    @JoinColumn(name = "PROVIDER_ID")
    public Customer getProvider() {
        return provider;
    }

    public void setProvider(Customer customer) {
        this.provider = customer;
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
        this.provider = note.getProvider();
    }
}
