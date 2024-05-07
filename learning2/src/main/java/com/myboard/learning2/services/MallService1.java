package com.myboard.learning2.services;

import org.springframework.stereotype.Service;

import com.myboard.learning2.entities.mall.Customer1;
import com.myboard.learning2.entities.mall.Customer2;
import com.myboard.learning2.entities.mall.Customer2_Product2;
import com.myboard.learning2.entities.mall.Product1;
import com.myboard.learning2.entities.mall.Product2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;

@Service("mallService1")
public class MallService1 {
    
    @PersistenceUnit
    private EntityManagerFactory emf;

    public void test1() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try{
            tx = em.getTransaction();
            tx.begin();
            Product1.Product1_PK objPK = new Product1.Product1_PK();
            objPK.shopSeller = "농심";
            objPK.productCode = "G001";
            objPK.optionCode = "001";

            Product1 obj = new Product1();
            obj.setProduct_PK(objPK);
            obj.setName("농심 새우깡 매운맛");
            em.persist(obj);
            
            tx.commit();
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally{
            em.close();
        }
    }

    public void test2() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try{
            tx = em.getTransaction();
            tx.begin();

            Product1 product1 = new Product1();
            product1.setProduct_PK(new Product1.Product1_PK("해태제과", "G002", "003"));
            product1.setName("해태 부라보콘 초코맛");
            em.persist(product1); // 상품등록1

            Product1 product2 = new Product1();
            product2.setProduct_PK(new Product1.Product1_PK("삼양", "G011", "002"));
            product2.setName("삼양 불닭볶음면 크림까르보");
            em.persist(product2); // 상품등록2

            Customer1 customer1 = new Customer1();
            customer1.setCustomerId(1L);
            customer1.setCustomerName("고객1");
            em.persist(customer1); // 고객등록

            // 주문등록
            customer1.getProducts().add(product1); // Owner ⭕️
            customer1.getProducts().add(product2);
            // product1.getCustomers().add(customer1); // Owner ❌
            // product2.getCustomers().add(customer1);

            tx.commit();
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally{
            em.close();
        }
    }
    
    public void test3() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try{
            tx = em.getTransaction();
            tx.begin();

            Product2 p1 = new Product2();
            p1.setProduct_PK(new Product2.Product2_PK("오뚜기", "G008", "001"));
            p1.setName("오뚜기 고소한참기름 320ML");
            em.persist(p1); // 상품등록1

            Product2 p2 = new Product2();
            p2.setProduct_PK(new Product2.Product2_PK("CJ", "G090", "000"));
            p2.setName("스팸 클래식 200gX10개");
            em.persist(p2); // 상품등록2

            Customer2 c1 = new Customer2();
            c1.setCustomerId(1L);
            c1.setCustomerName("고객2");
            em.persist(c1); // 고객등록

            Customer2_Product2 order;
            order = new Customer2_Product2(); // 주문등록1
            order.setCustomer(c1); // 양방향 참조는 setter 안에 넣었다.
            order.setProduct(p1);
            order.setMemo("배송메모...");
            em.persist(order);

            order = new Customer2_Product2(); // 주문등록2
            order.setCustomer(c1);
            order.setProduct(p2);
            order.setMemo("배송메모...");
            em.persist(order);

            tx.commit();
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally{
            em.close();
        }
    }
}
/*
test1() 메서드 실행 결과
==================================================================================================================================
🔴 @EmbeddedId, @Embeddable 어노테이션을 사용하여 다음과 같이 composite primary key 가 만들어진다.

CREATE TABLE `product1` (
  `name` varchar(255) DEFAULT NULL,
  `o_code` varchar(255) NOT NULL,
  `p_code` varchar(255) NOT NULL,
  `shop_seller` varchar(255) NOT NULL,
  PRIMARY KEY (`o_code`,`p_code`,`shop_seller`)
)



test2() 메서드 실행 결과
==================================================================================================================================
🔴 아래와 같이 고객(customer1) 테이블과 연결테이블(customer1_product1)이 생성되고, @ManyToMany, @JoinTable 어노테이션으로 연결 엔티티 없이 연결 테이블을 직접 매핑했다.
🔴 상품, 고객 양쪽 엔티티에 컬렉션 변수가 있는 '양방향'에서는 연관관계의 소유자만이 foreign key에 대해 관리(등록, 수정, 삭제)할 수 있고, 반대쪽은 오로지 읽기(조회)만 가능하다.

CREATE TABLE `customer1` (
  `customer_id` bigint(20) NOT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
)

CREATE TABLE `customer1_product1` (
  `customer_id` bigint(20) NOT NULL,
  `o_code` varchar(255) NOT NULL,
  `p_code` varchar(255) NOT NULL,
  `shop_seller` varchar(255) NOT NULL,
  UNIQUE KEY `UKml48mlat0vpcqyqrb0aymt7df` (`customer_id`,`shop_seller`,`p_code`,`o_code`),
  KEY `FKe5mcwsxe4htky36aduevklifc` (`shop_seller`,`p_code`,`o_code`),
  CONSTRAINT `FKe5mcwsxe4htky36aduevklifc` FOREIGN KEY (`shop_seller`, `p_code`, `o_code`) REFERENCES `product1` (`o_code`, `p_code`, `shop_seller`),
  CONSTRAINT `FKmi0dgjhxw1oqebk6o2o7ghrek` FOREIGN KEY (`customer_id`) REFERENCES `customer1` (`customer_id`)
)

test3() 메서드 실행 결과
==================================================================================================================================
🔴 test2()와 다르게 연결 클래스(Customer2_Product2)를 만들어 엔티티로 사용했다. 그리고 PK와 FK를 분리하여 비식별관계(Non-Identifying Relationship)로 만들었다.
🔴 영속 컨테이너를 통해 엔티티를 조회했을 때는 검색된 객체들이 양방향 참조를 유지하지만, 영속 컨테이너를 이용하지 않은 상태에서는 양방향 참조를 유지하지 않는다.
    그래서 연관관계의 소유자(Owner)인 Customer2_Product2 클래스의 setter 메서드에 양방향 참조를 위한 코드를 작성했다.
🔴 실행결과 생성되는 테이블은 아래와 같다.

CREATE TABLE `product2` (
  `name` varchar(255) DEFAULT NULL,
  `o_code` varchar(255) NOT NULL,
  `p_code` varchar(255) NOT NULL,
  `shop_seller` varchar(255) NOT NULL,
  PRIMARY KEY (`o_code`,`p_code`,`shop_seller`)
)

CREATE TABLE `customer2` (
  `customer_id` bigint(20) NOT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
)

CREATE TABLE `customer2_product2` (
  `od_customer_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `memo` longtext NOT NULL DEFAULT '',
  `od_option_code` varchar(255) DEFAULT NULL,
  `od_product_code` varchar(255) DEFAULT NULL,
  `od_shop_seller` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FKsk3fiftgrgfjl8tnh1s8v9rn8` (`od_customer_id`),
  KEY `FK9g2dr9gwcq8rj6tmo7tppxed3` (`od_option_code`,`od_product_code`,`od_shop_seller`),
  CONSTRAINT `FK9g2dr9gwcq8rj6tmo7tppxed3` FOREIGN KEY (`od_option_code`, `od_product_code`, `od_shop_seller`) REFERENCES `product2` (`o_code`, `p_code`, `shop_seller`),
  CONSTRAINT `FKsk3fiftgrgfjl8tnh1s8v9rn8` FOREIGN KEY (`od_customer_id`) REFERENCES `customer2` (`customer_id`)
)
*/