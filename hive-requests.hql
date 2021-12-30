-- GET BOOKS WHERE NOMBRE_PRET_TOTAL = 817 ( comparaison avec execution HBASE ) 32 rows in 130 seconds

SELECT * FROM ece_2021_fall_app_1.fazil_koning_hbase_hive_library
WHERE Nombre_de_pret_total = 817;


-- TOP 5 auteurs ayant le plus de pret de livre en bibliothÃque
SELECT AUTEUR, SUM(Nombre_de_pret_total) as cnt  FROM ece_2021_fall_app_1.fazil_koning_hbase_hive_library
GROUP BY Auteur
SORT BY cnt DESC 
LIMIT 5;


-- TOP 5 des auteurs ayant Ã©crit le plus de livre
SELECT AUTEUR, sum(nombre_de_pret_total) as cnt FROM ece_2021_fall_app_1.fazil_koning_hbase_hive_library
GROUP BY AUTEUR 
SORT BY cnt DESC LIMIT 5;


-- TOP 5 livres de Lauer Doris
SELECT titre, nombre_de_pret_total FROM ece_2021_fall_app_1.fazil_koning_hbase_hive_library
WHERE Auteur = 'Lauer, Doris'
SORT BY nombre_de_pret_total DESC
LIMIT 5;


-- Auteurs ayant a la fois des livres pour adulte et des DVD jeunesse
SELECT DISTINCT L1.Auteur FROM ece_2021_fall_app_1.fazil_koning_hbase_hive_library AS L1
INNER JOIN ece_2021_fall_app_1.fazil_koning_hbase_hive_library AS L2 ON L1.Auteur = L2.Auteur
WHERE L1.Type_de_document = 'DVD jeunesse' AND L2.Type_de_document = 'Livre pour adulte';

-- Titre du livre etant present dans le plus de bibliotheques ( localisations )
SELECT Titre, Nombre_de_localisations FROM ece_2021_fall_app_1.fazil_koning_hbase_hive_library
SORT BY  Nombre_de_localisations DESC
LIMIT 1;
