SELECT Products.ProductName, [Order Details].UnitPrice
FROM [Order Details] INNER JOIN Products
ON 
    [Order Details].UnitPrice < 20 AND
    [Order Details].ProductID = Products.ProductId

/*
Мой запрос выглядел так:

SELECT Products.ProductName, Products.UnitPrice 
FROM Products CROSS JOIN [Order Details]
WHERE 
Products.ProductID = [Order Details].ProductID AND 
[Order Details].UnitPrice < 20

Но моя ключевая ошибка в том, что я выбирал UnitPrice из таблицы Products,
а нужно из [Order Details]

Такой запрос выдаст тоже правильный результат:

SELECT Products.ProductName, [Order Details].UnitPrice 
FROM Products CROSS JOIN [Order Details]
WHERE 
    Products.ProductID = [Order Details].ProductID AND 
    [Order Details].UnitPrice < 20
*/
