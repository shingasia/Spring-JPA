package com.myboard.learning2.entities.employee;

import java.util.Date;

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
@Table(name = "employee_card_test1")
public class EmployeeCardTest1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARD_ID")
    private Long cardId;

    @Column(name = "EXPIRE_DATE")
    private Date expireDate;

    @MapsId
    @OneToOne
    @JoinColumn(name = "EMP_ID")
    private EmployeeTest6 employee;
    
    // Getter / Setter
    public Long getCardId() {
        return cardId;
    }
    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
    public Date getExpireDate() {
        return expireDate;
    }
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
    public EmployeeTest6 getEmployee() {
        return employee;
    }
    public void setEmployee(EmployeeTest6 employee) {
        this.employee = employee;
    }
}
/*
 * @MapsId 어노테이션 설명
 * @JoinColumn으로 매핑한 foreign key 칼럼을 primary key 칼럼으로도 사용하겠다는 의미이다.
 */


// https://www.objectdb.com/api/java/jpa/Table
// https://getemoji.com/