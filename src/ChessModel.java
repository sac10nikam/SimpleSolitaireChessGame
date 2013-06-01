import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/*
 * ChessModel.java
 *
 * Version:
 * $Id: ChessModel.java,v 1.2 2013/05/03 01:25:38 p243-06k Exp $
 * Revisions:
 * $Log: ChessModel.java,v $
 * Revision 1.2  2013/05/03 01:25:38  p243-06k
 * Fixed movement of rook and bishop and queen.
 *
 * Revision 1.1  2013/05/03 00:02:08  p243-06k
 * Push to team account CVS
 *
 * Revision 1.7  2013/05/02 23:58:24  zkf5289
 * Fully Working Solution with one bug related too queens in the auto solver. But User gameplay is unaffected.
 *
 * Revision 1.6  2013/05/02 20:48:16  zkf5289
 * Finished Cheat, undo, reset
 *
 * Revision 1.5  2013/05/02 19:58:02  zkf5289
 * Gui in mostly working order. Still lots of bugs to be fixed.
 *
 * Revision 1.4  2013/05/02 06:52:44  zkf5289
 * Have initial board state showing and buttons are made. Updating of board does not seem to be working.
 *
 * Revision 1.3  2013/05/02 05:55:39  zkf5289
 * Solver now works and started on GUI
 *
 * Revision 1.2  2013/05/02 03:47:03  zkf5289
 * Chess Pieces should be finished and get all valid neighbors. Solver is not working correctly.
 *
 * Revision 1.1  2013/05/01 01:57:56  zkf5289
 * Initial Push
 *
 */

/**
 * @author Friss
 * 
 */

public class ChessModel extends Puzzle<HashSet<ChessPiece>> {

	private int movesmade;

	private ArrayList<HashSet<ChessPiece>> moves = new ArrayList<HashSet<ChessPiece>>();

	private static int rows;

	private static int cols;

	private HashSet<ChessPiece> currentconfig = new HashSet<ChessPiece>();

	private ArrayList<ChessPiece> currentMoves = new ArrayList<ChessPiece>();

	/*
	 * Creates a chess game model
	 * 
	 * @param int r = number of rows on the board int c = number or columns on
	 * the board HashSet<ChessPiece> config - set of all chess pieces in the
	 * game.
	 */
	public ChessModel(int r, int c, HashSet<ChessPiece> config) {

		this.movesmade = 0;
		super.start = config;
		super.goal = 1;
		this.rows = r;
		this.cols = c;
		this.currentconfig = config;
		this.moves.add( config );
	}

	/*
	 * Returns a deepcopy of the current configuration
	 * 
	 * @param none
	 * 
	 * @return HashSet<ChessPiece> config - set of all chess pieces in the game.
	 */
	public HashSet<ChessPiece> getConfig() {

		return DeepCopy( this.currentconfig );
	}

	/*
	 * Returns the number of rows on the board
	 * 
	 * @param none
	 * 
	 * @rerun int rows number of rows on the board.
	 */
	public static int getRows() {

		return rows;
	}

	/*
	 * Called when a user selects a chess piece button on the board. Then either
	 * tries to make the move if it is a valid more or adds it to a current move
	 * stack.
	 * 
	 * @param Chess Piece c - Chess Piece user pressed HashSet<ChessPiece>
	 * config - set of all chess pieces in the game.
	 * 
	 * @returns boolean true if successful or false if unsuccessful.
	 */
	public boolean makeMove(ChessPiece c, HashSet<ChessPiece> config) {

		switch ( currentMoves.size() ) {
		case 2:
			this.currentMoves.removeAll( currentMoves );

			return false;
		case 0:
			currentMoves.add( c );
			return true;
		case 1:
			currentMoves.add( c );
			if ( checkMove( currentMoves.get( 0 ), c, config ) ) {
				if ( makeMove( c, config ) ) {
					this.currentMoves.removeAll( currentMoves );
					return true;
				}
				return false;
			} else {
				return false;
			}

		default:
			throw new RuntimeException(
					"Internal Error: currentMoves Stack too big." );
		}

	}

	/*
	 * Checks the move if it is a valid move for the chess piece to make
	 * 
	 * @param Chess Piece c1 - chess piece to be moved Chess Piece c2 - chess
	 * piece to be removed HashSet<ChessPiece> config - set of all chess pieces
	 * in the game.
	 * 
	 * @return boolean true if valid false if not.
	 */
	private boolean checkMove(ChessPiece c1, ChessPiece c2,
			HashSet<ChessPiece> config) {

		ArrayList<Tuple> moves = c1.getMoves( config );
		if ( moves.contains( c2.getLocation() ) ) {
			for ( ChessPiece c : config ) {
				if ( c.compareTo( c1 ) == 0 ) {
					HashSet<ChessPiece> newConfig = c.movePiece( c2, config );
					update( newConfig );
					
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}

	/*
	 * Returns the number of columns on the board.
	 * 
	 * @param none.
	 * 
	 * @return int cols number of columns on the board.
	 */
	public static int getCols() {

		return cols;
	}

	/*
	 * Returns the number of moves the user has made in the game.
	 * 
	 * @param none.
	 * 
	 * @return int movesMade number of moves made by user.
	 */
	public int getMoveCount() {

		return movesmade;
	}

	/*
	 * Updates the model to reflect a change made to the board.
	 * 
	 * @param HashSet<ChessPiece> config - set of all chess pieces in the game.
	 */
	public void update(HashSet<ChessPiece> config) {

		HashSet<ChessPiece> copy = DeepCopy( config );
		this.moves.add( copy );
		this.currentconfig = config;
		start = config;
		this.movesmade++;
	}

	/*
	 * Calls reset on the board until all moves had been undone. Then resets
	 * movesMade.
	 * 
	 * @param none.
	 */
	public void reset() {

		while (undo()) {

		}
		this.movesmade = 0;
	}

	/*
	 * Undos the user's last move.
	 * 
	 * @param none.
	 * 
	 * @return boolean true if successful false if not.
	 */
	public boolean undo() {

		if ( this.moves.size() == 1 ) {
			return false;
		} else {
			if ( this.moves.remove( this.moves.size() - 1 ) != null ) {
				this.currentconfig = this.moves.get( moves.size() - 1 );
				start = this.moves.get( moves.size() - 1 );
				return true;
			} else {
				return false;
			}
		}

	}

	/*
	 * Returns the size of the currentMoves stack to determine user move status.
	 * 
	 * @param none.
	 * 
	 * @return int currentMoves stack size.
	 */
	public int moveStatus() {

		return currentMoves.size();
	}

	/*
	 * Prints the current board. 
	 * 
	 * @param HashSet<ChessPiece> config - set of all chess pieces in the game.
	 */
	public static void printBoard(HashSet<ChessPiece> config) {

		String board[][] = new String[getRows()][getCols()];
		for ( int i = 0; i < getRows(); i++ ) {
			for ( int j = 0; j < getCols(); j++ ) {
				board[i][j] = ".";
			}
		}
		for ( ChessPiece c : config ) {
			board[c.getLocation().x][c.getLocation().y] = c.printBoardName();
		}
		for ( String[] row : board ) {
			for ( String piece : row ) {
				System.out.print( piece );
			}
			System.out.println();
		}
	}

	/*
	 * Determines the moves needed to win the game and if solution exists makes the next move. 
	 * 
	 * @param none. 
	 * 
	 * @return true if there is a solution false if no solution. 
	 */
	public boolean cheat() {

		Solver<HashSet<ChessPiece>> chessSolver = new Solver<HashSet<ChessPiece>>(
				this );
		ArrayList<HashSet<ChessPiece>> steps;
		steps = chessSolver.solve();
		if (steps != null){
		update( steps.get( 1 ) );
		return true;
		}else{
			return false;
		}

	}

	/*
	 * Creates the board as a 2D array of Strings. 
	 * 
	 * @param HashSet<ChessPiece> config - set of all chess pieces in the game.
	 * 
	 * @returns String[][] of the current board. 
	 */
	public static String[][] getBoard(HashSet<ChessPiece> config) {

		String board[][] = new String[getRows()][getCols()];
		for ( int i = 0; i < getRows(); i++ ) {
			for ( int j = 0; j < getCols(); j++ ) {
				board[i][j] = ".";
			}
		}
		for ( ChessPiece c : config ) {
			board[c.getLocation().x][c.getLocation().y] = c.printBoardName();
		}
		return board;
	}

	/*
	 * Main Program Run method for the model. Takes a file in and then finds the shortest path solution for it. 
	 * 
	 * @param args - intial board configuration. 
	 */
	public static void main(String[] args) {

		if ( args.length != 1 ) {
			System.out.println( "usage: java Chess input-file" );
		} else {
			String gameFile = args[0];
			BufferedReader reader;
			// redactionsFile wordFile
			try {
				reader = new BufferedReader( new FileReader(
						new File( gameFile ) ) );

			} catch ( FileNotFoundException e ) {
				System.out.println( gameFile + " not found." );
				return;
			}

			HashSet<ChessPiece> pieces = new HashSet<ChessPiece>();
			try {
				String line = reader.readLine();
				String[] split = line.split( "\\s+" );
				line = reader.readLine();
				int rows = Integer.parseInt( split[0] );
				int cols = Integer.parseInt( split[1] );

				int row = 0;
				int id = 0;
				while (line != null) {

					split = line.split( "\\s+" );
					for ( int col = 0; col < split.length; col++ ) {
						if ( split[col].compareTo( "B" ) == 0 ) {
							Bishop bishop = new Bishop( row, col, id );
							pieces.add( bishop );
							id++;
						}
						if ( split[col].compareTo( "Q" ) == 0 ) {
							Queen queen = new Queen( row, col, id );
							pieces.add( queen );
							id++;
						}
						if ( split[col].compareTo( "K" ) == 0 ) {
							King king = new King( row, col, id );
							pieces.add( king );
							id++;
						}
						if ( split[col].compareTo( "N" ) == 0 ) {
							Knight knight = new Knight( row, col, id );
							pieces.add( knight );
							id++;
						}
						if ( split[col].compareTo( "P" ) == 0 ) {
							Pawn pawn = new Pawn( row, col, id );
							pieces.add( pawn );
							id++;
						}
						if ( split[col].compareTo( "R" ) == 0 ) {
							Rook rook = new Rook( row, col, id );
							pieces.add( rook );
							id++;
						}
					}
					line = reader.readLine();
					row++;
				}

				reader.close();

				ChessModel game = new ChessModel( rows, cols, pieces );

				System.out.println( pieces );
				printBoard( pieces );
				ArrayList<HashSet<ChessPiece>> steps;
				Solver<HashSet<ChessPiece>> chessSolver = new Solver<HashSet<ChessPiece>>(
						game );

				steps = chessSolver.solve();
				if ( steps != null ) {
					for ( int i = 0; i < steps.size(); i++ ) {
						System.out.println( "Step " + i + ": " );
						printBoard( steps.get( i ) );
					}
				} else {
					System.out.println( "No solution." );

				}
			} catch ( IOException e ) {
				e.printStackTrace();
				return;
			}

		}

	}

	/*
	 * Deep copies the configuration to be used again. Creates all new ChessPiece objects. 
	 * 
	 * @param HashSet<ChessPiece> config - set of all chess pieces in the game.
	 * 
	 * @return HashSet<ChessPiece> copy - set of all chess pieces in the game.
	 */
	public static HashSet<ChessPiece> DeepCopy(HashSet<ChessPiece> pieces) {

		HashSet<ChessPiece> copy = new HashSet<ChessPiece>();
		for ( ChessPiece c : pieces ) {
			if ( c instanceof Bishop ) {
				Bishop piece = new Bishop( c.getLocation().x,
						c.getLocation().y, c.getID() );
				copy.add( piece );
			}
			if ( c instanceof Queen ) {
				Queen piece = new Queen( c.getLocation().x, c.getLocation().y,
						c.getID() );
				copy.add( piece );
			}
			if ( c instanceof King ) {
				King piece = new King( c.getLocation().x, c.getLocation().y,
						c.getID() );
				copy.add( piece );
			}
			if ( c instanceof Pawn ) {
				Pawn piece = new Pawn( c.getLocation().x, c.getLocation().y,
						c.getID() );
				copy.add( piece );
			}
			if ( c instanceof Knight ) {
				Knight piece = new Knight( c.getLocation().x,
						c.getLocation().y, c.getID() );
				copy.add( piece );
			}
			if ( c instanceof Rook ) {
				Rook piece = new Rook( c.getLocation().x, c.getLocation().y,
						c.getID() );
				copy.add( piece );
			}
		}

		return copy;

	}

	/*
	 * Gets all possible neighbors and moves for the chess pieces on the game board. 
	 * 
	 * @param HashSet<ChessPiece> config - set of all chess pieces in the game.
	 * 
	 * @return ArrayList<HashSet<ChessPiece>> of all possible board configurations. 
	 */
	@Override
	public ArrayList<HashSet<ChessPiece>> getNeighbors(
			HashSet<ChessPiece> config) {

		ArrayList<HashSet<ChessPiece>> neighbors = new ArrayList<HashSet<ChessPiece>>();

		for ( ChessPiece c : config ) {

			HashSet<ChessPiece> clone = DeepCopy( config );

			ArrayList<HashSet<ChessPiece>> moves = c.getNeighbors(
					c.getMoves( clone ), clone );
			for ( HashSet<ChessPiece> neighbor : moves ) {
				neighbors.add( neighbor );
			}
		}

		return neighbors;
	}

	/*
	 * Determines if the given config is the goal to the puzzle. 
	 * 
	 * @param HashSet<ChessPiece> config - set of all chess pieces in the game.
	 * 
	 * @return true or false if goal configuration. 
	 */
	@Override
	public boolean isGoal(HashSet<ChessPiece> config) {

		if ( config != null ) {
			if ( config.size() == 1 ) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
