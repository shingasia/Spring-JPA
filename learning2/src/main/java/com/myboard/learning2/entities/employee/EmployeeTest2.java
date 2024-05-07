package com.myboard.learning2.entities.employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;

@Entity(name = "employeeTest2")
@Table(name = "employeeTest2")
@TableGenerator(
    name = "SEQ_GENERATOR",
    table = "board_sequences",
    pkColumnName = "SEQ_NAME",
    pkColumnValue = "employee2_seq",
    valueColumnName = "NEXT_VALUE",
    initialValue = 0,
    allocationSize = 1
)
public class EmployeeTest2 {
    
    @Id
    @Column(name = "emp_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SEQ_GENERATOR")
    private Long id;

    // Getter / Setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}

/*
실행되는 쿼리
Hibernate: select tbl.NEXT_VALUE from board_sequences tbl where tbl.SEQ_NAME=? for update
Hibernate: update board_sequences set NEXT_VALUE=?  where NEXT_VALUE=? and SEQ_NAME=?
Hibernate: insert into employee_test2 (emp_id) values (?)
*/


/*
 * @TableGenerator 어노테이션의 속성들
 * String name       : 하나 이상의 클래스에서 참조하여 ID 값의 생성기가 될 수 있는 제너레이터 이름. @GeneratedValue에서 테이블 제너레이터를 참조할 때 이 이름을 사용.
 * String table      : 생성된 ID 값을 저장하는 테이블 이름
 * String pkColumnName : PK(primary key) 칼럼 이름
 * String pkColumnValue : 해당 테이블에 다른 종류의 ID 값도 저장할 수 있으므로 어떤 테이블의 Generated value인지 구별하기 위한 제너레이터 테이블의 PK값
 * String valueColumnName : 생성된 마지막 ID 값을 저장하는 칼럼 이름
 * int initialValue  : 마지막으로 생성된 ID 값을 저장하는 칼럼의 초기값
 * int allocationSize : 생성기에서 ID 번호를 할당할 때 증가할 양. ✅ 기본값 : 50
 * String catalog : 테이블의 카탈로그
 * String schema : 테이블의 스키마
 * UniqueConstraint[] uniqueConstraints : 테이블에 배치할 unique 제약조건. 복합 고유 키(composite unique key)를 정의하기 위해 사용
 */