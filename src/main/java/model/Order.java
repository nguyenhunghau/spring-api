package model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ORDERS")
public class Order {
    private int id;
    private Timestamp date;
    private String staffId;
    private String providerId;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DATE")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "STAFF_ID")
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "PROVIDER_ID")
    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        if (staffId != null ? !staffId.equals(order.staffId) : order.staffId != null) return false;
        if (providerId != null ? !providerId.equals(order.providerId) : order.providerId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (staffId != null ? staffId.hashCode() : 0);
        result = 31 * result + (providerId != null ? providerId.hashCode() : 0);
        return result;
    }
}
