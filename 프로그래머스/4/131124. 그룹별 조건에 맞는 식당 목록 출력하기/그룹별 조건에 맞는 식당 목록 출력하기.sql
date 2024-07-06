-- 코드를 입력하세요
SELECT 
    MEM.MEMBER_NAME,
    RR.REVIEW_TEXT,
    DATE_FORMAT(RR.REVIEW_DATE, '%Y-%m-%d') AS REVIEW_DATE
FROM
    REST_REVIEW AS RR
JOIN
    (SELECT 
        R.MEMBER_ID,
        MMM.MM
    FROM 
        REST_REVIEW AS R
    CROSS JOIN
        (SELECT
            MAX(M) AS MM
        FROM 
            (
            SELECT
                MEMBER_ID,
                COUNT(*) AS M
            FROM 
                REST_REVIEW
            GROUP BY
                MEMBER_ID
        ) AS A
        ) AS MMM
    GROUP BY
        MEMBER_ID
    HAVING
        COUNT(*) = MM) AS B
        ON B.MEMBER_ID = RR.MEMBER_ID
        JOIN
        MEMBER_PROFILE AS MEM
        ON RR.MEMBER_ID = MEM.MEMBER_ID
ORDER BY
    REVIEW_DATE,
    REVIEW_TEXT

    