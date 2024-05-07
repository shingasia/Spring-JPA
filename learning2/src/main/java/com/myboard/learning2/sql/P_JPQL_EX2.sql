DELIMITER //
CREATE OR REPLACE PROCEDURE P_JPQL_EX2 (
	IN P_START_SALES    BIGINT,
	IN P_END_SALES      BIGINT
)
`PROC_BODY`:BEGIN
	-- CALL P_JPQL_EX2 (0, 99999999999)
	
	SELECT
	    A.product_code, COUNT(*) AS CNT, COUNT(DISTINCT A.option_code1, A.option_code2) AS OPT_CNT,
	    MIN(A.product_price + A.option_price1 + A.option_price2) AS MIN_PRICE,
	    MAX(A.product_price + A.option_price1 + A.option_price2) AS MAX_PRICE
	FROM   product3 AS A
	GROUP BY A.product_code;
	
	
	CREATE TEMPORARY TABLE IF NOT EXISTS TMP_PRODUCT_OD_CNT (
		od_pcode   BIGINT(20),
		od_ocode1  VARCHAR(255),
		od_ocode2  VARCHAR(255),
		od_cnt     BIGINT(20),
		PRIMARY KEY(od_pcode, od_ocode1, od_ocode2)
	) ENGINE=MEMORY;
	
	INSERT INTO TMP_PRODUCT_OD_CNT
	SELECT od_pcode, od_ocode1, od_ocode2, SUM(od_cnt) AS od_cnt
	FROM customer3_product3
	GROUP BY od_pcode, od_ocode1, od_ocode2;
	
	
	SELECT A.od_pcode AS od_product_code,
			 A.od_ocode1 AS od_option_code1,
			 A.od_ocode2 AS od_option_code2,
			 B.product_name,
	       A.od_cnt * (B.product_price + B.option_price1 + B.option_price2) AS total_sales
	FROM   TMP_PRODUCT_OD_CNT AS A
	INNER JOIN
	       product3 AS B
	ON     A.od_pcode  = B.product_code
	AND    A.od_ocode1 = B.option_code1
	AND    A.od_ocode2 = B.option_code2
	WHERE  (CASE WHEN P_START_SALES IS NOT NULL THEN P_START_SALES ELSE 0 END)
			  <=
			 (CASE WHEN P_START_SALES IS NOT NULL THEN A.od_cnt * (B.product_price + B.option_price1 + B.option_price2) ELSE 0 END)
	AND    (CASE WHEN P_END_SALES IS NOT NULL THEN P_END_SALES ELSE 0 END)
			  >=
			 (CASE WHEN P_END_SALES IS NOT NULL THEN A.od_cnt * (B.product_price + B.option_price1 + B.option_price2) ELSE 0 END)
	ORDER BY total_sales DESC;
	
	
	DROP TEMPORARY TABLE IF EXISTS TMP_PRODUCT_OD_CNT;
END ; //
DELIMITER ;