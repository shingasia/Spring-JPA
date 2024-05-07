package com.myboard.learning2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myboard.learning2.entities.employee.EmployeeTest4;
import com.myboard.learning2.entities.employee.EmployeeTest4.EmployeeTest4_PK;


@Repository
public interface EmployeeRepository4 extends JpaRepository<EmployeeTest4, EmployeeTest4_PK> {
    
    // @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    // EntityManager em;
}
