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
