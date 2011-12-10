DROP TABLE Cases CASCADE
;
DROP TABLE Damage_Assessment CASCADE
;
DROP TABLE Building CASCADE
;	
DROP TABLE Client CASCADE 
;
DROP TABLE Damage_Classification CASCADE
;
DROP TABLE D_User CASCADE
;


CREATE TABLE D_User 
    ( 
     User_Id INT (20)  NOT NULL AUTO_INCREMENT ,
	 Password VARCHAR (50) NOT NULL,
     Lname VARCHAR (20)  NOT NULL , 
     Fname VARCHAR (20)  NOT NULL , 
     Telephone VARCHAR (20) NOT NULL, 
     Agency VARCHAR (20) , 
     Email VARCHAR (100) NOT NULL,	 
     User_Role 	VARCHAR(15) NOT NULL DEFAULT 'VOLUNTEER',
     IsApproved CHAR(1) NOT NULL DEFAULT 'N',
	 PRIMARY KEY (User_Id)	 
    ) 
;

CREATE TABLE Client 
    ( 
     Client_Id INT (20)  NOT NULL AUTO_INCREMENT, 
     Address VARCHAR (200) , 
     Apt_No VARCHAR (10) , 
     City VARCHAR (15) , 
     State CHAR (2) , 
     Zip_Code VARCHAR (10) , 
     Municipality VARCHAR (200) , 
     County VARCHAR (50) , 
     Last_Name VARCHAR (20) , 
     First_Name VARCHAR (20) ,
	 PRIMARY KEY (Client_Id)	
    ) 
;


CREATE TABLE Building 
    ( 
     Build_Id INT (20)  NOT NULL AUTO_INCREMENT, 	
     Landlord_Name VARCHAR (40) , 
     Contact_information VARCHAR (200) , 
     Dwelling_Type VARCHAR (50)  , 
     Insurance_Flood VARCHAR (50)  , 
     Insurance_Structure VARCHAR (50)  , 
     Insurance_Contents VARCHAR (50)  , 
     Ownership VARCHAR (50)  ,
	 PRIMARY KEY (Build_Id)
    ) 
;

CREATE TABLE Damage_Assessment 
    ( 
     Assessment_Id INT(20)  NOT NULL AUTO_INCREMENT , 
     Structural_Damage VARCHAR (50) , 
     Debris_Amount VARCHAR (50) , 
     Business_Inventory_Loss VARCHAR (50) , 
     Number_Of_Floor SMALLINT NOT NULL DEFAULT -1, 
     Is_There_Basement VARCHAR (5) , 
     Water_Level_Living_Area Int (4) NOT NULL DEFAULT -1, 
     Water_Level_Basement Int (4) NOT NULL DEFAULT -1, 
     Is_Electricity_On VARCHAR (5) , 
     Is_Gas_On VARCHAR (5), 
     Basement_Occupied VARCHAR (5) , 
     Occupied_Description VARCHAR (255) , 
     Classification_Reason VARCHAR (255) , 
     Electrical_service_box VARCHAR (50) ,
     Furnace VARCHAR (50) ,
     Hot_Water_Heater VARCHAR (50) ,
     Washer VARCHAR (50) ,
     Dryer VARCHAR (50) ,
     Stove VARCHAR (50) ,
     Refrigerator VARCHAR (50) ,
	 PRIMARY KEY (Assessment_Id)
	 ) 
;

CREATE TABLE Cases 
    ( 
     Case_Id INT (20)  NOT NULL AUTO_INCREMENT, 
     Comment VARCHAR (255) , 
     Client_Id INT (20)  NOT NULL , 
     Damage_Assessment_Id INT (20)  NOT NULL , 
     Building_Id INT (20)  NOT NULL , 
     User_Id INT (20) , 
     Start_Time DATETIME, 
     Completion_Time DATETIME,
	 PRIMARY KEY (Case_Id),
	 FOREIGN KEY (User_Id) references D_User(User_Id) ,
	 FOREIGN KEY (Client_Id) references Client(Client_Id) ,
	 FOREIGN KEY (Building_Id) references Building(Build_Id),
	 FOREIGN KEY (Damage_Assessment_Id) references Damage_Assessment(Assessment_Id)
    ) 
;
 