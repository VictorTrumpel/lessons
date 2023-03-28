SELECT Orders.Freight, Customers.CompanyName
FROM Orders 
FULL JOIN Customers
ON Orders.CustomerID = Customers.CustomerID
ORDER BY Freight;

/*
  Технически, мы получим несколько дополнительных записей, у которых в поле Freight записан NULL. 
  Это записи, которые не подошли под условие отбора, поэтому поля, соответствующие другой таблице, 
  заполняются значением NULL.  
*/