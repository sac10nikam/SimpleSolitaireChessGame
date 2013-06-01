SimpleSolitaireChessGame
========================

A Simple Solitaire Chess game in java. 

All chesspieces move as they would in a regular chess game except each move MUST take another piece. 

Goal is remove all but one chess piece from the board. 

<h2>Game Pieces</h2>
<ul>
<li>K = King</li>
<li>N = Knight</li>
<li>Q = Queen</li>
<li>B = Bishop</li>
<li>R = Rook</li>
<li>P = Pawn</li>
</ul>

<h2>Configuration</h2>
game.txt is used to create the inital board configuration. 

The following is the expected configuration for game.txt

X Y <br />
. . . .<br />
. . . .<br />
. . . .<br />
. . . .

Where X and Y are the size of the board.<br />
All '.' can be replaced by one of the single letter representations of the pieces found above. 

<h2>Buttons</h2>
Cheat - will make the next best possible move (if one exists).<br />
Undo - undos last move<br />
Reset - Resets board to original configuration.

<h2>Running the Game</h2>
java Chess

Note: Be sure to have game.txt in the same directory as Chess.class
