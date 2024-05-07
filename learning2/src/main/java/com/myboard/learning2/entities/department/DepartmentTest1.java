package com.myboard.learning2.entities.department;

import java.util.ArrayList;
import java.util.List;

import com.myboard.learning2.entities.employee.EmployeeTest5;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(catalog = "myboard", schema = "myboard", name = "department_test1")
public class DepartmentTest1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPT_ID")
    private Long deptId;
    // @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "dept", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<EmployeeTest5> employeeList = new ArrayList<EmployeeTest5>();

    // Getter / Setter
    public Long getDeptId() {
        return deptId;
    }
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<EmployeeTest5> getEmployeeList() {
        return employeeList;
    }
    public void setEmployeeList(List<EmployeeTest5> employeeList) {
        this.employeeList = employeeList;
    }
    
}