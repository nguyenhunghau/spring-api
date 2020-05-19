package model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Orders {
    private int id;
    private Date date;
    private Staff staff;
    private Customer provider;

    private Collection<OrderDetail> orderDetailCollection;

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
    @JoinColumn(name = "PROVIDER_ID")
    public Customer getProvider() {
        return provider;
    }

    public void setProvider(Customer provider) {
        this.provider = provider;
    }
    

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name = "ORDER_ID")
    public Collection<OrderDetail> getOrderDetailCollection() {
        return orderDetailCollection;
    }

    public void setOrderDetailCollection(Collection<OrderDetail> detailCollection) {
        this.orderDetailCollection = detailCollection;
    }

    @OneToOne
    @JoinColumn(name = "STAFF_ID")
    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void merge(Orders order) {
        this.date = order.getDate();
        this.staff = order.getStaff();
        this.provider = order.getProvider();
    }
}
