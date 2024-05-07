package com.myboard.learning2.services;

import org.springframework.stereotype.Service;

import com.myboard.learning2.entities.employee.EmployeeTest4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;

@Service("employeeServiceTest1")
public class EmployeeServiceTest1 {
    
    // @PersistenceContext
    // private EntityManager em;
    
    @PersistenceUnit
    private EntityManagerFactory emf;

    public void test1() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try{
            // tx = em.getTransaction();
            EmployeeTest4.EmployeeTest4_PK emp4pk = new EmployeeTest4.EmployeeTest4_PK("a", "b", "c");
            EmployeeTest4 emp4 = new EmployeeTest4();
            emp4.setEmpTestPK(emp4pk);
            emp4.setFullname("abc");
            System.out.println("문제없음..."+emf.getClass().getName());
            
            // tx.begin();
            em.persist(emp4);
            // tx.commit();
        }catch(Exception e){
            System.out.println("예외발생....");
            e.printStackTrace();
            // tx.rollback();
        }finally{
            em.close();
        }
        
    }
    
}

/*
🔴 트랜젝션 관련 코드를 모두 주석처리 후 실행하면 persist()를 호출해도 INSERT 쿼리가 실행되지 않는다. 테이블에 실제 INSERT가 발생되기 위해서는 persist 메서드 호출이 반드시 트랜젝션 안에서 이루어져야 한다.
🔴 JPA는 등록/수정/삭제 작업에 대해서는 반드시 트랜젝션 안에서 실행되어야 한다.
🔴 영속 컨텍스트(Persistence Context)는 모든 엔티티를 데이터베이스에서 가져오거나 데이터베이스에 저장하는 첫 번째 수준 캐시(cache)입니다.
🔴 영속 컨텍스트는 관리되는 엔티티에 대한 모든 변경 사항을 추적합니다. 트랜젝션 실행 중에 변경된 사항이 있으면 엔티티는 dirty로 표시됩니다. 트랜젝션이 완료되면 이러한 변경 사항이 데이터베이스로 플러시(flush)됩니다.
🔴 기본 영속 컨텍스트 유형은 PersistenceContextType.TRANSACTION 입니다. EntityManager에게 트랜잭션 영속 컨텍스트를 사용하라고 지시하려면 간단히 @PersistenceContext 어노테이션을 적용하면 됩니다.
*/

/*
⭐️ EntityManager 라이프사이클에 대한 설명
EntityManager는 엔티티 인스턴스의 생명주기(lifecycle)를 관리하는 API입니다.
EntityManager 객체는 영속성 유닛(persistence unit)으로 정의된 엔티티 집합을 관리합니다.
각각의 EntityManager 객체는 영속 컨텍스트와 연결됩니다. 영속 컨텍스트는 특정 엔티티 인스턴스가 생성(created), 유지(persisted), 삭제(removed) 되는 범위를 정의합니다.
영속 컨텍스트가 닫히면(closed), 관리되는 모든 엔티티 객체는 영속 컨텍스트 및 EntityManager에서 분리되어(detached) 더 이상 관리되지 않습니다.
엔티티 객체가 영속 컨텍스트에서 분리되면 EntityManager가 관리할 수 없으며 해당 엔티티 객체에 대한 모든 변경 사항은 데이터베이스와 동기화되지 않습니다.

트랜젝션 범위(transaction scope) 또는 확장 영속 컨텍스트(extended persistence context)가 끝나면 엔티티 객체는 관리되지 않고 분리(detached)됩니다.
이 사실의 중요한 결과는 분리된 엔티티를 직렬화하여 네트워크를 통해 클라이언트로 보낼 수 있다는 것입니다.
클라이언트는 이러한 직렬화된 객체를 원격으로 변경하고 서버로 다시 보내 데이터베이스와 다시 병합 및 동기화할 수 있습니다.

🔴 Spring 프레임워크에서 Bean의 기본 스코프는 singleton이므로 @PersistenceContext 어노테이션으로 Container-managed EntityManager 객체를 Bean에 주입하면 현재 해당 EntityManager 객체를 singleton으로 활용한다는 의미입니다. 이것은 버그입니다.
그러나 https://www.baeldung.com/hibernate-entitymanager 에 따르면, 단순한 EntityManager 대신 특수 프록시를 주입한다는 것을 발견했습니다.
결과적으로 IoC 컨테이너가 각 EntityManager가 하나의 스레드로 제한되도록 보장한다는 것입니다.

⭐️ 영속성 유닛(Persistence Unit)
애플리케이션의 EntityManager들이 관리하는 모든 엔티티 테이블을 지정합니다.
각 영속성 유닛에는 단일 데이터베이스에 저장된 데이터를 나타내는 모든 클래스가 포함되어 있습니다.

일반적으로 엔티티 테이블 들의 이름을 영속성 유닛으로 제공하려면, EntityManagerFactory에서 구성할 때 해당 이름을 채워야 합니다.

⭐️ EntityManager 의 두 가지 타입
1. Container-managed
EntityManager 객체의 생명주기(lifecycle)를 Spring의 IoC 컨테이너가 담당합니다.
트랜젝션 시작, 트랜젝션 커밋, 트랜젝션 롤백 등 트랜젝션의 흐름과 EntityManager의 할당, 할당 해제를 IoC 컨테이너가 관리합니다.
애플리케이션에 여러 개의 EntityManager가 있다고 가정하면 EntityManager의 각 작업은 엔티티의 A 상태에서 B 상태로 변경합니다.
하지만 우리는 영속 컨텍스트가 애플리케이션의 엔티티를 관리하는 장소라는 것을 알고 있습니다.
그럼 한가지 궁금증이 "해당 엔티티의 B상태가 다른 EntityManager에서 사용되는 동일한 엔티티에 반영될까?"
답은 "가능하다." 영속 컨텍스트는 현재 JTA 트랜젝션과 함께 자동으로 전파(propagated)됩니다.
Container-managed EntityManager는 JTA 트랜젝션을 사용해야 합니다.
영속 컨텍스트가 현재 JTA 트랜젝션을 통해 자동으로 전파되고(propagated), 동일한 영속성 유닛(persistence unit)에 매핑된 EntityManager 참조가 JTA 내에서 동일한 영속 컨텍스트에 대한 액세스를 제공하기 때문입니다.

2. Application-managed
설명생략.....


⭐️ 영속 컨텍스트의 두 가지 타입
1. Transaction-scoped persistence context
새 트랜젝션이 시작될 때마다 EntityManager(Container-managed, Application-managed 둘 다)에 대해 새로운 영속 컨텍스트가 생성됩니다.
이는 영속 컨텍스트의 수명이 트랜젝션과 함께 수행됨을 의미합니다.
트랜젝션이 생성될 때 영속 컨텍스트가 생성되며, 마찬가지로 트랜젝션이 commit 또는 rollback 되면 영속 컨텍스트가 해제됩니다.


2. Extended persistence context
영속 컨텍스트의 수명이 여러개의 트렌젝션에 걸쳐 있습니다.
엔티티가 트랜젝션 없이 persist 된 경우 데이터베이스에 플러시되지 않고 확장 영속 컨텍스트에만 저장됩니다.
확장 영속 컨텍스트는 동일한 식별자를 사용하여 엔티티를 persist 하는 것을 허용하지 않습니다.

⭐️ 
*/


// ⭐️ EntityManager에 접근하는 여러가지 방법 => https://javatute.com/jpa/how-to-get-jpa-entitymanager-in-spring-boot/