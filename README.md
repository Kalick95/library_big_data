# Library HBASE HIVE
## Collaborateurs
Nous sommes FAZIL Kalickbeck, KONING Thibault en ING5 APP SI 01.
## Partie Java
Nous avons commencé par essayer de développer une application java se connectant à Hbase et Hive, mais nous n'avons pas réussi.
Nous n'avions pas les services Hbase lancés et lorsque nous avons essayé sur le edge de lancer ces services, nous n'avions pas les droits.

Vous pouvez voir nos tentatives de connections à Hbase dans nos commits et Hive sur la branche master actuelle.

Nous avons donc décidé de faire notre projet en ligne de commandes.

## Partie Script
Toutes les commandes que nous listons se lançent dans le edge.
### Intégration de Hive dans Hbase
Nous avons créé une table Hbase dans Hive à l'aide du script setuphive.hql, que nous avons appelé ece_2021_fall_app_1.fazil_koning_hbase_hive_library.

Vous pouvez lancer ce script à l'aide la commande :
```
hive -f setuphive.hql
```
Mais la table est déja créée, donc cela va vous sortir une erreur, vous pouvez donc utiliser notre table déja créée.

### Importation de notre dataset dans la table Hbase
Pour peupler notre table Hbase, nous avons importé notre csv qui se trouve dans le fichier library.zip.

Nous avons envoyé le fichier library.csv dans hdfs à l'adresse suivante /education/ece_2021_fall_app_1/t.koning-ece/library.csv avec la commande  : 
```
hdfs dfs -copyFromLocal ./library.csv /education/ece_2021_fall_app_1/t.koning-ece/
```

Enfin, nous avons importé notre csv dans la table hbase avec la commande suivante : 
```
hbase org.apache.hadoop.hbase.mapreduce.ImportTsv -Dimporttsv.separator=';' -Dimporttsv.columns='HBASE_ROW_KEY,details:ISBN,details:Langue,details:Titre,details:Editeur,details:Date,details:Collection,details:Titre_de_serie,details:Numero,details:Auteur_Dates,details:Dates,details:Auteur_secondaire_Dates,details:Type_de_document,details:Nombre_de_localisations,details:Nombre_de_pret_total,details:Nombre_de_prets_2017,details:Nombre_de_pret_annee_2018_au_26_juillet_2018,details:Nombre_dexemplaires,details:Categorie_statistique_1,details:Auteur,details:Co-auteur,details:Auteur_secondaire' ece_2021_fall_app_1:fazil_koning_hbase_hive_library /education/ece_2021_fall_app_1/t.koning-ece/library.csv
```

### Requêtes Hbase
Nous avons un fichier hbase_requests.txt qui contient les requêtes CRUD ainsi que deux requêtes utilisant les filtres.

Vous pouvez lancer ce fichier avec la commande suivante :
```
hbase shell hbase_requests.txt
```

### Requêtes Hive
Nous avons un fichier hive-requests.hql qui contient des requêtes plus complexes en HQL.
Vous pouvez lancer ce fichier avec la commande suivante :
```
hive -f hive-requests.hql
```

### Commandes à lancer
```
hive -f setuphive.hql
```

```
hbase org.apache.hadoop.hbase.mapreduce.ImportTsv -Dimporttsv.separator=';' -Dimporttsv.columns='HBASE_ROW_KEY,details:ISBN,details:Langue,details:Titre,details:Editeur,details:Date,details:Collection,details:Titre_de_serie,details:Numero,details:Auteur_Dates,details:Dates,details:Auteur_secondaire_Dates,details:Type_de_document,details:Nombre_de_localisations,details:Nombre_de_pret_total,details:Nombre_de_prets_2017,details:Nombre_de_pret_annee_2018_au_26_juillet_2018,details:Nombre_dexemplaires,details:Categorie_statistique_1,details:Auteur,details:Co-auteur,details:Auteur_secondaire' ece_2021_fall_app_1:fazil_koning_hbase_hive_library /education/ece_2021_fall_app_1/t.koning-ece/library.csv
```

```
hbase shell hbase_requests.txt
```

```
hive -f hive-requests.hql
```

