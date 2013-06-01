import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;


/*
 * HelpBox.java
 *
 * Version:
 * $Id: HelpBox.java,v 1.1 2013/05/03 00:02:08 p243-06k Exp $
 * Revisions:
 * $Log: HelpBox.java,v $
 * Revision 1.1  2013/05/03 00:02:08  p243-06k
 * Push to team account CVS
 *
 * Revision 1.1  2013/05/02 23:58:26  zkf5289
 * Fully Working Solution with one bug related too queens in the auto solver. But User gameplay is unaffected.
 *
 */

/**
 * @author Friss
 *
 */

public class HelpBox extends JFrame {

	/**
	 * 
	 */
	public HelpBox(int size){
		double framesize = size * .75;
		int frame = (int)framesize;
		
		 
		// Set the border layout for the frame
        setLayout(new BorderLayout());

        // Create panel1 for the button and
        // use a grid layout
        JTextArea textArea = new JTextArea(
        	    "How to Play:\n" +
        	    "Click on one Chess Piece to move and then the piece to move to." +
        	    "The game will keep track of the number of moves you make. \n\n " +
        	    "Buttons: \n" +
        	    "Undo: Undo last move \n"+
        	    "Reset: Resets the board back to the starting configuration. \n"+
        	    "Cheat: Completes the next best move. \n"+
        	    "Help: Opens this box. \n"
        	);
        	textArea.setEditable(false);
        	textArea.setLineWrap(true);
        	textArea.setWrapStyleWord(true);
        	textArea.setMargin(new Insets(10,10,10,10));
        
       
        JPanel mainpanel = new JPanel(new BorderLayout());
        mainpanel.add( textArea, BorderLayout.CENTER );
        add(mainpanel, BorderLayout.CENTER);
        setTitle("Chess Game Help");
        setSize(frame, frame);
        setLocation(200, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// TODO Auto-generated method stub

	}

}
