package com.myboard.learning2.dto;

public class EmployeeDTO1 {
    private Long deptId;
    private Long cntOfEmp;
    private Double avgSalary;


    // constructor
    public EmployeeDTO1(Long deptId, Long cntOfEmp, Double avgSalary) {
        this.deptId = deptId;
        this.cntOfEmp = cntOfEmp;
        this.avgSalary = avgSalary;
    }
    
    // getter / setter
    public Long getDeptId() {
        return deptId;
    }
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
    public Long getCntOfEmp() {
        return cntOfEmp;
    }
    public void setCntOfEmp(Long cntOfEmp) {
        this.cntOfEmp = cntOfEmp;
    }
    public Double getAvgSalary() {
        return avgSalary;
    }
    public void setAvgSalary(Double avgSalary) {
        this.avgSalary = avgSalary;
    }

    @Override
    public String toString() {
        return "EmployeeDTO1 [deptId=" + deptId + ", cntOfEmp=" + cntOfEmp + ", avgSalary=" + avgSalary + "]";
    }
    
}
