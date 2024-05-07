package com.myboard.learning2.entities.mall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "product2")
public class Product2 {

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "shopSeller", column = @Column(name = "shop_seller")),
        @AttributeOverride(name = "productCode", column = @Column(name = "p_code")),
        @AttributeOverride(name = "optionCode", column = @Column(name = "o_code")),
    })
    private Product2_PK product_PK;
    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Customer2_Product2.class, mappedBy = "product")
    private List<Customer2_Product2> orders = new ArrayList<Customer2_Product2>();
    @Embeddable
    public static class Product2_PK implements Serializable {
        public String shopSeller;  // 공급사(매입처) => 농심, 해찬들, 해태제과, 오뚜기
        public String productCode; // 상품코드
        public String optionCode;  // 옵션코드 => 사이즈, 색상, 두께

        // 생성자1
        public Product2_PK() { }
        // 생성자2
        public Product2_PK(String shopSeller, String productCode, String optionCode) {
            this.shopSeller = shopSeller;
            this.productCode = productCode;
            this.optionCode = optionCode;
        }
        @Override
        public int hashCode() {
            return super.hashCode();
        }
        @Override
        public boolean equals(Object obj) {
            if(obj == null){
                return false;
            }else if(obj == this){
                return true;
            }else if(this.getClass() != obj.getClass()){
                return false;
            }
            Product2_PK other = (Product2_PK) obj;
            return (
                this.shopSeller.equals(other.shopSeller)
                &&
                this.productCode.equals(other.productCode)
                &&
                this.optionCode.equals(other.optionCode)
            );
        }
    }
    // Getter / Setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Product2_PK getProduct_PK() {
        return product_PK;
    }
    public void setProduct_PK(Product2_PK product_PK) {
        this.product_PK = product_PK;
    }
    public List<Customer2_Product2> getOrders() {
        return orders;
    }
    public void setOrders(List<Customer2_Product2> orders) {
        this.orders = orders;
    }
    
}
/*
 * 🔴 Composite Primary Keys 정의하려면 몇 가지 규칙을 따라야 합니다.
 * 1) composite primary key 클래스는 public 으로 해야합니다.
 * 2) 인수가 없는 생성자(no-arg constructor)가 있어야 합니다.
 * 3) equals(), hashCode() 메서드를 정의해야 합니다.
 * 4) Serializable 인터페이스를 구현해야 합니다.
 */

