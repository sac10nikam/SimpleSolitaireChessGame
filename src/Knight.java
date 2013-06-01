import java.util.ArrayList;
import java.util.HashSet;

/*
 * Knight.java
 *
 * Version:
 * $Id: Knight.java,v 1.1 2013/05/03 00:02:06 p243-06k Exp $
 * Revisions:
 * $Log: Knight.java,v $
 * Revision 1.1  2013/05/03 00:02:06  p243-06k
 * Push to team account CVS
 *
 * Revision 1.4  2013/05/02 23:58:26  zkf5289
 * Fully Working Solution with one bug related too queens in the auto solver. But User gameplay is unaffected.
 *
 * Revision 1.3  2013/05/02 05:55:41  zkf5289
 * Solver now works and started on GUI
 *
 * Revision 1.2  2013/05/02 03:47:04  zkf5289
 * Chess Pieces should be finished and get all valid neighbors. Solver is not working correctly.
 *
 * Revision 1.1  2013/05/01 05:06:01  zkf5289
 * Made most of the chess pieces.
 *
 */

/**
 * Knight Chess Piece Object Class
 * 
 * @author Zachary Friss <ZKF5289@RIT.EDU>
 * 
 */

public class Knight extends ChessPiece {

	/*
	 * Creates a knight object.
	 * 
	 * @param int x - x row location int y - y col location int id - unique id
	 * for piece.
	 */
	public Knight(int x, int y, int id) {

		super.locale = new Tuple( x, y );
		super.piecename = "Knight";
		super.id = id;
	}

	/*
	 * Overrides absctract class name because King and Knight share "K" as the
	 * first letter.
	 * 
	 * @param none.
	 * 
	 * @return String N
	 */
	@Override
	public String printBoardName() {

		return "N";
	}

	/*
	 * Compares current chess piece to given chess piece object and compares all
	 * variables.
	 * 
	 * @param ChessPiece o - chess piece to compare to0
	 * 
	 * @return int -1 if not equal 0 if equal.
	 */
	public int compareTo(ChessPiece o) {

		if ( this.id != o.id ) {
			return -1;
		} else if ( locale.x != o.getLocation().x
				|| locale.y != o.getLocation().y ) {
			return -1;
		} else if ( piecename != o.piecename ) {
			return -1;
		} else {
			return 0;
		}
	}

	/*
	 * Gets all possible move locations based off current board configuration.
	 * 
	 * @param HashSet<ChessPiece> config - set of all chess pieces in the game.
	 * 
	 * @return ArrayList<Tuple> moves list of all possible move locations.
	 */
	@Override
	public ArrayList<Tuple> getMoves(HashSet<ChessPiece> config) {

		ArrayList<Tuple> moves = new ArrayList<Tuple>();
		for ( ChessPiece c : config ) {
			int abs1 = Math.abs( c.getLocation().x - this.locale.x );
			int abs2 = Math.abs( c.getLocation().y - this.locale.y );

			if ( (abs1 == 1 && abs2 == 2) || (abs1 == 2 && abs2 == 1) ) {
				moves.add( new Tuple( c.getLocation().x, c.getLocation().y ) );
			}

		}

		return moves;
	}

}
