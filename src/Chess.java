import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Chess.java
 *
 * Version:
 * $Id: Chess.java,v 1.2 2013/05/03 00:09:46 p243-06k Exp $
 * Revisions:
 * $Log: Chess.java,v $
 * Revision 1.2  2013/05/03 00:09:46  p243-06k
 * Removed excess validate calls
 *
 * Revision 1.1  2013/05/03 00:02:07  p243-06k
 * Push to team account CVS
 *
 * Revision 1.5  2013/05/02 23:58:25  zkf5289
 * Fully Working Solution with one bug related too queens in the auto solver. But User gameplay is unaffected.
 *
 * Revision 1.4  2013/05/02 20:48:17  zkf5289
 * Finished Cheat, undo, reset
 *
 * Revision 1.3  2013/05/02 19:58:02  zkf5289
 * Gui in mostly working order. Still lots of bugs to be fixed.
 *
 * Revision 1.2  2013/05/02 06:52:45  zkf5289
 * Have initial board state showing and buttons are made. Updating of board does not seem to be working.
 *
 * Revision 1.1  2013/05/02 05:55:39  zkf5289
 * Solver now works and started on GUI
 *
 */

/**
 * @author Friss
 * 
 */

public class Chess extends JFrame {

	private JTextField textField;

	private ChessModel model;

	private ArrayList<BoardButton> buttons = new ArrayList<BoardButton>();

	private String statusmsg = "";

	/**
	 * Creates a Chess Game GUI to use and interact with. .
	 * 
	 */
	public Chess(ChessModel chessModel) {

		this.model = chessModel;

		setLayout( new BorderLayout() );

		JPanel panel1 = new JPanel();

		panel1.setLayout( new GridLayout( model.getRows(), model.getCols() ) );

		ButtonListener listener = new ButtonListener();
		boolean even = true;
		String[][] board = model.getBoard( model.getConfig() );

		for ( int i = 0; i < model.getRows(); i++ ) {
			for ( int j = 0; j < model.getCols(); j++ ) {
				BoardButton button = new BoardButton( new Tuple( i, j ) );
				button.setForeground( Color.WHITE );
				if ( even ) {
					button.setBackground( Color.BLACK );
				} else {
					button.setBackground( Color.RED );
				}
				String text = board[i][j];
				if ( Character.isLetter( text.charAt( 0 ) ) ) {
					button.setText( text );
				}
				button.setBorderPainted( true );
				button.setContentAreaFilled( false );
				button.setOpaque( true );
				button.setFocusPainted( false );
				button.addActionListener( listener );
				panel1.add( button );
				buttons.add( button );
				even = !even;
			}

		}

		JPanel mainpanel = new JPanel( new BorderLayout() );
		textField = new JTextField();
		textField.setForeground( Color.BLACK );
		textField
				.setText( "Moves :" + model.getMoveCount() + "   " + statusmsg );
		mainpanel.add( textField, BorderLayout.NORTH );
		mainpanel.add( panel1, BorderLayout.CENTER );

		add( mainpanel, BorderLayout.CENTER );

		JPanel panel3 = new JPanel( new FlowLayout() );

		JButton button = new JButton( "Help" );
		button.setForeground( Color.RED );
		button.addActionListener( listener );
		panel3.add( button );

		button = new JButton( "Cheat" );
		button.setForeground( Color.RED );
		button.addActionListener( listener );
		panel3.add( button );

		button = new JButton( "Undo" );
		button.setForeground( Color.RED );
		button.addActionListener( listener );
		panel3.add( button );

		button = new JButton( "Reset" );
		button.setForeground( Color.RED );
		button.addActionListener( listener );
		panel3.add( button );

		mainpanel.add( panel3, BorderLayout.SOUTH );

		setTitle( "Chess | Zachary Friss | ZKF5289" );
		setSize( 500, 500 );
		setLocation( 100, 100 );
		setVisible( true );
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

	}

	/*
	 * ButtonListener handles button presses in the GUI and calls the correct
	 * model method.
	 */
	class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			Object button = e.getSource();
			if ( button instanceof BoardButton ) {

				Tuple num = ((BoardButton) button).getPos();

				HashSet<ChessPiece> board = model.getConfig();

				for ( ChessPiece c : board ) {
					if ( c.getLocation().compareTo( num ) == 0 ) {

						model.makeMove( c, model.getConfig() );

						update();

					} else {

					}
				}

			} else {

				if ( e.getActionCommand().compareTo( "Reset" ) == 0 ) {
					model.reset();
					update();

				} else if ( e.getActionCommand().compareTo( "Help" ) == 0 ) {
					HelpBox thehelp = new HelpBox(
							buttons.get( 0 ).getSize().width * model.getRows() );
				}

				if ( !model.isGoal( model.getConfig() ) ) {
					if ( e.getActionCommand().compareTo( "Cheat" ) == 0 ) {

						if(!model.cheat()){
						textField.setText( "Moves: " + model.getMoveCount() + " No Solution with current configuration. Press Undo or Reset the game." );
						}else{
							update();
						}

						
					} else if ( e.getActionCommand().compareTo( "Undo" ) == 0 ) {
						model.undo();
						update();

					}

				}
			}
		}
	}

	/*
	 * Updates the board visually and all text fields with current instructions.
	 * 
	 * @param none
	 */
	private void update() {

		String[][] board = model.getBoard( model.getConfig() );

		for ( BoardButton b : buttons ) {
			int row = b.getPos().x;
			int col = b.getPos().y;
			String text = board[row][col];

			if ( Character.isLetter( text.charAt( 0 ) ) ) {
				b.setText( text );
				b.setForeground( Color.WHITE );

			} else {
				b.setText( "" );
			}

		}

		int status = model.moveStatus();

		switch ( status ) {

		case 0:
			statusmsg = "Select the first piece.";
			break;
		case 1:
			statusmsg = "Select the second piece.";
			break;
		case 2:
			statusmsg = "Invalid Move: Undo or select a new piece.";
			break;
		}
		if ( model.isGoal( model.getConfig() ) ) {
			statusmsg = "End of Game! Winner!";
		}
		textField.setText( "Moves: " + model.getMoveCount() + " " + statusmsg );
		validate();
		
	}

	/**
	 * Main method that runs the program and creates a GUI.
	 * 
	 * @param args
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

				ChessModel gameModel = new ChessModel( rows, cols, pieces );

				Chess game = new Chess( gameModel );

//				System.out.println( pieces );
//				gameModel.printBoard( pieces );
//				ArrayList<HashSet<ChessPiece>> steps;
//				Solver<HashSet<ChessPiece>> chessSolver = new Solver<HashSet<ChessPiece>>(
//						gameModel );
//
//				steps = chessSolver.solve();
//
//				if ( steps != null ) {
//					for ( int i = 0; i < steps.size(); i++ ) {
//						System.out.println( "Step " + i + ": " );
//						game.model.printBoard( steps.get( i ) );
//						game.model.update( steps.get( i ) );
//						System.out.println( steps.get( i ) );
//						game.update();
//						System.out.println( gameModel.getMoveCount() );
//						try {
//							Thread.sleep( 1000 );
//						} catch ( InterruptedException e ) {
//							e.printStackTrace();
//						}
//
//					}
//				} else {
//					System.out.println( "No solution." );
//
//				}

			} catch ( IOException e ) {
				e.printStackTrace();
				return;
			}

		}
	}
}
