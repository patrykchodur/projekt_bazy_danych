INSERT INTO obywatel (imie, nazwisko, data_urodzenia, plec, czlonkowstwo_partii) VALUES ('admin', 'admin', '19971211', 'M', 'wewnetrznaPartia');

INSERT INTO DaneLogowania (id_obywatela, nick, haslo) SELECT o.id, 'admin', 'admin' FROM Obywatel o WHERE o.imie = 'admin';

