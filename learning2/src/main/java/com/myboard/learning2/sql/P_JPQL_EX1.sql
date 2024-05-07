DELIMITER //
CREATE OR REPLACE PROCEDURE P_JPQL_EX1 (
	IN P_EMP_ID    VARCHAR(5)
)
`PROC_BODY`:BEGIN
	-- SELECT * FROM employee_test7;
	-- SELECT * FROM department_test2;
	-- CALL P_JPQL_EX1 (NULL);
	
	IF P_EMP_ID IS NOT NULL THEN
	BEGIN
		SELECT
		       A.emp_id, A.emp_name, A.start_date, A.end_date, A.emp_salary, B.dept_name
		FROM   employee_test7 AS A
		LEFT OUTER JOIN
				 department_test2 AS B
		ON     A.dept_id = B.dept_id
		WHERE  A.emp_id = CONVERT(P_EMP_ID, INT);
		
		LEAVE `PROC_BODY`;
	END;
	END IF;
	
	-- 직원정보(결과집합1)
	SELECT
		    A.emp_id, A.emp_name, A.start_date, A.end_date, A.emp_salary, IFNULL(B.dept_name, '인턴') AS dept_name
	FROM   employee_test7 AS A
	LEFT OUTER JOIN
			 department_test2 AS B
	ON     A.dept_id = B.dept_id
	WHERE  NOW(6) BETWEEN A.start_date AND A.end_date
	ORDER BY A.emp_id ASC;
	
	-- 부서별 사람수(결과집합2)
	SELECT
			 A.dept_id, A.dept_name, B.emp_cnt
	FROM   department_test2 AS A
	LEFT OUTER JOIN (
		    SELECT B.dept_id, COUNT(*) AS emp_cnt
		    FROM   employee_test7 AS B
		    GROUP BY B.dept_id
	) AS B
	ON     A.dept_id = B.dept_id
	
	UNION ALL
	
	SELECT A.dept_id, '인턴' AS dept_name, COUNT(*) AS emp_cnt
	FROM   employee_test7 AS A
	WHERE  A.dept_id IS NULL
	GROUP BY A.dept_id;
	
END; //
DELIMITER ;