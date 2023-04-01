/*
    Задание:
    Отберите с помощью LEFT JOIN все записи из таблицы Customers, для которых FK-ключ таблицы Orders пустой.
*/

/*
Мое решение: 

SELECT * FROM Customers
LEFT JOIN Orders
ON Orders.ShipRegion IS NULL

Мои ошибки:
1. Я не доконца понимал что такое FK. FK - это ключ, какое-то значение, которое является для какой-то таблицы PK.
И у таблицы может быть несколько FK, но один PK. 
Например, для таблицы Customers CustomerID - это PK, а для таблицы Orders CustomerID является уже FK.

2. Второе, я не совсем понимал что от меня требуется в задании, а сконцентрировался на синтаксисе SQL.
По факту же, задание можно было бы переформулировать так: 
    Нужно найти всех заказчиков (Customers), которые не сделали заказа.
    Если заказчик не сделал заказ, значит его нет в таблице заказов (Orders).
    Значит нужно найти такие CustomerID из таблицы Customers, которых нет в таблице Orders.

Такой код выдал бы аналогичный результат:

SELECT * FROM Customers
WHERE CustomerID <> ALL (
    SELECT CustomerID FROM Orders
)

На примере кода выше, становится понятно преимущество LEFT JOIN - вместо 2-х запросов 
мы получаем аналогичный результат одним запросом. 
Но возможно, скрорость с которой работают 2 этих выражения одинакова - я не знаю :))

*/

SELECT * FROM Customers
WHERE CustomerID <> ALL (
    SELECT CustomerID FROM Orders
)

SELECT * FROM Customers LEFT JOIN Orders
  ON Orders.CustomerID = Customers.CustomerID
  WHERE Orders.CustomerID IS NULL