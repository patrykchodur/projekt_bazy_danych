CREATE VIEW wszyscy_obywatele AS
SELECT id, imie, nazwisko FROM obywatel ORDER BY id;

CREATE OR REPLACE FUNCTION ocena_po_dodaniu_myslozbrodni() RETURNS TRIGGER AS $$
	DECLARE
		ocena INTEGER;
		stara_ocena INTEGER;
	BEGIN
		ocena := new.stopien_niebezpieczenstwa;
		IF ocena IS NULL THEN
			RETURN NEW;
		END IF;
		SELECT ocena_obywatela INTO stara_ocena FROM obywatel WHERE id = new.obywatel_id;
		ocena := stara_ocena - ocena;
		UPDATE obywatel SET ocena_obywatela = ocena WHERE id = new.obywatel_id;
		RETURN NEW;
	END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER ocena_trigger AFTER INSERT ON myslozbrodnie
FOR EACH ROW EXECUTE PROCEDURE ocena_po_dodaniu_myslozbrodni();

CREATE OR REPLACE FUNCTION ilosc_pracownikow() RETURNS TRIGGER AS $$
	DECLARE
		id_wczesniejszego_ministerstwa INTEGER;
		id_nowego_ministerstwa INTEGER;
	BEGIN
		IF TG_OP = 'UPDATE' OR TG_OP = 'DELETE' THEN
			IF OLD.praca_id IS NULL THEN
				id_wczesniejszego_ministerstwa := NULL;
			ELSE
				SELECT p.ministerstwo_id INTO id_wczesniejszego_ministerstwa FROM
				praca p WHERE id = OLD.praca_id;
			END IF;

			IF id_wczesniejszego_ministerstwa IS NOT NULL THEN
				UPDATE ministerstwo SET liczba_pracownikow = liczba_pracownikow - 1
				WHERE id = id_wczesniejszego_ministerstwa;
			END IF;
		END IF;

		IF TG_OP = 'UPDATE' OR TG_OP = 'INSERT' THEN
			SELECT p.ministerstwo_id INTO id_nowego_ministerstwa FROM
			praca p WHERE id = NEW.praca_id;

			UPDATE ministerstwo SET liczba_pracownikow = liczba_pracownikow + 1
			WHERE id = id_nowego_ministerstwa;
			RETURN NEW;
		END IF;
		RETURN NULL;
	END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER ilosc_pracownikow_trigger AFTER INSERT OR UPDATE OR DELETE ON obywatel
FOR EACH ROW EXECUTE PROCEDURE ilosc_pracownikow();

