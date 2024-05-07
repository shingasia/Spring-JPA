package com.myboard.learning2.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.myboard.learning2.entities.department.DepartmentTest1;
import com.myboard.learning2.entities.employee.EmployeeCardTest1;
import com.myboard.learning2.entities.employee.EmployeeTest4;
import com.myboard.learning2.entities.employee.EmployeeTest5;
import com.myboard.learning2.entities.employee.EmployeeTest6;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;

@Service("employeeServiceTest2")
public class EmployeeServiceTest2 {
    
    // @PersistenceContext
    // private EntityManager em;
    
    @PersistenceUnit
    private EntityManagerFactory emf;

    public void test1() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try{
            tx = em.getTransaction();
            tx.begin();
            
            // 부서 등록
            DepartmentTest1 department = new DepartmentTest1();
            department.setName("개발부");
            em.persist(department);

            // 직원 등록
            EmployeeTest5 emp1 = new EmployeeTest5();
            emp1.setName("둘리");
            emp1.setDept(department);
            em.persist(emp1);
            
            // 직원 등록
            EmployeeTest5 emp2 = new EmployeeTest5();
            emp2.setName("도우너");
            emp2.setDept(department);
            em.persist(emp2);

            tx.commit();
        }catch(Exception e){
            System.out.println("예외발생....");
            e.printStackTrace();
            tx.rollback();
        }finally{
            em.close();
        }
        
    }

    public void test2() {
        EntityManager em = emf.createEntityManager();
        EmployeeTest5 emp1 = em.find(EmployeeTest5.class, 2L);
        System.out.println(emp1.getName() + "의 부서 : "+emp1.getDept().getName());
    }

    public void test3() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try{
            tx = em.getTransaction();
            tx.begin();
            
            // 부서에 대한 참조 제거
            EmployeeTest5 emp1 = em.find(EmployeeTest5.class, 1L);
            emp1.setDept(null);
            EmployeeTest5 emp2 = em.find(EmployeeTest5.class, 2L);
            emp1.setDept(null);


            // 부서 삭제
            DepartmentTest1 department = em.find(DepartmentTest1.class, 1L);
            em.remove(department);

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
        }
    }

    public void test4() {
        EntityManager em = emf.createEntityManager();
        // 부서에 속한 직원 조회
        DepartmentTest1 department = em.find(DepartmentTest1.class, 1L);
        for(EmployeeTest5 employee : department.getEmployeeList()) {
            System.out.println(employee.getName() + "(" + employee.getDept().getName() + ")");
        }
    }

    public void test5() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try{
            tx = em.getTransaction();
            tx.begin();


            EmployeeTest5 emp1 = new EmployeeTest5();
            emp1.setName("짱구");

            // 부서 객체만 만들고 persist는 하지 않음 => 직원 등록할 때 cascasde에 의해 등록됨
            DepartmentTest1 dept1 = new DepartmentTest1();
            dept1.setName("떡잎방범대");
            // em.persist(dept1);

            // 직원 등록
            emp1.setDept(dept1);
            emp1.getDept().getEmployeeList().add(emp1);
            em.persist(emp1);

            tx.commit();
        }catch(Exception e){
            System.out.println("예외발생....");
            e.printStackTrace();
            tx.rollback();
        }finally{
            em.close();
        }
    }

    public void test6() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try{
            tx = em.getTransaction();
            tx.begin();
            
            // 직원 객체만 만들고 persist는 하지 않음 => 부서 등록할 때 cascade에 의해 등록됨
            EmployeeTest5 emp1 = new EmployeeTest5();
            emp1.setName("짱구");
            
            // 부서 등록
            DepartmentTest1 dept1 = new DepartmentTest1();
            dept1.setName("떡잎방범대");
            
            dept1.getEmployeeList().add(emp1);
            emp1.setDept(dept1);

            em.persist(dept1);
            
            tx.commit();
        }catch(Exception e){
            System.out.println("예외발생....");
            e.printStackTrace();
            tx.rollback();
        }finally{
            em.close();
        }
    }

    public void test7() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            
            EmployeeCardTest1 card = new EmployeeCardTest1();
            card.setExpireDate(new Date());

            EmployeeTest6 emp = new EmployeeTest6();
            card.setEmployee(emp);
            emp.setName("코난");
            emp.setEmpCard(card);

            em.persist(card); // 사원증 등록
            em.persist(emp);  // 직원 등록

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
🔴 영속 컨테이너는 관리 상태의 엔티티만 인지할 수 있기 때문에 당연히 연관관계에 참여하는 모든 엔티티는 관리 상태로 존재해야 한다.
    위 예제에서 직원 등록 전에 부서 엔티티를 먼저 관리 상태로 만들었다. 그래야 직원 객체를 관리 상태로 만들 때 부서 엔티티를 이용할 수 있다.
🔴 @JoinColumn(name = "DEPT_ID") 설정으로 다음과 같이 'dept_id' 칼럼이 추가되고 foreign key가 만들어졌다.

CREATE TABLE `employee_test5` (
  `dept_id` bigint(20) DEFAULT NULL,
  `emp_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `FKdwgsmiry94s8aayc1f6wxuwlw` (`dept_id`),
  CONSTRAINT `FKdwgsmiry94s8aayc1f6wxuwlw` FOREIGN KEY (`dept_id`) REFERENCES `department_test1` (`dept_id`)
)
*/

/*
test2() 메서드 실행 결과
==================================================================================================================================
🔴 @ManyToOne 은 기본적으로 아래와 같이 LEFT OUTER JOIN 쿼리를 생성한다. @ManyToOne(optional = false) 로 변경하면 INNER JOIN 쿼리를 생성한다.
select
    et1_0.emp_id, d1_0.dept_id, d1_0.name, et1_0.emp_name
from      myboard.employee_test5 et1_0
left join myboard.department_test1 d1_0
on        d1_0.dept_id = et1_0.dept_id
where     et1_0.emp_id=?

🔴 @ManyToOne(fetch = FetchType.LAZY) 로 설정하면 직원을 조회할 때 부서 정보를 조인하여 가져오지 않고, 부서 관련 데이터를 사용하는 시점에 SELECT 쿼리를 한 번 더 생성한다.
1) em.find 로 직원 조회
select et1_0.emp_id,et1_0.dept_id,et1_0.emp_name from myboard.employee_test5 et1_0 where et1_0.emp_id=?
2) emp1.getDept().getName()으로 부서 정보 사용
select dt1_0.dept_id,dt1_0.name from myboard.department_test1 dt1_0 where dt1_0.dept_id=?
*/

/*
test3() 메서드 실행 결과
==================================================================================================================================
🔴 foreign key 제약조건 위배 때문에 부서 엔티티를 먼저 삭제할 수 없다. 따라서 직원 엔티티를 모두 조회해서 dept를 null로 설정하여 부서에 대한 참조를 끊고, @ManyToOne(optional = true)로 null을 다시 허용하도록 수정한다.
    실제로 직원 테이블에서 foreign key 칼럼을 NULL 또는 부서 테이블의 primary key에 있는 값으로 UPDATE 하는 것은 허용되지만 다른 값으로 UPDATE 하면 foreign key 제약조건 위배로 에러 발생.
    또 반대로 부서 테이블도 primary key 칼럼 값이 직원 테이블의 foreign key 칼럼에 의해 참조되고 있는 값이면 foreign key 제약조건 위배로 cascade 없으면 [수정/삭제] 불가능하다.
🔴 엔티티를 삭제할 때는 먼저 연관관계에 있는 엔티티들과의 관계를 제거해야 한다.
*/

/*
test4() 메서드 실행 결과
==================================================================================================================================
🔴 비즈니스 특성상 직원을 조회 했을 때 직원이 속한 부서 정보를 사용하고 또 부서를 조회 했을 때 해당 부서에 속해 있는 직원 정보를 사용하는 경우 관계를 양방향(Bidirection)으로 매핑해야 된다.
    그래서 DepartmentTest1, EmployeeTest5 둘 다 서로를 참조하는 변수가 있고, 참조 변수 중 하나를 연관관계의 소유자로 지정하여 foreign key와 매핑한다.
🔴 foreign key와 매핑된 참조 변수를 연관관계 소유자(Owner)라고 한다. 부서 엔티티에서 mappedBy 속성은 관계를 소유하는 직원 엔터티의 속성 이름을 지정
🔴 양방향에서는 연관관계의 소유자만이 foreign key에 대해 관리(등록, 수정, 삭제)할 수 있고, 반대쪽은 오로지 읽기(조회)만 가능하다.
*/

/*
test5() 메서드 실행 결과
==================================================================================================================================
🔴 직원 객체에 @ManyToOne(cascade = CascadeType.PERSIST) 이렇게 cascade 속성이 없다면 해당 메서드는 에러가 발생한다. 부서(FK로 사용되는 객체)를 먼저 저장하지 않았기 때문이다.
*/

/*
test6() 메서드 실행 결과
==================================================================================================================================
🔴 부서 객체에 @OneToMany(cascade = CascadeType.PERSIST) 속성이 없다면 해당 메서드는 부서만 등록되고 직원은 persist 하지 않아서 등록되지 않는다.
*/

/*
test7() 메서드 실행 결과
==================================================================================================================================
🔴 @MapsId 를 생략하면 PK, FK가 분리되어 테이블이 만들어진다. @Id 어노테이션이 지정된 칼럼이 PK가 된다.
CREATE TABLE `employee_card_test1` (
  `card_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `emp_id` bigint(20) DEFAULT NULL,
  `expire_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`card_id`),
  UNIQUE KEY `UK_dcwgjewxgiahlhxl93c0jtmwj` (`emp_id`),
  CONSTRAINT `FKolhrn1biy5st3t57okwj717ae` FOREIGN KEY (`emp_id`) REFERENCES `employee_test6` (`emp_id`)
)

🔴 @MapsId 어노테이션을 적용하면 @MapsId를 지정한 참조 변수가 foreign key이면서 동시에 primary key가 된다.
CREATE TABLE `employee_card_test1` (
  `emp_id` bigint(20) NOT NULL,
  `expire_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  CONSTRAINT `FKolhrn1biy5st3t57okwj717ae` FOREIGN KEY (`emp_id`) REFERENCES `employee_test6` (`emp_id`)
)


*/