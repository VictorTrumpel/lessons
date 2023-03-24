SELECT Products.ProductName, Categories.CategoryName 
FROM Products, Categories
WHERE Products.CategoryID = Categories.CategoryID