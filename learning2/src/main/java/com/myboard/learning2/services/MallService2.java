package com.myboard.learning2.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.myboard.learning2.dto.ProductDTO1;
import com.myboard.learning2.entities.mall.Customer3;
import com.myboard.learning2.entities.mall.Customer3_Product3;
import com.myboard.learning2.entities.mall.Product1;
import com.myboard.learning2.entities.mall.Product3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;

@Service("mallService2")
public class MallService2 {
    @PersistenceUnit
    private EntityManagerFactory emf;

    public void test1() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        Product3 p = null;
        Customer3 c = null;
        Customer3_Product3 o = null;
        try{
            tx = em.getTransaction();
            tx.begin();
            
            // 상품 등록
            Object[][] data = {
                 {1L, "소프트블루솦", "100ml", "멈칫 섬유향수", 8000, 2000, 0}
                ,{1L, "마젠타레이디", "100ml", "멈칫 섬유향수", 8000, 2000, 0}
                ,{1L, "마린프레쉬코든", "100ml", "멈칫 섬유향수", 8000, 2000, 0}
                ,{1L, "엠버바닐라", "70ml", "멈칫 섬유향수", 8000, 0, 0}
                ,{1L, "라이트블루아쿠아", "70ml", "멈칫 섬유향수", 8000, 0, 0}
                ,{1L, "마시멜로우피치", "70ml", "멈칫 섬유향수", 8000, 0, 0}
                ,{2L, "포레스트블루", "200ml", "배쓰프로젝트 바디스프레이", 42000, 0, 0}
                ,{2L, "웜그레이", "200ml", "배쓰프로젝트 바디스프레이", 42000, 0, 0}
                ,{2L, "오키드허쉬", "200ml", "배쓰프로젝트 바디스프레이", 42000, 0, 0}
                ,{2L, "셀룰리안", "10ml", "배쓰프로젝트 바디스프레이", 2500, 0, 0}
                ,{2L, "쉘핑크", "10ml", "배쓰프로젝트 바디스프레이", 2500, 0, 0}
                ,{3L, "클리어클라우드", "100g", "배쓰프로젝트 입욕제", 7500, 0, 0}
                ,{3L, "미드나잇인뉴욕", "200g", "배쓰프로젝트 입욕제", 7500, 0, 3000}
            };
            for(int i = 0; i < data.length; i++) {
                // System.out.printf("%5d, %10s, %5s\n", data[i][0], data[i][1], data[i][2]);
                p = new Product3();
                p.setProduct_pk(new Product3.Product3_PK((Long)data[i][0], (String)data[i][1], (String)data[i][2]));
                p.setProductName((String)data[i][3]+" "+(String)data[i][1]+" "+(String)data[i][2]);
                p.setProductPrice((Integer)data[i][4]);
                p.setOptionPrice1((Integer)data[i][5]);
                p.setOptionPrice2((Integer)data[i][6]);
                em.persist(p);
            }
            // 고객 등록
            for(int i = 1; i <= 3; i++) {
                c = new Customer3();
                c.setCustomerId(Long.valueOf(i));
                c.setCustomerName("고객"+i);
                em.persist(c);
            }

            // 주문 등록
            // 1L 고객은 1개씩 다, 2L 고객은 홀수 번째 상품 2개씩, 3L 고객은 바디스프레이 1개씩 주문
            for(int i = 1; i <= 3; i++) {
                int orderCnt = (i == 2 ? 2 : 1);
                System.out.println("내부 루프 시작전..."+i);
                for(int j = 0; j < data.length; j++) {
                    if(i == 2 && ((j+1) % 2 == 0)) {
                        continue;
                    }
                    if(i == 3 && (Long)data[j][0] != 2L) {
                        continue;
                    }
                    o = new Customer3_Product3();
                    o.setCustomer(em.find(Customer3.class, i));
                    o.setProduct(em.find(Product3.class, new Product3.Product3_PK((Long)data[j][0], (String)data[j][1], (String)data[j][2])));
                    o.setOrderCount(orderCnt);
                    o.setMemo("메모...");
                    em.persist(o);
                }
            }
            tx.commit();
        }catch(Exception e){
            System.out.println("EX_CLASS - " + e.getClass().getName() + ", EX_MSG - " + e.getMessage());
            tx.rollback();
        }finally{
            em.close();
        }
    }
    public void test2() {
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Object[]> query1 = em.createNamedQuery("Product3.getCountOptYN", Object[].class);
        List<Object[]> result1 = query1.getResultList();
        for(Object[] a : result1) {
            // System.out.printf("옵션가격유무 : %10s, 개수 : %5d\n", (String)a[0], Integer.valueOf(a[1]+""));
            System.out.printf("옵션가격유무 : %10s, 개수 : %5d\n", a[0], a[1]);
        }
        TypedQuery<ProductDTO1> query2 = em.createNamedQuery("Product3.getSaleStats", ProductDTO1.class);
        List<ProductDTO1> result2 = query2.getResultList();
        for(ProductDTO1 a : result2) {
            System.out.println(a);
        }
    }
    public void test3() {
        EntityManager em = emf.createEntityManager();
        
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("Customer3Product3.P_JPQL_EX2");
        // query.registerStoredProcedureParameter(0, getClass(), null)
        query.setParameter("P_START_SALES", 20000);
        query.setParameter("P_END_SALES", 90000);
        query.execute();
        
        List<Object[]> r1 = null, r2 = null;
        r1 = query.getResultList();
        for(Object[] a : r1) {
            System.out.println(Arrays.toString(a));
        }
        if(query.hasMoreResults()) {
            r2 = query.getResultList();
            for(Object[] a : r2) {
                System.out.println(Arrays.toString(a));
            }
        }
        System.out.println("더 있나? "+query.hasMoreResults());
    }
}
/*
🟣 test1() 로 샘플 데이터를 넣어 두고 테스트 => 샘플 데이터를 넣기 위함이다.
1L 고객은 1개씩 다, 2L 고객은 홀수 번째 상품 2개씩, 3L 고객은 바디스프레이 1개씩 주문

test2() 메서드 실행 결과
==================================================================================================================================
🔴 @NamedQuery, @NamedNativeQuery 어노테이션으로 특정 엔티티와 관련된 쿼리를 엔티티 클래스 상단에 등록하고 사용할 수 있다. 이때 쿼리 이름은 중복되면 안되고 관례적으로 엔티티 이름을 접두사로 추가한다. (Product3 엔티티 참고)
🔴 em.createNamedQuery 처럼 네이티브 SQL도 가능하다.
🔴 @NamedNativeQuery는 resultSetMapping이 있어 결과집합을 특정 클래스에 프로젝션 가능하다. 물론 List<Object[]>로 받는 것도 가능하다

옵션가격유무 :     opt_no, 개수 :     9
옵션가격유무 :    opt_yes, 개수 :     4

ProductDTO1 [name=배쓰프로젝트 바디스프레이 쉘핑크 10ml, cnt1=3, cnt2=4]
ProductDTO1 [name=배쓰프로젝트 바디스프레이 오키드허쉬 200ml, cnt1=3, cnt2=4]
ProductDTO1 [name=배쓰프로젝트 바디스프레이 포레스트블루 200ml, cnt1=3, cnt2=4]
ProductDTO1 [name=멈칫 섬유향수 소프트블루솦 100ml, cnt1=2, cnt2=3]
ProductDTO1 [name=멈칫 섬유향수 라이트블루아쿠아 70ml, cnt1=2, cnt2=3]
ProductDTO1 [name=배쓰프로젝트 입욕제 미드나잇인뉴욕 200g, cnt1=2, cnt2=3]
ProductDTO1 [name=멈칫 섬유향수 마린프레쉬코든 100ml, cnt1=2, cnt2=3]
ProductDTO1 [name=배쓰프로젝트 바디스프레이 셀룰리안 10ml, cnt1=2, cnt2=2]
ProductDTO1 [name=배쓰프로젝트 바디스프레이 웜그레이 200ml, cnt1=2, cnt2=2]
ProductDTO1 [name=멈칫 섬유향수 마젠타레이디 100ml, cnt1=1, cnt2=1]
ProductDTO1 [name=멈칫 섬유향수 엠버바닐라 70ml, cnt1=1, cnt2=1]
ProductDTO1 [name=배쓰프로젝트 입욕제 클리어클라우드 100g, cnt1=1, cnt2=1]
ProductDTO1 [name=멈칫 섬유향수 마시멜로우피치 70ml, cnt1=1, cnt2=1]

test3() 메서드 실행 결과
==================================================================================================================================
🔴 @NamedStoredProcedureQuery의 resultSetMappings 속성으로 결과집합 여러개를 DTO로 프로젝션 하는 것이 안된다. 그래서 그냥 List<Object[]>로 받음.

[1, 6, 6, 8000, 10000]
[2, 5, 5, 2500, 42000]
[3, 2, 2, 7500, 10500]

[2, 웜그레이, 200ml, 배쓰프로젝트 바디스프레이 웜그레이 200ml, 84000]
[3, 미드나잇인뉴욕, 200g, 배쓰프로젝트 입욕제 미드나잇인뉴욕 200g, 31500]
[1, 마린프레쉬코든, 100ml, 멈칫 섬유향수 마린프레쉬코든 100ml, 30000]
[1, 소프트블루솦, 100ml, 멈칫 섬유향수 소프트블루솦 100ml, 30000]
[1, 라이트블루아쿠아, 70ml, 멈칫 섬유향수 라이트블루아쿠아 70ml, 24000]


https://eclipse.googlesource.com/eclipselink/eclipselink.runtime/+/2.5.0-M6/jpa/eclipselink.jpa.test/src/org/eclipse/persistence/testing/tests/jpa21/advanced/NamedStoredProcedureQueryTestSuite.java
*/