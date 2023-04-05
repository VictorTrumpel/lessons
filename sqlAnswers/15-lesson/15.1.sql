-- CREATE TABLE Region ( 
--     RegionID int NOT NULL, 
--     RegionDescription nchar(50) NOT NULL,
--     TerritoryId int NOT NULL); 

-- CREATE TABLE Territories ( 
--     TerritoryId int NOT NULL, 
--     TerritoryDescription nchar(50) NOT NULL,
--     RegionID int NOT NULL); 

-- INSERT INTO Territories (TerritoryId, TerritoryDescription, RegionID)
-- VALUES (1, 'Terr Descr 1', 1)

-- INSERT INTO Region (RegionID, RegionDescription, TerritoryId)
-- VALUES (1, 'Reg Descr 1', 1)

-- INSERT INTO Territories (TerritoryId, TerritoryDescription, RegionID)
-- VALUES (2, 'Terr Descr 2', 2)

-- INSERT INTO Region (RegionID, RegionDescription, TerritoryId)
-- VALUES (2, 'Reg Descr 1', 2)

-- SELECT * FROM Territories
-- SELECT * FROM Region