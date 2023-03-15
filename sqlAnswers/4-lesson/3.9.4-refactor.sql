/* Отберите всех сотрудников (Employees) - мужчин (анализируйте поле TitleOfCourtesy). */

SELECT * FROM Employees
WHERE TitleOfCourtesy = 'Dr.' or TitleOfCourtesy = 'Mr.'

/* Смотрел только на поле TitleOfCourtesy, не учел поле TitleOfCourtesy*/