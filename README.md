# TP3 - Système de fichiers

Auteur : Romain PERRIN - Doctorant Unistra - ICube équipe SDC (<romain.perrin@unistra.fr>)\
Module : A31 - Conception et Programmation Objet Avancées - DUT S3 - IUT Robert SCHUMAN - 2020/21\
Développé sous Eclipse (Windows 10)

## Oanisation du code

* une classe contenant le main - **InteractiveShell.java** (à lancer dans un termminal)
* un package contenant le modèle - **fileSystem**

Compilation : laisser Eclipse compiler puis allez dans le répertoire bin/\
Il y a un fichier InteractiveShell.class et un répertoire fileSystem/\
Dans un terminal *java InteractiveShell -cp fileSystem*

## Nommage des classes et interfaces

Les objets abstraits du système de fichier sont de type **SystemComponent** (classe abstraite, une interface peut aussi faire l'affaire).\
Les objets concrets sont **File** (type non composite) et **Directory** (type composite) qui héritent de SystemComponent.\
La fabrique est la classe **SystemComponentFactory** qui possède une méthode *getSystemComponent* vérifiant la validité des arguments avant
d'appeler les constructeurs respectivement **File**.*File(...)* et **Directory**.*Directory(...)*.\
La classe **FileSystem** contient le noeud **root** (racine du système) et **workingDir** la répertoire courant (évlouant au cours de l'utilisation du système).\
La classe **Services** contient des méthodes statiques et travaillent sur des objets de type **SystemComponent**.\
La classe **Terminal** contient l'implémentation des méthodes utilisable dans le terminal virtuel.\
La classe **InteractiveShell** est le main fourni sur Moodle et implémente l'utilisation du terminal (JShell).

## Notes diverses

* la classe **SystemComponentFactory** (patron fabrique) n'implémente pas le patron singleton demandé dans le sujet
* la commande *rmdir dirname* est en réalité un *rm -R dirname* et supprime un répertoire s'il est non vide
* la commande *touch* ne permet pas d'ajouter du contenu à un fichier il a donc un contenu par défaut (non vide)
* la racine est créée un seule fois automatiquement dans le constructeur de **FileSystem**, pas besoin de la créer ailleurs.
* la fabrique ne fait que créer un composant, elle ne l'insère pas dans l'arbre du système de fichiers (elle teste en revanche la validité du nom)
* l'insertion/suppression/déplacement dans l'arbre est géré par la classe **FileSystem** (y compris la gestion des noms de fichiers/répertoires dupliqués)
* le support de la commande *mv* a été ajouté toutes les commandes sont donc disponibles
