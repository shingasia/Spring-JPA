package com.myboard.learning2.entities.mall;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name = "customer2")
public class Customer2 {

    @Id
    @Column(name = "customer_id")
    private long customerId;
    @Column(name = "customer_name")
    private String customerName;

    @OneToMany(targetEntity = Customer2_Product2.class, mappedBy = "customer")
    private List<Customer2_Product2> orders = new ArrayList<Customer2_Product2>();
    
    
    // Getter / Setter
    public long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public List<Customer2_Product2> getOrders() {
        return orders;
    }
    public void setOrders(List<Customer2_Product2> orders) {
        this.orders = orders;
    }
    
}


