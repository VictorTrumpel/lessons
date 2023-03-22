SELECT * FROM [Order Details]
WHERE ProductID IN (
    SELECT ProductID FROM Products
    WHERE UnitsInStock > 40
)
