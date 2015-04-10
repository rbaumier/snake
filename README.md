# Snake


## Bibliographie

### Principe du jeu

Le jeu du snake consiste à contrôler un serpent qui fait au départ 3 unités de longueur.
Afin de gagner un niveau, le joueur doit manger des fruits afin de faire grandir le serpent pour atteindre une certaine taille.

Afin d’atteindre la fin du niveau, le joueur doit éviter tout contact avec :
- son corps
- le labyrinthe
- les bords de l’écran

Seule la tête du snake peut être déplacée par le joueur, le reste du corps suit suivant les
mouvements de la tête.

<p align="center">
  <img src="./docs/waow.gif"/>
</p>


### Format du jeu

Le plateau du jeu est un carré de 50 cases de côté.

Modes de jeu :
- Survival Mode
- Arcade Mode


#### Survival Mode

Choisissez votre niveau et essayer d’atteindre le plus haut score possible sans entrer en collision avec un mur ou le corps du serpent.


#### Arcade Mode

Vous commencez au niveau 1 et devez atteindre le score de 25 pour passer au niveau suivant. Le fait de passer au niveau supérieur augmente petit à petit la difficulté.


### Itérations

- Perd quand le snake est coincé (par lui-même ou le bord de la map).
- Le snake grossit lorsqu’il mange un objet.
- Sauvegarder les meilleurs scores et afficher le classement à la fin d’une partie.
- Bouton pause/quitter(&sauvegarder?) la partie.
- Consulter les meilleurs scores depuis le menu principal.


## Infographie (définition mathématique de la réprésentation graphique des objets du modèle)

un fruit ne peut pas apparaitre sur une case occupée par :
- un mur
- le corps ou la tete du snake


## Modèle (les objets qu'on va manipuler, etc...)

Liste des objets manipulés :
- fruit
- tête
- corps
- mur

