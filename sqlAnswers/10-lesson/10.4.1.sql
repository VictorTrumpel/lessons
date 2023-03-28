SELECT Products.ProductName, Products.UnitPrice 
FROM Products CROSS JOIN [Order Details]
WHERE 
    Products.ProductID = [Order Details].ProductID AND 
    [Order Details].UnitPrice < 20