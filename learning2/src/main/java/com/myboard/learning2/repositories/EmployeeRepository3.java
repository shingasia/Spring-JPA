package com.myboard.learning2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myboard.learning2.entities.employee.EmployeeTest3;


@Repository
public interface EmployeeRepository3 extends JpaRepository<EmployeeTest3, Long> {
    
    // @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    // EntityManager em;
}
