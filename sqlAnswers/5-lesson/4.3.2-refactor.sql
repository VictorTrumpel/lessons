/*
    Найдите все заказы, плата за груз у которых (Freight) лежит в диапазоне от 100 до 200, 
    а страна-поставщик ShipCountry -- либо USA, либо France.
*/

SELECT * FROM Orders
WHERE 
    (Freight BETWEEN 100 AND 200) AND
    (ShipCountry IN ('France','USA'))