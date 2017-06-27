DROP TABLE KONTO CASCADE CONSTRAINTS;
DROP TABLE DANE_WYPOZYCZENIA CASCADE CONSTRAINTS;
DROP TABLE WYPOZYCZENIE CASCADE CONSTRAINTS;
DROP TABLE KLIENT CASCADE CONSTRAINTS;
DROP TABLE EGZEMPLARZ CASCADE CONSTRAINTS;
DROP TABLE FILM CASCADE CONSTRAINTS;
DROP TABLE REZYSER CASCADE CONSTRAINTS;
DROP TABLE GATUNEK CASCADE CONSTRAINTS;
DROP TABLE REZERWACJE CASCADE CONSTRAINTS;

CREATE TABLE GATUNEK(
ID_GATUNKU NUMBER CONSTRAINT GATUNEK_PK PRIMARY KEY,
NAZWA VARCHAR2(30) NOT NULL
);

CREATE TABLE REZYSER(
ID_REZYSERA NUMBER CONSTRAINT REZYSER_PK PRIMARY KEY,
NAZWA VARCHAR2(30) NOT NULL
);

CREATE TABLE FILM(
ID_FILMU NUMBER CONSTRAINT FILM_PK PRIMARY KEY,
NAZWA VARCHAR2(80) NOT NULL,
OPIS VARCHAR2(300) NOT NULL,
ILOSC NUMBER(3) NOT NULL,
PREMIERA DATE NOT NULL,
ID_GATUNKU NUMBER NOT NULL,
ID_REZYSERA NUMBER NOT NULL,
CONSTRAINT FI_GA_FK FOREIGN KEY (ID_GATUNKU) REFERENCES GATUNEK(ID_GATUNKU),
CONSTRAINT FI_RE_FK FOREIGN KEY (ID_REZYSERA) REFERENCES REZYSER(ID_REZYSERA)
);

CREATE TABLE EGZEMPLARZ(
ID_EGZEMPLARZU NUMBER CONSTRAINT EGZEMPLARZ_PK PRIMARY KEY,
ID_FILMU NUMBER NOT NULL,
CONSTRAINT EG_FI_FK FOREIGN KEY (ID_FILMU) REFERENCES FILM(ID_FILMU)
);

CREATE TABLE KLIENT(
ID_KLIENTA NUMBER CONSTRAINT KLIENT_PK PRIMARY KEY,
IMIE VARCHAR2(30) NOT NULL,
NAZWISKO VARCHAR2(30) NOT NULL,
EMAIL VARCHAR2(30)
);

CREATE TABLE WYPOZYCZENIE(
ID_WYPOZYCZENIA NUMBER CONSTRAINT WYPOZYCZENIE_PK PRIMARY KEY,
DATA_WYPOZYCZENIA DATE NOT NULL,
DATA_ODDANIA DATE
);


CREATE TABLE DANE_WYPOZYCZENIA(
ID_EGZEMPLARZU NUMBER NOT NULL,
ID_KLIENTA NUMBER NOT NULL,
ID_WYPOZYCZENIA NUMBER NOT NULL,
CONSTRAINT DA_EG_FK FOREIGN KEY (ID_EGZEMPLARZU) REFERENCES EGZEMPLARZ(ID_EGZEMPLARZU),
CONSTRAINT DA_KL_FK FOREIGN KEY (ID_KLIENTA) REFERENCES KLIENT(ID_KLIENTA),
CONSTRAINT DA_WY_FK FOREIGN KEY (ID_WYPOZYCZENIA) REFERENCES WYPOZYCZENIE(ID_WYPOZYCZENIA)
);

ALTER TABLE DANE_WYPOZYCZENIA DISABLE CONSTRAINT DA_EG_FK CASCADE;
ALTER TABLE DANE_WYPOZYCZENIA DISABLE CONSTRAINT DA_KL_FK CASCADE;

CREATE TABLE KONTO(
ID_KONTA NUMBER CONSTRAINT KONTO_PK PRIMARY KEY,
LOGIN VARCHAR2(20) NOT NULL CONSTRAINT KONTO_UNI UNIQUE,
HASLO VARCHAR2(30) NOT NULL,
CZY_ADMIN NUMBER NOT NULL CONSTRAINT ADM_CHECK CHECK((CZY_ADMIN = 0) OR (CZY_ADMIN = 1)),
ID_KLIENTA NUMBER NOT NULL CONSTRAINT KLIENT_UNI UNIQUE,
CONSTRAINT KO_KL_FK FOREIGN KEY (ID_KLIENTA) REFERENCES KLIENT(ID_KLIENTA)
);

ALTER TABLE KONTO DISABLE CONSTRAINT KO_KL_FK CASCADE;

CREATE TABLE REZERWACJE(
ID_REZERW NUMBER CONSTRAINT REZERW_PK PRIMARY KEY,
ID_FILMU NUMBER NOT NULL,
ID_KLIENTA NUMBER NOT NULL,
CONSTRAINT REZ_FILM_FK FOREIGN KEY (ID_FILMU) REFERENCES FILM(ID_FILMU),
CONSTRAINT REZ_KLI_FK FOREIGN KEY (ID_KLIENTA) REFERENCES KLIENT(ID_KLIENTA)
);

