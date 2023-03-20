SELECT CategoryId, AVG(UnitPrice) as AVG_PRICE
FROM Products
GROUP BY CategoryId
ORDER BY AVG_PRICE
