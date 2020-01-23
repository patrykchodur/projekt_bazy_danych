enum StopienCzlonkowstwa {
  proletariusz
  zewnetrznaPartia
  wewnetrznaPartia
}

Table Obywatel {
  id serial
  imie varchar [not null]
  nazwisko varchar [not null]
  data_urodzenia date [not null]
  plec char [not null]
  czlonkowstwo_partii StopienCzlonkowstwa [not null]
  ocena_obywatela int
  data_smierci date
  nieobywatel boolean [not null, default: false]
  praca_id int
  
}

Table Ministerstwo {
  id serial
  nazwa_ministerstwa varchar [not null]
  liczba_pracownikow int
  opis_ministerstwa varchar
}

Table Praca {
  id serial
  nazwa varchar
  ministerstwo_id int
  opis_obowiazkow varchar [not null]
  naczelnik_id int
  praca_spoleczna boolean [not null]
  poczatek_pracy timestamp [not null]
  koniec_pracy timestamp [not null]

}

Table Aktywnosc {
  id serial
  opis_aktywnosci varchar
  obowiazkowa boolean [not null]
  poczatek_aktywnosci timestamp [not null]
  koniec_aktywnosci timestamp
  miejsce_aktywnosci varchar
}

Table Obywatel_Aktywnosc {
  aktywnosc_id int [not null]
  obywatel_id int [not null]
  stopien_zaangazowania int // w skali od 0 do 9, -5 gdy nieobecny
}

Table Rozmowa {
  id serial
  poczatek_rozmowy timestamp [not null]
  koniec_rozmowy timestamp
  transkrypcja_rozmowy varchar [not null]
  stopien_niebezpieczenstwa int // dla calej rozmowy
  uwagi_i_odstepstwa varchar
}

Table Obywatel_Rozmowa {
  obywatel_id int [not null]
  rozmowa_id int [not null]
}

Table Donosy {
  id serial
  obywatel_skladajacy int [not null]
  data_zdarzenia timestamp
  miejsce_zdarzenia varchar
  opis_zdarzenia varchar
}

Table Myslozbrodnie {
  id serial
  obywatel_id int [not null]
  stopien_niebezpieczenstwa int
  powiazany_donos int [ref: - Donosy.id]
  funkcjonariusz_id int
}



Ref: Obywatel.id < Obywatel_Aktywnosc.obywatel_id
Ref: Aktywnosc.id < Obywatel_Aktywnosc.aktywnosc_id
Ref: Obywatel.id < Myslozbrodnie.obywatel_id
Ref: Ministerstwo.id < Praca.ministerstwo_id
Ref: Obywatel.id < Obywatel_Rozmowa.obywatel_id
Ref: Rozmowa.id < Obywatel_Rozmowa.rozmowa_id
Ref: Obywatel.praca_id - Praca.id
Ref: Obywatel.id - Praca.naczelnik_id
Ref: Obywatel.id - Myslozbrodnie.funkcjonariusz_id






