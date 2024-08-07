SELECT 
    ORDER_ID,
    PRODUCT_ID,
    DATE_FORMAT(OUT_DATE, "%Y-%m-%d") AS OUT_DATE ,
    CASE 
        WHEN OUT_DATE IS NULL THEN '출고미정'
        WHEN DATE_FORMAT(OUT_DATE, '%m-%d') < '05-02' THEN '출고완료'
        WHEN DATE_FORMAT(OUT_DATE, '%m-%d') >= '05-02' THEN '출고대기'
    END AS '출고상태'
FROM 
    FOOD_ORDER
ORDER BY 
    ORDER_ID
;
    