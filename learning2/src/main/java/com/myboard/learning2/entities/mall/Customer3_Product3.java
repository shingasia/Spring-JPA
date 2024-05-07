package com.myboard.learning2.entities.mall;

import com.myboard.learning2.dto.P_JPQL_EX2_DTO1;
import com.myboard.learning2.dto.P_JPQL_EX2_DTO2;
import com.myboard.learning2.dto.ProductDTO1;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.SqlResultSetMappings;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;

@Entity(name = "Customer3_Product3")
@Table(name = "customer3_product3")
@NamedStoredProcedureQueries(
    @NamedStoredProcedureQuery(
        name = "Customer3Product3.P_JPQL_EX2",
        procedureName = "P_JPQL_EX2",
        parameters = {
            @StoredProcedureParameter(name = "P_START_SALES", type = Integer.class, mode = ParameterMode.IN),
            @StoredProcedureParameter(name = "P_END_SALES", type = Integer.class, mode = ParameterMode.IN)
        }
        // ,resultSetMappings = {
        //     "Customer3Product3.P_JPQL_EX2_DTO1", "Customer3Product3.P_JPQL_EX2_DTO2"
        // }
    )
)
/*
@SqlResultSetMappings({
    @SqlResultSetMapping(
        name = "Customer3Product3.P_JPQL_EX2_DTO1",
        classes = {
            @ConstructorResult(
                targetClass = P_JPQL_EX2_DTO1.class,
                columns = {
                    @ColumnResult(name = "product_code",type = Integer.class),
                    @ColumnResult(name = "cnt", type = Integer.class),
                    @ColumnResult(name = "opt_cnt", type = Integer.class),
                    @ColumnResult(name = "min_price", type = Integer.class),
                    @ColumnResult(name = "max_price", type = Integer.class)
                }
            )
        }
    )
    ,
    @SqlResultSetMapping(
        name = "Customer3Product3.P_JPQL_EX2_DTO2",
        classes = {
            @ConstructorResult(
                targetClass = P_JPQL_EX2_DTO2.class,
                columns = {
                    @ColumnResult(name = "od_product_code", type = Integer.class),
                    @ColumnResult(name = "od_option_code1", type = String.class),
                    @ColumnResult(name = "od_option_code2", type = String.class),
                    @ColumnResult(name = "product_name", type = String.class),
                    @ColumnResult(name = "total_sales", type = Integer.class)
                }
            )
        }
    )
})
*/
public class Customer3_Product3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(targetEntity = Customer3.class)
    @JoinColumn(name = "od_customer_id", referencedColumnName = "customer_id")
    private Customer3 customer;

    @ManyToOne(targetEntity = Product3.class)
    @JoinColumns(
        value = {
            @JoinColumn(name = "od_pcode", referencedColumnName = "product_code"),
            @JoinColumn(name = "od_ocode1", referencedColumnName = "option_code1"),
            @JoinColumn(name = "od_ocode2", referencedColumnName = "option_code2")
        }
    )
    private Product3 product;

    @Column(name = "od_cnt")
    private int orderCount;

    @Column(name = "memo", columnDefinition = " LONGTEXT NOT NULL DEFAULT '' ")
    private String memo;

    // Getter / Setter
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Customer3 getCustomer() {
        return customer;
    }
    public void setCustomer(Customer3 customer) {
        this.customer = customer;
        customer.getOrders().add(this);  // 양방향 참조
    }
    public Product3 getProduct() {
        return product;
    }
    public void setProduct(Product3 product) {
        this.product = product;
        product.getOrders().add(this);  // 양방향 참조
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public int getOrderCount() {
        return orderCount;
    }
    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }
    
}
