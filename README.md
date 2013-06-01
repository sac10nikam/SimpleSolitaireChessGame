SimpleSolitaireChessGame
========================

A Simple Solitaire Chess game in java. 

All chesspieces move as they would in a regular chess game except each move MUST take another piece. 

Goal is remove all but one chess piece from the board. 

K = King
N = Knight
Q = Queen
B = Bishop
R = Rook
P = Pawn

game.txt is used to create the inital board configuration. 

The following is the expected configuration for game.txt

X Y 
. . . .
. . . .
. . . .
. . . .

Where X and Y are the size of the board.
All '.' can be replaced by one of the single letter representations of the pieces found above. 

Cheat - will make the next best possible move (if one exists).
Undo - undos last move
Reset - Resets board to original configuration.
