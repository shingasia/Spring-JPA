package com.myboard.learning2.entities.employee;

import com.myboard.learning2.entities.department.DepartmentTest1;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(catalog = "myboard", schema = "myboard", name = "employee_test5")
public class EmployeeTest5 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;
    @Column(name = "emp_name")
    private String name;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "DEPT_ID")
    private DepartmentTest1 dept;

    // Getter / Setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public DepartmentTest1 getDept() {
        return dept;
    }
    public void setDept(DepartmentTest1 dept) {
        this.dept = dept;
    }
}
/*
 * @JoinColumn 어노테이션의 속성들
 * String name                 : foreign key 칼럼 이름. 생략하면 ["엔티티 또는 embeddable 클래스를 참조하는 변수(property or field) 이름" + "_" + "참조된 테이블의 primary key 이름"] 이다.
 * String referencedColumnName : foreign key 칼럼에 의해 참조되는 칼럼의 이름. 생략하면 참조된 테이블의 primary key 칼럼 이름과 같다.
 */

/*
 * @ManyToOne 어노테이션의 속성들
 * boolean optional            : 연관된 엔티티가 반드시 있어야 하는지의 여부를 결정. => 조회할 때 INNER JOIN, LEFT OUTER JOIN을 결정한다.     ✅ 기본값 : true
 * FetchType fetch             : 글로벌 페치 전략을 설정. EAGER는 연관 엔티티를 동시에 조회하며(JOIN 쿼리가 발생), LAZY는 연관 엔티티를 실제 사용할 때 조회한다.    ✅ 기본값 : FetchType.EAGER
 * CascadeType[] cascade       : 영속성 전이 기능을 설정. 연관 엔티티를 같이 저장하거나 삭제할 때 사용한다.     ✅ 기본값 : 사용하지 않음
 */

/*
 * @OneToMany 어노테이션의 속성들
 * String mappedBy             : 양방향 관계에서는 필수 속성이다. 연관관계의 소유자(Owner)를 지정한다.
 * FetchType fetch             : 글로벌 페치 전략을 설정. EAGER는 연관 엔티티를 동시에 조회하며(JOIN 쿼리가 발생), LAZY는 연관 엔티티를 실제 사용할 때 조회한다.    ✅ 기본값 : FetchType.LAZY
 * CascadeType[] cascade       : 영속성 전이 기능을 설정. 연관 엔티티를 같이 저장하거나 삭제할 때 사용한다.     ✅ 기본값 : 사용하지 않음
 * boolean orphanRemoval       :       ✅ 기본값 : false
 */

/*
 * CascadeType 종류
 * PERSIST    : Entity를 영속 객체로 추가할 때 연관된 Entity도 함께 영속 객체로 추가한다.
 * REMOVE     : Entity를 삭제할 때 연관된 Entity도 함께 삭제한다.
 * DETACH     : Entity를 영속 컨텍스트에서 분리할 때 연관된 Entity도 함께 분리 상태로 만든다.
 * REFRESH    : Entity를 데이터베이스에서 다시 읽어올 때 연관된 Entity도 함께 다시 읽어온다.
 * MERGE      : Entity를 준영속 상태에서 다시 영속 상태로 변경할 때 연관된 Entity도 함께 변경한다.
 * ALL        : 모든 상태 변화에 대해 연관된 Entity도 함께 적용한다.
 */

