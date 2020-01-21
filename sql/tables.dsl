enum StopienCzlonkowstwa {
  proletariusz
  zewnetrznaPartia
  wewnetrznaPartia
}

Table Obywatele {
  id int [pk, increment]
  imie varchar [not null]
  nazwisko varchar [not null]
  data_urodzenia date [not null]
  plec char [not null]
  czlonkowstwo_partii StopienCzlonkowstwa [not null]
  ocena_obywatela int
  data_smierci date
  nieobywatel boolean [not null, default: false]
}

Table Ministerstwa {
  id int [pk, increment]
  nazwa_ministerstwa varchar [not null]
  liczba_pracownikow int
  opis_ministerstwa varchar
  naczelnik_ministerstwa int [not null, ref: > Obywatele.id]
}

Table Praca {
  id int [pk, not null]
  ministerstwo int [not null, ref: > Ministerstwa.id]
  opis_obowiazkow varchar [not null]
  naczelnik int [ref: > Obywatele.id]
  praca_spoleczna boolean [not null]
}

Table GodzinyPracy {
  id int [pk, increment]
  obywatel int [not null, ref: > Obywatele.id]
  zajecie int [not null, ref: > Praca.id]
  poczatek_pracy datetime [not null]
  koniec_pracy datetime [not null]
  czas_przyjscia datetime
  czas_wyjscia datetime
  uwagi_i_odstepstwa varchar
}

Table Aktywnosci {
  id int [pk, increment]
  opis_aktywnosci varchar
  obowiazkowa boolean [not null]
  poczatek_aktywnosci datetime [not null]
  koniec_aktywnosci datetime
  miejsce_aktywnosci varchar
  uwagi_i_odstepstwa varchar
}

Table UczestnictwoWAktywnosci {
  id int [pk, increment]
  aktywnosc int [not null, ref: > Aktywnosci.id]
  obywatel int [not null, ref: > Obywatele.id]
  obecnosc boolean [not null]
  stopien_zaangazowania int // w skali od 0 do 9, -5 gdy nieobecny
  czas_przybycia datetime
}

Table Rozmowy {
  id int [pk, increment]
  poczatek_rozmowy datetime [not null]
  koniec_rozmowy datetime
  transkrypcja_rozmowy varchar [not null]
  stopien_niebezpieczenstwa int // dla calej rozmowy
  uwagi_i_odstepstwa varchar
}

Table UczestnicyRozmowy {
  id int [pk, increment]
  obywatel int [not null, ref: > Obywatele.id]
  rozmowa int [not null, ref: > Rozmowy.id]
  moment_dolaczenia datetime [not null]
  moment_zakonczenia datetime
  uwagi_i_odstepstwa varchar
}

Table Donosy {
  id serial
  obywatel_skladajacy int [not null, ref: > Obywatele.id]
  obywatel_podejrzany int [not null, ref: > Obywatele.id]
  data_zdarzenia datetime
  miejsce_zdarzenia varchar
  opis_zdarzenia varchar
}

Table Myslozbrodnie {
  id int [pk, increment]
  obywatel int [not null, ref: > Obywatele.id]
  stopien_niebezpieczenstwa int
  powiazany_donos int [ref: - Donosy.id]
  funkcjonariusz_prowadzacy int [ref: > Obywatele.id]
}
