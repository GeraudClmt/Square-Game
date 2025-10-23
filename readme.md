# TicTacToe
## Introduction
Un jeu de plateau de la société square game, codé en Java

## Les classes
`TicTacToe` :
- tableau a deux dimensions de `Cell`
- la taille du plateau
- un attribut `player1` de type `Player`
- un attribut `player2`de type `Player`
- la méthode `display` parcourt le tableau pour afficher son contenu
- la méthode `getMoveFromPlayer` récupère l’entrée utilisateur, X et Y, qui doit être un entier dans le tableau de jeux et doit être une case libre. Recommence tant que ce n'est pas valide.
- la méthode `setOwner` prend en paramètre les coordonnées X et Y, et le Player. Met à jour le tableau pour ajouter la case du joueur
- la méthode `play` qui gère la logique du jeu, le jeu s’arrête si les 9 cases sont remplies
- la méthode `isOver` retourne ”true” si trois pions sont alignés ou si le plateau est rempli

`Cell`:
- un attribut `representation` qui stock le contenu de la `Cell`
- une méthode qui retourne son contenu

`Player` :
- Un attribut `representation` qui prend comme valeur “X” ou “O”
- Une méthode `getRepresentation` qui retourne la representation

## Diagramme UMLœ
```mermaid
---
title: Jeu TicTacToe
---
classDiagram
    Main --> SquareGame : use
    SquareGame --> Game : use
    Game <|-- TicTacToe : extends
    Game <|-- Gomoku : extends
    Game <|-- Connect4 : extends
    Game --> Board : use
    Game --> Cell : use
    Game --> Player : use
    Game --> View : use
    Game --> InteractionUtilisateur : use
    Board --> Cell : use
    Player <|-- HumanPlayer : extends
    Player <|-- ArtificialPlayer : extends

    class Main{
        +main()
    }
    class SquareGame{
        +start()
    }
    class Game{
        <<abstract>>
        #player1 : Player
        #player2 : Player
        #board : Board
        #width : int
        #height : int
        #countTurnPlayed : int
        #winningLength : int
        #signList : String[]
        +setOwner() void
        +isOver() void
        +getBoardToString() String
        +countAlignement() int
        +play(): void
        +getCoordinates() int[]
        +getCoordinatesForArtificialPlayer() int[]
        +playerTurn() void
    }
    class TicTacToe{
    }
    class Gomoku{
    }
    class Connect4{
        -rand : Random
        +getCoordinates() int[]
        +getCoordinatesForArtificialPlayer() int[]
        -getRow() int
    }
    class Board{
        -cells : Cell[][]
        +toString() : String
        +getCell() : Cell
        +setCell() : void
        +getWidth() : int
        +getHeight() : int
        +isEmptyCell(): Boolean
    }
    class Cell{
        +representation : String
        +getRepresentation() : String
        +isEmpty() : Boolean
    }
    class Player{
        <<abstract>>
        -representation : String
        +getRepresentation() : String
    }
    class HumanPlayer{

    }
    class ArtificialPlayer{

    }
    class View{
        +printMessage() void
    }
    class InteractionUtilisateur{
        -scanner : Scanner
        -view : View
        +getInputInt() : int
        +getSign() String
        +isPositifResponse() Boolean
        + getGameChoice() int
    }
```
## Diagramme de séquence
```mermaid
---
title: Jeu TicTacToe
---
sequenceDiagram
    autonumber
    participant M as Main
    participant SG as SquareGame
    participant IU as InteractionUtilisateur
    participant G as TicTacToe (Game)
    participant B as Board
    participant P1 as Player1
    participant P2 as Player2
    participant V as View

    M->>SG: start()
    SG->>IU: getGameChoice()
    IU-->>SG: choix = TicTacToe
    SG->>G: new TicTacToe()
    G->>B: new Board(width, height)
    G->>P1: new HumanPlayer()
    G->>P2: new ArtificialPlayer()

    loop tant que !G.isOver()
        G->>P1: playerTurn()
        alt joueur humain
            G->>IU: getCoordinates()
            IU-->>G: [x, y]
        else joueur IA
            G->>G: getCoordinatesForArtificialPlayer()
        end
        G->>B: setCell(x, y, P1.getRepresentation())
        G->>B: toString()
        B-->>G: "Plateau actuel"
        G->>V: printMessage("Plateau actuel")


        G->>G: isOver()
    end

    G->>V: printMessage("Fin de la partie !")
```
## Exemple de sortie console
```bash
-------------
|   |   |   |
-------------
|   |   |   |
-------------
|   |   |   |
-------------
```
```bash
-------------
| O |   |   |
-------------
|   | X |   |
-------------
|   |   |   |
-------------
```