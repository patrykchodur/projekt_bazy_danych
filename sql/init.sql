INSERT INTO Obywatel (
	imie,
	nazwisko,
	data_urodzenia,
	plec,
	czlonkowstwo_partii
)
VALUES (
	'admin',
	'admin',
	'19971211',
	'M',
	'wewnetrznaPartia'
);
INSERT INTO DaneLogowania (
	id_obywatela,
	nick,
	haslo
)
SELECT o.id, 'admin', 'admin' FROM Obywatel o WHERE o.imie = 'admin';

INSERT INTO Ministerstwo (
	nazwa_ministerstwa,
	liczba_pracownikow,
	opis_ministerstwa
)
VALUES (
	'Ministerstwo Milosci',
	0,
	'Ministerstwo zajmujace sie leczeniem serc obywateli'
);

INSERT INTO Ministerstwo (
	nazwa_ministerstwa,
	liczba_pracownikow,
	opis_ministerstwa
)
VALUES (
	'Ministerstwo Prawdy',
	0,
	'Ministerstwo stojace na strazy prawdy i dbajace, aby byla ona zawsze aktualna'
);

INSERT INTO Ministerstwo (
	nazwa_ministerstwa,
	liczba_pracownikow,
	opis_ministerstwa
)
VALUES (
	'Ministerstwo Pokoju',
	0,
	'Ministerstwo zajmujace sie walka o pokoj na swiecie'
);

INSERT INTO Ministerstwo (
	nazwa_ministerstwa,
	liczba_pracownikow,
	opis_ministerstwa
)
VALUES (
	'Ministerstwo Obfitosci',
	0,
	'Ministerstwo zajmujace sie planowaniem produkcji i dystrybucja jej owocow'
);

