package com.myboard.learning2.study.jpa;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.myboard.learning2.entities.employee.EmployeeTest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

@Component
public class EntityManagerEx1 {

    @PersistenceContext
    EntityManager em;

    public void method1() {
        // 엔티티 매니저 팩토리 생성
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit1");
        
        // 엔티티 매니저 생성
        // EntityManager em = emf.createEntityManager();

        // 엔티티 트렌잭션 생성
        EntityTransaction tx = em.getTransaction();
        
        try {
            EmployeeTest employee = new EmployeeTest();
            employee.setId(1L);
            employee.setName("둘리");
            employee.setMailId("gurum");
            employee.setStartDate(new Date());
            employee.setTitle("과장");
            employee.setDeptName("총무부");
            employee.setSalary(2500.00);
            employee.setCommissionPct(12.50);
            // 직원 등록
            em.persist(employee);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            em.close();
            // emf.close();
        }
        

    }
}
// Spring Boot 애플리케이션에서 속성을 읽을 때의 우선순위 => https://www.appsdeveloperblog.com/reading-application-properties-spring-boot/
