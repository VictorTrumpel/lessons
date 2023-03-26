SELECT t1.*, t2.*
FROM Customers t1, Customers t2 
WHERE 
    (t1.CustomerID <> t2.CustomerID) AND
    (t1.Region IS NULL) AND
    (t2.Region IS NULL)