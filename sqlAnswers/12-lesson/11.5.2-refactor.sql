/*
Задание:

Выведите конкретную информацию по всем пользователям Customers и поставщикам Suppliers -- имя контактной персоны, 
город и страну, а также идентификацию группы (пользователь или поставщик).
*/

/*
Впринципе я получил правильные записи и правильно объединил страницы. Но мне стоит учесть, что все-таки
лучше присваивать типы записям из разных таблиц. В моем же случае, резульаты получились обезличены - 
я не знаю какому типу пренадлежит конкретная запись - Customer или Supplier
*/

SELECT ContactName, City, Country FROM Customers
UNION
SELECT ContactName, City, Country FROM Suppliers

SELECT 'Customer' As Type, ContactName, City, Country FROM Customers 
UNION 
SELECT 'Supplier' As Type, ContactName, City, Country FROM Suppliers 
ORDER BY Type