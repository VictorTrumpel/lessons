SELECT Products.ProductName, [Order Details].UnitPrice
FROM Products JOIN [Order Details]
ON Products.ProductID = [Order Details].ProductID