CREATE TYPE StopienCzlonkowstwa AS ENUM (
  'proletariusz',
  'zewnetrznaPartia',
  'wewnetrznaPartia'
);

CREATE TABLE Obywatel (
  id serial,
  imie varchar NOT NULL,
  nazwisko varchar NOT NULL,
  data_urodzenia date NOT NULL,
  plec char NOT NULL,
  czlonkowstwo_partii StopienCzlonkowstwa NOT NULL,
  ocena_obywatela int,
  data_smierci date,
  nieobywatel boolean NOT NULL DEFAULT false,
  praca_id int
);

CREATE TABLE Ministerstwo (
  id serial,
  nazwa_ministerstwa varchar NOT NULL,
  liczba_pracownikow int,
  opis_ministerstwa varchar
);

CREATE TABLE Praca (
  id serial,
  nazwa varchar,
  ministerstwo_id int,
  opis_obowiazkow varchar NOT NULL,
  naczelnik_id int,
  praca_spoleczna boolean NOT NULL,
  poczatek_pracy timestamp NOT NULL,
  koniec_pracy timestamp NOT NULL
);

CREATE TABLE Aktywnosc (
  id serial,
  opis_aktywnosci varchar,
  obowiazkowa boolean NOT NULL,
  poczatek_aktywnosci timestamp NOT NULL,
  koniec_aktywnosci timestamp,
  miejsce_aktywnosci varchar
);

CREATE TABLE Obywatel_Aktywnosc (
  aktywnosc_id int NOT NULL,
  obywatel_id int NOT NULL,
  stopien_zaangazowania int
);

CREATE TABLE Rozmowa (
  id serial,
  poczatek_rozmowy timestamp NOT NULL,
  koniec_rozmowy timestamp,
  transkrypcja_rozmowy varchar NOT NULL,
  stopien_niebezpieczenstwa int,
  uwagi_i_odstepstwa varchar
);

CREATE TABLE Obywatel_Rozmowa (
  obywatel_id int NOT NULL,
  rozmowa_id int NOT NULL
);

CREATE TABLE Donosy (
  id serial,
  obywatel_skladajacy int NOT NULL,
  data_zdarzenia timestamp,
  miejsce_zdarzenia varchar,
  opis_zdarzenia varchar
);

CREATE TABLE Myslozbrodnie (
  id serial,
  obywatel_id int NOT NULL,
  stopien_niebezpieczenstwa int,
  powiazany_donos int,
  funkcjonariusz_id int
);

ALTER TABLE Myslozbrodnie ADD FOREIGN KEY (powiazany_donos) REFERENCES Donosy (id);

ALTER TABLE Obywatel_Aktywnosc ADD FOREIGN KEY (obywatel_id) REFERENCES Obywatel (id);

ALTER TABLE Obywatel_Aktywnosc ADD FOREIGN KEY (aktywnosc_id) REFERENCES Aktywnosc (id);

ALTER TABLE Myslozbrodnie ADD FOREIGN KEY (obywatel_id) REFERENCES Obywatel (id);

ALTER TABLE Praca ADD FOREIGN KEY (ministerstwo_id) REFERENCES Ministerstwo (id);

ALTER TABLE Obywatel_Rozmowa ADD FOREIGN KEY (obywatel_id) REFERENCES Obywatel (id);

ALTER TABLE Obywatel_Rozmowa ADD FOREIGN KEY (rozmowa_id) REFERENCES Rozmowa (id);

ALTER TABLE Praca ADD FOREIGN KEY (id) REFERENCES Obywatel (praca_id);

ALTER TABLE Praca ADD FOREIGN KEY (naczelnik_id) REFERENCES Obywatel (id);

ALTER TABLE Myslozbrodnie ADD FOREIGN KEY (funkcjonariusz_id) REFERENCES Obywatel (id);
