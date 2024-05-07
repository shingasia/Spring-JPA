package com.myboard.learning2.entities.mall;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "Customer2_Product2")
@Table(name = "customer2_product2")
public class Customer2_Product2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    
    @ManyToOne(targetEntity = Customer2.class)
    @JoinColumn(name = "od_customer_id", referencedColumnName = "customer_id")
    private Customer2 customer;

    @ManyToOne(targetEntity = Product2.class)
    @JoinColumns(
        value = {
            @JoinColumn(name = "od_shop_seller", referencedColumnName = "shop_seller"),
            @JoinColumn(name = "od_product_code", referencedColumnName = "p_code"),
            @JoinColumn(name = "od_option_code", referencedColumnName = "o_code")
        }
    )
    private Product2 product;
    
    @Column(name = "memo", columnDefinition = " LONGTEXT NOT NULL DEFAULT '' ")
    private String memo;
    
    // Getter / Setter
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Customer2 getCustomer() {
        return customer;
    }
    public void setCustomer(Customer2 customer) {
        this.customer = customer;
        customer.getOrders().add(this); // 양방향 참조
    }
    public Product2 getProduct() {
        return product;
    }
    public void setProduct(Product2 product) {
        this.product = product;
        product.getOrders().add(this); // 양방향 참조
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    
}
