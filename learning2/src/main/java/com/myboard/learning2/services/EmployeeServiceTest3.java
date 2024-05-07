package com.myboard.learning2.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.myboard.learning2.dto.EmployeeDTO1;
import com.myboard.learning2.entities.department.DepartmentTest1;
import com.myboard.learning2.entities.department.DepartmentTest2;
import com.myboard.learning2.entities.employee.EmployeeTest5;
import com.myboard.learning2.entities.employee.EmployeeTest7;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;

@Service("employeeServiceTest3")
public class EmployeeServiceTest3 {
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
            
            // 부서 2개
            DepartmentTest2 dept1 = new DepartmentTest2();
            dept1.setName("부서1");

            DepartmentTest2 dept2 = new DepartmentTest2();
            dept2.setName("부서2");

            // 부서 없는 직원 2명
            EmployeeTest7 emp1 = new EmployeeTest7();
            emp1.setName("알바1");
            emp1.setStartDate(LocalDateTime.now());
            emp1.setEndDate(LocalDateTime.of(9999, 12, 31, 23, 59, 59, 999999));
            emp1.setEmpSalary(210 * 10000.0);

            EmployeeTest7 emp2 = new EmployeeTest7();
            emp2.setName("알바2");
            emp2.setStartDate(LocalDateTime.now());
            emp2.setEndDate(LocalDateTime.of(9999, 12, 31, 23, 59, 59, 999999));
            emp2.setEmpSalary(200 * 10000.0);
            
            // 부서 있는 직원 4명
            EmployeeTest7 emp3 = new EmployeeTest7();
            emp3.setName("정직원1");
            emp3.setStartDate(LocalDateTime.now());
            emp3.setEndDate(LocalDateTime.parse("9999-12-31 23:59:59.999999", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")));
            emp3.setEmpSalary(250 * 10000.0);
            emp3.setDept(dept1); // 부서 등록

            EmployeeTest7 emp4 = new EmployeeTest7();
            emp4.setName("정직원2");
            emp4.setStartDate(LocalDateTime.now());
            emp4.setEndDate(LocalDateTime.parse("9999-12-31 23:59:59.999999", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")));
            emp4.setEmpSalary(250 * 10000.0);
            emp4.setDept(dept2);

            EmployeeTest7 emp5 = new EmployeeTest7();
            emp5.setName("정직원3");
            emp5.setStartDate(LocalDateTime.now());
            emp5.setEndDate(LocalDateTime.parse("9999-12-31 23:59:59.999999", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")));
            emp5.setEmpSalary(265 * 10000.0);
            emp5.setDept(dept2);

            EmployeeTest7 emp6 = new EmployeeTest7();
            emp6.setName("정직원4");
            emp6.setStartDate(LocalDateTime.now());
            emp6.setEndDate(LocalDateTime.parse("9999-12-31 23:59:59.999999", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")));
            emp6.setEmpSalary(290 * 10000.0);
            emp6.setDept(dept2);

            dept1.getEmpList().add(emp1);
            dept1.getEmpList().add(emp2);
            dept1.getEmpList().add(emp3);

            dept2.getEmpList().add(emp4);
            dept2.getEmpList().add(emp5);
            dept2.getEmpList().add(emp6);
            em.persist(dept1); // 부서1
            em.persist(dept2); // 부서2

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

        // JPQL1
        TypedQuery<EmployeeTest7> query1 = em.createQuery("SELECT e FROM EmployeeTest7 AS e", EmployeeTest7.class);
        List<EmployeeTest7> empList1 = query1.getResultList();

        List<Object[]> empList2 = em.createQuery(
                          " SELECT A, (CASE WHEN B.name IS NULL THEN '인턴' ELSE B.name END) AS deptName "
                        + " FROM   EmployeeTest7 AS A "
                        + " LEFT OUTER JOIN "
                        + "        DepartmentTest2 AS B "
                        + " ON     A.dept = B ", Object[].class)
                        // + " ON     A.dept.deptId = B.deptId ", Object[].class)
                        .getResultList();

        // for(EmployeeTest7 emp : empList1) {
        //     System.out.println(emp);
        // }

        for(Object[] emp : empList2) {
            System.out.println("사원정보 : "+emp[0].toString()+", 부서이름 : "+emp[1]);
        }
    }

    public void test3() {
        EntityManager em = emf.createEntityManager();
        EmployeeDTO1 summary = em.createQuery(
                          // " SELECT A.dept.deptId, COUNT(A) AS CNT, AVG(A.empSalary) AS avg_salary "
                          " SELECT new com.myboard.learning2.dto.EmployeeDTO1(A.dept.deptId, COUNT(A), AVG(A.empSalary)) "
                        + " FROM   EmployeeTest7 AS A "
                        + " WHERE  A.dept.deptId = :deptId "
                        + " GROUP BY A.dept.deptId "
                        + " ORDER BY A.dept.deptId ASC ", EmployeeDTO1.class)
                        .setParameter("deptId", 2L)
                        .getSingleResult();
        
        System.out.println(summary);
    }

    public void test4() {
        EntityManager em = emf.createEntityManager();
        // StoredProcedureQuery query1 = em.createStoredProcedureQuery("P_JPQL_EX1", Object[].class, Object[].class);
        StoredProcedureQuery query1 = em.createStoredProcedureQuery("P_JPQL_EX1");
        query1.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query1.setParameter(1, null);
        query1.execute();
        
        List r1 = query1.getResultList();
        List r2 = null;
        if(query1.hasMoreResults()) {
            r2 = query1.getResultList();
        }

        System.out.println("결과집합1 ----------------------------");
        for (Object o : r1) {
            System.out.println(Arrays.toString((Object[]) o));
        }
        if(r1 != null) {
            System.out.println("결과집합2 ----------------------------");
            for (Object o : r2) {
                System.out.println(Arrays.toString((Object[]) o));
            }
        }
        System.out.println("더 있나? "+query1.hasMoreResults());
    }

    public void test5() {
        EntityManager em = emf.createEntityManager();
        Object[] summary = em.createQuery(
                        " SELECT CURRENT_DATE, CURRENT_TIME, CURRENT_TIMESTAMP ", Object[].class)
                        .getSingleResult();
        
        System.out.println(Arrays.toString(summary));
    }
}
/*
🟣 test1() 로 샘플 데이터를 넣어 두고 테스트
test1() 메서드 실행 결과
==================================================================================================================================
🔴 부서 클래스에서 CascadeType.PERSIST 를 적용해서 부서 객체를 등록하면 연관관계에 있는 직원 들도 같이 등록된다. => 직원 테이블에 INSERT 쿼리가 나간다.
    그러나 연관관계의 주인이 직원이고, setDept()로 부서에 대한 정보를 넣지 않은 직원 엔티티는 NULL로 INSERT 된다.

test2() 메서드 실행 결과
==================================================================================================================================
🔴 JPQL은 검색 대상이 데이터베이스의 테이블이 아닌 엔티티 객체다. 따라서 엔티티 이름(@Entity의 name 속성으로 지정한 이름, 지정하지 않으면 클래스 이름)을 사용.
🔴 쿼리 결과가 특정 엔티티와 매칭되지 않아서 List<Object[]> 타입으로 받았다.

실행한 조인 쿼리의 출력 결과는 아래와 같다.

사원정보 : EmployeeTest7 [id=1, name=정직원1, startDate=2024-04-28T16:34:33.806305, endDate=9999-12-31T23:59:59.999999, empSalary=2500000.0, dept=com.myboard.learning2.entities.department.DepartmentTest2@6cb31c94], 부서이름 : 부서1
사원정보 : EmployeeTest7 [id=2, name=알바1, startDate=2024-04-28T16:34:33.806305, endDate=9999-12-31T23:59:59.000999, empSalary=2100000.0, dept=null], 부서이름 : 인턴
사원정보 : EmployeeTest7 [id=3, name=알바2, startDate=2024-04-28T16:34:33.806305, endDate=9999-12-31T23:59:59.000999, empSalary=2000000.0, dept=null], 부서이름 : 인턴
사원정보 : EmployeeTest7 [id=4, name=정직원2, startDate=2024-04-28T16:34:33.808299, endDate=9999-12-31T23:59:59.999999, empSalary=2500000.0, dept=com.myboard.learning2.entities.department.DepartmentTest2@2bf65221], 부서이름 : 부서2
사원정보 : EmployeeTest7 [id=5, name=정직원3, startDate=2024-04-28T16:34:33.808299, endDate=9999-12-31T23:59:59.999999, empSalary=2500000.0, dept=com.myboard.learning2.entities.department.DepartmentTest2@2bf65221], 부서이름 : 부서2
사원정보 : EmployeeTest7 [id=6, name=정직원4, startDate=2024-04-28T16:34:33.808299, endDate=9999-12-31T23:59:59.999999, empSalary=2500000.0, dept=com.myboard.learning2.entities.department.DepartmentTest2@2bf65221], 부서이름 : 부서2

test3() 메서드 실행 결과
==================================================================================================================================
🔴 JPQL 실행 결과를 DTO로 매핑하는 예제이다. 주의사항은 여기서 new 와 함께 DTO 클래스의 이름이 반드시 패키지 경로가 포함된 전체 경로를 지정해야 한다는 것이다.
🔴 JPQL 에서 COUNT()와 같은 집계 함수는 리턴 타입이 정해져있다. COUNT는 java.lang.Long, AVG는 java.lang.Double, 나머지 MAX, MIN, SUM은 인자로 전달된 데이터 타입니다.
🔴 실행 결과가 1건이므로 getSingleResult() 사용

test4() 메서드 실행 결과
==================================================================================================================================
🔴 DTO는 Entity가 아니기 때문에 MappingException이 발생한다. 따라서 Object[] 로 프로시저의 결과값 여러개를 받아오는 예제이다. 출력 결과는 아래와 같다.
🔴 프로시저는 com/myboard/learning2/sql 폴더 참고

결과집합1 ----------------------------
[1, 정직원1, 2024-04-28 22:53:47.393766, 9999-12-31 23:59:59.999999, 2500000.0, 부서1]
[2, 알바1, 2024-04-28 22:53:47.393766, 9999-12-31 23:59:59.000999, 2100000.0, 인턴]
[3, 알바2, 2024-04-28 22:53:47.393766, 9999-12-31 23:59:59.000999, 2000000.0, 인턴]
[4, 정직원2, 2024-04-28 22:53:47.396756, 9999-12-31 23:59:59.999999, 2500000.0, 부서2]
[5, 정직원3, 2024-04-28 22:53:47.396756, 9999-12-31 23:59:59.999999, 2650000.0, 부서2]
[6, 정직원4, 2024-04-28 22:53:47.396756, 9999-12-31 23:59:59.999999, 2900000.0, 부서2]
결과집합2 ----------------------------
[1, 부서1, 1]
[2, 부서2, 3]
[null, 인턴, 2]

test5() 메서드 실행 결과
==================================================================================================================================
[2024-04-29, 12:47:58, 2024-04-29 12:47:58.24764]

JPQL이 제공하는 날짜 함수이다.
CURRENT_DATE : 현재 날짜
CURRENT_TIME : 현재 시간
CURRENT_TIMESTAMP : 현재 날짜와 시간
*/