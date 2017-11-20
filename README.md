
Samuel Desgagnés 111 159 167
Alexis-Raphaël Picard Carrier, 910 130 100
Anthony Sylvain, 111 160 665


Bienvenue

Ceci est un fichier lisez-moi pour la remise 2 du tp1 de l'hiver 2017
Spécifications formelles et vérifications logiciels

Équipe #4


EXÉCUTION DU CODE

Nous avons remis l'entièreté du projet éclipse que vous pourrez 
importer dans éclipse pour l'exécuter via le bouton "Run" fourni par le logiciel eclipse.

En démarrant le programme, assurez-vous d'entrer un argument de type integer. Pour entrer ce paramètre, il suffit
d'aller dans le "run configuration" et ensuite dans l'onglet argument pour ajouter un argument. 
Ensuite la simulation débutera en appuyant sur Run.


INTERPRÉTATION DES SORTIES

"Train (ligne) - (identifiant de train) est en panne." 
-Le train spécifié est en panne

"Train (ligne) - (identifiant de train) est réparé." 
-Le train spécifié est réparé

"Train (ligne) - (identifiant de train) entre dans le tronçon (nom du tronçon)"
-Le train spécifié entre dans le tronçon partagé spécifié.

"Train (ligne) - (identifiant de train) sort du tronçon (nom du tronçon)"
-Le train spécifié sort du tronçon partagé spécifié.

"Train (ligne) - (identifiant de train) a un délai de 1 seconde." 
-Le train spécifié a un délai d'attente de 1 seconde.


PANNE, RÉPARATION ET DÉLAI
Comme il n'y avait rien de spécifié dans l'énoncé en rapport avec la simulation de panne et de délai,
nous avons programmé une fonction aléatoire qui déclenche pour chaque train, selon une probabilité, un délai ou une panne.


MOINS DE PRIORITÉ REQ1 POUR TRAIN DE LIGNE A
Pour donner moins de priorité à l'entrée des train A dans le tronçon partagé AB, nous avons donné la priorité d'entrée au train de ligne B.
Toutefois, si le trainB-0 tombe en délai ou en panne, les trains A pourront passer tant et aussi longtemps que ce train B-0 ne sera pas prêt.
Une fois que B-0 fera une demande pour entrer, les trains A cesseront d'entrer et sortiront pour laisser la place aux trainB.
En cours de route, si un train B (de # 13 par exemple) veut entrer et que des trains A sont sur le tronçonAB, ils cesseront d'entrer et laisseront la 
place aux trains B jusqu'à ce que ceux-ci soient tous sortis. Cela s'applique pour tous les numéros de train.