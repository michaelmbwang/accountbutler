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

//to know more about the User, select RAW to see the full sql codes

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
