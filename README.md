github url:
https://repository.genmymodel.com/duyuefeng0708/3343
Please fork me and after editing send me a pull request.



datebase design url:
https://repository.genmymodel.com/duyuefeng0708/3343



database url:
http://somee.com/DOKA/DOU/DOMP/DOMSSQL/DOMSSQLQuery.aspx?k1k3k5=9E0XWM5bRdzJd92VcIQaSW07h872mWFU16AUKu%2bNCv8%3d

user:duyuefeng0708
pw:Cs33433343

sql server (transact-sql) sql scripts (taking user as an example)

//to know more about the table creation, select RAW to see the full sql codes

CREATE TABLE [User]
(
UserName nchar(50) NOT NULL, 
password nchar(50) NOT NULL,
CONSTRAINT PK_User PRIMARY KEY (UserName)
);

INSERT INTO [User]
VALUES('d','d');

Select * from [User];

CREATE TABLE [Asset]
(
UserName nchar(50) NOT NULL, 
Savings real,
CheckingAccount real,
CreditCard real,
Investment real,
Cash real,
Others real,
CONSTRAINT PK_Asset PRIMARY KEY (UserName),
CONSTRAINT FK_Asset_User FOREIGN KEY (UserName)     
    REFERENCES [User] (UserName) 
);

INSERT INTO [Asset] 
VALUES('du',2.1,1,1,1,1,1);

Select * from [Asset];

CREATE TABLE [Budget]
(
BudgetId uniqueidentifier NOT NULL,
UserName nchar(50) NOT NULL, 
MonthTotalAmount real,
Food real,
Clothes real,
Transportation real,
Groceries real,
Education real,
Health real,
Housing real,
Savings real,
Others real,
BudgetYear nchar(10),
BudgetMonth nchar(10),
CONSTRAINT PK_Budget PRIMARY KEY (BudgetId),
CONSTRAINT FK_Budget_User FOREIGN KEY (UserName)     
    REFERENCES [User] (UserName) 
);

Not inserted yet

Select * from [Budget];

CREATE TABLE [Record]
(
RecordId uniqueidentifier NOT NULL,
UserName nchar(50) NOT NULL, 
Income tinyint,
AssetType nchar(20),
Location nchar(500),
Amount real,
Comment nchar(500),
Date date,
ExpenseType nchar(20),
CONSTRAINT PK_Record PRIMARY KEY (RecordId),
CONSTRAINT FK_Record_User FOREIGN KEY (UserName)     
    REFERENCES [User] (UserName) 
);

INSERT INTO [Record] 
VALUES(NEWID(),'d',0,'Cash','H',100,'C',GETDATE(),'Health');

Select * from [Record];



