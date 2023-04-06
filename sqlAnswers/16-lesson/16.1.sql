-- для начала удалю таблицы и пересоздам их, но поля RegionID и TerritoryID сделаю
-- как primaryKey

-- 1.
-- DROP TABLE Territories
-- DROP TABLE Region

-- 2. Создаю 2 таблицы с PRIMARY KEY
-- CREATE TABLE Region ( 
--     RegionID int IDENTITY(1,1) PRIMARY KEY, 
--     RegionDescription nchar(50) NOT NULL,
--     TerritoryId int NOT NULL); 

-- CREATE TABLE Territories ( 
--     TerritoryID int IDENTITY(1,1) PRIMARY KEY, 
--     TerritoryDescription nchar(50) NOT NULL,
--     RegionID int NOT NULL); 

-- 3. Добавлю несколько записей в ту и другую таблицу
-- INSERT INTO Territories (TerritoryDescription, RegionID)
-- VALUES ('Terr Descr 1', 1)

-- INSERT INTO Region (RegionDescription, TerritoryId)
-- VALUES ('Reg Descr 1', 1)

-- INSERT INTO Territories (TerritoryDescription, RegionID)
-- VALUES ('Terr Descr 2', 2)

-- INSERT INTO Region (RegionDescription, TerritoryId)
-- VALUES ('Reg Descr 1', 2)

-- 4. Добавляю подходящие индексы для базы
CREATE INDEX RegionID ON Region (RegionID)
CREATE INDEX TerritoryID ON Territories (TerritoryID)

