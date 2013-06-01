import java.util.ArrayList;
import java.util.HashSet;

/*
 * King.java
 *
 * Version:
 * $Id: King.java,v 1.1 2013/05/03 00:02:09 p243-06k Exp $
 * Revisions:
 * $Log: King.java,v $
 * Revision 1.1  2013/05/03 00:02:09  p243-06k
 * Push to team account CVS
 *
 * Revision 1.4  2013/05/02 23:58:26  zkf5289
 * Fully Working Solution with one bug related too queens in the auto solver. But User gameplay is unaffected.
 *
 * Revision 1.3  2013/05/02 03:47:04  zkf5289
 * Chess Pieces should be finished and get all valid neighbors. Solver is not working correctly.
 *
 * Revision 1.2  2013/05/01 21:34:20  zkf5289
 * More work finished on Chess Pieces
 *
 * Revision 1.1  2013/05/01 05:05:58  zkf5289
 * Made most of the chess pieces.
 *
 */

/**
 * King Chess Piece Object Class
 * 
 * @author Zachary Friss <ZKF5289@RIT.EDU>
 * 
 */

public class King extends ChessPiece {

	/*
	 * Creates a bishop object.
	 * 
	 * @param int x - x row location int y - y col location int id - unique id
	 * for piece.
	 */
	public King(int x, int y, int id) {

		super.locale = new Tuple( x, y );
		super.piecename = "King";
		super.id = id;
	}

	/*
	 * Compares current chess piece to given chess piece object and compares all
	 * variables.
	 * 
	 * @param ChessPiece o - chess piece to compare to0
	 * 
	 * @return int -1 if not equal 0 if equal.
	 */
	@Override
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

		HashSet<ChessPiece> clone = (HashSet<ChessPiece>) config.clone();
		ArrayList<Tuple> moves = new ArrayList<Tuple>();
		for ( ChessPiece c : clone ) {
			// south
			if ( c.getLocation().x == this.locale.x + 1
					&& c.getLocation().y == this.locale.y ) {
				moves.add( new Tuple( c.getLocation().x, c.getLocation().y ) );
			}
			// north
			if ( c.getLocation().x == this.locale.x - 1
					&& c.getLocation().y == this.locale.y ) {
				moves.add( new Tuple( c.getLocation().x, c.getLocation().y ) );
			}

			// east
			if ( c.getLocation().x == this.locale.x
					&& c.getLocation().y == this.locale.y + 1 ) {
				moves.add( new Tuple( c.getLocation().x, c.getLocation().y ) );
			}
			// west
			if ( c.getLocation().x == this.locale.x
					&& c.getLocation().y == this.locale.y - 1 ) {
				moves.add( new Tuple( c.getLocation().x, c.getLocation().y ) );
			}

			// southeast
			if ( c.getLocation().x == this.locale.x + 1
					&& c.getLocation().y == this.locale.y + 1 ) {
				moves.add( new Tuple( c.getLocation().x, c.getLocation().y ) );
			}

			// northeast
			if ( c.getLocation().x == this.locale.x - 1
					&& c.getLocation().y == this.locale.y + 1 ) {
				moves.add( new Tuple( c.getLocation().x, c.getLocation().y ) );
			}

			// northwest
			if ( c.getLocation().x == this.locale.x - 1
					&& c.getLocation().y == this.locale.y - 1 ) {
				moves.add( new Tuple( c.getLocation().x, c.getLocation().y ) );
			}
			// southwest
			if ( c.getLocation().x == this.locale.x + 1
					&& c.getLocation().y == this.locale.y - 1 ) {
				moves.add( new Tuple( c.getLocation().x, c.getLocation().y ) );
			}

		}
		// TODO Auto-generated method stub
		return moves;
	}

}
