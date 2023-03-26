SELECT * FROM Orders
WHERE CustomerID = ANY (
    SELECT CustomerID FROM Customers
    WHERE Region IS NOT NULL
)