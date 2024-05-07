package com.myboard.learning2.entities.employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_test6")
public class EmployeeTest6 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_ID")
    private Long id;
    @Column(name = "EMP_NAME")
    private String name;
    
    @OneToOne(mappedBy = "employee")
    private EmployeeCardTest1 empCard;

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
    public EmployeeCardTest1 getEmpCard() {
        return empCard;
    }
    public void setEmpCard(EmployeeCardTest1 empCard) {
        this.empCard = empCard;
    }
}
