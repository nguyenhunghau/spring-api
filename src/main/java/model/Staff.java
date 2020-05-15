package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Staff {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    
    public Staff() {
        
    }

    public Staff(int id, String name, String address, String phone, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return id == staff.id &&
                Objects.equals(name, staff.name) &&
                Objects.equals(address, staff.address) &&
                Objects.equals(phone, staff.phone) &&
                Objects.equals(email, staff.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phone, email);
    }

    public void merge(Staff staff) {
        this.name = staff.getName();
        this.address = staff.getAddress();
        this.phone = staff.getPhone();
        this.email = staff.getEmail();
    }
}
