package com.myboard.learning2.entities.employee;

import java.time.LocalDateTime;

import com.myboard.learning2.entities.department.DepartmentTest2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "EmployeeTest7")
@Table(name = "employee_test7")
public class EmployeeTest7 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_ID")
    private Long id;
    @Column(name = "EMP_NAME")
    private String name;
    @Column(name = "START_DATE")
    private LocalDateTime startDate;
    @Column(name = "END_DATE")
    private LocalDateTime endDate;
    @Column(name = "EMP_SALARY")
    private Double empSalary;
    
    @ManyToOne
    @JoinColumn(name = "DEPT_ID")
    private DepartmentTest2 dept;
    
    // getter / setter
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
    public LocalDateTime getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    public LocalDateTime getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
    public Double getEmpSalary() {
        return empSalary;
    }
    public void setEmpSalary(Double empSalary) {
        this.empSalary = empSalary;
    }
    public DepartmentTest2 getDept() {
        return dept;
    }
    public void setDept(DepartmentTest2 dept) {
        this.dept = dept;
        if(dept != null) {
            dept.getEmpList().add(this);
        }
    }
    @Override
    public String toString() {
        return "EmployeeTest7 [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
                + ", empSalary=" + empSalary + ", dept=" + dept + "]";
    }
    
}
