package com.myboard.learning2.entities.department;

import java.util.ArrayList;
import java.util.List;

import com.myboard.learning2.entities.employee.EmployeeTest7;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "DepartmentTest2")
@Table(name = "department_test2")
public class DepartmentTest2 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPT_ID")
    private Long deptId;

    @Column(name = "DEPT_NAME")
    private String name;
    
    @OneToMany(mappedBy = "dept", cascade = CascadeType.PERSIST)
    private List<EmployeeTest7> empList = new ArrayList<EmployeeTest7>();

    // getter / setter
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
    public List<EmployeeTest7> getEmpList() {
        return empList;
    }
    public void setEmpList(List<EmployeeTest7> empList) {
        this.empList = empList;
    }
}
