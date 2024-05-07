package com.myboard.learning2.entities.mall;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name = "customer1")
public class Customer1 {

    @Id
    @Column(name = "customer_id")
    private long customerId;
    @Column(name = "customer_name")
    private String customerName;

    @ManyToMany(targetEntity = Product1.class)
    @JoinTable(
        name = "customer1_product1",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = {
            @JoinColumn(name = "shop_seller"), @JoinColumn(name = "p_code"), @JoinColumn(name = "o_code")
        },
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"customer_id", "shop_seller", "p_code", "o_code"})
        }
    )
    private List<Product1> products = new ArrayList<Product1>();
    
    
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
    public List<Product1> getProducts() {
        return products;
    }
    public void setProducts(List<Product1> products) {
        this.products = products;
    }
}

/*
 * @ManyToMany 어노테이션의 속성들
 * Class targetEntity          : 관계가 매핑되는 대상 엔터티 클래스를 지정합니다.  ✅ 기본값 : void.class
 * FetchType fetch             : 연관된 엔터티를 데이터베이스에서 로드해야 하는 시기를 결정. EAGER는 연관 엔티티를 동시에 조회하며(JOIN 쿼리가 발생), LAZY는 연관 엔티티를 실제 사용할 때 조회한다.   ✅ 기본값 : FetchType.LAZY
 * CascadeType[] cascade       : 영속성 전이 기능을 설정. 연관 엔티티를 같이 저장하거나 삭제할 때 사용한다.     ✅ 기본값 : 사용하지 않음
 * String mappedBy             : 관계를 소유(own)하는 대상 엔터티의 필드를 나타냅니다.
 */

/*
 * @JoinTable 어노테이션의 속성들
 * String name                     : 연결 테이블 이름을 지정합니다.
 * JoinColumn[] joinColumns        : 연결 테이블의 foreign key를 지정합니다. 연관관계의 소유자(Owner) 쪽 테이블의 primary key를 참조합니다.
 * JoinColumn[] inverseJoinColumns : 연결 테이블의 foreign key를 지정합니다. 연관관계의 소유자가 아닌 쪽 테이블의 primary key를 참조합니다.
 * UniqueConstraint[] uniqueConstraints : 테이블에 배치할 unique 제약조건. 복합 고유 키(composite unique key)를 정의하기 위해 사용
 */

