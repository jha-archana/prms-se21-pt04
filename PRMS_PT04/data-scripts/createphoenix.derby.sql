set schema APP;

-- -----------------------------------------------------
-- Table "roles"
-- -----------------------------------------------------
CREATE  TABLE "role" (
  "role" VARCHAR(15) NOT NULL ,
  "accessPrivilege" VARCHAR(45) DEFAULT NULL ,
  PRIMARY KEY ("role") );

CREATE UNIQUE INDEX "role_UNIQUE" ON "role" ("role" ASC) ;


-- -----------------------------------------------------
-- Table "user"
-- -----------------------------------------------------

CREATE  TABLE "user" (
  "id" VARCHAR(40) NOT NULL ,
  "password" VARCHAR(45) DEFAULT NULL ,
  "name" VARCHAR(45) DEFAULT  NULL ,
  "role" VARCHAR(256) DEFAULT  NULL ,
  PRIMARY KEY ("id") 
);

CREATE UNIQUE INDEX "id_UNIQUE" ON "user" ("id" ASC) ;

CREATE INDEX "role_index" ON "user" ("role" ASC) ;

-- -----------------------------------------------------
-- Insert Data For Table "role"
-- -----------------------------------------------------

-- role, access privilege
insert into "role" values('presenter','radio program presenter');
insert into "role" values('manager', 'station manager');
insert into "role" values('admin', 'system administrator');
insert into "role" values('producer', 'program producer');


-- -----------------------------------------------------
-- Insert Data For Table "user"
-- -----------------------------------------------------

-- id, password, name, role
insert into "user" values('dilbert', 'dilbert', 'dilbert, the hero', 'presenter:producer');
insert into "user" values('wally', 'wally', 'wally, the bludger', 'producer');
insert into "user" values('pointyhead', 'pointyhead', 'pointyhead, the manager', 'manager');
insert into "user" values('catbert', 'catbert', 'catbert, the hr', 'admin:manager');
insert into "user" values('dogbert', 'dogbert', 'dogbert, the CEO', 'producer:admin');
insert into "user" values('mercury', 'mercury', 'mercury', 'presenter');
insert into "user" values('venus', 'venus', 'venus', 'presenter');
insert into "user" values('mars', 'mars', 'mars', 'presenter');
insert into "user" values('saturn', 'saturn', 'saturn', 'presenter');
insert into "user" values('uranus', 'uranus', 'uranus', 'presenter');
insert into "user" values('neptune', 'neptune', 'neptune', 'presenter');
insert into "user" values('pluto', 'pluto', 'pluto', 'presenter');
insert into "user" values('ira', 'ira', 'Ira Steven Behr', 'producer');
insert into "user" values('harve', 'harve', 'Harve Bennett', 'producer');
insert into "user" values('rick', 'rick', 'Rick Berman', 'producer');
insert into "user" values('kenneth', 'kenneth', 'Kenneth Biller', 'producer');
insert into "user" values('brannon', 'brannon', 'Brannon Braga', 'producer');
insert into "user" values('gene', 'gene', 'Gene L. Coon', 'producer');
insert into "user" values('manny', 'manny', 'Manny Coto', 'producer');
insert into "user" values('robert', 'robert', 'Robert H. Justman', 'producer');


-- -----------------------------------------------------
-- Table "radio-program"
-- -----------------------------------------------------
CREATE TABLE "radio-program" (
  "name" VARCHAR(45) NOT NULL ,
  "desc" VARCHAR(100) DEFAULT NULL ,
  "typicalDuration" TIME DEFAULT NULL ,
  PRIMARY KEY ("name") );

CREATE UNIQUE INDEX "name_UNIQUE" ON "radio-program" ("name" ASC) ;


-- -----------------------------------------------------
-- Insert Data For Table "radio-program"
-- -----------------------------------------------------

insert into "radio-program" values('short news', 'summarised 5 minutes broadcasted every 2 hours', '00:05:00');
insert into "radio-program" values('news', 'full news broadcasted four times a day', '00:30:00');
insert into "radio-program" values('top 10', 'countdown music play of top 10 songs of the week', '01:00:00');
insert into "radio-program" values('your choice', 'audinece ask for music album song of thier choice', '01:00:00');
insert into "radio-program" values('opinions', 'discuss, debate or share opinions regarding a theme or subject', '00:30:00');
insert into "radio-program" values('noose', 'black comedy show', '00:30:00');
insert into "radio-program" values('ppk', 'phu chu kang comedy show', '00:30:00');
insert into "radio-program" values('dance floor', 'dance show', '00:30:00');
insert into "radio-program" values('charity', 'president charity show for unfortunate', '00:30:00');

-- -----------------------------------------------------
-- Table "annual-schedule"
-- -----------------------------------------------------
CREATE  TABLE "annual-schedule" (
  "year" INT NOT NULL ,
  "assingedBy" VARCHAR(40) DEFAULT NULL ,
  PRIMARY KEY ("year") ,
  CONSTRAINT "id_as"
    FOREIGN KEY ("assingedBy" )
    REFERENCES "user" ("id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX "id_annual_schedule" ON "annual-schedule" ("assingedBy" ASC) ;

-- -----------------------------------------------------
-- Table "program-slot"
-- Derby dun have DATETIME, using TIMESTAMP
-- -----------------------------------------------------

CREATE  TABLE "program-slot" (
  "duration" VARCHAR(20) NOT NULL ,
  "dateOfProgram" DATE NOT NULL ,
  "startTime" TIME NOT NULL ,
  "program-name" VARCHAR(45) DEFAULT NULL ,
   "presenter-id" VARCHAR(40) DEFAULT NULL,
    "producer-id" VARCHAR(40) DEFAULT NULL,
  PRIMARY KEY ("duration", "dateOfProgram","startTime") ,
  CONSTRAINT "name"
    FOREIGN KEY ("program-name" )
    REFERENCES "radio-program" ("name" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
   CONSTRAINT "presenterid"
    FOREIGN KEY ("presenter-id" )
    REFERENCES "user" ("id" )
ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT "producerid"   
 FOREIGN KEY ("producer-id" )
    REFERENCES "user" ("id" )
ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

CREATE INDEX "name_program_slot" ON "program-slot" ("program-name" ASC) ;

--CREATE UNIQUE INDEX "dateOfProgram_UNIQUE" ON "program-slot" ("dateOfProgram" ASC) ;
INSERT INTO "program-slot" ("duration", "dateOfProgram", "startTime", "program-name", "presenter-id", "producer-id") 
	VALUES ('00:30:00', '2013-09-11', '00:00:00', 'news', 'dilbert', 'wally');

INSERT INTO "program-slot" ("duration", "dateOfProgram", "startTime", "program-name", "presenter-id", "producer-id") 
	VALUES ('01:00:00', '2013-09-11', '00:30:00', 'your choice', 'dilbert', 'dilbert');

INSERT INTO "program-slot" ("duration", "dateOfProgram", "startTime", "program-name", "presenter-id", "producer-id") 
	VALUES ('02:00:00', '2013-09-11', '01:30:00', 'top 10', 'dilbert', 'dogbert');

-- With Time Stamp Conversion 
--INSERT INTO "program-slot" ("duration", "dateOfProgram", "startTime", "program-name", "presenter-id", "producer-id") 
--	VALUES (Time('00:30:00'), TimeStamp('2013-09-11 00:30:00'), TimeStamp('2013-09-11 00:00:00'), 'news', 'dilbert', 'wally');

--INSERT INTO "program-slot" ("duration", "dateOfProgram", "startTime", "program-name", "presenter-id", "producer-id") 
--	VALUES (Time('01:00:00'), TimeStamp('2013-09-11 00:30:00'), TimeStamp('2013-09-11 00:30:00'), 'your choice', 'dilbert', 'dilbert');

--INSERT INTO "program-slot" ("duration", "dateOfProgram", "startTime", "program-name", "presenter-id", "producer-id") 
--	VALUES (Time('02:00:00'), TimeStamp('2013-09-11 00:00:00'), TimeStamp('2013-09
-- -----------------------------------------------------
-- Table "weekly-schedule"
-- -----------------------------------------------------
CREATE  TABLE "weekly-schedule" (
  "startDate" TIMESTAMP NOT NULL ,
  "assignedBy" VARCHAR(40) DEFAULT NULL ,
  PRIMARY KEY ("startDate") ,
  CONSTRAINT "id_ws"
    FOREIGN KEY ("assignedBy" )
    REFERENCES "user" ("id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE UNIQUE INDEX "startDate_UNIQUE" ON "weekly-schedule" ("startDate" ASC) ;

CREATE INDEX "id_assigned_by" ON "weekly-schedule" ("assignedBy" ASC) ;
