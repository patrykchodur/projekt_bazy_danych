CREATE TYPE StopienCzlonkowstwa AS ENUM (
  'proletariusz',
  'zewnetrznaPartia',
  'wewnetrznaPartia'
);

CREATE TABLE Obywatele (
  id SERIAL PRIMARY KEY,
  imie varchar NOT NULL,
  nazwisko varchar NOT NULL,
  data_urodzenia date NOT NULL,
  plec char NOT NULL,
  czlonkowstwo_partii StopienCzlonkowstwa NOT NULL,
  ocena_obywatela int,
  data_smierci date,
  nieobywatel boolean NOT NULL DEFAULT false
);

CREATE TABLE Ministerstwa (
  id SERIAL PRIMARY KEY,
  nazwa_ministerstwa varchar NOT NULL,
  liczba_pracownikow int,
  opis_ministerstwa varchar,
  naczelnik_ministerstwa int NOT NULL
);

CREATE TABLE Praca (
  id int PRIMARY KEY NOT NULL,
  ministerstwo int NOT NULL,
  opis_obowiazkow varchar NOT NULL,
  naczelnik int,
  praca_spoleczna boolean NOT NULL
);

CREATE TABLE GodzinyPracy (
  id SERIAL PRIMARY KEY,
  obywatel int NOT NULL,
  zajecie int NOT NULL,
  poczatek_pracy datetime NOT NULL,
  koniec_pracy datetime NOT NULL,
  czas_przyjscia datetime,
  czas_wyjscia datetime,
  uwagi_i_odstepstwa varchar
);

CREATE TABLE Aktywnosci (
  id SERIAL PRIMARY KEY,
  opis_aktywnosci varchar,
  obowiazkowa boolean NOT NULL,
  poczatek_aktywnosci datetime NOT NULL,
  koniec_aktywnosci datetime,
  miejsce_aktywnosci varchar,
  uwagi_i_odstepstwa varchar
);

CREATE TABLE UczestnictwoWAktywnosci (
  id SERIAL PRIMARY KEY,
  aktywnosc int NOT NULL,
  obywatel int NOT NULL,
  obecnosc boolean NOT NULL,
  stopien_zaangazowania int,
  czas_przybycia datetime
);

CREATE TABLE Rozmowy (
  id SERIAL PRIMARY KEY,
  poczatek_rozmowy datetime NOT NULL,
  koniec_rozmowy datetime,
  transkrypcja_rozmowy varchar NOT NULL,
  stopien_niebezpieczenstwa int,
  uwagi_i_odstepstwa varchar
);

CREATE TABLE UczestnicyRozmowy (
  id SERIAL PRIMARY KEY,
  obywatel int NOT NULL,
  rozmowa int NOT NULL,
  moment_dolaczenia datetime NOT NULL,
  moment_zakonczenia datetime,
  uwagi_i_odstepstwa varchar
);

CREATE TABLE Donosy (
  id serial,
  obywatel_skladajacy int NOT NULL,
  obywatel_podejrzany int NOT NULL,
  data_zdarzenia datetime,
  miejsce_zdarzenia varchar,
  opis_zdarzenia varchar
);

CREATE TABLE Myslozbrodnie (
  id SERIAL PRIMARY KEY,
  obywatel int NOT NULL,
  stopien_niebezpieczenstwa int,
  powiazany_donos int,
  funkcjonariusz_prowadzacy int
);

ALTER TABLE Ministerstwa ADD FOREIGN KEY (naczelnik_ministerstwa) REFERENCES Obywatele (id);

ALTER TABLE Praca ADD FOREIGN KEY (ministerstwo) REFERENCES Ministerstwa (id);

ALTER TABLE Praca ADD FOREIGN KEY (naczelnik) REFERENCES Obywatele (id);

ALTER TABLE GodzinyPracy ADD FOREIGN KEY (obywatel) REFERENCES Obywatele (id);

ALTER TABLE GodzinyPracy ADD FOREIGN KEY (zajecie) REFERENCES Praca (id);

ALTER TABLE UczestnictwoWAktywnosci ADD FOREIGN KEY (aktywnosc) REFERENCES Aktywnosci (id);

ALTER TABLE UczestnictwoWAktywnosci ADD FOREIGN KEY (obywatel) REFERENCES Obywatele (id);

ALTER TABLE UczestnicyRozmowy ADD FOREIGN KEY (obywatel) REFERENCES Obywatele (id);

ALTER TABLE UczestnicyRozmowy ADD FOREIGN KEY (rozmowa) REFERENCES Rozmowy (id);

ALTER TABLE Donosy ADD FOREIGN KEY (obywatel_skladajacy) REFERENCES Obywatele (id);

ALTER TABLE Donosy ADD FOREIGN KEY (obywatel_podejrzany) REFERENCES Obywatele (id);

ALTER TABLE Myslozbrodnie ADD FOREIGN KEY (obywatel) REFERENCES Obywatele (id);

ALTER TABLE Myslozbrodnie ADD FOREIGN KEY (powiazany_donos) REFERENCES Donosy (id);

ALTER TABLE Myslozbrodnie ADD FOREIGN KEY (funkcjonariusz_prowadzacy) REFERENCES Obywatele (id);
