CREATE SCHEMA IF NOT EXISTS portfolio_db;

DROP TABLE IF EXISTS TBL_COMPANY;
DROP TABLE IF EXISTS TBL_USER;

CREATE TABLE PORTFOLIO_DB.TBL_COMPANY (
  ID INT AUTO_INCREMENT  PRIMARY KEY
  ,NAME VARCHAR(250) NOT NULL
  ,ADDRESS VARCHAR(250)
  ,CREATED_AT DATE NOT NULL
  ,UPDATED_AT DATE NOT NULL
);

CREATE TABLE PORTFOLIO_DB.TBL_USER (
    ID INT AUTO_INCREMENT  PRIMARY KEY
    ,FIRST_NAME VARCHAR(250) NOT NULL
    ,MIDDLE_NAME VARCHAR(250) NOT NULL
    ,LAST_NAME VARCHAR(250) NOT NULL
    ,EMAIL VARCHAR(250) NOT NULL
    ,USERNAME VARCHAR(250) NOT NULL
    ,PASSWORD VARCHAR(250) NOT NULL
    ,CREATED_AT DATE NOT NULL
    ,UPDATED_AT DATE NOT NULL
);