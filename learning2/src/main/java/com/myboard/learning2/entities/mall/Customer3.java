package com.myboard.learning2.entities.mall;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer3")
public class Customer3 {
    @Id
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name")
    private String customerName;

    @OneToMany(targetEntity = Customer3_Product3.class, mappedBy = "customer")
    private List<Customer3_Product3> orders = new ArrayList<Customer3_Product3>();

    // Getter / Setter
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public List<Customer3_Product3> getOrders() {
        return orders;
    }
    public void setOrders(List<Customer3_Product3> orders) {
        this.orders = orders;
    }
}
