package com.myboard.learning2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myboard.learning2.entities.employee.EmployeeTest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeTest, Long> {
    
    // @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    // EntityManager em;
}
