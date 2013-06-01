import java.util.ArrayList;
import java.util.HashSet;

/*
 * Rook.java
 *
 * Version:
 * $Id: Rook.java,v 1.3 2013/05/03 01:59:58 p243-06k Exp $
 * Revisions:
 * $Log: Rook.java,v $
 * Revision 1.3  2013/05/03 01:59:58  p243-06k
 * All piecces should move as they should with no errors.
 *
 * Revision 1.2  2013/05/03 01:25:38  p243-06k
 * Fixed movement of rook and bishop and queen.
 *
 * Revision 1.1  2013/05/03 00:02:07  p243-06k
 * Push to team account CVS
 *
 * Revision 1.5  2013/05/02 23:58:26  zkf5289
 * Fully Working Solution with one bug related too queens in the auto solver. But User gameplay is unaffected.
 *
 * Revision 1.4  2013/05/02 05:55:40  zkf5289
 * Solver now works and started on GUI
 *
 * Revision 1.3  2013/05/02 03:47:04  zkf5289
 * Chess Pieces should be finished and get all valid neighbors. Solver is not working correctly.
 *
 * Revision 1.2  2013/05/01 21:34:20  zkf5289
 * More work finished on Chess Pieces
 *
 * Revision 1.1  2013/05/01 05:05:57  zkf5289
 * Made most of the chess pieces.
 *
 */

/**
 * Rook Chess Piece Object Class
 * @author Zachary Friss <ZKF5289@RIT.EDU>
 * 
 */

public class Rook extends ChessPiece {

	/*
	 * Creates a rook object.
	 * 
	 * @param int x - x row location int y - y col location int id - unique id
	 * for piece.
	 */
	public Rook(int x, int y, int id) {

		super.locale = new Tuple( x, y );
		super.piecename = "Rook";
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
		HashSet<ChessPiece> moveable = new HashSet<ChessPiece>();

		for ( ChessPiece c : config ) {
			if ( (c.getLocation().x == this.locale.x
					|| c.getLocation().y == this.locale.y) && c.compareTo( this ) != 0 ) {
				moveable.add( c );
			}

		}

		ChessPiece north = null;
		ChessPiece south = null;
		ChessPiece east = null;
		ChessPiece west = null;

		for ( ChessPiece c : moveable ) {
			// horizontal movement
			if ( c.getLocation().x == this.locale.x ) {
				int relative = c.getLocation().y - this.locale.y;
				if ( relative > 0 ) {
					// east
					if ( east == null
							|| east.getLocation().y > c.getLocation().y ) {
						east = c;
					}

				} else {
					// west
					if ( west == null
							|| west.getLocation().y < c.getLocation().y ) {
						west = c;
					}
				}
			}
			// vertical movement
			if ( c.getLocation().y == this.locale.y ) {
				int relative = c.getLocation().x - this.locale.x;
				if ( relative < 0 ) {
					// north
					if ( north == null
							|| north.getLocation().x < c.getLocation().x ) {
						north = c;
					}

				} else {
					// west
					if ( south == null
							|| south.getLocation().x > c.getLocation().x ) {
						south = c;
					}
				}
			}
		}

		if ( north != null ) {
			moves.add( north.getLocation() );
		}
		if ( south != null ) {
			moves.add( south.getLocation() );
		}
		if ( east != null ) {
			moves.add( east.getLocation() );
		}
		if ( west != null ) {
			moves.add( west.getLocation() );
		}

		return moves;
	}

}
