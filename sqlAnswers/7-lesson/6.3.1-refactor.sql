-- вторым столбцом я не подсчитал сколько этих типов всего в таблице Contacts
SELECT ContactType, COUNT(ContactType) as Counts
FROM Contacts
GROUP by ContactType