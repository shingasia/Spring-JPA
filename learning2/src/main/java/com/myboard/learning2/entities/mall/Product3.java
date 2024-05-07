package com.myboard.learning2.entities.mall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.myboard.learning2.dto.ProductDTO1;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.SqlResultSetMappings;
import jakarta.persistence.Table;

@Entity(name = "Product3")
@Table(name = "product3")
@NamedQueries({
    @NamedQuery(name = "Product3.getCountOptYN",
        query = " SELECT (CASE "
              + "             WHEN A.optionPrice1 != 0 OR A.optionPrice2 != 0 THEN 'opt_yes' "
              + "             ELSE 'opt_no' "
              + "         END) AS OPT_YN "
              + "        ,COUNT(A) AS CNT "
              + " FROM   Product3 AS A "
              + " GROUP BY (CASE "
              + "               WHEN A.optionPrice1 != 0 OR A.optionPrice2 != 0 THEN 'opt_yes' "
              + "               ELSE 'opt_no' "
              + "           END) "
    )
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "Product3.getSaleStats",
        query = " SELECT A.product_name, B.주문횟수, B.총주문개수 "
              + " FROM   product3 AS A "
              + " LEFT OUTER JOIN ( "
              + " 	SELECT B.od_pcode, B.od_ocode1, B.od_ocode2, COUNT(*) AS 주문횟수, SUM(od_cnt) AS 총주문개수 "
              + " 	FROM  customer3_product3 AS B "
              + " 	GROUP BY B.od_pcode, B.od_ocode1, B.od_ocode2 "
              + " ) AS B "
              + " ON     A.product_code = B.od_pcode "
              + " AND    A.option_code1 = B.od_ocode1 "
              + " AND    A.option_code2 = B.od_ocode2 "
              + " ORDER BY B.총주문개수 DESC ",
        resultSetMapping = "Product3.ProductDTO1"
    )
})
@SqlResultSetMappings({
    @SqlResultSetMapping(
        name = "Product3.ProductDTO1",
        classes = {
            @ConstructorResult(
                targetClass = ProductDTO1.class,
                columns = {
                    @ColumnResult(name = "product_name",type = String.class),
                    @ColumnResult(name = "주문횟수", type = Integer.class),
                    @ColumnResult(name = "총주문개수", type = Integer.class)
                }
            )
        }
    )
})
public class Product3 {
    @EmbeddedId
    private Product3_PK product_pk;

    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_price")
    private int productPrice;
    @Column(name = "option_price1")
    private int optionPrice1;
    @Column(name = "option_price2")
    private int optionPrice2;

    @OneToMany(targetEntity = Customer3_Product3.class, mappedBy = "product")
    private List<Customer3_Product3> orders = new ArrayList<Customer3_Product3>();
    @Embeddable
    public static class Product3_PK implements Serializable {
        @Column(name = "product_code")
        public Long productCode;
        @Column(name = "option_code1")
        public String optionCode1;
        @Column(name = "option_code2")
        public String optionCode2;

        // 생성자1
        public Product3_PK() { }
        // 생성자2
        public Product3_PK(Long productCode, String optionCode1, String optionCode2) {
            this.productCode = productCode;
            this.optionCode1 = optionCode1;
            this.optionCode2 = optionCode2;
        }
        @Override
        public int hashCode() {
            return super.hashCode();
        }
        @Override
        public boolean equals(Object obj) {
            if(obj == null) {
                return false;
            }else if(obj == this) {
                return true;
            }else if(this.getClass() != obj.getClass()) {
                return false;
            }
            Product3_PK other = (Product3_PK) obj;
            return (
                this.productCode == other.productCode
                &&
                this.optionCode1.equals(other.optionCode1)
                &&
                this.optionCode2.equals(other.optionCode2)
            );
        }
    }
    // Getter / Setter
    public Product3_PK getProduct_pk() {
        return product_pk;
    }
    public void setProduct_pk(Product3_PK product_pk) {
        this.product_pk = product_pk;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
    public int getOptionPrice1() {
        return optionPrice1;
    }
    public void setOptionPrice1(int optionPrice1) {
        this.optionPrice1 = optionPrice1;
    }
    public int getOptionPrice2() {
        return optionPrice2;
    }
    public void setOptionPrice2(int optionPrice2) {
        this.optionPrice2 = optionPrice2;
    }
    public List<Customer3_Product3> getOrders() {
        return orders;
    }
    public void setOrders(List<Customer3_Product3> orders) {
        this.orders = orders;
    }
    
}
