package com.myboard.learning2.entities.employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;

@Entity(name = "employeeTest3")
@Table(name = "employeeTest3")
@TableGenerator(
    name = "SEQ_GENERATOR",
    table = "board_sequences",
    pkColumnName = "SEQ_NAME",
    pkColumnValue = "employee3_seq",
    valueColumnName = "NEXT_VALUE",
    initialValue = 0,
    allocationSize = 1
)
public class EmployeeTest3 {
    
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