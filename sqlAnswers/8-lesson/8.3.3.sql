SELECT Products.ProductName, Products.UnitPrice, Categories.CategoryName
FROM Products, [Order Details], Categories
WHERE 
    Products.ProductID = [Order Details].ProductID AND 
    Products.[CategoryID] = Categories.CategoryID AND
    [Order Details].UnitPrice < 20