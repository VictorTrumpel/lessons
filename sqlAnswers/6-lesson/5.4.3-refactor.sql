/*
    5.4.3. Найдите среднюю, минимальную и максимальную цены по полю UnitPrice 
    из таблицы Order Details.
*/
/*
    в своем решении я 3 раза вызвал команду SELECT, буду знать, что можно вызывать ф-ии агрегирования
    последовательно.
*/

SELECT AVG(UnitPrice), MIN(UnitPrice), MAX(UnitPrice)
FROM [Order Details]