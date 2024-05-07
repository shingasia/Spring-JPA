package com.myboard.learning2.entities.employee;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity(name = "employeeTest4")
@Table(name = "employeeTest4")
public class EmployeeTest4 {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Long id;
    
    @Id
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name1", column = @Column(name = "first_name")),
        @AttributeOverride(name = "name2", column = @Column(name = "second_name")),
        @AttributeOverride(name = "name3", column = @Column(name = "third_name")),
    })
    private EmployeeTest4_PK empTestPK;
    
    // @Id
    private String fullname;
    
    @Embeddable
    public static class EmployeeTest4_PK {
        String name1;
        String name2;
        String name3;

        // 생성자1
        EmployeeTest4_PK(){}
        // 생성자2
        public EmployeeTest4_PK(String name1, String name2, String name3) {
            this.name1 = name1;
            this.name2 = name2;
            this.name3 = name3;
        }


        // Getter / Setter
        public String getName1() {
            return name1;
        }
        public void setName1(String name1) {
            this.name1 = name1;
        }
        public String getName2() {
            return name2;
        }
        public void setName2(String name2) {
            this.name2 = name2;
        }
        public String getName3() {
            return name3;
        }
        public void setName3(String name3) {
            this.name3 = name3;
        }
    }

    // Getter / Setter
    public EmployeeTest4_PK getEmpTestPK() {
        return empTestPK;
    }
    public void setEmpTestPK(EmployeeTest4_PK empTestPK) {
        this.empTestPK = empTestPK;
    }
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
}

/*
@Embeddable
엔티티에는 엔티티가 아닌 다른 클래스에 대한 참조가 있을 수 있습니다. 이러한 비엔티티(non-entity) 클래스를 embeddable 클래스 라고 합니다.
JPA는 클래스가 다른 엔티티에 삽입될 것임을 선언하기 위해 @Embeddable 어노테이션을 제공합니다.
embeddable 클래스의 모든 필드는 소유자(owner) 엔티티 테이블에 매핑됩니다.

@Embedded(@EmbeddedId 가 아님!) 어노테이션은 엔티티 클래스에 사용됩니다. 이 어노테이션은 embeddable 클래스를 참조하는 필드/속성에 배치됩니다.
엔티티에 속한 embeddable 클래스의 객체는 소유 엔티티에만 속하며 다른 영구 엔티티 간에 공유되어서는 안됩니다.
*/

/*
🔴 @id 어노테이션을 embeddable 클래스를 참조하는 필드에 지정해야 composite primary key 로 테이블이 만들어진다.
만약 @id 어노테이션을 다른 필드에 적용하고 @Embedded 어노테이션만 적용할 경우 그냥 일반 칼럼으로 테이블이 만들어진다.
🔴 @AttributeOverrides 어노테이션으로 칼럼 이름을 변경할 수 있다.
*/