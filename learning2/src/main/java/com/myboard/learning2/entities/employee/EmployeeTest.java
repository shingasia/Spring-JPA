package com.myboard.learning2.entities.employee;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;

@Entity(name = "employeeTest")
@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"emp_id", "mailId"}),
        @UniqueConstraint(columnNames = {"testCol1", "testCol2", "testCol3", "testCol4"})
    }
)
public class EmployeeTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;
    @Column(name = "emp_name", length = 100, nullable = false, columnDefinition = " VARCHAR(200) DEFAULT '' ")
    private String name;
    private String mailId; // ddl-auto=create로 하면 칼럼명 'mail_id' 으로 테이블이 생성된다.

    @Column(name="START_DATE")
    private Date startDate; // ddl-auto=create로 하면 칼럼명 'start_date' 으로 테이블이 생성된다. datetime(6)

    private String title;

    @Column(name="DEPT_NAME")
    private String deptName;
    private Double salary;

    @Column(name="COMMISSION_PCT")
    private Double commissionPct; // ddl-auto=create로 하면 칼럼명 'commission_pct' 으로 테이블이 생성된다.

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime testCol1;
    private char testCol2;
    @Column(name = "testCol3", length = 90, nullable = false)
    private String testCol3;
    private String testCol4;
    @Column(name = "testCol5", insertable = false, columnDefinition = " NUMERIC(10, 4) DEFAULT 0 ")
    private double testCol5;

    // Getter / Setter
    
    public String getTestCol3() {
        return testCol3;
    }

    public void setTestCol3(String testCol3) {
        this.testCol3 = testCol3;
    }

    public String getTestCol4() {
        return testCol4;
    }

    public void setTestCol4(String testCol4) {
        this.testCol4 = testCol4;
    }
    
    public double getTestCol5() {
        return testCol5;
    }

    public void setTestCol5(double testCol5) {
        this.testCol5 = testCol5;
    }

    public LocalDateTime getTestCol1() {
        return testCol1;
    }

    public void setTestCol1(LocalDateTime testCol1) {
        this.testCol1 = testCol1;
    }

    public char getTestCol2() {
        return testCol2;
    }

    public void setTestCol2(char testCol2) {
        this.testCol2 = testCol2;
    }

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

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(Double commissionPct) {
        this.commissionPct = commissionPct;
    }

    
}
/*
 * 1. We must also ensure that the entity has a no-arg constructor and a primary key
 * 번역) 엔티티 클래스는 인수가 없는 생성자와 기본 키가 있어야 합니다.
 * 2. 기본적으로 엔티티 클래스에서 static 또는 transient로 선언되지 않은 필드는 영속 필드(Persistent Fields)입니다.
 * @Entity : entity 이름은 기본적으로 클래스 이름으로 설정됩니다. name 속성으로 이름을 변경할 수 있습니다.
 * 3. entity classes must not be declared final
 * 번역) 엔티티 클래스를 final로 선언하면 안됩니다.
 * 4. @Id 는 기본 키를 정의합니다. @Id를 사용하여 Java 기본 타입 또는 기본 래퍼(wrapper) 타입, String, Date, BigDecimal, BigInteger 유형 중 하나의 단일 속성에 매핑됩니다.
 * 5. 기본 키를 자동으로 생성하려면 @GeneratedValue를 사용합니다.
 *    AUTO(기본값), IDENTITY, SEQUENCE, TABLE 이렇게 4가지 제너레이션 타입을 사용할 수 있습니다.
 * 6. 대부분의 경우 데이터베이스 테이블 이름과 엔티티 이름은 동일하지 않습니다. 이러한 경우 @Table을 사용합니다.
 */

/*
 * @Column 어노테이션의 속성들
 * String name             : 매핑될 칼럼 이름을 지정한다.(생략 시 변수 이름과 동일한 칼럼 매핑)
 * boolean unique          : 칼럼이 unique key 인지 여부를 결정. 이는 테이블 레벨의 UniqueConstraint 어노테이션에 대한 shortcut이며 unique key 제약조건이 단일 칼럼에만 해당하는 경우에 유용합니다. ✅ 기본값 : false
 * boolean nullable        : 칼럼이 NULL을 허용하는지 여부입니다. ✅ 기본값 : true
 * boolean insertable      : 영속성 공급자(persistence provider) 생성한 SQL INSERT 작업 중에 해당 칼럼을 포함해야 하는지 여부를 제어합니다. ✅ 기본값 : true
 * boolean updatable       : 영속성 공급자(persistence provider) 생성한 SQL UPDATE 작업 중에 해당 칼럼을 포함해야 하는지 여부를 제어합니다. ✅ 기본값 : true
 * String columnDefinition : 칼럼에 대한 DDL을 생성할 때 SQL 조각입니다. 추론된 타입의 칼럼을 생성하려면 이 SQL을 기본값으로 사용합니다.
 * int length              : 문자열 타입의 칼럼 길이를 지정합니다. ✅ 기본값 : 255
 * int precision           : 숫자 타입의 전체 자릿수를 지정합니다. ✅ 기본값 : 0
 * int scale               : 숫자 타입의 소수점 자릿수를 지정합니다. ✅ 기본값 : 0
 */

/*
 * @Table 어노테이션의 속성들
 * String name             : 매핑할 테이블 이름을 지정. ✅ 기본값 : 엔티티 이름
 * String catalog          : 테이블의 카탈로그를 지정.
 * String schema           : 테이블의 스키마를 지정.
 * 🔴 카탈로그와 스키마의 개념은 DBMS 종류마다 아키텍쳐가 다르다.
 * 🔴 MariaDB의 카탈로그, 스키마 확인 쿼리 SELECT * FROM INFORMATION_SCHEMA.TABLES;
 * UniqueConstraint[] uniqueConstraints : 테이블에 배치할 unique 제약조건. 복합 고유 키(composite unique key)를 정의하기 위해 사용
 */

/*
CREATE TABLE `employee_test` (
    `commission_pct` double DEFAULT NULL,
    `salary` double DEFAULT NULL,
    `test_col2` char(1) NOT NULL,
    `test_col5` decimal(10,4) DEFAULT 0.0000,
    `emp_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `start_date` datetime(6) DEFAULT NULL,
    `test_col1` datetime(6) DEFAULT NULL,
    `test_col3` varchar(90) NOT NULL,
    `emp_name` varchar(200) NOT NULL DEFAULT '',
    `dept_name` varchar(255) DEFAULT NULL,
    `mail_id` varchar(255) DEFAULT NULL,
    `test_col4` varchar(255) DEFAULT NULL,
    `title` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`emp_id`),
    UNIQUE KEY `UKscp4gde1k1kc9eux9mk57hwvu` (`emp_id`,`mail_id`),
    UNIQUE KEY `UK1atktwqcyw8ciknw2kxs87ikp` (`test_col1`,`test_col2`,`test_col3`,`test_col4`)
)
*/
// https://www.objectdb.com/api/java/jpa/Table
// https://getemoji.com/

