SELECT * FROM Orders
WHERE Freight > ALL (
    SELECT UnitPrice FROM Products
)
