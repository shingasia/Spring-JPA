package com.myboard.learning2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myboard.learning2.entities.employee.EmployeeTest2;


@Repository
public interface EmployeeRepository2 extends JpaRepository<EmployeeTest2, Long> {
    
    // @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    // EntityManager em;
}
